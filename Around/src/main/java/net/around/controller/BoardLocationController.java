package net.around.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import net.around.service.BoardLoactionService;

//location으로 board들을 관리할 컨트롤러
 
@Controller
public class BoardLocationController {
	static Logger logger = LoggerFactory.getLogger(BoardLocationController.class);
	@Autowired
	BoardLoactionService bLService;
	
	/*@RequestMapping(value="/getAroundBoards", method=RequestMethod.GET)
	public @ResponseBody List<Board> getAroundBoards(Model model, HttpSession session){
		logger.trace("class : BoardLocationController, method : getAroundBoards");
		Map<String,Object> filter = new HashMap<>();
		
		float centerLat = (float) 36.81506854521463;
		float centerLng = (float) 127.11321711527751;
		float range = (float) 0.0050;
		// 민국 - 세션이 아직 없어서 주석
		//int userNo = (int) session.getAttribute("userNo");
		int userNo = 1;
		
		filter.put("centerLat", centerLat);
		filter.put("centerLng", centerLng);
		filter.put("range", range);
		filter.put("userNo", userNo);
		
		List<Board> myAroundBoards = bLService.getBoardsBycenterLocation(filter);
		
		model.addAttribute("myAroundBoards", myAroundBoards);
		logger.trace("getAroundBoards 끝");
		// 임시
		return myAroundBoards;
	}*/
}
