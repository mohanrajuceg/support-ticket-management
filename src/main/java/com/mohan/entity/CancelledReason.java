package com.mohan.entity;

import java.util.stream.Stream;

import lombok.Getter;

@Getter
public enum CancelledReason {
	ENDUSER,
	OTHERS;
	
	public static CancelledReason of(String text) {
		return Stream.of(values())
				.filter(status -> status.toString().equals(text))
				.findFirst().orElseThrow(() -> new IllegalArgumentException("Unsupported value : " + text));
	}
}