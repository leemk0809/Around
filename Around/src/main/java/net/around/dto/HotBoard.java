package net.around.dto;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HotBoard {
	int hotBoardNo;
	int categoryNo;
	String title;
	String contents;
	String filePath;
	MultipartFile file;
}
