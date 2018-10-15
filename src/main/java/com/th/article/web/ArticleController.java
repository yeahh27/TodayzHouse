package com.th.article.web;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.nhncorp.lucy.security.xss.XssFilter;
import com.th.article.service.ArticleService;
import com.th.article.vo.ArticleSearchVO;
import com.th.article.vo.ArticleVO;
import com.th.common.session.Session;
import com.th.common.web.DownloadUtil;
import com.th.files.vo.FileMapVO;
import com.th.files.vo.FilesVO;
import com.th.link.vo.LinkVO;
import com.th.member.vo.MemberVO;

import io.github.seccoding.web.pager.explorer.PageExplorer;

@Controller
public class ArticleController {
	
	private Logger logger = LoggerFactory.getLogger(ArticleController.class);
	
	@Autowired
	private ArticleService articleService;
	
	//@Value("${upload.path}")
	private String uploadPath = "C:/Users/YEAH/Documents/uploadFiles";
	
	@GetMapping("/")
	public String viewHomepage() {
		return "home";
	}
	
	@GetMapping("/board/{boardId}/articleWrite")
	public String viewArticleWritePage(@PathVariable int boardId) {
		return "article/write" + boardId;
	}
	
 	
	@PostMapping("/board/{boardId}/articleWrite")
	public ModelAndView doArticleWriteAction(@PathVariable int boardId, @Valid @ModelAttribute ArticleVO articleVO, Errors errors
											,@ModelAttribute FileMapVO fileMapVO, HttpSession session, HttpServletRequest request
											,@SessionAttribute(Session.CSRF_TOKEN) String sessionToken) {
		
		if(!articleVO.getToken().equals(sessionToken)) {
			throw new RuntimeException("잘못된 접근입니다.");
		}
		
		ModelAndView view = new ModelAndView("redirect:/board/" + boardId);
		List<FilesVO> fileList = new ArrayList<>();

		for(String index : fileMapVO.getFileMap().keySet()) {
			FilesVO filesVO = fileMapVO.getFileMap().get(index);
			filesVO.setBoardId(articleVO.getBoardId());
			
			for(int i=0; i<filesVO.getFileList().size(); i++) {
				FilesVO addFileVO = new FilesVO();
				addFileVO.setBoardId(boardId);
				addFileVO.setContent(filesVO.getContent());
				addFileVO.setFile(filesVO.getFileList().get(i));
				addFileVO.setIdx(filesVO.getIdx());
				fileList.add(addFileVO);
	
			}
			
			articleVO.setFileVOList(fileList);
			
			// XSS
			XssFilter filter = XssFilter.getInstance("lucy-xss-superset.xml");
			articleVO.setTitle(filter.doFilter(articleVO.getTitle()));
			for(int i=0; i<fileList.size(); i++) {
				articleVO.getFileVOList().get(i).setContent(filter.doFilter(articleVO.getFileVOList().get(i).getContent()));
			}
			
		}
		fileUpload(fileList);
		MemberVO loginMemberVO = (MemberVO) session.getAttribute(Session.MEMBER);
		articleVO.setEmail(loginMemberVO.getEmail());
	
		boolean isSuccess = this.articleService.createArticle(articleVO);

		String paramFormat = "IP:%s, Param:%s, Result:%s";
		logger.debug( String.format(paramFormat, request.getRemoteAddr()
				, articleVO.getTitle() + ", " + articleVO.getEmail() + ", " + articleVO.getFileVOList().toString()
				, view.getViewName()) );	
		
		return view;
	}
	
