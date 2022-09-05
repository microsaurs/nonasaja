package kr.spring.used.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.spring.used.dao.UsedMapper;
import kr.spring.used.vo.UsedFavVO;
import kr.spring.used.vo.UsedVO;

@Service
@Transactional
public class UsedServiceImpl implements UsedService {
	
	@Autowired
	private UsedMapper usedMapper;
	
	@Override
	public List<UsedVO> selectList(Map<String, Object> map) {
		return usedMapper.selectList(map);
	}

	@Override
	public int selectRowCount(Map<String, Object> map) {
		return usedMapper.selectRowCount(map);
	}

	@Override
	public void insertUsed(UsedVO used) {
		usedMapper.insertUsed(used);
	}

	@Override
	public UsedVO selectUsed(Integer used_num) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateHit(Integer used_num) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateUsed(UsedVO used) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteUsed(Integer used_num) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteFile(Integer used_num) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public UsedFavVO selectFav(UsedFavVO fav) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int selectFavCount(Integer used_num) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void insertFav(UsedFavVO usedFav) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteFav(Integer fav_num) {
		// TODO Auto-generated method stub
		
	}

}
