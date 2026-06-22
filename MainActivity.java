package com.example.remasalomari;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText username, password, repassword;
    Button btnsignup, btnsignin;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = findViewById(R.id.name);
        password = findViewById(R.id.pass);
        repassword = findViewById(R.id.repass);
        btnsignup = findViewById(R.id.btnsign1);
        btnsignin = findViewById(R.id.btnsign2);

        DB = new DBHelper(this);

        btnsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String user = username.getText().toString();
                String pass = password.getText().toString();
                String repass = repassword.getText().toString();

                if (user.isEmpty() || pass.isEmpty() || repass.isEmpty()) {
                    Toast.makeText(MainActivity.this, "الرجاء إدخال جميع الحقول", Toast.LENGTH_SHORT).show();
                } else {
                    if (pass.equals(repass)) {
                        Boolean checkuser = DB.checkusername(user);
                        if (!checkuser) {
                            Boolean insert = DB.insertData(user, pass);
                            if (insert) {
                                Toast.makeText(MainActivity.this, "تم التسجيل بنجاح", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                                startActivity(intent);
                            } else {
                                Toast.makeText(MainActivity.this, "حدث خطأ أثناء التسجيل", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(MainActivity.this, "اسم المستخدم موجود مسبقًا", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(MainActivity.this, "كلمة المرور غير متطابقة", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        btnsignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}
