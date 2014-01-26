package sao.mods.client.renderer;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import sao.mods.entity.projectile.EntitySwordSkill;

public class RenderSlash extends Render
{
    private static final ResourceLocation slash = new ResourceLocation("sao", "textures/effects/swordSkill.png");
    private static final ResourceLocation thrust= new ResourceLocation("sao", "textures/effects/Laser.png");
    private float colorR[] = {  56F/255F, 224F/255F,   0F/255F, 224F/255F,   0F/255F, 224F/255F,   0F/255F};
    private float colorG[] = {  56F/255F, 224F/255F,   0F/255F,   0F/255F, 224F/255F, 224F/255F,   0F/255F};
    private float colorB[] = { 224F/255F,   0F/255F, 224F/255F,   0F/255F,   0F/255F, 224F/255F,   0F/255F};

    //TODO 打撃と貫通が未実装
    public void doRenderSlash(EntitySwordSkill par1EntitySwordSkill, double par2, double par3, double par4, float par5, float par6)
    {
        int color = par1EntitySwordSkill.getType();
        int element = par1EntitySwordSkill.getElement();
        this.bindEntityTexture(par1EntitySwordSkill);
        GL11.glPushMatrix();
        GL11.glTranslatef((float)par2, (float)par3, (float)par4);
        GL11.glDisable(GL11.GL_LIGHTING);
        GL11.glEnable(GL11.GL_NORMALIZE);
        GL11.glEnable(GL11.GL_BLEND);
        if (element % 7 == 6)
        {
        	GL11.glBlendFunc(GL11.GL_ZERO, GL11.GL_ONE_MINUS_SRC_COLOR);
        	//GL11.glBlendFunc(GL11.GL_ZERO, GL11.GL_DST_COLOR);
        }
        else
        {
        	GL11.glBlendFunc(GL11.GL_ONE, GL11.GL_ONE_MINUS_SRC_COLOR);
        	GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE);
        }
        float size = par1EntitySwordSkill.getSkillSize();
        float size2;
        Tessellator tessellator = Tessellator.instance;

        if(par1EntitySwordSkill.getCount() <= 0)
        {
        	int delayCount = -par1EntitySwordSkill.getCount();
        	if(delayCount > 10)
        	{
        		delayCount = 10;
        	}
        	size2 = delayCount * 0.3F * size;
        	if(size2 > 1.0F)
        	{
        		size2 = 1.0F;
        	}
        	GL11.glRotatef(180F - this.renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
        	GL11.glRotatef(-this.renderManager.playerViewX, 1.0F, 0.0F, 0.0F);
        	this.renderLightEffect(tessellator, element % 7, size2, par1EntitySwordSkill);
        }
        //斬撃の描画
        else if (color == 0)
        {
        	size2 = size * 2.0F;
        	GL11.glScalef(size2, size2, size2);
        	GL11.glRotatef(par1EntitySwordSkill.prevRotationYaw + (par1EntitySwordSkill.rotationYaw - par1EntitySwordSkill.prevRotationYaw) * par6 - 90.0F, 0.0F, 1.0F, 0.0F);
        	GL11.glRotatef(par1EntitySwordSkill.prevRotationPitch + (par1EntitySwordSkill.rotationPitch - par1EntitySwordSkill.prevRotationPitch) * par6, 0.0F, 0.0F, 1.0F);
        	GL11.glRotatef(par1EntitySwordSkill.getAngleZ(), 1.0F, 0.0F, 0.0F);
        	this.renderSlash(tessellator, element);
        }
        //刺突の描画
        else if (color == 1)
        {
        	GL11.glRotatef(par5, 0.0F, 1.0F, 0.0F);
        	GL11.glRotatef(-par1EntitySwordSkill.rotationPitch, 1.0F, 0.0F, 0.0F);

        	double centerZ1 = size * 1.2D / 2.0D;
        	double centerZ2 = size / 2.0D;

        	GL11.glEnable(GL11.GL_CULL_FACE);//表綿描画
        	GL11.glEnable(32826);
        	this.renderThrust(tessellator, size * 10.0D, size, centerZ1 - centerZ2, 5, 1.0F);
        	GL11.glDisable(32826);

        	GL11.glEnable(GL11.GL_BLEND);
        	this.renderThrust(tessellator, size * 1.2D * 10.0D, size * 1.2F, 0.0D, element, 0.6F);
        }
        //貫通の描画
        else if (color == 3)
        {
        	GL11.glRotatef(par5, 0.0F, 1.0F, 0.0F);
        	GL11.glRotatef(-par1EntitySwordSkill.rotationPitch, 1.0F, 0.0F, 0.0F);

        	double centerZ1 = size * 1.2D / 2.0D;
        	double centerZ2 = size / 2.0D;

        	GL11.glEnable(GL11.GL_CULL_FACE);//表綿描画
        	GL11.glEnable(32826);
        	this.renderPenetrate(tessellator, size * 15.0D, size, centerZ1 - centerZ2, 5, 1.0F);
        	GL11.glDisable(32826);

        	GL11.glEnable(GL11.GL_BLEND);
        	this.renderPenetrate(tessellator, size * 1.2D * 15.0D, size * 1.2F, 0.0D, element, 0.6F);
        }
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glPopMatrix();
    }

