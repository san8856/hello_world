package com.yedam.service;

import org.apache.ibatis.session.SqlSession;

import com.yedam.common.DataSource;
import com.yedam.mapper.MemberMapper;
import com.yedam.vo.MemberVO;

// 업무(service 구현 객체)
public class MemberServiceImpl implements MemberService {
	SqlSession sqlSession = DataSource.getInstance().openSession(true);
	MemberMapper mapper = sqlSession.getMapper(MemberMapper.class);
	
	@Override
	public MemberVO login(String id, String pw) {
		return mapper.selectMember(id, pw);
	}

}
