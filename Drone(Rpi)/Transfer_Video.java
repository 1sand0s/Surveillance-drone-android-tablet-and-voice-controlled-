package Rpi_Server;


import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.*;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import com.sun.org.apache.xml.internal.security.utils.Base64;

import processing.core.PApplet;
import processing.video.Capture;

class Transfer_Video extends PApplet
{
	/* Class that handles transferring the video as seen by the cam mounted 
	   on the bot to the android tablet or pc or any client connected to
	   the bot */
	static Capture v;
	static Object delay;
	static File f1;
	static Transfer_Video current;
	static boolean set=false;
	static String index=new String();
	static String h[];
	public void setup()
	{
		size(640,480);
		h=Capture.list();
		current=this;
		delay=new Object();
		try
		{
			if(h.length==0)
			{
				Send_To_Client("No Cameras");
			}
			else
			{
				for(int i=0;i<h.length;i++)
				{
					Send_To_Client(h[i]+" Index= "+i);
				}
				new Wait_For_Input().start();
			}
		}
		catch(Exception e)
		{
			
		}
	}
	public void draw()
	{
		if(set)
		{
			if(v.available()==true)
			{
				v.read();
			}
			image(v,0,0);
			save("C:/Users/Computer/Desktop/pic.jpg");
			f1=new File("C:/Users/Computer/Desktop/pic.jpg");
			try
			{
				BufferedImage i=ImageIO.read(f1);
				ByteArrayOutputStream ba=new ByteArrayOutputStream();
				ImageIO.write(i,"jpg",ba);
				String s=Base64.encode(ba.toByteArray());
				AcceptClient.pr.write(s);
			}
			catch(Exception e)
			{
				
			}
		}
	}
	public void Send_To_Client(String g)throws Exception
	{
		AcceptClient.pr.write(g);
		AcceptClient.pr.newLine();
		AcceptClient.pr.flush();
	}
	class Wait_For_Input extends Thread
	{
		public void run()
		{
			synchronized(delay)
			{
				
				try
				{
					Send_To_Client("Select One by Choosing Index");
					delay.wait();
					System.out.println("Check3  "+index);
					Send_To_Client(index);
					v=new Capture(current,h[Integer.parseInt(index)]);
					v.start();
					set=true;
				}
				catch(Exception e)
				{
						
				}
				
			}
		}
	}
	static class Input_Received extends Thread
	{
		 String f;
		 Input_Received(String f2)
		 {
			 f=f2;
		 }
		public void run()
		{
			synchronized(delay)
			{
				
				index=f;
				System.out.println("Check 2 "+index);
				delay.notify();
			}
		}
	}
	
}
