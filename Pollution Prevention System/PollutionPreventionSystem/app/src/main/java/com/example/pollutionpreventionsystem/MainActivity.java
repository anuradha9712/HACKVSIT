package com.example.pollutionpreventionsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    Button b1;
    EditText mail,pass;
    TextView newuser;
    FirebaseAuth mfirebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b1=findViewById(R.id.button3);
        mail=findViewById(R.id.editText2);
        pass=findViewById(R.id.editText3);
        newuser=findViewById(R.id.textView2);
        mfirebaseAuth=FirebaseAuth.getInstance();
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email1 = mail.getText().toString();//get data from edittext referred by email
                String pass1 = pass.getText().toString();
                if (email1.isEmpty()) {

                    mail.setError("" +
                            "Please enter Your Email");
                    mail.requestFocus();

                } else if (pass1.isEmpty()) {

                    pass.setError("Please Enter password");
                    pass.requestFocus();
                } else if (email1.isEmpty() || pass1.isEmpty()) {

                    Toast.makeText(MainActivity.this, "Empty fields!!!Fill Details", Toast.LENGTH_SHORT).show();

                } else if (pass1.length() < 5) {
                    pass.setError("at least 5 characters long");
                } else {
                    mail.getText().clear();
                    pass.getText().clear();
                    final ProgressDialog pd = new ProgressDialog(MainActivity.this);
                    pd.setMessage("Authenticating...");
                    pd.show();
                    mfirebaseAuth.signInWithEmailAndPassword(email1, pass1).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (!task.isSuccessful()) {
                                Toast.makeText(MainActivity.this, "Unsuccessful Login!!!Try Again", Toast.LENGTH_LONG).show();
                            } else {
                                startActivity(new Intent(MainActivity.this, Main2Activity.class));
                                finish();
                            }
                            pd.dismiss();
                        }
                    });
                }
            }
        });

        newuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,register.class));
            }
        });
    }
}