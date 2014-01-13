package garnet.mods;

import garnet.mods.entity.IGarnetEnemyFlying;
import garnet.mods.entity.passive.EntityGarnetBase;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.attributes.AttributeInstance;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityGhast;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.pathfinding.PathEntity;
import net.minecraft.pathfinding.PathPoint;
import net.minecraft.util.MathHelper;

import org.apache.commons.lang3.StringUtils;

public abstract class EntityGarnetAITarget extends EntityAIBase
{
    protected EntityCreature taskOwner;
    protected boolean shouldCheckSight;
    protected boolean nearbyOnly;
    protected int targetSearchStatus;
    protected int targetSearchDelay;
    private int field_75298_g;

    public EntityGarnetAITarget(EntityCreature par1EntityCreature, boolean par2)
    {
        this(par1EntityCreature, par2, false);
    }

    public EntityGarnetAITarget(EntityCreature par1EntityCreature, boolean par2, boolean par3)
    {
        this.taskOwner = par1EntityCreature;
        this.shouldCheckSight = par2;
        this.nearbyOnly = par3;
    }

    @Override
    public boolean continueExecuting()
    {
        EntityLivingBase var1 = this.taskOwner.getAttackTarget();

        if (var1 == null)
        {
        	return false;
        }
        else if (!var1.isEntityAlive())
        {
        	return false;
        }
        else
        {
        	double var2 = this.getTargetDistance();

        	if (this.taskOwner.getDistanceSqToEntity(var1) > var2 * var2)
        	{
        		return false;
        	}
        	else
        	{
        		if (this.shouldCheckSight)
        		{
        			if (this.taskOwner.getEntitySenses().canSee(var1))
        			{
        				this.field_75298_g = 0;
        			}
        			else if (++this.field_75298_g > 60)
        			{
        				return false;
        			}
        		}

        		return true;
        	}
        }
    }

    protected double getTargetDistance()
    {
        AttributeInstance var1 = this.taskOwner.getEntityAttribute(SharedMonsterAttributes.followRange);
        return var1 == null ? 16.0D : var1.getAttributeValue();
    }

    @Override
    public void startExecuting()
    {
        this.targetSearchStatus = 0;
        this.targetSearchDelay = 0;
        this.field_75298_g = 0;
    }

    @Override
    public void resetTask()
    {
        this.taskOwner.setAttackTarget((EntityLivingBase)null);
    }

    protected boolean isGarnetSuitableTarget(EntityLivingBase par1EntityLivingBase, boolean par2)
    {
        if (par1EntityLivingBase == null)
        {
        	return false;
        }
        else if (par1EntityLivingBase == this.taskOwner)
        {
        	return false;
        }
        else if (!par1EntityLivingBase.isEntityAlive())
        {
        	return false;
        }
        else
        {
        	if (par1EntityLivingBase.getClass() == EntityCreeper.class)
        	{
        		if (this.taskOwner instanceof EntityGarnetBase)
        		{
        			if (!((EntityGarnetBase)this.taskOwner).canAttackExplosive())
        			{
        				return false;
        			}
        		}
        		else
        		{
        			return false;
        		}
        	}
        	if (par1EntityLivingBase.getClass() == EntityGhast.class || par1EntityLivingBase instanceof IGarnetEnemyFlying)
        	{
        		if (this.taskOwner instanceof EntityGarnetBase)
        		{
        			if (!((EntityGarnetBase)this.taskOwner).canAttackFlying())
        			{
        				return false;
        			}
        		}
        		else
        		{
        			return false;
        		}
        	}

        	if (this.taskOwner instanceof EntityGarnetBase && StringUtils.isNotEmpty(((EntityGarnetBase)this.taskOwner).getOwnerName()))
        	{
        		if (par1EntityLivingBase instanceof EntityGarnetBase && ((EntityGarnetBase)this.taskOwner).getOwner() == ((EntityGarnetBase) par1EntityLivingBase).getOwner())
        		{
        			return false;
        		}

        		if (par1EntityLivingBase == ((EntityGarnetBase)this.taskOwner).getOwner())
        		{
        			return false;
        		}
        	}
        	else if (par1EntityLivingBase instanceof EntityPlayer && !par2 && ((EntityPlayer)par1EntityLivingBase).capabilities.disableDamage)
        	{
        		return false;
        	}

        	if (!this.taskOwner.func_110176_b(MathHelper.floor_double(par1EntityLivingBase.posX), MathHelper.floor_double(par1EntityLivingBase.posY), MathHelper.floor_double(par1EntityLivingBase.posZ)))
        	{
        		return false;
        	}
        	else if (this.shouldCheckSight && !this.taskOwner.getEntitySenses().canSee(par1EntityLivingBase))
        	{
        		return false;
        	}
        	else
        	{
        		if (this.nearbyOnly)
        		{
        			if (--this.targetSearchDelay <= 0)
        			{
        				this.targetSearchStatus = 0;
        			}

        			if (this.targetSearchStatus == 0)
        			{
        				this.targetSearchStatus = this.canEasilyReach(par1EntityLivingBase) ? 1 : 2;
        			}

        			if (this.targetSearchStatus == 2)
        			{
        				return false;
        			}
        		}

        		return true;
        	}
        }
    }

    protected boolean canEasilyReach(EntityLivingBase par1EntityLivingBase)
    {
        this.targetSearchDelay = 10 + this.taskOwner.getRNG().nextInt(5);
        PathEntity var1 = this.taskOwner.getNavigator().getPathToEntityLiving(par1EntityLivingBase);

        if (var1 == null)
        {
        	return false;
        }
        else
        {
        	PathPoint var2 = var1.getFinalPathPoint();

        	if (var2 == null)
        	{
        		return false;
        	}
        	else
        	{
        		int var3 = var2.xCoord - MathHelper.floor_double(par1EntityLivingBase.posX);
        		int var4 = var2.zCoord - MathHelper.floor_double(par1EntityLivingBase.posZ);
        		return (double)(var3 * var3 + var4 * var4) <= 2.25D;
        	}
        }
    }
}