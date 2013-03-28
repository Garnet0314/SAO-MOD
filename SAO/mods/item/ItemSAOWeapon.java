package SAO.mods.item;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryEnderChest;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import SAO.mods.SAOMOD;
import SAO.mods.entity.ISAOMob;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemSAOWeapon extends Item
{
    private final EnumSAOWeapon saoWeapon;

    public ItemSAOWeapon(int par1, EnumSAOWeapon par2EnumSAOWeapon)
    {
        super(par1);
        this.saoWeapon = par2EnumSAOWeapon;
        this.maxStackSize = 1;
        this.setMaxDamage(par2EnumSAOWeapon.getMaxUses());
        this.setCreativeTab(SAOMOD.saoTabs);
    }

    @Override
    public boolean hitEntity(ItemStack par1ItemStack, EntityLiving par2EntityLiving, EntityLiving par3EntityLiving)
    {
        if (par3EntityLiving instanceof EntityPlayer)
        {
        	EntityPlayer var1 = (EntityPlayer)par3EntityLiving;
        	if (this.checkSkill(par1ItemStack, var1))
        	{
        		par1ItemStack.damageItem(1, par3EntityLiving);
        	}
        	else
        	{
        		par1ItemStack.damageItem(10, par3EntityLiving);
        		if (par3EntityLiving.getRNG().nextInt(3) == 0)
        		{
        			var1.addExhaustion(4);
        		}
        	}
        }
        else
        {
        	par1ItemStack.damageItem(1, par3EntityLiving);
        }
        return true;
    }

    @Override
    public int getDamageVsEntity(Entity par1Entity)
    {
        int var1 = 0;
        int var2 = 0;
        int var3[];
        int var4[];
        int var5;

        if (par1Entity instanceof ISAOMob)
        {
        	ISAOMob var6Entity = (ISAOMob)par1Entity;
        	var3 = new int[] {this.saoWeapon.getSlashElement() * (100 - var6Entity.getSlashResist()) / 100, this.saoWeapon.getThrustElement() * (100 - var6Entity.getThrustResist()) / 100, this.saoWeapon.getStrikeElement() * (100 - var6Entity.getStrikeResist()) / 100, this.saoWeapon.getPenetrateElement() *(100 - var6Entity.getPenetrateResist()) / 100};
        	var4 = new int[] {this.saoWeapon.getQuakeElement() * (100 - var6Entity.getQuakeResist()) / 100, this.saoWeapon.getWaterElement() * (100 - var6Entity.getWaterResist()) / 100, this.saoWeapon.getFireElement() * (100 - var6Entity.getFireResist()) / 100, this.saoWeapon.getAeroElement() * (100 - var6Entity.getAeroResist()) / 100, this.saoWeapon.getShineElement() * (100 - var6Entity.getShineResist()) / 100, this.saoWeapon.getDarkElement() * (100 - var6Entity.getDarkResist()) / 100};
        }
        else
        {
        	var3 = new int[] {this.saoWeapon.getSlashElement(), this.saoWeapon.getThrustElement(), this.saoWeapon.getStrikeElement(), this.saoWeapon.getPenetrateElement()};
        	var4 = new int[] {this.saoWeapon.getQuakeElement(), this.saoWeapon.getWaterElement(), this.saoWeapon.getFireElement(), this.saoWeapon.getAeroElement(), this.saoWeapon.getShineElement(), this.saoWeapon.getDarkElement()};
        }

        for (var5 = 0; var5 < var3.length; var5++)
        {
        	if (var1 < var3[var5])
        	{
        		var1 = var3[var5];
        	}
        }
        for (var5 = 0; var5 < var4.length; var5++)
        {
        	var2 += var4[var5];
        }
        return var1 + var2;
    }

    private boolean checkSkill(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer)
    {
        InventoryEnderChest var1 = par2EntityPlayer.getInventoryEnderChest();
        if (var1 != null)
        {
        	ItemStack var2;
        	ItemSAOSkill var3;
        	for (int var4 = 0;var4 < var1.getSizeInventory(); var4++)
        	{
        		var2 = var1.getStackInSlot(var4);
        		if (var2 != null && var2.getItem() instanceof ItemSAOSkill)
        		{
        			var3 = (ItemSAOSkill)var2.getItem();
        			if (var3.getSkillName() == this.saoWeapon.getSkillType() && var3.getSkillLevel() >= this.saoWeapon.getSkillLevel())
        			{
        				return true;
        			}
        		}
        	}
        }
        return false;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void updateIcons(IconRegister par1IconRegister)
    {
        this.iconIndex = par1IconRegister.registerIcon("SAO:" + this.getUnlocalizedName());
    }
}