	private void fileUpload(List<FilesVO> fileList) {
		
		int size = fileList.size();
		
		for(int i=0; i<size;i++) {
			MultipartFile uploadFile = fileList.get(i).getFile();
			
			if(!uploadFile.isEmpty()) {
				// 실제 파일 이름
				String originFileName = uploadFile.getOriginalFilename();
				// 파일 시스템에 저장될 파일의 이름(난수)
				String fileName = UUID.randomUUID().toString();
				
				// 폴더가 존재하지 않는다면, 생성
				File uploadDir = new File(this.uploadPath);
				if(!uploadDir.exists()) {
					uploadDir.mkdirs();
				}
				
				// 파일이 업로드될 경로 지정
				File destFile = new File(this.uploadPath, fileName);
				
				try {
					// 업로드
					uploadFile.transferTo(destFile);
					// DB에 File 정보 저장하기 위한 정보 셋팅
					fileList.get(i).setOriginFileName(originFileName);
					fileList.get(i).setFileName(fileName);
				} catch (IllegalStateException | IOException e) {
					throw new RuntimeException(e.getMessage(), e);
				}
			}
			
		}
		
	}
	
	
	@RequestMapping("/board/{boardId}")
	public ModelAndView viewBoardPage(@PathVariable int boardId, @ModelAttribute ArticleSearchVO articleSearchVO, HttpSession session, HttpServletRequest request) {
		// 전체검색 or 상세 -> 목록 or 글쓰기
		if(articleSearchVO.getSearchKeyword() == null) {
			articleSearchVO = (ArticleSearchVO) session.getAttribute(Session.SEARCH);
			if(articleSearchVO == null) {
				articleSearchVO = new ArticleSearchVO();
				articleSearchVO.setPageNo(0);
			}
		}
		
		articleSearchVO.setBoardId(boardId);
		PageExplorer pageExplorer = this.articleService.readAllArticles(articleSearchVO);
		session.setAttribute(Session.SEARCH, articleSearchVO);

		ModelAndView view = new ModelAndView("article/list" + boardId);
		
		if(pageExplorer != null) {
			logger.info("URL : /board/" + boardId + ", IP : " + request.getRemoteAddr() + ", List Size : " + pageExplorer.getList().size());
			
			XssFilter filter = XssFilter.getInstance("lucy-xss-superset.xml");
			for(Object articleVO : pageExplorer.getList()) {
				ArticleVO article = (ArticleVO) articleVO;
				article.setTitle(filter.doFilter(article.getTitle()));
				for(int i=0; i<article.getFileVOList().size(); i++) {
					article.getFileVOList().get(i).setContent(filter.doFilter(article.getFileVOList().get(i).getContent()));
				}
			}
			
			view.addObject("boardId", boardId);
			view.addObject("articleList", pageExplorer.getList());
			view.addObject("pagenation", pageExplorer.make());
			view.addObject("size", pageExplorer.getTotalCount());
			view.addObject("articleSearchVO", articleSearchVO);
		}
		
		return view;
	}
	
	@RequestMapping("/board/{boardId}/init")
	public String viewBoardListPageForInitiate(@PathVariable int boardId, HttpSession session) {
		session.removeAttribute(Session.SEARCH);
		return "redirect:/board/" + boardId;
	}
	
	@GetMapping("/read/{boardId}/{articleId}")
	public String goViewArticlePage (@PathVariable int boardId, @PathVariable String articleId) {
		boolean isSuccess = this.articleService.updateViewCount(boardId, articleId);
		
		if(!isSuccess) {
			return "article/list" + boardId;
		}
		
		return "redirect:/board/" + boardId + "/" + articleId;
	}
	
	@GetMapping("/board/{boardId}/{articleId}")
	public ModelAndView viewArticlePage(@PathVariable int boardId, @PathVariable String articleId, HttpSession session
										, @SessionAttribute(Session.SEARCH) ArticleSearchVO articleSearchVO) {
		PageExplorer pageExplorer = this.articleService.readAllArticles(articleSearchVO);
		
		ModelAndView view = new ModelAndView("article/detail" + boardId);
		
		MemberVO memberVO = (MemberVO) session.getAttribute(Session.MEMBER);
		
		if(pageExplorer != null) {
			
			XssFilter filter = XssFilter.getInstance("lucy-xss-superset.xml");
			for(Object articleVO : pageExplorer.getList()) {
				ArticleVO article = (ArticleVO) articleVO;
				article.setTitle(filter.doFilter(article.getTitle()));
				for(int i=0; i<article.getFileVOList().size(); i++) {
					article.getFileVOList().get(i).setContent(filter.doFilter(article.getFileVOList().get(i).getContent()));
				}			
			}
			
			ArticleVO articleVO = this.articleService.readOneArticle(boardId, articleId);
			view.addObject("articleVO", articleVO);
			
			List<FilesVO> filesVOList = articleVO.getFileVOList();
			Map<String, List<FilesVO>> filesVOMap = new HashMap<>();

			for(int i=0; i<filesVOList.size(); i++) {
				String idx = filesVOList.get(i).getIdx();
				
				String fileId = filesVOList.get(i).getFileId();
				List<LinkVO> linkList = this.articleService.readLinkList(fileId);
				filesVOList.get(i).setLinkList(linkList);
				
				if(filesVOMap.get(idx)==null) {
					List<FilesVO> fileLists = new ArrayList<>();
					fileLists.add(filesVOList.get(i));
					filesVOMap.put(idx, fileLists);
				} else {
					filesVOMap.get(idx).add(filesVOList.get(i));
				}
			}
			view.addObject("filesVOMap", filesVOMap);
			
			boolean isRecommend = this.articleService.isRecommend(boardId, articleId, memberVO.getEmail());		// 로그인한 사용자가 추천했는지
			view.addObject("isRecommend", isRecommend);
			
			boolean isReport = this.articleService.isReport(boardId, articleId, memberVO.getEmail());
			view.addObject("isReport", isReport);
			
			boolean isWriterLogin = this.articleService.isWriterLogin(articleVO.getMemberVO().getEmail(), articleVO.getMemberVO().getName());
			view.addObject("isWriterLogin", isWriterLogin);
		}

		return view;
	}
	
