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
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SigninActivity extends AppCompatActivity {

    private EditText emailEdit, pwEdit, repwEdit;
    private Button  signinbtn;
    private FirebaseAuth mAuth;
    private MaterialButton backlogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        mAuth = FirebaseAuth.getInstance();

        emailEdit = findViewById(R.id.email);
        pwEdit = findViewById(R.id.password);
        repwEdit = findViewById(R.id.repassword);
        signinbtn = findViewById(R.id.signbtn);

        // chuyen login sang signin
        signinbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register();
            }
        });
        backlogin=(MaterialButton) findViewById(R.id.signinbackbtn);
        backlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SigninActivity.this,LoginActivity.class);
                startActivity(intent);
            }

        });

    }


    private void register() {
        String email, pw, repw;
        email = emailEdit.getText().toString();
        pw = pwEdit.getText().toString();
        repw = repwEdit.getText().toString();

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
        if (pw.length()<8) {
            Toast.makeText(this , "Password có ít nhất 8 ký tự",Toast.LENGTH_SHORT).show();
            return;
        }

        //Nếu re-password trống
        if(TextUtils.isEmpty(repw)){
            Toast.makeText(this , "Vui lòng nhập Re-password",Toast.LENGTH_SHORT).show();
            return;
        }

        //Nếu re-password và re-password k giống nhau
        if (!pw.equals(repw)) {
            Toast.makeText(this , "Password và Re-password phải giống nhau",Toast.LENGTH_SHORT).show();
            return;
        }

        mAuth.createUserWithEmailAndPassword(email, pw).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful() ){
                    Toast.makeText(getApplicationContext() , "Taọ tài khoản thành công",Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(SigninActivity.this, LoginActivity.class);
                    startActivity(i);
                }
                else{
                    Toast.makeText(getApplicationContext() , "Taọ tài khoản không thành công",Toast.LENGTH_SHORT).show();

                }
            }
        });


    }


}
