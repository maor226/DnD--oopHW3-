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

    public Integer Range(Tile t){
        Integer res = Math.toIntExact(Math.round(Math.sqrt(Math.pow(position.x - t.position.x, 2)
                + Math.pow(position.y - t.position.y, 2))));
        return res;
    }
}
