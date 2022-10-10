package net.around.dto;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Board {
	int boardNo;
	String title;
	int hit;
	String content;
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm")
	Date writedDate;
	int userNo;
	int categoryNo;
	String imagePath;
	MultipartFile file;
	String viewStatus;
	/*String file;*/
	float latitude;
	float longitude;
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm")
	Date targetDate;
	private List<BoardLocation> boardLocationsSet;
}
