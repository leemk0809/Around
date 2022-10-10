package net.around.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import net.around.dto.Board;

@Mapper
public interface BoardDao {
	public Board selectForBoardNo(Map<String, Object> filter);
	public List<Board> selectAllBoard();
	public Board selectBoard(int boardNo);
	public List<Board> selectMyBoard(int userNo);
	public List<Board> selectMyBoardByPaging(Map<String, Object> filter);
	public List<Board> selectAllBoardByPaging(Map<String, Object> filter);
	public List<Board> selectAllBoardByPagingMyFd(Map<String, Object> filter);
	public int deleteBoardByBoardNo(int boardNo);
	public int updateBoard(Board board);
	public int insertBoard(Board board);
	public List<Board> selectMyCategoryBoardByPaging(Map<String, Object> filter);
	public List<Board> searchByTitleContent(Map<String,Object> filter);
	public List<Board> searchByTitleContentCategory(Map<String,Object> filter);
	public List<Board> slowMessage(Map<String, Object> slowBoard);
}