    //TODO 光のエフェクト
    private void renderLightEffect(Tessellator par1, int par2, float par3, EntitySwordSkill par4EntitySwordSkill)
    {
        par2 %= 7;
        int var1 = par4EntitySwordSkill.getCount() % 2;
        double var2 = (double)(var1 * 32 +  0) / 64.0D;
        double var3 = (double)(var1 * 32 + 32) / 64.0D;
        double var4 = 0.0D;
        double var5 = 1.0D;
        double var6 = 1.0D;
        double var7 = 0.5D;
        double var8 = 0.25D;
        GL11.glScalef(par3, par3, par3);
        par1.startDrawingQuads();
        par1.setNormal(0.0F, 1.0F, 0.0F);
        par1.setColorRGBA_F(this.colorR[par2], this.colorG[par2], this.colorB[par2], 0.5F);
        par1.addVertexWithUV(0.0D - var7, 0.0D - var8, 0.0D, var2, var5);
        par1.addVertexWithUV(var6 - var7, 0.0D - var8, 0.0D, var3, var5);
        par1.addVertexWithUV(var6 - var7, 1.0D - var8, 0.0D, var3, var4);
        par1.addVertexWithUV(0.0D - var7, 1.0D - var8, 0.0D, var2, var4);
        par1.draw();
    }

    //斬撃
    private void renderSlash(Tessellator par1, int par2)
    {
        GL11.glDisable(GL11.GL_CULL_FACE);//両面描画
        par2 %= 7;
        double var1 = 0.0D / 128.0D;
        double var2 = 32.0D / 128.0D;
        double var3 = 0.0D / 128.0D;
        double var4 = 32.0D / 128.0D;

        par1.startDrawingQuads();
        par1.setNormal(0.0F, 1.0F, 0.0F);
        par1.setColorRGBA_F(this.colorR[par2], this.colorG[par2], this.colorB[par2], 1.0F);
        par1.addVertexWithUV(0.0D, 0.0D, 0.5D, var2, var3);
        par1.addVertexWithUV(0.5D, 0.0D, 0.5D, var1, var3);
        par1.addVertexWithUV(0.5D, 0.0D, -0.5D, var1, var4);
        par1.addVertexWithUV(0.0D, 0.0D, -0.5D, var2, var4);
        par1.draw();
        par1.startDrawingQuads();
        par1.setNormal(0.0F, 1.0F, 0.0F);
        par1.setColorRGBA_F(1.0F, 1.0F, 1.0F, 0.4F);
        par1.addVertexWithUV(0.0D , 0.0D, 0.45D, var2, var3);
        par1.addVertexWithUV(0.45D, 0.0D, 0.45D, var1, var3);
        par1.addVertexWithUV(0.45D, 0.0D, -0.45D, var1, var4);
        par1.addVertexWithUV(0.0D , 0.0D, -0.45D, var2, var4);
        par1.draw();
        GL11.glEnable(GL11.GL_CULL_FACE);//表綿描画
    }

