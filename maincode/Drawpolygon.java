/*Author: Adeyemo Joel
*Description: Create the Draw polygon controller
*/

import java.awt.*;
import java.util.*;
public class Drawpolygon extends Item {
    private Vector polygonList;        //Creates a list of the points
    private Vector runningSumList;     // Holds all the temporty polygon list
    private Point starting_point;
    private Point point1;
    private Point point2;

    public Drawpolygon() {
        polygonList = new Vector(); // Intialize the polygonList 
        runningSumList = new Vector();
    }

    public Drawpolygon(Point point) {
        polygonList = new Vector(); // Intialize the polygonList 
        polygonList.add(point);
        runningSumList = new Vector();
        runningSumList.add(point);
    }

    public Drawpolygon(Point p,int z) {
        if (z == 1) {
            polygonList = new Vector(); // Intialize the polygonList 
            polygonList.add(p);
        }
    }

    public void addtoPolygon(Point point) {
        polygonList.add(point);
        runningSumList.add(point);
    }

    public void justtwoPolygon(Point point1, Point point2) {
        polygonList = new Vector();
        polygonList.add(point1);
        polygonList.add(point2);
    }

    public void setendpoint(Point point) {
        polygonList.add(point);
        runningSumList.add(point);
        polygonList = new Vector<>(runningSumList); //Copy all the points to one list
        
        Enumeration enumeration =polygonList.elements();
        while (enumeration.hasMoreElements()) {
            Point px = (Point) (enumeration.nextElement());
            System.out.println(px);
        }
    }
    public boolean includes(Point point) {
        Enumeration enumeration = polygonList.elements();
        while (enumeration.hasMoreElements()) {
            point1 = (Point) (enumeration.nextElement());
            if ((distance(point, point1) < 10.0))
                return true;
                break;
        }
        return false;
    }
    public Point getPoint() {
        //return the first point
        Enumeration enumeration = polygonList.elements(); //Get all items
        point1 = (Point) (enumeration.nextElement());
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
        uiContext.drawPolygon(polygonList);
    }
    // public String toString() {
    //     Enumeration enumeration = polygonList.elements(); //Get all items
    //     point1 =(Point)(enumeration.nextElement());
    //     return "First point  at " + point1;
    // }
}
