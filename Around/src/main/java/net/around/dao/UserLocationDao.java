package net.around.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import net.around.dto.UserHistory;
import net.around.dto.UserLocation;

@Mapper
public interface UserLocationDao {
	public int insertUserHistory(UserHistory userHistory);
	public List<UserLocation> userAllLocation(int userNo);
}
