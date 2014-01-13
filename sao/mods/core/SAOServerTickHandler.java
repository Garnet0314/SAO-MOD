package sao.mods.core;

import java.util.EnumSet;

import net.minecraft.world.World;
import sao.mods.inventory.InventorySkillHelper;
import sao.mods.world.gen.WorldGenPalace;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;

public class SAOServerTickHandler implements ITickHandler
{
    private World theWorld;
    private boolean dataRead;

    @Override
    public void tickStart(EnumSet<TickType> type, Object... tickData)
    {
        InventorySkillHelper.unloadCount();
    }

    @Override
    public void tickEnd(EnumSet<TickType> type, Object... tickData)
    {
        if(type.equals(EnumSet.of(TickType.SERVER)))
        {
        	onTickInGame();
        }
    }

    @Override
    public EnumSet ticks()
    {
        return EnumSet.of(TickType.SERVER);
    }

    @Override
    public String getLabel()
    {
        return null;
    }

    public void onTickInGame()
    {
        this.theWorld = FMLCommonHandler.instance().getMinecraftServerInstance().worldServerForDimension(ConfigWorld.aincradDimensionID);
        if(this.theWorld != null)
        {
        	if(!this.dataRead)
        	{
        		SAOSaveFile.readData();
        		this.dataRead = true;
        	}

        	if(SAOSaveFile.createdPalace == 0 && !ConfigWorld.PalaceSpawning && this.dataRead)
        	{
        		if(ConfigWorld.xLocation == 0 && ConfigWorld.yLocation == 0 && ConfigWorld.zLocation == 0)
        		{
        			WorldGenPalace var1 = new WorldGenPalace(this.theWorld, 0, this.theWorld.getWorldInfo().getSpawnY(), 0);
        			var1.CreatePalace();
        		}
        		else
        		{
        			WorldGenPalace var1 = new WorldGenPalace(this.theWorld, ConfigWorld.xLocation, ConfigWorld.yLocation, ConfigWorld.zLocation);
        			var1.CreatePalace();
        		}
        		SAOSaveFile.createdPalace = 1;
        		SAOSaveFile.writeData();
        	}
        }
    }
}