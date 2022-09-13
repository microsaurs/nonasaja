package kr.spring.used.service;

import java.util.List;
import java.util.Map;

import kr.spring.used.vo.UsedFavVO;
import kr.spring.used.vo.UsedReplyVO;
import kr.spring.used.vo.UsedVO;

public interface UsedService {
	//부모글
		public List<UsedVO> selectList(Map<String,Object> map);
		public int selectRowCount(Map<String, Object> map);
		public void insertUsed(UsedVO used);
		public UsedVO selectUsed(Integer used_num);
		public void updateHit(Integer used_num);
		public void updateUsed(UsedVO used);
		public void deleteUsed(Integer used_num);
		public void deleteFile(Integer used_num);
		
		//댓글
		public List<UsedReplyVO> selectListReply(Map<String, Object> map);
		public int selectRowCountReply(Map<String, Object> map);
		public UsedReplyVO selectReply(Integer re_num);
		public void insertReply(UsedReplyVO usedReply);
		public void updateReply(UsedReplyVO usedReply);
		public void deleteReply(Integer re_num);
		
		//부모글 좋아요
		public UsedFavVO selectFav(UsedFavVO fav);
		public int selectFavCount(Integer used_num);
		public void insertFav(UsedFavVO usedFav);
		public void deleteFav(Integer fav_num);
}
