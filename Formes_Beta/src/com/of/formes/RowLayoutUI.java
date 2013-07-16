package com.of.formes;

public class RowLayoutUI {
	public String answerOptions;
	public String questionText;
	public AnswerType answerType;

	public RowLayoutUI() {
		super();

	}

	public RowLayoutUI(Answer answer, Question question,
			AnswerType answerType) {
		super();
		this.answerOptions = answer;
		this.questionText = question;
		this.answerType = (answerType);
	}
	
	

}
