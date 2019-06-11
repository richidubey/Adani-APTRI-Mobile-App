package com.example.myapplication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import org.w3c.dom.Text;

public class SignupActivity extends AppCompatActivity  implements View.OnClickListener {


    private FirebaseAuth mAuth;
    private Button buttonRegister;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private TextView textViewLogin;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;
    private static final String TAG = "SignUpActivity";

    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        buttonRegister = (Button) findViewById(R.id.buttonRegister);
        editTextEmail = (EditText) findViewById(R.id.editTextID);
        editTextPassword=(EditText) findViewById(R.id.editTextPassword);

        textViewLogin = (TextView) findViewById(R.id.textViewLogin);

        progressDialog = new ProgressDialog(this);

        firebaseAuth = FirebaseAuth.getInstance();


        //TextView textView1 = (TextView) findViewById(R.id.NewAcc);

        // textView1.setOnClickListener(new View.OnClickListener(){

        //   Intent myIntent = new Intent(view.getContext(), SignupActivity.class);
        // startActivityForResult(myIntent, 0);
        //   }
        //});


        // TextView prettyText= (TextView) findViewById(R.id.prettyme);

        //   Typeface font = Typeface.createFromAsset(getAssets(), "Pacifico.ttf");

        //   prettyText.setTypeface(font);

        buttonRegister.setOnClickListener(this);
        textViewLogin.setOnClickListener(this);

    }
    private void registerUser () {
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();


        if (TextUtils.isEmpty(email)) {
            //email is empty

            Toast.makeText(this, "Please Enter Emp ID", Toast.LENGTH_SHORT).show();
            //return stops from executing further
            return;
        }

        if (TextUtils.isEmpty(password)) {
            //Password is empty

            Toast.makeText(this, "Please Enter Password", Toast.LENGTH_SHORT).show();
            //return stops from executing further
            return;
        }

        //Validations are OKAY!!

        progressDialog.setMessage("Being Registered. Please Wait");
        progressDialog.show();

        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();
                        if (task.isSuccessful()) {
                            //Show that user is successfully registered
                            Toast.makeText(SignupActivity.this, "Registeration Successful", Toast.LENGTH_SHORT).show();
                        } else {
                            //  Toast.makeText(HomeActivity.this, "Registeration Failed :( !!", Toast.LENGTH_SHORT).show();

                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(SignupActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });

        progressDialog.dismiss();

    }

    @Override

    public void onClick (View view){
        if (view == buttonRegister) {
            registerUser();
        }
        if (view == textViewLogin) {
            //Will Open Register Page
        }
    }
}
