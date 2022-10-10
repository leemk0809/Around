package net.around.exception;

public class FriendNotFoundException extends RuntimeException{
	public FriendNotFoundException(){
		super("친구가 이미 존재합니다.");
	}
}
