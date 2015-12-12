import java.io.*;
import java.net.*;
import java.applet.*;
import java.awt.*;
import java.awt.event.*;

public class IMClientGUI extends IMGUI

{
   public void connect() throws IOException
    {
      socket = new Socket("localhost", 8080);
    }

 }
