package com.example.booksapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    DBManager dbManager;
    FragmentTransaction transaction;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbManager=DBManager.getInstance();
        dbManager.setMain(this);
        FragmentManager manager = getSupportFragmentManager();
        transaction=manager.beginTransaction();
        transaction.replace(R.id.fragmentLayout, FragmentList.newInstance(this),"LISTA").commit();
        //transaction.replace(R.id.fragmentLayout,new ListFragment(this)," ");
        //transaction.commit();


    }


}