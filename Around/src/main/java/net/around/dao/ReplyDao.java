package net.around.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import net.around.dto.Reply;

@Mapper
public interface ReplyDao {
	public List<Reply> selectReplyByBoardNo(int boardNo);
	public int insertReply(Reply reply);
}
