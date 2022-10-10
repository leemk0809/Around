package net.around.service;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.around.dao.PromiseDao;
import net.around.dto.Promise;

@Service
public class PromiseServiceImpl implements PromiseService {

	static Logger logger = LoggerFactory.getLogger(PromiseServiceImpl.class);
	
	@Autowired
	PromiseDao pDao;
	
	@Override
	public int insertPromise(Promise promise) {
		logger.trace("PromiseServiceImpl - insertPromise 동작");
		int result = pDao.insertPromise(promise);
		return result;
	}

	@Override
	public int updatePromise(Map<String, Object> ProAndDate, Promise promise) {
		logger.trace("PromiseServiceImpl - updatePromise 동작");
		
		int result;
		
		Promise updatePromise = pDao.getPromiseByProAndDate(ProAndDate);
		
		logger.trace("updatePromise : {}", updatePromise);
		
		updatePromise.setPromiseTitle(promise.getPromiseTitle());
		updatePromise.setPromiseDate(promise.getPromiseDate());
		updatePromise.setPromiseLatitude(promise.getPromiseLatitude());
		updatePromise.setPromiseLongitude(promise.getPromiseLongitude());
		updatePromise.setPromiseContent(promise.getPromiseContent());
		updatePromise.setPromiseStatus(promise.getPromiseStatus());
		
		result = pDao.updatePromise(updatePromise);
		
		return result;
	}

	@Override
	public List<Promise> getMyPromiseByPromote(int userNo) {
		logger.trace("PromiseServiceImpl - getMyPromiseByPromote 동작");
		List<Promise> promise = pDao.getMyPromiseByPromote(userNo);
		return promise;
	}

	@Override
	public List<Promise> getMyPromiseByInvitee(int userNo) {
		logger.trace("PromiseServiceImpl - getMyPromiseByInvitee 동작");
		List<Promise> promise = pDao.getMyPromiseByInvitee(userNo);
		return promise;
	}

	@Override
	public int deletePromise(int promiseId) {
		logger.trace("PromiseServiceImpl - deletePromise 동작");
		int result = pDao.deletePromise(promiseId);
		return result;
	}

	@Override
	public String selectStatus(int promiseId) {
		logger.trace("PromiseServiceImpl - selectStatus 동작");
		Promise p = pDao.selectStatus(promiseId);
		return p.getPromiseStatus();
	}

	@Override
	public int updateStatus(int promiseId, String status) {
		logger.trace("PromiseServiceImpl - selectStatus 동작");
		Promise p = new Promise();
		p.setPromiseId(promiseId);
		p.setPromiseStatus(status);
		return pDao.updateStatus(p);
	}
}
