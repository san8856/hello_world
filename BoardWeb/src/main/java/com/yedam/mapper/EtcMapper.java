package com.yedam.mapper;

import java.util.List;
import java.util.Map;

public interface EtcMapper {
	List<Map<String, Object>> selectEvent();
	int insertEvent(Map<String, Object> data);
	int deleteEvent(Map<String, Object> data);
	
	// chart를 그리기 위한 쿼리.
	List<Map<String, Object>> selectWriter();

	//로그.
	int insertLogging(Map<String, String> map);
	
}
