package garnet.mods;

import garnet.mods.entity.passive.EntityGarnetTameable;
import net.minecraft.entity.ai.EntityAIBase;

public class EntityGarnetAIStandby extends EntityAIBase
{
    private EntityGarnetTameable theHost;

    public EntityGarnetAIStandby(EntityGarnetTameable par1EntityGarnetBase)
    {
        theHost = par1EntityGarnetBase;
        setMutexBits(5);
    }

    @Override
    public boolean shouldExecute()
    {
        if(!this.theHost.isEnable())
        {
        	return true;
        }
        else if (!theHost.isStandbyMode())
        {
        	return false;
        }
        else if (theHost.isInWater())
        {
        	return false;
        }
        else if (!theHost.onGround)
        {
        	return false;
        }
        else
        {
        	return true;
        }
    }

    @Override
    public void startExecuting()
    {
        this.theHost.getNavigator().clearPathEntity();
    }
}