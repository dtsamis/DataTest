package com.example.android.datatest;
import android.support.annotation.NonNull;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.app.ProgressDialog;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.firebase.client.Firebase;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

private EditText email;
private EditText password;
private Button btnSignIn;
private Button btnSignUp;

//Firebase..

private FirebaseAuth mAuth;
private ProgressDialog mDialog;


@Override
protected void onCreate(Bundle savedInstanceState) {
super.onCreate(savedInstanceState);
setContentView(R.layout.activity_main);
//FirebaseApp.initializeApp(getApplicationContext());
mAuth=FirebaseAuth.getInstance();

if (mAuth.getCurrentUser()!=null){
    startActivity(new Intent(getApplicationContext(),DataButtons.class));
}
btnSignIn=findViewById(R.id.btnsignin);
btnSignUp=findViewById(R.id.btnsignup);
email=findViewById(R.id.email_login);
password=findViewById(R.id.password_login);

//for sign up page
btnSignUp.setOnClickListener(new View.OnClickListener() {
@Override
public void onClick(View view) {

startActivity(new Intent(getApplicationContext(), RegistrationActivity.class));
}
});



}


public void signIn(View view)
    {
    btnSignIn.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {

    String mEmail=email.getText().toString().trim();
    String mPass=password.getText().toString().trim();

    if (TextUtils.isEmpty(mEmail)){
        email.setError("Required Field..");
        return;
    }
    if (TextUtils.isEmpty(mPass)){
        password.setError("Required Field..");
        return;
    }

    mDialog.setMessage("Processing..");
    mDialog.show();

    mAuth.signInWithEmailAndPassword(mEmail,mPass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
    @Override
    public void onComplete(@NonNull Task<AuthResult> task) {
    if (task.isSuccessful()){
        mDialog.dismiss();
        startActivity(new Intent(getApplicationContext(), DataButtons.class));

        Toast.makeText(getApplicationContext(),"Login Complete",Toast.LENGTH_SHORT).show();
    }else {
        Toast.makeText(getApplicationContext(),"Problem..",Toast.LENGTH_SHORT).show();

    }
    }
    });



    }
    });
    }
}
