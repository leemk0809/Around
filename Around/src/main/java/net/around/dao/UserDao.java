package net.around.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import net.around.dto.User;

@Mapper
public interface UserDao {
	public List<User> selectAllUser();
	public User selectUser(String userId);
	public User selectUserByNickname(String nickname);
	public int insertUser(User user);
	public User searchId(Map<String,Object> info);
	public User searchPw(Map<String,Object> info);
	public int deleteUser(int userNo);
	public List<User> selectAllUserByPaging(Map<String, Object> filter);
	public List<User> searchByNickNameUserNameUsers(Map<String,Object> filter);
	public User selectUserProfilePathByUserNo(int userNo);
	public User searchUserByUserNo(int userNo);
	public List<User> selectUserFriends(int userNo);
	public List<User> selectNotFriends(int userNo);
	public List<User> selectIfYouKnow(int userNo);
}
