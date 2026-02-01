import javax.swing.*;
import java.io.*;
import java.util.ArrayList;

public class PaintModel {
    private String color ;
    private String shape;
    private ArrayList<Shape> shapes ;

    public PaintModel()
    {
        this.color = "black";
        this.shape = "line";
        shapes = new ArrayList<>();

    }
    public String getColor() {
        return color;
    }
    public String getShape() {
        return shape;
    }
    public ArrayList<Shape> getShapes() {
        return shapes;
    }

    public void setColor(String color) {
        this.color = color;
    }
    public void setShape(String shape) {
        this.shape = shape;
    }
    public void setShapes(ArrayList<Shape> shapes) {
        this.shapes = shapes;
    }

    public void Undo()
    {
        if (shapes.size()>0)
        {
            shapes.remove(shapes.size()-1);
        }
    }

    public void Save() throws IOException {
        JFileChooser jFileChooser = new JFileChooser();
        if(jFileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION)
        {
            File file = jFileChooser.getSelectedFile();
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(shapes);
            objectOutputStream.flush();
            objectOutputStream.close();
        }

    }

    public void Load() throws IOException, ClassNotFoundException {
        JFileChooser jFileChooser = new JFileChooser();
        if (jFileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
        {
            File file = jFileChooser.getSelectedFile();
            FileInputStream fileInputStream = new FileInputStream(file);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            shapes = (ArrayList<Shape>) objectInputStream.readObject();
            objectInputStream.close();
        }
    }

    public void addShape(Shape shape) {
        shapes.add(shape);
    }

    public void mouseMoved(int x, int y) {
        if(!shapes.isEmpty())
        {
            Shape shape = shapes.get(shapes.size()-1);
            shape.mouseMoved(x,y);
        }
    }
}
