package sao.mods.entity.projectile;

import java.util.List;

import sao.mods.entity.ISAOMob;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnchantmentThorns;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.boss.EntityDragonPart;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class EntitySwordSkill extends Entity
{
    private EntityLivingBase shootingEntity;//スキル発動主
    private float attackDamage;//ダメージ
    private float elementDamage;//属性ダメージ
    private double limitSpeed;//最高もしくは最低速度
    private double acceleration;//加速度
    private double vectorX;//X軸方向の移動ベクトル
    private double vectorY;//Y軸方向の移動ベクトル
    private double vectorZ;//Z軸方向の移動ベクトル
    private int lastTicksExisted;
    private double lastMotionX;
    private double lastMotionY;
    private double lastMotionZ;

    public EntitySwordSkill(World par1World)
    {
        super(par1World);
        this.setSize(0.1F, 0.1F);
    }

    public EntitySwordSkill(World world, EntityLivingBase entity, double xPos, double yPos, double zPos, float angleHorizon, float angleVertiacal, double firstSpeed, float size, int dead, int delay)
    {
        super(world);

        this.shootingEntity = entity;

        //ダメージの初期化
        this.attackDamage = 1.0F;
        this.elementDamage = 0.0F;

        //加速の初期化
        this.limitSpeed = firstSpeed;//弾の最大速度、または最低速度
        this.acceleration = 0.0D;//弾の加速度

        //ベクトルの設定
        float var1 = (entity.rotationYaw + angleHorizon) / 180.0F * (float)Math.PI;
        float var2 = (entity.rotationPitch + angleVertiacal) / 180.0F * (float)Math.PI;
        this.vectorX = -MathHelper.sin(var1) * MathHelper.cos(var2);
        this.vectorY = -MathHelper.sin(var2);
        this.vectorZ = MathHelper.cos(var1) * MathHelper.cos(var2);

        //移動量の設定
        this.motionX = this.vectorX * firstSpeed;//初期移動量はベクトル*初期速度
        this.motionY = this.vectorY * firstSpeed;
        this.motionZ = this.vectorZ * firstSpeed;

        //データ保持用
        this.lastMotionX = this.motionX;
        this.lastMotionY = this.motionY;
        this.lastMotionZ = this.motionZ;
        this.lastTicksExisted = this.ticksExisted;

        //角度を更新
        this.updateAngle();
        //位置を設定
        this.setPosition(xPos, yPos, zPos);
        //大きさを設定
        this.setSkillSize(size);
        this.setSize(size, size);
        //弾の消滅時間を設定
        this.setDeadTime(dead + delay);
        this.setCount(-delay);
    }

    public EntitySwordSkill(World world, EntityLivingBase entity, double xPos, double yPos, double zPos, float angleHorizon, float angleVertiacal, double firstSpeed, float size, int dead)
    {
        this(world, entity, xPos, yPos, zPos, angleHorizon, angleVertiacal, firstSpeed, size, dead, 0);
    }

    public EntitySwordSkill(World world, EntityLivingBase entity, float angleHorizon, float angleVertiacal, double firstSpeed, float size, int dead)
    {
        this(world, entity, entity.posX - (double)(MathHelper.cos(entity.rotationYaw / 180.0F * (float)Math.PI) * 0.16F), entity.posY + entity.getEyeHeight() - MathHelper.sin(entity.rotationPitch / 180.0F * (float)Math.PI) * 0.2F - 0.2F, entity.posZ - (double)(MathHelper.sin(entity.rotationYaw / 180.0F * (float)Math.PI) * 0.16F), angleHorizon, angleVertiacal, firstSpeed, size, dead, 0);
    }

    public EntitySwordSkill(World world, EntityLivingBase entity, float angleHorizon, float angleVertiacal, double firstSpeed, float size)
    {
        this(world, entity, angleHorizon, angleVertiacal, firstSpeed, size, 120);
    }

    @Override
    protected void entityInit()
    {
        this.dataWatcher.addObject(16, new Integer(0));//ｚ軸回転
        this.dataWatcher.addObject(17, new Integer(0));
        this.dataWatcher.addObject(18, new Integer(0));
        this.dataWatcher.addObject(19, new Integer(0));
        this.dataWatcher.addObject(20, new Integer(0));//属性、0:無属性、1:地属性、2:水属性、3:火属性、4:風属性、5:光属性、6:闇属性
        this.dataWatcher.addObject(21, new Integer(0));
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound nbtTagCompound) {}

    @Override
    public void readEntityFromNBT(NBTTagCompound nbtTagCompound) {}

    @Override
    public boolean canBeCollidedWith()
    {
        return true;
    }

    @Override
    public float getCollisionBorderSize()
    {
        return 1.0F;
    }

    @Override
    public float getBrightness(float par1)
    {
        return 1.0F;
    }

    //移動量から弾の速度を取得
    public double getSpeed()
    {
        return (double)MathHelper.sqrt_double(this.motionX * this.motionX + this.motionY * this.motionY + this.motionZ * this.motionZ);
    }

    //ベクトルを更新
    public void setVector()
    {
        //角度に合わせてベクトルを更新
        this.vectorX =  -Math.sin(this.rotationYaw / 180F * (float)Math.PI) * Math.cos(this.rotationPitch / 180F * (float)Math.PI);//X方向
        this.vectorY =  -Math.sin(this.rotationPitch / 180F * (float)Math.PI);//Y方向
        this.vectorZ =  Math.cos(this.rotationYaw / 180F * (float)Math.PI) * Math.cos(this.rotationPitch / 180F * (float)Math.PI);//Z方向

        this.motionX = this.vectorX * getSpeed();
        this.motionY = this.vectorY * getSpeed();
        this.motionZ = this.vectorZ * getSpeed();

        this.lastMotionX = this.motionX;
        this.lastMotionY = this.motionY;
        this.lastMotionZ = this.motionZ;
    }

    //角度の更新
    public void updateAngle()
    {
        float f = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionZ * this.motionZ);
        if(!this.worldObj.isRemote)
        {
        	this.rotationYaw = (float)((Math.atan2(this.motionX, this.motionZ) * 180D) / 3.1415927410125732D);
        }
        if(!this.worldObj.isRemote)
        {
        	for (this.rotationPitch = (float)((Math.atan2(this.motionY, f) * 180D) / 3.1415927410125732D); this.rotationPitch - this.prevRotationPitch < -180F; this.prevRotationPitch -= 360F) {}
        }
        for (; this.rotationPitch - this.prevRotationPitch >= 180F; this.prevRotationPitch += 360F) {}
        for (; this.rotationYaw - this.prevRotationYaw < -180F; this.prevRotationYaw -= 360F) {}
        for (; this.rotationYaw - this.prevRotationYaw >= 180F; this.prevRotationYaw += 360F) {}
        this.rotationPitch = this.prevRotationPitch + (this.rotationPitch - this.prevRotationPitch) * 1.0F;
    }

    @Override
    public void onUpdate()
    {
        //弾の主がいないか、死んでいる場合消える
        if (this.shootingEntity == null || !this.shootingEntity.isEntityAlive())
        {
        	if (!this.worldObj.isRemote)
        	{
        		this.setDead();
        	}
        	return;
        }
        else
        {
        	super.onUpdate();
        }

        //ダメージがない、消滅時間を過ぎる
        if(this.attackDamage <= 0.0F || this.getCount() > this.getDeadTime())
        {
        	if(!this.worldObj.isRemote)
        	{
        		this.setDead();
        	}
        	return;
        }

        this.extinguish();//火はつかないようにする

        //時間が進んでいないなら
        if(this.ticksExisted <= this.lastTicksExisted)
        {
        	return;//処理を終了させる
        }
        else//時間が通常通り進んでいるなら
        {
        	//何事もなかったように移動量を設定する
        	this.motionX = this.lastMotionX;
        	this.motionY = this.lastMotionY;
        	this.motionZ = this.lastMotionZ;
        	if(!this.worldObj.isRemote)
        	{
        		if(this.getCount() >= 0)
        		{
        			this.setCount(this.lastTicksExisted);
        		}
        		else
        		{
        			this.setCount(this.getCount() + 1);
        			return;
        		}
        	}
        }

        //加速減速の処理
        //加速で速度が限界値より遅いなら
        if(this.acceleration > 0.0D && this.getSpeed() < this.limitSpeed)
        {
        	this.motionX += this.vectorX * this.acceleration;
        	this.motionY += this.vectorY * this.acceleration;
        	this.motionZ += this.vectorZ * this.acceleration;

        	//上限を越えていたら上限にする
        	if(this.getSpeed() > this.limitSpeed )
        	{
        		this.motionX = this.vectorX * this.limitSpeed;
        		this.motionY = this.vectorY * this.limitSpeed;
        		this.motionZ = this.vectorZ * this.limitSpeed;
        	}
        }
        //減速で速度が限界値より速いなら
        else if(this.acceleration < 0.0D && this.getSpeed() > this.limitSpeed)
        {
        	if(Math.abs(this.acceleration) > this.getSpeed() - this.limitSpeed)
        	{
        		this.motionX = this.vectorX * this.limitSpeed;
        		this.motionY = this.vectorY * this.limitSpeed;
        		this.motionZ = this.vectorZ * this.limitSpeed;
        	}
        	else
        	{
        		this.motionX += this.vectorX * this.acceleration;
        		this.motionY += this.vectorY * this.acceleration;
        		this.motionZ += this.vectorZ * this.acceleration;
        	}
        }

        //速度0なら消滅
        if(this.getSpeed() == 0.0F)
        {
        	if(!this.worldObj.isRemote)
        	{
        		this.setDead();
        	}
        	return;
        }

        //衝突処理
        this.hitCheck();
        //最終的な移動量を現在地に加える
        if(!this.worldObj.isRemote && this.getCount() >= 0)
        {
        	this.posX += this.motionX;
        	this.posY += this.motionY;
        	this.posZ += this.motionZ;
        }

        this.updateAngle();//角度を更新する
        this.setPosition(this.posX, this.posY, this.posZ);//位置の更新

        //通常通り時間が進んでいるなら
        if(this.ticksExisted > this.lastTicksExisted)
        {
        	//最後に時間が動いていたときの時間と移動量を保存する
        	this.lastTicksExisted = this.ticksExisted;
        	this.lastMotionX = this.motionX;
        	this.lastMotionY = this.motionY;
        	this.lastMotionZ = this.motionZ;
        }
    }

    //衝突処理
    public void hitCheck()
    {
        //始点（現在地）
        Vec3 vec3d = this.worldObj.getWorldVec3Pool().getVecFromPool(this.posX, this.posY, this.posZ);
        //終点（現在地に移動量を足した点）
        Vec3 vec3d1 = this.worldObj.getWorldVec3Pool().getVecFromPool(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);
        //始点と終点からブロックとの当たりを取得
        MovingObjectPosition movingObjectPosition = this.worldObj.rayTraceBlocks_do_do(vec3d, vec3d1, false, true);
        vec3d = this.worldObj.getWorldVec3Pool().getVecFromPool(this.posX, this.posY, this.posZ);
        vec3d1 = this.worldObj.getWorldVec3Pool().getVecFromPool(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);
        //何らかのブロックに当たっているなら
        if (movingObjectPosition != null && this.getType() != 3)
        {
        	//終点を当たった点に変更
        	vec3d1 = this.worldObj.getWorldVec3Pool().getVecFromPool(movingObjectPosition.hitVec.xCoord, movingObjectPosition.hitVec.yCoord, movingObjectPosition.hitVec.zCoord);
        }

        movingObjectPosition = this.hitEntityCheck(movingObjectPosition, vec3d, vec3d1);

        if (movingObjectPosition != null)
        {
        	//当たった場合の処理をする
        	this.onImpact(movingObjectPosition);
        }
    }

    //Entityとの当たり判定
    public MovingObjectPosition hitEntityCheck(MovingObjectPosition par1MovingObjectPosition, Vec3 vec3d, Vec3 vec3d1)
    {
        Entity var1 = null;//当たったEntity
        double var2 = 0.0D;//そのEntityまでの距離
        float var3 = this.getSkillSize() * 0.5F;
        List var4 = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, this.boundingBox.addCoord(this.motionX, this.motionY, this.motionZ).expand(var3, var3, var3));

        for (int var5 = 0; var5 < var4.size(); var5++)
        {
        	Entity var6 = (Entity)var4.get(var5);

        	//var6が当たり判定を取り、var6が使用者でないなら
        	if (var6 != null && var6.canBeCollidedWith() && var6 != this.shootingEntity && (var6 instanceof EntityLivingBase || var6 instanceof EntityDragonPart || var6 instanceof EntitySwordSkill))
        	{
        		AxisAlignedBB var8 = var6.boundingBox.expand(var3, var3, var3);
        		MovingObjectPosition var9 = var8.calculateIntercept(vec3d, vec3d1);
        		double var10;
        		//var6がソードスキルなら使用者が違うときだけ
        		if(var6 instanceof EntitySwordSkill && ((EntitySwordSkill) var6).shootingEntity == this.shootingEntity)
        		{
        			continue;
        		}
        		else if (var9 != null)
        		{
        			//当たっているならここからその点までの距離を取得
        			var10 = vec3d.distanceTo(var9.hitVec);
        			//今までで一番近くにいるなら、標的に
        			if (var10 < var2 || var2 == 0.0D)
        			{
        				var1 = var6;
        				var2 = var10;
        			}
        		}
        	}
        }

        //当たったEntityがいるなら、当たったEntityをMovingObjectPositionで登録
        if (var1 != null)
        {
            par1MovingObjectPosition = new MovingObjectPosition(var1);
        }

        return par1MovingObjectPosition;
    }

    //ブロックやEntityに当たった時の処理
    private void onImpact(MovingObjectPosition par1MovingObjectPosition)
    {
        if (!this.worldObj.isRemote)
        {
        	Entity var1 = par1MovingObjectPosition.entityHit;

        	//当たったEntityがいるなら
        	if (var1 != null )
        	{
        		//それがEntitySlashなら
        		if(var1 instanceof EntitySwordSkill)
        		{
        			EntitySwordSkill var2 = (EntitySwordSkill)var1;

        			//使用者が違うなら
        			if(this.shootingEntity != var2.shootingEntity)
        			{
        				//相殺する
        				float var3 = this.attackDamage;
        				this.attackDamage -= var2.attackDamage;
        				var2.attackDamage -= var3;
        				if(this.attackDamage < 0.0F)
        				{
        					this.attackDamage = 0.0F;
        				}
        				if(var2.attackDamage < 0.0F)
        				{
        					var2.attackDamage = 0.0F;
        				}
        			}
        		}
        		else
        		{
        			if(var1 != this.shootingEntity)
        			{
        				//ノックバックしないように
        				double var2 = var1.motionX;
        				double var3 = var1.motionY;
        				double var4 = var1.motionZ;

        				var1.hurtResistantTime = 0;
        				if (this.attackEntityAsSkill(var1))
        				{
        					if (var1 instanceof EntityLivingBase)
        					{
        						if (this.getType() != 3)
        						{
        							this.attackDamage -= ((EntityLivingBase) var1).getHealth();
        							if (this.attackDamage <= 0.0F)
        							{
        								this.setDead();
        							}
        						}
        					}
        					else if (this.getType() != 3)
        					{
        						this.setDead();
        					}
        				}

        				var1.motionX = var2;
        				var1.motionY = var3;
        				var1.motionZ = var4;
        			}
        		}
        	}
        	//ブロックなら
        	else if (this.getType() != 3)
        	{
        		this.setDead();
        	}
        }
    }

    /* ========== 防御について ========== */
    @Override
    public boolean attackEntityFrom(DamageSource par1DamageSource, float par2)
    {
        this.setBeenAttacked();
        if (par1DamageSource.getEntity() != null)
        {
        	Vec3 var1 = par1DamageSource.getEntity().getLookVec();
        	this.rotationYaw = par1DamageSource.getEntity().rotationYaw;
        	this.rotationPitch = par1DamageSource.getEntity().rotationPitch;
        	this.setRotation(this.rotationYaw, this.rotationPitch);
        	this.setVector();
        	this.motionX += var1.xCoord * 2.0D;
        	this.motionY += var1.yCoord * 2.0D;
        	this.motionZ -= var1.zCoord * 2.0D;
        	this.worldObj.playSoundAtEntity(this, "random.bow", 2.0F, 0.3F);//音を出す
        	if (par1DamageSource.getEntity() instanceof EntityLivingBase)
        	{
        		this.shootingEntity = (EntityLivingBase)par1DamageSource.getEntity();
        	}
        	return true;
        }
        else
        {
        	return false;
        }
    }

    /* ========== 攻撃について ========== */
    public boolean attackEntityAsSkill(Entity par1Entity)
    {
        boolean var1;
        //対象がSAOのモブの時は処理を被ダメ側で行う
        if (par1Entity instanceof ISAOMob)
        {
        	var1 = par1Entity.attackEntityFrom(DamageSource.causeIndirectMagicDamage(this, this.shootingEntity), 0.0F);
        }
        //対象がそれ以外の時
        else
        {
        	float var2 = 1.0F;
        	float var3 = 0.0F;
        	float[] var4 = new float[]{0.0F, 0.0F, 0.0F, 0.0F};
        	float[] var5 = new float[]{0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F};
        	int var6;
        	int var7 = this.getType();
        	for (var6 = 0; var6 < var4.length; var6++)
        	{
        		if (var6 == var7)
        		{
        			var4[var6] = this.attackDamage;
        		}
        		else
        		{
        			var4[var6] = 0.0F;
        		}
        	}
        	var7 = this.getElement() - 1;
        	for (var6 = 0; var6 < var5.length; var6++)
        	{
        		if (var6 == var7)
        		{
        			var5[var6] = this.elementDamage;
        		}
        		else
        		{
        			var5[var6] = 0.0F;
        		}
        	}
        	//プレイヤーのときは防具で軽減処理を行う
        	if (par1Entity instanceof EntityPlayer)
        	{
        		//TODO
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
        	if (this.shootingEntity.isPotionActive(Potion.damageBoost))
        	{
        		var2 += 3 << this.shootingEntity.getActivePotionEffect(Potion.damageBoost).getAmplifier();
        	}
        	if (this.shootingEntity.isPotionActive(Potion.weakness))
        	{
        		var2 -= 2 << this.shootingEntity.getActivePotionEffect(Potion.weakness).getAmplifier();
        	}

        	var1 = par1Entity.attackEntityFrom(DamageSource.causeIndirectMagicDamage(this, this.shootingEntity), var2 + var3);
        }
        return var1;
    }

    /* ========== 設定・取得関連 ========== */
    //Z軸の角度を設定する
    public void setAngleZ(float par1)
    {
        float var1 = par1;
        if (var1 > 180.0F)
        {
        	var1 = -180.0F - var1 % 180.0F;
        }
        else if (var1 < -180.0F)
        {
        	var1 = 180.0F - var1 % 180.0F;
        }
        this.dataWatcher.updateObject(16, Integer.valueOf((int)(var1 * 10000.0F)));
    }

    //Z軸の角度を取得
    public float getAngleZ()
    {
        return (float)this.dataWatcher.getWatchableObjectInt(16) / 10000.0F;
    }

    //ショットのアニメーションのカウントを設定
    public void setCount(int par1)
    {
        this.dataWatcher.updateObject(17, Integer.valueOf(par1));
    }

    //ショットのアニメーションのカウントを取得
    public int getCount()
    {
        return this.dataWatcher.getWatchableObjectInt(17);
    }

    //消滅時間を設定
    public void setDeadTime(int par1)
    {
        this.dataWatcher.updateObject(18, Integer.valueOf(par1));
    }

    //消滅時間を取得
    public int getDeadTime()
    {
        return this.dataWatcher.getWatchableObjectInt(18);
    }

    //スキルの種類を設定、0:斬撃、1:刺突、2:打撃、3:貫通
    public void setType(int par1)
    {
        this.dataWatcher.updateObject(19, Integer.valueOf(par1));
    }

    //スキルの種類を取得
    public int getType()
    {
        return this.dataWatcher.getWatchableObjectInt(19);
    }

    //スキルの属性を設定、0:無属性、1:地属性、2:水属性、3:火属性、4:風属性、5:光属性、6:闇属性
    public void setElement(int par1)
    {
        this.dataWatcher.updateObject(20, Integer.valueOf(par1));
    }

    //スキルの属性を取得
    public int getElement()
    {
        return this.dataWatcher.getWatchableObjectInt(20);
    }

    //スキルの大きさを設定
    public void setSkillSize(float par1)
    {
        this.dataWatcher.updateObject(21, Integer.valueOf((int)(par1 * 100.0F)));
    }

    //スキルの大きさを取得
    public float getSkillSize()
    {
        return (float)this.dataWatcher.getWatchableObjectInt(21) / 100.0F;
    }

    //加速度を設定
    public void setAcceleration(double par1)
    {
        this.acceleration = par1;
    }

    //最高もしくは最低速度を設定
    public void setLimitSpeed(double par1)
    {
        this.limitSpeed = par1;
    }

    //ダメージ量を設定
    public void setDamage(float par1)
    {
        this.attackDamage = par1;
    }

    //ダメージ量を取得
    public float getDamage()
    {
        return this.attackDamage;
    }

    //属性ダメージ取得
    public float getElementDamage()
    {
        return this.elementDamage;
    }

    //属性ダメージ量を設定
    public void setElemetDamage(float par1)
    {
        this.elementDamage = par1;
    }

    /* ========== 描画系 ========== */
    @SideOnly(Side.CLIENT)
    @Override
    public float getShadowSize()
    {
        return 0.0F;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean isInRangeToRenderDist(double par1)
    {
        double var1 = this.boundingBox.getAverageEdgeLength() * 4.0D;
        var1 *= 128.0D;
        return par1 < var1 * var1;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public int getBrightnessForRender(float par1)
    {
        return 0xf000f0;
    }
}