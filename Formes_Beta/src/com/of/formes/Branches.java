package com.of.formes;

import java.util.ArrayList;

public class Branches {
	Boolean isExpandable;
	ArrayList<Question> questionList;
	ArrayList<Answer> answerList;

	public Branches(Boolean isExpandable, ArrayList<Question> questionList,
			ArrayList<Answer> answerList) {

		this.isExpandable = isExpandable;
		this.questionList = questionList;
		this.answerList = answerList;
	}

	public int getNoOfQuestions() {
		// TODO Auto-generated method stub
		return questionList.size();
	}

}
