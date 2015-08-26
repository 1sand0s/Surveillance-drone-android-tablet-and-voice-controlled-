/**
 * Copyright (C) 2015 ADITYA T 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
 
import java.net.*;
import java.io.*;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
class Rpi_Client_Java extends JFrame implements Runnable,ActionListener
{
    Socket socket;    
    static JTextField textf;
    static JTextArea texta;
    static JButton send,close,clear ;
    static String sendTo;
    static String IP;
    static char buf[];
    Thread t=null;
    static BufferedReader din;
    static BufferedWriter dout;
    Rpi_Client_Java(String IP,String PORTN) throws Exception
    {
        super(IP);
        this.IP=IP;
        sendTo=PORTN;
        textf=new JTextField(50);
        texta=new JTextArea(50,50);
        send=new JButton("Send");
        buf=new char[100];
        close=new JButton("Close");
	clear=new JButton("Clear");
	int PORT=Integer.parseInt(PORTN);
        socket=new Socket(IP,PORT);
        send.addActionListener(this);
	send.setActionCommand("send");
	close.addActionListener(this);
	close.setActionCommand("close");
	clear.addActionListener(this);
	clear.setActionCommand("clear");
        din=new BufferedReader(new InputStreamReader(socket.getInputStream()));
        dout=new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));        
        dout.write(IP);
        dout.newLine();
        dout.flush();
        t=new Thread(this);
        t.start();

    }
	public enum options{send,close,clear}
	public void actionPerformed(ActionEvent e)
	{
	    options op=options.valueOf(e.getActionCommand());
		switch(op)
		{
			case send:
			try
            		{
                		dout.write(sendTo + " "  + "DATA" + " " + textf.getText().toString());    
                		dout.newLine();
                		dout.flush();
                		texta.append("\n" + IP + " Says:" + textf.getText().toString());    
                		textf.setText("");
            		}
            		catch(Exception ex)
            		{
            		}    
			break;
			
			case close:
			try
            		{
                		dout.write(IP + " LOGOUT");
                		dout.newLine();
		                dout.flush();
		                System.exit(1);
            		}
            		catch(Exception ex)
            		{
            		}
			break;
			
			case clear:
				texta.setText("");
				break;
		}
	}
    void setup()
    {
        setSize(600,400);
        setLayout(new GridLayout(2,1));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setBackground(new Color(0));
        add(texta);
        JPanel p=new JPanel();
        p.add(textf);
	p.add(clear);
        p.add(send);
        p.add(close);
        add(p);
        setVisible(true);   
    }

    public static void main(String args[]) throws Exception
    {
        new Rpi_Client_Java(args[0],args[1]).setup();          
    }    
    public void run()
    {        
        while(true)
        {
            try
            {
            	din.read(buf,0,100);
            	texta.append("\n"+sendTo + " Says :");
            	for(int i=0;i<buf.length;i++)
            	{
            		texta.append(buf[i]+"");
            	}
            }
            catch(Exception ex)
            {
                ex.printStackTrace();
            }
        }
    }
}
