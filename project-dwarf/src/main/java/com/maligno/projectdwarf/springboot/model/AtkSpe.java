package com.maligno.projectdwarf.springboot.model;

import com.maligno.projectdwarf.springboot.exception.IllegalAtkSpeException;

public enum AtkSpe {
	THEMEGAPUNCH,
	THEABOMINABLESUBMISSION,
	THEFORBIDENSPLINTER;
	
	public static AtkSpe fromString(String value) throws IllegalAtkSpeException {
        String uppercaseValue = value.toUpperCase();

        switch (uppercaseValue) {
            case "THEMEGAPUNCH":
                return THEMEGAPUNCH;
            case "THEABOMINABLESUBMISSION":
                return THEABOMINABLESUBMISSION;
            case "THEFORBIDENSPLINTER":
                return THEFORBIDENSPLINTER;
            default:
                throw new IllegalAtkSpeException(value);
        }
    }
}
