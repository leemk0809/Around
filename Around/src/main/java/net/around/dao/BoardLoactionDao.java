package net.around.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import net.around.dto.BoardLocation;

@Mapper
public interface BoardLoactionDao {
	public BoardLocation selectBoard(int boradNo);
	public int insertBoardLocation(BoardLocation boardLocation);
	public List<BoardLocation> selectBoardLocationByUserNo(int userNo);
	public List<BoardLocation> getBoardsBycenterLocation(Map<String, Object> filter);
}
