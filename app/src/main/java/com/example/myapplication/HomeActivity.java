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

public class HomeActivity extends AppCompatActivity  implements View.OnClickListener {


    private FirebaseAuth mAuth;
    private Button buttonLogin;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private TextView textViewNewAcc;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;
    private static final String TAG = "HomeActivity";

    protected void onCreate(Bundle savedInstanceState) {


         super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        buttonLogin = (Button) findViewById(R.id.buttonSignIn);
        editTextEmail = (EditText) findViewById(R.id.editTextID);
        editTextPassword=(EditText) findViewById(R.id.editTextPassword);

        textViewNewAcc= (TextView) findViewById(R.id.textViewNewAcc);

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

        buttonLogin.setOnClickListener(this);
        textViewNewAcc.setOnClickListener(this);

    }
        private void UserLogin() {
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

        progressDialog.setMessage("Logging In. Please Wait...");
        progressDialog.show();

        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();
                        if (task.isSuccessful()) {
                            //Show that user is successfully registered
                            Toast.makeText(HomeActivity.this, "Sign In Successful", Toast.LENGTH_SHORT).show();
                        } else {
                          //  Toast.makeText(HomeActivity.this, "Registeration Failed :( !!", Toast.LENGTH_SHORT).show();

                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(HomeActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });



    }

        @Override

        public void onClick (View view){
        if (view == buttonLogin) {
            UserLogin();
        }
        if (view == textViewNewAcc) {
            //Will Open Register Page
            finish();
            startActivity(new Intent(this,SignupActivity.class));
        }
    }
}
