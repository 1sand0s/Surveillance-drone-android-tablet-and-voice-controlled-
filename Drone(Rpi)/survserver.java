import java.net.*;
import java.util.*;
import java.io.*;

public class survserver
{
   
    static Vector cliSoc;
    static Vector Log;
    static 
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
    public static native void cam();
    survserver() throws Exception
    {
        ServerSocket soc=new ServerSocket(9000,100);
        cliSoc=new Vector();
        Log=new Vector();
        System.out.println("Waiting for connection .....");
        while(true)
        {    
            Socket CSoc=soc.accept();
            AcceptClient obClient=new AcceptClient(CSoc);
        }
    }
    public static void main(String args[]) throws Exception
    {
        
       survserver  ob=new survserver();
    }

class AcceptClient extends Thread
{
    Socket ClientSocket;
    BufferedReader br;
    PrintWriter pr;
    AcceptClient (Socket CSoc) throws Exception
    {
        ClientSocket=CSoc;

        br=new BufferedReader(new InputStreamReader(ClientSocket.getInputStream()));
        pr=new PrintWriter(new OutputStreamWriter(ClientSocket.getOutputStream()),true);
        
        String LoginName=br.readLine();
	pr.println("Connected ");
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
				f.trim();
				if(f.equalsIgnoreCase("start"))
				{
						pr.println("Starting");
						pr.flush();
						start1();
		         	}
				else if(f.startsWith("search"))
				{
						
						pr.println("Searching");
						pr.flush();
						f=f+" ";
						String g=f.substring(f.indexOf("-"),f.length()-1);
						search(g);
				}
				else if(f.equalsIgnoreCase("front"))
				{
						pr.println("Moving front");
                                  		pr.flush();
						front();
				}
				else if(f.equalsIgnoreCase("back"))
				{
					pr.println("Moving back");
					pr.flush();
					back();
				}
				else if(f.equalsIgnoreCase("left"))
				{
					pr.println("Moving left");
					pr.flush();
					left();
				}
				else if(f.equalsIgnoreCase("right"))
				{
					pr.println("Moving Right");
					pr.flush();
					right();
				}
				else if (f.equalsIgnoreCase("cam"))
				{
					pr.println("Initializing cam");
					pr.flush();
					cam();
				}

                StringTokenizer st=new StringTokenizer(msgFromClient);
                String Sendto=st.nextToken();                
                String MsgType=st.nextToken();
                int iCount=0;
                
                if(MsgType.equals("LOGOUT"))
                {
                    for(iCount=0;iCount<Log.size();iCount++)
                    {
                        if(Log.elementAt(iCount).equals(Sendto))
                        {
                            Log.removeElementAt(iCount);
                            cliSoc.removeElementAt(iCount);
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
                    for(iCount=0;iCount<Log.size();iCount++)
                    {
                        if(Log.elementAt(iCount).equals(Sendto))
                        {    
                            Socket tSoc=(Socket)cliSoc.elementAt(iCount);                            
                            PrintWriter tdout=new PrintWriter(new OutputStreamWriter(tSoc.getOutputStream()),true);
                            tdout.println(msg); 
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
}
