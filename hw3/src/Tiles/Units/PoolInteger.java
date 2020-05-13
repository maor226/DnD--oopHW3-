package Tiles.Units;

public class PoolInteger {
    Integer pool;
    Integer amaunt;

    public PoolInteger(Integer pool, Integer amaunt) {
        this.pool = pool;
        this.amaunt = amaunt;
    }

    public void AddPool(Integer addision){
        setPool(pool+addision);
    }
    public void AddAmaunt(Integer addision){
        setAmaunt(amaunt+addision);
    }

    public Integer getPool() {
        return pool;
    }

    public Integer getAmaunt() {
        return amaunt;
    }

    public void setPool(Integer pool) {
        this.pool = pool;
    }

    public void setAmaunt(Integer amaunt) {
        if(amaunt>pool)
            amaunt=pool;
        this.amaunt = amaunt;
    }
}
