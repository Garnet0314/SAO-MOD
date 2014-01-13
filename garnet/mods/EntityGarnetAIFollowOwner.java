package garnet.mods;

import garnet.mods.entity.passive.EntityGarnetTameable;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityGarnetAIFollowOwner extends EntityAIBase
{
    private EntityGarnetTameable theHost;
    private EntityPlayer theOwner;
    private World theWorld;
    private double moveSpeed;
    private PathNavigate petPathfinder;
    private int moveTick;
    private double Distance;
    private double maxDist;
    private double minDist;
    private boolean avoidWater;

    public EntityGarnetAIFollowOwner(EntityGarnetTameable par1EntityGarnetBase, double par2, double par3)
    {
        this.theHost = par1EntityGarnetBase;
        this.theWorld = par1EntityGarnetBase.worldObj;
        this.moveSpeed = par2;
        this.petPathfinder = par1EntityGarnetBase.getNavigator();
        this.Distance = par3;
        this.setMutexBits(3);
    }

    @Override
    public boolean shouldExecute()
    {
        EntityPlayer var1 = theHost.getOwner();

        if (var1 == null)
        {
        	return false;
        }
        else if (this.theHost.isStandbyMode())
        {
        	return false;
        }
        else if (this.theHost.isFlying())
        {
        	return false;
        }
        else
        {
        	if (this.theHost.isFreeMode())
        	{
        		this.minDist = this.Distance * 4.0F + 6.0F;
        	}
        	else
        	{
        		this.minDist = this.Distance * 2.0F + 3.0F;
        	}

        	if (this.theHost.getDistanceSqToEntity(var1) < this.minDist * this.minDist)
        	{
        		return false;
        	}
        	else
        	{
        		this.theOwner = var1;
        		return true;
        	}
        }
    }

    @Override
    public boolean continueExecuting()
    {
        if (this.theHost.isFreeMode())
        {
        	this.maxDist = this.Distance * 2.0F;
        }
        else
        {
        	this.maxDist = this.Distance + 1.0F;
        }
        return !this.petPathfinder.noPath() && this.theHost.getDistanceSqToEntity(this.theOwner) > this.maxDist * this.maxDist && !this.theHost.isStandbyMode();
    }

    @Override
    public void startExecuting()
    {
        this.moveTick = 0;
        this.avoidWater = this.theHost.getNavigator().getAvoidsWater();
        this.theHost.getNavigator().setAvoidsWater(false);
    }

    @Override
    public void resetTask()
    {
        this.theOwner = null;
        this.petPathfinder.clearPathEntity();
        this.theHost.getNavigator().setAvoidsWater(this.avoidWater);
    }

    @Override
    public void updateTask()
    {
        this.theHost.getLookHelper().setLookPositionWithEntity(this.theOwner, 10.0F, (float)this.theHost.getVerticalFaceSpeed());

        if (!this.theHost.isStandbyMode())
        {
        	if (--this.moveTick <= 0)
        	{
        		this.moveTick = 10;

        		if (!this.petPathfinder.tryMoveToEntityLiving(this.theOwner, this.moveSpeed))
        		{
        			if (this.theHost.getDistanceSqToEntity(this.theOwner) >= 225.0D)
        			{
        				int var1 = MathHelper.floor_double(this.theOwner.posX) - 2;
        				int var2 = MathHelper.floor_double(this.theOwner.posZ) - 2;
        				int var3 = MathHelper.floor_double(this.theOwner.boundingBox.minY);

        				for (int var4 = 0; var4 <= 4; ++var4)
        				{
        					for (int var5 = 0; var5 <= 4; ++var5)
        					{
        						if ((var4 < 1 || var5 < 1 || var4 > 3 || var5 > 3) && this.theWorld.doesBlockHaveSolidTopSurface(var1 + var4, var3 - 1, var2 + var5) && !this.theWorld.isBlockNormalCube(var1 + var4, var3, var2 + var5) && !this.theWorld.isBlockNormalCube(var1 + var4, var3 + 1, var2 + var5))
        						{
        							this.theHost.setLocationAndAngles((double)((float)(var1 + var4) + 0.5F), (double)var3, (double)((float)(var2 + var5) + 0.5F), this.theHost.rotationYaw, this.theHost.rotationPitch);
        							this.petPathfinder.clearPathEntity();
        							return;
        						}
        					}
        				}
        			}
        		}
        	}
        }
    }
}