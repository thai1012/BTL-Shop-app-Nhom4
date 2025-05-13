package com.example.shopapp.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.shopapp.Adapter.CartAdapter;
import com.example.shopapp.Helper.ChangeNumberItemsListener;
import com.example.shopapp.Helper.ManagmentCart;
import com.example.shopapp.databinding.ActivityCartBinding;

public class CartActivity extends AppCompatActivity {
    private ActivityCartBinding binding;
    private ManagmentCart managmentCart;
    private double tax;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        binding = ActivityCartBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        managmentCart = new ManagmentCart(this);

        setupButtons();
        initCartList();
        calculateCart();
    }

    private void setupButtons() {
        // Nút back
        binding.backBtn.setOnClickListener(v -> finish());

        // Nút checkout
        binding.btnCheckout.setOnClickListener(v -> {
            startActivity(new Intent(CartActivity.this, OrderSuccessActivity.class));
            finish();
        });

        // Nút "Back to Home" khi giỏ hàng rỗng
        binding.backToHomeBtn.setOnClickListener(v -> {
            Intent intent = new Intent(CartActivity.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        });
    }

    private void initCartList() {
        if (managmentCart.getListCart().isEmpty()) {
            // Hiển thị giao diện trống
            binding.emptyLayout.setVisibility(View.VISIBLE);
            binding.scrollView2.setVisibility(View.GONE);
        } else {
            // Hiển thị danh sách giỏ hàng
            binding.emptyLayout.setVisibility(View.GONE);
            binding.scrollView2.setVisibility(View.VISIBLE);

            binding.cartView.setLayoutManager(
                    new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
            );
            binding.cartView.setAdapter(
                    new CartAdapter(
                            managmentCart.getListCart(),
                            this,
                            new ChangeNumberItemsListener() {
                                @Override
                                public void changed() {
                                    calculateCart();
                                }
                            }
                    )
            );
        }
    }

    private void calculateCart() {
        double percentTax = 0.02;
        double delivery = 10;

        double subtotal = Math.round(managmentCart.getTotalFee() * 100.0) / 100.0;
        tax = Math.round(subtotal * percentTax * 100.0) / 100.0;
        double total = Math.round((subtotal + tax + delivery) * 100.0) / 100.0;

        binding.totalFeeTxt.setText("$" + subtotal);
        binding.taxTxt.setText("$" + tax);
        binding.deliveryTxt.setText("$" + delivery);
        binding.totalTxt.setText("$" + total);
    }
}
