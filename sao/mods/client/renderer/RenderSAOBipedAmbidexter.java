package sao.mods.client.renderer;

import static net.minecraftforge.client.IItemRenderer.ItemRenderType.EQUIPPED;
import static net.minecraftforge.client.IItemRenderer.ItemRendererHelper.BLOCK_3D;

import org.lwjgl.opengl.GL11;

import net.minecraft.block.Block;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.MinecraftForgeClient;

public class RenderSAOBipedAmbidexter extends RenderSAOBiped
{
    public RenderSAOBipedAmbidexter(ModelBiped par1ModelBiped, float par2)
    {
        super(par1ModelBiped, par2);
    }

    @Override
    protected void renderEquippedItems(EntityLivingBase par1EntityLivingBase, float par2)
    {
        float f1 = 1.0F;
        GL11.glColor3f(f1, f1, f1);
        super.renderEquippedItems(par1EntityLivingBase, par2);
        ItemStack itemstack = par1EntityLivingBase.getHeldItem();
        float f2;

        if (itemstack != null)
        {
            GL11.glPushMatrix();

            this.modelBipedMain.bipedLeftArm.postRender(0.0625F);
            GL11.glTranslatef(0.0625F, 0.4375F, 0.0625F);

            IItemRenderer customRenderer = MinecraftForgeClient.getItemRenderer(itemstack, EQUIPPED);
            boolean is3D = (customRenderer != null && customRenderer.shouldUseRenderHelper(EQUIPPED, itemstack, BLOCK_3D));

            if (itemstack.getItem() instanceof ItemBlock && (is3D || RenderBlocks.renderItemIn3d(Block.blocksList[itemstack.itemID].getRenderType())))
            {
                f2 = 0.5F;
                GL11.glTranslatef(0.0F, 0.1875F, -0.3125F);
                f2 *= 0.75F;
                GL11.glRotatef(20.0F, 1.0F, 0.0F, 0.0F);
                GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
                GL11.glScalef(-f2, -f2, f2);
            }
            else if (itemstack.itemID == Item.bow.itemID)
            {
                f2 = 0.625F;
                GL11.glTranslatef(0.0F, 0.125F, 0.3125F);
                GL11.glRotatef(-20.0F, 0.0F, 1.0F, 0.0F);
                GL11.glScalef(f2, -f2, f2);
                GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
                GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
            }
            else if (Item.itemsList[itemstack.itemID].isFull3D())
            {
                f2 = 0.625F;

                if (Item.itemsList[itemstack.itemID].shouldRotateAroundWhenRendering())
                {
                    GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
                    GL11.glTranslatef(0.0F, -0.125F, 0.0F);
                }

                this.func_82422_c();
                GL11.glScalef(f2, -f2, f2);
                GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
                GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
            }
            else
            {
                f2 = 0.375F;
                GL11.glTranslatef(0.25F, 0.1875F, -0.1875F);
                GL11.glScalef(f2, f2, f2);
                GL11.glRotatef(60.0F, 0.0F, 0.0F, 1.0F);
                GL11.glRotatef(-90.0F, 1.0F, 0.0F, 0.0F);
                GL11.glRotatef(20.0F, 0.0F, 0.0F, 1.0F);
            }

            this.renderManager.itemRenderer.renderItem(par1EntityLivingBase, itemstack, 0);

            if (itemstack.getItem().requiresMultipleRenderPasses())
            {
                for (int x = 1; x < itemstack.getItem().getRenderPasses(itemstack.getItemDamage()); x++)
                {
                    this.renderManager.itemRenderer.renderItem(par1EntityLivingBase, itemstack, x);
                }
            }

            GL11.glPopMatrix();
        }
    }
}