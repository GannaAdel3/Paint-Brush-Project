import javax.swing.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.ArrayList;

public class PaintController extends JFrame implements ActionListener {
    private PaintModel paintModel;
    private PaintView paintView;

    public PaintController(){
        paintModel = new PaintModel();
        paintView = new PaintView(paintModel);

        paintView.red.addActionListener(this);
        paintView.blue.addActionListener(this);
        paintView.green.addActionListener(this);

        paintView.Line.addActionListener(this);
        paintView.Dot.addActionListener(this);
        paintView.Oval.addActionListener(this);
        paintView.Rectangle.addActionListener(this);
        paintView.Save.addActionListener(this);
        paintView.Undo.addActionListener(this);
        paintView.Load.addActionListener(this);
        paintView.ClearAll.addActionListener(this);

        paintView.addMouseListener(new  MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                String color = paintModel.getColor();
                String form = paintModel.getShape();
                int x = e.getX();
                int y = e.getY();

                Shape shape = new Shape(color,form,x,y);
                paintModel.addShape(shape);

                paintView.repaint();
            }
        });

        paintView.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();
                paintModel.mouseMoved(x,y);
                paintView.repaint();
            }
        });

        setVisible(true);
        add(paintView);
        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "Red":
                paintModel.setColor("red");
                break;
            case "Blue":
                paintModel.setColor("blue");
                break;
            case "Green":
                paintModel.setColor("green");
                break;

            case "Dot":
                paintModel.setShape("Dot");
                break;
            case "Oval":
                paintModel.setShape("Oval");
                break;
            case "Line":
                paintModel.setShape("Line");
                break;
            case "Rectangle":
                paintModel.setShape("Rectangle");
                break;

            case "Save":
                try {
                    paintModel.Save();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                break;
            case "Undo":
                paintModel.Undo();
                paintView.repaint();
                break;
            case "Load":
                try {
                    paintModel.Load();
                    paintView.repaint();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                } catch (ClassNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
                break;
            case "Clear All":
                paintModel.setShapes(new ArrayList<>());
                paintView.repaint();

        }

    }

}

