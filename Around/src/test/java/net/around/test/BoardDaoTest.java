package net.around.test;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.slf4j.Slf4j;
import net.around.dao.BoardDao;
import net.around.dto.Board;

@SpringBootTest
@Slf4j
public class BoardDaoTest {


	@Autowired
	BoardDao bDao;

	@Test
	public void selectAllBoardTest() {
		List<Board> boards = bDao.selectAllBoard();
		log.debug("boards.size : {}", boards.size()); 
		//assertThat(boards.size(), is(notNullValue()));
	}
	
	@Test
	public void selectBoardTest(){
		Board board = bDao.selectBoard(216);
		log.debug("board : {}", board);
		//assertThat(board.getTitle(), is(notNullValue()));
	}
	
	@Test
	public void selectMyBoardTest(){
		List<Board> boards = bDao.selectMyBoard(2);
		log.debug("boards.size : {}", boards.size());
		//assertThat(boards.size(), is(2));
	}
	
	//@Test @Disabled
	public void selectAllBoardByPagingTest(){
		Map<String, Object> filter = new HashMap<>();
		filter.put("from", 1);
		filter.put("to", 2);
		
		List<Board> boards = bDao.selectAllBoardByPaging(filter);
		log.debug("boards.size : {}", boards.size());
		//assertThat(boards.size(), is(2));
	}
	
	//@Test @Disabled
	public void deleteBoardByBoardNo(){
		int result = bDao.deleteBoardByBoardNo(54);
		Board board = bDao.selectBoard(54);
		log.debug("board : {}", board);
		//assertThat(result, is(1));
	}
	
	
	
	//@Test @Disabled
	public void updateBoard(){	
		Board board = new Board();
		board.setBoardNo(1);
		board.setTitle("나는 은구다");
		board.setContent("이것은 수정");
		board.setCategoryNo(1);
		board.setImagePath("/img/thumbs/01.jpg");
		
		int result = bDao.updateBoard(board);
		
		//assertThat(result, is(1));
	}
	
	
	
	//@Test @Disabled
	public void insertBoard(){
		
		Board board = new Board();	
		board.setUserNo(2);
		board.setTitle("나는 은구다");
		board.setContent("이것은 insert 다");
		board.setCategoryNo(1);
		board.setImagePath("/img/thumbs/01.jpg");
		
		int result = bDao.insertBoard(board);
		log.debug("insertBoard result : {}",result);		
				
		//assertThat(result, is(1));
	}
	
	//@Test @Disabled
	public void slowBoard() throws ParseException{
		Map<String, Object> slowMessage = new HashMap<>();
		/*
		String str = "2016-11-03";
		SimpleDateFormat fdm = new SimpleDateFormat("yyyy-MM-dd");
		Date date = fdm.parse(str);*/
		
		slowMessage.put("latitude", 36.81513);
		slowMessage.put("longitude", 127.11389);
		
		List<Board> list = bDao.slowMessage(slowMessage);
		log.debug("list : {}", list);
	}
}
