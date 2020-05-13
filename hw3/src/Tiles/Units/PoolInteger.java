package Tiles.Units;

public class PoolInteger {
    Integer pool;
    Integer amount;

    public PoolInteger(Integer pool, Integer amaunt) {
        this.pool = pool;
        this.amount = amaunt;
    }

    public void AddPool(Integer addision){
        setPool(pool+addision);
    }
    public void AddAmount(Integer addision){
        setAmount(amount +addision);
    }
    public boolean Use(Integer amount){
        if(this.amount>=amount){
            setAmount(this.amount -amount);
            return true;
        }
        return false;
    }

    public Integer getPool() {
        return pool;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setPool(Integer pool) {
        this.pool = pool;
    }

    public void setAmount(Integer amount) {
        if(amount>pool)
            amount=pool;
        this.amount = amount;
    }
}
