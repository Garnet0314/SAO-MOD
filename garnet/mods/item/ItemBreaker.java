package garnet.mods.item;

import java.util.LinkedList;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.world.World;

public abstract class ItemBreaker extends ItemTool
{
	protected int breakCount;
    private LinkedList breakableList;

    private boolean isCut;
    protected Block leavesIDs[];
    protected Block cutBlockIDs[];

    private boolean isBreak;
    protected Block mineBlockIDs[];

    protected boolean isDig;
    protected int xDig;
    protected int yDig;
    protected int zDig;
    protected Block digBlockIDs[];

    public ItemBreaker(int par1)
    {
        super(par1, 4.0F, EnumToolMaterial.EMERALD, new Block[]{});

        this.breakCount = 0;
        this.breakableList = new LinkedList();

        this.isCut = false;
        this.isBreak = false;
        this.isDig = false;
        this.xDig = 0;
        this.yDig = 0;
        this.zDig = 0;

        this.setMaxDamage(250);
    }

    @Override
    public float getStrVsBlock(ItemStack par1ItemStack, Block par2Block)
    {
        return this.efficiencyOnProperMaterial;
    }

    @Override
    public boolean hitEntity(ItemStack par1ItemStack, EntityLivingBase par2EntityLivingBase, EntityLivingBase par3EntityLivingBase)
    {
        par1ItemStack.damageItem(1, par3EntityLivingBase);
        return true;
    }

    @Override
    public boolean onBlockDestroyed(ItemStack par1ItemStack, World par2World, int par3, int par4, int par5, int par6, EntityLivingBase par7EntityLivingBase)
    {
        EntityPlayer var1 = (EntityPlayer)par7EntityLivingBase;
        if (this.canBreak(par2World, var1, par3))
        {
        	if (this.isDig)
        	{
        		this.xDig = par4;
        		this.yDig = par5;
        		this.zDig = par6;
        	}
        	this.breakAll(var1, par2World, par3, par4, par5, par6);
        }

        int var2 = 1 + this.breakCount;

        par1ItemStack.damageItem(var2, par7EntityLivingBase);
        return true;
    }

    @Override
    public boolean canHarvestBlock(Block par1Block)
    {
        return true;
    }

    protected boolean canBreak(World par1World, EntityPlayer par2EntityPlayer, int par3)
    {
        if (par3 == 0 || par3 == Block.bedrock.blockID)
        {
        	return false;
        }
        else
        {
        	this.isCut = this.isIDInList(par3, this.cutBlockIDs);
        	this.isBreak = this.isIDInList(par3, this.mineBlockIDs);
        	this.isDig = this.isIDInList(par3, this.digBlockIDs);
        	return (this.isCut || this.isBreak || this.isDig);
        }
    }

    protected void breakAll(EntityPlayer par1EntityPlayer, World par2World, int par3, int par4, int par5, int par6)
    {
        this.breakCount = 0;
        if (this.isCut)
        {
        	this.checkLeaves(par1EntityPlayer, par2World, par4, par5, par6);
        }
        this.checkConnection(par1EntityPlayer, par2World, par3, par4, par5, par6);
        while(this.breakBlock(par1EntityPlayer, par2World, par3));
        this.breakableList.clear();
    }

    protected void checkConnection(EntityPlayer par1EntityPlayer, World par2World, int par3, int par4, int par5, int par6)
    {
        int var0 = 1;
        int var9 = 1;
        int var2 = 1;
        int var11 = 1;
        int var4 = 1;
        int var13 = 1;
        if (this.isDig)
        {
        	if (this.xDig + 2 == par4)
        	{
        		var0 = 0;
        	}

        	if (this.xDig - 2 == par4)
        	{
        		var9 = 0;
        	}

        	if (this.yDig + 2 == par5)
        	{
        		var2 = 0;
        	}

        	var11 = 0;

        	if (this.zDig + 2 == par6)
        	{
        		var4 = 0;
        	}

        	if (this.zDig - 2 == par6)
        	{
        		var13 = 0;
        	}
        }

        for (int var1 = -var9; var1 <= var0; var1++)
        {
        	for (int var3 = -var11; var3 <= var2; var3++)
        	{
        		for (int var5 = -var13; var5 <= var4; var5++)
        		{
        			int var6 = par2World.getBlockId(par4 + var1, par5 + var3, par6 + var5);
        			if (this.checkBlock(Block.blocksList[var6], par3))
        			{
        				int var8[] = {par4 + var1, par5 + var3, par6 + var5};
        				this.breakableList.offer(var8);
        			}
        		}
        	}
        }
    }

