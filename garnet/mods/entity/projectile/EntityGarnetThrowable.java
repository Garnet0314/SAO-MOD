package garnet.mods.entity.projectile;

import garnet.mods.entity.passive.EntityGarnetBase;
import garnet.mods.entity.passive.EntityGarnetTameable;
import net.minecraft.block.Block;
import net.minecraft.block.BlockTNT;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.stats.StatList;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class EntityGarnetThrowable extends EntityThrowable
{
    private int xTile = -1;
    private int yTile = -1;
    private int zTile = -1;
    private int inTile = -1;
    private double damage = 2.0D;
    private int knockBackStrength = 0;

    public EntityGarnetThrowable(World par1World)
    {
        super(par1World);
    }

    public EntityGarnetThrowable(World par1World, double par2, double par4, double par6)
    {
        super(par1World, par2, par4, par6);
    }

    public EntityGarnetThrowable(World par1World, EntityLivingBase par2EntityLivingBase)
    {
        super(par1World, par2EntityLivingBase);
    }

    public EntityGarnetThrowable(World par1World, EntityLivingBase par2EntityLivingBase, EntityLivingBase par3EntityLivingBase, float par4, float par5)
    {
        super(par1World, par2EntityLivingBase);
        double var1 = 1.0D;
        if (par3EntityLivingBase.height >= 10.0F)
        {
        	var1 = 0.1D;
        }
        double var6 = par3EntityLivingBase.posX - par2EntityLivingBase.posX;
        double var8 = par3EntityLivingBase.posY + (double)par3EntityLivingBase.getEyeHeight() * var1 - 1.100000023841858D - this.posY;
        double var10 = par3EntityLivingBase.posZ - par2EntityLivingBase.posZ;
        float var12 = MathHelper.sqrt_double(var6 * var6 + var10 * var10) * 0.2F;
        this.setThrowableHeading(var6, var8 + (double)var12, var10, par4, par5);
    }

    public EntityGarnetThrowable(World par1World, EntityLivingBase par2EntityLivingBase, float par3)
    {
        super(par1World, par2EntityLivingBase);
        this.setThrowableHeading(this.motionX, this.motionY, this.motionZ, par3, 1.0F);
    }

    @Override
    protected void entityInit()
    {
        this.dataWatcher.addObject(16, Byte.valueOf((byte)0));
    }

    @Override
    protected void onImpact(MovingObjectPosition par1MovingObjectPosition)
    {
        if(par1MovingObjectPosition.entityHit != null)
        {
        	float var1 = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionY * this.motionY + this.motionZ * this.motionZ);
        	int var2 = (int)Math.ceil((double)var1 * this.getDamage(par1MovingObjectPosition.entityHit) / 2.0D);
        	if (this.isBurning())
        	{
        		par1MovingObjectPosition.entityHit.setFire(5);
        	}
        	if (this.getIsCritical())
        	{
        		double var3 = this.posX;
        		double var4 = this.posY;
        		double var5 = this.posZ;
        		this.worldObj.createExplosion(this, var3, var4, var5, 6.0F, true);
        	}
        	par1MovingObjectPosition.entityHit.hurtResistantTime = 0;
        	if (this.getThrower() instanceof EntityPlayer)
        	{
        		((EntityPlayer)this.getThrower()).addStat(StatList.damageDealtStat, var2);
        	}
        	if(par1MovingObjectPosition.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, this.getThrower()), var2))
        	{
        		if (par1MovingObjectPosition.entityHit instanceof EntityLivingBase)
        		{
        			if (this.knockBackStrength > 0)
        			{
        				float var3 = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionZ * this.motionZ);
        				if (var3 > 0.0F)
        				{
        					par1MovingObjectPosition.entityHit.addVelocity((this.motionX * (double)this.knockBackStrength * 0.60000002384185791D) / (double)var3, 0.10000000000000001D, (this.motionZ * (double)this.knockBackStrength * 0.60000002384185791D) / (double)var3);
        				}
        			}
        		}
        	}
        	else if (!(par1MovingObjectPosition.entityHit instanceof EntityGarnetBase))
        	{
        		if (par1MovingObjectPosition.entityHit.attackEntityFrom(DamageSource.causeMobDamage(this.getThrower()), var2))
        		{
        			if (par1MovingObjectPosition.entityHit instanceof EntityLivingBase)
        			{
        				if (this.knockBackStrength > 0)
        				{
        					float var3 = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionZ * this.motionZ);
        					if (var3 > 0.0F)
        					{
        						par1MovingObjectPosition.entityHit.addVelocity((this.motionX * (double)this.knockBackStrength * 0.60000002384185791D) / (double)var3, 0.10000000000000001D, (this.motionZ * (double)this.knockBackStrength * 0.60000002384185791D) / (double)var3);
        					}
        				}
        			}
        		}
        		else if (this.getThrower() instanceof EntityGarnetTameable && ((EntityGarnetTameable)this.getThrower()).isEnable())
        		{
        			if (par1MovingObjectPosition.entityHit.attackEntityFrom(DamageSource.causePlayerDamage(((EntityGarnetTameable)this.getThrower()).getOwner()), var2))
        			{
        				if (par1MovingObjectPosition.entityHit instanceof EntityLivingBase)
        				{
        					if (this.knockBackStrength > 0)
        					{
        						float var3 = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionZ * this.motionZ);
        						if (var3 > 0.0F)
        						{
        							par1MovingObjectPosition.entityHit.addVelocity((this.motionX * (double)this.knockBackStrength * 0.60000002384185791D) / (double)var3, 0.10000000000000001D, (this.motionZ * (double)this.knockBackStrength * 0.60000002384185791D) / (double)var3);
        						}
        					}
        				}
        			}
        		}
        	}
        	Vec3 vec3d = Vec3.createVectorHelper(this.posX, this.posY, this.posZ);
        	Vec3 vec3d2 = Vec3.createVectorHelper(this.motionX, this.motionY, this.motionZ);
        	double var4 = vec3d.distanceTo( par1MovingObjectPosition.hitVec) / vec3d2.lengthVector();
        	this.posX += this.motionX * var4;
        	this.posY += this.motionY * var4;
        	this.posZ += this.motionZ * var4;
        }
        else
        {
        	this.xTile = par1MovingObjectPosition.blockX;
        	this.yTile = par1MovingObjectPosition.blockY;
        	this.zTile = par1MovingObjectPosition.blockZ;
        	this.inTile = worldObj.getBlockId(this.xTile, this.yTile, this.zTile);
        	this.motionX = (float)(par1MovingObjectPosition.hitVec.xCoord - this.posX);
        	this.motionY = (float)(par1MovingObjectPosition.hitVec.yCoord - this.posY);
        	this.motionZ = (float)(par1MovingObjectPosition.hitVec.zCoord - this.posZ);
        	float var5 = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionY * this.motionY + this.motionZ * this.motionZ);
        	this.posX -= (this.motionX / (double)var5) * 0.05000000074505806D;
        	this.posY -= (this.motionY / (double)var5) * 0.05000000074505806D;
        	this.posZ -= (this.motionZ / (double)var5) * 0.05000000074505806D;
        	this.posX += this.motionX;
        	this.posY += this.motionY;
        	this.posZ += this.motionZ;
        	this.inGround = true;
        	this.throwableShake = 7;
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
        	if (this.getIsCritical())
        	{
        		int var2 = par1MovingObjectPosition.blockX;
        		int var3 = par1MovingObjectPosition.blockY;
        		int var4 = par1MovingObjectPosition.blockZ;
        		this.worldObj.createExplosion(this, var2, var3, var4, 4.0F, true);
        	}

        	if (Block.blocksList[inTile] instanceof BlockTNT)
        	{
        		this.worldObj.setBlock(this.xTile, this.yTile, this.zTile, 0);
        	}
        }
        for(int var6 = 0; var6 < 8; var6++)
        {
        	this.worldObj.spawnParticle("snowballpoof", this.posX, this.posY, this.posZ, 0.0D, 0.0D, 0.0D);
        }
        this.posX -= this.motionX;
        this.posY -= this.motionY;
        this.posZ -= this.motionZ;
        this.worldObj.playSoundAtEntity(this, "random.bowhit", 1.0F, 1.2F / (this.rand.nextFloat() * 0.2F + 0.9F));
        if (!this.worldObj.isRemote)
        {
        	this.setDead();
        }
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

    public void setKnockback(int par1)
    {
        this.knockBackStrength = par1;
    }

    public int getKnockBack()
    {
        return this.knockBackStrength;
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
}