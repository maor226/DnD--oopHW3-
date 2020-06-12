package Tiles;

public abstract class Tile {
    protected Character tile;
    protected Point position;

    public Tile(Character tile, Point position) {
        this.tile = tile;
        this.position = position;
    }

   public void Print(){
       System.out.print(tile);
   }

    public Point getPosition() {
        return position;
    }

    public void setPosition(Point position){this.position=position;}
}
