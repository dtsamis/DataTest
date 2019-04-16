package com.example.android.datatest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.firebase.client.Firebase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DataButtons extends AppCompatActivity
    {

    @Override
    protected void onCreate(Bundle savedInstanceState)
        {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_buttons);
        }

    public void addItem(View view)
        {
        startActivity(new Intent(getApplicationContext(),AddItem.class));
        }
    }
