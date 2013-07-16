package com.of.formes;

public class RowLayoutUI {
	public String answerOptions;
	public String questionText;
	public AnswerType answerType;

	public RowLayoutUI() {
		super();

	}

	public RowLayoutUI(String answerOptions, String questionText,
			String answerType) {
		super();
		this.answerOptions = answerOptions;
		this.questionText = questionText;
		this.answerType = AnswerType.valueOf(answerType);
	}

}
