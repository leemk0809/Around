package net.around.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.around.dao.BoardDao;
import net.around.dao.BoardLoactionDao;
import net.around.dao.UserFriendDao;
import net.around.dto.Board;
import net.around.dto.BoardLocation;
import net.around.dto.UserFriend;

@Service
@Transactional(timeout=60)
public class BoardLoactionServiceImpl implements BoardLoactionService{
	static Logger logger = LoggerFactory.getLogger(BoardLoactionServiceImpl.class);
	
	@Autowired
	BoardLoactionDao boardlocationDao;
	
	@Autowired
	UserFriendDao userFriendDao;
	
	@Autowired
	BoardDao boardDao;
	
	@Override
	public BoardLocation selectBoard(int boradNo) {
		logger.trace("BoardLoactionServiceImpl - selectBoard");
		return boardlocationDao.selectBoard(boradNo);
	}

	@Override
	public int insertBoardLocation(BoardLocation boardLocation) {
		logger.trace("BoardLoactionServiceImpl - insertBoardLocation");
		return boardlocationDao.insertBoardLocation(boardLocation);
		
	}

	@Override
	public List<BoardLocation> selectBoardLocationByUserNo(int userNo) {
		logger.trace("BoardLoactionServiceImpl - selectBoardLocationByUserNo");
		return boardlocationDao.selectBoardLocationByUserNo(userNo);
	}

	@Override
	public List<Board> getBoardsBycenterLocation(Map<String,Object> filter) {
		logger.trace("BoardLoactionServiceImpl - selectBoardLocationByUserNo");;
		
		// 민국 - 좌표 값에 해당하는 게시물들의 번호를 가져옴
		List<BoardLocation> aroundBoardNo = boardlocationDao.getBoardsBycenterLocation(filter);
		logger.trace("aroundBoardNo : {}, getClass : {}", aroundBoardNo, aroundBoardNo.getClass());


		List<Board> aroundBoardOwner = new ArrayList<Board>();
		// 민국 - 해당 게시물의 주인들을 매칭
		for(int i = 0 ; i < aroundBoardNo.size() ; i++){
			// 게시물 번호에 해당하는 게시물 가져옴
			logger.trace("aroundBoardNo : {}", aroundBoardNo.get(i));
			
			Board board = boardDao.selectBoard(aroundBoardNo.get(i).getBoardNo());
			// 게시물을 쓴 주인을 저장 
			aroundBoardOwner.add(board);
		}
		logger.trace("aroundBoardOwner : {}", aroundBoardOwner);
		
		// 민국 - 내 친구들을 가져옴.
		int userNo = (int) filter.get("userNo");
		logger.trace("userNo : {}", userNo);
		List<UserFriend> myFriend = userFriendDao.friendList(userNo);
		logger.trace("myFriend : {}", myFriend);
		
		List<Board> aroundMyFriend = new ArrayList<>();
		
		// 민국 - 근처 게시물들 중에 내 친구들 게시물만 남김
		for(int i = 0 ; i < aroundBoardOwner.size(); i++){
			for(int j = 0 ; j < myFriend.size(); j++){
				if(aroundBoardOwner.get(i).getUserNo() == myFriend.get(j).getFriendNo()){
					aroundMyFriend.add(aroundBoardOwner.get(i));
				}
			}
		}
		
		// 민국 - 내 친구들 게시물 확인
		logger.trace("aroundMyFriend : {}", aroundMyFriend.size());
		for(int i = 0 ; i < aroundMyFriend.size(); i++){
			logger.trace("arroundMyFriend({}) : {}",i, aroundMyFriend.get(i));
		}
		
		// 민국 - 내 주변 친구들의 게시물 중에 visible인 게시물
		List<Board> aroundMyFriendBoard = new ArrayList<>();
		int hiddenCnt = 0;
		
		// 민국 - 내 친구들이 주변에 쓴 글 중에서 visible인 글을 찾음
		for(int i = 0 ; i < aroundMyFriend.size(); i++){
			if("hidden".equals(aroundMyFriend.get(i).getViewStatus())){
				aroundMyFriendBoard.add(aroundMyFriend.get(i));
				// 민국 - 보여줄 글(Board) DTO에 위치값 삽입
				for(BoardLocation aroundBoard : aroundBoardNo){
					if(aroundBoard.getBoardNo() == aroundMyFriendBoard.get(hiddenCnt).getBoardNo()){
						aroundMyFriendBoard.get(hiddenCnt).setLatitude(aroundBoard.getLatitude());
						aroundMyFriendBoard.get(hiddenCnt).setLongitude(aroundBoard.getLongitude());
					}
				}
				hiddenCnt++;
			} 
		}
		
		return aroundMyFriendBoard;
	}
}
