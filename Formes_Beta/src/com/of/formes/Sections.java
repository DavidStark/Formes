package com.of.formes;

import java.util.ArrayList;

public class Sections {
	int sectionId;
	String name;
	ArrayList<Branches> branchList;

	public Sections(int sectionId, String name,
			ArrayList<Branches> branchList) {
		super();
		this.sectionId = sectionId;
		this.name = name;
		this.branchList = branchList;
	}

}
