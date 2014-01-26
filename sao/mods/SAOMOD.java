package sao.mods;

import java.util.logging.Level;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.MinecraftForge;
import sao.mods.client.SAOClientTickHandler;
import sao.mods.client.gui.SAOGuiHandler;
import sao.mods.core.ConfigBlock;
import sao.mods.core.ConfigEntity;
import sao.mods.core.ConfigItem;
import sao.mods.core.ConfigWeapon;
import sao.mods.core.ConfigWorld;
import sao.mods.core.SAOBlock;
import sao.mods.core.SAOCommonProxy;
import sao.mods.core.SAOCreativeTab;
import sao.mods.core.SAOEntity;
import sao.mods.core.SAOEventHookContainer;
import sao.mods.core.SAOHPBarManager;
import sao.mods.core.SAOItem;
import sao.mods.core.SAOServerTickHandler;
import sao.mods.core.SAOWeapon;
import sao.mods.core.SAOWorld;
import sao.mods.inventory.InventorySkillHelper;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStoppingEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;

/**
 * メインクラス
 *
 * @author garnet
 *
 */
@Mod(modid = "SAO-MOD", name = "SAO", version = "1.6.4.000")
@NetworkMod(clientSideRequired = true, serverSideRequired = true)
public class SAOMOD
{
    @SidedProxy(clientSide = "sao.mods.client.SAOClientProxy", serverSide = "sao.mods.core.SAOServerProxy")
    public static SAOCommonProxy proxy;

    @Instance("SAO-MOD")
    public static SAOMOD instance;

    private ConfigWeapon configWeapon = new ConfigWeapon();
    private ConfigBlock configBlock = new ConfigBlock();
    private ConfigItem configItem = new ConfigItem();
    private ConfigWorld configWorld = new ConfigWorld();
    private ConfigEntity configEntity = new ConfigEntity();

    public final static CreativeTabs saoTabs = new SAOCreativeTab("SAO");
    public final static CreativeTabs saoBlocks = new SAOCreativeTab("Block");
    public final static CreativeTabs saoWeapons = new SAOCreativeTab("Weapon");

    private KeyBinding keyBinding;

    public final static int guiSkill = 0;

    public SAOHPBarManager manager = new SAOHPBarManager();
    public final Minecraft minecraft = Minecraft.getMinecraft();

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        Configuration cfg = new Configuration(event.getSuggestedConfigurationFile());
        try
        {
        	cfg.load();
        	this.configWeapon.preLoad(cfg);
        	this.configBlock.preLoad(cfg);
        	this.configItem.preLoad(cfg);
        	this.configWorld.preLoad(cfg);
        	this.configEntity.preLoad(cfg);
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
        SAOWeapon.build(this.configWeapon);
        SAOBlock.build(this.configBlock);
        SAOItem.build(this.configItem);
        SAOItem.addRecipe();
        SAOWorld.load(this.configWorld);
        SAOEntity.build(this.configEntity);
        NetworkRegistry.instance().registerGuiHandler(this, new SAOGuiHandler());

        TickRegistry.registerTickHandler(new SAOClientTickHandler(this), Side.CLIENT);
        TickRegistry.registerTickHandler(new SAOServerTickHandler(), Side.SERVER);
        MinecraftForge.EVENT_BUS.register(new SAOEventHookContainer());
        this.proxy.registerRenderers();
        this.proxy.registerKeyBinds();
    }

    @EventHandler
    public void handleServerStopping(FMLServerStoppingEvent event)
    {
        InventorySkillHelper.unloadAll();
    }
}