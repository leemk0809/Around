package net.around.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserCategory {
	int listNo;
	int categoryNo;
	int userNo;
	Category category;
}
