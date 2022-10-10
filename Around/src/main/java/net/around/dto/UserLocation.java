package net.around.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserLocation {
	int userLocationNo;
	String locationName;
	Date registerDate;
	int userNo;
	float latitude;
	float longitude;
}
