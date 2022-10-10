package net.around.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.around.dao.HotBoardDao;
import net.around.dto.HotBoard;

@Service
public class HotBoardServiceImpl implements HotBoardService{
	static Logger logger = LoggerFactory.getLogger(HotBoardServiceImpl.class);
	
	@Autowired
	HotBoardDao hotBoardService;
	
	@Override
	public List<HotBoard> selectHotBoardByCategory(int categoryNo) {
		logger.trace("HotBoardServiceImpl - selectHotBoardByCategory");
		return hotBoardService.selectHotBoardByCategory(categoryNo);
	}

	@Override
	public int insertHotBoard(HotBoard hotBoard) {
		logger.trace("HotBoardServiceImpl - insertHotBoard");
		return hotBoardService.insertHotBoard(hotBoard);
	}

	@Override
	public List<HotBoard> selectAllHotBoard() {
		logger.trace("HotBoardServiceImpl - selectAllHotBoard");
		return hotBoardService.selectAllHotBoard();
	}

}
