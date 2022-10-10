package net.around.service;

import java.util.List;

import net.around.dto.Category;

public interface CategoryService {
	public List<Category> selectAllCategory();
	public List<Category> selectAllCategoryByPaging(int pageNo);
	public List<Category> searchByCategoryName(int pageNo, String searchStr);
	public int insertCategory(String categoryName);
	public List<Category> selectAllCategoryByUser(int userNo);
}
