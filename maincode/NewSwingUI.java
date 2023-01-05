import java.awt.Graphics;
import java.util.Arrays;
import java.util.Vector;
import java.awt.*;
import java.util.*;
public class NewSwingUI implements UIContext {
  private Graphics graphics;
  private static NewSwingUI swingUI;
  private NewSwingUI() {
  }
  public static NewSwingUI getInstance() {
    if (swingUI == null) {
      swingUI = new NewSwingUI();
    }
    return swingUI;
  }
  public  void setGraphics(Graphics graphics) {
    this.graphics = graphics;
  }
  public void drawLabel(String s, Point p) {
    if (p != null) {
      if (s != null) {
        graphics.drawString(s, (int) p.getX(), (int) p.getY());
      }
    }
    int length = graphics.getFontMetrics().stringWidth(s);
    graphics.drawString("_", (int) p.getX() + length, (int) p.getY());
  }

  public void drawLine(Point point1, Point point2) {
    int i1 = 0;
    int i2 = 0;
    int i3 = 0;
    int i4 = 0;
    if (point1 != null) {
      i1 = Math.round((float) (point1.getX()));
      i2 = Math.round((float) (point1.getY()));
      if (point2 != null) {
        i3 = Math.round((float) (point2.getX()));
        i4 = Math.round((float) (point2.getY()));
      } else {
        i3 = i1;
        i4 = i2;
      }
      graphics.drawLine(i1, i2, i3, i4);
    }
  }

  public void drawMoveLine(Point prevpoint, Point currpoint){
    int dx = 0;
    int dy = 0;

    dx = (int) (prevpoint.x - currpoint.x);
    dy = (int) (prevpoint.y - currpoint.y);

  }
  public void drawPolygon(Vector pointlist) {
    int[] x= new int[pointlist.size()];
    int[] y=new int[pointlist.size()];
    int count=0;
    boolean found=false;
    Point point;
    Enumeration enumeration = pointlist.elements();   //Get all items
    while (enumeration.hasMoreElements()) {
      found = true;
      point = (Point) (enumeration.nextElement());
      if (point != null) {
        int x1 = Math.round((float) (point.getX()));
        int y1 = Math.round((float) (point.getY()));
        x[count] = x1;
        y[count] = y1;
        count++;
      }
    }
    
    System.out.println(x);
    System.out.println(y);
    System.out.println(found);
    graphics.drawPolygon(x, y, pointlist.size());
    
    
    // // x coordinates of vertices
    // int x[] = { 10, 30, 40, 50, 110, 140 };

    // // y coordinates of vertices
    // int y[] = { 140, 110, 50, 40, 30, 10 };

    // // number of vertices
    // int numberofpoints = 2;

    // graphics.drawPolygon(x, y, numberofpoints);
  }
  public void drawblacksquare(Vector pointlist) {
    Point point;
    Enumeration enumeration = pointlist.elements();   //Get all items
    while (enumeration.hasMoreElements()) {
      point = (Point) (enumeration.nextElement());
      int x = Math.round((float) (point.getX()));
      int y = Math.round((float) (point.getY()));
      graphics.fill3DRect(x, y, 5, 5, true);
    }
    
  }
 
}
