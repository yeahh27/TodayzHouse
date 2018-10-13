package com.th.link.vo;

import javax.validation.constraints.NotEmpty;

public class LinkVO {

	private String linkId;
	private int linkX;
	private int linkY;
	@NotEmpty(message="사이트 주소를 입력하세요.")
	private String address;
	private String fileId;
	@NotEmpty(message="제품명을 입력하세요.")
	private String product;

	public String getLinkId() {
		return linkId;
	}

	public void setLinkId(String linkId) {
		this.linkId = linkId;
	}

	public int getLinkX() {
		return linkX;
	}

	public void setLinkX(int linkX) {
		this.linkX = linkX;
	}

	public int getLinkY() {
		return linkY;
	}

	public void setLinkY(int linkY) {
		this.linkY = linkY;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getFileId() {
		return fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	@Override
	public String toString() {
		return "LinkVO [linkId=" + linkId + ", linkX=" + linkX + ", linkY=" + linkY + ", address=" + address
				+ ", fileId=" + fileId + ", product=" + product + "]";
	}

}
