package sao.mods.item;

import java.util.List;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.Entity;
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
import sao.mods.entity.EntitySwordSkill;
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
        this.setCreativeTab(SAOMOD.saoWeapons);
    }

    //TODO ダメージ処理
    @Override
    public boolean hitEntity(ItemStack par1ItemStack, EntityLivingBase par2EntityLivingBase, EntityLivingBase par3EntityLivingBase)
    {
        if (par3EntityLivingBase instanceof EntityPlayer)
        {
        	EntityPlayer var1 = (EntityPlayer)par3EntityLivingBase;
        	if (this.checkSkill(par1ItemStack, var1))
        	{
        		par1ItemStack.damageItem(1, par3EntityLivingBase);
        	}
        	else
        	{
        		par1ItemStack.damageItem(10, par3EntityLivingBase);
        		if (par3EntityLivingBase.getRNG().nextInt(3) == 0)
        		{
        			var1.addExhaustion(4.0F);
        		}
        	}
        }
        else
        {
        	par1ItemStack.damageItem(1, par3EntityLivingBase);
        }
        return true;
    }

    //適切なスキルを所持しているか
    private boolean checkSkill(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer)
    {
        //TODO 専用のインベントリを準備
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


    public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
    {
        //TODO いずれはスキル所持を確認
        if (!this.doingSwordSkill(par1ItemStack) && (this.checkSkill(par1ItemStack, par3EntityPlayer) || true))
        {
        	this.setSwordSkill(par1ItemStack, true);
        }
        return par1ItemStack;
    }

    @Override
    public void onUpdate(ItemStack par1ItemStack, World par2World, Entity par3Entity, int par4, boolean par5)
    {
        super.onUpdate(par1ItemStack, par2World, par3Entity, par4, par5);
        if (par5)
        {
        	//ソードスキル発動中か
        	if (this.doingSwordSkill(par1ItemStack))
        	{
        		if (par3Entity instanceof EntityPlayer)
        		{
        			int var1 = this.getTimerSkill(par1ItemStack);
        			this.swordSkill(par1ItemStack, par2World, (EntityPlayer)par3Entity, var1);
        			this.setTimerSkill(par1ItemStack, var1 + 1);
        		}
        	}
        }
        //手持ちでないなら初期化
        else if (this.getTimerSkill(par1ItemStack) > 0)
        {
        	this.setTimerSkill(par1ItemStack, 0);
        }
        else if (this.doingSwordSkill(par1ItemStack))
        {
        	this.setSwordSkill(par1ItemStack, false);
        }
    }

    //ソードスキル発動or停止
    private void setSwordSkill(ItemStack par1ItemStack, boolean par2)
    {
        this.checkTag(par1ItemStack);
        NBTTagCompound var1 = par1ItemStack.getTagCompound();
        var1.setBoolean("SwordSkill", par2);
        this.setTimerSkill(par1ItemStack, 0);
    }

    //ソードスキル発動中か
    private boolean doingSwordSkill(ItemStack par1ItemStack)
    {
        this.checkTag(par1ItemStack);
        return par1ItemStack.getTagCompound().getBoolean("SwordSkill");
    }

    //ソードスキル発動時間セット
    private void setTimerSkill(ItemStack par1ItemStack, int par2)
    {
        this.checkTag(par1ItemStack);
        NBTTagCompound var1 = par1ItemStack.getTagCompound();
        var1.setInteger("AcuateTime", par2);
    }

    //ソードスキル発動時間取得
    private int getTimerSkill(ItemStack par1ItemStack)
    {
        this.checkTag(par1ItemStack);
        return par1ItemStack.getTagCompound().getInteger("AcuateTime");
    }

    //初期化用
    private boolean checkTag(ItemStack par1ItemStack)
    {
        if (par1ItemStack.hasTagCompound() && par1ItemStack.stackTagCompound.hasKey("SwordSkill") && par1ItemStack.stackTagCompound.hasKey("AcuateTime"))
        {
        	return true;
        }
        NBTTagCompound var1 = new NBTTagCompound();
        par1ItemStack.setTagCompound(var1);
        var1.setBoolean("SwordSkill", false);
        var1.setInteger("AcuateTime", 0);
        return false;
    }

    //表示
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
    @SideOnly(Side.CLIENT)
    public boolean shouldRotateAroundWhenRendering()
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

    /* ========== 武器の種類 ========== */
    public float getSlashElement()
    {
        return this.saoWeapon.getSlashElement();
    }

    public float getThrustElement()
    {
        return this.saoWeapon.getThrustElement();
    }

    public float getStrikeElement()
    {
        return this.saoWeapon.getStrikeElement();
    }

    public float getPenetrateElement()
    {
        return this.saoWeapon.getPenetrateElement();
    }

    /* ========== 武器の属性 ========== */
    public float getQuakeElement()
    {
        return this.saoWeapon.getQuakeElement();
    }

    public float getWaterElement()
    {
        return this.saoWeapon.getWaterElement();
    }

    public float getFireElement()
    {
        return this.saoWeapon.getFireElement();
    }

    public float getAeroElement()
    {
        return this.saoWeapon.getAeroElement();
    }

    public float getShineElement()
    {
        return this.saoWeapon.getShineElement();
    }

    public float getDarkElement()
    {
        return this.saoWeapon.getDarkElement();
    }

    /* ========== ソードスキル処理部分 ========== */
    //TODO いずれはスキルのクラスで処理を行う
    protected void swordSkill(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer, int par4)
    {
        if (par4 > 20)
        {
        	this.setSwordSkill(par1ItemStack, false);
        }
        else if (par4 < 3)
        {
        	EntitySwordSkill var1 = new EntitySwordSkill(par2World, par3EntityPlayer, 10.0F - 10.0F * par4, 10.0F - 10.0F * par4, 1.5, 1);
        	var1.setAcceleration(-0.1F);
        	var1.setLimitSpeed(0.0F);
        	var1.setType(1);
        	var1.setElement(0);
        	var1.setSkillSize(0.1F);
        	if (!par2World.isRemote)
        	{
        		par2World.spawnEntityInWorld(var1);
        	}
        }
        else if (par4 < 5)
        {
        	EntitySwordSkill var1 = new EntitySwordSkill(par2World, par3EntityPlayer, -10.0F + 20.0F * (par4 - 3), 10.0F - 20.0F * (par4 - 3), 1.5, 1);
        	var1.setAcceleration(-0.1F);
        	var1.setLimitSpeed(0.0F);
        	var1.setType(1);
        	var1.setElement(0);
        	var1.setSkillSize(0.1F);
        	if (!par2World.isRemote)
        	{
        		par2World.spawnEntityInWorld(var1);
        	}
        }
        else if (par4 < 8)
        {
        	EntitySwordSkill var1 = new EntitySwordSkill(par2World, par3EntityPlayer, 10.0F - 10.0F * (par4 - 5), 10.0F - 10.0F * (par4 - 5), 1.5, 1);
        	var1.setAcceleration(-0.1F);
        	var1.setLimitSpeed(0.0F);
        	var1.setType(1);
        	var1.setElement(0);
        	var1.setSkillSize(0.1F);
        	if (!par2World.isRemote)
        	{
        		par2World.spawnEntityInWorld(var1);
        	}
        }
        else if (par4 < 10)
        {
        	EntitySwordSkill var1 = new EntitySwordSkill(par2World, par3EntityPlayer, -10.0F + 20.0F * (par4 - 8), 10.0F - 20.0F * (par4 - 8), 1.5, 1);
        	var1.setAcceleration(-0.1F);
        	var1.setLimitSpeed(0.0F);
        	var1.setType(1);
        	var1.setElement(0);
        	var1.setSkillSize(0.1F);
        	if (!par2World.isRemote)
        	{
        		par2World.spawnEntityInWorld(var1);
        	}
        }
        else if (par4 == 15)
        {
        	EntitySwordSkill var1 = new EntitySwordSkill(par2World, par3EntityPlayer, 0, 0, 2.5, 1);
        	var1.setAcceleration(-0.3F);
        	var1.setLimitSpeed(0.0F);
        	var1.setType(1);
        	var1.setElement(0);
        	var1.setSkillSize(0.2F);
        	if (!par2World.isRemote)
        	{
        		par2World.spawnEntityInWorld(var1);
        	}
        }
    }
}