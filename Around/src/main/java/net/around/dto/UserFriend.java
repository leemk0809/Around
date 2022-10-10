package net.around.dto;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserFriend {
	int listNo;
	int friendNo;
	String statue;
	int userNo;
	String alarm;
	String userName;
	String nickname;
	private Set<UserFriend> usersSet; 
	String profilePath;
}
