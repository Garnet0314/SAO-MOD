package sao.mods.item;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;

public class ItemSAOEffectiveWeapon extends ItemSAOWeapon
{
    private int potionID;
    private int potionLevel;

    public ItemSAOEffectiveWeapon(int par1, EnumSAOWeapon par2EnumSAOWeapon, int par3, int par4)
    {
        super(par1, par2EnumSAOWeapon);
        this.potionID = par3;
        this.potionLevel = par4;
    }

    @Override
    public float getDamageVsEntity(Entity par1Entity, ItemStack par2ItemStack)
    {
        if (par1Entity instanceof EntityLiving)
        {
        	EntityLiving var1 = (EntityLiving)par1Entity;
        	if (!var1.isPotionActive(this.potionID))
        	{
        		var1.addPotionEffect(new PotionEffect(this.potionID, 600, this.potionLevel));
        	}
        }
        return super.getDamageVsEntity(par1Entity, par2ItemStack);
    }
}