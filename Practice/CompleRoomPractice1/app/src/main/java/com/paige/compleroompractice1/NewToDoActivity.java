package com.paige.compleroompractice1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class NewToDoActivity extends AppCompatActivity {

    public static final String EXTRA_REPLY = "com.android.reply";
    private EditText editText;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_to_do_activitiy);

        editText = findViewById(R.id.edit_text);
        button = findViewById(R.id.btnInsert);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent replyIntent = new Intent();
                if(TextUtils.isEmpty(editText.getText().toString())){
                    setResult(RESULT_CANCELED, replyIntent);
                } else {
                    String todoString = editText.getText().toString();
                    replyIntent.putExtra(EXTRA_REPLY, todoString); //이렇게 하면 onActivityResult에 date 라는 parameter로 넘어감
                    setResult(RESULT_OK, replyIntent);
                }
                finish();
            }
        });

    }
}
