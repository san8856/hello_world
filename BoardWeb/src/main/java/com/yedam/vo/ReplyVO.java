package com.yedam.vo;

import java.util.Date;

import lombok.Data;
//getter setter override tostirng 등 다 한번에 해주는 코드 @data
@Data
public class ReplyVO {
	private int replyNo;    // 댓글번호
	private String reply;   // 댓글내용
	private String replyer; // 댓글작성자
	private Date replyDate; // 댓글장성일시
	private int boardNo;    // 원본 글 번호.
}
