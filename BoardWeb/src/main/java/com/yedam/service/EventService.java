package com.yedam.service;

import java.util.List;
import java.util.Map;

import com.yedam.vo.EventVO;

public interface EventService {
	List<Map<String, Object>> getEvents();
	boolean addEvent(EventVO evo);
	boolean removeEvent(EventVO evo);
	
}
