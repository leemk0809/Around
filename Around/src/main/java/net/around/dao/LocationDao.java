package net.around.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import net.around.dto.UserLocation;

@Mapper
public interface LocationDao {
	public List<UserLocation> userAllLocation(int userNo);
	public int insertUserLocation(UserLocation userLocation);
	public int deleteUserLocation(int userLocationNo);
	public UserLocation userLocationOne(String LocationName);
}
