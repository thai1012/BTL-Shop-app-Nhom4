package com.example.shopapp.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;                       // <-- thêm import này
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.shopapp.Adapter.CartAdapter;
import com.example.shopapp.Helper.ChangeNumberItemsListener;
import com.example.shopapp.Helper.ManagmentCart;
import com.example.shopapp.R;
import com.example.shopapp.databinding.ActivityCartBinding;

public class CartActivity extends AppCompatActivity {
    private ActivityCartBinding binding;
    private ManagmentCart managmentCart;
    private double tax;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Edge-to-edge UI
        EdgeToEdge.enable(this);

        // Inflate layout via ViewBinding
        binding = ActivityCartBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Setup management and UI
        managmentCart = new ManagmentCart(this);
        setupBackButton();
        setupCheckoutButton();
        initCartList();
        calculateCart();
    }

    private void setupBackButton() {
        binding.backBtn.setOnClickListener(v -> finish());
    }

    private void setupCheckoutButton() {
        binding.btnCheckout.setOnClickListener(v -> {
            // Launch success screen
            startActivity(new Intent(CartActivity.this, OrderSuccessActivity.class));
            finish();
        });
    }

    private void initCartList() {
        if (managmentCart.getListCart().isEmpty()) {
            binding.emptyTxt.setVisibility(View.VISIBLE);
            binding.scrollView2.setVisibility(View.GONE);
        } else {
            binding.emptyTxt.setVisibility(View.GONE);
            binding.scrollView2.setVisibility(View.VISIBLE);
        }
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

    private void calculateCart() {
        double percentTax = 0.02;
        double delivery = 10;

        double subtotal = Math.round(managmentCart.getTotalFee() * 100.0) / 100.0;
        tax = Math.round(subtotal * percentTax * 100.0) / 100.0;
        double total = Math.round((subtotal + tax + delivery) * 100.0) / 100.0;

        binding.totalFeeTxt.setText("$" + subtotal);
        binding.taxTxt     .setText("$" + tax);
        binding.deliveryTxt.setText("$" + delivery);
        binding.totalTxt   .setText("$" + total);
    }
}