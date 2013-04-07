package SAO.mods.client;

import net.minecraft.client.model.ModelZombie;
import net.minecraft.entity.player.EntityPlayer;
import SAO.mods.client.gui.GuiSkill;
import SAO.mods.core.SAOKeyHandler;
import SAO.mods.core.SAOServerProxy;
import SAO.mods.entity.EntitySAOZombie;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.KeyBindingRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class SAOClientProxy extends SAOServerProxy
{
    @Override
    public int addArmor(String par1Str)
    {
        return RenderingRegistry.addNewArmourRendererPrefix(par1Str);
    }

    @Override
    public void registerRenderers()
    {
        RenderingRegistry.registerEntityRenderingHandler(EntitySAOZombie.class, new RenderSAOMob(new ModelZombie(), 0.6F));
        RenderingRegistry.registerBlockHandler(new RenderBlockUnbreakable());
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