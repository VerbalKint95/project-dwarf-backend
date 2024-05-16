package com.maligno.projectdwarf.springboot.exception;

public class IllegalAtkSpeException extends IllegalAccessException {

	private static final long serialVersionUID = 1L;

	public IllegalAtkSpeException(String msg) {
		super(msg +" cannot be resolve as AtkSpe enum");
	}
}
