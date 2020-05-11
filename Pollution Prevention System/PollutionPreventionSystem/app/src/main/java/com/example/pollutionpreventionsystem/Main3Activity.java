package com.example.pollutionpreventionsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class Main3Activity extends AppCompatActivity {

    private DrawerLayout dl;
    private ActionBarDrawerToggle t;
    private NavigationView nv;
    Button b1,b2;
    FirebaseAuth mFirebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        b1=findViewById(R.id.button2);
        b2=findViewById(R.id.button4);

//drawer open and close
        dl = (DrawerLayout) findViewById(R.id.activity_main);
        t = new ActionBarDrawerToggle(this, dl, R.string.Open, R.string.Close);

        dl.addDrawerListener(t);
        t.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//3 buttons

        nv = (NavigationView) findViewById(R.id.nv);//click on navigation item
        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch (id) {
                    case R.id.predict:
                        startActivity(new Intent(Main3Activity.this,prediction.class));
                        break;
                    case R.id.classification:
                        startActivity(new Intent(Main3Activity.this,classification.class));
                        break;
                    case R.id.challengefriend:
                        startActivity(new Intent(Main3Activity.this,challengeme.class));
                                                break;
                    case R.id.signout:
                        startActivity(new Intent(Main3Activity.this,MainActivity.class));
                        mFirebaseAuth=FirebaseAuth.getInstance();
                        mFirebaseAuth.signOut();
                        finish();

                    default:
                        return true;
                }


                return true;
            }


        });

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Main3Activity.this,AQI.class));
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Main3Activity.this,NQI.class));
            }
        });
    }
    //3 buttons click
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (t.onOptionsItemSelected(item))
            return true;

        return super.onOptionsItemSelected(item);

    }
}



