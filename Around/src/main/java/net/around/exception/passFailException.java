package net.around.exception;

public class passFailException extends RuntimeException{
	public passFailException(){
		super("비밀번호가 맞지 않습니다");
	}
}
