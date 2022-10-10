package net.around.test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.slf4j.Slf4j;
import net.around.dao.BoardLoactionDao;
import net.around.dto.BoardLocation;


@SpringBootTest
@Slf4j
public class BoardLoactionTest {
	
	//@Autowired
	//BoardLoactionService service;
	
	@Autowired 
	BoardLoactionDao dao;
	
	
	@Test
	public void selectTest(){
		//BoardLocation board = service.selectBoard(21);
		//log.debug("aa : {}", board);
	}
	
	@Test @Disabled
	public void insertTest() {
		BoardLocation b = new BoardLocation();
		float l = (float) 36.815129;
		float lo = (float)127.11389389;
		b.setLatitude(l);
		b.setLongitude(lo);
		b.setBoardNo(22);
		//int re = service.insertBoardLocation(b);
		
		//log.debug("re:{}",re);
		
		
		//assertThat(re, is(1));
	}
	
	@Test @Disabled
	public void getBoardsBycenterLocationDaoTest(){
		Map<String,Object> filter = new HashMap<>();
		
		filter.put("centerLat", (float) 36.81506854521463);
		filter.put("centerLng", (float) 127.11321711527751);
		filter.put("range", (float) 0.0050);
		
		List<BoardLocation> BoardLocations = dao.getBoardsBycenterLocation(filter);
		
		log.debug("BoardLocations size : {}", BoardLocations.size());
	}

	@Test @Disabled
	public void getBoardsBycenterLocationServiceTest(){
		Map<String,Object> filter = new HashMap<>();
		float centerLat = (float)36.81512;
		float centerLng = (float)127.1142;
		float range = (float) 0.0050;
		int userNo = 2;
		
		filter.put("centerLat", centerLat);
		filter.put("centerLng", centerLng);
		filter.put("range", range);
		filter.put("userNo", userNo);
		
		//List<Board> boards = service.getBoardsBycenterLocation(filter);
		
		//log.debug("boards : {}" , boards);
	}
	
}
