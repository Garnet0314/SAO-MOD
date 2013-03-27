package SAO.mods.core;

import net.minecraftforge.common.Configuration;

public class ConfigWeapon
{
    public static int smallSwordID;

    public void load(Configuration cfg)
    {
        this.smallSwordID = cfg.getItem("smallSwordID", 25000).getInt();
    }
}