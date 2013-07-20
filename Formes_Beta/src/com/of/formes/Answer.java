package com.of.formes;

import android.util.SparseArray;

public class Answer {
	Integer minLength, maxLength;
	AnswerType type;
	String defaultValue;
	Boolean readonly;
	Integer answerId;
	SparseArray<String> answerChoices;

	public Answer(Integer minLenght, Integer maxLength, String type,
			String defaultValue, Boolean readonly, Integer answerId,
			SparseArray<String> answerChoices) {

		this.minLength = minLenght;
		this.maxLength = maxLength;
		this.type = AnswerType.valueOf(type);
		this.defaultValue = defaultValue;
		this.readonly = readonly;
		this.answerId = answerId;
		this.answerChoices = answerChoices;
	}

	public String[] getChoicesArrar() {
		String[] ansChoiceArray = new String[2];
		for (int i = 0; i < answerChoices.size(); i++) {
			ansChoiceArray[i] = answerChoices.get(i);
		}
		return ansChoiceArray;
	}

}