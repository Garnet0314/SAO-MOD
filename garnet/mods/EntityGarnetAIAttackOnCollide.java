package garnet.mods;

import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.pathfinding.PathEntity;
import net.minecraft.pathfinding.PathPoint;
import net.minecraft.util.MathHelper;

public class EntityGarnetAIAttackOnCollide extends EntityAIBase
{
    private EntityCreature theHost;
    private int attackTick;
    private double moveSpeed;
    private boolean longMemory;
    private PathEntity thePathEntity;
    private Class classTarget;
    private int moveTick;
    private int failedPathFindingPenalty;
    private float attackRange;
    private int nextAttackTime;

    public EntityGarnetAIAttackOnCollide(EntityCreature par1EntityCreature, Class par2Class, double par3, boolean par4)
    {
        this(par1EntityCreature, par3, par4, 20, 0.25F);
        this.classTarget = par2Class;
    }

    public EntityGarnetAIAttackOnCollide(EntityCreature par1EntityCreature, double par2, boolean par3)
    {
        this(par1EntityCreature, par2, par3, 20, 0.25F);
    }

    public EntityGarnetAIAttackOnCollide(EntityCreature par1EntityCreature, Class par2Class, double par3, boolean par4, int par5, float par6)
    {
        this(par1EntityCreature, par3, par4, par5, par6);
        this.classTarget = par2Class;
    }

    public EntityGarnetAIAttackOnCollide(EntityCreature par1EntityCreature, double par2, boolean par3, int par4, float par5)
    {
        this.theHost = par1EntityCreature;
        this.attackTick = 0;
        this.moveSpeed = par2;
        this.longMemory = par3;
        this.nextAttackTime = par4;
        this.attackRange = par5;
        this.setMutexBits(3);
    }

    @Override
    public boolean shouldExecute()
    {
        EntityLivingBase var1 = this.theHost.getAttackTarget();

        if (var1 == null)
        {
        	return false;
        }
        else if (!var1.isEntityAlive())
        {
        	return false;
        }
        else if (this.classTarget != null && !this.classTarget.isAssignableFrom(var1.getClass()))
        {
        	return false;
        }
        else
        {
        	if (-- this.moveTick <= 0)
        	{
        		this.thePathEntity = this.theHost.getNavigator().getPathToEntityLiving(var1);
        		this.moveTick = 4 + this.theHost.getRNG().nextInt(7);
        		return this.thePathEntity != null;
        	}
        	else
        	{
        		return true;
        	}
        }
    }

    @Override
    public boolean continueExecuting()
    {
        EntityLivingBase var1 = this.theHost.getAttackTarget();
        if (var1 == null)
        {
        	return false;
        }
        else if (!var1.isEntityAlive())
        {
        	return false;
        }
        else if (this.failedPathFindingPenalty >= 100)
        {
        	return false;
        }
        else if (!this.longMemory)
        {
        	return !this.theHost.getNavigator().noPath();
        }
        else
        {
        	return this.theHost.func_110176_b(MathHelper.floor_double(var1.posX), MathHelper.floor_double(var1.posY), MathHelper.floor_double(var1.posZ));
        }
    }

    @Override
    public void startExecuting()
    {
        this.theHost.getNavigator().setPath(this.thePathEntity, this.moveSpeed);
        this.moveTick = 0;
        this.failedPathFindingPenalty = 0;
    }

    @Override
    public void resetTask()
    {
        this.theHost.getNavigator().clearPathEntity();
    }

    @Override
    public void updateTask()
    {
        EntityLivingBase var1 = this.theHost.getAttackTarget();
        this.theHost.getLookHelper().setLookPositionWithEntity(var1, 30.0F, 30.0F);

        if ((this.longMemory || this.theHost.getEntitySenses().canSee(var1)) && --this.moveTick <= 0)
        {
        	this.moveTick = this.failedPathFindingPenalty + 4 + this.theHost.getRNG().nextInt(7);
        	this.theHost.getNavigator().tryMoveToEntityLiving(var1, this.moveSpeed);
        	if (this.theHost.getNavigator().getPath() != null)
        	{
        		PathPoint var2 = this.theHost.getNavigator().getPath().getFinalPathPoint();
        		if (var2 != null && var1.getDistance(var2.xCoord, var2.yCoord, var2.zCoord) < 1)
        		{
        			this.failedPathFindingPenalty = 0;
        		}
        		else
        		{
        			this.failedPathFindingPenalty += 10;
        		}
        	}
        	else
        	{
        		this.failedPathFindingPenalty += 10;
        	}
        }

        this.attackTick = Math.max(this.attackTick - 1, 0);
        double var3 = (double)(this.theHost.width + var1.width + this.attackRange);

        if (this.theHost.getDistanceSq(var1.posX, var1.boundingBox.minY, var1.posZ) <= var3 * var3)
        {
        	if (this.attackTick <= 0)
        	{
        		this.attackTick = this.nextAttackTime;

        		if (this.theHost.getHeldItem() != null)
        		{
        			this.theHost.swingItem();
        		}

        		this.theHost.attackEntityAsMob(var1);
        	}
        }
    }
}