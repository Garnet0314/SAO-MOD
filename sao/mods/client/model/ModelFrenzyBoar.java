package sao.mods.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class ModelFrenzyBoar extends ModelBase
{
    private ModelRenderer bodyFront[] = new ModelRenderer[7];
    private ModelRenderer bodyBack[] = new ModelRenderer[5];
    private ModelRenderer head[] = new ModelRenderer[7];
    private ModelRenderer tuskRight[] = new ModelRenderer[4];
    private ModelRenderer tuskLeft[] = new ModelRenderer[4];
    private ModelRenderer earRight;
    private ModelRenderer earLeft;
    private ModelRenderer armRight[] = new ModelRenderer[4];
    private ModelRenderer armLeft[] = new ModelRenderer[4];
    private ModelRenderer legRight[] = new ModelRenderer[4];
    private ModelRenderer legLeft[] = new ModelRenderer[4];
    private ModelRenderer tail[] = new ModelRenderer[2];

    public ModelFrenzyBoar()
    {
        this.textureWidth = 512;
        this.textureHeight = 256;

        this.bodyFront[0] = new ModelRenderer(this, 0, 217);
        this.bodyFront[0].addBox(-10.5F, -7.5F, -11.0F, 21, 15, 22);
        this.bodyFront[0].setRotationPoint(0.0F, 9.5F, -1.0F);
        this.bodyFront[1] = new ModelRenderer(this, 0, 165);
        this.bodyFront[1].addBox(-9.5F, -3.0F, -9.0F, 19, 3, 18);
        this.bodyFront[1].setRotationPoint(0.0F, -7.5F, 0.0F);
        this.bodyFront[2] = new ModelRenderer(this, 0, 146);
        this.bodyFront[2].addBox(-7.5F, -2.0F, -7.0F, 15, 2, 14);
        this.bodyFront[2].setRotationPoint(0.0F, -3.0F, 0.0F);
        this.bodyFront[3] = new ModelRenderer(this, 0, 131);
        this.bodyFront[3].addBox(-6.5F, -1.0F, -6.0F, 13, 1, 12);
        this.bodyFront[3].setRotationPoint(0.0F, -2.0F, 0.0F);
        this.bodyFront[4] = new ModelRenderer(this, 87, 243);
        this.bodyFront[4].addBox(-4.5F, -2.0F, -4.0F, 9, 2, 8);
        this.bodyFront[4].setRotationPoint(0.0F, -1.0F, 0.0F);
        this.bodyFront[5] = new ModelRenderer(this, 63, 140);
        this.bodyFront[5].addBox(-8.5F, 0.0F, -9.0F, 17, 1, 18);
        this.bodyFront[5].setRotationPoint(0.0F, 21.0F, 0.0F);
        this.bodyFront[6] = new ModelRenderer(this, 53, 120);
        this.bodyFront[6].addBox(-7.5F, 0.0F, -8.0F, 15, 2, 16);
        this.bodyFront[6].setRotationPoint(0.0F, 0.0F, 0.0F);

        this.bodyBack[0] = new ModelRenderer(this, 0, 188);
        this.bodyBack[0].addBox(-8.5F, -6.0F, 0.0F, 17, 12, 15);
        this.bodyBack[0].setRotationPoint(0.0F, 1.0F, 10.0F);
        this.bodyBack[1] = new ModelRenderer(this, 87, 229);
        this.bodyBack[1].addBox(-6.5F, -1.0F, -5.5F, 13, 1, 11);
        this.bodyBack[1].setRotationPoint(0.0F, -6.0F, 9.0F);
        this.bodyBack[2] = new ModelRenderer(this, 70, 40);
        this.bodyBack[2].addBox(-5.5F, -1.0F, -4.0F, 11, 1, 8);
        this.bodyBack[2].setRotationPoint(0.0F, -1.0F, 1.0F);
        this.bodyBack[3] = new ModelRenderer(this, 80, 54);
        this.bodyBack[3].addBox(-7.5F, 0.0F, -8.0F, 15, 1, 16);
        this.bodyBack[3].setRotationPoint(0.0F, 13.0F, -4.0F);
        this.bodyBack[4] = new ModelRenderer(this, 100, 0);
        this.bodyBack[4].addBox(-7.5F, 11.0F, 0.0F, 15, 8, 1);
        this.bodyBack[4].setRotationPoint(0.0F, -21.0F, 9.0F);

        this.head[0] = new ModelRenderer(this, 90, 190);
        this.head[0].addBox(-6.5F, -8.0F, -6.0F, 13, 10, 6);
        this.head[0].setRotationPoint(0.0F, 1.5F, -10.0F);
        this.head[1] = new ModelRenderer(this, 0, 117);
        this.head[1].addBox(-7.5F, 0F, -2.5F, 15, 7, 5);
        this.head[1].setRotationPoint(0.0F, 0.0F, -3.5F);
        this.head[2] = new ModelRenderer(this, 79, 177);
        this.head[2].addBox(-4.5F, -4.5F, -1.0F, 9, 9, 1);
        this.head[2].setRotationPoint(0.0F, 1.5F, -2.5F);
        this.head[3] = new ModelRenderer(this, 101, 177);
        this.head[3].addBox(-3.5F, -3.5F, -2.0F, 7, 7, 2);
        this.head[3].setRotationPoint(0.0F, 1.0F, -1.0F);
        this.head[4] = new ModelRenderer(this, 80, 164);
        this.head[4].addBox(-2.5F, -3.5F, -3.0F, 5, 7, 3);
        this.head[4].setRotationPoint(0.0F, 1.0F, -2.0F);
        this.head[5] = new ModelRenderer(this, 99, 167);
        this.head[5].addBox(-2.0F, -1.5F, -3.0F, 4, 3, 3);
        this.head[5].setRotationPoint(0.0F, 2.0F, -2.0F);
        this.head[6] = new ModelRenderer(this, 40, 100);
        this.head[6].addBox(-4.5F, -1.0F, -2.5F, 9, 1, 5);
        this.head[6].setRotationPoint(0.0F, -13.5F, 7.5F);

        this.tuskRight[0] = new ModelRenderer(this, 68, 191);
        this.tuskRight[0].addBox(-2.0F, -1.0F, -1.0F, 2, 2, 2);
        this.tuskRight[0].setRotationPoint(-2.5F, 1.5F, -1.0F);
        this.tuskRight[1] = new ModelRenderer(this, 42, 110);
        this.tuskRight[1].addBox(-1.0F, -1.0F, -1.0F, 2, 2, 2);
        this.tuskRight[1].setRotationPoint(-2.0F, -1.0F, 0.0F);
        this.tuskRight[2] = new ModelRenderer(this, 53, 110);
        this.tuskRight[2].addBox(-1.0F, -1.0F, -1.0F, 2, 2, 2);
        this.tuskRight[2].setRotationPoint(-1.0F, -1.0F, 0.0F);
        this.tuskRight[3] = new ModelRenderer(this, 31, 104);
        this.tuskRight[3].addBox(-1.0F, -1.0F, -1.0F, 1, 1, 2);
        this.tuskRight[3].setRotationPoint(1.0F, -1.0F, 0.0F);

        this.tuskLeft[0] = new ModelRenderer(this, 42, 124);
        this.tuskLeft[0].mirror = true;
        this.tuskLeft[0].addBox(0.0F, -1.0F, -1.0F, 2, 2, 2);
        this.tuskLeft[0].setRotationPoint(2.5F, 1.5F, -1.0F);
        this.tuskLeft[1] = new ModelRenderer(this, 42, 118);
        this.tuskLeft[1].mirror = true;
        this.tuskLeft[1].addBox(-1.0F, -1.0F, -1.0F, 2, 2, 2);
        this.tuskLeft[1].setRotationPoint(2.0F, -1.0F, 0.0F);
        this.tuskLeft[2] = new ModelRenderer(this, 31, 110);
        this.tuskLeft[2].mirror = true;
        this.tuskLeft[2].addBox(-1.0F, -1.0F, -1.0F, 2, 2, 2);
        this.tuskLeft[2].setRotationPoint(1.0F, -1.0F, 0.0F);
        this.tuskLeft[3] = new ModelRenderer(this, 64, 111);
        this.tuskLeft[3].mirror = true;
        this.tuskLeft[3].addBox(0.0F, -1.0F, -1.0F, 1, 1, 2);
        this.tuskLeft[3].setRotationPoint(-1.0F, -1.0F, 0.0F);

        this.earRight = new ModelRenderer(this, 74, 110);
        this.earRight.addBox(-5.0F, 0.0F, -2.0F, 6, 1, 4);
        this.earRight.setRotationPoint(-6.5F, -7.0F, -3.0F);
        this.earLeft = new ModelRenderer(this, 74, 100);
        this.earLeft.mirror = true;
        this.earLeft.addBox(-1.0F, 0.0F, -2.0F, 6, 1, 4);
        this.earLeft.setRotationPoint(6.5F, -7.0F, -3.0F);

        this.armRight[0] = new ModelRenderer(this, 0, 93);
        this.armRight[0].addBox(-3.0F, -5.5F, -5.5F, 3, 11, 11);
        this.armRight[0].setRotationPoint(-10.5F, 0.5F, -2.0F);
        this.armRight[1] = new ModelRenderer(this, 0, 73);
        this.armRight[1].addBox(-4.0F, 0.0F, -3.5F, 4, 10, 7);
        this.armRight[1].setRotationPoint(-2.0F, -2.0F, -0.5F);
        this.armRight[2] = new ModelRenderer(this, 0, 25);
        this.armRight[2].addBox(-3.0F, 0.0F, -2.0F, 3, 10, 4);
        this.armRight[2].setRotationPoint(0.0F, 5.0F, -0.5F);
        this.armRight[3] = new ModelRenderer(this, 0, 41);
        this.armRight[3].addBox(-3.0F, 0.0F, -2.0F, 3, 1, 4);
        this.armRight[3].setRotationPoint(0.0F, 10.0F, -1.0F);

        this.armLeft[0] = new ModelRenderer(this, 0, 49);
        this.armLeft[0].mirror = true;
        this.armLeft[0].addBox(0.0F, -5.5F, -5.5F, 3, 11, 11);
        this.armLeft[0].setRotationPoint(10.5F, 0.5F, -2.0F);
        this.armLeft[1] = new ModelRenderer(this, 87, 210);
        this.armLeft[1].mirror = true;
        this.armLeft[1].addBox(0.0F, 0.0F, -3.5F, 4, 10, 7);
        this.armLeft[1].setRotationPoint(2.0F, -2.0F, -0.5F);
        this.armLeft[2] = new ModelRenderer(this, 69, 198);
        this.armLeft[2].mirror = true;
        this.armLeft[2].addBox(0.0F, 0.0F, -2.0F, 3, 10, 4);
        this.armLeft[2].setRotationPoint(0.0F, 5.0F, -0.5F);
        this.armLeft[3] = new ModelRenderer(this, 300, 200);
        this.armLeft[3].mirror = true;
        this.armLeft[3].addBox(0.0F, 0.0F, -2.0F, 3, 1, 4);
        this.armLeft[3].setRotationPoint(0.0F, 10.0F, -1.0F);

        this.legRight[0] = new ModelRenderer(this, 20, 0);
        this.legRight[0].addBox(-3.0F, -4.5F, -3.0F, 3, 9, 6);
        this.legRight[0].setRotationPoint(-8.5F, 3.0F, 10.0F);
        this.legRight[1] = new ModelRenderer(this, 200, 0);
        this.legRight[1].addBox(-3.0F, -3.0F, -3.0F, 3, 6, 6);
        this.legRight[1].setRotationPoint(0.0F, 4.0F, -3.0F);
        this.legRight[2] = new ModelRenderer(this, 250, 0);
        this.legRight[2].addBox(-3.0F, 0.0F, 0.0F, 3, 4, 3);
        this.legRight[2].setRotationPoint(0.0F, 1.0F, 1.0F);
        this.legRight[3] = new ModelRenderer(this, 280, 0);
        this.legRight[3].addBox(-3.0F, 0.0F, 0.0F, 3, 1, 4);
        this.legRight[3].setRotationPoint(0.0F, 4.0F, -1.0F);

        this.legLeft[0] = new ModelRenderer(this, 50, 0);
        this.legLeft[0].mirror = true;
        this.legLeft[0].addBox(0.0F, -4.5F, -3.0F, 3, 9, 6);
        this.legLeft[0].setRotationPoint(8.5F, 3.0F, 10.0F);
        this.legLeft[1] = new ModelRenderer(this, 74, 0);
        this.legLeft[1].mirror = true;
        this.legLeft[1].addBox(0.0F, -3.0F, -3.0F, 3, 6, 6);
        this.legLeft[1].setRotationPoint(0.0F, 5.5F, -3.0F);
        this.legLeft[2] = new ModelRenderer(this, 150, 0);
        this.legLeft[2].mirror = true;
        this.legLeft[2].addBox(0.0F, 0.0F, 0.0F, 3, 4, 3);
        this.legLeft[2].setRotationPoint(0.0F, 1.0F, 1.0F);
        this.legLeft[3] = new ModelRenderer(this, 180, 0);
        this.legLeft[3].mirror = true;
        this.legLeft[3].addBox(0.0F, 0.0F, 0.0F, 3, 1, 4);
        this.legLeft[3].setRotationPoint(0.0F, 4.0F, -1.0F);

        this.tail[0] = new ModelRenderer(this, 300, 0);
        this.tail[0].addBox(-0.5F, -0.5F, 0F, 1, 1, 6);
        this.tail[0].setRotationPoint(0.0F, -4.0F, 12.0F);
        this.tail[1] = new ModelRenderer(this, 0, 0);
        this.tail[1].addBox(-1.0F, -1.0F, 0.0F, 2, 1, 3);
        this.tail[1].setRotationPoint(0.0F, 0.0F, 4.0F);

        int var1;
        for (var1 = 0; var1 < 6; var1++)
        {
        	this.bodyFront[var1].addChild(this.bodyFront[var1 + 1]);
        	this.head[var1].addChild(this.head[var1 + 1]);
        }
        for (var1 = 0; var1 < 3; var1++)
        {
        	this.bodyBack[var1].addChild(this.bodyBack[var1 + 1]);
        	this.tuskRight[var1].addChild(this.tuskRight[var1 + 1]);
        	this.tuskLeft[var1].addChild(this.tuskLeft[var1 + 1]);
        	this.armRight[var1].addChild(this.armRight[var1 + 1]);
        	this.armLeft[var1].addChild(this.armLeft[var1 + 1]);
        	this.legRight[var1].addChild(this.legRight[var1 + 1]);
        	this.legLeft[var1].addChild(this.legLeft[var1 + 1]);
        }
        this.bodyFront[0].addChild(this.bodyBack[0]);
        this.bodyFront[0].addChild(this.head[0]);
        this.head[4].addChild(this.tuskRight[0]);
        this.head[4].addChild(this.tuskLeft[0]);
        this.head[0].addChild(this.earRight);
        this.head[0].addChild(this.earLeft);
        this.bodyFront[0].addChild(this.armRight[0]);
        this.bodyFront[0].addChild(this.armLeft[0]);
        this.bodyBack[0].addChild(this.legRight[0]);
        this.bodyBack[0].addChild(this.legLeft[0]);
        this.bodyBack[0].addChild(this.tail[0]);
        this.tail[0].addChild(this.tail[1]);
    }

    public void render(Entity par1Entity, float par2, float par3, float par4, float par5, float par6, float par7)
    {
        this.setRotationAngles(par2, par3, par4, par5, par6, par7, par1Entity);
        this.bodyFront[0].render(par7);
    }

    public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity)
    {
        this.head[0].rotateAngleX = par5 / (180F / (float)Math.PI);
        this.head[0].rotateAngleY = par4 / (180F / (float)Math.PI);

        float var1 = MathHelper.sin(par3 * 0.133F) * 0.1F;
        float var2 = MathHelper.cos(par3 * 0.133F) * 0.1F;
        this.armRight[0].rotateAngleX = MathHelper.cos(par1 * 0.6662F) * 1.4F * par2;
        this.armLeft[0].rotateAngleX = MathHelper.cos(par1 * 0.6662F + (float)Math.PI) * 1.4F * par2;
        this.legRight[0].rotateAngleX = MathHelper.cos(par1 * 0.6662F + (float)Math.PI) * 1.4F * par2;
        this.legLeft[0].rotateAngleX = MathHelper.cos(par1 * 0.6662F) * 1.4F * par2;
        this.tail[0].rotateAngleX = var1;
        this.tail[0].rotateAngleY = var2;
    }
}