package SAO.mods.core;

import java.util.EnumSet;

import net.minecraft.world.World;
import SAO.mods.building.WorldGenPalace;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;

public class SAOServerTickHandler implements ITickHandler
{
    private World theWorld;
    private boolean dataRead;

    @Override
    public void tickStart(EnumSet<TickType> type, Object... tickData) {}

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
        //いずれSAO用のディメンションへ変更
        this.theWorld = FMLCommonHandler.instance().getMinecraftServerInstance().worldServerForDimension(-1);
        if(this.theWorld != null)
        {
        	if(!this.dataRead)
        	{
        		SAOSaveFile.readData();
        		this.dataRead = true;
        	}

        	if(SAOSaveFile.createdPalace == 0 && !ConfigBuilding.PalaceSpawning && this.dataRead)
        	{
        		if(ConfigBuilding.xLocation == 0 && ConfigBuilding.yLocation == 0 && ConfigBuilding.zLocation == 0)
        		{
        			WorldGenPalace var1 = new WorldGenPalace(this.theWorld, this.theWorld.getWorldInfo().getSpawnX() / 8, this.theWorld.getWorldInfo().getSpawnY(), this.theWorld.getWorldInfo().getSpawnZ() / 8);
        			var1.CreatePalace();
        		}
        		else
        		{
        			WorldGenPalace var1 = new WorldGenPalace(this.theWorld, ConfigBuilding.xLocation, ConfigBuilding.yLocation, ConfigBuilding.zLocation);
        			var1.CreatePalace();
        		}
        		SAOSaveFile.createdPalace = 1;
        		SAOSaveFile.writeData();
        	}
        }
    }
}