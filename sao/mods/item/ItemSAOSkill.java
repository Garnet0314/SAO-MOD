package sao.mods.item;

import java.util.List;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import sao.mods.SAOMOD;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemSAOSkill extends Item
{
    private String skillName;
    private int[] levelUP = {0, 100, 200, 300, 10000};

    public ItemSAOSkill(int par1, String par2Str)
    {
        super(par1);
        this.maxStackSize = 1;
        this.setCreativeTab(SAOMOD.saoTabs);
        this.skillName = par2Str;
    }

    public void addSkillExperience(ItemStack par1ItemStack, int par2)
    {
        this.checkTag(par1ItemStack);
        if (par2 > 0)
        {
        	NBTTagCompound var1 = par1ItemStack.getTagCompound();
        	var1.setInteger("exp", par2 + this.getSkillExperience(par1ItemStack));
        }
    }

    private int getSkillExperience(ItemStack par1ItemStack)
    {
        this.checkTag(par1ItemStack);
        return par1ItemStack.getTagCompound().getInteger("exp");
    }

    private boolean checkTag(ItemStack par1ItemStack)
    {
        if (par1ItemStack.hasTagCompound())
        {
        	return true;
        }
        NBTTagCompound var1 = new NBTTagCompound();
        par1ItemStack.setTagCompound(var1);
        var1.setInteger("exp", 0);
        return false;
    }

    public String getSkillName()
    {
        return this.skillName;
    }

    public int getSkillLevel(ItemStack par1ItemStack)
    {
        for (int var1 = 0; var1 < this.levelUP.length; var1++)
        {
        	if (this.getSkillExperience(par1ItemStack) < this.levelUP[var1])
        	{
        		return var1 - 1;
        	}
        }
        return this.levelUP.length;
    }

    @Override
    public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4)
    {
        par3List.add("Skill Level : " + this.getSkillLevel(par1ItemStack));
        par3List.add("Skill Exp : " + this.getSkillExperience(par1ItemStack));
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister par1IconRegister)
    {
        this.itemIcon = par1IconRegister.registerIcon("sao:" + this.getUnlocalizedName());
    }
}