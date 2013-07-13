package com.of.formes;

public class Answer {
	Integer minLength, maxLength;
	AnswerType type;
	String defaultValue;
	Boolean readonly;
	Integer answerId;

	public Answer(Integer minLenght, Integer maxLength, String type,
			String defaultValue, Boolean readonly, Integer answerId) {

		this.minLength = minLenght;
		this.maxLength = maxLength;
		this.type = AnswerType.valueOf(type);
		this.defaultValue = defaultValue;
		this.readonly = readonly;
		this.answerId = answerId;
	}

}