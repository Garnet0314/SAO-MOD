package garnet.mods.entity.projectile;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.enchantment.EnchantmentThorns;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.packet.Packet70GameEvent;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public abstract class EntityGarnetArrow extends Entity implements IProjectile
{
    private int xTile = -1;
    private int yTile = -1;
    private int zTile = -1;
    private int inTile = 0;
    private int inData = 0;
    private boolean inGround = false;
    public int canBePickedUp = 0;
    public int arrowShake = 0;
    public Entity shootingEntity;
    protected int ticksInGround;
    private int ticksInAir = 0;
    private double damage = 2.0D;
    private int knockbackStrength;

    public EntityGarnetArrow(World par1World)
    {
        super(par1World);
        this.renderDistanceWeight = 10.0D;
        this.setSize(0.5F, 0.5F);
    }

    public EntityGarnetArrow(World par1World, double par2, double par4, double par6)
    {
        super(par1World);
        this.renderDistanceWeight = 10.0D;
        this.setSize(0.5F, 0.5F);
        this.setPosition(par2, par4, par6);
        this.yOffset = 0.0F;
    }

    public EntityGarnetArrow(World par1World, EntityLivingBase par2EntityLivingBase, EntityLivingBase par3EntityLivingBase, float par4, float par5)
    {
        super(par1World);
        this.renderDistanceWeight = 10.0D;
        this.shootingEntity = par2EntityLivingBase;

        if (par2EntityLivingBase instanceof EntityPlayer)
        {
        	this.canBePickedUp = 1;
        }

        double var1 = 1.0D;
        if (par2EntityLivingBase.height > 10.0F)
        {
        	var1 = 0.05D;
        }
        this.posY = par2EntityLivingBase.posY + (double)par2EntityLivingBase.getEyeHeight() - 0.10000000149011612D;
        double var6 = par3EntityLivingBase.posX - par2EntityLivingBase.posX;
        double var8 = par3EntityLivingBase.posY + (double)par3EntityLivingBase.getEyeHeight() * var1 - 0.699999988079071D - this.posY;
        double var10 = par3EntityLivingBase.posZ - par2EntityLivingBase.posZ;
        double var12 = (double)MathHelper.sqrt_double(var6 * var6 + var10 * var10);

        if (var12 >= 1.0E-7D)
        {
        	float var14 = (float)(Math.atan2(var10, var6) * 180.0D / Math.PI) - 90.0F;
        	float var15 = (float)(-(Math.atan2(var8, var12) * 180.0D / Math.PI));
        	double var16 = var6 / var12;
        	double var18 = var10 / var12;
        	this.setLocationAndAngles(par2EntityLivingBase.posX + var16, this.posY, par2EntityLivingBase.posZ + var18, var14, var15);
        	this.yOffset = 0.0F;
        	float var20 = (float)var12 * 0.2F;
        	this.setThrowableHeading(var6, var8 + (double)var20, var10, par4, par5);
        }
    }

    public EntityGarnetArrow(World par1World, EntityLivingBase par2EntityLivingBase, float par3)
    {
        super(par1World);
        this.renderDistanceWeight = 10.0D;
        this.shootingEntity = par2EntityLivingBase;

        if (par2EntityLivingBase instanceof EntityPlayer)
        {
        	this.canBePickedUp = 1;
        }

        this.setSize(0.5F, 0.5F);
        this.setLocationAndAngles(par2EntityLivingBase.posX, par2EntityLivingBase.posY + (double)par2EntityLivingBase.getEyeHeight(), par2EntityLivingBase.posZ, par2EntityLivingBase.rotationYaw, par2EntityLivingBase.rotationPitch);
        this.posX -= (double)(MathHelper.cos(this.rotationYaw / 180.0F * (float)Math.PI) * 0.16F * 6.0F);
        this.posY -= 0.10000000149011612D;
        this.posZ -= (double)(MathHelper.sin(this.rotationYaw / 180.0F * (float)Math.PI) * 0.16F * 6.0F);
        this.setPosition(this.posX, this.posY, this.posZ);
        this.yOffset = 0.0F;
        this.motionX = (double)(-MathHelper.sin(this.rotationYaw / 180.0F * (float)Math.PI) * MathHelper.cos(this.rotationPitch / 180.0F * (float)Math.PI));
        this.motionZ = (double)(MathHelper.cos(this.rotationYaw / 180.0F * (float)Math.PI) * MathHelper.cos(this.rotationPitch / 180.0F * (float)Math.PI));
        this.motionY = (double)(-MathHelper.sin(this.rotationPitch / 180.0F * (float)Math.PI));
        this.setThrowableHeading(this.motionX, this.motionY, this.motionZ, par3 * 1.5F, 1.0F);
    }

    @Override
    protected void entityInit()
    {
        this.dataWatcher.addObject(16, Byte.valueOf((byte)0));
    }

    @Override
    public void setThrowableHeading(double par1, double par3, double par5, float par7, float par8)
    {
        float var9 = MathHelper.sqrt_double(par1 * par1 + par3 * par3 + par5 * par5);
        par1 /= (double)var9;
        par3 /= (double)var9;
        par5 /= (double)var9;
        par1 += this.rand.nextGaussian() * 0.007499999832361937D * (double)par8;
        par3 += this.rand.nextGaussian() * 0.007499999832361937D * (double)par8;
        par5 += this.rand.nextGaussian() * 0.007499999832361937D * (double)par8;
        par1 *= (double)par7;
        par3 *= (double)par7;
        par5 *= (double)par7;
        this.motionX = par1;
        this.motionY = par3;
        this.motionZ = par5;
        float var10 = MathHelper.sqrt_double(par1 * par1 + par5 * par5);
        this.prevRotationYaw = this.rotationYaw = (float)(Math.atan2(par1, par5) * 180.0D / Math.PI);
        this.prevRotationPitch = this.rotationPitch = (float)(Math.atan2(par3, (double)var10) * 180.0D / Math.PI);
        this.ticksInGround = 0;
    }

    @Override
    public void setPositionAndRotation2(double par1, double par3, double par5, float par7, float par8, int par9)
    {
        this.setPosition(par1, par3, par5);
        this.setRotation(par7, par8);
    }

    @Override
    public void setVelocity(double par1, double par3, double par5)
    {
        this.motionX = par1;
        this.motionY = par3;
        this.motionZ = par5;

        if (this.prevRotationPitch == 0.0F && this.prevRotationYaw == 0.0F)
        {
        	float var7 = MathHelper.sqrt_double(par1 * par1 + par5 * par5);
        	this.prevRotationYaw = this.rotationYaw = (float)(Math.atan2(par1, par5) * 180.0D / Math.PI);
        	this.prevRotationPitch = this.rotationPitch = (float)(Math.atan2(par3, (double)var7) * 180.0D / Math.PI);
        	this.prevRotationPitch = this.rotationPitch;
        	this.prevRotationYaw = this.rotationYaw;
        	this.setLocationAndAngles(this.posX, this.posY, this.posZ, this.rotationYaw, this.rotationPitch);
        	this.ticksInGround = 0;
        }
    }

    @Override
    public void onUpdate()
    {
        super.onUpdate();

        if (this.prevRotationPitch == 0.0F && this.prevRotationYaw == 0.0F)
        {
        	float var1 = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionZ * this.motionZ);
        	this.prevRotationYaw = this.rotationYaw = (float)(Math.atan2(this.motionX, this.motionZ) * 180.0D / Math.PI);
        	this.prevRotationPitch = this.rotationPitch = (float)(Math.atan2(this.motionY, (double)var1) * 180.0D / Math.PI);
        }

        int var16 = this.worldObj.getBlockId(this.xTile, this.yTile, this.zTile);

        if (var16 > 0)
        {
        	Block.blocksList[var16].setBlockBoundsBasedOnState(this.worldObj, this.xTile, this.yTile, this.zTile);
        	AxisAlignedBB var2 = Block.blocksList[var16].getCollisionBoundingBoxFromPool(this.worldObj, this.xTile, this.yTile, this.zTile);

        	if (var2 != null && var2.isVecInside(this.worldObj.getWorldVec3Pool().getVecFromPool(this.posX, this.posY, this.posZ)))
        	{
        		this.inGround = true;
        	}
        }

        if (this.arrowShake > 0)
        {
        	--this.arrowShake;
        }

        if (this.inGround)
        {
        	int var18 = this.worldObj.getBlockId(this.xTile, this.yTile, this.zTile);
        	int var19 = this.worldObj.getBlockMetadata(this.xTile, this.yTile, this.zTile);

        	if (var18 == this.inTile && var19 == this.inData)
        	{
        		++this.ticksInGround;

        		if (this.ticksInGround == 1200)
        		{
        			this.setDead();
        		}
        	}
        	else
        	{
        		this.inGround = false;
        		this.motionX *= (double)(this.rand.nextFloat() * 0.2F);
        		this.motionY *= (double)(this.rand.nextFloat() * 0.2F);
        		this.motionZ *= (double)(this.rand.nextFloat() * 0.2F);
        		this.ticksInGround = 0;
        		this.ticksInAir = 0;
        	}
        }
        else
        {
        	++this.ticksInAir;
        	Vec3 var17 = this.worldObj.getWorldVec3Pool().getVecFromPool(this.posX, this.posY, this.posZ);
        	Vec3 var3 = this.worldObj.getWorldVec3Pool().getVecFromPool(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);
        	MovingObjectPosition var4 = this.worldObj.rayTraceBlocks_do_do(var17, var3, false, true);
        	var17 = this.worldObj.getWorldVec3Pool().getVecFromPool(this.posX, this.posY, this.posZ);
        	var3 = this.worldObj.getWorldVec3Pool().getVecFromPool(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);

        	if (var4 != null)
        	{
        		var3 = this.worldObj.getWorldVec3Pool().getVecFromPool(var4.hitVec.xCoord, var4.hitVec.yCoord, var4.hitVec.zCoord);
        	}

        	Entity var5 = null;
        	List var6 = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, this.boundingBox.addCoord(this.motionX, this.motionY, this.motionZ).expand(1.0D, 1.0D, 1.0D));
        	double var7 = 0.0D;
        	int var9;
        	float var11;

        	for (var9 = 0; var9 < var6.size(); ++var9)
        	{
        		Entity var10 = (Entity)var6.get(var9);

        		if (var10.canBeCollidedWith() && (var10 != this.shootingEntity || this.ticksInAir >= 5))
        		{
        			var11 = 0.3F;
        			AxisAlignedBB var12 = var10.boundingBox.expand((double)var11, (double)var11, (double)var11);
        			MovingObjectPosition var13 = var12.calculateIntercept(var17, var3);

        			if (var13 != null)
        			{
        				double var14 = var17.distanceTo(var13.hitVec);

        				if (var14 < var7 || var7 == 0.0D)
        				{
        					var5 = var10;
        					var7 = var14;
        				}
        			}
        		}
        	}

        	if (var5 != null)
        	{
        		var4 = new MovingObjectPosition(var5);
        	}

        	float var20;
        	float var26;

        	if (var4 != null)
        	{
        		this.onImpact(var4);
        	}

        	if (this.getIsCritical())
        	{
        		for (var9 = 0; var9 < 4; ++var9)
        		{
        			this.worldObj.spawnParticle("crit", this.posX + this.motionX * (double)var9 / 4.0D, this.posY + this.motionY * (double)var9 / 4.0D, this.posZ + this.motionZ * (double)var9 / 4.0D, -this.motionX, -this.motionY + 0.2D, -this.motionZ);
        		}
        	}

        	this.posX += this.motionX;
        	this.posY += this.motionY;
        	this.posZ += this.motionZ;
        	var20 = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionZ * this.motionZ);
        	this.rotationYaw = (float)(Math.atan2(this.motionX, this.motionZ) * 180.0D / Math.PI);

        	for (this.rotationPitch = (float)(Math.atan2(this.motionY, (double)var20) * 180.0D / Math.PI); this.rotationPitch - this.prevRotationPitch < -180.0F; this.prevRotationPitch -= 360.0F)
        	{
        		;
        	}

        	while (this.rotationPitch - this.prevRotationPitch >= 180.0F)
        	{
        		this.prevRotationPitch += 360.0F;
        	}

        	while (this.rotationYaw - this.prevRotationYaw < -180.0F)
        	{
        		this.prevRotationYaw -= 360.0F;
        	}

        	while (this.rotationYaw - this.prevRotationYaw >= 180.0F)
        	{
        		this.prevRotationYaw += 360.0F;
        	}

        	this.rotationPitch = this.prevRotationPitch + (this.rotationPitch - this.prevRotationPitch) * 0.2F;
        	this.rotationYaw = this.prevRotationYaw + (this.rotationYaw - this.prevRotationYaw) * 0.2F;
        	float var22 = 0.99F;
        	var11 = 0.05F;

        	if (this.isInWater())
        	{
        		for (int var25 = 0; var25 < 4; ++var25)
        		{
        			var26 = 0.25F;
        			this.worldObj.spawnParticle("bubble", this.posX - this.motionX * (double)var26, this.posY - this.motionY * (double)var26, this.posZ - this.motionZ * (double)var26, this.motionX, this.motionY, this.motionZ);
        		}

        		var22 = 0.8F;
        	}

        	this.motionX *= (double)var22;
        	this.motionY *= (double)var22;
        	this.motionZ *= (double)var22;
        	this.motionY -= (double)var11;
        	this.setPosition(this.posX, this.posY, this.posZ);
        	this.doBlockCollisions();
        }
    }

    public void onImpact(MovingObjectPosition par1MovingObjectPosition)
    {
        float var20;
        float var26;
        if (par1MovingObjectPosition.entityHit != null)
        {
        	var20 = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionY * this.motionY + this.motionZ * this.motionZ);
        	int var23 = MathHelper.ceiling_double_int((double)var20 * this.getDamage(par1MovingObjectPosition.entityHit));

        	if (this.getIsCritical())
        	{
        		var23 += this.rand.nextInt(var23 / 2 + 2);
        	}

        	DamageSource var21 = null;
        	DamageSource var22 = null;

        	if (this.shootingEntity == null)
        	{
        		var21 = DamageSource.causeThrownDamage(this, this);
        	}
        	else
        	{
        		var21 = DamageSource.causeThrownDamage(this, this.shootingEntity);
        	}

        	if (this.isBurning() && !(par1MovingObjectPosition.entityHit instanceof EntityEnderman))
        	{
        		par1MovingObjectPosition.entityHit.setFire(5);
        	}

        	par1MovingObjectPosition.entityHit.hurtResistantTime = 0;
        	if (par1MovingObjectPosition.entityHit.attackEntityFrom(var21, var23))
        	{
        		if (par1MovingObjectPosition.entityHit instanceof EntityLivingBase)
        		{
        			EntityLivingBase var24 = (EntityLivingBase)par1MovingObjectPosition.entityHit;

        			if (!this.worldObj.isRemote)
        			{
        				var24.setArrowCountInEntity(var24.getArrowCountInEntity() + 1);
        			}

        			if (this.knockbackStrength > 0)
        			{
        				var26 = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionZ * this.motionZ);

        				if (var26 > 0.0F)
        				{
        					par1MovingObjectPosition.entityHit.addVelocity(this.motionX * (double)this.knockbackStrength * 0.6000000238418579D / (double)var26, 0.1D, this.motionZ * (double)this.knockbackStrength * 0.6000000238418579D / (double)var26);
        				}
        			}

        			if (this.shootingEntity != null)
        			{
        				EnchantmentThorns.func_92096_a(this.shootingEntity, var24, this.rand);
        			}

        			if (this.shootingEntity != null && par1MovingObjectPosition.entityHit != this.shootingEntity && par1MovingObjectPosition.entityHit instanceof EntityPlayer && this.shootingEntity instanceof EntityPlayerMP)
        			{
        				((EntityPlayerMP)this.shootingEntity).playerNetServerHandler.sendPacketToPlayer(new Packet70GameEvent(6, 0));
        			}
        		}

        		this.playSound("random.bowhit", 1.0F, 1.2F / (this.rand.nextFloat() * 0.2F + 0.9F));

        		if (!(par1MovingObjectPosition.entityHit instanceof EntityEnderman))
        		{
        			this.setDead();
        		}
        	}
        	else
        	{
        		this.motionX *= -0.10000000149011612D;
        		this.motionY *= -0.10000000149011612D;
        		this.motionZ *= -0.10000000149011612D;
        		this.rotationYaw += 180.0F;
        		this.prevRotationYaw += 180.0F;
        		this.ticksInAir = 0;
        	}
        }
        else
        {
        	this.xTile = par1MovingObjectPosition.blockX;
        	this.yTile = par1MovingObjectPosition.blockY;
        	this.zTile = par1MovingObjectPosition.blockZ;
        	this.inTile = this.worldObj.getBlockId(this.xTile, this.yTile, this.zTile);
        	this.inData = this.worldObj.getBlockMetadata(this.xTile, this.yTile, this.zTile);
        	this.motionX = (double)((float)(par1MovingObjectPosition.hitVec.xCoord - this.posX));
        	this.motionY = (double)((float)(par1MovingObjectPosition.hitVec.yCoord - this.posY));
        	this.motionZ = (double)((float)(par1MovingObjectPosition.hitVec.zCoord - this.posZ));
        	var20 = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionY * this.motionY + this.motionZ * this.motionZ);
        	this.posX -= this.motionX / (double)var20 * 0.05000000074505806D;
        	this.posY -= this.motionY / (double)var20 * 0.05000000074505806D;
        	this.posZ -= this.motionZ / (double)var20 * 0.05000000074505806D;
        	this.playSound("random.bowhit", 1.0F, 1.2F / (this.rand.nextFloat() * 0.2F + 0.9F));
        	this.inGround = true;
        	this.arrowShake = 7;
        	this.setIsCritical(false);
        	if (this.isBurning())
        	{
        		int var2 = par1MovingObjectPosition.blockX;
        		int var3 = par1MovingObjectPosition.blockY;
        		int var4 = par1MovingObjectPosition.blockZ;

        		switch (par1MovingObjectPosition.sideHit)
        		{
        			case 0:
        				--var3;
        				break;

        			case 1:
        				++var3;
        				break;

        			case 2:
        				--var4;
        				break;

        			case 3:
        				++var4;
        				break;

        			case 4:
        				--var2;
        				break;

        			case 5:
        				++var2;
        		}

        		if (this.worldObj.isAirBlock(var2, var3, var4))
        		{
        			this.worldObj.setBlock(var2, var3, var4, Block.fire.blockID);
        		}
        	}

        	if (this.inTile != 0)
        	{
        		Block.blocksList[this.inTile].onEntityCollidedWithBlock(this.worldObj, this.xTile, this.yTile, this.zTile, this);
        	}
        }
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound)
    {
        par1NBTTagCompound.setShort("xTile", (short)this.xTile);
        par1NBTTagCompound.setShort("yTile", (short)this.yTile);
        par1NBTTagCompound.setShort("zTile", (short)this.zTile);
        par1NBTTagCompound.setByte("inTile", (byte)this.inTile);
        par1NBTTagCompound.setByte("inData", (byte)this.inData);
        par1NBTTagCompound.setByte("shake", (byte)this.arrowShake);
        par1NBTTagCompound.setByte("inGround", (byte)(this.inGround ? 1 : 0));
        par1NBTTagCompound.setByte("pickup", (byte)this.canBePickedUp);
        par1NBTTagCompound.setDouble("damage", this.damage);
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound)
    {
        this.xTile = par1NBTTagCompound.getShort("xTile");
        this.yTile = par1NBTTagCompound.getShort("yTile");
        this.zTile = par1NBTTagCompound.getShort("zTile");
        this.inTile = par1NBTTagCompound.getByte("inTile") & 255;
        this.inData = par1NBTTagCompound.getByte("inData") & 255;
        this.arrowShake = par1NBTTagCompound.getByte("shake") & 255;
        this.inGround = par1NBTTagCompound.getByte("inGround") == 1;

        if (par1NBTTagCompound.hasKey("damage"))
        {
        	this.damage = par1NBTTagCompound.getDouble("damage");
        }

        if (par1NBTTagCompound.hasKey("pickup"))
        {
        	this.canBePickedUp = par1NBTTagCompound.getByte("pickup");
        }
        else if (par1NBTTagCompound.hasKey("player"))
        {
        	this.canBePickedUp = par1NBTTagCompound.getBoolean("player") ? 1 : 0;
        }
    }

    @Override
    public void onCollideWithPlayer(EntityPlayer par1EntityPlayer)
    {
        if (!this.worldObj.isRemote && this.inGround && this.arrowShake <= 0)
        {
        	boolean var2 = this.canBePickedUp == 1 || this.canBePickedUp == 2 && par1EntityPlayer.capabilities.isCreativeMode;

        	if (this.canBePickedUp == 1 && !par1EntityPlayer.inventory.addItemStackToInventory(new ItemStack(this.getItemID(), 1, 0)))
        	{
        		var2 = false;
        	}

        	if (var2)
        	{
        		this.playSound("random.pop", 0.2F, ((this.rand.nextFloat() - this.rand.nextFloat()) * 0.7F + 1.0F) * 2.0F);
        		par1EntityPlayer.onItemPickup(this, 1);
        		this.setDead();
        	}
        }
    }

    @Override
    protected boolean canTriggerWalking()
    {
        return false;
    }

    @Override
    public float getShadowSize()
    {
        return 0.0F;
    }

    public void setDamage(double par1)
    {
        this.damage = par1;
    }

    public double getBaseDamage()
    {
        return this.damage;
    }

    public double getDamage(Entity par1Entity)
    {
        return this.damage;
    }

    public void setKnockbackStrength(int par1)
    {
        this.knockbackStrength = par1;
    }

    public int getKnockbackStrength()
    {
        return this.knockbackStrength;
    }

    @Override
    public boolean canAttackWithItem()
    {
        return false;
    }

    public void setIsCritical(boolean par1)
    {
        byte var2 = this.dataWatcher.getWatchableObjectByte(16);

        if (par1)
        {
        	this.dataWatcher.updateObject(16, Byte.valueOf((byte)(var2 | 1)));
        }
        else
        {
        	this.dataWatcher.updateObject(16, Byte.valueOf((byte)(var2 & -2)));
        }
    }

    public boolean getIsCritical()
    {
        byte var1 = this.dataWatcher.getWatchableObjectByte(16);
        return (var1 & 1) != 0;
    }

    protected int getItemID()
    {
        return Item.arrow.itemID;
    }
}