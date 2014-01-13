package sao.mods.world;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.Teleporter;
import net.minecraft.world.WorldServer;
import sao.mods.core.ConfigWorld;

public class TeleporterSAO extends Teleporter
{
    public TeleporterSAO(WorldServer par1WorldServer)
    {
        super(par1WorldServer);
    }

    @Override
    public void placeInPortal(Entity par1Entity, double par2, double par4, double par6, float par8)
    {
        int var1 = ConfigWorld.xLocation;
        int var2 = (int)par1Entity.posY;
        int var3 = ConfigWorld.zLocation;
        int var4;
        for(var4 = var2; var4 < 255; var4++)
        {
        	if(par1Entity.worldObj.getBlockId(var1, var4 - 1, var3) != 0) continue;
        	if(par1Entity.worldObj.getBlockId(var1, var4, var3) != 0) continue;
        	if(par1Entity.worldObj.getBlockId(var1, var4 + 1, var3) != 0) continue;
        	par1Entity.setLocationAndAngles(var1, var4, var3, 0, 0);
        	break;
        }

        if (par1Entity instanceof EntityPlayer)
        {
        	EntityPlayer var5 = (EntityPlayer)par1Entity;
        	for (var4 = 0; var4 < var5.inventory.mainInventory.length; var4++)
        	{
        		var5.inventory.mainInventory[var4] = null;
        	}
        	for (var4 = 0; var4 < var5.inventory.armorInventory.length; var4++)
        	{
        		var5.inventory.armorInventory[var1] = null;
        	}
        }
    }
}