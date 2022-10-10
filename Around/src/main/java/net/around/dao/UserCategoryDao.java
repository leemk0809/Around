package net.around.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import net.around.dto.UserCategory;

@Mapper
public interface UserCategoryDao {
	public List<UserCategory> selectAllUserCategory();	
	public List<UserCategory> selectUserCategory(int userNo);
	public int insertUserCategory(UserCategory userCategory);
	public int deleteUserCategory(UserCategory userCategory);
}
