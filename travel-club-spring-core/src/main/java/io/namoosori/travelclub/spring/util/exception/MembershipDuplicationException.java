package io.namoosori.travelclub.spring.util.exception;

public class MembershipDuplicationException extends RuntimeException {
	//
	private static final long serialVersionUID = -7196327736293090551L;

	public MembershipDuplicationException(String message) {
		super(message);
	}

}
