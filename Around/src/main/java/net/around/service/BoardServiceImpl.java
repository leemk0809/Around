package net.around.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.around.dao.BoardDao;
import net.around.dto.Board;

@Service
@Transactional(timeout=60)
public class BoardServiceImpl implements BoardService{
	static Logger logger = LoggerFactory.getLogger(BoardServiceImpl.class);

	@Autowired
	BoardDao boardDao;
	
	@Override
	public Board selectForBoardNo(int userNo, String title) {
		logger.trace("BoardServiceImpl - selectForBoardNo() 동작");
		Map<String, Object> filter = new HashMap<>();
		filter.put("title", title);
		filter.put("userNo", userNo);
		return boardDao.selectForBoardNo(filter);
	}
	
	@Override
	public List<Board> viewAllBoards() {
		logger.trace("BoardServiceImpl - viewAllBoards() 동작");
		return boardDao.selectAllBoard();
	}

	@Override
	public Board selectBoard(int boardNo) {
		//boardNo로 Board객체 하나 검색
		logger.trace("BoardServiceImpl - selectBoard() 동작");
		return boardDao.selectBoard(boardNo);
	}

	@Override
	public List<Board> selectMyBoard(int userNo) {
		//userNo로 내가 쓴 Board 리스트로 검색
		logger.trace("BoardServiceImpl - selectMyBoard() 동작");
		return boardDao.selectMyBoard(userNo);
	}
	
	@Override
	public List<Board> selectMyBoardByPaging(int userNo, int pageNo) {
		logger.trace("BoardServiceImpl - selectAllBoardByPaging() 동작");
		Map<String, Object> filter = new HashMap<>();
		filter.put("from", 9*pageNo-8);
		filter.put("to", 9*pageNo);
		filter.put("userNo", userNo);
		return boardDao.selectMyBoardByPaging(filter);
	}

	@Override
	public List<Board> selectAllBoardByPaging(int pageNo) {
		// TODO Auto-generated method stub
		logger.trace("BoardServiceImpl - selectAllBoardByPaging() 동작");
		Map<String, Object> filter = new HashMap<>();
		filter.put("from", 9*pageNo-8);
		filter.put("to", 9*pageNo);		
		return boardDao.selectAllBoardByPaging(filter);
	}
	
	@Override
	public List<Board> selectAllBoardByPagingMyFd(int pageNo,int userNo) {
		// TODO Auto-generated method stub
		logger.trace("BoardServiceImpl - selectAllBoardByPaging() 동작");
		Map<String, Object> filter = new HashMap<>();
		filter.put("from", 9*pageNo-8);
		filter.put("to", 9*pageNo);
		filter.put("userNo", userNo);
		return boardDao.selectAllBoardByPagingMyFd(filter);
	}

	@Override
	public int deleteBoardByBoardNo(int boardNo) {
		logger.trace("BoardServiceImpl - deleteBoardByBoardNo() 동작");		
		return boardDao.deleteBoardByBoardNo(boardNo);
	}
	
	@Override
	public int updateBoard(Board board) {
		logger.trace("BoardServiceImpl - updateBoard() 동작");		
		return boardDao.updateBoard(board);
	}

	@Override
	public int insertBoard(Board board) {
		logger.trace("BoardServiceImpl - insertBoard() 동작");		
		return boardDao.insertBoard(board);
	}

	@Override
	public List<Board> selectMyCategoryBoardByPaging(int userNo, int pageNo, int categoryNo) {
		logger.trace("BoardServiceImpl - selectAllBoardByPaging() 동작");
		Map<String, Object> filter = new HashMap<>();
		filter.put("from", 9*pageNo-8);
		filter.put("to", 9*pageNo);
		filter.put("userNo", userNo);
		filter.put("categoryNo", categoryNo);
		return boardDao.selectMyCategoryBoardByPaging(filter);
	}

	@Override
	public List<Board> searchByTitleContent(int pageNo,int userNo, String search) {
		logger.trace("BoardServiceImpl - selectAllBoardByPaging() 동작");
		Map<String, Object> filter = new HashMap<>();
		filter.put("from", 9*pageNo-8);
		filter.put("to", 9*pageNo);
		filter.put("title", search);
		filter.put("content", search);
		filter.put("userNo", userNo);
		return boardDao.searchByTitleContent(filter);
	}

	@Override
	public List<Board> searchByTitleContentCategory(int pageNo, int userNo,int categoryNo, String search) {
		logger.trace("BoardServiceImpl - searchByTitleContentCategory() 동작");
		Map<String, Object> filter = new HashMap<>();
		filter.put("from", 9*pageNo-8);
		filter.put("to", 9*pageNo);
		filter.put("userNo", userNo);
		filter.put("title", search);
		filter.put("content", search);
		filter.put("categoryNo", categoryNo);
		return boardDao.searchByTitleContentCategory(filter);
	}

	@Override
	public List<Board> slowMessage(Map<String, Object> slowBoard) {
		logger.trace("BoardServiceImpl - slowMessage() 동작");
		List<Board> list = boardDao.slowMessage(slowBoard);
		return list;
	}

	

	

}
