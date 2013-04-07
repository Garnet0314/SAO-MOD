package SAO.mods.entity;

import net.minecraft.entity.boss.IBossDisplayData;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public abstract class EntitySAOBoss extends EntityMob implements ISAOMob, IBossDisplayData
{
    public EntitySAOBoss(World par1World)
    {
        super(par1World);
    }

    @Override
    protected boolean isAIEnabled()
    {
        return true;
    }

    @Override
    protected void entityInit()
    {
        super.entityInit();
        this.dataWatcher.addObject(15, new Integer(this.getHealth()));
        this.dataWatcher.addObject(16, new Integer(0));
    }

    @Override
    protected void updateAITick()
    {
        this.dataWatcher.updateObject(15, Integer.valueOf(this.getHealth()));
    }

    @Override
    public int getDragonHealth()
    {
       return this.dataWatcher.getWatchableObjectInt(15);
    }

    @Override
    public void onLivingUpdate()
    {
        super.onLivingUpdate();
        if (!this.worldObj.isRemote)
        {
        	if (this.getAttackTarget() == null)
        	{
        		this.addTargetTimer();
        	}
        	else
        	{
        		this.resetTargetTimer();
        	}
        }

        if (!this.isTargetExist())
        {
        	this.setDead();
        }
    }

    @Override
    public void onDeath(DamageSource par1DamageSource)
    {
        super.onDeath(par1DamageSource);
        //死んだときにディメンション開放
    }

    private void addTargetTimer()
    {
        this.dataWatcher.updateObject(16, this.dataWatcher.getWatchableObjectInt(16) + 1);
    }

    private void resetTargetTimer()
    {
        this.dataWatcher.updateObject(16, 0);
    }

    private boolean isTargetExist()
    {
        return this.dataWatcher.getWatchableObjectInt(16) < 500;
    }

    @Override
    public float getSize()
    {
        return 1.0F;
    }

    @Override
    public int getSlashResist()
    {
        return 0;
    }

    @Override
    public int getThrustResist()
    {
        return 0;
    }

    @Override
    public int getStrikeResist()
    {
        return 0;
    }

    @Override
    public int getPenetrateResist()
    {
        return 0;
    }

    @Override
    public int getQuakeResist()
    {
        return 0;
    }

    @Override
    public int getWaterResist()
    {
        return 0;
    }

    @Override
    public int getFireResist()
    {
        return 0;
    }

    @Override
    public int getAeroResist()
    {
        return 0;
    }

    @Override
    public int getShineResist()
    {
        return 0;
    }

    @Override
    public int getDarkResist()
    {
        return 0;
    }
}