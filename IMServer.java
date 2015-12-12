import java.io.*;
import java.net.*;


public class IMServer
{
    final int size = 5;


    ClientReader[] clientreader = new ClientReader[size];


   public static void main(String[] args)
   {
      IMServer imserver = new IMServer();

      imserver.init();
   }




   public void init()
   {
      Thread[] t = new Thread[size];

      try
      {
         ServerSocket server = new ServerSocket(8080);


         for(int i = 0; i < size; i++)
         {
            Socket socket = server.accept();

            clientreader[i] = new ClientReader(socket);

            t[i] = new Thread(clientreader[i]);

            t[i].start();
         }

      }
      catch(IOException x){};





   }




   int count = 0;


   public class ClientReader implements Runnable
   {
      DataInputStream  input;
      DataOutputStream output;


      int index;


      public ClientReader(Socket socket)
      {
         try
         {
            input  = new DataInputStream(socket.getInputStream());

            output = new DataOutputStream(socket.getOutputStream());
         }
         catch(IOException x){};
         
         index = count++;
      }


      public void run()
      {
         while(true)
         {
            try
            {
               String message = input.readUTF();


               for(int i = 0; i < size; i++)
               {
                  if(i != index)
                     clientreader[i].output.writeUTF(message);
               }
            }
            catch(IOException x){};
         }
      }
   }




}
