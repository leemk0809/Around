package net.around.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;
import net.around.dto.User;
import net.around.service.UserHistoryService;
import net.around.service.UserService;

@SpringBootTest
@Slf4j
public class UserServiceTest {

	@Autowired
	UserService service;
	@Autowired
	UserHistoryService ss;
	
	@Test
	public void loginServiceTest(){
		//유저 로그인 테스트
		User user = service.loginUser("hong", "1234");
		log.debug("user : {}", user);
	}
	
	@Test @Disabled
	public void loginFailTestById(){
		//유저 아이디 오류 테스트
		User user = service.loginUser("ddss121", "1111");
		log.debug("user : {}", user);
	}
	
	@Test @Disabled
	public void loginFailTestByPass(){
		//유저 비밀번호 오류 테스트
		User user = service.loginUser("lee", "2344");
		log.debug("user : {}", user);
	}
	
	@Test @Disabled
	public void AllUserTest(){
		//전체 회원 조회 테스트
		List<User> users = service.AllUser();
		log.debug("users : {}", users);
	}
	
	@Test @Disabled
	@Transactional
	public void insertUserTest() throws ParseException{
		//회원 입력 테스트
		String str = "88-12-12";
		SimpleDateFormat fdm = new SimpleDateFormat("yy-MM-dd");
		Date date = fdm.parse(str);
			User user = new User(0, "kang", "aabb", "hodong", "kanghodong",
												"010-000-0000", "abc@def", date,"",null);
			int result = service.insertUser(user);
			log.debug("result : {}", result);
	}
	
	@Test @Disabled
	@Transactional
	public void insertUserDuplicateByIdTest() throws ParseException{
		//아이디 중복 오류 테스트
		String str = "88-12-12";
		SimpleDateFormat fdm = new SimpleDateFormat("yy-MM-dd");
		Date date = fdm.parse(str);
			User user = new User(0, "hong", "aabb", "hodong", "kanghodong",
												"010-000-0000", "abc@def", date,"",null);
			int result = service.insertUser(user);
			log.debug("result : {}", result);
	}
	
	@Test @Disabled
	@Transactional
	public void insertUserDuplicateByNicknameTest() throws ParseException{
		//아이디 중복 오류 테스트
		String str = "88-12-12";
		SimpleDateFormat fdm = new SimpleDateFormat("yy-MM-dd");
		Date date = fdm.parse(str);
			User user = new User(0, "kang", "aabb", "hhh", "kanghodong",
												"010-000-0000", "abc@def", date,"",null);
			int result = service.insertUser(user);
			log.debug("result : {}", result);
	}
	
	
	@Test @Disabled
	public void searchId() {
		// searchId 테스트
		String str = service.searchId("kildong","eungoo1241@naver.com");
		log.debug("searchId - hong: {}", str);
	}
	
	@Test @Disabled
	public void searchPw() {	
		// searchPw 테스트
		String str = service.searchPw("hong","kildong","eungoo1241@naver.com");
		log.debug("searchPw - hong : {}", str);
	}
	
	@Test @Disabled
	public void searchPwFail() {	
		// searchPw 테스트
		String str = service.searchPw("hon","kildong","eungoo1241@naver.com");
		log.debug("searchPw - hong : {}", str);
	}
	
	
	
	
	@Test @Disabled
	public void deleteUser() {	
		
		int str = service.deleteUser(4);
		log.debug("deleteUser: {}", str);
	}
	
	@Test @Disabled
	public void searchUserByUserNoTest(){
		int userNo = 2;
		User user = service.searchUserByUserNo(userNo);
		
	}
	
	@Test @Disabled
	public void selectUserFriends(){
		int userNo = 108;
		List<User> users = service.selectUserFriends(userNo);
		log.debug("users :{}",users);
		
	} 
	@Test @Disabled
	public void selectIfYouKnow(){
		int userNo = 162;
		List<User> users = service.selectIfYouKnow(userNo);
		log.debug("users :{}",users);
		
	}
	@Test @Disabled
	public void isnt(){
		int userNo = 162;
		int i = ss.insertUserHistory(userNo, 381);
		log.debug("성공 :{}",i);		
	}
	

	
}

