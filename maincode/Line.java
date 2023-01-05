import java.awt.*;
import java.awt.Point;
public class Line extends Item {
  private Point point1;
  private Point point2;
  public Line(Point point1, Point point2) {
    this.point1 = point1;
    this.point2 = point2;
  }
  public Line(Point point1) {
    this.point1 = point1;
	  point2 = null;
  }
  public Line() {
	  point1 = null;
	  point2 = null;
  }

  public boolean includes(Point point) {
    return ((distance(point, point1) < 10.0) || (distance(point, point2) < 10.0));
  }
  public Point getPoint() {
    return point1;
  }
  public void setLocation(Point p) {
    //distance between the two point
    //double disth = distance(this.point1, this.point2);
    double NewX = this.point2.getX() - this.point1.getX();
    double NewY = this.point2.getY()-this.point1.getY();
    NewX= p.getX() + NewX;
    NewY = p.getY() + NewY;
    Point p3 = new Point();
    p3.setLocation(NewX,NewY);
    this.point1 = p;
    this.point2 = p3;

  }
  public void render() {
    uiContext.drawLine(point1, point2);
  }
  public void setPoint1(Point point) {
    point1 = point;
  }
  public void setPoint2(Point point) {
    point2 = point;
  }
  public Point getPoint1() {
    return point1;
  }
  public Point getPoint2() {
    return point2;
  }
  public String toString() {
    return "Line  from " + point1 + " to " + point2;
  }
}

