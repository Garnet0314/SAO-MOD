package garnet.mods.item;

import garnet.mods.entity.projectile.EntityGarnetBullet;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.world.World;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.FMLCommonHandler;

public abstract class ItemGarnetGun extends ItemBow
{
    protected int reloadTime;
    protected int bulletID;
    protected int reloadRatio;
    protected boolean isFullAuto;
    protected float bulletSpeed;
    protected int bulletForce;
    private byte doFullAuto;

    public ItemGarnetGun(int par1)
    {
        super(par1);
        this.maxStackSize = 1;
        this.doFullAuto = 0;
    }

    @Override
    public boolean isFull3D()
    {
        return true;
    }

    @Override
    public void onPlayerStoppedUsing(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer, int par4)
    {
        if (!this.isReload(par1ItemStack) && !this.isWeaponFullAuto(par1ItemStack))
        {
        	if (par1ItemStack.getItemDamage() < this.getMaxDamage())
        	{
        		if (this.fireBullet(par1ItemStack, par2World, par3EntityPlayer, this.bulletSpeed, this.itemRand.nextFloat() * -3.0F, 30.0F))
        		{
        			par1ItemStack.damageItem(1, par3EntityPlayer);
        		}
        	}
        }
        this.cancelReload(par1ItemStack, 0x4000);
    }

