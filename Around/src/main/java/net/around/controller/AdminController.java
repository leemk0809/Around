package net.around.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import net.around.dto.Admin;
import net.around.dto.Board;
import net.around.dto.Category;
import net.around.dto.HotBoard;
import net.around.dto.User;
import net.around.service.BoardService;
import net.around.service.CategoryService;
import net.around.service.HotBoardService;
import net.around.service.UserService;

@Controller
public class AdminController {
	static Logger logger = LoggerFactory.getLogger(AdminController.class);
	
	@Autowired
	BoardService boardService;
	@Autowired
	UserService userService;
	@Autowired
	CategoryService categoryService;
	@Autowired
	HotBoardService hotBoardService;
	
	@RequestMapping(value="/admin")
	public String amdin(){
		logger.trace("admin");
		return "adminLogin";
	}
	
	@RequestMapping(value="/adminMain")
	public String admain(){
		return "adminMain2";
	}
	@RequestMapping(value = "/adminLogin", method = RequestMethod.POST)
	public String adminLogin(Model model, HttpSession session, @RequestParam String id, @RequestParam String password) {
		logger.trace("class : LoginController, method : login ////// id : {}, password : {}",id, password);
		
		Admin admin = new Admin();
		
		if(id.equals(admin.getAdminId())&&password.equals(admin.getPassword())){
			return "adminMain2";
		}else{
			return "adminLogin";
		}
			
		
		/*User loginUser = service.loginUser(id, password);		
		
		if (loginUser != null) {
			session.setAttribute("userNo", loginUser.getUserNo());
			model.addAttribute("message", loginUser);
			return "admin/mainBoard";
		} else {
			// 민국 - 로그인이 안됬을 시, 로그인 실패에 해당하는 페이지나 알람을 보여줘야 함.
			return "index";
		}*/
		
		
	}
	
	@RequestMapping(value="/adminBoard", method=RequestMethod.GET)
	public String adminBoard(Model model,@RequestParam Integer boardNo,HttpSession session){
		logger.trace("class : MainController, method : adminBoard");		
		logger.trace("boardNo : {}",boardNo);		
		Board board = boardService.selectBoard(boardNo);		
		model.addAttribute("board", board);			
		return "adminBoard";
			
	}
	
	
	
	@RequestMapping(value="/adminDeleteBoard", method=RequestMethod.GET)
	public String adminDeleteBoard(HttpSession session,@RequestParam Integer boardNo){
		int result;
		logger.trace("class : BoardController, method : deleteBoard");	
		result = boardService.deleteBoardByBoardNo(boardNo);
		logger.trace("삭제 결과 : {}", result);
		return "adminMain2";
	}
	
	@RequestMapping(value="/returnAdminMainBoard", method=RequestMethod.GET)
	public String returnAdminMainBoard(){		
		return "adminMain2";
	}
	
	@RequestMapping("/adminMemberInit")
	public String initMember(){
		return "adminMember2";	
	}
	
	@RequestMapping(value="/adminMember")
	public @ResponseBody List<User> friendList(Model model, HttpSession session,@RequestParam Integer index){
		
		logger.trace("index : {}",index);
		logger.trace("class : FriendController, method : friendList");
		List<User> users = userService.selectAllUserByPaging(index);		
		return users;	
	}
	
	
	
	@RequestMapping(value="/deleteAdminUser")
	public String deleteAdminUser(Model model,@RequestParam Integer userNo){	
		logger.trace("삭제 call:{}",userNo);
		int result = userService.deleteUser(userNo);
		logger.trace("삭제 :{}",result);
		List<User> users = userService.AllUser();
		model.addAttribute("users",users);
		return "adminMember";	
	}
	
	
	
	@RequestMapping(value="/searchMember", method=RequestMethod.GET)
	public @ResponseBody List<User> searchMember(@RequestParam Integer index,@RequestParam String searchStr){
		logger.trace("searchMember");
		List<User> users = userService.searchByNickNameUserNameUsers(index, searchStr);
		return users;
	}
	
