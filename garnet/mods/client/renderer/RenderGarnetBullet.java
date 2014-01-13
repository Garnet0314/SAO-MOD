package garnet.mods.client.renderer;

import garnet.mods.entity.projectile.EntityGarnetBullet;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

public class RenderGarnetBullet extends RenderGarnet
{
    @Override
    protected ResourceLocation getPath()
    {
        return new ResourceLocation("garnet", "textures/entities/bullet.png");
    }

    public void renderGarnetBullet(EntityGarnetBullet par1EntityGarnetBullet, double par2, double par4, double par6, float par8, float par9)
    {
        this.bindEntityTexture(par1EntityGarnetBullet);
        GL11.glPushMatrix();
        GL11.glTranslatef((float)par2, (float)par4, (float)par6);
        GL11.glRotatef(par1EntityGarnetBullet.prevRotationYaw + (par1EntityGarnetBullet.rotationYaw - par1EntityGarnetBullet.prevRotationYaw) * par9 - 90.0F, 0.0F, 1.0F, 0.0F);
        GL11.glRotatef(par1EntityGarnetBullet.prevRotationPitch + (par1EntityGarnetBullet.rotationPitch - par1EntityGarnetBullet.prevRotationPitch) * par9, 0.0F, 0.0F, 1.0F);
        Tessellator var10 = Tessellator.instance;
        byte var11 = 0;
        float var12 = 0.0F;
        float var13 = (float)(5 + var11 * 10) / 32.0F;
        float var14 = (float)(0 + var11 * 10) / 32.0F;
        float var15 = (float)(5 + var11 * 10) / 32.0F;
        float var16 = 0.0F;
        float var17 = 0.15625F;
        float var18 = (float)(5 + var11 * 10) / 32.0F;
        float var19 = (float)(10 + var11 * 10) / 32.0F;
        float var20 = 0.05625F * 0.5F;
        GL11.glEnable(GL12.GL_RESCALE_NORMAL);
        float var21 = -par9;//(float)par1EntityMadomagiBullet.arrowShake - par9;

        if (var21 > 0.0F)
        {
            float var22 = -MathHelper.sin(var21 * 3.0F) * var21;
            GL11.glRotatef(var22, 0.0F, 0.0F, 1.0F);
        }

        GL11.glRotatef(45.0F, 1.0F, 0.0F, 0.0F);
        GL11.glScalef(var20, var20, var20);
        GL11.glTranslatef(-4.0F, 0.0F, 0.0F);
        GL11.glNormal3f(var20, 0.0F, 0.0F);
        var10.startDrawingQuads();
        var10.addVertexWithUV(-2.0D, -2.0D, -2.0D, (double)var16, (double)var18);
        var10.addVertexWithUV(-2.0D, -2.0D, 2.0D, (double)var17, (double)var18);
        var10.addVertexWithUV(-2.0D, 2.0D, 2.0D, (double)var17, (double)var19);
        var10.addVertexWithUV(-2.0D, 2.0D, -2.0D, (double)var16, (double)var19);
        var10.draw();
        GL11.glNormal3f(-var20, 0.0F, 0.0F);
        var10.startDrawingQuads();
        var10.addVertexWithUV(-2.0D, 2.0D, -2.0D, (double)var16, (double)var18);
        var10.addVertexWithUV(-2.0D, 2.0D, 2.0D, (double)var17, (double)var18);
        var10.addVertexWithUV(-2.0D, -2.0D, 2.0D, (double)var17, (double)var19);
        var10.addVertexWithUV(-2.0D, -2.0D, -2.0D, (double)var16, (double)var19);
        var10.draw();

        for (int var23 = 0; var23 < 4; ++var23)
        {
            GL11.glRotatef(90.0F, 1.0F, 0.0F, 0.0F);
            GL11.glNormal3f(0.0F, 0.0F, var20);
            var10.startDrawingQuads();
            var10.addVertexWithUV(-2.5D, -2.0D, 0.0D, (double)var12, (double)var14);
            var10.addVertexWithUV(2.5D, -2.0D, 0.0D, (double)var13, (double)var14);
            var10.addVertexWithUV(2.5D, 2.0D, 0.0D, (double)var13, (double)var15);
            var10.addVertexWithUV(-2.5D, 2.0D, 0.0D, (double)var12, (double)var15);
            var10.draw();
        }

        GL11.glDisable(GL12.GL_RESCALE_NORMAL);
        GL11.glPopMatrix();
    }

    @Override
    public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9)
    {
        this.renderGarnetBullet((EntityGarnetBullet)par1Entity, par2, par4, par6, par8, par9);
    }
}