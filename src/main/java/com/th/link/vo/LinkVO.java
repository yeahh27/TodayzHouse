package com.th.link.vo;

import javax.validation.constraints.NotEmpty;

public class LinkVO {

	private String linkId;
	private double linkX;
	private double linkY;
	@NotEmpty(message = "사이트 주소를 입력하세요.")
	private String address;
	private String fileId;
	@NotEmpty(message = "제품명을 입력하세요.")
	private String product;

	private String token;

	public String getLinkId() {
		return linkId;
	}

	public void setLinkId(String linkId) {
		this.linkId = linkId;
	}

	public double getLinkX() {
		return linkX;
	}

	public void setLinkX(double linkX) {
		this.linkX = linkX;
	}

	public double getLinkY() {
		return linkY;
	}

	public void setLinkY(double linkY) {
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

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	@Override
	public String toString() {
		return "LinkVO [linkId=" + linkId + ", linkX=" + linkX + ", linkY=" + linkY + ", address=" + address
				+ ", fileId=" + fileId + ", product=" + product + ", token=" + token + "]";
	}

}
