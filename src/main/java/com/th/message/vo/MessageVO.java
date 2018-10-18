package com.th.message.vo;

public class MessageVO {

	private String messageId;
	private String toEmail;
	private String fromEmail;
	private String regDate;

	public String getMessageId() {
		return messageId;
	}

	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}

	public String getToEmail() {
		return toEmail;
	}

	public void setToEmail(String toEmail) {
		this.toEmail = toEmail;
	}

	public String getFromEmail() {
		return fromEmail;
	}

	public void setFromEmail(String fromEmail) {
		this.fromEmail = fromEmail;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	@Override
	public String toString() {
		return "MessageVO [messageId=" + messageId + ", toEmail=" + toEmail + ", fromEmail=" + fromEmail + ", regDate="
				+ regDate + "]";
	}

}
