package com.yedam.service;

import java.util.List;
import java.util.Map;

public interface EtcService {
	List<Map<String, Object>> eventList();
	boolean addEvent(Map<String, Object> data);
	boolean removeEvent(Map<String, Object> data);
}