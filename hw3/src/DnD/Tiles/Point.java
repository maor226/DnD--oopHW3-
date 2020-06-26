package DnD.Tiles;

public class Point {
    private Integer x;
    private Integer y;

    public Point(Integer x, Integer y) {
        this.x = x;
        this.y = y;
    }



    public void Move(Direction direction){
        switch (direction){
            case Up:
                x--;
                break;
            case Down:
                x++;
                break;
            case Left:
                y--;
                break;
            case Right:
                y++;
                break;
        }
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
