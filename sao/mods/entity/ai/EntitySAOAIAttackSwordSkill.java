package sao.mods.entity.ai;

import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.pathfinding.PathEntity;
import net.minecraft.pathfinding.PathPoint;
import net.minecraft.util.MathHelper;
import sao.mods.entity.ISAOMob;

public class EntitySAOAIAttackSwordSkill extends EntityAIBase
{
    private EntityCreature theHost;
    private ISAOMob theSAOMob;
    private int attackTick;
    private double moveSpeed;
    private boolean longMemory;
    private PathEntity thePathEntity;
    private Class classTarget;
    private int moveTick;
    private int failedPathFindingPenalty;
    private float attackRange;
    private int finishAttackTime;
    private double stayX;
    private double stayY;
    private double stayZ;

    public EntitySAOAIAttackSwordSkill(ISAOMob par1ISAOMob, double par2, boolean par3)
    {
        this(par1ISAOMob, par2, par3, 100, 0.5F);
    }

    public EntitySAOAIAttackSwordSkill(ISAOMob par1ISAOMob, double par2, boolean par3, int par4, float par5)
    {
        if (!(par1ISAOMob instanceof EntityCreature))
        {
        	throw new IllegalArgumentException("SwordSkill requires Mob implements SAOMob");
        }
        else
        {
        	this.theHost = (EntityCreature)par1ISAOMob;
        	this.theSAOMob = par1ISAOMob;
        	this.moveSpeed = par2;
        	this.longMemory = par3;
        	this.finishAttackTime = par4;
        	this.attackRange = par5;
        	this.setMutexBits(3);
        }
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
        else if (!this.theSAOMob.shouldSwordSkill())
        {
        	return false;
        }
        else
        {
        	double var3 = (double)(this.theHost.width + var1.width + this.attackRange);
        	if (this.theHost.getDistanceSq(var1.posX, var1.boundingBox.minY, var1.posZ) > var3 * var3)
        	{
        		this.thePathEntity = this.theHost.getNavigator().getPathToEntityLiving(var1);
        		this.moveTick = 4 + this.theHost.getRNG().nextInt(7);
        		this.attackTick = -1;
        		return this.thePathEntity != null;
        	}
        	else
        	{
        		this.attackTick = 0;
        		this.stayX = this.theHost.posX;
        		this.stayY = this.theHost.posY;
        		this.stayZ = this.theHost.posZ;
        		return true;
        	}
        }
    }

    @Override
    public boolean continueExecuting()
    {
        if (!this.theSAOMob.continueSwordSkill())
        {
        	return false;
        }
        else if (this.attackTick < 0)
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
        else
        {
        	if (this.finishAttackTime < this.attackTick)
        	{
        		return false;
        	}
        	else
        	{
        		return true;
        	}
        }
    }

    @Override
    public void startExecuting()
    {
        this.theSAOMob.readySwordSkill();
        this.theHost.getNavigator().setPath(this.thePathEntity, this.moveSpeed);
        this.failedPathFindingPenalty = 0;
    }

    @Override
    public void resetTask()
    {
        this.theHost.getNavigator().clearPathEntity();
        this.theSAOMob.resetSwordSkill();
    }

    @Override
    public void updateTask()
    {
        EntityLivingBase var1 = this.theHost.getAttackTarget();
        if (var1 != null)
        {
        	this.theHost.getLookHelper().setLookPositionWithEntity(var1, 30.0F, 30.0F);
        }

        if (this.attackTick < 0)
        {
        	if ((this.longMemory || this.theHost.getEntitySenses().canSee(var1)) && --this.moveTick <= 0)
        	{
        		this.moveTick = this.failedPathFindingPenalty + 4 + this.theHost.getRNG().nextInt(7);
        		this.theHost.getNavigator().tryMoveToEntityLiving(var1, this.moveSpeed);
        		if (this.theHost.getNavigator().getPath() != null)
        		{
        			PathPoint var2 = this.theHost.getNavigator().getPath().getFinalPathPoint();
        			if (var2 != null && var1.getDistance(var2.xCoord, var2.yCoord, var2.zCoord) < 1.0D)
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

        	double var3 = (double)(this.theHost.width + var1.width + this.attackRange);

        	if (this.theHost.getDistanceSq(var1.posX, var1.boundingBox.minY, var1.posZ) <= var3 * var3)
        	{
        		this.attackTick++;
        		this.theHost.getNavigator().clearPathEntity();
        		this.theSAOMob.startSwordSkill();
        		this.stayX = this.theHost.posX;
        		this.stayY = this.theHost.posY;
        		this.stayZ = this.theHost.posZ;
        		this.theHost.motionX = 0.0D;
        		this.theHost.motionY = 0.0D;
        		this.theHost.motionZ = 0.0D;
        	}
        }
        else
        {
        	if (this.theSAOMob.doSwordSkill(this.attackTick))
        	{
        		if (this.theHost.getHeldItem() != null)
        		{
        			this.theHost.swingItem();
        		}
        	}
        	this.attackTick++;
        	this.theHost.setPosition(this.stayX, this.stayY, this.stayZ);
        	this.theHost.motionX = 0.0D;
        	this.theHost.motionY = 0.0D;
        	this.theHost.motionZ = 0.0D;
        }
    }
}