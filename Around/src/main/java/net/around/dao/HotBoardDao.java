package net.around.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import net.around.dto.HotBoard;

@Mapper
public interface HotBoardDao {
	public List<HotBoard> selectHotBoardByCategory(int categoryNo); 
	public int insertHotBoard(HotBoard hotBoard);
	public List<HotBoard> selectAllHotBoard();
}