    //刺突の描画をする
    private void renderThrust(Tessellator tessellator, double length, float width, double zPos, int color, float alpha)
    {
        float maxWidth = (float)width;//最大の太さをmaxWidthとして保存
        color %= 7;

        int zAngleDivNum = 8;//Z軸回転の分割数
        double angleZ;//Z軸回転変数
        double angleSpanZ = Math.PI * 2.0D / (double)zAngleDivNum;//Z軸回転の変化量

        int zDivNum = 13;//刺突の奥方向への分割数。必ず奇数
        double zLength = length;//刺突の長さ（Z方向の長さ、奥行き）
        double zDivLength = zLength / (double)(zDivNum - 1);//Z方向へ分割したときの1分割分の長さ
        double zLength2 = zLength / 2.0D;//長さの半分
        double zPosOld = zPos;//ひとつ前の描画位置
        //初期のXとYの座標（刺突の始点は点）
        float xPos = 0F;
        float yPos = 0F;
        float xPos2 = 0F;
        float yPos2 = 0F;
        //初期のXとYの座標
        float xPosOld = xPos;
        float yPosOld = yPos;
        float xPos2Old = xPos2;
        float yPos2Old = yPos2;
        //半円を描くように刺突が太くなるための変数。cos0 ~ cos180で処理
        float angle = 0F;
        float angleSpan = (float)Math.PI / (float)(zDivNum - 1);
        //刺突の太さ。Z軸方向への進行で２つ必要
        width = (float)Math.sin(angle) * maxWidth;
        float widthOld = width;

        //奥行きが長さの半分に達するまで（奥行きの初期値は長さの半分のマイナス値）
        for(int j = 1; j < zDivNum; j++)
        {
        	zPos += zDivLength;//奥行きを１段階増やす
        	widthOld = width;
        	angle += angleSpan;//刺突の描く半円の角度を更新
        	width = (float)Math.sin(angle) * maxWidth;
        	//XとY座標は初期値、0度のときの座標に戻る。
        	xPos = 0F;
        	yPos = (float)width;
        	//Z軸回転の始点
        	angleZ = angleSpanZ;

        	for(int i = 0; i <= zAngleDivNum; i++)
        	{
        		xPos = (float)Math.cos(angleZ) * width;
        		yPos = (float)Math.sin(angleZ) * width;
        		xPos2 = (float)Math.cos(angleZ) * widthOld;
        		yPos2 = (float)Math.sin(angleZ) * widthOld;

        		tessellator.startDrawingQuads();
        		tessellator.setColorRGBA_F(this.colorR[color], this.colorG[color], this.colorB[color], alpha);
        		tessellator.setNormal(0.0F, 1.0F, 0.0F);
        		tessellator.addVertexWithUV(  xPosOld , yPosOld , zPos   , 0.0F, 0.0F);
        		tessellator.addVertexWithUV(  xPos    , yPos    , zPos   , 1.0F, 0.0F);
        		tessellator.addVertexWithUV(  xPos2   , yPos2   , zPosOld, 1.0F, 1.0F);
        		tessellator.addVertexWithUV(  xPos2Old, yPos2Old, zPosOld, 0.0F, 1.0F);
        		tessellator.draw();

        		xPosOld = xPos;
        		yPosOld = yPos;
        		xPos2Old = xPos2;
        		yPos2Old = yPos2;
        		angleZ += angleSpanZ;
        	}
        	zPosOld = zPos;//古い奥行きを今の奥行きに更新
        }
    }

