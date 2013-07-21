package sao.mods.item;

import java.util.Iterator;
import java.util.List;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryEnderChest;
import net.minecraft.item.EnumAction;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import sao.mods.SAOMOD;
import sao.mods.entity.ISAOMob;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemSAOWeapon extends Item
{
    private final EnumSAOWeapon saoWeapon;
    private EntityLivingBase theTarget;
    private double moveX;
    private double moveZ;
    private double posX;
    private double posZ;

    public ItemSAOWeapon(int par1, EnumSAOWeapon par2EnumSAOWeapon)
    {
        super(par1);
        this.saoWeapon = par2EnumSAOWeapon;
        this.moveX = 0.0D;
        this.moveZ = 0.0D;
        this.posX = 0.0D;
        this.posZ = 0.0D;
        this.maxStackSize = 1;
        this.setMaxDamage(par2EnumSAOWeapon.getMaxUses());
        this.setCreativeTab(SAOMOD.saoWeapons);
    }

    @Override
    public boolean hitEntity(ItemStack par1ItemStack, EntityLivingBase par2EntityLivingBase, EntityLivingBase par3EntityLivingBase)
    {
        if (par3EntityLivingBase instanceof EntityPlayer)
        {
        	EntityPlayer var1 = (EntityPlayer)par3EntityLivingBase;
        	if (this.checkSkill(par1ItemStack, var1))
        	{
        		par1ItemStack.damageItem(1, par3EntityLivingBase);
        		if (!this.doingSwordSkill(par1ItemStack) && var1.isSneaking() && var1.getDistanceSqToEntity(par2EntityLivingBase) < 25.0D)
        		{
        			this.setSwordSkill(par1ItemStack, true);
        			this.theTarget = par2EntityLivingBase;
        			this.moveX = par2EntityLivingBase.posX - var1.posX;
        			this.moveZ = par2EntityLivingBase.posZ - var1.posZ;
        			double var2 = Math.sqrt(this.moveX * this.moveX + this.moveZ * this.moveZ) * 0.25D;
        			this.moveX = this.moveX / var2;
        			this.moveZ = this.moveZ / var2;
        		}
        	}
        	else
        	{
        		par1ItemStack.damageItem(10, par3EntityLivingBase);
        		if (par3EntityLivingBase.getRNG().nextInt(3) == 0)
        		{
        			var1.addExhaustion(4);
        		}
        	}
        }
        else
        {
        	par1ItemStack.damageItem(1, par3EntityLivingBase);
        }
        return true;
    }

    @Override
    public float getDamageVsEntity(Entity par1Entity, ItemStack par2ItemStack)
    {
        int var1 = 1;
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

        if (this.doingSwordSkill(par2ItemStack))
        {
        	var1 = var1 / 5 + 1;
        	var2 = var2 / 4;
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
        			if (var3.getSkillName() == this.saoWeapon.getSkillType())
        			{
        				var3.addSkillExperience(var2, 1);
        				if (var3.getSkillLevel(var2) >= this.saoWeapon.getSkillLevel())
        				{
        					return true;
        				}
        			}
        		}
        	}
        }
        return false;
    }

    @Override
    public void onUpdate(ItemStack par1ItemStack, World par2World, Entity par3Entity, int par4, boolean par5)
    {
        super.onUpdate(par1ItemStack, par2World, par3Entity, par4, par5);
        if (par5)
        {
        	if (this.doingSwordSkill(par1ItemStack))
        	{
        		if (par3Entity instanceof EntityPlayer)
        		{
        			int var1 = this.getTimerSkill(par1ItemStack);
        			if (this.theTarget == null || !this.theTarget.isEntityAlive() || par3Entity.getDistanceSqToEntity(this.theTarget) > 36.0D || var1 > 80)
        			{
        				this.setTimerSkill(par1ItemStack, 0);
        				this.setSwordSkill(par1ItemStack, false);
        				return;
        			}

        			EntityPlayer var2 = (EntityPlayer)par3Entity;
        			if (var1 == 48)
        			{
        				this.posX = this.theTarget.posX - this.moveX;
        				this.posZ = this.theTarget.posZ - this.moveZ;
        			}
        			else if (var1 > 48)
        			{
        				var2.setPosition(this.posX, var2.posY, this.posZ);
        			}
        			else
        			{
        				if (var1 % 8 == 0)
        				{
        					var2.swingItem();
        					List var3 = var2.worldObj.selectEntitiesWithinAABB(EntityLiving.class, var2.boundingBox.expand(2.0D, 0.0D, 2.0D), null);
        					Iterator var4 = var3.iterator();
        					while (var4.hasNext())
        					{
        						EntityLiving var5 = (EntityLiving)var4.next();
        						if (var5 != null && var5.isEntityAlive() && var5 != this.theTarget)
        						{
        							var2.attackTargetEntityWithCurrentItem(var5);
        						}
        					}
        					var2.attackTargetEntityWithCurrentItem(this.theTarget);
        				}
        				var2.setPosition(this.theTarget.posX - this.moveX, var2.posY, this.theTarget.posZ - this.moveZ);
        			}
        			if (!par2World.isRemote)
        			{
        				this.setTimerSkill(par1ItemStack, var1 + 1);
        			}
        		}
        	}
        }
        else if (this.getTimerSkill(par1ItemStack) > 0)
        {
        	this.setTimerSkill(par1ItemStack, 0);
        }
        else if (this.doingSwordSkill(par1ItemStack))
        {
        	this.setSwordSkill(par1ItemStack, false);
        }
    }

    private void setSwordSkill(ItemStack par1ItemStack, boolean par2)
    {
        this.checkTag(par1ItemStack);
        NBTTagCompound var1 = par1ItemStack.getTagCompound();
        var1.setBoolean("SwordSkill", par2);
    }

    private boolean doingSwordSkill(ItemStack par1ItemStack)
    {
        this.checkTag(par1ItemStack);
        return par1ItemStack.getTagCompound().getBoolean("SwordSkill");
    }

    private void setTimerSkill(ItemStack par1ItemStack, int par2)
    {
        this.checkTag(par1ItemStack);
        NBTTagCompound var1 = par1ItemStack.getTagCompound();
        var1.setInteger("AcuateTime", par2);
    }

    private int getTimerSkill(ItemStack par1ItemStack)
    {
        this.checkTag(par1ItemStack);
        return par1ItemStack.getTagCompound().getInteger("AcuateTime");
    }

    private boolean checkTag(ItemStack par1ItemStack)
    {
        if (par1ItemStack.hasTagCompound())
        {
        	return true;
        }
        NBTTagCompound var1 = new NBTTagCompound();
        par1ItemStack.setTagCompound(var1);
        var1.setBoolean("SwordSkill", false);
        var1.setInteger("AcuateTime", 0);
        return false;
    }

    @Override
    public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4)
    {
        par3List.add(1, "Durability : " + this.saoWeapon.getMaxUses());
        par3List.add(2, "Slash : " + this.saoWeapon.getSlashElement() + " Thrust : " + this.saoWeapon.getThrustElement());
        par3List.add(3, "Strike : " + this.saoWeapon.getStrikeElement() + " Penetrate : " + this.saoWeapon.getPenetrateElement());
        par3List.add(4, "Earth : " + this.saoWeapon.getQuakeElement() + " Fier : " + this.saoWeapon.getFireElement() + " Shine : " + this.saoWeapon.getShineElement());
        par3List.add(5, "Water : " + this.saoWeapon.getWaterElement() + " Wind : " + this.saoWeapon.getAeroElement() + " Dark : " + this.saoWeapon.getDarkElement());
        par3List.add(6, "Skill : " + this.saoWeapon.getSkillType() + " (Level:" + this.saoWeapon.getSkillLevel() + ")");
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean isFull3D()
    {
        return true;
    }

    @Override
    public EnumAction getItemUseAction(ItemStack par1ItemStack)
    {
        return EnumAction.block;
    }

    @Override
    public boolean hasEffect(ItemStack par1ItemStack)
    {
        return this.doingSwordSkill(par1ItemStack);
    }

    @Override
    public EnumRarity getRarity(ItemStack par1ItemStack)
    {
        return this.doingSwordSkill(par1ItemStack) ? EnumRarity.rare : EnumRarity.common;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister par1IconRegister)
    {
        this.itemIcon = par1IconRegister.registerIcon("sao:" + this.getUnlocalizedName());
    }
}