package com.mohan.entity;

import java.util.stream.Stream;

import lombok.Getter;

@Getter
public enum Status {
	CANCELLED,
	COMPLETED;
	
	public static Status of(String text) {
		return Stream.of(values())
				.filter(status -> status.toString().equals(text))
				.findFirst().orElseThrow(() -> new IllegalArgumentException("Unsupported value : " + text));
	}
}