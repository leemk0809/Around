package net.around.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import net.around.dto.UserFriend;

@Mapper
public interface UserFriendDao {
	public List<UserFriend> friendList(int userNo);
	public int updateAlarm(Map<String , Object> friend);
	public int deleteFriend(int friendNo);
	public int insertFriend(UserFriend userFriend);
	public String selectAlarm(Map<String, Object> filter);
}
