package com.example.firebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.firebase.databinding.ActivityRegisterBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegisterActivity extends AppCompatActivity {

    ActivityRegisterBinding binding;
    FirebaseAuth mAuth;
    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow();
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }

        mAuth = FirebaseAuth.getInstance();

        binding.signinText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        binding.registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!binding.edtName.getText().toString().isEmpty() && !binding.edtEmail.getText().toString().isEmpty() && !binding.edtPassword.getText().toString().isEmpty()) {
                    if (binding.edtPassword.getText().toString().length() >= 6) {
                        dialog = new ProgressDialog(RegisterActivity.this);
                        dialog.setTitle("Please Wait");
                        dialog.setMessage("Registering User...");
                        dialog.setCancelable(false);
                        dialog.show();
                        registerUser();
                    } else {
                        binding.edtPassword.setError("Enter 6 or more Characters");
                    }
                } else {
                    if (binding.edtName.getText().toString().isEmpty()){
                        binding.edtName.setError("Enter Name");
                    }
                    if (binding.edtEmail.getText().toString().isEmpty()){
                        binding.edtEmail.setError("Enter Email");
                    }
                    if (binding.edtPassword.getText().toString().isEmpty()){
                        binding.edtPassword.setError("Enter Password");
                    }
                }
            }
        });
    }

    public void registerUser(){
        mAuth.createUserWithEmailAndPassword(binding.edtEmail.getText().toString(), binding.edtPassword.getText().toString())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            dialog.dismiss();
                            StartActivity.editor.putString("method", "email");
                            StartActivity.editor.commit();
                            Toast.makeText(RegisterActivity.this, "User Registered", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(RegisterActivity.this, MainActivity.class));
                            SignInActivity.activity.finish();
                            StartActivity.activity.finish();
                            finish();
                        } else {
                            dialog.dismiss();
                            Toast.makeText(RegisterActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

}