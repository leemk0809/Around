package net.around.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.around.dao.UserCategoryDao;
import net.around.dto.UserCategory;

@Service
public class UserCategoryServiceImpl implements UserCategoryService {
	static Logger logger = LoggerFactory.getLogger(UserCategoryServiceImpl.class);
	
	@Autowired
	UserCategoryDao userCategoryDao;

	@Override
	public List<UserCategory> selectAllUserCategory() {
		logger.trace("UserCategoryServiceImpl - selectAllUserCategory() 동작");
		return userCategoryDao.selectAllUserCategory();
				
	}

	@Override
	public List<UserCategory> selectUserCategory(int userNo) {
		logger.trace("UserCategoryServiceImpl - selectUserCategory() 동작");
		return userCategoryDao.selectUserCategory(userNo);
	}

	@Override
	public int insertUserCategory(UserCategory userCategory) {
		logger.trace("UserCategoryServiceImpl - insertUserCategory() 동작");
		return userCategoryDao.insertUserCategory(userCategory);
	}

	@Override
	public int deleteUserCategory(int userNo,int categoryNo) {
		logger.trace("UserCategoryServiceImpl - deleteUserCategory() 동작");
		
		UserCategory userCategory = new UserCategory();
		userCategory.setUserNo(userNo);
		userCategory.setCategoryNo(categoryNo);		
		return userCategoryDao.deleteUserCategory(userCategory);
	}	
	

}
