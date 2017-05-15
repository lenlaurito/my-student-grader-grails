package com.synacy.mystudentgrader.student


public enum Gender {
	MALE, FEMALE

	public static Gender valueOfGender(String genderString) {
		values().find { it.name().equalsIgnoreCase(genderString) }
	}
}
