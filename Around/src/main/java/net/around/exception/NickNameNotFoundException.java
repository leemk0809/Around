package net.around.exception;

public class NickNameNotFoundException extends RuntimeException{
	public NickNameNotFoundException(){
		super("존재하지 않는 닉네임 입니다.");
	}
}
