package net.around.dao;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import net.around.dto.Category;

@Mapper
public interface CategoryDao {
	public List<Category> selectAllCategory();
	public List<Category> selectAllCategoryByPaging(Map<String, Object> filter);
	public List<Category> searchByCategoryName(Map<String, Object> filter);
	public int insertCategory(Category category);
	public List<Category> selectAllCategoryByUser(int userNo);
}