    protected boolean checkBlock(Block par1Block, int par2)
    {
        if (par1Block == null)
        {
        	return false;
        }
        else if (par2 == Block.oreRedstone.blockID || par2 == Block.oreRedstoneGlowing.blockID)
        {
        	if (par1Block.blockID != Block.oreRedstone.blockID && par1Block.blockID != Block.oreRedstoneGlowing.blockID)
        	{
        		return false;
        	}
        	return true;
        }
        else if (par2 == Block.dirt.blockID || par2 == Block.grass.blockID || par2 == Block.mycelium.blockID)
        {
        	if (par1Block.blockID != Block.dirt.blockID && par1Block.blockID != Block.grass.blockID && par1Block.blockID != Block.mycelium.blockID)
        	{
        		return false;
        	}
        	return true;
        }
        return par1Block.blockID == par2;
    }

    protected boolean breakBlock(EntityPlayer par1EntityPlayer, World par2World, int par3)
    {
        if (this.breakableList.size() == 0)
        {
        	return false;
        }
        if (this.breakableList.peek() == null)
        {
        	return false;
        }
        int var1[] = (int[])this.breakableList.poll();
        if (var1 == null)
        {
        	return false;
        }

        Block var2Block = Block.blocksList[par2World.getBlockId(var1[0], var1[1], var1[2])];
        int var3 = par2World.getBlockMetadata(var1[0], var1[1], var1[2]);
        if (this.checkBlock(var2Block, par3))
        {
        	if (par2World.setBlock(var1[0], var1[1], var1[2], 0))
        	{
        		this.breakCount++;
        		var2Block.harvestBlock(par2World, par1EntityPlayer, var1[0], var1[1], var1[2], var3);
        		if (this.isCut)
        		{
        			this.checkLeaves(par1EntityPlayer, par2World, var1[0], var1[1], var1[2]);
        		}
        	}
        	this.checkConnection(par1EntityPlayer, par2World, par3, var1[0], var1[1], var1[2]);
        }
        return true;
    }

    protected void checkLeaves(EntityPlayer par1EntityPlayer, World par2World, int par3, int par4, int par5)
    {
        for (int var1 = -4; var1 <= 4; var1++)
        {
        	for (int var3 = -4; var3 <= 4; var3++)
        	{
        		for (int var5 = -4; var5 <= 4; var5++)
        		{
        			Block var6Block = Block.blocksList[par2World.getBlockId(par3 + var1, par4 + var3, par5 + var5)];
        			int var7 = par2World.getBlockMetadata(par3 + var1, par4 + var3, par5 + var5);
        			if (var6Block != null)
        			{
        				if (isIDInList(var6Block.blockID, leavesIDs))
        				{
        					if (par2World.setBlock(par3 + var1, par4 + var3, par5 + var5, 0))
        					{
        						var6Block.harvestBlock(par2World, par1EntityPlayer, par3 + var1, par4 + var3, par5 + var5, var7);
        					}
        				}
        			}
        		}
        	}
        }
    }

    protected boolean isIDInList(int par1, Block[] par2Block)
    {
        for (int var1 = 0; var1 < par2Block.length; var1++)
        {
        	int var2 = par2Block[var1].blockID;
        	if (par1 == var2)
        	{
        		return true;
        	}
        }
        return false;
    }
}