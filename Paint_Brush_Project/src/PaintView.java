import javax.swing.*;
import java.awt.*;

public class PaintView extends JPanel {
    private PaintModel paintModel;
    private JPanel topPanel;
    private JPanel paintPanel;

    JButton red;
    JButton blue;
    JButton green;

    JButton Dot;
    JButton Rectangle;
    JButton Oval;
    JButton Line;

    JButton Undo;
    JButton Save;
    JButton Load;

    JButton ClearAll;

    public PaintView(PaintModel paintModel) {
        this.paintModel = paintModel;
        topPanel = new JPanel(new GridLayout(1,10));
        paintPanel = new JPanel(new BorderLayout());

        red = new JButton("Red");
        blue = new JButton("Blue");
        green = new JButton("Green");

        Dot = new JButton("Dot");
        Rectangle = new JButton("Rectangle");
        Oval = new JButton("Oval");
        Line = new JButton("Line");

        Undo = new JButton("Undo");
        Save = new JButton("Save");
        Load = new JButton("Load");
        ClearAll = new JButton("Clear All");

        topPanel.add(red);
        topPanel.add(blue);
        topPanel.add(green);

        topPanel.add(Dot);
        topPanel.add(Oval);
        topPanel.add(Line);
        topPanel.add(Rectangle);

        topPanel.add(Undo);
        topPanel.add(Save);
        topPanel.add(Load);
        topPanel.add(ClearAll);

        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(750,450));
        this.add(topPanel,BorderLayout.NORTH);
        this.add(paintPanel,BorderLayout.SOUTH);
    }

    public void drawDot(Graphics g, Shape shape) {
        g.fillOval(shape.getX()-5,shape.getY()-5,10,10);
    }

    public void drawOval(Graphics g, Shape shape) {
        g.fillOval(shape.getX(),shape.getY(),shape.getWidth(),shape.getHeight());
    }

    public void drawRectangle(Graphics g, Shape shape) {
        g.fillRect(shape.getX(),shape.getY(),shape.getWidth(),shape.getHeight());
    }
    public void drawLine(Graphics g, Shape shape) {
        g.drawLine(shape.getX(),shape.getY(),shape.getX()+getWidth(),shape.getY()+getHeight());
    }

    public void drawShape(Graphics g, Shape shape) {
        String color = shape.getColor();
        changeColor(g,color);
        String form = shape.getShape();
        if(form.equals("Dot")) {
            drawDot(g,shape);
        }
        else if(form.equals("Oval")) {
            drawOval(g,shape);
        }
        else if(form.equals("Line")) {
            drawLine(g,shape);
        }
        else {
            drawRectangle(g,shape);
        }
    }

    public void changeColor(Graphics g , String color) {
        switch (color) {
            case "red":
                g.setColor(Color.RED);
                break;
            case "green":
                g.setColor(Color.GREEN);
                break;
            case "blue":
                g.setColor(Color.BLUE);
                break;
        }
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Shape shape: paintModel.getShapes()) {
           drawShape(g,shape);
        }
    }




}
