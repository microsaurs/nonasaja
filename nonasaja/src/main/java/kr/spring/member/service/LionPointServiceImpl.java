package kr.spring.member.service;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.spring.member.dao.LionPointMapper;
import kr.spring.member.vo.LionPointVO;
import kr.spring.member.vo.MemberVO;

@Service
public class LionPointServiceImpl implements LionPointService {
	private static final Logger logger = LoggerFactory.getLogger(LionPointServiceImpl.class);
	
	@Autowired
	private LionPointMapper lionPointMapper;
	
	@Override
	public int selectPointNum() {
		return 0;
	}

	@Override
	public void insertPoint(LionPointVO point) {

	}

	@Override
	public int selectPointCnt(Map<String, Object> map) {
		return 0;
	}

	@Override
	public List<LionPointVO> selectPointList(Map<String, Object> map) {
		return null;
	}

	@Override
	public LionPointVO selectPointbyMem(MemberVO member) {
		return null;
	}

}
