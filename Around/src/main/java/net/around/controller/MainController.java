package net.around.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.extern.slf4j.Slf4j;
import net.around.dto.Board;
import net.around.dto.BoardLocation;
import net.around.dto.HotBoard;
import net.around.dto.Reply;
import net.around.dto.User;
import net.around.dto.UserCategory;
import net.around.dto.UserLocation;
import net.around.service.BoardLoactionService;
import net.around.service.BoardService;
import net.around.service.CategoryService;
import net.around.service.HotBoardService;
import net.around.service.LocationService;
import net.around.service.ReplyService;
import net.around.service.UserCategoryService;
import net.around.service.UserService;

@Slf4j
@Controller
public class MainController {

	static Logger logger = LoggerFactory.getLogger(MainController.class);
	
	@Autowired
	BoardService service;
	@Autowired
	UserService userservice;
	@Autowired
	LocationService location;
	@Autowired
	CategoryService categoryService;
	@Autowired
	UserCategoryService userCategory;
	@Autowired
	ReplyService replyService;
	@Autowired
	HotBoardService hotBoardService;
	@Autowired
	BoardLoactionService boardLocationService;
	
	
	// Format 형태로 입력된 문자열을 date로 바꿈
	@InitBinder
	public void setBindingFormat(WebDataBinder binder){
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(format, true));
	}
	
	@RequestMapping(value="getAllBoards", method=RequestMethod.GET)
	public @ResponseBody List<Board> getAllBoards(Model model){
		logger.trace("class : MainController, method : getBoards");
		List<Board> boards = service.viewAllBoards();		
		
		return boards;
	}
	
	@RequestMapping(value="/mainBoard", method=RequestMethod.GET)
	public @ResponseBody List<Board> getAllBoards2(Model model,HttpSession session,@RequestParam Integer index){
		logger.trace("class : MainController, method : getAllBoards2");
		List<Board> boards = service.selectAllBoardByPagingMyFd(index,(int) session.getAttribute("userNo"));		
		return boards;
	}
	
	@RequestMapping(value="/selectBoard", method=RequestMethod.GET)
	public @ResponseBody Board getBoard(Model model,@RequestParam Integer userNo){
		logger.trace("class : MainController, method : getBoard");
		Board board = service.selectBoard(userNo);
		
		return board;
	}
	
	@RequestMapping(value="/selectMyBoard", method=RequestMethod.GET)
	public @ResponseBody List<Board> selectMyBoard(Model model,HttpSession session,@RequestParam Integer index){
		logger.trace("class : MainController, method : selectMyBoard");
		int userNo =  (int) session.getAttribute("userNo");
		logger.trace("userNo : {}, index : {}", userNo, index);
		List<Board> boards = service.selectMyBoardByPaging(userNo,index);
		logger.trace("boards: {}", boards);
		//List<Board> boards = service.selectMyBoard(userNo);
		return boards;
	}
	
	
	@RequestMapping(value="/searchBoard", method=RequestMethod.GET)
	public @ResponseBody List<Board> searchBoard(Model model,HttpSession session,@RequestParam Integer index,@RequestParam String searchStr){
		logger.trace("class : MainController, method : searchBoard");		
		List<Board> boards = service.searchByTitleContent(index,(int) session.getAttribute("userNo"), searchStr);		
		return boards;
	}
	@RequestMapping(value="/searchCategoryBoard", method=RequestMethod.GET)
	public @ResponseBody List<Board> searchCategoryBoard(Model model,HttpSession session,@RequestParam Integer index,@RequestParam Integer categoryNo,@RequestParam String searchStr){
		logger.trace("class : MainController, method : searchCategoryBoard");		
		List<Board> boards = service.searchByTitleContentCategory(index,(int) session.getAttribute("userNo"),categoryNo,searchStr);		
		return boards;
	}
	
	
	
	
	
	
	@RequestMapping(value="/detailBoard", method=RequestMethod.GET)
	public String detailBoard(Model model,@RequestParam Integer boardNo,HttpSession session){
		logger.trace("class : MainController, method : detailBoard");		
		logger.trace("boardNo : {}",boardNo);		
		Board board = service.selectBoard(boardNo);
		BoardLocation bLocation = new BoardLocation();		
		
		if("hidden".equals(board.getViewStatus())){
			bLocation = boardLocationService.selectBoard(boardNo);			
			board.setLatitude(bLocation.getLatitude());
			board.setLongitude(bLocation.getLongitude());
		}
		logger.trace("board : {}", board);
		
		int userNo =  (int) session.getAttribute("userNo");
		List<Reply> re = replyService.selectReplyByBoardNo(boardNo);		
		
		List<String> replyUsers = new ArrayList<>();
				
		for(int i = 0 ; i < re.size(); i++){
			User userTemp = userservice.searchUserByUserNo(re.get(i).getUserNo()); 
			replyUsers.add(userTemp.getNickname()+" : "+re.get(i).getReplyContets());
		}
		logger.trace("replyUsers : {}", replyUsers);		
		model.addAttribute("replyUsers", replyUsers);	
		
		
		List<HotBoard> hots = hotBoardService.selectHotBoardByCategory(board.getCategoryNo());
		model.addAttribute("hots", hots);
		
		logger.trace("boardNo : {}",boardNo);
		logger.trace("userNo : {}",userNo);
		logger.trace("Board userNo : {}",board.getUserNo());
		session.setAttribute("myBoard", board);
		model.addAttribute("board", board);
		if(userNo == board.getUserNo()){
			logger.trace("detailMyBoard 콜");
			return "detailMyBoard2";
		}else{
			logger.trace("detailBoard 콜");
			return "detailBoard2";
		}		
	}
	@RequestMapping(value="/hotBoards", method=RequestMethod.GET)
	public @ResponseBody List<HotBoard> hotBoards(Model model,HttpSession session,@RequestParam Integer categoryNo){
		logger.trace("class : MainController, method : hotBoards");
		List<HotBoard> hotBoards = hotBoardService.selectHotBoardByCategory(categoryNo);		
		return hotBoards;
	}
	
	
	
	
	
	
	
	
	@RequestMapping(value="/mylocation")
	public String userLocation(Model model,HttpSession session){
		logger.trace("class : MainController, method : userLocation");
				
		List<UserLocation> locations=location.userAllLocation((int) session.getAttribute("userNo"));
		model.addAttribute("locations", locations);
		return "userLocation2";
	}
	@RequestMapping(value="/savelocation")
	public @ResponseBody UserLocation savelocation(HttpSession session,@RequestParam Float latitude,@RequestParam Float longitude,@RequestParam String userLocationName) throws ParseException{
		logger.trace("class : MainController, method : savelocation");
		logger.trace("userLocationName :{} , latitude : {} , longitude {}",userLocationName,latitude,longitude);
		UserLocation ul = new UserLocation();
				
		ul.setLatitude(latitude);
		ul.setLocationName(userLocationName);
		ul.setLongitude(longitude);
		ul.setUserNo((int) session.getAttribute("userNo"));
		log.debug("ul : {}", ul);
		location.insertUserLocation(ul);
		UserLocation locations = location.selectOneLocation(userLocationName);
		return locations;
	}
	
	@RequestMapping(value="/deleteLocation", method=RequestMethod.GET)
	public String deleteLocation(Model model, HttpSession session,@RequestParam int userLocationNo){
		logger.trace("class : MainController, method : deleteLocation");
		logger.trace("locationNo: {}", userLocationNo );
		
		location.deleteUserLocation(userLocationNo);
		
		List<UserLocation> locations=location.userAllLocation((int) session.getAttribute("userNo"));
		model.addAttribute("locations", locations);
		
		return "userLocation2";
		
	}
	
	
	@RequestMapping(value="/category")
	public String category(Model model,HttpSession session){
		logger.trace("class : MainController, method : category");
		List<UserCategory> categories = userCategory.selectUserCategory((int) session.getAttribute("userNo"));
		model.addAttribute("categories",categories);		
		return "category2";
	}
	
	@RequestMapping(value="/scategoryBoard")
	public String scategoryBoard(Model model,HttpSession session,@RequestParam Integer categoryNo){
		logger.trace("class : MainController, method : categoryBoard");
		model.addAttribute("categoryNo",categoryNo);		
		return "categoryBoard2";		
	}
	@RequestMapping(value="/categoryBoard")
	public @ResponseBody List<Board> categoryBoard(Model model,HttpSession session,@RequestParam Integer index,@RequestParam Integer categoryNo){
		logger.trace("class : MainController, method : categoryBoard");				
		List<Board> boards = service.selectMyCategoryBoardByPaging((int) session.getAttribute("userNo"), index, categoryNo);	
		return boards;		
	}
	
	@RequestMapping(value="/developer")
	public String developer(){
		return "LocationTest/testMain";
	}
		
	@RequestMapping(value="/compass")
	public String compass(){
		return "compass2";
	}
}
