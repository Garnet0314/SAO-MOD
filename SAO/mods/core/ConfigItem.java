package SAO.mods.core;

import net.minecraftforge.common.Configuration;

public class ConfigItem
{
    public static int saoIemID;
    public static int money100ID;
    public static int money500ID;
    public static int money1000ID;

    public void load(Configuration cfg)
    {
        this.saoIemID= cfg.getItem("SAOItemID", 24000).getInt() - 256;
        this.money100ID = cfg.getItem("money100ID", 27000).getInt();
        this.money500ID = cfg.getItem("money500ID", 27001).getInt();
        this.money1000ID = cfg.getItem("money1000ID", 27002).getInt();
    }
}