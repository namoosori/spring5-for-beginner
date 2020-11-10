package io.namoosori.travelclub.spring.util.exception;

public class MemberDuplicationException extends RuntimeException {
	//
	private static final long serialVersionUID = -7196327736293090551L;
	
	public MemberDuplicationException(String message) {
		super(message);
	}

}
