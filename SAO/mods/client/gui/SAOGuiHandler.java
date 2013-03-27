package SAO.mods.client.gui;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryEnderChest;
import net.minecraft.tileentity.TileEntityEnderChest;
import net.minecraft.world.World;
import SAO.mods.SAOMOD;
import SAO.mods.inventory.ContainerSkill;
import cpw.mods.fml.common.network.IGuiHandler;

public class SAOGuiHandler implements IGuiHandler
{
    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
    {
        if (ID == SAOMOD.guiSkill)
        {
        	return new GuiSkill(player.inventory, player.getInventoryEnderChest());
        }
        return null;
    }

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
    {
        if (ID == SAOMOD.guiSkill)
        {
        	return new ContainerSkill(player.inventory, player.getInventoryEnderChest());
        }
        return null;
    }
}