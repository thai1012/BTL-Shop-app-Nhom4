package com.example.shopapp.Activity;

import android.os.Bundle;
import android.widget.ImageButton;       // thêm import này
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.example.shopapp.R;

public class ProfileActivity extends AppCompatActivity {

    ImageButton btnBack;               // khai báo nút back
    ImageView imgAvatar;
    TextView tvFullName, tvDob, tvAddress, tvEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        // ánh xạ view
        btnBack    = findViewById(R.id.btnBack);
        imgAvatar  = findViewById(R.id.imgAvatar);
        tvFullName = findViewById(R.id.tvFullName);
        tvDob      = findViewById(R.id.tvDob);
        tvAddress  = findViewById(R.id.tvAddress);
        tvEmail    = findViewById(R.id.tvEmail);

        // gán dữ liệu mẫu
        tvFullName.setText("Trần Duy Hưng");
        tvDob     .setText("12/07/2003");
        tvAddress .setText("Trương Định, Giáp Bát, Hoàng Mai, Hà Nội");
        tvEmail   .setText("mariszayne2k3@gmail.com");

        // xử lý click quay lại
        btnBack.setOnClickListener(v -> finish());
    }
}
