package com.of.formes;

import java.util.List;

import com.example.android.navigationdrawerexample.R;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class RowLayoutUIAdapter extends ArrayAdapter<RowLayoutUI> {

	Context context;
	int layoutResourceId;
	RowLayoutUI data[] = null;

	public RowLayoutUIAdapter(Context context, int layoutResourceId,
			RowLayoutUI data[]) {
		super(context, layoutResourceId, data);
		this.layoutResourceId = layoutResourceId;
		this.context = context;
		this.data = data;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.widget.ArrayAdapter#getView(int, android.view.View,
	 * android.view.ViewGroup)
	 */
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		View row = convertView;
		RowHolder holder = null;

		if (row == null) {
			LayoutInflater inflater = ((Activity) context).getLayoutInflater();
			row = inflater.inflate(layoutResourceId, parent, false);

			holder = new RowHolder();
			// holder.imgIcon = (ImageView) row.findViewById(R.id.imgIcon);
			// holder.txtTitle = (TextView) row.findViewById(R.id.txtTitle);
			holder.answerDatePckr = (DatePicker) row
					.findViewById(R.id.ansDatePckr);
			holder.answerSpinner = (Spinner) row
					.findViewById(R.id.ansOptnsSpinner);
			holder.answerTxt = (EditText) row.findViewById(R.id.ansEditTxt);
			holder.questionTxt = (TextView) row.findViewById(R.id.questionText);

			row.setTag(holder);
		} else {
			holder = (RowHolder) row.getTag();
		}

		RowLayoutUI layoutUI = data[position];
		holder.questionTxt.setText(layoutUI.questionText);
//		Weather weather = data[position];
//		holder.txtTitle.setText(weather.title);
//		holder.imgIcon.setImageResource(weather.icon);

		return row;
		
	}

	static class RowHolder {
		
		TextView questionTxt;
		Spinner answerSpinner;
		EditText answerTxt;
		DatePicker answerDatePckr;
	}

}
