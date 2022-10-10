package net.around.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import net.around.dto.User;
import net.around.dto.UserFriend;
import net.around.service.CategoryService;
import net.around.service.UserCategoryService;
import net.around.service.UserFriendService;
import net.around.service.UserService;

@Controller
public class FriendController {
	
	static Logger logger = LoggerFactory.getLogger(FriendController.class);
	
	@Autowired
	UserFriendService service;
	
	@Autowired
	UserService userService;
	
	@Autowired
	UserCategoryService userCategoryService;
	
	@Autowired
	CategoryService categoryService;
	
	
	
	@RequestMapping(value="/friendList", method=RequestMethod.GET)
	public String friendList(Model model, HttpSession session){
		logger.trace("class : FriendController, method : friendList");
		int userNo = (int) session.getAttribute("userNo");
		List<UserFriend> friends = service.friendList(userNo);
		for(int i = 0 ; i < friends.size() ; i++){
			friends.get(i).setProfilePath(userService.selectUserProfilePathByUserNo(friends.get(i).getFriendNo()));	
		}
		
		List<User> ifYouKnows = userService.selectIfYouKnow(userNo);	
		logger.trace("fuking : {} ", friends);
		model.addAttribute("ifYouKnows", ifYouKnows);
		model.addAttribute("friends", friends);
//		logger.trace("alarm : {}", friends.get(0).getAlarm());
		return "friendList2";
	}
	
	@RequestMapping(value="/updateAlarm", method=RequestMethod.POST)
	public @ResponseBody String updateAlarm(HttpSession session, @RequestParam String alarm, 
																		  @RequestParam int friendNo){
		int result;
		logger.trace("class : FriendController, method : updateAlarm");
		logger.trace("friendNo : {}", friendNo);
		Map<String, Object> friend = new HashMap<>();
		friend.put("alarm", alarm);
		friend.put("friendNo", friendNo);
		friend.put("userNo", (int) session.getAttribute("userNo"));
		result = service.updateAlarm(friend);
		return service.selectAlarm(friendNo,(int) session.getAttribute("userNo"));
	}
	
	@RequestMapping(value="/deleteFriend", method=RequestMethod.GET)
	public String deleteFriend(HttpSession session, Model model, @RequestParam int friendNo){
		logger.trace("class : FriendController, method : deleteFriend");
		service.deleteFriend(friendNo);
		int userNo = (int) session.getAttribute("userNo");
		List<UserFriend> friends = service.friendList(userNo);
		for(int i = 0 ; i < friends.size() ; i++){
			friends.get(i).setProfilePath(userService.selectUserProfilePathByUserNo(friends.get(i).getFriendNo()));	
		}
		
		List<User> ifYouKnows = userService.selectIfYouKnow(userNo);	
		model.addAttribute("ifYouKnows", ifYouKnows);
		model.addAttribute("friends", friends);
		return "friendList2";
	}
	
	@RequestMapping(value="/initSearchFriend", method=RequestMethod.GET)
	public String initSearchFriend(Model model, HttpSession session){
		logger.trace("class : FriendController, method : initSearchFriend");
		int userNo = (int) session.getAttribute("userNo");
		List<User> users =  userService.selectNotFriends(userNo);
		model.addAttribute("users", users);
		
		return "searchFriend2";
	}
	
	@RequestMapping(value="/searchFriend", method=RequestMethod.POST)
	public String searchFriend(Model model, HttpSession session, @RequestParam String nickname){
		logger.trace("class : FriendController, method : searchFriend");
		
		if(nickname != null){
			List<User> users = new ArrayList<>();
			users.add(service.searchUserbyNickname(nickname));
			model.addAttribute("users", users);
		}
		
		return "searchFriend2";
	}
	
	@RequestMapping(value="/insertFriend", method=RequestMethod.GET)
	public String insertFriend(HttpSession session, Model model, @RequestParam int userNo){
		logger.trace("class : FriendController, method : insertFriend");
		
		// jsp에서 가져온 userNo -> friendNo
		int friendNo = userNo;
		
		// insert를 요청한 userNo
		int myUserNo = (int) session.getAttribute("userNo");
		service.insertFriend(friendNo, myUserNo);
		
		// friendList로 보내기 위한 초기값.
		List<UserFriend> friends = service.friendList(myUserNo);
		
		for(int i = 0 ; i < friends.size() ; i++){
			friends.get(i).setProfilePath(userService.selectUserProfilePathByUserNo(friends.get(i).getFriendNo()));	
		}
		List<User> ifYouKnows = userService.selectIfYouKnow(myUserNo);	
		model.addAttribute("ifYouKnows", ifYouKnows);
		model.addAttribute("friends", friends);
		
		return "friendList2";
	}
	
	
	
	
	
	
	
	
}
