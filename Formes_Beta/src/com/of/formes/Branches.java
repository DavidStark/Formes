package com.of.formes;

import java.util.ArrayList;

public class Branches {
	Boolean isExpandable;
	ArrayList<Question> questionList;
	ArrayList<Answer> answerList;

	public Branches() {
		questionList = new ArrayList<Question>();
		answerList = new ArrayList<Answer>();
	}
}
