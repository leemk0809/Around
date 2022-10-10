package net.around.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.around.dao.UserFriendDao;
import net.around.dto.User;
import net.around.dto.UserFriend;
import net.around.exception.FriendNotFoundException;

@Service
public class UserFriendServiceImpl implements UserFriendService {
	
	static Logger logger = LoggerFactory.getLogger(UserFriendServiceImpl.class);
	
	@Autowired
	UserFriendDao ufDao;
	
	@Autowired
	UserService userSerivce;
	
	@Override
	public List<UserFriend> friendList(int userNo) {
		logger.trace("UserFriendServiceImpl - friendList() 동작");
		List<UserFriend> friends = ufDao.friendList(userNo);
		return friends;
	}

	@Override
	public int updateAlarm(Map<String, Object> friend) {
		logger.trace("UserFriendServiceImpl - updateAlarm() 동작");
		return ufDao.updateAlarm(friend);
	}

	@Override
	public String selectAlarm(int friendNo, int userNo) {
		logger.trace("UserFriendServiceImpl - selectAlarm() 동작");
		Map<String, Object> filter = new HashMap<>();
		filter.put("userNo", userNo);
		filter.put("friendNo", friendNo);
		return ufDao.selectAlarm(filter);
	}

	@Override
	public int deleteFriend(int friendNo) {
		logger.trace("UserFriendServiceImpl - deleteFriend() 동작");
		return ufDao.deleteFriend(friendNo);
	}
	
	@Override
	public User searchUserbyNickname(String nickname) {
		logger.trace("UserFriendServiceImpl - searchUserbyNickname() 동작");
		return userSerivce.searchByNickname(nickname);
	}

	@Override
	public int insertFriend(int friendNo, int userNo) {
		logger.trace("UserFriendServiceImpl - insertFriend() 동작");
		List<UserFriend> friends = friendList(userNo);
		
		// 추가하려는 친구가 이미 있는 친구 인지 검색
		String searchResult = searchFriend(friendNo, friends);
		logger.trace("serachResult : {}", searchResult);
		
		if("Friend Not Found".equals(searchResult)){
			UserFriend userFriend = new UserFriend(0,friendNo,"friend",userNo,"on","","",null,"");
			return ufDao.insertFriend(userFriend);
		} else {
			throw new FriendNotFoundException();			
		}
	}
	
	// 민국 - 추가 할 유저의 친구 목록과 추가 하려는 친구번호.
	private String searchFriend(int friendNo, List<UserFriend> friends){
		logger.trace("searchFriend(), friendNo : {}, friends : {}", friends);
		for(int i = 0 ; i < friends.size(); i++){
			if(friends.get(i).getFriendNo() == friendNo){
				logger.trace("searchFriend(), getFriendNo : {} , friendNo : {}", friends.get(i).getFriendNo(), friendNo);
				return "Friend Found";
			}
		}
		return "Friend Not Found";
	}

}
