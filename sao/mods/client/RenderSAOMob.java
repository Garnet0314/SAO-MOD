package sao.mods.client;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import sao.mods.entity.ISAOMob;

public class RenderSAOMob extends RenderLiving
{
    private float scale;

    public RenderSAOMob(ModelBase par1ModelBase, float par2)
    {
        super(par1ModelBase, par2);
    }

    protected void preRenderScale(ISAOMob par1ISAOMob, float par2)
    {
        GL11.glScalef(par1ISAOMob.getSize(), par1ISAOMob.getSize(), par1ISAOMob.getSize());
    }

    @Override
    protected void preRenderCallback(EntityLivingBase par1EntityLivingBase, float par2)
    {
        this.preRenderScale((ISAOMob)par1EntityLivingBase, par2);
    }

    @Override
    protected ResourceLocation func_110775_a(Entity par1Entity)
    {
        return this.getThePath((ISAOMob)par1Entity);
    }

    protected ResourceLocation getThePath(ISAOMob par1ISAOMob)
    {
        return new ResourceLocation(par1ISAOMob.getTexture());
    }
}