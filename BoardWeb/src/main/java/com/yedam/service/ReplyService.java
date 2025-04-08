package com.yedam.service;

import java.util.List;
import java.util.Map;

import com.yedam.vo.ReplyVO;
import com.yedam.vo.SearchDTO;

//업무 프로세스(service)

public interface ReplyService {
	List<ReplyVO> replyList(SearchDTO search);
	boolean addReply(ReplyVO rvo);
	boolean removeReply(int replyNo);
	ReplyVO getReply(int replyNo);
	
	//페이징 계산	
	int getTotalCnt(int boardNo);
	
	// Datatable용 데이터.
	List<Map<String, Object>> replyListForDT(int boardNo);
}