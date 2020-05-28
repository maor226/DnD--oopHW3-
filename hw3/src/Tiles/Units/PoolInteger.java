package Tiles.Units;

public class PoolInteger {
    Integer pool;
    Integer amount;

    public PoolInteger(Integer pool, Integer amount) {
        this.pool = pool;
<<<<<<< HEAD
        this.amount = amount;
=======
        this.amount = amaunt;
>>>>>>> 271c39df42e3669a2c7d93e3e28cf59a39101637
    }

    public void AddPool(Integer addition){
        setPool(pool+addition);
    }
<<<<<<< HEAD
    public void AddAmount(Integer addition){
        setAmount(amount+addition);
=======
    public void AddAmount(Integer addision){
        setAmount(amount +addision);
    }
    public boolean Use(Integer amount){
        if(this.amount>=amount){
            setAmount(this.amount -amount);
            return true;
        }
        return false;
>>>>>>> 271c39df42e3669a2c7d93e3e28cf59a39101637
    }
    public void Damage(Integer damage) { setAmount(amount-damage);}

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
<<<<<<< HEAD
        else if(amount<0)
            amount=0;
=======
>>>>>>> 271c39df42e3669a2c7d93e3e28cf59a39101637
        this.amount = amount;
    }
}