	@GetMapping("/board/{boardId}/articleModify/{articleId}")
	public ModelAndView viewArticleModifyPage(@PathVariable int boardId, @PathVariable String articleId
											  , HttpSession session) {
		
		MemberVO sessionUser = (MemberVO) session.getAttribute(Session.MEMBER);
		
		ArticleVO articleVO = this.articleService.readOneArticle(boardId, articleId);

		String sessionToken = (String) session.getAttribute(Session.CSRF_TOKEN);
		if(!articleVO.getToken().equals(sessionToken)) {
			throw new RuntimeException("잘못된 접근입니다.");
		}
		
		String articleUser = articleVO.getEmail();
		if(!sessionUser.getEmail().equals(articleUser)) {
			return new ModelAndView("redirect:/board/" + boardId);
		}
		
		ModelAndView view = new ModelAndView("article/write" + boardId);
		view.addObject("articleVO", articleVO);
		
		return view;
	}
	
	@PostMapping("/board/{boardId}/articleModify/{articleId}")
	public ModelAndView doArticleModifyAction(@PathVariable int boardId, @PathVariable String articleId
											  , @Valid @ModelAttribute ArticleVO articleVO, Errors errors, @ModelAttribute FileMapVO fileMapVO
											  , @SessionAttribute(Session.CSRF_TOKEN) String sessionToken) {
		
		if(!articleVO.getToken().equals(sessionToken)) {
			throw new RuntimeException("잘못된 접근입니다.");
		}
		
		ModelAndView view = new ModelAndView("redirect:/board/" + boardId + "/" + articleId);
		if (errors.hasErrors()) {
			view.setViewName("article/detail" + boardId);		// view path 지정
			view.addObject("articleVO", articleVO);
			return view;
		}
		
		List<FilesVO> fileList = new ArrayList<>();
		
		for(String index : fileMapVO.getFileMap().keySet()) {
			FilesVO filesVO = fileMapVO.getFileMap().get(index);
			filesVO.setBoardId(articleVO.getBoardId());
			
			for(int i=0; i<filesVO.getFileList().size(); i++) {
				FilesVO addFileVO = new FilesVO();
				addFileVO.setBoardId(boardId);
				addFileVO.setContent(filesVO.getContent());
				addFileVO.setFile(filesVO.getFileList().get(i));
				addFileVO.setIdx(filesVO.getIdx());
				fileList.add(addFileVO);
			}
			
			articleVO.setFileVOList(fileList);
			
			// XSS
			XssFilter filter = XssFilter.getInstance("lucy-xss-superset.xml");
			articleVO.setTitle(filter.doFilter(articleVO.getTitle()));
			for(int i=0; i<fileList.size(); i++) {
				articleVO.getFileVOList().get(i).setContent(filter.doFilter(articleVO.getFileVOList().get(i).getContent()));
			}
			
		}
		fileUpload(fileList);
		
		boolean isModifySuccess = this.articleService.updateArticle(articleVO);
		
		return view;
	}
	
	@GetMapping("/board/{boardId}/articleDelete/{articleId}")
	public String doArticleDeleteAction(@PathVariable int boardId, @PathVariable String articleId, HttpSession session) {
		
		MemberVO sessionUser = (MemberVO) session.getAttribute(Session.MEMBER);
		
		ArticleVO articleVO = this.articleService.readOneArticle(boardId, articleId);
		
		String articleUser = articleVO.getEmail();
		if(!sessionUser.getEmail().equals(articleUser)) {
			return "redirect:/board/" + boardId;
		}
		
		boolean isDeleteSuccess = this.articleService.deleteArticle(boardId, articleId);
		
		return "redirect:/board/" + boardId;
	}

}
