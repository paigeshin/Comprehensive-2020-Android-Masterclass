package com.paige.completeroompractice2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddNewItem extends AppCompatActivity {

    public static final String NEW_ITEM_NAME = "com.package.item.name";
    public static final String NEW_ITEM_PRICE = "com.package.item.price";

    private EditText etName, etPrice;
    private Button btnConfirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_item);
        bindView();
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean etNameEmpty = TextUtils.isEmpty(etName.getText().toString());
                boolean etPriceEmpty = TextUtils.isEmpty(etPrice.getText().toString());

                if(!etNameEmpty && !etPriceEmpty){
                    Intent intent = new Intent();
                    intent.putExtra(NEW_ITEM_NAME, etName.getText().toString());
                    intent.putExtra(NEW_ITEM_PRICE, Long.parseLong(etPrice.getText().toString()));
                    setResult(RESULT_OK, intent);
                } else {
                    setResult(RESULT_CANCELED);
                }

                finish();

            }
        });
    }

    private void bindView(){
        etName = findViewById(R.id.etProductName);
        etPrice = findViewById(R.id.etProductPrice);
        btnConfirm = findViewById(R.id.btnConfirm);
    }
}
