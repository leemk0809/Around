package net.around.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import net.around.dto.Promise;

@Mapper
public interface PromiseDao {
	List<Promise> getAllPromise();
	/**
	 * 사용자 번호로 주최한 약속들을 가져온다.
	 * @param userNo
	 * @return
	 */
	List<Promise> getMyPromiseByPromote(int userNo);
	/**
	 * 사용자 번호로 초대받은 약속들을 가져온다.
	 * @param userNo
	 * @return
	 */
	List<Promise> getMyPromiseByInvitee(int userNo);
	
	public int insertPromise(Promise promise);
	public Promise getPromiseByProAndDate(Map<String, Object> ProAndDate);
	public int updatePromise(Promise promise);
	public int deletePromise(int promiseId);
	public Promise selectStatus(int promiseId);
	public int updateStatus(Promise promise);
}
