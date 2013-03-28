package SAO.mods.client;

import net.minecraft.entity.player.EntityPlayer;
import SAO.mods.client.gui.GuiSkill;
import SAO.mods.core.SAOKeyHandler;
import SAO.mods.core.SAOServerProxy;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.KeyBindingRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class SAOClientProxy extends SAOServerProxy
{
    @Override
    public void registerRenderers()
    {
    }

    @Override
    public void registerKeyBinds()
    {
        KeyBindingRegistry.registerKeyBinding(new SAOKeyHandler());
    }

    @Override
    public void openGui(EntityPlayer par1EntityPlayer)
    {
        FMLClientHandler.instance().displayGuiScreen(par1EntityPlayer, GuiSkill.getInstance(par1EntityPlayer));
    }
}