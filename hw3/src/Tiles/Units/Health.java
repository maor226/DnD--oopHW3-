package Tiles.Units;

public class Health {
    private Integer healthPool;
    private Integer healthAmount;

    public Health(Integer healthpool, Integer healthamount)
    {
        healthPool = healthpool;
        healthAmount = healthamount;
    }


    public Integer getHealthPool() {
        return healthPool;
    }

    public void addHealthPool(Integer health)
    {
        healthPool += health;
    }
    public void AddHealth(Integer health) {
        if (health+healthAmount>healthPool)
            healthAmount=healthPool;
        healthAmount+=health;
    }

    public void refill()
    {
        healthAmount=healthPool;
    }

    public Integer getHealthAmount() {
        return healthAmount;
    }

    public void setHealthAmount(Integer healthAmount) {
        this.healthAmount = healthAmount;
    }
}
