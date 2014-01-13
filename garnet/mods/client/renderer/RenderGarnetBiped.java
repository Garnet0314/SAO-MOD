package garnet.mods.client.renderer;

import garnet.mods.entity.passive.EntityGarnetBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

public class RenderGarnetBiped extends RenderBiped
{
    protected float scale;

    public RenderGarnetBiped(ModelBiped par1ModelBiped, float par2)
    {
        this(par1ModelBiped, par2, 200);
    }

    public RenderGarnetBiped(ModelBiped par1ModelBiped, float par2, int par3)
    {
        super(par1ModelBiped, par2);
        this.scale = (float)par3 / 200.0F;
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity par1Entity)
    {
        return this.getThePath((EntityGarnetBase)par1Entity);
    }

    protected ResourceLocation getThePath(EntityGarnetBase par1EntityGarnetBase)
    {
        return new ResourceLocation(par1EntityGarnetBase.getTextureLocal(), par1EntityGarnetBase.getTextureMode());
    }

    protected void preRenderScale(EntityLivingBase par1EntityLivingBase, float par2)
    {
        GL11.glScalef(this.scale, this.scale, this.scale);
    }

    @Override
    protected void preRenderCallback(EntityLivingBase par1EntityLivingBase, float par2)
    {
        this.preRenderScale(par1EntityLivingBase, par2);
    }
}