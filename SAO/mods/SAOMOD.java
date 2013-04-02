package SAO.mods;

import java.util.logging.Level;

import net.minecraft.client.settings.KeyBinding;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.MinecraftForge;
import SAO.mods.client.gui.SAOGuiHandler;
import SAO.mods.core.BuildSAOBlock;
import SAO.mods.core.BuildSAOItem;
import SAO.mods.core.BuildSAOWeapon;
import SAO.mods.core.ConfigBlock;
import SAO.mods.core.ConfigBuilding;
import SAO.mods.core.ConfigItem;
import SAO.mods.core.ConfigWeapon;
import SAO.mods.core.SAOCreativeTab;
import SAO.mods.core.SAOEventHookContainer;
import SAO.mods.core.SAOServerProxy;
import SAO.mods.core.SAOServerTickHandler;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;

@Mod(modid = "SAO-MOD", name = "SAO", version = "1.5.1.000")

@NetworkMod(clientSideRequired = true, serverSideRequired = false)
public class SAOMOD
{
    @SidedProxy(clientSide = "SAO.mods.client.SAOClientProxy", serverSide = "SAO.mods.core.SAOServerProxy")
    public static SAOServerProxy proxy;

    @Mod.Instance("SAO")
    public static SAOMOD instance;

    private ConfigWeapon configWeapon = new ConfigWeapon();
    private ConfigBlock configBlock = new ConfigBlock();
    private ConfigItem configItem = new ConfigItem();
    private ConfigBuilding configBuilding = new ConfigBuilding();

    public final static CreativeTabs saoTabs = new SAOCreativeTab("SAO");

    private KeyBinding keyBinding;

    public final static int guiSkill = 0;

    @Mod.PreInit

    public void preInit(FMLPreInitializationEvent event)
    {
        Configuration cfg = new Configuration(event.getSuggestedConfigurationFile());
        try
        {
        	cfg.load();
        	this.configWeapon.load(cfg);
        	this.configBlock.load(cfg);
        	this.configItem.load(cfg);
        	this.configBuilding.load(cfg);
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

    @Mod.Init
    public void init(FMLInitializationEvent event)
    {
        BuildSAOWeapon.build(this.configWeapon);
        BuildSAOBlock.build(this.configBlock);
        BuildSAOItem.build(this.configItem);
        BuildSAOItem.addRecipe();
        NetworkRegistry.instance().registerGuiHandler(this, new SAOGuiHandler());
        TickRegistry.registerTickHandler(new SAOServerTickHandler(), Side.SERVER);
        MinecraftForge.EVENT_BUS.register(new SAOEventHookContainer());
        this.proxy.registerRenderers();
        this.proxy.registerKeyBinds();
    }
}