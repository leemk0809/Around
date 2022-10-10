package net.around.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import net.around.dto.User;
import net.around.service.UserService;

@Controller
public class LoginController {
	static Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@Autowired
	UserService service;
	
	// 민국 : 필수 입력 사항 설정 --> 해당 항목의 값이 없을 경우 오류 발생
	@InitBinder
	public void setEssentialFields(WebDataBinder binder){
		binder.setRequiredFields("userId", "password", "nickname", "userName");
	}
	
	// 민국 : Format형태로 입력 된 문자열을 date로 바꿈
	@InitBinder
	public void setBindingFormat(WebDataBinder binder){
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(format, true));
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(Model model, HttpSession session, @RequestParam String id, @RequestParam String password) {
		logger.trace("class : LoginController, method : login ////// id : {}, password : {}",id, password);
		
		User loginUser = service.loginUser(id, password);		
		
		if (loginUser != null) {
			session.setAttribute("userNo", loginUser.getUserNo());
			model.addAttribute("message", loginUser);
			return "mainBoard2";
		} else {
			// 민국 - 로그인이 안됬을 시, 로그인 실패에 해당하는 페이지나 알람을 보여줘야 함.
			return "index";
		}
	}
	
	/*
	//login확인 servlet
	@RequestMapping(value="/androidLogin/{Jlogin:.+}", method=RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody Map<String, Object> androidLogin(@PathVariable("Jlogin") String Jlogin, HttpSession session){
		Map<String, Object> result = new HashMap<>();
		Gson gson = new Gson();
		Map<String, Object> userInfo = gson.fromJson(Jlogin, Map.class);
		User loginUser = service.loginUser((String)userInfo.get("userId"), (String)userInfo.get("userPass"));
		
		result.put("userId", loginUser.getUserId());
		result.put("userPass", loginUser.getPassword());
		 result.put("userNo", loginUser.getUserNo());
		
		//session.setAttribute("userNo", loginUser.getUserNo());
		//logger.trace("session : {}", session.getAttribute("userNo"));

		return result;
	}
	//login확인 후 session에 userNo를 저장 후 mainBoard로 이동
	@RequestMapping(value="/androidMainBoard/{JuserNo:.+}", method=RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public String androidLoginBoard(@PathVariable("JuserNo") String JuserNo, HttpSession session){

		logger.trace("main page 전");
		Gson gson = new Gson();
		Map<String, Object> map = gson.fromJson(JuserNo, Map.class);
		int userNo = (int)((double)map.get("userNo"));
		
		logger.trace("userNo : {}", userNo);
		
		if(String.valueOf(userNo) != null){
			//세션 저장
			session.setAttribute("userNo",userNo);
			logger.trace("session 저장");
			return "mainBoard2";
		}else{
			logger.trace("session 저장 실패");
			return "index";
		}
	}
	*/
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpSession session) {
		
		Object objUserNo = session.getAttribute("userNo");
		if(objUserNo != null){
			session.removeAttribute("userNo");			
		}
		
		return "index";
	}

	// 민국 - 특정 경우에 index 페이지로 보내야 할 경우 사용
	@RequestMapping(value = "/returnIndex", method = RequestMethod.GET)
	public String returnIndex(HttpSession session) {
		return "index";
	}
	
	@RequestMapping(value = "/joinPage", method = RequestMethod.GET)
	public String joinPage(Model model) {
		logger.trace("class : LoginController, method : joinPage");
		User user = new User();
		//model.addAttribute("user", new User(1,"","","","","","",new Date(),""));
		model.addAttribute("user", user);
		return "join2";
	}
	
	private static final String uploadDir = "C:/Users/EG-717-8/git/FinalProject-3/projectBy3/src/main/webapp/WEB-INF/assets/images/userImages/";
	//private static final String uploadDir = "E:/sts-bundle/pivotal-tc-server-developer-3.1.5.RELEASE/server/wtpwebapps/projectBy3/WEB-INF/assets/images";
	//private static final String uploadDir = "C:/Users/1-718-8/git/FinalProject-3/projectBy3/src/main/webapp/WEB-INF/assets/images/userImages/";
	
	@RequestMapping(value = "/joinPage", method = RequestMethod.POST)
	public String join(Model model, User user, BindingResult result, HttpSession session, @RequestParam MultipartFile file) throws IllegalStateException, IOException{
		logger.trace("class : LoginController, method : join, userInfo : {}", user);
		
		
		
		if(user != null){
			File uploadFile = new File ( uploadDir + user.getUserId() +"."+file.getOriginalFilename());
			if(file.getOriginalFilename().length()!=0){
				file.transferTo(uploadFile);
				user.setProfilePath(uploadFile.getName());
				logger.trace("회원가입 사진 업로드 :{}",uploadFile.getName());
			}
			logger.trace("유저:{}",user);
			service.insertUser(user);
			
		}
		return "index";
	}
	
	
	
	/*@RequestMapping(value = "/searchId", method=RequestMethod.GET)
	public String searchIdPage(Model model){
		logger.trace("class : LoginController, method : searchIdPage");
		return "searchId";
	}*/
	
	@RequestMapping(value = "/searchId", method = RequestMethod.GET)
	public @ResponseBody String searchId(@RequestParam String name, @RequestParam String email) {
		logger.trace("class : LoginController, method : searchId");
		String id = service.searchId(name, email);
		return id;
	}
/*	
	@RequestMapping(value = "/searchPass", method=RequestMethod.GET)
	public String searchPassPage(Model model){
		logger.trace("class : LoginController, method : searchPassPage");
		return "searchPass";
	}*/
	
	@RequestMapping(value = "/searchPass", method = RequestMethod.GET)
	public @ResponseBody String searchPass(@RequestParam String id,@RequestParam String name, @RequestParam String email) {
		logger.trace("class : LoginController, method : searchPass");
		String password = service.searchPw(id, name, email);
		return password;
	}

/*	@RequestMapping(value = "/searchId", method = RequestMethod.POST)
	public String searchId(Model model, User user) {
		logger.trace("class : LoginController, method : searchId");
		return "searchId";
	}*/
	
	/*@RequestMapping(value = "/searchPw", method = RequestMethod.POST)
	public String searchPw(Model model, User user) {
		logger.trace("class : LoginController, method : searchPw");

		return "searchPw";
	}*/	
	
}
