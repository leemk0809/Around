package net.around.test;

/*
@SpringBootTest
@Slf4j
public class UserFriendServiceTest {

	static Logger logger = LoggerFactory.getLogger(UserFriendServiceTest.class);
	
	@Autowired
	UserFriendService service;
	
	//@Test
	public void friendListTest() {
		List<UserFriend> friends = service.friendList(2);
		logger.trace("friends : {}", friends);
		assertThat(friends.size(), is(1));
	}

	@Test
	public void updateAlarmTest(){
		int result;
		Map<String, Object> friend = new HashMap<>();
		friend.put("alarm", "off");
		friend.put("friendNo", 162);
		friend.put("userNo", 163);
		result = service.updateAlarm(friend);
		logger.trace("result : {}", result);
	}
	
	//@Test
	public void selectAlarmTest(){
		String result = service.selectAlarm(1);
		logger.trace("result : {}", result);
		assertThat(result.toString(), is("on"));
	}
	
	//@Test
	public void deleteFriendTest(){
		int result = service.deleteFriend(1);
		logger.trace("result : {}", result);
		assertThat(result, is(1));
	}
	
	@Test(expected=exception.FriendNotFoundException.class)
	public void insertFriendTest(){
		int result = service.insertFriend(2, 1);
		logger.trace("result : {}", result);
		assertThat(result, is(1));
	}
}
*/
