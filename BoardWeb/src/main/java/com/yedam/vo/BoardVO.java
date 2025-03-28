package com.yedam.vo;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
//vo - value object

//lombok 설치, 라이브러리 다운로드.
@Getter
@Setter
@ToString
public class BoardVO {
	private int boardNo; // 오라클에서는 board_no -> 자바에서는 _ 없이 이어지는 단어 첫번째 자리를 대문자로. boardNo.
	private String title;
	private String content;
	private String writer;
	private Date writeDate; // write_date
}
