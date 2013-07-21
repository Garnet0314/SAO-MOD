package sao.mods;

import java.util.logging.Level;

import net.minecraft.client.settings.KeyBinding;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.MinecraftForge;
import sao.mods.client.gui.SAOGuiHandler;
import sao.mods.core.ConfigBlock;
import sao.mods.core.ConfigBuilding;
import sao.mods.core.ConfigEntity;
import sao.mods.core.ConfigItem;
import sao.mods.core.ConfigWeapon;
import sao.mods.core.SAOBlock;
import sao.mods.core.SAOCommonProxy;
import sao.mods.core.SAOCreativeTab;
import sao.mods.core.SAOEntity;
import sao.mods.core.SAOEventHookContainer;
import sao.mods.core.SAOItem;
import sao.mods.core.SAOServerTickHandler;
import sao.mods.core.SAOWeapon;
import sao.mods.inventory.InventorySkillHelper;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.ServerStopping;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStoppingEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;

@Mod(modid = "SAO-MOD", name = "SAO", version = "1.6.2.000")
@NetworkMod(clientSideRequired = true, serverSideRequired = true)
public class SAOMOD
{
    @SidedProxy(clientSide = "sao.mods.client.SAOClientProxy", serverSide = "sao.mods.core.SAOServerProxy")
    public static SAOCommonProxy proxy;

    @Mod.Instance("SAO-MOD")
    public static SAOMOD instance;

    private ConfigWeapon configWeapon = new ConfigWeapon();
    private ConfigBlock configBlock = new ConfigBlock();
    private ConfigItem configItem = new ConfigItem();
    private ConfigBuilding configBuilding = new ConfigBuilding();
    private ConfigEntity configEntity = new ConfigEntity();

    public final static CreativeTabs saoTabs = new SAOCreativeTab("SAO");
    public final static CreativeTabs saoBlocks = new SAOCreativeTab("Block");
    public final static CreativeTabs saoWeapons = new SAOCreativeTab("Weapon");

    private KeyBinding keyBinding;

    public final static int guiSkill = 0;

    @Mod.EventHandler
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
        	this.configEntity.load(cfg);
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

    @Mod.EventHandler
    public void init(FMLInitializationEvent event)
    {
        SAOWeapon.build(this.configWeapon);
        SAOBlock.build(this.configBlock);
        SAOItem.build(this.configItem);
        SAOItem.addRecipe();
        SAOEntity.build(this.configEntity);
        NetworkRegistry.instance().registerGuiHandler(this, new SAOGuiHandler());
        TickRegistry.registerTickHandler(new SAOServerTickHandler(), Side.SERVER);
        MinecraftForge.EVENT_BUS.register(new SAOEventHookContainer());
        this.proxy.registerRenderers();
        this.proxy.registerKeyBinds();
    }

    @Mod.EventHandler
    public void handleServerStopping(FMLServerStoppingEvent event)
    {
        InventorySkillHelper.unloadAll();
    }
}