package com.example.practical.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.practical.MainActivity;
import com.example.practical.R;
import com.example.practical.entity.Product;
import com.example.practical.repository.AppDatabase;

public class AddProductActivity extends AppCompatActivity {
    LoadProductActivity loadProductActivity;
    EditText etName,etQuantity;
    Button btnAdd,btnView;

    private AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        int SDK_INT = android.os.Build.VERSION.SDK_INT;
        if (SDK_INT > 8)
        {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                    .permitAll().build();
            StrictMode.setThreadPolicy(policy);
            //your codes here

        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_product_activity);
        initView();
        initListener();

    }
    private void initListener() {
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String productName = etName.getText().toString();
                String productQuantityBeforeConvert = etQuantity.getText().toString();

                if (productName.equals("")) {
                    Toast toast = Toast.makeText(getApplicationContext(), "Need to provide product name", Toast.LENGTH_LONG);
                    toast.show();
                    return;
                }

                if (!isInteger(productQuantityBeforeConvert)) {
                    Toast toast = Toast.makeText(getApplicationContext(), "Quantity must be whole number and cannot be null", Toast.LENGTH_LONG);
                    toast.show();
                    return;
                }



                int productQuantity = Integer.parseInt(productQuantityBeforeConvert);

                Product product = new Product();
                product.setName(productName);
                product.setQuantity(productQuantity);
                db.productDao().insertProduct(product);

                Toast toast = Toast.makeText(getApplicationContext(), "Add product successfully", Toast.LENGTH_LONG);
                toast.show();
            }
        });

        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddProductActivity.this, LoadProductActivity.class);
                AddProductActivity.this.startActivity(intent);
            }
        });
    }

    private void initView() {
        etName = (EditText) findViewById(R.id.name);
        etQuantity = (EditText) findViewById(R.id.quantity);
        btnAdd = (Button) findViewById(R.id.button_add);
        btnView = (Button) findViewById(R.id.button_view);
        db = AppDatabase.getAppDatabase(this);
    }

    public static boolean isInteger(String str) {
        if (str == null) {
            return false;
        }
        int length = str.length();
        if (length == 0) {
            return false;
        }
        int i = 0;
        if (str.charAt(0) == '-') {
            if (length == 1) {
                return false;
            }
            i = 1;
        }
        for (; i < length; i++) {
            char c = str.charAt(i);
            if (c < '0' || c > '9') {
                return false;
            }
        }
        return true;
    }


}
