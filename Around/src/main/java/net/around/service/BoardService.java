package net.around.service;

import java.util.List;
import java.util.Map;

import net.around.dto.Board;

public interface BoardService {
	public Board selectForBoardNo(int userNo,String title);
	public List<Board> viewAllBoards();
	public Board selectBoard(int boardNo);
	public List<Board> selectMyBoard(int userNo);
	public List<Board> selectMyBoardByPaging(int userNo,int pageNo);
	public List<Board> selectAllBoardByPaging(int pageNo);
	public List<Board> selectAllBoardByPagingMyFd(int pageNo,int userNo);
	public int deleteBoardByBoardNo(int boardNo);
	public int updateBoard(Board board);
	public int insertBoard(Board board);
	public List<Board> selectMyCategoryBoardByPaging(int userNo,int pageNo,int categoryNo);
	public List<Board> searchByTitleContent(int pageNo,int userNo, String search);
	public List<Board> searchByTitleContentCategory(int pageNo,int userNo,int categoryNo,String search);
	public List<Board> slowMessage(Map<String, Object> slowBoard);
}
