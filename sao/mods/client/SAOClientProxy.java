package sao.mods.client;

import garnet.mods.client.renderer.RenderGarnetBiped;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelZombie;
import net.minecraft.entity.player.EntityPlayer;
import sao.mods.client.gui.GuiSkill;
import sao.mods.client.model.ModelFrenzyBoar;
import sao.mods.client.renderer.RenderBlockUnbreakable;
import sao.mods.client.renderer.RenderSAOMob;
import sao.mods.client.renderer.RenderSlash;
import sao.mods.core.SAOCommonProxy;
import sao.mods.core.SAOKeyHandler;
import sao.mods.entity.EntitySwordSkill;
import sao.mods.entity.monster.EntityFrenzyBoar;
import sao.mods.entity.monster.EntitySAOZombie;
import sao.mods.entity.passive.EntityKirito;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.KeyBindingRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class SAOClientProxy implements SAOCommonProxy
{
    @Override
    public int addArmor(String par1Str)
    {
        return RenderingRegistry.addNewArmourRendererPrefix(par1Str);
    }

    @Override
    public void registerRenderers()
    {
        RenderingRegistry.registerEntityRenderingHandler(EntitySwordSkill.class, new RenderSlash());
        RenderingRegistry.registerEntityRenderingHandler(EntityKirito.class, new RenderGarnetBiped(new ModelBiped(0.0F, 0.0F, 64, 64), 0.6F));
        RenderingRegistry.registerEntityRenderingHandler(EntitySAOZombie.class, new RenderSAOMob(new ModelZombie(), 0.6F));
        RenderingRegistry.registerEntityRenderingHandler(EntityFrenzyBoar.class, new RenderSAOMob(new ModelFrenzyBoar(), 1.0F));

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