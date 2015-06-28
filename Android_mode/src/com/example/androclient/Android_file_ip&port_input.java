package com.example.androclient;

import android.support.v7.app.ActionBarActivity;

import java.net.*;
import java.io.*;
import java.util.*;
import java.awt.*;

import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.os.Build;

public class MainActivity extends ActionBarActivity
{
	static EditText ip,port;
        @Override
	
        protected void onCreate(Bundle savedInstanceState) 
        {
        	super.onCreate(savedInstanceState);
        	setContentView(R.layout.activity_main);
	        Button con;
        	TextView te;
	        ip=(EditText)findViewById(R.id.editText1);
	        port=(EditText)findViewById(R.id.editText2);
	        con=(Button)findViewById(R.id.button123);
        	con.setOnClickListener(new OnClickListener()
        	{
		        @Override
			public void onClick(View v) 
			{
		             String h=ip.getText().toString();
		             String p=port.getText().toString();
		             Intent i=new Intent(getApplicationContext(),touch.class);
		             i.putExtra("IP", h);
		             i.putExtra("PORT",p);
		             //Log.e("n",ip.getText()+"."+port.getText()); //For Testing if it works
		             startActivity(i);
		        }
        	});
    	}



}
