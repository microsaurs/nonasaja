package kr.spring.used.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.spring.used.dao.UsedMapper;
import kr.spring.used.vo.UsedFavVO;
import kr.spring.used.vo.UsedReplyVO;
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
		return usedMapper.selectUsed(used_num);
	}

	@Override
	public void updateHit(Integer used_num) {
		usedMapper.updateHit(used_num);	
	}

	@Override
	public void updateUsed(UsedVO used) {
		usedMapper.updateUsed(used);
	}

	@Override
	public void deleteUsed(Integer used_num) {
		//부모글 삭제
		usedMapper.deleteUsed(used_num);
		//댓글이 존재하면 댓글을 우선 삭제하고 부모글을 삭제
		
	}

	@Override
	public void deleteFile(Integer used_num) {
		usedMapper.deleteFile(used_num);
	}

	@Override
	public UsedFavVO selectFav(UsedFavVO fav) {
		return usedMapper.selectFav(fav);
	}

	@Override
	public int selectFavCount(Integer used_num) {
		return usedMapper.selectFavCount(used_num);
	}

	@Override
	public void insertFav(UsedFavVO usedFav) {
		usedMapper.insertFav(usedFav);
		
	}

	@Override
	public void deleteFav(Integer fav_num) {
		usedMapper.deleteFav(fav_num);
	}

	@Override
	public List<UsedReplyVO> selectListReply(Map<String, Object> map) {
		return usedMapper.selectListReply(map);
	}

	@Override
	public int selectRowCountReply(Map<String, Object> map) {
		return usedMapper.selectRowCountReply(map);
	}

	@Override
	public UsedReplyVO selectReply(Integer re_num) {
		return usedMapper.selectReply(re_num);
	}

	@Override
	public void insertReply(UsedReplyVO usedReply) {
		usedMapper.insertReply(usedReply);
	}

	@Override
	public void updateReply(UsedReplyVO usedReply) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteReply(Integer re_num) {
		// TODO Auto-generated method stub
		
	}

}
