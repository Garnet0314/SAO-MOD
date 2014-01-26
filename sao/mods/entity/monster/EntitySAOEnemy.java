package sao.mods.entity.monster;

import garnet.mods.EntityGarnetEnemy;
import net.minecraft.block.Block;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnchantmentThorns;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import sao.mods.entity.ISAOMob;
import sao.mods.entity.projectile.EntitySwordSkill;
import sao.mods.item.ItemSAOWeapon;

public abstract class EntitySAOEnemy extends EntityGarnetEnemy implements ISAOMob
{
    public EntitySAOEnemy(World par1World)
    {
        super(par1World);
        this.setSize(0.6F, 1.8F);
    }

    @Override
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
    }

    @Override
    protected void entityInit()
    {
        super.entityInit();
        this.dataWatcher.addObject(16, Byte.valueOf((byte)0));
    }

    @Override
    protected boolean isAIEnabled()
    {
        return true;
    }

    @Override
    protected boolean canDespawn()
    {
        return true;
    }

    @Override
    public String getTextureLocal()
    {
        return "sao";
    }

    @Override
    public float getSize()
    {
        return 1.0F;
    }

    /* ========== 防御について ========== */
    @Override
    public boolean attackEntityFrom(DamageSource par1DamageSource, float par2)
    {
        Entity var1 = par1DamageSource.getEntity();//攻撃主
        float var2 = 1.0F;//有効武器ダメージ
        float var3 = 0.0F;//合計属性ダメージ
        float var4[] = new float[] {0.0F, 0.0F, 0.0F, 0.0F};//武器攻撃力
        float var5[] = new float[] {0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F};//属性攻撃力
        int var6[] = new int[] {this.getSlashResist(), this.getThrustResist(), this.getStrikeResist(), this.getPenetrateResist()};//防御力
        int var7[] = new int[] {this.getQuakeResist(), this.getWaterResist(), this.getFireResist(), this.getAeroResist(), this.getShineResist(), this.getDarkResist()};//属性防御
        float var8;//ダメージ計算用
        int var9;//for文用、特に意味なし

        //スキルによるダメージ
        if (par1DamageSource.getSourceOfDamage() instanceof EntitySwordSkill)
        {
        	EntitySwordSkill var10 = (EntitySwordSkill)par1DamageSource.getSourceOfDamage();
        	int var11 = var10.getType();
        	for (var9 = 0; var9 < var4.length; var9++)
        	{
        		if (var9 == var11)
        		{
        			var4[var9] = var10.getDamage();
        		}
        	}
        	var11 = var10.getElement() - 1;
        	for (var9 = 0; var9 < var5.length; var9++)
        	{
        		if (var9 == var11)
        		{
        			var5[var9] = var10.getElementDamage();
        		}
        	}
        }
        //攻撃主がプレイヤーでかつ武器がSAOの武器の時のダメージ
        else if (var1 instanceof EntityPlayer)
        {
        	EntityPlayer var10 = (EntityPlayer)var1;
        	ItemStack var11 = var10.inventory.getCurrentItem();
        	if (var6 != null && var11.getItem() instanceof ItemSAOWeapon)
        	{
        		ItemSAOWeapon var12 = (ItemSAOWeapon)var11.getItem();
        		var4 = new float[] {var12.getSlashElement(), var12.getThrustElement(), var12.getStrikeElement(), var12.getPenetrateElement()};
        		var5 = new float[] {var12.getQuakeElement(), var12.getWaterElement(), var12.getFireElement(), var12.getAeroElement(), var12.getShineElement(), var12.getDarkElement()};
        	}
        }
        //攻撃主がSAOのモブの時
        else if (var1 instanceof ISAOMob)
        {
        	ISAOMob var10 = (ISAOMob)var1;
        	var4 = new float[] {var10.getSlashAttack(), var10.getThrustAttack(), var10.getStrikeAttack(), var10.getPenetrateAttack()};
        	var5 = new float[] {var10.getQuakeAttack(), var10.getWaterAttack(), var10.getFireAttack(), var10.getAeroAttack(), var10.getShineAttack(), var10.getDarkAttack()};
        }
        //それ以外の生物時のダメージは1.0F
        else if (var1 instanceof EntityLivingBase) {}
        //非生物のときはそのままダメージ
        else
        {
        	var4 = new float[] {par2};
        	var6 = new int[] {0};
        }

        for (var9 = 0; var9 < var4.length; var9++)
        {
        	var8 = var4[var9] * (100 - var6[var9]) / 100;
        	if (var2 < var8)
        	{
        		var2 = MathHelper.floor_float(var8);
        	}
        }
        for (var9 = 0; var9 < var5.length; var9++)
        {
        	var8 = var5[var9] * (100 - var7[var9]) / 100;
        	var3 += MathHelper.floor_float(var8);
        }

        //ポーションによる補正
        if (var1 instanceof EntityLivingBase)
        {
        	EntityLivingBase var10 = (EntityLivingBase)var1;
        	if (var10.isPotionActive(Potion.damageBoost))
        	{
        		var2 += 3 << var10.getActivePotionEffect(Potion.damageBoost).getAmplifier();
        	}
        	if (var10.isPotionActive(Potion.weakness))
        	{
        		var2 -= 2 <<var10.getActivePotionEffect(Potion.weakness).getAmplifier();
        	}
        }

        //属性ダメージがマイナスの時は回復
        if (var3 < 0.0F)
        {
        	this.heal(-var3);
        	var3 = 0.0F;
        }

        return super.attackEntityFrom(par1DamageSource, var2 + var3);
    }

    @Override
    public int getSlashResist()
    {
        return 0;
    }

    @Override
    public int getThrustResist()
    {
        return 0;
    }

    @Override
    public int getStrikeResist()
    {
        return 0;
    }

    @Override
    public int getPenetrateResist()
    {
        return 0;
    }

    @Override
    public int getQuakeResist()
    {
        return 0;
    }

    @Override
    public int getWaterResist()
    {
        return 0;
    }

    @Override
    public int getFireResist()
    {
        return 0;
    }

    @Override
    public int getAeroResist()
    {
        return 0;
    }

    @Override
    public int getShineResist()
    {
        return 0;
    }

    @Override
    public int getDarkResist()
    {
        return 0;
    }

    /* ========== 攻撃について ========== */
    @Override
    public boolean attackEntityAsMob(Entity par1Entity)
    {
        boolean var1;
        //対象がSAOのモブの時は処理を被ダメ側で行う
        if (par1Entity instanceof ISAOMob)
        {
        	var1 = par1Entity.attackEntityFrom(DamageSource.causeMobDamage(this), 0.0F);
        }
        //対象がそれ以外の時
        else
        {
        	float var2 = 1.0F;
        	float var3 = 0.0F;
        	float[] var4 = new float[]{this.getSlashAttack(), this.getThrustAttack(), this.getStrikeAttack(), this.getPenetrateAttack()};
        	float[] var5 = new float[]{this.getQuakeAttack(), this.getWaterAttack(), this.getFireAttack(), this.getAeroAttack(), this.getShineAttack(), this.getDarkAttack()};
        	int var6;
        	//プレイヤーのときは防具で軽減処理を行う
        	if (par1Entity instanceof EntityPlayer)
        	{
        		//TODO 防具実装してから
        	}

        	for (var6 = 0; var6 < var4.length; var6++)
        	{
        		if (var2 < var4[var6])
        		{
        			var2 = MathHelper.floor_float(var4[var6]);
        		}
        	}
        	for (var6 = 0; var6 < var5.length; var6++)
        	{
        		var3 += MathHelper.floor_float(var5[var6]);
        	}

        	//属性ダメージがマイナスのときは回復
        	if (var3 < 0.0F)
        	{
        		if (par1Entity instanceof EntityLivingBase)
        		{
        			((EntityLivingBase) par1Entity).heal(-var3);
        		}
        		var3 = 0.0F;
        	}

        	//ポーション補正
        	if (this.isPotionActive(Potion.damageBoost))
        	{
        		var2 += 3 << this.getActivePotionEffect(Potion.damageBoost).getAmplifier();
        	}
        	if (this.isPotionActive(Potion.weakness))
        	{
        		var2 -= 2 <<this.getActivePotionEffect(Potion.weakness).getAmplifier();
        	}

        	//エンチャ補正
        	if (par1Entity instanceof EntityLivingBase)
        	{
        		var2 += EnchantmentHelper.getEnchantmentModifierLiving(this, (EntityLivingBase)par1Entity);
        	}

        	var1 = par1Entity.attackEntityFrom(DamageSource.causeMobDamage(this), var2 + var3);
        }

        //攻撃に成功した時のみ発生するエンチャ効果
        if (var1)
        {
        	int var8 = 0;
        	if (par1Entity instanceof EntityLivingBase)
        	{
        		var8 += EnchantmentHelper.getKnockbackModifier(this, (EntityLivingBase)par1Entity);
        		EnchantmentThorns.func_92096_a(this, (EntityLivingBase)par1Entity, this.rand);
        	}

        	if (var8 > 0)
        	{
        		par1Entity.addVelocity((double)(-MathHelper.sin(this.rotationYaw * (float)Math.PI / 180.0F) * (float)var8 * 0.5F), 0.1D, (double)(MathHelper.cos(this.rotationYaw * (float)Math.PI / 180.0F) * (float)var8 * 0.5F));
        		this.motionX *= 0.6D;
        		this.motionZ *= 0.6D;
        	}

        	int var7 = EnchantmentHelper.getFireAspectModifier(this);

        	if (var7 > 0)
        	{
        		par1Entity.setFire(var7 * 4);
        	}
        }
        return var1;
    }

    @Override
    public float getSlashAttack()
    {
        return 0.0F;
    }

    @Override
    public float getThrustAttack()
    {
        return 0.0F;
    }

    @Override
    public float getStrikeAttack()
    {
        return 0.0F;
    }

    @Override
    public float getPenetrateAttack()
    {
        return 0.0F;
    }

    @Override
    public float getQuakeAttack()
    {
        return 0.0F;
    }

    @Override
    public float getWaterAttack()
    {
        return 0.0F;
    }

    @Override
    public float getFireAttack()
    {
        return 0.0F;
    }

    @Override
    public float getAeroAttack()
    {
        return 0.0F;
    }

    @Override
    public float getShineAttack()
    {
        return 0.0F;
    }

    @Override
    public float getDarkAttack()
    {
        return 0.0F;
    }

    /* ========== ソードスキルについて ========== */
    @Override
    public boolean shouldSwordSkill()
    {
        return true;
    }

    @Override
    public boolean continueSwordSkill()
    {
        return true;
    }

    @Override
    public void readySwordSkill() {}

    @Override
    public void startSwordSkill()
    {
        this.setFlying(true);
    }

    @Override
    public void resetSwordSkill()
    {
        this.setFlying(false);
    }

    @Override
    public boolean doSwordSkill(int par1)
    {
        return false;
    }

    /* =========== 飛行について =========== */
    public boolean isFlying()
    {
        return this.dataWatcher.getWatchableObjectByte(16) == 1;
    }

    public void setFlying(boolean par1)
    {
        if (par1)
        {
        	this.dataWatcher.updateObject(16, Byte.valueOf((byte)1));
        }
        else
        {
        	this.dataWatcher.updateObject(16, Byte.valueOf((byte)0));
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
}