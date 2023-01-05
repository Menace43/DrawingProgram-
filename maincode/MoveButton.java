/* Author: Adeyemo Joel
 *  Descripton: Deals with the MoveButton Part
 * Algorithm:
    Select all the components and just get a point of the components
    Add a black dot to each of the components ie line, oval,circle,polygon etc
    If a any of the components is clicked:
        Get the point to know which components was clicked
        Set the location ie item.setLocation
        Delete the Last line and add to model
        referesh
 
 *       
*/

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
public class MoveButton extends JButton implements ActionListener{
    protected JPanel drawingPanel;
    protected View view;
    private MouseHandler mouseHandler;
    private MoveCommand moveCommand;
    private UndoManager undoManager;

    public MoveButton(UndoManager undoManager, View jFrame, JPanel jPanel) {
        super("Move");
        addActionListener(this);
        view = jFrame;
        drawingPanel = jPanel;
        this.undoManager = undoManager;
        mouseHandler = new MouseHandler();
    }

    public void actionPerformed(ActionEvent event) {
        moveCommand = new MoveCommand();
        view.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR)); //Change Cursor
        moveCommand.selectAll();
        drawingPanel.addMouseListener(mouseHandler);
        drawingPanel.addMouseMotionListener(mouseHandler);
        undoManager.beginCommand(moveCommand);
    }

    private class MouseHandler extends MouseAdapter {
        private int pointCount = 0;
        private Point Currpoint = new Point();
        public void mousePressed(MouseEvent event) {
            if (moveCommand.KnowPoint(View.mapPoint(event.getPoint()))) {
                System.out.println("Mouse Pressed");
                System.out.println(event.getPoint());
                pointCount = 1;
                Currpoint = event.getPoint();
            }
        }

        public void mouseDragged(MouseEvent event) {

            System.out.println("MouseDragged");
            System.out.println(event.getPoint());
            int NewX;
            int NewY;

            if (pointCount == 1) {
                moveCommand.setLocation(View.mapPoint(event.getPoint()));
            }

        }
        
        public void mouseReleased(MouseEvent e) {
            System.out.println("Mouse Released");
            System.out.println(e.getPoint());
            pointCount = 0;
            moveCommand.setLocation(View.mapPoint(e.getPoint()));
            moveCommand.ClearList();
            drawingPanel.removeMouseListener(this);
            view.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            undoManager.endCommand(moveCommand);
        }
    }
}