    //貫通の描画をする
    private void renderPenetrate(Tessellator tessellator, double length, float width, double zPos, int color, float alpha)
    {
        float maxWidth = (float)width;//最大の太さをmaxWidthとして保存
        color %= 7;

        int zAngleDivNum = 8;//Z軸回転の分割数
        double angleZ;//Z軸回転変数
        double angleSpanZ = Math.PI * 2.0D / (double)zAngleDivNum;//Z軸回転の変化量

        int zDivNum = 13;//刺突の奥方向への分割数。必ず奇数
        double zLength = length;//刺突の長さ（Z方向の長さ、奥行き）
        double zDivLength = zLength / (double)(zDivNum - 1);//Z方向へ分割したときの1分割分の長さ
        double zLength2 = zLength / 2.0D;//長さの半分
        double zPosOld = zPos;//ひとつ前の描画位置
        //初期のXとYの座標（貫通の始点は点）
        float xPos = 0F;
        float yPos = 0F;
        float xPos2 = 0F;
        float yPos2 = 0F;
        //初期のXとYの座標
        float xPosOld = xPos;
        float yPosOld = yPos;
        float xPos2Old = xPos2;
        float yPos2Old = yPos2;
        //半円を描くように刺突が太くなるための変数。cos0 ~ cos180で処理
        float angle = 0F;
        float angleSpan = (float)Math.PI / (float)(zDivNum - 1);
        //貫通の太さ。Z軸方向への進行で２つ必要
        width = (float)Math.sin(angle) * maxWidth;
        float widthOld = width;

        //奥行きが長さの半分に達するまで（奥行きの初期値は長さの半分のマイナス値）
        for(int j = 1; j < zDivNum; j++)
        {
        	zPos += zDivLength;//奥行きを１段階増やす
        	widthOld = width;
        	angle += angleSpan;//貫通の描く半円の角度を更新
        	width = (float)Math.sin(angle) * maxWidth;
        	//XとY座標は初期値、0度のときの座標に戻る。
        	xPos = 0F;
        	yPos = (float)width;
        	//Z軸回転の始点
        	angleZ = angleSpanZ;

        	for(int i = 0; i <= zAngleDivNum; i++)
        	{
        		xPos = (float)Math.cos(angleZ) * width;
        		yPos = (float)Math.sin(angleZ) * width;
        		xPos2 = (float)Math.cos(angleZ) * widthOld;
        		yPos2 = (float)Math.sin(angleZ) * widthOld;

        		tessellator.startDrawingQuads();
        		tessellator.setColorRGBA_F(this.colorR[color], this.colorG[color], this.colorB[color], alpha);
        		tessellator.setNormal(0.0F, 1.0F, 0.0F);
        		tessellator.addVertexWithUV(  xPosOld , yPosOld , zPos   , 0.0F, 0.0F);
        		tessellator.addVertexWithUV(  xPos    , yPos    , zPos   , 1.0F, 0.0F);
        		tessellator.addVertexWithUV(  xPos2   , yPos2   , zPosOld, 1.0F, 1.0F);
        		tessellator.addVertexWithUV(  xPos2Old, yPos2Old, zPosOld, 0.0F, 1.0F);
        		tessellator.draw();

        		xPosOld = xPos;
        		yPosOld = yPos;
        		xPos2Old = xPos2;
        		yPos2Old = yPos2;
        		angleZ += angleSpanZ;
        	}
        	zPosOld = zPos;//古い奥行きを今の奥行きに更新
        }
    }

    private ResourceLocation getPath(EntitySwordSkill par1EntitySwordSkill)
    {
        int var1 = par1EntitySwordSkill.getType();
        if(par1EntitySwordSkill.getCount() < 0)
        {
        	return this.thrust;
        }
        else if (var1 == 1 || var1 == 3)
        {
        	return this.thrust;
        }
        return this.slash;
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity par1Entity)
    {
        return this.getPath((EntitySwordSkill)par1Entity);
    }

    @Override
    public void doRender(Entity par1Entity, double par2, double par3, double par4, float par5, float par6)
    {
        this.doRenderSlash((EntitySwordSkill)par1Entity, par2, par3, par4, par5, par6);
    }
}