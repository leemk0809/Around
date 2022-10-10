package net.around.service;

import java.util.List;

import net.around.dto.Reply;

public interface ReplyService {
	public List<Reply> selectReplyByBoardNo(int boardNo);
	public int insertReply(Reply reply);
}
