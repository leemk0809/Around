package net.around.dao;

import org.apache.ibatis.annotations.Mapper;

import net.around.dto.UserHistory;

@Mapper
public interface UserHistoryDao {
	public int insertUserHistory(UserHistory userHistory);

}