	@RequestMapping("/adminCategory")
	public String initCategory(){
		return "adminCategory";	
	}
	
	@RequestMapping(value="/adminCategoryLoad",method=RequestMethod.GET)
	public @ResponseBody List<Category> adminCategoryLoad(Model model, HttpSession session,@RequestParam Integer index){
		
		logger.trace("index : {}",index);
		logger.trace("class : FriendController, method : friendList");
		List<Category> Category = categoryService.selectAllCategoryByPaging(index);
		return Category;	
	}
	
	
	@RequestMapping(value="/searchCategory", method=RequestMethod.GET)
	public @ResponseBody List<Category> searchCategory(@RequestParam Integer index,@RequestParam String searchStr){
		logger.trace("searchCategory");
		List<Category> cate = categoryService.searchByCategoryName(index, searchStr);
		return cate;
	}
	
	@RequestMapping(value="/insertCategory", method=RequestMethod.GET)
	public @ResponseBody List<Category> insertCategory(@RequestParam Integer index,@RequestParam String searchStr){
		logger.trace("insertCategory");
		categoryService.insertCategory(searchStr);
		List<Category> cate = categoryService.selectAllCategoryByPaging(index);
		return cate;
	}
	
	@RequestMapping(value="/adminLogout")
	public String logout(HttpSession session){
		//session.
		return "adminLogin";
	}
	
	@RequestMapping(value="/adminwriteHotBoard")
	public String writeHotBoard(Model model, HttpSession session){
		logger.trace("class : BoardController, method : writeBoard");
		
		List<Category> category = categoryService.selectAllCategory();
		model.addAttribute("category", category);		
		
		HotBoard hot = new HotBoard();
		model.addAttribute("hot", hot);		
		return "adminWriteHotBoard";
	}
	
	// 민국, 위치
		//private static final String uploadDir = "C:/Users/1-718-8/git/FinalProject-3/projectBy3/src/main/webapp/WEB-INF/assets/images/userImages/";
		private static final String uploadDir = "C:/Users/EG-717-8/git/FinalProject-3/projectBy3/src/main/webapp/WEB-INF/assets/images/userImages/";
		//private static final String uploadDir = "E:/sts-bundle/pivotal-tc-server-developer-3.1.5.RELEASE/server/wtpwebapps/projectBy3/WEB-INF/assets/images";
	
	@RequestMapping(value="/adminwriteHotBoard", method=RequestMethod.POST)
	public String writeHotBoard(Model model, HttpSession session,HotBoard hotBoard,@RequestParam MultipartFile file)throws IllegalStateException, IOException{
		logger.trace("class : BoardController, method : writeBoard");
		File uploadFile = new File ( uploadDir +"." +System.currentTimeMillis()+file.getOriginalFilename());
		if(file.getOriginalFilename().length()!=0){
			logger.trace("파이리 있다 :{}",file.getOriginalFilename().length());
			file.transferTo(uploadFile);
			hotBoard.setFilePath(uploadFile.getName());
		}
		
		int result = hotBoardService.insertHotBoard(hotBoard);
		logger.trace("성공:{}",result);
		
		return "adminMain2";
	}
	
	
	@RequestMapping(value="/adminHotBoard", method=RequestMethod.GET)
	public String adminAdBoard(Model model,HttpSession session){
		logger.trace("class : AdminController, method : adminAdBoard");
		
		return "adminHotBoard";			
	}
	
	@RequestMapping(value="/getAllHotBoard", method=RequestMethod.GET)
	public @ResponseBody List<HotBoard> getAllHotBoard(Model model,HttpSession session){
		logger.trace("class : AdminController, method : getAllHotBoard");
		List<HotBoard> hot =  hotBoardService.selectAllHotBoard();
		
		logger.trace("hotBoard : {}", hot);
		
		//model.addAttribute("hot", hot);
		return hot;			
	}

	
	
	
	
	
	
}
