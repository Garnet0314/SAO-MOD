package garnet.mods;

import garnet.mods.entity.IGarnetEnemyFlying;
import net.minecraft.command.IEntitySelector;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityGhast;

public class FilterIGarnetEnemyFlying implements IEntitySelector
{
    @Override
    public boolean isEntityApplicable(Entity par1Entity)
    {
        return par1Entity instanceof IGarnetEnemyFlying;
    }
}