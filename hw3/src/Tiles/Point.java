package Tiles;

public class Point {
    Integer x;
    Integer y;

    public Point(Integer x, Integer y) {
        this.x = x;
        this.y = y;
    }

    public void MoveUp(Integer step) {
        y += step;
    }

    public void MoveDown(Integer step) {
        y -= step;
    }

    public void MoveRight(Integer step) {
        x += step;
    }

    public void MoveLeft(Integer step) {
        x -= step;
    }


    public Integer getX() {
        return x;
    }

    public Integer getY() {
        return y;
    }
}
