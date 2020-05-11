package com.example.pollutionpreventionsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class challengeme extends AppCompatActivity {
    ListView l;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_challengeme);

        l=findViewById(R.id.ListView);
        ArrayList<String> al=new ArrayList<>();
        al.add("1.Ronak     points=?");
        al.add("2.Anuradha  points=?");
        al.add("3.Anu       points=?");
        al.add("4.Sunil     points=?");
        al.add("5.Krishna   points=?");
        al.add("6.Updesh    points=?");
        al.add("7.Tanuj     points=?");
        al.add("8.Saurav    points=?");
        al.add("9.Rishav    points=?");
        al.add("10.XYZ      points=?");
        al.add("60.ABC      points=?");
        al.add("61.Rohit    points=?");
        al.add("63.Ruchi    points=?");
        al.add("100.Monika  points=?");
        ArrayAdapter arrayAdapter=new ArrayAdapter(this,android.R.layout.simple_list_item_1,al);
        l.setAdapter(arrayAdapter);

    }
}
