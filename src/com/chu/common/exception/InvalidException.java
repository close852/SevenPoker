package com.chu.common.exception;

public class InvalidException extends Exception {

	public InvalidException() {
		super("범위를 넘어 섰습니다. 다시입력하세요");
	}

	public InvalidException(String msg) {
		super(msg);
	}
}
