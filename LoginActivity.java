package com.example.remasalomari;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText username, password;
    Button btnlogin;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = findViewById(R.id.login_user);
        password = findViewById(R.id.login_pass);
        btnlogin = findViewById(R.id.btn_login2);
        DB = new DBHelper(this);

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String user = username.getText().toString();
                String pass = password.getText().toString();

                if (user.isEmpty() || pass.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "الرجاء إدخال جميع الحقول", Toast.LENGTH_SHORT).show();
                } else {
                    Boolean checkuserpass = DB.checkusernamepassword(user, pass);
                    if (checkuserpass) {
                        Toast.makeText(LoginActivity.this, "تم تسجيل الدخول بنجاح", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), WelcomActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(LoginActivity.this, "بيانات الدخول غير صحيحة", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}
