package sao.mods.core;

import sao.mods.SAOMOD;
import sao.mods.entity.monster.EntityFrenzyBoar;
import sao.mods.entity.monster.EntitySAOZombie;
import sao.mods.entity.passive.EntityKirito;
import sao.mods.entity.projectile.EntitySwordSkill;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class SAOEntity
{
    public static void build(ConfigEntity par1)
    {
        //キリト
        EntityRegistry.registerGlobalEntityID(EntityKirito.class, "Kirito", par1.entityID + 2, 16777215, 0x8888ff);
        EntityRegistry.registerModEntity(EntityKirito.class, "Kirito", par1.entityID + 2, SAOMOD.instance, 80, 3, true);
        LanguageRegistry.instance().addStringLocalization("entity.Kirito.name", "en_US", "Kirito");
        LanguageRegistry.instance().addStringLocalization("entity.Kirito.name", "ja_JP", "キリト");

        //属性ゾンビ
        EntityRegistry.registerGlobalEntityID(EntitySAOZombie.class, "SAOZombie", par1.entityID, 16777215, 0x88ff88);
        EntityRegistry.registerModEntity(EntitySAOZombie.class, "SAOZombie", par1.entityID, SAOMOD.instance, 80, 3, true);
        LanguageRegistry.instance().addStringLocalization("entity.SAOZombie.name", "en_US", "Zombie");
        LanguageRegistry.instance().addStringLocalization("entity.SAOZombie.name", "ja_JP", "ゾンビ");
        //フレンジーボア
        EntityRegistry.registerGlobalEntityID(EntityFrenzyBoar.class, "FrenzyBoar", par1.entityID + 1, 16777215, 0x88ff88);
        EntityRegistry.registerModEntity(EntityFrenzyBoar.class, "FrenzyBoar", par1.entityID + 1, SAOMOD.instance, 80, 3, true);
        LanguageRegistry.instance().addStringLocalization("entity.FrenzyBoar.name", "en_US", "FrenzyBoar");
        LanguageRegistry.instance().addStringLocalization("entity.FrenzyBoar.name", "ja_JP", "フレンジーボア");

        //ソードスキル
        EntityRegistry.registerGlobalEntityID(EntitySwordSkill.class, "Slash", par1.entityID + 3);
        EntityRegistry.registerModEntity(EntitySwordSkill.class, "Slash", par1.entityID + 3, SAOMOD.instance, 80, 3, true);
        LanguageRegistry.instance().addStringLocalization("entity.Slash.name", "en_US", "Slash");
    }
}