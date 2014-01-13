package garnet.mods.entity.passive;

import net.minecraft.block.Block;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnchantmentThorns;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.passive.IAnimals;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public abstract class EntityGarnetBase extends EntityCreature implements IAnimals
{
    private int healTime;

    public EntityGarnetBase(World par1World)
    {
        super(par1World);
        this.setSize(0.6F, 1.8F);
        this.healTime = 0;
    }

    @Override
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getAttributeMap().func_111150_b(SharedMonsterAttributes.attackDamage);
    }

    protected abstract String getTexture();

    public String getTextureMode()
    {
        return "textures/mobs/" + this.getTexture() + ".png";
    }

    public abstract String getTextureLocal();

    @Override
    public void onLivingUpdate()
    {
        if (!this.worldObj.isRemote)
        {
        	this.autoHealing();
        }
        super.onLivingUpdate();
    }

    @Override
    protected boolean isAIEnabled()
    {
        return true;
    }

    @Override
    public float getEyeHeight()
    {
        return this.height * 0.8F;
    }

    @Override
    protected float getSoundVolume()
    {
        return 1.0F;
    }

    @Override
    public boolean attackEntityFrom(DamageSource par1DamageSource, float par2)
    {
        Entity var3 = par1DamageSource.getEntity();
        if (!(var3 instanceof EntityPlayer) && !par1DamageSource.isFireDamage())
        {
        	par2 = MathHelper.floor_double((double)(par2 * (100 - this.getProtectStrength()) / 100));
        }
        return super.attackEntityFrom(par1DamageSource, par2);
    }

    protected int getProtectStrength()
    {
        return 0;
    }

    @Override
    public boolean attackEntityAsMob(Entity par1Entity)
    {
        float var1 = this.getAttackStrength();
        int var2 = 0;

        if (this.isPotionActive(Potion.damageBoost))
        {
        	var1 += 3 << this.getActivePotionEffect(Potion.damageBoost).getAmplifier();
        }

        if (this.isPotionActive(Potion.weakness))
        {
        	var1 -= 2 <<this.getActivePotionEffect(Potion.weakness).getAmplifier();
        }

        if (par1Entity instanceof EntityLivingBase)
        {
        	var1 += EnchantmentHelper.getEnchantmentModifierLiving(this, (EntityLivingBase)par1Entity);
        	var2 += EnchantmentHelper.getKnockbackModifier(this, (EntityLivingBase)par1Entity);
        }

        boolean var3 = par1Entity.attackEntityFrom(DamageSource.causeMobDamage(this), var1);

        if (var3)
        {
        	if (var2 > 0)
        	{
        		par1Entity.addVelocity((double)(-MathHelper.sin(this.rotationYaw * (float)Math.PI / 180.0F) * (float)var2 * 0.5F), 0.1D, (double)(MathHelper.cos(this.rotationYaw * (float)Math.PI / 180.0F) * (float)var2 * 0.5F));
        		this.motionX *= 0.6D;
        		this.motionZ *= 0.6D;
        	}

        	int var4 = EnchantmentHelper.getFireAspectModifier(this);

        	if (var4 > 0)
        	{
        		par1Entity.setFire(var4 * 4);
        	}

        	if (par1Entity instanceof EntityLivingBase)
        	{
        		EnchantmentThorns.func_92096_a(this, (EntityLivingBase)par1Entity, this.rand);
        	}
        }

        return var3;
    }

    protected float getAttackStrength()
    {
        return (float)this.getEntityAttribute(SharedMonsterAttributes.attackDamage).getAttributeValue();
    }

    @Override
    protected void entityInit()
    {
        super.entityInit();
        this.dataWatcher.addObject(18, Byte.valueOf((byte)0));
    }

    @Override
    protected int getDropItemId()
    {
        return 0;
    }

    @Override
    protected void dropFewItems(boolean par1, int par2)
    {
        int var1 = this.getDropItemId();
        if (var1 > 0)
        {
        	this.dropItem(var1, 1);
        }
    }

    @Override
    protected void dropEquipment(boolean par1, int par2){}

    public void chatMessage(String par1Str){}

    public abstract String getOwnerName();

    public abstract EntityPlayer getOwner();

    public boolean isFlying()
    {
        return this.dataWatcher.getWatchableObjectByte(18) == 1;
    }

    public void setFlying(boolean par1)
    {
        if (par1)
        {
        	this.dataWatcher.updateObject(18, Byte.valueOf((byte)1));
        }
        else
        {
        	this.dataWatcher.updateObject(18, Byte.valueOf((byte)0));
        }
    }

    @Override
    protected void fall(float par1)
    {
        if (!this.isFlying())
        {
        	super.fall(par1);
        }
    }

    @Override
    protected void updateFallState(double par1, boolean par3)
    {
        if (!this.isFlying())
        {
        	super.updateFallState(par1, par3);
        }
    }

    @Override
    public void moveEntityWithHeading(float par1, float par2)
    {
        if (this.isFlying())
        {
        	if (this.isInWater())
        	{
        		this.moveFlying(par1, par2, 0.02F);
        		this.moveEntity(this.motionX, this.motionY, this.motionZ);
        		this.motionX *= 0.800000011920929D;
        		this.motionY *= 0.800000011920929D;
        		this.motionZ *= 0.800000011920929D;
        	}
        	else if (this.handleLavaMovement())
        	{
        		this.moveFlying(par1, par2, 0.02F);
        		this.moveEntity(this.motionX, this.motionY, this.motionZ);
        		this.motionX *= 0.5D;
        		this.motionY *= 0.5D;
        		this.motionZ *= 0.5D;
        	}
        	else
        	{
        		float var3 = 0.91F;

        		if (this.onGround)
        		{
        			var3 = 0.54600006F;
        			int var4 = this.worldObj.getBlockId(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.boundingBox.minY) - 1, MathHelper.floor_double(this.posZ));

        			if (var4 > 0)
        			{
        				var3 = Block.blocksList[var4].slipperiness * 0.91F;
        			}
        		}

        		float var8 = 0.16277136F / (var3 * var3 * var3);
        		this.moveFlying(par1, par2, this.onGround ? 0.1F * var8 : 0.02F);
        		var3 = 0.91F;

        		if (this.onGround)
        		{
        			var3 = 0.54600006F;
        			int var5 = this.worldObj.getBlockId(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.boundingBox.minY) - 1, MathHelper.floor_double(this.posZ));

        			if (var5 > 0)
        			{
        				var3 = Block.blocksList[var5].slipperiness * 0.91F;
        			}
        		}

        		this.moveEntity(this.motionX, this.motionY, this.motionZ);
        		this.motionX *= (double)var3;
        		this.motionY *= (double)var3;
        		this.motionZ *= (double)var3;
        	}

        	this.prevLimbSwingAmount = this.limbSwingAmount;
        	double var10 = this.posX - this.prevPosX;
        	double var9 = this.posZ - this.prevPosZ;
        	float var7 = MathHelper.sqrt_double(var10 * var10 + var9 * var9) * 4.0F;

        	if (var7 > 1.0F)
        	{
        		var7 = 1.0F;
        	}

        	this.limbSwingAmount += (var7 - this.limbSwingAmount) * 0.4F;
        	this.limbSwing += this.limbSwingAmount;
        }
        else
        {
        	super.moveEntityWithHeading(par1, par2);
        }
    }

    @Override
    public boolean isOnLadder()
    {
        if (this.isFlying())
        {
        	return false;
        }
        else
        {
        	return super.isOnLadder();
        }
    }

    public void autoHealing()
    {
        this.healTime++;
        if (this.healTime >= this.getHealFrequency())
        {
        	this.healTime = 0;
        	if (this.getHealth() < this.getMaxHealth())
        	{
        		this.heal(1.0F);
        		if (this.getHealth() > 0.0F)
        		{
        			this.chatMessage("HP = " + this.getHealth() * 0.5);
        		}
        	}
        }
    }

    protected int getHealFrequency()
    {
        return 100;
    }

    public boolean canAttackExplosive()
    {
        return false;
    }

    public boolean canAttackFlying()
    {
        return false;
    }
}