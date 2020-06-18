package com.test.notice;

import java.util.Calendar;

public class NoticeBasic {
	private String noticeNum;
	private String noticeTitle;
	private String noticeContent;
	private Calendar noticeInsertDate;
	
	public NoticeBasic(String noticeNum, String noticeTitle, String noticeContent, Calendar noticeInsertDate) {
		this.noticeNum = noticeNum;
		this.noticeTitle = noticeTitle;
		this.noticeContent = noticeContent;
		this.noticeInsertDate = noticeInsertDate;
	}

	public String getNoticeNum() {
		return noticeNum;
	}

	public void setNoticeNum(String noticeNum) {
		this.noticeNum = noticeNum;
	}

	public String getNoticeTitle() {
		return noticeTitle;
	}

	public void setNoticeTitle(String noticeTitle) {
		this.noticeTitle = noticeTitle;
	}

	public String getNoticeContent() {
		return noticeContent;
	}

	public void setNoticeContent(String noticeContent) {
		this.noticeContent = noticeContent;
	}

	public Calendar getNoticeInsertDate() {
		return noticeInsertDate;
	}

	public void setNoticeInsertDate(Calendar noticeInsertDate) {
		this.noticeInsertDate = noticeInsertDate;
	}

	@Override
	public String toString() {
		return "NoticeBasic [noticeNum=" + noticeNum + ", noticeTitle=" + noticeTitle + ", noticeContent="
				+ noticeContent + ", noticeInsertDate=" + noticeInsertDate + "]";
	}

}
