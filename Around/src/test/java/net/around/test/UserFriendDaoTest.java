package net.around.test;

/*
@SpringBootTest
@Slf4j
public class UserFriendDaoTest {

	static Logger logger = LoggerFactory.getLogger(UserFriendDaoTest.class);

	@Autowired
	UserFriendDao ufDao;
	
	@Test
	public void friendListTest(){
		List<UserFriend> friends = ufDao.friendList(2);
		logger.trace("친구 목록 : {}", friends);
//		assertThat(friends.size(), is(1));
	}
	
	@Test
	public void updateAlarmTest(){
		int result;
		Map<String, Object> friend = new HashMap<>();
		friend.put("alarm", "on");
		friend.put("friendNo", 162);
		friend.put("userNo", 163);
		result = ufDao.updateAlarm(friend);
		logger.trace("result : {}", result);
	}
	@Test
	public void selectAlarmTest(){
		String alarm = ufDao.selectAlarm(1);
		logger.trace("result : {}", alarm);
	}
	
	@Test
	@Transactional
	public void deleteFriendTest(){
		int result = ufDao.deleteFriend(1);
		logger.trace("result : {}", result);
	}
	
	@Test
	public void insertFriendTest(){
		UserFriend userFriend = new UserFriend(0,3,"unknown",1,"on","","",null,"");
		int result = ufDao.insertFriend(userFriend);
		logger.trace("result : {}", result);
		assertThat(result, is(1));
	}
}
*/
