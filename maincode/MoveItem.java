import java.awt.*;
import java.text.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.*;
import javax.swing.*;
import java.util.*;

public class MoveItem extends Item{
    private Item item;
    private Point point1;
    private Vector PointList;

    public MoveItem() {
        PointList = new Vector<>();
    }

    public void moveaddpoint(Point point) {
        PointList.add(point);
    }
    public void Clear() {
        PointList.removeAllElements();
    }
    public boolean includes(Point point) {

        Enumeration enumeration =PointList.elements();
        while (enumeration.hasMoreElements()) {
            point1 = (Point) (enumeration.nextElement());
            if ((distance(point, point1) < 10.0))
                return true;
                break;
        }
        return false;
    }
    public Point getPoint() {
        //return item.getPoint();
        Point p= new Point();
        return p;
    }
    public void setLocation(Point p){
               
    }
    public void render() {
        uiContext.drawblacksquare(PointList);
    }
    public String toString() {
        Enumeration enumeration = PointList.elements(); //Get all items
        point1 =(Point)(enumeration.nextElement());
        return "First point  at " + point1;
    }
}
