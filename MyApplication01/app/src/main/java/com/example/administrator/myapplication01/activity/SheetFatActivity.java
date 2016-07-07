package com.example.administrator.myapplication01.activity;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.example.administrator.myapplication01.R;
import com.example.administrator.myapplication01.view.Fab;
import com.gordonwong.materialsheetfab.MaterialSheetFab;
import com.gordonwong.materialsheetfab.MaterialSheetFabEventListener;

public class SheetFatActivity extends AppCompatActivity implements View.OnClickListener{
    private MaterialSheetFab materialSheetFab;
    private int statusBarColor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sheet_fat);
        initView();
    }

    private void initView() {
        Fab fab = (Fab) findViewById(R.id.fab);  //圆形按钮

        View sheetView = findViewById(R.id.fab_sheet);
        View overlay = findViewById(R.id.overlay);
        int sheetColor = getResources().getColor(R.color.background_card);
        int fabColor = getResources().getColor(R.color.theme_accent);
        materialSheetFab = new MaterialSheetFab<>(fab, sheetView, overlay, sheetColor, fabColor);
        materialSheetFab.setEventListener(new MaterialSheetFabEventListener() {
            @Override
            public void onShowSheet() {
                // Save current status bar color
                statusBarColor = getStatusBarColor();
                // Set darker status bar color to match the dim overlay
                setStatusBarColor(getResources().getColor(R.color.theme_primary_dark2));
            }

            @Override
            public void onHideSheet() {
                // Restore status bar color
                setStatusBarColor(statusBarColor);
            }
        });

        findViewById(R.id.fab_sheet_item_recording).setOnClickListener(this);
        findViewById(R.id.fab_sheet_item_reminder).setOnClickListener(this);
        findViewById(R.id.fab_sheet_item_photo).setOnClickListener(this);
        findViewById(R.id.fab_sheet_item_note).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Toast.makeText(this, "sheet_item_pressed", Toast.LENGTH_SHORT).show();
        materialSheetFab.hideSheet();
    }

    @Override
    public void onBackPressed() {
        if (materialSheetFab.isSheetVisible()) {
            materialSheetFab.hideSheet();
        } else {
            super.onBackPressed();
        }
    }

    private int getStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            return getWindow().getStatusBarColor();
        }
        return 0;
    }
    private void setStatusBarColor(int color) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(color);
        }
    }
}