    @Override
    public ItemStack onEaten(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
    {
        this.reloadMagazine(par1ItemStack, par2World, par3EntityPlayer);
        return par1ItemStack;
    }

    @Override
    public int getMaxItemUseDuration(ItemStack par1ItemStack)
    {
        int var1 = this.getReload(par1ItemStack);
        if (var1 > -1 && (var1 & 0xf000) < 0x4000)
        {
        	return this.reloadTime();
        }
        else
        {
        	return super.getMaxItemUseDuration(par1ItemStack);
        }
    }

    @Override
    public EnumAction getItemUseAction(ItemStack par1ItemStack)
    {
        return this.isReload(par1ItemStack) ? EnumAction.block : EnumAction.bow;
    }

    @Override
    public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
    {
        this.resetBolt(par1ItemStack);
        int var1 = this.getReload(par1ItemStack);
        if (var1 == -1)
        {
        	if (this.canReload(par1ItemStack, par3EntityPlayer))
        	{
        		if (this.isEmpty(par1ItemStack))
        		{
        			this.setReload(par1ItemStack, 0);
        			var1 = 0;
        		}
        	}
        }
        if (var1 == 0)
        {
        	this.releaseMagazine(par1ItemStack, par2World, par3EntityPlayer);
        }
        par3EntityPlayer.setItemInUse(par1ItemStack, this.getMaxItemUseDuration(par1ItemStack));
        return par1ItemStack;
    }

    @Override
    public void onUpdate(ItemStack par1ItemStack, World par2World, Entity par3Entity, int par4, boolean par5)
    {
        if (!this.isReload(par1ItemStack) && this.isWeaponFullAuto(par1ItemStack))
        {
        	if (par3Entity != null && par3Entity instanceof EntityPlayer)
        	{
        		EntityPlayer var1EntityPlayer = (EntityPlayer)par3Entity;
        		if (var1EntityPlayer.isUsingItem() && par1ItemStack == var1EntityPlayer.getCurrentEquippedItem() && this.cycleBolt(par1ItemStack))
        		{
        			if (par1ItemStack.getItemDamage() < this.getMaxDamage())
        			{
        				try
        				{
        					if (FMLClientHandler.instance().getClient().gameSettings.keyBindUseItem.pressed)
        					{
        						FMLCommonHandler.instance().getSidedDelegate().sendPacket(new Packet250CustomPayload("GarnetGun", new byte[]{(byte)0x0010}));
        					}
        				}
        				catch(Error e){}
        				catch(Exception e){}
        				if (this.getDoFullAuto(par1ItemStack))
        				{
        					if (this.fireBullet(par1ItemStack, par2World, var1EntityPlayer, this.bulletSpeed, this.itemRand.nextFloat() * -0.3F, 35.8F))
        					{
        						par1ItemStack.damageItem(1, var1EntityPlayer);
        					}
        				}
        				this.setDoFullAuto(par1ItemStack, (byte)0x0000);
        			}
        			else if(this.canReload(par1ItemStack, var1EntityPlayer))
        			{
        				var1EntityPlayer.stopUsingItem();
        			}
        		}
        	}
        }
        super.onUpdate(par1ItemStack, par2World, par3Entity, par4, par5);
    }

    private int reloadTime()
    {
        return this.reloadTime;
    }

    private boolean canReload(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer)
    {
        if (par2EntityPlayer.capabilities.isCreativeMode || par2EntityPlayer.inventory.hasItem(this.getBulletID(par1ItemStack)))
        {
        	return true;
        }
        return false;
    }

    private boolean isReload(ItemStack par1ItemStack)
    {
        return this.getReload(par1ItemStack) > -1;
    }

    private void setReload(ItemStack par1ItemStack, int par2)
    {
        this.checkTag(par1ItemStack);
        NBTTagCompound var1 = par1ItemStack.getTagCompound();
        var1.setInteger("Reload", par2);
    }

    private int getReload(ItemStack par1ItemStack)
    {
        this.checkTag(par1ItemStack);
        NBTTagCompound var1 = par1ItemStack.getTagCompound();
        return var1.getInteger("Reload");
    }

    private void cancelReload(ItemStack par1ItemStack, int par2)
    {
        if (this.getReload(par1ItemStack) >= par2)
        {
        	this.setReload(par1ItemStack, -1);
        }
    }

    private boolean isEmpty(ItemStack par1ItemStack)
    {
        return par1ItemStack.getItemDamage() >= this.getMaxDamage();
    }

    private void releaseMagazine(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
    {
        this.setReload(par1ItemStack, (0x1000 | par1ItemStack.getItemDamage()));
        par1ItemStack.setItemDamage(this.getMaxDamage());
    }

    public void reloadMagazine(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
    {
        if (!par2World.isRemote)
        {
        	if (par3EntityPlayer == null || par3EntityPlayer.capabilities.isCreativeMode || EnchantmentHelper.getEnchantmentLevel(Enchantment.infinity.effectId, par1ItemStack) > 0)
        	{
        		par1ItemStack.setItemDamage(0);
        	}
        	else
        	{
        		int var1 = this.getReload(par1ItemStack);
        		var1 = (var1 > 0) ? var1 & 0x0fff : 0;
        		int var2 = 0;
        		while (true)
        		{
        			if (var1 == 0 || !par3EntityPlayer.inventory.hasItem(this.getBulletID(par1ItemStack)))
        			{
        				break;
        			}
        			var1--;
        			if (++var2 >= this.reloadRatio)
        			{
        				var2 = 0;
        				par3EntityPlayer.inventory.consumeInventoryItem(this.getBulletID(par1ItemStack));
        			}
        		}
        		par1ItemStack.setItemDamage(var1);
        	}
        }
        this.setReload(par1ItemStack, 0x4000);
    }

    private int getBulletID(ItemStack par1ItemStack)
    {
        return this.bulletID;
    }

    protected boolean fireBullet(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer, float par4, float par5, float par6)
    {
        if (!par2World.isRemote)
        {
        	EntityGarnetBullet var1 = new EntityGarnetBullet(par2World, par3EntityPlayer, par4);

        	int var2 = EnchantmentHelper.getEnchantmentLevel(Enchantment.power.effectId, par1ItemStack) + this.bulletForce;
        	if (var2 > 0)
        	{
        		var1.setDamage(var1.getBaseDamage() + (double)var2 * 0.5D + 0.5D);
        	}

        	int var3 = EnchantmentHelper.getEnchantmentLevel(Enchantment.punch.effectId, par1ItemStack);
        	if (var3 > 0)
        	{
        		var1.setKnockback(var3);
        	}

        	if (EnchantmentHelper.getEnchantmentLevel(Enchantment.flame.effectId, par1ItemStack) > 0)
        	{
        		var1.setFire(100);
        	}

        	par2World.playSoundAtEntity(par3EntityPlayer, "random.bow", 1.0F, 1.0F / (itemRand.nextFloat() * 0.4F + 1.2F) + par4 * 0.125F);
        	par2World.spawnEntityInWorld(var1);
        }
        par3EntityPlayer.rotationPitch += par5;
        par3EntityPlayer.rotationYaw = par3EntityPlayer.rotationYaw + (this.itemRand.nextFloat() - 0.5F) * par5;
        return (EnchantmentHelper.getEnchantmentLevel(Enchantment.infinity.effectId, par1ItemStack) <= 0) && !par3EntityPlayer.capabilities.isCreativeMode;
    }

    private boolean isWeaponFullAuto(ItemStack par1ItemStack)
    {
        return this.isFullAuto;
    }

    private void resetBolt(ItemStack par1ItemStack)
    {
        this.checkTag(par1ItemStack);
        par1ItemStack.getTagCompound().setBoolean("Bolt", false);
    }

    private boolean cycleBolt(ItemStack par1ItemStack)
    {
        this.checkTag(par1ItemStack);
        NBTTagCompound var1 = par1ItemStack.getTagCompound();
        boolean var2 = !(var1.getBoolean("Bolt"));
        var1.setBoolean("Bolt", var2);
        return var2;
    }

    private boolean getBolt(ItemStack par1ItemStack)
    {
        this.checkTag(par1ItemStack);
        NBTTagCompound var1 = par1ItemStack.getTagCompound();
        return var1.getBoolean("Bolt");
    }

    public void setDoFullAuto(ItemStack par1ItemStack, byte par2)
    {
        this.checkTag(par1ItemStack);
        NBTTagCompound var1 = par1ItemStack.getTagCompound();
        var1.setByte("FullAuto", par2);
    }

    private boolean getDoFullAuto(ItemStack par1ItemStack)
    {
        this.checkTag(par1ItemStack);
        return par1ItemStack.getTagCompound().getByte("FullAuto") == (byte)0x0010;
    }

    private boolean checkTag(ItemStack par1ItemStack)
    {
        if (par1ItemStack.hasTagCompound())
        {
        	return true;
        }
        NBTTagCompound var1 = new NBTTagCompound();
        par1ItemStack.setTagCompound(var1);
        var1.setInteger("Reload", -1);
        var1.setBoolean("Bolt", false);
        var1.setByte("FullAuto", (byte)0x0000);
        return false;
    }
}