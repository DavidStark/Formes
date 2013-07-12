package com.of.formes;

public class Answer {
	Integer minLenght, maxLength;
	AnswerType type;
	String text, defaultValue;
	Boolean readonly;
	Integer answerId;

	public Answer(Integer minLenght, Integer maxLength, AnswerType type,
			String text, String defaultValue, Boolean readonly, Integer answerId) {

		this.minLenght = minLenght;
		this.maxLength = maxLength;
		this.type = type;
		this.text = text;
		this.defaultValue = defaultValue;
		this.readonly = readonly;
		this.answerId = answerId;
	}

}