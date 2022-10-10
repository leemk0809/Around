package net.around.controller;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;
import net.around.dto.Board;
import net.around.dto.BoardLocation;
import net.around.dto.Category;
import net.around.dto.Reply;
import net.around.dto.UserLocation;
import net.around.service.BoardLoactionService;
import net.around.service.BoardService;
import net.around.service.CategoryService;
import net.around.service.LocationService;
import net.around.service.ReplyService;
import net.around.service.UserService;

@Controller
@SessionAttributes({ "MyBoard" })
@Slf4j
public class BoardController {

	static Logger logger = LoggerFactory.getLogger(BoardController.class);

	@Autowired
	BoardService service;
	@Autowired
	CategoryService ctservice;
	@Autowired
	UserService userService;
	@Autowired
	BoardLoactionService boardLocationService;
	@Autowired
	LocationService userLocationService;
	@Autowired
	ReplyService replyService;

	@InitBinder
	public void setBindingFormat(WebDataBinder binder) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(format, true));
	}

	@RequestMapping(value = "/deleteBoard", method = RequestMethod.GET)
	public String deleteBoard(HttpSession session, @RequestParam int boardNo) {
		int result;
		logger.trace("class : BoardController, method : deleteBoard");
		// Board board = (Board) session.getAttribute("myBoard");
		// logger.trace("myBoard : {}", board);
		// result = service.deleteBoardByBoardNo(board.getBoardNo());
		result = service.deleteBoardByBoardNo(boardNo);
		logger.trace("삭제 결과 : {}", result);
		return "mainBoard2";
	}

	@RequestMapping(value = "/writeBoard")
	public String writeBoard(Model model, HttpSession session) {
		logger.trace("class : BoardController, method : writeBoard");

		List<Category> category = ctservice.selectAllCategory();
		model.addAttribute("category", category);

		int userNo = (int) session.getAttribute("userNo");
		logger.trace("userNo : {}", userNo);

		Board board = new Board(0, "", 0, "", null, userNo, 0, "", null, "visible", 0, 0, null, null);
		session.setAttribute("board", board);
		model.addAttribute("board", board);
		return "writeBoard3";
	}

	// 민국, 위치
	 //private static final String uploadDir ="C:/Users/1-718-8/git/FinalProject-3/projectBy3/src/main/webapp/WEB-INF/assets/images/userImages/";
	private static final String uploadDir = "C:/Users/EG-717-8/git/FinalProject-3/projectBy3/src/main/webapp/WEB-INF/assets/images/userImages/";
	// private static final String uploadDir =
	// "E:/sts-bundle/pivotal-tc-server-developer-3.1.5.RELEASE/server/wtpwebapps/projectBy3/WEB-INF/assets/images";

	@RequestMapping(value = "/writeBoard", method = RequestMethod.POST)
	public String writeBoardPost(HttpSession session, Board board, @RequestParam MultipartFile file)
			throws IllegalStateException, IOException, ParseException {
		logger.trace("class : BoardController, method : writeBoardPost");
		logger.trace("board : {}", board);
		int userNo = (int) session.getAttribute("userNo");

		logger.trace("date@ : {}", board.getTargetDate());
		
		File uploadFile = new File(uploadDir + userNo + "." + System.currentTimeMillis() + file.getOriginalFilename());

		if (file.getOriginalFilename().length() != 0) {
			logger.trace("파이리 있다 :{}", file.getOriginalFilename().length());
			file.transferTo(uploadFile);
			board.setImagePath(uploadFile.getName());
		} else if (file.getOriginalFilename().length() == 0) {
			logger.trace("파이리 없다 :{}", file.getOriginalFilename().length());
			if (userService.selectUserProfilePathByUserNo(userNo) != null) {
				board.setImagePath(userService.selectUserProfilePathByUserNo(userNo));
			}
		}

		logger.trace("원래 파일 명:{}", file.getOriginalFilename());
		logger.trace("변경 후  파일 명:{}", uploadFile.getName());
		// 쓰기 버튼을 누르고 글 작성 시간 추가
		board.setWritedDate(new Date());

		if (board.getTargetDate() == null) {
			board.setTargetDate(new Date(0));
		}

		int result = service.insertBoard(board);

		// board location에 위치정보 등록
		Board insertBoard = service.selectForBoardNo(board.getUserNo(), board.getTitle());
		logger.trace("글쓰기 결과 : {}", result);
		logger.trace("보이는 상황:{}", board.getViewStatus());
		if (board.getViewStatus().equals("hidden")) {
			BoardLocation boardLocation = new BoardLocation();
			boardLocation.setLatitude(board.getLatitude());
			boardLocation.setLongitude(board.getLongitude());
			boardLocation.setBoardNo(insertBoard.getBoardNo());
			logger.trace("위치:{},{}", board.getLatitude(), board.getLongitude());
			int resultLocation = boardLocationService.insertBoardLocation(boardLocation);
			logger.trace("성공 여부: {}", resultLocation);
		}

		return "mainBoard2";
	}

	@RequestMapping(value = "/writeBoardLocation", method = RequestMethod.GET)
	public String writeBoardLocation(HttpSession session, Model model, @RequestParam Float latitude,
			@RequestParam Float longitude) {
		List<Category> category = ctservice.selectAllCategory();
		model.addAttribute("category", category);
		Board board = new Board(0, "", 0, "", null, (int) session.getAttribute("userNo"), 0, "", null, "hidden",
				latitude, longitude, null, null);
		model.addAttribute("board", board);
		return "writeBoard3";
	}

	@RequestMapping(value = "/writeBoardLocations", method = RequestMethod.GET)
	public String writeBoardLocations(HttpSession session, Model model, @RequestParam Float latitude,
			@RequestParam Float longitude, @RequestParam String locationName) {
		List<Category> category = ctservice.selectAllCategory();
		model.addAttribute("locationName", locationName);
		model.addAttribute("category", category);
		Board board = new Board(0, "", 0, "", null, (int) session.getAttribute("userNo"), 0, "", null, "hidden",
				latitude, longitude, null, null);
		model.addAttribute("board", board);
		return "writeBoard3";
	}

	@RequestMapping(value = "/updateBoard", method = RequestMethod.POST)
	public String updateBoard(HttpSession session, Board board) {
		logger.trace("class : BoardController, method : deleteBoard");
		String ImagePath = board.getImagePath();

		logger.trace("board : {}", board);

		// 이미지가 문자열 null이 아니고 주소가 null일떄 예외 처리.
		logger.trace("myBoard imagePath : {}", ImagePath);
		if (ImagePath == null) {
			logger.trace("ImagePath null!!!!!!!!!");
			board.setImagePath("null");
		}
		int result = service.updateBoard(board);
		logger.trace("수정 결과 : {}", result);
		return "mainBoard2";
	}

	@RequestMapping(value = "/getMyLocation", method = RequestMethod.GET)
	public String getMyLocation(Model model, HttpSession session) {
		List<UserLocation> userLocations = userLocationService.userAllLocation((int) session.getAttribute("userNo"));
		Board board = (Board) session.getAttribute("board");
		logger.trace("위치 누를때 저장된 : {}", board);
		log.debug("userLocations : {}", userLocations);
		model.addAttribute("userLocations", userLocations);
		return "writeLocation2";
	}

	@RequestMapping(value = "/returnMainBoard", method = RequestMethod.GET)
	public String returnMainBoard(HttpSession session, Board board) {
		return "mainBoard2";
	}

	@RequestMapping(value = "/addReply", method = RequestMethod.GET)
	public String addReply(Model model, HttpSession session, @RequestParam String contents, @RequestParam int boardNo) {
		logger.trace("콘:{},{}", boardNo, contents);
		Reply re = new Reply(1, boardNo, (int) session.getAttribute("userNo"), contents, null);
		replyService.insertReply(re);
		Board board = service.selectBoard(boardNo);
		List<Reply> res = replyService.selectReplyByBoardNo(boardNo);
		session.setAttribute("myBoard", board);
		model.addAttribute("board", board);
		model.addAttribute("reply", res);
		BoardLocation bLocation = new BoardLocation();
		if("hidden".equals(board.getViewStatus())){
			bLocation = boardLocationService.selectBoard(boardNo);			
			board.setLatitude(bLocation.getLatitude());
			board.setLongitude(bLocation.getLongitude());
		}
		
		if((int) session.getAttribute("userNo") == board.getUserNo()){
			logger.trace("detailMyBoard 콜");
			return "detailMyBoard2";
		}else{
			logger.trace("detailBoard 콜");
			return "detailBoard2";
		}
	}

}
