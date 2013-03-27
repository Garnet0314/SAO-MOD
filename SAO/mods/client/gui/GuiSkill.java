package SAO.mods.client.gui;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.StatCollector;

import org.lwjgl.opengl.GL11;

import SAO.mods.inventory.ContainerSkill;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiSkill extends GuiContainer
{
    private IInventory upperChestInventory;
    private IInventory lowerChestInventory;
    private static GuiSkill instance;

    public GuiSkill(IInventory par1IInventory, IInventory par2IInventory)
    {
        super(new ContainerSkill(par1IInventory, par2IInventory));
        this.upperChestInventory = par1IInventory;
        this.lowerChestInventory = par2IInventory;
        this.allowUserInput = false;
        this.ySize = 168;
    }

    public static GuiSkill getInstance(EntityPlayer par1EntityPlayer)
    {
        if (instance == null)
        {
        	instance = new GuiSkill(par1EntityPlayer.inventory, par1EntityPlayer.getInventoryEnderChest());
        }
        return instance;
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int par1, int par2)
    {
        this.fontRenderer.drawString(this.lowerChestInventory.isInvNameLocalized() ? this.lowerChestInventory.getInvName() : StatCollector.translateToLocal("Skill"), 8, 6, 4210752);
        this.fontRenderer.drawString(this.upperChestInventory.isInvNameLocalized() ? this.upperChestInventory.getInvName() : StatCollector.translateToLocal(this.upperChestInventory.getInvName()), 8, this.ySize - 96 + 2, 4210752);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float f, int i, int j)
    {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.renderEngine.bindTexture("/gui/container.png");
        int k = (this.width - this.xSize) / 2;
        int l = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(k, l, 0, 0, this.xSize, 3 * 18 + 17);
        this.drawTexturedModalRect(k, l + 3 * 18 + 17, 0, 126, this.xSize, 96);
    }
}