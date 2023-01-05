
/*Author: Adeyemo Joel
*Description: Parts to Draw a polygon Command based on the current Context UI
*/
import java.awt.*;
import java.text.*;
import java.util.*;

public class DrawPolygonCommand extends Command {
    private Drawpolygon polygonish;
    private int pointCount;
    private Item item;
    private Vector itemList;

    public DrawPolygonCommand() {
        polygonish = new Drawpolygon();
    }
    public DrawPolygonCommand(Point point) { // constructor that create the first point
        //itemList = new Vector();
        polygonish = new Drawpolygon(point);
        pointCount = 1;
    }
    public DrawPolygonCommand(Point point,int x) { // constructor that create the to be shifted point
        if (x == 1) {
            polygonish = new Drawpolygon(point,1);   
            pointCount = 1;
        }
    }
    public void setendPoint(Point point) {
        if (++pointCount == 2) {
            polygonish.setendpoint(point);
        }
       
    }
    public void addtoPointtoPolygon(Point pay) {
        polygonish.addtoPolygon(pay);
        pointCount = 1;
    }
    public void addjusttwoPolygon(Point point1, Point point2) {
        polygonish.justtwoPolygon(point1, point2);
    }
    public void selectdelete(Point point){
        //idea is to get each point, and delete it
        boolean found = false;
        itemList = new Vector();
        Enumeration enumeration = model.getItems();
        while (enumeration.hasMoreElements()) {
            item = (Item) (enumeration.nextElement());
            if (item.includes(point)) {
                model.markSelected(item);
                itemList.add(item);
                found = true;
                break;
            }
        }
        if(found){ model.deleteSelectedItems();} 
    }
    public void execute() {
        model.addItem(polygonish);
    }
    public boolean undo() {
        model.removeItem(polygonish);
        return true;
    }
    public boolean redo() {
        execute();
        return true;
    }
}

