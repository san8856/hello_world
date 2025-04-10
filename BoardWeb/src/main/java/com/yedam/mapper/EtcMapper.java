package com.yedam.mapper;

import java.util.List;
import java.util.Map;

public interface EtcMapper {
	List<Map<String, Object>> selectEvent();
	int insertEvent(Map<String, Object> data);
	int deleteEvent(Map<String, Object> data);
}
