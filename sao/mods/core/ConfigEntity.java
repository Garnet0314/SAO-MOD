package sao.mods.core;

import net.minecraftforge.common.Configuration;

public class ConfigEntity
{
    public static int entityID;

    public void load(Configuration cfg)
    {
        this.entityID = cfg.get("entity", "entityID", 1000).getInt();
    }
}