package net.around.service;

import java.util.List;

import net.around.dto.User;

public interface UserService {
	public User loginUser(String id, String password);
	public List<User> AllUser();
	public int insertUser(User user);
	public String searchId(String name,String email);
	public String searchPw(String id,String name,String email);
	public int deleteUser(int userNo);
	public List<User> selectAllUserByPaging(int pageNo);
	public User searchByNickname(String nickname);
	public List<User> searchByNickNameUserNameUsers(int pageNo, String search);
	public String selectUserProfilePathByUserNo(int userNo);
	public User searchUserByUserNo(int userNo);
	public List<User> selectUserFriends(int userNo);
	public List<User> selectNotFriends(int userNo);
	public List<User> selectIfYouKnow(int userNo);
}