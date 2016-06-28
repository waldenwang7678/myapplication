package com.example.administrator.myapplication01.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.administrator.myapplication01.R;

public class SearchActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_search);
		
	}
	
	public void SearchShow(View view){
		Intent intent = new Intent(SearchActivity.this,SearchShowActivity.class);
		intent.putExtra("SearchName", "犬");
		startActivity(intent);
	}

}
