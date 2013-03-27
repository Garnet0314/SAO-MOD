package SAO.mods.entity;

import net.minecraft.entity.EntityLiving;

public interface ISAOMob
{
    int getSlashResist();
    int getThrustResist();
    int getStrikeResist();
    int getPenetrateResist();

    int getQuakeResist();
    int getWaterResist();
    int getFireResist();
    int getAeroResist();
    int getShineResist();
    int getDarkResist();
}