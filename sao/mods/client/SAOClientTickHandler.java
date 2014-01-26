package sao.mods.client;

import java.util.EnumSet;

import sao.mods.SAOMOD;
import sao.mods.client.renderer.RenderHPBar;
import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;

public class SAOClientTickHandler implements ITickHandler
{
    private SAOMOD saoHPBar;
    private boolean noWorld;

    public SAOClientTickHandler(SAOMOD saoHPBar)
    {
        this.saoHPBar = saoHPBar;
    }

    public void tickStart(EnumSet<TickType> type, Object... tickData) {}

    public void tickEnd(EnumSet<TickType> type, Object... tickData)
    {
        if (this.saoHPBar.minecraft.theWorld == null)
        {
        	this.noWorld = true;
        	return;
        }

        if (this.noWorld)
        {
        	this.noWorld = false;
        	String username = this.saoHPBar.minecraft.thePlayer.username;
        	int nameOffset = this.saoHPBar.minecraft.fontRenderer.getStringWidth(username);
        	this.saoHPBar.manager.setBar(new RenderHPBar(nameOffset));
        }

        if (this.saoHPBar.minecraft.currentScreen != null)
        {
        	return;
        }

        if (this.saoHPBar.minecraft.mcProfiler.profilingEnabled)
        {
        	this.saoHPBar.minecraft.mcProfiler.startSection("SAO HPBar");
        	this.update();
        	this.saoHPBar.minecraft.mcProfiler.endSection();
        }
        else
        {
        	this.update();
        }
    }

    private void update()
    {
        if (this.saoHPBar.minecraft.thePlayer != null)
        {
        	this.saoHPBar.manager.checkUpdate(this.saoHPBar.minecraft);
        	this.saoHPBar.manager.render(this.saoHPBar.minecraft);
        }
    }

    public EnumSet<TickType> ticks()
    {
        return EnumSet.of(TickType.RENDER);
    }

    public String getLabel()
    {
        return "SAO HPBar";
    }
}