package com.queen.calendar.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.queen.calendar.R;

import org.w3c.dom.Text;

import java.security.PrivateKey;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = LoginActivity.class.getSimpleName();

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    private TextView mTextview, mEmail, mPswrd;
    private Button mBtn;
    private ProgressBar mProgess;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mProgess = (ProgressBar) findViewById(R.id.progressBar);
        mEmail = (TextView) findViewById(R.id.email);
        mPswrd = (TextView) findViewById(R.id.password);
        mTextview =(TextView) findViewById(R.id.log2);
        mBtn = (Button) findViewById(R.id.btnLogin);
        mAuth = FirebaseAuth.getInstance();

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    Intent intent = new Intent(LoginActivity.this, DashboardActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    finish();
                }
            }
        };

        mTextview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        mBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, DashboardActivity.class);
                startActivity(intent);
                loginWithPassword();
//                showProgressBar();

            }
        });

    }

    //ProgressBar
//    private void showProgressBar(){
//        mProgess.setVisibility(View.VISIBLE);
//    }
//    private void hideProgressBar() {
//        mProgess.setVisibility(View.GONE);
//    }

    //User Login
    private void loginWithPassword() {
        String email = mEmail.getText().toString().trim();
        String password = mPswrd.getText().toString().trim();

        if (email.equals("")){
            mEmail.setError("Please Fill");
            return;
        }
        if (password.equals("")) {
            mPswrd.setError("Enter password");
            return;
        }else {
            mPswrd.setError("Incorrect password");
        }

        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
//                hideProgressBar();
            }
        });

        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                Log.d(TAG, "SignInWithEmail: onComplete" + task.isSuccessful());
                if (!task.isSuccessful()){
                    Log.d(TAG, "SignInWithEmail", task.getException());
                    Toast.makeText(LoginActivity.this, "Authentication Failde", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }


}