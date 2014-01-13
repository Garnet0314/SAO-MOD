package garnet.mods;

import garnet.mods.entity.projectile.EntityGarnetBullet;

import java.util.logging.Level;

import net.minecraftforge.common.Configuration;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.EntityRegistry;

@Mod(modid = "Garnet-MOD", name = "mod_Garnet", version = "1.6.4.082")

@NetworkMod(clientSideRequired = true, serverSideRequired = false, channels = {"Garnet", "GarnetGun"}, packetHandler = PacketHandler.class)

public class mod_Garnet
{
    @SidedProxy(clientSide = "garnet.mods.client.GarnetClientProxy", serverSide = "garnet.mods.CommonProxy")
    public static CommonProxy proxy;

    @Instance("Garnet-MOD")
    public static mod_Garnet instance;

    private static int BulletID;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        Configuration cfg = new Configuration(event.getSuggestedConfigurationFile());
        try
        {
        	cfg.load();
        	//System
        	//Entity
        	this.BulletID = cfg.get("entity", "entityBulletID", 233).getInt();
        }
        catch (Exception e)
        {
        	FMLLog.log(Level.SEVERE, e, "Error Message");
        }
        finally
        {
        	cfg.save();
        }
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        this.loadItems();
        this.loadRecipes();
        this.loadEntities();
        this.proxy.registerRenderers();
    }

    private void loadItems() {}

    private void loadRecipes() {}

    private void loadEntities()
    {
        EntityRegistry.registerGlobalEntityID(EntityGarnetBullet.class, "GarnetBullet", this.BulletID);
        EntityRegistry.registerModEntity(EntityGarnetBullet.class, "GarnetBullet", this.BulletID, this, 64, 10, true);
    }
}