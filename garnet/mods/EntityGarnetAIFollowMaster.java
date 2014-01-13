package garnet.mods;

import garnet.mods.entity.IServantGarnet;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.util.MathHelper;

public class EntityGarnetAIFollowMaster extends EntityAIBase
{
    private EntityCreature theHost;
    private IServantGarnet theIServantGarnet;
    private EntityLivingBase theMaster;
    private double moveSpeed;
    private int moveTick;
    private boolean field_75344_i;

    public EntityGarnetAIFollowMaster(IServantGarnet par1IServantGarnet, double par2)
    {
        this.theHost = (EntityCreature)par1IServantGarnet;
        this.theIServantGarnet = par1IServantGarnet;
        this.moveSpeed = par2;
        this.setMutexBits(1);
    }

    @Override
    public boolean shouldExecute()
    {
        EntityLivingBase var1 = this.theIServantGarnet.getMaster();

        if (var1 == null)
        {
        	return false;
        }
        else if (!var1.isEntityAlive())
        {
        	return false;
        }
        else if (this.theHost.getDistanceSqToEntity(var1) < 16.0D)
        {
        	return false;
        }
        else
        {
        	this.theMaster = var1;
        	return true;
        }
    }

    @Override
    public boolean continueExecuting()
    {
        return !this.theHost.getNavigator().noPath() && this.theHost.getDistanceSqToEntity(this.theMaster) > 4.0D;
    }

    @Override
    public void startExecuting()
    {
        this.moveTick = 0;
        this.field_75344_i = this.theHost.getNavigator().getAvoidsWater();
        this.theHost.getNavigator().setAvoidsWater(false);
    }

    @Override
    public void resetTask()
    {
        this.theMaster = null;
        this.theHost.getNavigator().clearPathEntity();
        this.theHost.getNavigator().setAvoidsWater(this.field_75344_i);
    }

    @Override
    public void updateTask()
    {
        this.theHost.getLookHelper().setLookPositionWithEntity(this.theMaster, 10.0F, (float)this.theHost.getVerticalFaceSpeed());

        if (--this.moveTick <= 0)
        {
        	this.moveTick = 10;

        	if (!this.theHost.getNavigator().tryMoveToEntityLiving(this.theMaster, this.moveSpeed))
        	{
        		if (this.theHost.getDistanceSqToEntity(this.theMaster) >= 144.0D)
        		{
        			int var1 = MathHelper.floor_double(this.theMaster.posX) - 2;
        			int var2 = MathHelper.floor_double(this.theMaster.posZ) - 2;
        			int var3 = MathHelper.floor_double(this.theMaster.boundingBox.minY);

        			for (int var4 = 0; var4 <= 4; ++var4)
        			{
        				for (int var5 = 0; var5 <= 4; ++var5)
        				{
        					if ((var4 < 1 || var5 < 1 || var4 > 3 || var5 > 3) && this.theHost.worldObj.doesBlockHaveSolidTopSurface(var1 + var4, var3 - 1, var2 + var5) && !this.theHost.worldObj.isBlockNormalCube(var1 + var4, var3, var2 + var5) && !this.theHost.worldObj.isBlockNormalCube(var1 + var4, var3 + 1, var2 + var5))
        					{
        						this.theHost.setLocationAndAngles((double)((float)(var1 + var4) + 0.5F), (double)var3, (double)((float)(var2 + var5) + 0.5F), this.theHost.rotationYaw, this.theHost.rotationPitch);
        						this.theHost.getNavigator().clearPathEntity();
        						return;
        					}
        				}
        			}
        		}
        	}
        }
    }
}