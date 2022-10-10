package net.around.dto;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Reply {
	int replyNo;
	int boardNo;
	int userNo;
	String replyContets;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	Date replyDate;
}
