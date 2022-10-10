package net.around.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.around.dao.ReplyDao;
import net.around.dto.Reply;

@Service
public class ReplyServiceImpl implements ReplyService {
	static Logger logger = LoggerFactory.getLogger(ReplyServiceImpl.class);
		
	
	@Autowired
	ReplyDao replyDao;
	
	@Override
	public List<Reply> selectReplyByBoardNo(int boardNo) {
		logger.trace("ReplyServiceImpl - selectReplyByBoardNo");
		return replyDao.selectReplyByBoardNo(boardNo);
	}

	@Override
	public int insertReply(Reply reply) {
		logger.trace("ReplyServiceImpl - insertReply");
		return replyDao.insertReply(reply);
	}

}
