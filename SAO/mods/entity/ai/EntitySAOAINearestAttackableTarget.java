package SAO.mods.entity.ai;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import net.minecraft.command.IEntitySelector;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;

public class EntitySAOAINearestAttackableTarget extends EntitySAOAITarget
{
    private Class targetClass;
    private float targetHeight;
    private final IEntitySelector theIEntitySelector;
    private EntitySAOAINearestAttackableTargetSorter theNearestAttackableTargetSorter;

    public EntitySAOAINearestAttackableTarget(EntityLiving par1EntityLiving, Class par2Class, float par3, float par4, boolean par5)
    {
        this(par1EntityLiving, par2Class, par3, par4, par5, false);
    }

    public EntitySAOAINearestAttackableTarget(EntityLiving par1EntityLiving, Class par2Class, float par3, float par4, boolean par5, boolean par6)
    {
        this(par1EntityLiving, par2Class, par3, par4, par5, par6, (IEntitySelector)null);
    }

    public EntitySAOAINearestAttackableTarget(EntityLiving par1EntityLiving, Class par2Class, float par3, float par4, boolean par5, boolean par6, IEntitySelector par7IEntitySelector)
    {
        super(par1EntityLiving, par3, par5, par6);
        this.targetClass = par2Class;
        this.targetHeight = par4;
        this.theNearestAttackableTargetSorter = new EntitySAOAINearestAttackableTargetSorter(this, par1EntityLiving);
        this.theIEntitySelector = par7IEntitySelector;
        this.setMutexBits(1);
    }

    @Override
    public boolean shouldExecute()
    {
        if (this.targetClass == EntityPlayer.class)
        {
        	EntityPlayer var1 = this.taskOwner.worldObj.getClosestVulnerablePlayerToEntity(this.taskOwner, (double)this.targetDistance);

        	if (this.isSAOSuitableTarget(var1, false))
        	{
        		this.taskOwner.setAttackTarget(var1);
        		return true;
        	}
        }
        else
        {
        	List var5 = this.taskOwner.worldObj.selectEntitiesWithinAABB(this.targetClass, this.taskOwner.boundingBox.expand((double)this.targetDistance, (double)this.targetHeight + 4.0D, (double)this.targetDistance), this.theIEntitySelector);
        	Collections.sort(var5, this.theNearestAttackableTargetSorter);
        	Iterator var2 = var5.iterator();

        	while (var2.hasNext())
        	{
        		Entity var3 = (Entity)var2.next();
        		EntityLiving var4 = (EntityLiving)var3;

        		if (this.isSAOSuitableTarget(var4, false))
        		{
        			this.taskOwner.setAttackTarget(var4);
        			return true;
        		}
        	}
        }
        return false;
    }

    @Override
    public boolean continueExecuting()
    {
        return super.continueExecuting() && this.shouldExecute();
    }
}