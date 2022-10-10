package net.around.test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.slf4j.Slf4j;
import net.around.dao.LocationDao;
import net.around.dao.UserLocationDao;
import net.around.dto.UserLocation;

@SpringBootTest
@Slf4j
public class locationDaoTest {

	@Autowired
	LocationDao lDao;
	
	@Autowired
	UserLocationDao udao;
	
	@Test
	public void selectUserLocationTest(){
		List<UserLocation> list = new ArrayList<>();
		list = udao.userAllLocation(2);
		log.debug("유저의 location list : {}", list);
	}

	//@Test
	public void insertUserLocationTest() throws ParseException{
		String str = "88-12-12";
		SimpleDateFormat fdm = new SimpleDateFormat("yy-MM-dd");
		Date date = fdm.parse(str);
		UserLocation userLocation = new UserLocation(0, "location1",date, 1000,1000,2);
		int result = lDao.insertUserLocation(userLocation);
		log.debug("등록된 값 : {}", userLocation);
	}
}

