package net.around.service;

import java.util.List;
import java.util.Map;

import net.around.dto.Promise;

public interface PromiseService {
	public int insertPromise(Promise promise);
	public int updatePromise(Map<String, Object> ProAndDate, Promise promise);
	public List<Promise> getMyPromiseByPromote(int userNo);
	public List<Promise> getMyPromiseByInvitee(int userNo);
	public int deletePromise(int promiseId);
	public String selectStatus(int promiseId);
	public int updateStatus(int promiseId, String status);
}
