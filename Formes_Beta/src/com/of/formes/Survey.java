package com.of.formes;

import java.util.ArrayList;
import java.util.Iterator;

public class Survey {
	String surveyName;
	ArrayList<Sections> sectionList;

	public Survey(String surveyName, ArrayList<Sections> sectionList) {

		this.surveyName = surveyName;
		this.sectionList = sectionList;
	}

	public String[] GetSectionNames() {

		int size = sectionList.size();
		String[] sectionName = new String[size];

		for (int i = 0; i < size; i++) {
			sectionName[i] = sectionList.get(i).name;
		}

		return sectionName;

	}

	public CharSequence getSurveyName() {
		return surveyName;
	}

	public String getSectionName(int i) {
		
		return sectionList.get(i).name;
	}
}
