package SAO.mods.client;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.monster.EntityMob;

import org.lwjgl.opengl.GL11;

import SAO.mods.entity.ISAOMob;

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
    protected void preRenderCallback(EntityLiving par1EntityLiving, float par2)
    {
        this.preRenderScale((ISAOMob)par1EntityLiving, par2);
    }
}