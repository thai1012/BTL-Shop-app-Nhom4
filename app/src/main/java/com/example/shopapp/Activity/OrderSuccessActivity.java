package com.example.shopapp.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

import com.example.shopapp.R;

public class OrderSuccessActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_success);

        Button btnHome = findViewById(R.id.btnHome);
        btnHome.setOnClickListener(v -> {
            // Trở về màn hình MainActivity
            finish();
        });
    }
}