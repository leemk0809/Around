package net.around.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.around.dao.CategoryDao;
import net.around.dto.Category;

@Service
public class CategoryServiceImpl implements CategoryService {
	static Logger logger = LoggerFactory.getLogger(CategoryServiceImpl.class);
	
	@Autowired
	CategoryDao categoryDao;
	
	@Override
	public List<Category> selectAllCategory() {
		logger.trace("CategoryServiceImpl - selectAllCategory() 동작");
		return categoryDao.selectAllCategory();
	}

	@Override
	public List<Category> selectAllCategoryByPaging(int pageNo) {
		logger.trace("CategoryServiceImpl - selectAllCategoryByPaging() 동작");
		Map<String, Object> filter = new HashMap<>();
		filter.put("from", 20*pageNo-19);
		filter.put("to", 20*pageNo);	
		
		return categoryDao.selectAllCategoryByPaging(filter);
	}

	@Override
	public List<Category> searchByCategoryName(int pageNo, String searchStr) {
		logger.trace("CategoryServiceImpl - searchByCategoryName() 동작");
		Map<String, Object> filter = new HashMap<>();
		filter.put("from", 20*pageNo-19);
		filter.put("to", 20*pageNo);
		filter.put("categoryName", searchStr);
		
		return categoryDao.searchByCategoryName(filter);
	}

	@Override
	public int insertCategory(String categoryName) {
		logger.trace("CategoryServiceImpl - insertCategory() 동작");
		Category category = new Category();
		category.setCategoryName(categoryName);		
		return categoryDao.insertCategory(category);
	}

	@Override
	public List<Category> selectAllCategoryByUser(int userNo) {
		logger.trace("CategoryServiceImpl - selectAllCategoryByUser() 동작");
		return categoryDao.selectAllCategoryByUser(userNo);
	}

}
