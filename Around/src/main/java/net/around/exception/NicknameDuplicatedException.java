package net.around.exception;

public class NicknameDuplicatedException extends RuntimeException{
	public NicknameDuplicatedException(){
		super("닉네임이 중복됩니다");
	}
}
