package edu.poly.test1;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    private EditText emailEdit, pwEdit;
    private Button loginbtn, signinbtn;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();

        emailEdit = findViewById(R.id.email);
        pwEdit = findViewById(R.id.password);
        loginbtn = findViewById(R.id.loginbtn);
        signinbtn = findViewById(R.id.signbtn);

        // chuyen login sang signin
        signinbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               register();
            }
        });
        // chuyen activity login sang main
        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
    }

        private void login() {
            // 2 biến lưu email và password
            String email, pw;
            email = emailEdit.getText().toString();
            pw = pwEdit.getText().toString();
            //Nếu email trống và k hợp lệ
            if(TextUtils.isEmpty(email)){
                Toast.makeText(this , "Vui lòng nhập email",Toast.LENGTH_SHORT).show();
                return;
            }else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                Toast.makeText(this , "Email không hợp lệ",Toast.LENGTH_SHORT).show();
                return;
            }

            //Nếu password trống
            if(TextUtils.isEmpty(pw)){
                Toast.makeText(this , "Vui lòng nhập password",Toast.LENGTH_SHORT).show();
                return;
            }

            mAuth.signInWithEmailAndPassword(email, pw).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    //Nếu đăng nhập không thành công
                    if(task.isSuccessful()){
                        Toast.makeText(getApplicationContext() , "Đăng nhập thành công",Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(i);
                    }
                    else{
                        Toast.makeText(getApplicationContext() , "Tài khoản hoặc mật khẩu không chính xác",Toast.LENGTH_SHORT).show();

                    }


                }
            });
        }

    private void register() {
        Intent intent=new Intent(LoginActivity.this,SigninActivity.class);
        startActivity(intent);
    }
}


