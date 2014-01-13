package garnet.mods;

import java.util.Comparator;

import net.minecraft.entity.Entity;

public class EntityGarnetAINearestAttackableTargetSorter implements Comparator
{
    private Entity theHost;

    public EntityGarnetAINearestAttackableTargetSorter( Entity par1Entity)
    {
        this.theHost = par1Entity;
    }

    public int compareDistanceSq(Entity par1Entity, Entity par2Entity)
    {
        double var1 = this.theHost.getDistanceSqToEntity(par1Entity);
        double var2 = this.theHost.getDistanceSqToEntity(par2Entity);
        return var1 < var2 ? -1 : (var1 > var2 ? 1 : 0);
    }

    @Override
    public int compare(Object par1Obj, Object par2Obj)
    {
        return this.compareDistanceSq((Entity)par1Obj, (Entity)par2Obj);
    }
}