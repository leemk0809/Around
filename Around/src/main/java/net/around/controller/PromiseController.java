package net.around.controller;

import java.sql.Date;
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

import net.around.dto.Promise;
import net.around.dto.User;
import net.around.service.PromiseService;
import net.around.service.UserFriendService;
import net.around.service.UserService;


@Controller
public class PromiseController {
	static Logger logger = LoggerFactory.getLogger(PromiseController.class);
	
	@Autowired
	UserFriendService userFriendService;
	@Autowired
	PromiseService promiseService;
	@Autowired
	UserService usersService;
	
	
	@RequestMapping(value="/promiseBoard")
	public String promiseBoard(Model model,HttpSession session){
		// 사용자 넘버로 자기 자신의 약속과 약속 당한 약속을 model에 저장하고 promiseMainBoard에 넘겨 주면 됩니다.		
		
		List<Promise> Inviteepromises = promiseService.getMyPromiseByInvitee((int)session.getAttribute("userNo"));
		List<Promise> Promotepromises = promiseService.getMyPromiseByPromote((int)session.getAttribute("userNo"));
		
		model.addAttribute("Inviteepromises", Inviteepromises);
		model.addAttribute("Promotepromises", Promotepromises);	
		return "promiseMainBoard2";
	}
	
	@RequestMapping(value="/updatepromiseStatus", method=RequestMethod.POST)
	public @ResponseBody String updatepromiseStatus(@RequestParam String status, @RequestParam int promiseId){
		
		if("거절".equals(status)){
			logger.trace("status : {}", status);
			int result = promiseService.deletePromise(promiseId);
			logger.trace("거절 후 삭제 성공:{}",result);
			
			return "delete";
		} else {
			int result = promiseService.updateStatus(promiseId, status);
			logger.trace("성공:{}",result);
			
			String str = promiseService.selectStatus(promiseId);
			logger.trace("str:{}",str);
			return str;
		}
	}
	@RequestMapping(value="/deletePromise", method=RequestMethod.GET)
	public String deletePromise(HttpSession session, Model model, @RequestParam int promiseId){
		int result = promiseService.deletePromise(promiseId);
		logger.trace("삭제 성공:{}",result); 
		
		List<Promise> Inviteepromises = promiseService.getMyPromiseByInvitee((int)session.getAttribute("userNo"));
		List<Promise> Promotepromises = promiseService.getMyPromiseByPromote((int)session.getAttribute("userNo"));
		
		model.addAttribute("Inviteepromises", Inviteepromises);
		model.addAttribute("Promotepromises", Promotepromises);	
		return "promiseMainBoard2";
	}
	
	
	
	
	
	@RequestMapping(value="/promise", method=RequestMethod.GET)
	public String promisePage(Model model,HttpSession session){		
		Promise promise = new Promise();
		List<User> friends = usersService.selectUserFriends((int)session.getAttribute("userNo"));
		
		logger.trace("promise!! : {}", promise);
		
		model.addAttribute("promise",promise);
		model.addAttribute("friends",friends);
		
		return "promise2";
	}
	@RequestMapping(value="/promise", method=RequestMethod.POST)
	public String promise(Model model,HttpSession session,Promise promise){
		promise.setPromoter((int)session.getAttribute("userNo"));
		promise.setPromiseStatus("약속 중");
		if(promise.getPromiseDate()==null){
			promise.setPromiseDate(new Date(0));
		}
		
		logger.trace("약속 잡기 :{}",promise);
		int result = promiseService.insertPromise(promise);
		logger.trace("약속 접기 성공 :{}",result);	
		
		List<Promise> Inviteepromises = promiseService.getMyPromiseByInvitee((int)session.getAttribute("userNo"));
		List<Promise> Promotepromises = promiseService.getMyPromiseByPromote((int)session.getAttribute("userNo"));
		
		model.addAttribute("Inviteepromises", Inviteepromises);
		model.addAttribute("Promotepromises", Promotepromises);	
		return "promiseMainBoard2";
	}
	
	
	
	@RequestMapping(value="/testPage")
	public String test(Model model,HttpSession session){
		return "LocationTest/testPage";
	}
}
