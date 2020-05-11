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

public class register extends AppCompatActivity {
    EditText name,ph,mail,pass;
    Button reg;
    TextView login;
    FirebaseAuth mFirebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);



        mail=findViewById(R.id.etmail4);
        pass=findViewById(R.id.etpass4);
        ph=findViewById(R.id.etph4);
        name=findViewById(R.id.etname4);
        reg=findViewById(R.id.bregister4);
        login=findViewById(R.id.tv4);
        mFirebaseAuth=FirebaseAuth.getInstance();
        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String mail1=mail.getText().toString();
                String pass1=pass.getText().toString();
                String ph1=ph.getText().toString();
                String name1=name.getText().toString();
                if (name1.isEmpty()) {

                    name.setError("" +
                            "Please enter Your Email");
                    name.requestFocus();

                }
                else if (mail1.isEmpty()) {

                    mail.setError("" +
                            "Please enter Your Email");
                    mail.requestFocus();

                } else if (pass1.isEmpty()) {

                    pass.setError("Please Enter password");
                    pass.requestFocus();
                } else if (mail1.isEmpty() || pass1.isEmpty()||ph1.isEmpty()||name1.isEmpty()) {

                    Toast.makeText(register.this, "Empty fields!!!Fill Details", Toast.LENGTH_SHORT).show();

                } else if (pass1.length() < 5) {
                    pass.setError("at least 5 characters long");
                }
                else if (ph1.length() < 10) {
                    pass.setError("Please Enter correct Phone number");
                }

                else {
                    final ProgressDialog pd = new ProgressDialog(register.this);
                    pd.setMessage("Loading...");
                    pd.show();
                    pass.getText().clear();
                    mail.getText().clear();
                    name.getText().clear();
                    ph.getText().clear();
                    mFirebaseAuth.createUserWithEmailAndPassword(mail1, pass1).addOnCompleteListener(register.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (!task.isSuccessful()) {
                                Toast.makeText(register.this, "Error!!!Try Again", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(register.this, "Successfully Registered,Login now", Toast.LENGTH_SHORT).show();
                            }
                            pd.dismiss();
                        }
                    });
                }
            }


        });
    login.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            startActivity(new Intent(register.this,MainActivity.class));
        }
    });


    }
}
