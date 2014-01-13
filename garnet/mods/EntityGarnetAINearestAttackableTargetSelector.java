package garnet.mods;

import net.minecraft.command.IEntitySelector;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;

class EntityGarnetAINearestAttackableTargetSelector implements IEntitySelector
{
    final IEntitySelector field_111103_c;

    final EntityGarnetAINearestAttackableTarget field_111102_d;

    EntityGarnetAINearestAttackableTargetSelector(EntityGarnetAINearestAttackableTarget par1EntityGarnetAINearestAttackableTarget, IEntitySelector par2IEntitySelector)
    {
        this.field_111102_d = par1EntityGarnetAINearestAttackableTarget;
        this.field_111103_c = par2IEntitySelector;
    }

    public boolean isEntityApplicable(Entity par1Entity)
    {
        return !(par1Entity instanceof EntityLivingBase) ? false : (this.field_111103_c != null && !this.field_111103_c.isEntityApplicable(par1Entity) ? false : this.field_111102_d.isGarnetSuitableTarget((EntityLivingBase)par1Entity, false));
    }
}