package net.around.exception;

public class SearchFailException extends RuntimeException{
	public SearchFailException(){
		super("찾기 오류");
	}
}
