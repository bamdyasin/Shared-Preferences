package com.earningaide.sharedpreference;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private TextView result;
    private EditText userName, userEmail;
    private Button saveBtn, loadBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        result = findViewById(R.id.textView);
        userName = findViewById(R.id.edName);
        userEmail = findViewById(R.id.edEmail);
        saveBtn = findViewById(R.id.btnSave);
        loadBtn = findViewById(R.id.btnLoad);


        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = userName.getText().toString();
                String email = userEmail.getText().toString();
                if (name.equals("") && email.equals("")) {
                    Toast.makeText(MainActivity.this,"No Data Found",Toast.LENGTH_SHORT).show();
                } else {
                    //data store -----------------------
                    SharedPreferences sPref = getSharedPreferences("userInput", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sPref.edit();
                    editor.putString("userKey",name);
                    editor.putString("mailKey",email);
                    editor.commit();
                    userName.setText("");
                    userEmail.setText("");
                    Toast.makeText(MainActivity.this,"Submit Success",Toast.LENGTH_SHORT).show();
                }
            }
        });



        loadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sPref = getSharedPreferences("userInput", Context.MODE_PRIVATE);
                if (sPref.contains("userKey") && sPref.contains("mailKey")){
                    String spUserName = sPref.getString("userKey","0");
                    String spUserEmail = sPref.getString("mailKey","0");
                    result.setText(spUserName+"\n"+spUserEmail);

                 } else {
                    Toast.makeText(MainActivity.this,"Data Not set",Toast.LENGTH_SHORT).show();
                }

            }
        });









    }
}