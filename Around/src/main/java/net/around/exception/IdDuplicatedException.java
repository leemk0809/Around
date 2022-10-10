package net.around.exception;

public class IdDuplicatedException extends RuntimeException{
	public IdDuplicatedException(){
		super("아이디가 중복 됩니다");
	}
}
