/*Author: Adeyemo Joel
*Description: Parts to Draw a polygon
* Algorithm:
    Get the first point-- due to clicking
    Listen for movement of the mouse 
    Has the mouse moves until second click:
        Get the current point
        Add to the items
        if there a change betwen the current point and the current last point:
           select that point
           delete that point with the line
           redraw with new point
    
    After Second Click:
        Get the current point
        Add to the items
        if there a change betwen the current point and the current last point:
           select that point
           delete that point with the line
           redraw with new point
           Draw line between first click and currpoint
           Draw line between second click and currpoint
    
    Until the right click:
        Add the right click as the last point:
        Draw Polyon with the three Click or more
    
    
*/

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class DrawpolygonButton extends JButton implements ActionListener{
    protected JPanel drawingPanel;
    protected View view;
    private MouseHandler mouseHandler;
    private DrawPolygonCommand polyCommand;
    private LineCommand lineCommand;
    private UndoManager undoManager;

    public DrawpolygonButton(UndoManager undoManager, View jFrame, JPanel jPanel) {
        super("Draw Polygon");
        this.undoManager = undoManager;
        addActionListener(this);
        view = jFrame;
        drawingPanel = jPanel;
        mouseHandler = new MouseHandler();
    }

    public void actionPerformed(ActionEvent event) {
        view.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
        // Change cursor when button is clicked
        drawingPanel.addMouseListener(mouseHandler);
        drawingPanel.addMouseMotionListener(mouseHandler);
        // Start listening for mouseclicks on the drawing panel
    }

    private class MouseHandler extends MouseAdapter {
        private int pointCount = 0;
        private int pointCounttest = 0;
        private boolean begin = true;
        private DrawPolygonCommand temppolyCommand;
        private Point tempPoint;
        private Point currentPoint;
        private Point currentlastPoint= new Point();

        public void mouseClicked(MouseEvent event) {

            if (SwingUtilities.isRightMouseButton(event) || event.isControlDown()) { // After right click
                System.out.print("Right Click Point");
                System.out.println(event.getPoint());

                //polyCommand.addtoPointtoPolygon(View.mapPoint(event.getPoint()));
                polyCommand = temppolyCommand;
                polyCommand.setendPoint(View.mapPoint(event.getPoint()));
                undoManager.beginCommand(polyCommand);
                begin = true;
                pointCount = -1;
                pointCounttest = 0;
                drawingPanel.removeMouseListener(this);
                drawingPanel.removeMouseMotionListener(this);
                view.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                undoManager.endCommand(polyCommand);
            }
            
            if (++pointCount == 1) {
                pointCounttest = 1;
                if (begin == true) {
                    System.out.print("First Point");
                    System.out.println(event.getPoint());
                    tempPoint = event.getPoint();
                    polyCommand = new DrawPolygonCommand(View.mapPoint(event.getPoint()));
                    temppolyCommand = polyCommand;
                    undoManager.beginCommand(polyCommand);
                    begin = false;
                }
                else 
                {
                    System.out.print("Next Point");
                    System.out.println(event.getPoint());
                    tempPoint = event.getPoint();
                    polyCommand = temppolyCommand;
                    polyCommand.addtoPointtoPolygon(View.mapPoint(event.getPoint()));
                }
            } else if (pointCount == 2) {
                System.out.print("Second Point");
                System.out.println(event.getPoint());
                tempPoint = event.getPoint();
                pointCount = 0;
                pointCounttest = 1;
                polyCommand = temppolyCommand;
                polyCommand.addtoPointtoPolygon(View.mapPoint(event.getPoint()));  ///**
            }
    
        }
    
        public void mouseMoved(MouseEvent event) {
            if (pointCounttest == 1) {
                System.out.print("Current Point");
                System.out.println(event.getPoint());
                currentPoint = event.getPoint();
                if (polyCommand != null ){
                    undoManager.endCommand(polyCommand);
                }
                if (currentlastPoint != currentPoint) {
                    System.out.print("Current Last Point");
                    System.out.println(currentlastPoint);
                    currentlastPoint = currentPoint;
                    polyCommand = new DrawPolygonCommand();
                    //polyCommand= new DrawPolygonCommand(tempPoint,1);
                    //Check for the current polygon command and delete it
                    polyCommand.selectdelete(currentlastPoint);
                    polyCommand.selectdelete(tempPoint);
                    polyCommand.addjusttwoPolygon(tempPoint,currentlastPoint);
                    undoManager.beginCommand(polyCommand);
                    //view.refresh();
                }

            }
            
        }
    }
       
}
