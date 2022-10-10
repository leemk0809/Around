package net.around.controller;

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

import net.around.dto.Category;
import net.around.dto.UserCategory;
import net.around.service.CategoryService;
import net.around.service.UserCategoryService;
import net.around.service.UserFriendService;
import net.around.service.UserService;

@Controller
public class CategoryController {
	
	static Logger logger = LoggerFactory.getLogger(CategoryController.class);
	
	@Autowired
	UserFriendService service;
	
	@Autowired
	UserService userService;
	
	@Autowired
	UserCategoryService userCategoryService;
	
	@Autowired
	CategoryService categoryService;
	
	
	@RequestMapping(value="/initSearchUserCategory", method=RequestMethod.GET)
	public String initSearchFriend(Model model, HttpSession session){
		logger.trace("class : CategoryController, method : initSearchFriend");
		
		List<Category> category = categoryService.selectAllCategoryByUser((int) session.getAttribute("userNo"));
		model.addAttribute("category", category);
		
		return "searchCategory2";
	}
	
	@RequestMapping(value="/searchCategory", method=RequestMethod.POST)
	public String searchCategory(Model model, HttpSession session, @RequestParam String categoryName){
		logger.trace("class : CategoryController, method : searchCategory");
		
		if(categoryName != null){
			
			List<Category> category = categoryService.searchByCategoryName(1, categoryName);	
			
			model.addAttribute("category", category);
			
			
		}
		
		return "searchCategory2";
	}
	
	@RequestMapping(value="/insertUserCategory", method=RequestMethod.GET)
	public String insertUserCategory(HttpSession session, Model model, @RequestParam int userNo){
		logger.trace("class : CategoryController, method : insertUserCategory");
		
		// jsp에서 가져온 userNo -> friendNo
		int categoryNo = userNo;
		
		// insert를 요청한 userNo
		int myUserNo = (int) session.getAttribute("userNo");		
	
		UserCategory userCategory = new UserCategory();
		userCategory.setCategoryNo(categoryNo);
		userCategory.setUserNo(myUserNo);
		userCategoryService.insertUserCategory(userCategory);
		
		List<UserCategory> category = userCategoryService.selectUserCategory(myUserNo);
		model.addAttribute("categories", category);
		
		return "category2";
	
	}
	
	@RequestMapping(value="/deleteUserCategory", method=RequestMethod.GET)
	public String deleteUserCategory(HttpSession session, Model model, @RequestParam int categoryNo){
		logger.trace("class : CategoryController, method : deleteUserCategory");
		
		int userNo = (int) session.getAttribute("userNo");
		
		userCategoryService.deleteUserCategory(userNo, categoryNo);		
		
		List<UserCategory> category = userCategoryService.selectUserCategory(userNo);
		model.addAttribute("categories", category);
		return "category2";
	}
	
	
	
	
	
	
}
