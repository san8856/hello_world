package com.yedam.vo;

import java.util.Date;

import lombok.Data;

@Data
public class LogVO {
	private int logSec;
	private String execPage;
	private String execIp;
	private Date execTime;
}
