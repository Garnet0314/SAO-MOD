package garnet.mods;

import java.util.Iterator;
import java.util.List;

import net.minecraft.entity.EntityCreature;
import net.minecraft.util.AxisAlignedBB;

public class EntityGarnetAIHurtByTarget extends EntityGarnetAITarget
{
    private boolean callForHelp;
    private double targetHeight;
    private int revengeTime;

    public EntityGarnetAIHurtByTarget(EntityCreature par1EntityCreature, boolean par2)
    {
        this(par1EntityCreature, 10.0D, par2);
    }

    public EntityGarnetAIHurtByTarget(EntityCreature par1EntityCreature, double par2, boolean par3)
    {
        super(par1EntityCreature, false);
        this.targetHeight = par2;
        this.callForHelp = par3;
        this.setMutexBits(1);
    }

    @Override
    public boolean shouldExecute()
    {
        if (this.taskOwner.func_142015_aE() == this.revengeTime)
        {
        	return false;
        }
        else if (!this.isGarnetSuitableTarget(this.taskOwner.getAITarget(), false))
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
        this.taskOwner.setAttackTarget(this.taskOwner.getAITarget());
        this.revengeTime = this.taskOwner.func_142015_aE();

        if (this.callForHelp)
        {
        	double var1 = this.getTargetDistance();
        	List var2 = this.taskOwner.worldObj.getEntitiesWithinAABB(this.taskOwner.getClass(), AxisAlignedBB.getAABBPool().getAABB(this.taskOwner.posX, this.taskOwner.posY, this.taskOwner.posZ, this.taskOwner.posX + 1.0D, this.taskOwner.posY + 1.0D, this.taskOwner.posZ + 1.0D).expand(var1, this.targetHeight, var1));
        	Iterator var3 = var2.iterator();

        	while (var3.hasNext())
        	{
        		EntityCreature var4 = (EntityCreature)var3.next();

        		if (this.taskOwner != var4 && var4.getAttackTarget() == null && !var4.isOnSameTeam(this.taskOwner.getAITarget()))
        		{
        			var4.setAttackTarget(this.taskOwner.getAITarget());
        		}
        	}
        }

        super.startExecuting();
    }
}