package com.of.formes;

import java.util.ArrayList;

public class Branches {
	Boolean isExpandable;
	ArrayList<Question> questionList = new ArrayList<Question>();
	ArrayList<Answer> answerList = new ArrayList<Answer>();

	public Branches(Boolean isExpandable, ArrayList<Question> questionList,
			ArrayList<Answer> answerList) {

		this.isExpandable = isExpandable;
		this.questionList.addAll(questionList);
		
		this.answerList.addAll(answerList);
	}

	public int getNoOfQuestions() {
		// TODO Auto-generated method stub
		return questionList.size();
	}

}
