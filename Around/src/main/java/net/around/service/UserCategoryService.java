package net.around.service;

import java.util.List;

import net.around.dto.UserCategory;

public interface UserCategoryService {
	public List<UserCategory> selectAllUserCategory();
	public List<UserCategory> selectUserCategory(int userNo);
	public int insertUserCategory(UserCategory userCategory);
	public int deleteUserCategory(int userNo,int categoryNo);
}
