package net.around.test;

/*
@SpringBootTest
@Slf4j
public class LocationServiceTest {

	static Logger logger = LoggerFactory.getLogger(LocationServiceTest.class);
	
	@Autowired
	LocationService service;
	
	//@Test
	public void userAllLocationTest(){
		List<UserLocation> userLocation = service.userAllLocation(3);
		logger.trace("검색한 유저의 로케이션 : {}", userLocation);
		assertThat(userLocation.size(), is(1));
		assertThat(userLocation.get(0).getUserLocationNo(), is(2));
	}
	 
	@Test
	public void insertUserLocationTest() throws ParseException{
		String str = "99-02-12";
		SimpleDateFormat fdm = new SimpleDateFormat("yy-MM-dd");
		Date date = fdm.parse(str);
		UserLocation userLocation = new UserLocation(0, "location2",date, 1000,1000,2);
		int result = service.insertUserLocation(userLocation);
		logger.trace("등록된 값 : {}", userLocation);
		assertThat(result, is(1));
	}
}

*/