package garnet.mods;

import garnet.mods.entity.passive.EntityGarnetTameable;
import net.minecraft.entity.EntityLivingBase;

public class EntityGarnetAIOwnerHurtTarget extends EntityGarnetAITarget
{
    private EntityGarnetTameable theHost;
    private EntityLivingBase theTarget;
    private int field_142050_e;

    public EntityGarnetAIOwnerHurtTarget(EntityGarnetTameable par1EntityGarnetBase)
    {
        super(par1EntityGarnetBase, false);
        this.theHost = par1EntityGarnetBase;
        this.setMutexBits(1);
    }

    @Override
    public boolean shouldExecute()
    {
        if (!this.theHost.isEnable())
        {
            return false;
        }
        else
        {
        	EntityLivingBase var1 = this.theHost.getOwner();

        	if (var1 == null)
        	{
        		return false;
        	}
        	else
        	{
        		this.theTarget = var1.getLastAttacker();
        		int var2 = var1.getLastAttackerTime();
        		return var2 != this.field_142050_e && this.isGarnetSuitableTarget(this.theTarget, false);
        	}
        }
    }

    @Override
    public void startExecuting()
    {
        this.taskOwner.setAttackTarget(this.theTarget);
        EntityLivingBase var1 = this.theHost.getOwner();

        if (var1 != null)
        {
            this.field_142050_e = var1.getLastAttackerTime();
        }

        super.startExecuting();
    }
}