import java.net.*;
import java.util.*;
import java.io.*;

public class Rpi_Server_Java
{
    Rpi_Server_Java() throws Exception
    {
    	ServerSocket soc=new ServerSocket(9000,100);
        System.out.println("Waiting for connection .....");
        while(true)
        {    
            Socket CSoc=soc.accept();
            new AcceptClient(CSoc);
        }
    }
    public static void main(String args[]) throws Exception
    {
        new Rpi_Server_Java();
    }
}

class AcceptClient extends Thread
{
    Socket ClientSocket;
    BufferedReader br;
    BufferedWriter pr;
    static ArrayList<Socket> cliSoc;
    static ArrayList<String>Log;
    /*static 
    {
	System.loadLibrary("cprogstart");
	System.loadLibrary("cprogsearch");
	System.loadLibrary("cprogfront");
	System.loadLibrary("cprogback");
	System.loadLibrary("cprogleft");
	System.loadLibrary("cprogright");
	System.loadLibrary("cprogcam");
    }
    public static  native void start1();
    public static native void search(String h);
    public static native void front();
    public static native void back();
    public static native void left();  
    public static native void right();
    public static native void cam();*/
    
    AcceptClient (Socket CSoc) throws Exception
    {
    	
        cliSoc=new ArrayList<Socket>();
        Log=new ArrayList<String>();
        ClientSocket=CSoc;

        br=new BufferedReader(new InputStreamReader(ClientSocket.getInputStream()));
        pr=new BufferedWriter(new OutputStreamWriter(ClientSocket.getOutputStream()));
        
        String LoginName=br.readLine();
        pr.write("Connected");
        pr.newLine();
        pr.flush();
        System.out.println("User Logged In :" + LoginName);
        Log.add(LoginName);
        cliSoc.add(ClientSocket);    
        start();
    }

    public void run()
    {
        while(true)
        {
            
            try
            {
                String msgFromClient=new String();
                msgFromClient=br.readLine();
				String f=msgFromClient.substring(msgFromClient.lastIndexOf(' ')+1,msgFromClient.length());
				System.out.println(f);
				f.trim();
				if(f.equalsIgnoreCase("start"))
				{
						pr.write("Starting");
						pr.newLine();
						pr.flush();
						//start1();
		         	}
				else if(f.startsWith("search"))
				{
						
						pr.write("Searching");
						pr.newLine();
						pr.flush();
						f=f+" ";
						String g=f.substring(f.indexOf("-"),f.length()-1);
						//search(g);
				}
				else if(f.equalsIgnoreCase("front"))
				{
						pr.write("Moving front");
						pr.newLine();
                				 pr.flush();
						//front();
				}
				else if(f.equalsIgnoreCase("back"))
				{
						pr.write("Moving back");
						pr.newLine();
						pr.flush();
						//back();
				}
				else if(f.equalsIgnoreCase("left"))
				{
						pr.write("Moving left");
						pr.newLine();
						pr.flush();
						//left();
				}
				else if(f.equalsIgnoreCase("right"))
				{
						pr.write("Moving Right");
						pr.newLine();
						pr.flush();
						//right();
				}
				else if (f.equalsIgnoreCase("cam"))
				{
						pr.write("Initializing cam");
						pr.newLine();
						pr.flush();
						//cam();
				}

                StringTokenizer st=new StringTokenizer(msgFromClient);
                String Sendto=st.nextToken();                
                String MsgType=st.nextToken();
                int i=0;
                
                if(MsgType.equals("LOGOUT"))
                {
                    for(i=0;i<Log.size();i++)
                    {
                        if(Log.get(i).equals(Sendto))
                        {
                            Log.remove(i);
                            cliSoc.remove(i);
                            System.out.println("User " + Sendto +" Logged Out ...");
                            break;
                        }
                    }
    
                }
                else
                {
                    String msg="";
                    while(st.hasMoreTokens())
                    {
                        msg=msg+" " +st.nextToken();
                    }
                    for(i=0;i<Log.size();i++)
                    {
                        if(Log.get(i).equals(Sendto))
                        {    
                            Socket tSoc=(Socket)cliSoc.get(i);                            
                            BufferedWriter tdout=new BufferedWriter(new OutputStreamWriter(tSoc.getOutputStream()));
                            tdout.write(msg);
                            tdout.newLine();
                            tdout.flush();
                            break;
                        }
                    }
                    
                }
                if(MsgType.equals("LOGOUT"))
                {
                    break;
                }

            }
            catch(Exception ex)
            {
                ex.printStackTrace();
            }
            
        }        
    }
}

