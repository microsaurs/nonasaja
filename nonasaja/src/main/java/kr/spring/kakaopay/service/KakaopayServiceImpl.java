package kr.spring.kakaopay.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.spring.member.dao.LionPointMapper;
import kr.spring.member.vo.LionPointVO;
import kr.spring.member.vo.MemberVO;

@Service
public class KakaopayServiceImpl implements KakaopayService {
	
	@Autowired
	private LionPointMapper lionpointMapper;
	

	@Override
	public int selectPointCnt(Integer mem_num) {
		return lionpointMapper.selectPointCnt(mem_num);
	}

	@Override
	public List<LionPointVO> selectPointList(Map<String, Object> map) {
		return lionpointMapper.selectPointList(map);
	}

	@Override
	public LionPointVO selectPointbyMem(Integer mem_num) {
		return lionpointMapper.selectPointbyMem(mem_num);
	}

	@Override
	public void insertPoint(LionPointVO point) {
		int point_num = lionpointMapper.selectPointNum();
		point.setPoint_num(point_num);
		lionpointMapper.insertPoint(point);
	}

	@Override
	public void updateMemberCash(MemberVO member) {
		lionpointMapper.updateMemberCash(member);
	}

}
