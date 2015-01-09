package com.example.androclient;

import java.io.IOException;
import java.net.*;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import java.io.*;

import android.view.Display;
import android.view.MotionEvent;
import android.view.Surface;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class touch extends Activity implements SensorEventListener {
    
    static TextView to,touc;
    static String PORT,IP;
    static Object g,h,tex,tex1,but,but2,sen1,s1;
    int x,y,z;
    SensorManager sen;
    Sensor s;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.touch_main);
        to=(TextView) findViewById(R.id.textView11);
        sen=(SensorManager)getSystemService(Context.SENSOR_SERVICE);
        s=sen.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        touc = (TextView) findViewById(R.id.textView21);
        
        Button exit = (Button) findViewById(R.id.button11);
        Button light=(Button)findViewById(R.id.button123);
        Intent i = getIntent();
      
        IP = i.getStringExtra("IP");
        PORT = i.getStringExtra("PORT");
        g=new String(IP);
        h=new String(PORT);
        tex=to;
        tex1=touc;
        but=exit;
        but2=light;
        sen1=sen;
        s1=s;
        Log.e("Second Screen", g + " " + h);
        Object y[]={g,h,tex,tex1,but,but2,sen1,s1};
         touche T=new touche();
         
         
         
         
         T.execute(y);
        
         
      
     
 
    }
  
    protected void onResume()
    {
    	 super.onResume();
         sen.registerListener(this,s,SensorManager.SENSOR_DELAY_NORMAL);
    }
    protected void onPause()
    {
    	super.onPause();
    	sen.unregisterListener(this);
    }
	@Override
	public void onSensorChanged(SensorEvent event)
	{
		
		PrintWriter pr=touche.getter();
		x=(int)event.values[0];
		y=(int)event.values[1];
		to.setText("x "+x+"  y  "+y);
		if(x>4)
		{
			pr.println(PORT + " "  + "DATA" + " " + "left");
			pr.flush();
			touc.setText("left");
			
		}
		else if(x<-4)
		{
			pr.println(PORT + " "  + "DATA" + " " + "right");
			pr.flush();
			touc.setText("right");
		}
		else if(y<-4)
		{
			pr.println(PORT + " "  + "DATA" + " " + "front");
			pr.flush();
			touc.setText("front");
		}
		else if(y>4)
		{
			pr.println(PORT + " "  + "DATA" + " " + "back");
			pr.flush();
			touc.setText("back");
		}
		
		
	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// TODO Auto-generated method stub
		
	}
	
}
class touche extends AsyncTask
{
	static Socket soc;	
    static BufferedReader br;
    static PrintWriter pr;
    static String IP,PORT;
    static TextView to,touc;
    static Button exit,lights;
    static int x,y,z;
    SensorManager sen;
    Sensor s;
	@Override
	protected Object doInBackground(Object params[]) 
	{
		Log.e("hello3", "hello3");
		IP=IP.valueOf(params[0]);
		PORT=PORT.valueOf(params[1]);
		to=(TextView) params[2];
		
		touc=(TextView) params[3];
		exit=(Button)params[4];
		lights=(Button)params[5];
	    sen=(SensorManager)params[6];
	    s=(Sensor)params[7];
		
		
		
		try
        {
			Log.e(IP,PORT);
			InetAddress inet=InetAddress.getByName(IP);
        	soc=new Socket(inet,Integer.parseInt(PORT));
        	Log.e("hello", "hello");
            pr=new PrintWriter(new OutputStreamWriter(soc.getOutputStream()),true);
            br=new BufferedReader(new InputStreamReader(soc.getInputStream()));
            pr.println(IP);
            pr.flush();
            
        }
		catch(UnknownHostException e1)
		{
			e1.printStackTrace();
		}
		catch(IOException e)
        {
        	e.printStackTrace();
        }
        
        exit.setOnClickListener(new View.OnClickListener() {
        	 
            public void onClick(View arg0) 
            {
            	
					pr.println(IP+" LOGOUT");
					pr.flush();
				
               System.exit(0);
                
            }
        });
        lights.setOnClickListener(new View.OnClickListener()
        {

			@Override
			public void onClick(View v) {
				
				
					pr.println(PORT + " "  + "DATA" + " " + "start");
					pr.flush();
				
			}
        	
        });

		return null;
	}
	public static PrintWriter getter()
	{
		return pr;
	}

}