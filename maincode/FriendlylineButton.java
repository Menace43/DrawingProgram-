
/*Author: Adeyemo Joel
*Description: Parts to create a Friendly Line Button
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
    
    

*/

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class FriendlylineButton extends JButton implements ActionListener {
    protected JPanel drawingPanel;
    protected View view;
    private MouseHandler mouseHandler;
    private LineCommand lineCommand;
    private UndoManager undoManager;

    public FriendlylineButton(UndoManager undoManager, View jFrame, JPanel jPanel) {
        super("FriendLine");
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
        private Point tempPoint;
        private Point currentPoint;
        private Point currentlastPoint= new Point();

        public void mouseClicked(MouseEvent event) {
            if (++pointCount == 1) {
                System.out.print("Temp Point");
                System.out.println(event.getPoint());
                lineCommand = new LineCommand(View.mapPoint(event.getPoint()));
                tempPoint = event.getPoint();
                undoManager.beginCommand(lineCommand);
            } else if (pointCount == 2) {
                pointCount = 0;
                lineCommand.setLinePoint(View.mapPoint(event.getPoint()));
                drawingPanel.removeMouseMotionListener(this);
                drawingPanel.removeMouseListener(this);
                view.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                undoManager.endCommand(lineCommand);
            }
        }

        public void mouseMoved(MouseEvent event) {
            if (pointCount == 1) {
                System.out.print("Current Point");
                System.out.println(event.getPoint());
                currentPoint = event.getPoint();
                if (lineCommand != null ){
                    undoManager.endCommand(lineCommand);
                }
                if (currentlastPoint != currentPoint) {
                    System.out.print("Current Last Point");
                    System.out.println(currentlastPoint);
                    currentlastPoint = currentPoint;
                    lineCommand = new LineCommand();
                    lineCommand.selectdelete(currentlastPoint);
                    lineCommand.selectdelete(tempPoint);
                    lineCommand.LineCommandMove(tempPoint, View.mapPoint(event.getPoint()));
                    undoManager.beginCommand(lineCommand);
                    //view.refresh();
                }

            }
        }
    }
}
