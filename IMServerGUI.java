import java.io.*;
import java.net.*;
import java.applet.*;
import java.awt.*;
import java.awt.event.*;

public class IMServerGUI extends IMGUI
{
   public void connect() throws IOException
   {

       ServerSocket server=new ServerSocket(8080);
       socket = server.accept();


   }


}