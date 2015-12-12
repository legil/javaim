import java.io.*;
import java.net.*;
import java.applet.*;
import java.awt.*;
import java.awt.event.*;

public class IMGUI extends AppletNLO implements Runnable, ActionListener
{

   TextArea chatTA = new TextArea();
   TextField messageTF = new TextField();

   Label     userLB = new Label("Username");
   TextField userTF = new TextField();



   List list1 = new List();
   List list2 = new List();



   Socket socket;
   DataInputStream input;
   DataOutputStream output;
   Thread t;


   public void connect()throws IOException
   {
    socket=new Socket("localhost",8080);

   }


   public void init()
   {
   try{

       connect();


       input = new DataInputStream(socket.getInputStream());
       output = new DataOutputStream(socket.getOutputStream());
      }
      catch(IOException x){System.out.print("error");}






      add(chatTA,    10,   10, 300, 200);
      add(messageTF, 10,  210, 300,  20, this);
      add(userLB,    310, 190, 60,   20);
      add(userTF,    310, 210, 60,   20);


      add(list1, 320, 10, 100, 100);
      add(list2, 320, 110, 100, 100);

      list1.addItem("Brian");
      list1.addItem("Richard");
      list1.addItem("Leslie");
      list1.addItem("Cindy");
      list1.addItem("Andy");
      list1.addItem("Zain");
      list1.addItem("Carol");
      list1.addItem("John");
      list1.addItem("Adriano");

      list1.addItemListener(new ListMover());
      list2.addItemListener(new ListRemover());



      t = new Thread(this);
      t.start();
   }


   public class ListMover implements ItemListener
   {

      public void itemStateChanged(ItemEvent e)
      {
         list2.addItem(list1.getSelectedItem());
         list1.remove(list1.getSelectedIndex());
      }

   }

   public class ListRemover implements ItemListener
   {

      public void itemStateChanged(ItemEvent e)
      {
         list1.addItem(list2.getSelectedItem());
         list2.remove(list2.getSelectedIndex());
      }

   }

   public void actionPerformed(ActionEvent e)
   {
        String message = userTF.getText() + ": " + messageTF.getText() + "\n";

        messageTF.setText("");


        chatTA.appendText(message);

        try
        {
           output.writeUTF(message);
        }
        catch(IOException x)
        {
           System.out.println("error");
        }
   }

   public void run(){

      do{
         try
         {
            String message = input.readUTF();
            chatTA.appendText(message);
         }
         catch(IOException x)
         {
            System.out.println("Error");
         }
      }while(true);

   }

}