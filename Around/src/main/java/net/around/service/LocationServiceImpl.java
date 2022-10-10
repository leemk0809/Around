package net.around.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.around.dao.LocationDao;
import net.around.dao.UserLocationDao;
import net.around.dto.UserLocation;

@Service
public class LocationServiceImpl implements LocationService {

	static Logger logger = LoggerFactory.getLogger(LocationServiceImpl.class);
	
	@Autowired
	LocationDao lDao;
	
	@Autowired
	UserLocationDao udao;
	
	@Override
	public List<UserLocation> userAllLocation(int userNo) {
		logger.trace("LocationServiceImpl - userAllLocation 동작");
		return udao.userAllLocation(userNo);
	}

	@Override
	public int insertUserLocation(UserLocation userLocation) {
		logger.trace("LocationServiceImpl - insertUserLocation 동작");
		return lDao.insertUserLocation(userLocation);
	}

	@Override
	public int deleteUserLocation(int userLocationNo) {
		logger.trace("LocationServiceImpl - deleteUserLocation 동작");
		return lDao.deleteUserLocation(userLocationNo);
	}

	@Override
	public UserLocation selectOneLocation(String locationName) {
		logger.trace("LocationServiceImpl - selectOneLocation 동작");
		return lDao.userLocationOne(locationName);
	}

}
