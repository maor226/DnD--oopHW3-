package Tiles;

public class Point {
    Integer x;
    Integer y;

    public Point(Integer x, Integer y) {
        this.x = x;
        this.y = y;
    }

    public void MoveUp(Integer step) {
        x += step;
    }

    public void MoveDown(Integer step) {
        x -= step;
    }

    public void MoveRight(Integer step) {
        y += step;
    }

    public void MoveLeft(Integer step) {
        y -= step;
    }


    public Integer getX() {
        return x;
    }

    public Integer getY() {
        return y;
    }

    public int Range(Point p)
    {
        Integer res = Math.toIntExact(Math.round(Math.sqrt(Math.pow(x - p.x, 2)
                + Math.pow(y - p.y, 2))));
        return res;
    }
}
