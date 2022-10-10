package net.around.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoardLocation {
	int boardLocationNo;
	float latitude;
	float longitude;
	int boardNo;
}
