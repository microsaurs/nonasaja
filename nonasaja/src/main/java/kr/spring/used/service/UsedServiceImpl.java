package kr.spring.used.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.spring.used.dao.UsedMapper;
import kr.spring.used.vo.UsedFavVO;
import kr.spring.used.vo.UsedReplyVO;
import kr.spring.used.vo.UsedRereplyVO;
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
		usedMapper.deleteReplyByBoardNum(used_num);
		//부모글 삭제
		usedMapper.deleteUsed(used_num);
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
	public UsedReplyVO selectReply(Integer reply_num) {
		return usedMapper.selectReply(reply_num);
	}

	@Override
	public void insertReply(UsedReplyVO usedReply) {
		usedMapper.insertReply(usedReply);
	}

	@Override
	public void updateReply(UsedReplyVO usedReply) {
		usedMapper.updateReply(usedReply);
	}

	@Override
	public void deleteReply(Integer reply_num) {
		usedMapper.deleteReply(reply_num);
	}

	@Override
	public List<UsedRereplyVO> selectListRereply(Map<String, Object> map) {
		return usedMapper.selectListRereply(map);
	}

	@Override
	public int selectRowCountRereply(Map<String, Object> map) {
		return usedMapper.selectRowCountRereply(map);
	}

	@Override
	public UsedRereplyVO selectRereply(Integer rereply_num) {
		return usedMapper.selectRereply(rereply_num);
	}

	@Override
	public void insertRereply(UsedRereplyVO usedRereply) {
		usedMapper.insertRereply(usedRereply);
	}

	@Override
	public void updateRereply(UsedRereplyVO usedRereply) {
		usedMapper.updateRereply(usedRereply);
	}

	@Override
	public void deleteRereply(Integer rereply_num) {
		usedMapper.deleteRereply(rereply_num);
		
	}

	
	@Override
	public void deleteRereplyByBoardNum(Integer used_num) {
		usedMapper.deleteRereplyByBoardNum(used_num);
	}
	

}
