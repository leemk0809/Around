package net.around.test;

/*
@SpringBootTest
@Slf4j
public class BoardServiceTest {

	static Logger logger = LoggerFactory.getLogger(BoardServiceTest.class);

	@Autowired
	BoardService service;

	@Test
	public void viewAllBoards() {
		List<Board> boards = service.viewAllBoards();
		logger.trace("boards.size : {}", boards.size()); 
		assertThat(boards.size(), is(notNullValue()));
	}
	
	@Test
	public void viewBoard(){
		Board board = service.selectBoard(1);
		logger.trace("board : {}", board);
		assertThat(board, is(notNullValue()));
		assertThat(service.selectBoard(4), is(nullValue()));
	}
	
	@Test
	public void viewMyBoard(){
		List<Board> boards = service.selectMyBoard(2);
		logger.trace("boards.size() : {}", boards.size());
		assertThat(boards.size(), is(3));
	}
	
	@Test
	public void selectAllBoardByPagingTest(){
		int pageNo = 1;
		List<Board> boards = service.selectAllBoardByPaging(pageNo);
		logger.trace("boards.size() : {}", boards.size());
		//assertThat(boards.size(), is(9));
	}
	
	
	//@Test
	public void deleteBoardByBoardNo(){
		int result = service.deleteBoardByBoardNo(53);
		Board board = service.selectBoard(53);
		logger.trace("board : {}", board);
		assertThat(result, is(1));
	}
	
	
	@Test
	@Transactional
	public void updateBoard(){
		
		Board board = new Board();
		board.setBoardNo(1);
		board.setTitle("나는 은구다");
		board.setContent("이것은 수정이다");
		board.setCategoryNo(1);
		board.setImagePath("null");
		
		int result = service.updateBoard(board);
		logger.trace("update result : {}",result);		
				
		assertThat(result, is(1));
	}
	
	
	//@Test	
	public void insertBoard(){
		
		Board board = new Board();	
		board.setUserNo(2);
		board.setTitle("나는 은구다2222");
		board.setContent("이것은 insert 다");
		board.setCategoryNo(1);
		board.setImagePath("/img/thumbs/01.jpg");
		
		int result = service.insertBoard(board);
		logger.trace("insertBoard result : {}",result);		
				
		assertThat(result, is(1));
	}
	@Test	
	public void searchByTitleContent(){
		List<Board> board = service.searchByTitleContent(1,2, "은구");
		logger.trace("은구로 검색 한 board : {} ",board);
	}
	
	@Test
	public void selectMyBoardByPagingTest(){
		List<Board> boards = service.selectMyBoardByPaging(23, 1);
		logger.trace("boards : {}", boards);
	}
	
	@Test
	public void selectForBoardNo(){
		Board b = service.selectForBoardNo(2, "asd");
		logger.trace("boards : {}", b);
	}
	
	@Test
	public void selectAllBoardByPagingfdTest(){
		List<Board> boards = service.selectAllBoardByPagingMyFd(1, 2);
		logger.trace("boards.size() : {}", boards.size());
		//assertThat(boards.size(), is(9));
	}
	
	
	@Test
	public void selectMyCategoryBoardByPaging(){
		List<Board> boards = service.selectMyCategoryBoardByPaging(2, 1, 1);
		logger.trace("보드() : {}", boards);
	}
	
	@Test
	public void slowBoardTest() throws ParseException{
		Map<String, Object> slowMessage = new HashMap<>();
		
		String str = "2016-10-26 17:55";
		SimpleDateFormat fdm = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Date date = fdm.parse(str);

		Timestamp timestamp = new Timestamp(date.getTime());
		
		slowMessage.put("targetDate", timestamp);
		slowMessage.put("latitude", 36.81513);
		slowMessage.put("longitude", 127.11389);
		slowMessage.put("range", 0.005);
		List<Board> boards = service.slowMessage(slowMessage);
		logger.trace("slowBoard : {}", boards);
	}
	
	
	
}
*/
