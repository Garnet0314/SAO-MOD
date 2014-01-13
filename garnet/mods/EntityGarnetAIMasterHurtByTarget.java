package garnet.mods;

import garnet.mods.EntityGarnetAITarget;
import garnet.mods.entity.IServantGarnet;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;

public class EntityGarnetAIMasterHurtByTarget extends EntityGarnetAITarget
{
    private IServantGarnet theServant;
    private EntityLivingBase theOwnerAttacker;
    private int field_142051_e;

    public EntityGarnetAIMasterHurtByTarget(IServantGarnet par1IServantGarnet)
    {
        super((EntityCreature)par1IServantGarnet, false);
        this.theServant = par1IServantGarnet;
        this.setMutexBits(1);
    }

    @Override
    public boolean shouldExecute()
    {
    	EntityLivingBase var1 = this.theServant.getMaster();
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
        	this.theOwnerAttacker = var1.getAITarget();
        	int var2 = var1.func_142015_aE();
        	return var2 != this.field_142051_e && this.isGarnetSuitableTarget(this.theOwnerAttacker, false);
        }
    }

    @Override
    public void startExecuting()
    {
        this.taskOwner.setAttackTarget(this.theOwnerAttacker);
        EntityLivingBase var1 = this.theServant.getMaster();

        if (var1 != null)
        {
        	this.field_142051_e = var1.func_142015_aE();
        }

        super.startExecuting();
    }
}