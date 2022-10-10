package net.around.test;

/*
@SpringBootTest
@Slf4j
public class PromiseDaoTest {
	static Logger logger = LoggerFactory.getLogger(PromiseDaoTest.class);
	
	@Autowired
	PromiseDao pDao;

	@Test
	public void getAllPromiseTest() {
		List<Promise> allPromise = pDao.getAllPromise();
		logger.trace("allPromise size : {}", allPromise.size());
	}
	
	@Test
	public void getMyPromiseByPromoteTest() {
		List<Promise> allPromise = pDao.getMyPromiseByPromote(2);
		logger.trace("size : {}", allPromise.size());
	}
	
	@Test
	public void getMyPromiseByInviteeTest() {
		List<Promise> allPromise = pDao.getMyPromiseByInvitee(1);
		logger.trace("size : {}", allPromise.size());
	}
	
	@Test
	public void insertPromiseTest() throws ParseException{
		String str = "16-12-01";
		SimpleDateFormat fdm = new SimpleDateFormat("yy-MM-dd");
		Date date = fdm.parse(str);
		Promise promise = new Promise(1, "은구와 민국의 약속", 2, 1, date, 36, 126, "우리 만날래?", "약속 중" );
		int result = pDao.insertPromise(promise);
		logger.trace("promise : {}", result);
		assertThat(result, is(1));
	}
	
	@Test
	public void updatePromiseTest() throws ParseException{
		String str = "16-11-01";
		SimpleDateFormat fdm = new SimpleDateFormat("yy-MM-dd");
		Date date = fdm.parse(str);
		Promise promise = new Promise(0, "민국과 은구의 약속", 2, 1, date, 38, 129, "장소 변경 하자", "약속 중");
		int result = pDao.updatePromise(promise);
		assertThat(result, is(1));
	}
	
	@Test
	public void getPromiseByProAndDateTest() throws ParseException{
		Map<String, Object> map = new HashMap<>();
		
		String str = "2016-12-01";
		SimpleDateFormat fdm = new SimpleDateFormat("yyyy-MM-dd");
		Date date = fdm.parse(str);

		Timestamp timestamp = new Timestamp(date.getTime());
		
		logger.trace("timestamp : {}", timestamp);

		//Date date = new GregorianCalendar(2016, 11, 01).getTime();
		
		map.put("promoter", 2);
		map.put("promiseDate", date);
		Promise promise = pDao.getPromiseByProAndDate(map);
		logger.trace("promisePP : {}", promise);
		
		//date 형태로 쿼리문 조회시 에러 -> date 형태로 어떻게 조회하는지 확인
	}

}
*/
