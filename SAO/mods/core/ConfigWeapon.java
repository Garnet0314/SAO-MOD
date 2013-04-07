package SAO.mods.core;

import net.minecraftforge.common.Configuration;

public class ConfigWeapon
{
    public static int swordID;
    public static int longSwordID;
    public static int rapierID;
    public static int curvedSwordID;
    public static int spearID;
    public static int axeID;
    public static int shortSwordID;
    public static int maceID;
    public static int clubID;

    public void load(Configuration cfg)
    {
        this.swordID = cfg.getItem("SwordID", 25000).getInt() - 256;
        this.longSwordID = cfg.getItem("LongSwordID", 25100).getInt() - 256;
        this.rapierID = cfg.getItem("RapierID", 25200).getInt() -256;
        this.curvedSwordID = cfg.getItem("CurvedSwordID", 25300).getInt() - 256;
        this.spearID = cfg.getItem("SpearID", 25400).getInt() - 256;
        this.axeID = cfg.getItem("AxeID", 25500).getInt() - 256;
        this.shortSwordID = cfg.getItem("ShortSwordID", 25600).getInt() - 256;
        this.maceID = cfg.getItem("MaceID", 25700).getInt() - 256;
        this.clubID = cfg.getItem("ClubID", 25800).getInt() - 256;
    }
}