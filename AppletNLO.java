import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;


public class AppletNLO extends Applet
{

   public AppletNLO()
   {
      super();

      setLayout(null);
   }


   public void add(Component c, int x, int y, int w, int h)
   {
      add(c);

      c.setBounds(x, y, w, h);
   }

   public void add(Component b, int x, int y, int w, int h, ActionListener al)
   {
      add(b, x, y, w, h);
   }

   public void add(Button b, int x, int y, int w, int h, ActionListener al)
   {
      add(b, x, y, w, h);

      b.addActionListener(al);
   }

   public void add(TextField t, int x, int y, int w, int h, ActionListener al)
   {
      add(t, x, y, w, h);

      t.addActionListener(al);
   }

}
