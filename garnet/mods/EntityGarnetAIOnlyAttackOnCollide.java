package garnet.mods;

import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;

public class EntityGarnetAIOnlyAttackOnCollide extends EntityAIBase
{
    private EntityCreature theHost;
    private EntityLivingBase theTarget;
    private int attackTick;
    private int nextAttackTime;
    private float attackRange;

    public EntityGarnetAIOnlyAttackOnCollide(EntityCreature par1EntityCreature)
    {
        this(par1EntityCreature, 20, 0.25F);
    }

    public EntityGarnetAIOnlyAttackOnCollide(EntityCreature par1EntityCreature, int par2, float par3)
    {
        this.theHost = par1EntityCreature;
        this.attackTick = 0;
        this.nextAttackTime = par2;
        this.attackRange = par3;
        this.setMutexBits(3);
    }

    @Override
    public boolean shouldExecute()
    {
        EntityLivingBase var1 = this.theHost.getAttackTarget();
        this.attackTick = Math.max(this.attackTick - 1, 0);

        if (var1 == null)
        {
        	return false;
        }
        else if (!var1.isEntityAlive())
        {
        	return false;
        }
        else if (this.attackTick <= 0)
        {
        	double var3 = (double)(this.theHost.width + var1.width + this.attackRange);
        	if (this.theHost.getDistanceSq(var1.posX, var1.boundingBox.minY, var1.posZ) <= var3 * var3)
        	{
        		this.theTarget = var1;
        		return true;
        	}
        }
        return false;
    }

    @Override
    public boolean continueExecuting()
    {
        return false;
    }

    @Override
    public void startExecuting()
    {
        this.theHost.attackEntityAsMob(this.theTarget);
        if (this.theHost.getHeldItem() != null)
        {
        	this.theHost.swingItem();
        }
    }

    @Override
    public void resetTask()
    {
        this.attackTick = this.nextAttackTime;
    }
}