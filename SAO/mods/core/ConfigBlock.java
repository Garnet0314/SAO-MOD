package SAO.mods.core;

import net.minecraftforge.common.Configuration;

public class ConfigBlock
{
    //Unbreakable Block, default 3701-3858
    public static int blockUB;
    //Dummy Block, default 3901-4058
    public static int blockDm;
	public static String bossSpawnerDir = "BossSpawnerData";

	public static int blockBossSpawner;

	public void load(Configuration cfg)
    {
        this.blockUB = cfg.getBlock("BlockUB", 3700).getInt();
        this.blockDm = cfg.getBlock("BlockDm", 3900).getInt();
        this.blockBossSpawner = cfg.getBlock("BlockBossSpawner", 3699).getInt();
    }
}