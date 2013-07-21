package sao.mods.core;

import sao.mods.SAOMOD;
import sao.mods.entity.EntitySAOZombie;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class SAOEntity
{
    public static void build(ConfigEntity par1)
    {
        EntityRegistry.registerGlobalEntityID(EntitySAOZombie.class, "SAOZombie", par1.entityID, 16777215, 0x88ff88);
        EntityRegistry.registerModEntity(EntitySAOZombie.class, "SAOZombie", par1.entityID, SAOMOD.instance, 80, 3, true);
        LanguageRegistry.instance().addStringLocalization("entity.SAOZombie.name", "en_US", "Zombie");
        LanguageRegistry.instance().addStringLocalization("entity.SAOZombie.name", "ja_JP", "ゾンビ");
    }
}