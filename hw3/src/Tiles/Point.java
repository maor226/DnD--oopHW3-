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

    public int Range(Point p)
    {
        Integer res = Math.toIntExact(Math.round(Math.sqrt(Math.pow(x - p.x, 2)
                + Math.pow(y - p.y, 2))));
        return res;
    }

    public void moveRandom() {
        int x = Math.toIntExact(Math.round(Math.random()*4));
        switch (x)
        {
            case 0: MoveUp(1);
                break;
            case 1: MoveDown(1);
                break;
            case 2:MoveRight(1);
                break;
            case 3:MoveLeft(1);
                break;
        }
    }
}
