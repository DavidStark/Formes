package com.of.formes;

import java.util.ArrayList;

public class Survey {
	String surveyName;
	ArrayList<Sections> sectionList;

	public Survey(String surveyName, ArrayList<Sections> sectionList) {

		this.surveyName = surveyName;
		this.sectionList = sectionList;
	}

}
