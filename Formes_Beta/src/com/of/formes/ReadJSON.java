package com.of.formes;

import java.io.InputStream;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.SparseArray;

public class ReadJSON {

	// JSON Node names
	private static final String TAG_SURVEY_NAME = "surveyName";
	private static final String TAG_SECTIONS = "sections";
	private static final String TAG_SECTION_NAME = "name";
	private static final String TAG_S_ID = "id";
	private static final String TAG_BRANCHES = "branches";
	private static final String TAG_EXPANDABLE = "expandable";
	private static final String TAG_QUESTIONS = "questions";
	private static final String TAG_Q_TEXT = "text";
	private static final String TAG_A_TYPE = "type";
	private static final String TAG_Q_ID = "id";
	private static final String TAG_READONLY = "readOnly";
	private static final String TAG_DEFAULT = "default";
	private static final String TAG_MIN_LENGTH = "minLength";
	private static final String TAG_MAX_LENGTH = "maxLength";
	private static final String TAG_ANS_CHOICE = "choices";
	private Survey surveyObject = null;

	public ReadJSON(InputStream is) {

		// survey Object
		String surveyName = null;
		JSONArray sections = null;

		// Creating JSON Parser instance
		JSONParser jParser = new JSONParser();

		// getting JSON string from URL
		JSONObject jsonSurvey = jParser.getJSONFromFile(is);
		ArrayList<Question> questionList = new ArrayList<Question>();
		ArrayList<Answer> answerList = new ArrayList<Answer>();
		ArrayList<Branches> branchList = new ArrayList<Branches>();
		ArrayList<Sections> sectionList = new ArrayList<Sections>();
		try {
			surveyName = getString(jsonSurvey, TAG_SURVEY_NAME);
			sections = jsonSurvey.getJSONArray(TAG_SECTIONS);

			// Looping through Sections array
			for (int i = 0; i < sections.length(); i++) {
				JSONObject section = sections.getJSONObject(i);

				int sectionId = getInt(section, TAG_S_ID);
				String sectionName = getString(section, TAG_SECTION_NAME);
				JSONArray branches = section.getJSONArray(TAG_BRANCHES);

				for (int j = 0; j < branches.length(); j++) {
					JSONObject branch = branches.getJSONObject(j);
					Boolean isExpandable = getBoolean(branch, TAG_EXPANDABLE);

					JSONArray questions = branch.getJSONArray(TAG_QUESTIONS);
					SparseArray<String> answerChoicesMap = new SparseArray<String>();
					for (int k = 0; k < questions.length(); k++) {
						JSONObject question = questions.getJSONObject(k);
						int questionId = getInt(question, TAG_Q_ID);
						String questionText = getString(question, TAG_Q_TEXT);
						String answerType = getString(question, TAG_A_TYPE);
						Boolean readOnly = getBoolean(question, TAG_READONLY);
						String defaultValue = getString(question, TAG_DEFAULT);
						int minLength = getInt(question, TAG_MIN_LENGTH);
						int maxLength = getInt(question, TAG_MAX_LENGTH);
					
						try {
							JSONArray answerChoices = question
									.getJSONArray(TAG_ANS_CHOICE);
							for (int x = 0; x < answerChoices.length(); x++) {
								answerChoicesMap.put(x, answerChoices.get(x)
										.toString());
							}
						} catch (JSONException e) {
							e.printStackTrace();
						}

						// Creating question and answer object to store in
						// Array list
						Question simpleQuestionObj = new Question(questionId,
								questionText);
						Answer simpleAnswerObj = new Answer(minLength,
								maxLength, answerType, defaultValue, readOnly,
								questionId, answerChoicesMap);
						questionList.add(simpleQuestionObj);
						answerList.add(simpleAnswerObj);
					}
					Branches simpleBranchesObj = new Branches(isExpandable,
							questionList, answerList);
					branchList.add(simpleBranchesObj);
					questionList.clear();
					answerList.clear();
				}

				Sections simpleSectionObj = new Sections(sectionId,
						sectionName, branchList);
				sectionList.add(simpleSectionObj);
				branchList.clear();

			}
			setSurveyObject(new Survey(surveyName, sectionList));

		} catch (JSONException e) {
			e.printStackTrace();
		}

	}

	private int getInt(JSONObject object, String tag) {
		try {
			return object.getInt(tag);
		} catch (JSONException e) {
			return -1;
		}
	}

	private String getString(JSONObject object, String tag) {
		try {
			return object.getString(tag);
		} catch (JSONException e) {
			return null;
		}
	}

	private Boolean getBoolean(JSONObject object, String tag) {
		try {
			return object.getBoolean(tag);
		} catch (JSONException e) {
			return null;
		}

	}

	public Survey getSurveyObject() {
		return surveyObject;
	}

	public void setSurveyObject(Survey surveyObject) {
		this.surveyObject = surveyObject;
	}
}
