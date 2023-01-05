/* Author: Adeyemo Joel
 *  Descripton: Deals with the MoveCommand Part
*/

import java.awt.*;
import java.util.*;

public class MoveCommand extends Command {
    private Item item;
    private MoveItem move;

    public MoveCommand() {
        move = new MoveItem();
    }
    public boolean KnowPoint(Point point) {
        boolean found = false;
        Enumeration enumeration = model.getItems();
        while (enumeration.hasMoreElements()) {
            item = (Item) (enumeration.nextElement());
            if (item.includes(point)) {
                model.Knowmove(item);
                found = true;
                break;
            }
        }
        return found;
    }

    public void selectAll() {
        Enumeration enumeration = model.getItems(); //Get all items
        while (enumeration.hasMoreElements()) {
            //System.out.println("Move Selected Items");
            item = (Item) (enumeration.nextElement());
            System.out.println(item.getPoint());
            move.moveaddpoint(item.getPoint()); // Add each point for moving
            model.markformove(item);
            //System.out.println(item);
        }
        model.addItem(move);
        model.markformoverefresh();
    }
    public void setLocation(Point p) {
        Enumeration enumeration = model.getKnowmoveItems();
        item = (Item) enumeration.nextElement();
        item.setLocation(p);

    }
    public void ClearList() {
        move.Clear();
        model.unmarkforKnownmove();
    }
    public void urmove(Point p) {
        // Get the point that should be moved
    }
    public boolean undo() {
        model.unmarkformove(move);
        return true;
    }

    public boolean redo() {
        execute();
        return true;
    }
    public boolean end() {
        model.unmarkforKnownmove();
        return true;
    }
    public void execute() {
        model.markformove(move);
        //model.addItem(move);
   } 
}
