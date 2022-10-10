package net.around.service;

import java.util.List;
import java.util.Map;

import net.around.dto.Board;
import net.around.dto.BoardLocation;

public interface BoardLoactionService {
	public BoardLocation selectBoard(int boradNo);
	public int insertBoardLocation(BoardLocation boardLocation);
	public List<BoardLocation> selectBoardLocationByUserNo(int userNo);
	public List<Board> getBoardsBycenterLocation(Map<String,Object> filter);
}
