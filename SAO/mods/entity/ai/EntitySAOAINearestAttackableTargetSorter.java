package SAO.mods.entity.ai;

import java.util.Comparator;

import net.minecraft.entity.Entity;

public class EntitySAOAINearestAttackableTargetSorter implements Comparator
{
    private Entity theHost;
    private final EntitySAOAINearestAttackableTarget parent;

    public EntitySAOAINearestAttackableTargetSorter(EntitySAOAINearestAttackableTarget par1EntitySAOAINearestAttackableTarget, Entity par2Entity)
    {
        this.parent = par1EntitySAOAINearestAttackableTarget;
        this.theHost = par2Entity;
    }

    public int compareDistanceSq(Entity par1Entity, Entity par2Entity)
    {
        double var3 = this.theHost.getDistanceSqToEntity(par1Entity);
        double var5 = this.theHost.getDistanceSqToEntity(par2Entity);
        return var3 < var5 ? -1 : (var3 > var5 ? 1 : 0);
    }

    @Override
    public int compare(Object par1Obj, Object par2Obj)
    {
        return this.compareDistanceSq((Entity)par1Obj, (Entity)par2Obj);
    }
}