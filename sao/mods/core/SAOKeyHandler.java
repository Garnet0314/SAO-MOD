package sao.mods.core;

import java.util.EnumSet;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.settings.KeyBinding;

import org.lwjgl.input.Keyboard;

import sao.mods.SAOMOD;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.KeyBindingRegistry.KeyHandler;
import cpw.mods.fml.common.TickType;

public class SAOKeyHandler extends KeyHandler
{
    private static KeyBinding[] keyBindings = new KeyBinding[] {new KeyBinding("key.skill", Keyboard.KEY_R)};
    private static boolean[] booleans = new boolean[] {false};
    private EnumSet<TickType> tickTypes = EnumSet.of(TickType.CLIENT);

    public SAOKeyHandler()
    {
        super(keyBindings, booleans);
    }

    @Override
    public String getLabel()
    {
        return "SAO";
    }

    @Override
    public void keyDown(EnumSet<TickType> types, KeyBinding kb, boolean tickEnd, boolean isRepeat)
    {
        if(kb == keyBindings[0])
        {
        	Minecraft mc = FMLClientHandler.instance().getClient();
        	GuiScreen screen = mc.currentScreen;
        	if(screen == null)
        	{
        		SAOMOD.proxy.openGui(mc.thePlayer);
        	}
        }
    }

    @Override
    public void keyUp(EnumSet<TickType> types, KeyBinding kb, boolean tickEnd)
    {
    }

    @Override
    public EnumSet<TickType> ticks()
    {
        return this.tickTypes;
    }
}