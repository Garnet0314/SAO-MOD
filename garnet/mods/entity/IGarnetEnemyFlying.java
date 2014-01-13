package garnet.mods.entity;

import garnet.mods.FilterIGarnetEnemyFlying;
import net.minecraft.command.IEntitySelector;

public interface IGarnetEnemyFlying
{
    IEntitySelector garnetEnemySelector = new FilterIGarnetEnemyFlying();
}