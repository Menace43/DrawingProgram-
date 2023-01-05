import java.awt.*;
import java.util.*;
public interface UIContext {
  //  public abstract void drawCircle(Circle circle);
  public abstract void drawLine(Point point1, Point point2);
  public abstract void drawMoveLine(Point prevpoint, Point currpoint);
  public abstract void drawLabel(String s, Point p);
  public abstract void drawPolygon(Vector pointlist);
  public abstract void drawblacksquare(Vector pointlist);
  
}
