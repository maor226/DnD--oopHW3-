package DnD.Tiles.Units;

public class PoolInteger {
    Integer pool;
    Integer amount;

    public PoolInteger(Integer pool,Integer amount) {
        this.pool = pool;
        this.amount = amount;
    }

    public void AddPool(Integer addition){
        setPool(pool+addition);
    }

    public void ChangeAmount(Integer addition) {
        if(amount+addition<0)
            setAmount(0);
        else if (amount+addition>pool)
            setAmount(pool);
        else
            setAmount(amount + addition);
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
        else if(amount<0)
            amount=0;
        this.amount = amount;
    }

    public boolean isZero()
    {
        return amount==0;
    }

    @Override
    public String toString() {
        return amount+"/"+pool;
    }
}
