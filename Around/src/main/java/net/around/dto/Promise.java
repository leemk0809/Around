package net.around.dto;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Promise {
	int promiseId; 
	String promiseTitle;
	int promoter; // 주최자 --> userNo
	int invitee; // 초대 받은 사람 --> userNo
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm")
	Date promiseDate;
	float promiseLatitude;
	float promiseLongitude;
	String promiseContent;
	String promiseStatus;
}
