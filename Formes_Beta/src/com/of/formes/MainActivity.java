/*
 * Copyright 2013 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.of.formes;

import java.io.IOException;
import java.io.InputStream;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.SearchManager;
import android.content.Intent;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import com.example.android.navigationdrawerexample.R;

@SuppressLint("ValidFragment")
public class MainActivity extends Activity {
	private DrawerLayout mDrawerLayout;
	private ListView mDrawerList;
	private ActionBarDrawerToggle mDrawerToggle;

	private CharSequence mDrawerTitle;
	private CharSequence mTitle;
	private String[] mSidePanelList;
	private Survey survey = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// Reading JSON file
		AssetManager assetManager = getAssets();
		try {
			InputStream is = assetManager.open("dummy.json");
			ReadJSON readJSON = new ReadJSON(is);
			survey = readJSON.getSurveyObject();
		} catch (IOException e) {

			e.printStackTrace();
		}

		/*
		 * Checking if we have read JSON file successfully If File read was
		 * success Assigning Parameters
		 */
		if (survey != null) {
			setContentView(R.layout.parent_fragment);
			mSidePanelList = survey.GetSectionNames();
			setTitle(survey.getSurveyName());
			mTitle = mDrawerTitle = getTitle();
			mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
			mDrawerList = (ListView) findViewById(R.id.left_drawer);

			// set a custom shadow that overlays the main content when the
			// drawer opens
			mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow,
					GravityCompat.START);

			// set up the drawer's list view with items and click listener
			mDrawerList.setAdapter(new ArrayAdapter<String>(this,
					R.layout.drawer_list_item, mSidePanelList));
			mDrawerList.setOnItemClickListener(new DrawerItemClickListener());
		}

		// mSidePanelList =
		// getResources().getStringArray(R.array.planets_array);

		// enable ActionBar app icon to behave as action to toggle nav drawer
		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setHomeButtonEnabled(true);

		// ActionBarDrawerToggle ties together the the proper interactions
		// between the sliding drawer and the action bar app icon
		mDrawerToggle = new ActionBarDrawerToggle(this, /* host Activity */
		mDrawerLayout, /* DrawerLayout object */
		R.drawable.ic_drawer, /* nav drawer image to replace 'Up' caret */
		R.string.drawer_open, /* "open drawer" description for accessibility */
		R.string.drawer_close /* "close drawer" description for accessibility */
		) {
			public void onDrawerClosed(View view) {
				getActionBar().setTitle(mTitle);
				invalidateOptionsMenu(); // creates call to
											// onPrepareOptionsMenu()
			}

			public void onDrawerOpened(View drawerView) {
				getActionBar().setTitle(mDrawerTitle);
				invalidateOptionsMenu(); // creates call to
											// onPrepareOptionsMenu()
			}
		};
		mDrawerLayout.setDrawerListener(mDrawerToggle);

		if (savedInstanceState == null) {
			selectItem(0);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.main, menu);
		return super.onCreateOptionsMenu(menu);
	}

	/* Called whenever we call invalidateOptionsMenu() */
	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		// If the nav drawer is open, hide action items related to the content
		// view
		boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
		menu.findItem(R.id.action_websearch).setVisible(!drawerOpen);
		return super.onPrepareOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// The action bar home/up action should open or close the drawer.
		// ActionBarDrawerToggle will take care of this.
		if (mDrawerToggle.onOptionsItemSelected(item)) {
			return true;
		}
		// Handle action buttons
		switch (item.getItemId()) {
		case R.id.action_websearch:
			// create intent to perform web search for this planet
			Intent intent = new Intent(Intent.ACTION_WEB_SEARCH);
			intent.putExtra(SearchManager.QUERY, getActionBar().getTitle());
			// catch event that there's no activity to handle intent
			if (intent.resolveActivity(getPackageManager()) != null) {
				startActivity(intent);
			} else {
				Toast.makeText(this, R.string.app_not_available,
						Toast.LENGTH_LONG).show();
			}
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	/* The click listner for ListView in the navigation drawer */
	private class DrawerItemClickListener implements
			ListView.OnItemClickListener {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			selectItem(position);
		}
	}

	private void selectItem(int position) {
		// update the main content by replacing fragments
		Fragment fragment = new SectionFragment();
		Bundle args = new Bundle();
		args.putInt(SectionFragment.ARG_SECTION_NUMBER, position);
		fragment.setArguments(args);

		FragmentManager fragmentManager = getFragmentManager();

		fragmentManager.beginTransaction()
				.replace(R.id.content_frame, fragment).commit();

		// update selected item and title, then close the drawer
		mDrawerList.setItemChecked(position, true);
		setTitle(mSidePanelList[position]);
		mDrawerLayout.closeDrawer(mDrawerList);
	}

	@Override
	public void setTitle(CharSequence title) {
		mTitle = title;
		getActionBar().setTitle(mTitle);
	}

	/**
	 * When using the ActionBarDrawerToggle, you must call it during
	 * onPostCreate() and onConfigurationChanged()...
	 */

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		// Sync the toggle state after onRestoreInstanceState has occurred.
		mDrawerToggle.syncState();
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		// Pass any configuration change to the drawer toggls
		mDrawerToggle.onConfigurationChanged(newConfig);
	}

	/**
	 * Fragment that appears in the "content_frame", shows a planet
	 */
	public class SectionFragment extends Fragment {
		public static final String ARG_SECTION_NUMBER = "planet_number";

		public SectionFragment() {
			// Empty constructor required for fragment subclasses
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View view = inflater.inflate(R.layout.fragment_basic, container,
					false);

			// now you must initialize your list view
			ListView listview = (ListView) view.findViewById(R.id.baseListView);

			// EDITED Code
			// String[] items = new String[] { "Item 1", "Item 2", "Item 3" };

			/*
			 * Weather weather_data[] = new Weather[] { new
			 * Weather(R.drawable.weather_cloudy, "Cloudy"), new
			 * Weather(R.drawable.weather_showers, "Showers"), new
			 * Weather(R.drawable.weather_snow, "Snow"), new
			 * Weather(R.drawable.weather_storm, "Storm"), new
			 * Weather(R.drawable.weather_sunny, "Sunny") };
			 */
			int sectionSelected = getArguments().getInt(ARG_SECTION_NUMBER);
			Sections selectedSection = survey.sectionList.get(sectionSelected);
			Branches selectedBranch = selectedSection.branchList.get(0);
			int size = selectedBranch.getNoOfQuestions();
			RowLayoutUI rowLayout[] = new RowLayoutUI[size];

			for (int i = 0; i < size; i++) {
				rowLayout[i] = new RowLayoutUI(
						selectedBranch.answerList.get(i),
						selectedBranch.questionList.get(i),
						selectedBranch.answerList.get(i).type);
			}

			/*
			 * RowLayoutUI rowLayout[] = new RowLayoutUI[] { new
			 * RowLayoutUI("a", "first question", "SmallFree"), new
			 * RowLayoutUI("b", "second question", "SmallFree"), new
			 * RowLayoutUI("b", "third question", "SmallFree") };
			 */
			// ArrayAdapter<String> adapter = new ArrayAdapter<String>(
			// getActivity(), android.R.layout.simple_list_item_1, items);

			RowLayoutUIAdapter adapter = new RowLayoutUIAdapter(getActivity(),
					R.layout.fragment_row, rowLayout);

			listview.setAdapter(adapter);

			// To have custom list view use this : you must define
			// CustomeAdapter class
			// listview.setadapter(new CustomeAdapter(getActivity()));
			// getActivty is used instead of Context
			return view;

		}

	}
}