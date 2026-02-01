import java.io.Serializable;

public class Shape  implements Serializable {
    private String color;
    private String shape;
    private int x;
    private int y;

    private int xMoved;
    private int yMoved;

    public Shape(String color, String shape, int x, int y) {
        this.color = color;
        this.shape = shape;
        this.x = x;
        this.y = y;
        this.xMoved = x;
        this.yMoved = y;
    }

    public String getColor() {
        return color;
    }
    public String getShape() {
        return shape;
    }
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }

    public int getWidth() {
        return Math.abs(x-xMoved);
    }
    public int getHeight() {
        return Math.abs(y-yMoved);
    }

    public void mouseMoved(int x, int y) {
        xMoved = x;
        yMoved = y;
    }
}
