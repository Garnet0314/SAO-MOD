package garnet.mods;

import garnet.mods.entity.IGarnetEnemyFlying;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import net.minecraft.command.IEntitySelector;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.boss.EntityWither;
import net.minecraft.entity.monster.EntityGhast;

public class EntityGarnetAINearestAttackableTarget extends EntityGarnetAITarget
{
    protected Class targetClass;
    protected double targetHeight;
    protected final EntityGarnetAINearestAttackableTargetSorter theNearestAttackableTargetSorter;
    protected final IEntitySelector theNearestAttackableTargetSelector;
    protected final IEntitySelector theIEntitySelector;
    private boolean allTarget;

    public EntityGarnetAINearestAttackableTarget(EntityCreature par1EntityCreature, Class par2Class, boolean par3)
    {
        this(par1EntityCreature, par2Class, 0.0D, par3);
    }

    public EntityGarnetAINearestAttackableTarget(EntityCreature par1EntityCreature, Class par2Class, double par3, boolean par4)
    {
        this(par1EntityCreature, par2Class, par3, par4, false);
    }

    public EntityGarnetAINearestAttackableTarget(EntityCreature par1EntityCreature, Class par2Class, double par3, boolean par4, boolean par5)
    {
        this(par1EntityCreature, par2Class, par3, par4, par5, (IEntitySelector)null, false);
    }

    public EntityGarnetAINearestAttackableTarget(EntityCreature par1EntityCreature, Class par2Class, double par3, boolean par4, boolean par5, IEntitySelector par6IEntitySelector, boolean par7)
    {
        super(par1EntityCreature, par4, par5);
        this.targetClass = par2Class;
        this.targetHeight = par3;
        this.theNearestAttackableTargetSorter = new EntityGarnetAINearestAttackableTargetSorter(par1EntityCreature);
        this.theNearestAttackableTargetSelector = new EntityGarnetAINearestAttackableTargetSelector(this, par6IEntitySelector);
        this.theIEntitySelector = par6IEntitySelector;
        this.allTarget = par7;
        this.setMutexBits(1);
    }

    @Override
    public boolean shouldExecute()
    {
        double var1 = this.getTargetDistance();
        if (this.theIEntitySelector == IGarnetEnemyFlying.garnetEnemySelector)
        {
        	List var2 = this.taskOwner.worldObj.selectEntitiesWithinAABB(this.targetClass, this.taskOwner.boundingBox.expand(32.0D, 32.0D, 32.0D), this.theNearestAttackableTargetSelector);

        	List var3 = this.taskOwner.worldObj.selectEntitiesWithinAABB(EntityGhast.class, this.taskOwner.boundingBox.expand(32.0D, 32.0D, 32.0D), (IEntitySelector)null);
        	var2.addAll(var3);
        	var3 = this.taskOwner.worldObj.selectEntitiesWithinAABB(EntityDragon.class, this.taskOwner.boundingBox.expand(32.0D, 32.0D, 32.0D), (IEntitySelector)null);
        	var2.addAll(var3);
        	var3 = this.taskOwner.worldObj.selectEntitiesWithinAABB(EntityWither.class, this.taskOwner.boundingBox.expand(32.0D, 32.0D, 32.0D), (IEntitySelector)null);
        	var2.addAll(var3);
        	if (this.allTarget)
        	{
        		var3 = this.taskOwner.worldObj.selectEntitiesWithinAABB(this.targetClass, this.taskOwner.boundingBox.expand(var1, this.targetHeight + 4.0D, var1), (IEntitySelector)null);
        		var2.addAll(var3);
        	}

        	Collections.sort(var2, this.theNearestAttackableTargetSorter);
        	Iterator var4 = var2.iterator();

        	while (var4.hasNext())
        	{
        		Entity var5 = (Entity)var4.next();
        		EntityLivingBase var6 = (EntityLivingBase)var5;

        		if (this.isGarnetSuitableTarget(var6, false))
        		{
        			this.taskOwner.setAttackTarget(var6);
        			return true;
        		}
        	}
        	return false;
        }
        else
        {
        	List var7 = this.taskOwner.worldObj.selectEntitiesWithinAABB(this.targetClass, this.taskOwner.boundingBox.expand(var1, this.targetHeight + 4.0D, var1), this.theNearestAttackableTargetSelector);
        	Collections.sort(var7, this.theNearestAttackableTargetSorter);

        	if (var7.isEmpty())
        	{
        		return false;
        	}
        	else
        	{
        		EntityLivingBase var8 = (EntityLivingBase)var7.get(0);
        		this.taskOwner.setAttackTarget(var8);
        		return true;
        	}
        }
    }

    @Override
    public boolean continueExecuting()
    {
        return super.continueExecuting() && this.shouldExecute();
    }
}