package com.yedam.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.yedam.common.DataSource;
import com.yedam.mapper.EventMapper;
import com.yedam.vo.EventVO;

public class EventServiceImpl implements EventService {

	private EventMapper mapper;

	public EventServiceImpl() {
		SqlSession sqlSession = DataSource.getInstance().openSession(true);
		this.mapper = sqlSession.getMapper(EventMapper.class);
	}

    @Override
    public boolean addEvent(EventVO evo) {
        return mapper.insertEvent(evo) == 1;
    }

    @Override
    public boolean removeEvent(EventVO evo) {
        return mapper.deleteEvent(evo) == 1;
    }

	@Override
	public List<Map<String, Object>> getEvents() {

	    return mapper.getEvents();
	}
}
