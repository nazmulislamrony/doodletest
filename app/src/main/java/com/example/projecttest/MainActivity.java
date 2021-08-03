package com.example.projecttest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    
    Button add_category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        add_category = findViewById(R.id.add_category_btn);
        add_category.setOnClickListener((View v) -> {
            startActivity(new Intent(this,ShowCategoryActivity.class));
        });
    }
}