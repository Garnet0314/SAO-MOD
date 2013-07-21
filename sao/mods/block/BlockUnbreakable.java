package sao.mods.block;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.StepSound;
import net.minecraft.client.particle.EffectRenderer;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.Icon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.common.IPlantable;

/*
 * 汎用破壊不能ブロック。
 * コンストラクタで指定した元ブロックの動作をエミュレートしつつ破壊不能なブロックを定義する。
 * なお、Block.java内で「public static final Block stone」のようにBlock型で定義されたもののフェイクとして利用することを想定している。
 * Block.java内でBlock以外の型で定義されているものは特別な動作をすることが期待されているため、
 * それ専用の破壊不能ブロッククラスを定義すべきである。
 * また、instanceofでクラスの一致を判定される場合やキャストされる場合も専用クラスが必要。
 */
public class BlockUnbreakable extends Block
{
    public Block original;
    boolean isOpaqueCubeOriginal;

    public BlockUnbreakable(int par1, Block original)
    {
        super(par1, original.blockMaterial);
        this.original = original;
        this.isOpaqueCubeOriginal = original.isOpaqueCube();
    }

    /*
     * コンストラクタ(public Block(int par1, Material par2Material)のこと)で使用されるメソッドは、
     * 移譲先(this.original)にアクセスしてはならない。
     * super(...)が呼ばれる時点では代入されていないので。
     */
    @Override
    public boolean isOpaqueCube()
    {
        return this.isOpaqueCubeOriginal;
    }

    /*
     * 破壊不能ブロックなので強度関係のメソッドは移譲しない。
     */
    @Override
    public Block setHardness(float par1)
    {
        return super.setHardness(par1);
    }

    @Override
    public Block setBlockUnbreakable()
    {
        return super.setBlockUnbreakable();
    }

    @Override
    public float getBlockHardness(World par1World, int par2, int par3, int par4)
    {
        return super.getBlockHardness(par1World, par2, par3, par4);
    }

    @Override
    public Block setResistance(float par1)
    {
        return super.setResistance(par1);
    }

    @Override
    public float getPlayerRelativeBlockHardness(EntityPlayer par1EntityPlayer, World par2World, int par3, int par4, int par5)
    {
        return super.getPlayerRelativeBlockHardness(par1EntityPlayer, par2World, par3, par4, par5);
    }

    /*
     * 専用のクリエイティブタブを指定するため移譲しない。
     */
    @Override
    public Block setCreativeTab(CreativeTabs par1CreativeTabs)
    {
        return super.setCreativeTab(par1CreativeTabs);
    }

    @Override
    public CreativeTabs getCreativeTabToDisplayOn()
    {
        return super.getCreativeTabToDisplayOn();
    }

    /*
     * 循環参照となるため移譲しない。
     */
    @Override
    public int getLightValue(IBlockAccess world, int x, int y, int z)
    {
        return this.lightValue[original.blockID];
    }

    @Override
    public int getRenderType()
    {
        // TODO configで設定できるようにすること
        return 51;
    }

    /*
     * 以下移譲メソッド
     * ネームテーブルの変更に備えて全て@Overrideをつけること。
     * また、Forgeのバージョンアップ時には必ず移譲漏れがないか確認すること。
     */
    @Override
    public Block setStepSound(StepSound par1StepSound)
    {
        return original.setStepSound(par1StepSound);
    }

    @Override
    public void addCollisionBoxesToList(World par1World, int par2, int par3, int par4, AxisAlignedBB par5AxisAlignedBB, List par6List, Entity par7Entity)
    {
        this.original.addCollisionBoxesToList(par1World, par2, par3, par4, par5AxisAlignedBB, par6List, par7Entity);
    }

    @Override
    public boolean canCollideCheck(int par1, boolean par2)
    {
        return this.original.canCollideCheck(par1, par2);
    }

    @Override
    public void breakBlock(World par1World, int par2, int par3, int par4, int par5, int par6)
    {
        this.original.breakBlock(par1World, par2, par3, par4, par5, par6);
    }

    @Override
    public void dropBlockAsItemWithChance(World par1World, int par2, int par3, int par4, int par5, float par6, int par7)
    {
        this.original.dropBlockAsItemWithChance(par1World, par2, par3, par4, par5, par6, par7);
    }

    @Override
    public int damageDropped(int par1)
    {
        return this.original.damageDropped(par1);
    }

    @Override
    public MovingObjectPosition collisionRayTrace(World par1World, int par2, int par3, int par4, Vec3 par5Vec3, Vec3 par6Vec3)
    {
        return this.original.collisionRayTrace(par1World, par2, par3, par4, par5Vec3, par6Vec3);
    }

    @Override
    public boolean canPlaceBlockOnSide(World par1World, int par2, int par3, int par4, int par5, ItemStack par6ItemStack)
    {
        return this.original.canPlaceBlockOnSide(par1World, par2, par3, par4, par5, par6ItemStack);
    }

    @Override
    public boolean canPlaceBlockOnSide(World par1World, int par2, int par3, int par4, int par5)
    {
        return this.original.canPlaceBlockOnSide(par1World, par2, par3, par4, par5);
    }

    @Override
    public boolean canPlaceBlockAt(World par1World, int par2, int par3, int par4)
    {
        return this.original.canPlaceBlockAt(par1World, par2, par3, par4);
    }

    @Override
    public int colorMultiplier(IBlockAccess par1iBlockAccess, int par2, int par3, int par4)
    {
        return this.original.colorMultiplier(par1iBlockAccess, par2, par3, par4);
    }

    @Override
    public boolean canProvidePower()
    {
        return this.original.canProvidePower();
    }

    @Override
    public boolean canBlockStay(World par1World, int par2, int par3, int par4)
    {
        return this.original.canBlockStay(par1World, par2, par3, par4);
    }

    @Override
    public float getAmbientOcclusionLightValue(IBlockAccess par1iBlockAccess, int par2, int par3, int par4)
    {
        return this.original.getAmbientOcclusionLightValue(par1iBlockAccess, par2, par3, par4);
    }

    @Override
    public void fillWithRain(World par1World, int par2, int par3, int par4)
    {
        this.original.fillWithRain(par1World, par2, par3, par4);
    }

    @Override
    public boolean isFlowerPot()
    {
        return this.original.isFlowerPot();
    }

    @Override
    public boolean func_82506_l()
    {
        return this.original.func_82506_l();
    }

    @Override
    public boolean canDropFromExplosion(Explosion par1Explosion)
    {
        return this.original.canDropFromExplosion(par1Explosion);
    }

    @Override
    public String getItemIconName()
    {
        return this.original.getItemIconName();
    }

    @Override
    public boolean canHarvestBlock(EntityPlayer player, int meta)
    {
        return this.original.canHarvestBlock(player, meta);
    }

    @Override
    public void addCreativeItems(ArrayList itemList)
    {
        this.original.addCreativeItems(itemList);
    }

    @Override
    public TileEntity createTileEntity(World world, int metadata)
    {
        return this.original.createTileEntity(world, metadata);
    }

    @Override
    public boolean canSilkHarvest(World world, EntityPlayer player, int x, int y, int z, int metadata)
    {
        return this.original.canSilkHarvest(world, player, x, y, z, metadata);
    }

    @Override
    public boolean canCreatureSpawn(EnumCreatureType type, World world, int x, int y, int z)
    {
        return this.original.canCreatureSpawn(type, world, x, y, z);
    }

    @Override
    public void beginLeavesDecay(World world, int x, int y, int z)
    {
        this.original.beginLeavesDecay(world, x, y, z);
    }

    @Override
    public boolean canSustainLeaves(World world, int x, int y, int z)
    {
        return this.original.canSustainLeaves(world, x, y, z);
    }

    @Override
    public boolean canBeReplacedByLeaves(World world, int x, int y, int z)
    {
        return this.original.canBeReplacedByLeaves(world, x, y, z);
    }

    @Override
    public boolean canConnectRedstone(IBlockAccess world, int x, int y, int z, int side)
    {
        return this.original.canConnectRedstone(world, x, y, z, side);
    }

    @Override
    public boolean canPlaceTorchOnTop(World world, int x, int y, int z)
    {
        return this.original.canPlaceTorchOnTop(world, x, y, z);
    }

    @Override
    public boolean canRenderInPass(int pass)
    {
        return this.original.canRenderInPass(pass);
    }

    @Override
    public boolean addBlockHitEffects(World worldObj, MovingObjectPosition target, EffectRenderer effectRenderer)
    {
        return this.original.addBlockHitEffects(worldObj, target, effectRenderer);
    }

    @Override
    public boolean addBlockDestroyEffects(World world, int x, int y, int z, int meta, EffectRenderer effectRenderer)
    {
        return this.original.addBlockDestroyEffects(world, x, y, z, meta, effectRenderer);
    }

    @Override
    public boolean canSustainPlant(World world, int x, int y, int z, ForgeDirection direction, IPlantable plant)
    {
        return this.original.canSustainPlant(world, x, y, z, direction, plant);
    }

    @Override
    public boolean canDragonDestroy(World world, int x, int y, int z)
    {
        return this.original.canDragonDestroy(world, x, y, z);
    }

    @Override
    public boolean equals(Object arg0)
    {
        return this.original.equals(arg0);
    }

    @Override
    public Block setLightOpacity(int par1)
    {
        return this.original.setLightOpacity(par1);
    }

    @Override
    public Block setLightValue(float par1)
    {
        return this.original.setLightValue(par1);
    }

    @Override
    public boolean renderAsNormalBlock()
    {
        return this.original.renderAsNormalBlock();
    }

    @Override
    public boolean getBlocksMovement(IBlockAccess par1iBlockAccess, int par2, int par3, int par4)
    {
        return this.original.getBlocksMovement(par1iBlockAccess, par2, par3, par4);
    }

    @Override
    public Block setTickRandomly(boolean par1)
    {
        return this.original.setTickRandomly(par1);
    }

    @Override
    public boolean getTickRandomly()
    {
        return this.original.getTickRandomly();
    }

    @Override
    public boolean hasTileEntity()
    {
        return this.original.hasTileEntity();
    }

    @Override
    public float getBlockBrightness(IBlockAccess par1iBlockAccess, int par2, int par3, int par4)
    {
        return this.original.getBlockBrightness(par1iBlockAccess, par2, par3, par4);
    }

    @Override
    public int getMixedBrightnessForBlock(IBlockAccess par1iBlockAccess, int par2, int par3, int par4)
    {
        return this.original.getMixedBrightnessForBlock(par1iBlockAccess, par2, par3, par4);
    }

    @Override
    public boolean shouldSideBeRendered(IBlockAccess par1iBlockAccess, int par2, int par3, int par4, int par5)
    {
        return this.original.shouldSideBeRendered(par1iBlockAccess, par2, par3, par4, par5);
    }

    @Override
    public boolean isBlockSolid(IBlockAccess par1iBlockAccess, int par2, int par3, int par4, int par5)
    {
        return this.original.isBlockSolid(par1iBlockAccess, par2, par3, par4, par5);
    }

    @Override
    public Icon getBlockTexture(IBlockAccess par1iBlockAccess, int par2, int par3, int par4, int par5)
    {
        return this.original.getBlockTexture(par1iBlockAccess, par2, par3, par4, par5);
    }

    @Override
    public Icon getIcon(int par1, int par2)
    {
        return this.original.getIcon(par1, par2);
    }

    @Override
    public AxisAlignedBB getSelectedBoundingBoxFromPool(World par1World, int par2, int par3, int par4)
    {
        return this.original.getSelectedBoundingBoxFromPool(par1World, par2, par3, par4);
    }

    @Override
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World, int par2, int par3, int par4)
    {
        return this.original.getCollisionBoundingBoxFromPool(par1World, par2, par3, par4);
    }

    @Override
    public boolean isCollidable()
    {
        return this.original.isCollidable();
    }

    @Override
    public void updateTick(World par1World, int par2, int par3, int par4, Random par5Random)
    {
        this.original.updateTick(par1World, par2, par3, par4, par5Random);
    }

    @Override
    public void randomDisplayTick(World par1World, int par2, int par3, int par4, Random par5Random)
    {
        this.original.randomDisplayTick(par1World, par2, par3, par4, par5Random);
    }

    @Override
    public void onBlockDestroyedByPlayer(World par1World, int par2, int par3, int par4, int par5)
    {
        this.original.onBlockDestroyedByPlayer(par1World, par2, par3, par4, par5);
    }

    @Override
    public void onNeighborBlockChange(World par1World, int par2, int par3, int par4, int par5)
    {
        this.original.onNeighborBlockChange(par1World, par2, par3, par4, par5);
    }

    @Override
    public int tickRate(World par1World)
    {
        return this.original.tickRate(par1World);
    }

    @Override
    public void onBlockAdded(World par1World, int par2, int par3, int par4)
    {
        this.original.onBlockAdded(par1World, par2, par3, par4);
    }

    @Override
    public int quantityDropped(Random par1Random)
    {
        return this.original.quantityDropped(par1Random);
    }

    @Override
    public int idDropped(int par1, Random par2Random, int par3)
    {
        return this.original.idDropped(par1, par2Random, par3);
    }

    @Override
    public float getExplosionResistance(Entity par1Entity)
    {
        return this.original.getExplosionResistance(par1Entity);
    }

    @Override
    public void onBlockDestroyedByExplosion(World par1World, int par2, int par3, int par4, Explosion par5Explosion)
    {
        this.original.onBlockDestroyedByExplosion(par1World, par2, par3, par4, par5Explosion);
    }

    @Override
    public int getRenderBlockPass()
    {
        return this.original.getRenderBlockPass();
    }

    @Override
    public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9)
    {
        return this.original.onBlockActivated(par1World, par2, par3, par4, par5EntityPlayer, par6, par7, par8, par9);
    }

    @Override
    public void onEntityWalking(World par1World, int par2, int par3, int par4, Entity par5Entity)
    {
        this.original.onEntityWalking(par1World, par2, par3, par4, par5Entity);
    }

    @Override
    public int onBlockPlaced(World par1World, int par2, int par3, int par4, int par5, float par6, float par7, float par8, int par9)
    {
        return this.original.onBlockPlaced(par1World, par2, par3, par4, par5, par6, par7, par8, par9);
    }

    @Override
    public void onBlockClicked(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer)
    {
        this.original.onBlockClicked(par1World, par2, par3, par4, par5EntityPlayer);
    }

    @Override
    public void velocityToAddToEntity(World par1World, int par2, int par3, int par4, Entity par5Entity, Vec3 par6Vec3)
    {
        this.original.velocityToAddToEntity(par1World, par2, par3, par4, par5Entity, par6Vec3);
    }

    @Override
    public void setBlockBoundsBasedOnState(IBlockAccess par1iBlockAccess, int par2, int par3, int par4)
    {
        this.original.setBlockBoundsBasedOnState(par1iBlockAccess, par2, par3, par4);
    }

    @Override
    public int getBlockColor()
    {
        return this.original.getBlockColor();
    }

    @Override
    public int getRenderColor(int par1)
    {
        return original.getRenderColor(par1);
    }

    @Override
    public int isProvidingWeakPower(IBlockAccess par1iBlockAccess, int par2, int par3, int par4, int par5)
    {
        return this.original.isProvidingWeakPower(par1iBlockAccess, par2, par3, par4, par5);
    }

    @Override
    public void onEntityCollidedWithBlock(World par1World, int par2, int par3, int par4, Entity par5Entity)
    {
        this.original.onEntityCollidedWithBlock(par1World, par2, par3, par4, par5Entity);
    }

    @Override
    public int isProvidingStrongPower(IBlockAccess par1iBlockAccess, int par2, int par3, int par4, int par5)
    {
        return this.original.isProvidingStrongPower(par1iBlockAccess, par2, par3, par4, par5);
    }

    @Override
    public void setBlockBoundsForItemRender()
    {
        this.original.setBlockBoundsForItemRender();
    }

    @Override
    public void harvestBlock(World par1World, EntityPlayer par2EntityPlayer, int par3, int par4, int par5, int par6)
    {
        this.original.harvestBlock(par1World, par2EntityPlayer, par3, par4, par5, par6);
    }

    @Override
    public int quantityDroppedWithBonus(int par1, Random par2Random)
    {
        return original.quantityDroppedWithBonus(par1, par2Random);
    }

    /*TODO
    @Override
    public void onBlockPlacedBy(World par1World, int par2, int par3, int par4, EntityLiving par5EntityLiving, ItemStack par6ItemStack)
    {
        this.original.onBlockPlacedBy(par1World, par2, par3, par4, par5EntityLiving, par6ItemStack);
    }
    */

    @Override
    public void onPostBlockPlaced(World par1World, int par2, int par3, int par4, int par5)
    {
        this.original.onPostBlockPlaced(par1World, par2, par3, par4, par5);
    }

    @Override
    public Block setUnlocalizedName(String par1Str)
    {
        return this.original.setUnlocalizedName(par1Str);
    }

    @Override
    public String getLocalizedName()
    {
        return this.original.getLocalizedName();
    }

    @Override
    public String getUnlocalizedName()
    {
        return this.original.getUnlocalizedName();
    }

    /*TODO
    @Override
    public String getUnlocalizedName2()
    {
        return this.original.getUnlocalizedName2();
    }
    */

    @Override
    public boolean onBlockEventReceived(World par1World, int par2, int par3, int par4, int par5, int par6)
    {
        return this.original.onBlockEventReceived(par1World, par2, par3, par4, par5, par6);
    }

    @Override
    public boolean getEnableStats()
    {
        return this.original.getEnableStats();
    }

    @Override
    public int getMobilityFlag()
    {
        return this.original.getMobilityFlag();
    }

    @Override
    public void onFallenUpon(World par1World, int par2, int par3, int par4, Entity par5Entity, float par6)
    {
        this.original.onFallenUpon(par1World, par2, par3, par4, par5Entity, par6);
    }

    @Override
    public int idPicked(World par1World, int par2, int par3, int par4)
    {
        return this.original.idPicked(par1World, par2, par3, par4);
    }

    @Override
    public int getDamageValue(World par1World, int par2, int par3, int par4)
    {
        return this.original.getDamageValue(par1World, par2, par3, par4);
    }

    @Override
    public void getSubBlocks(int par1, CreativeTabs par2CreativeTabs, List par3List)
    {
        this.original.getSubBlocks(par1, par2CreativeTabs, par3List);
    }

    @Override
    public void onBlockHarvested(World par1World, int par2, int par3, int par4, int par5, EntityPlayer par6EntityPlayer)
    {
        this.original.onBlockHarvested(par1World, par2, par3, par4, par5, par6EntityPlayer);
    }

    @Override
    public void onSetBlockIDWithMetaData(World par1World, int par2, int par3, int par4, int par5)
    {
        this.original.onSetBlockIDWithMetaData(par1World, par2, par3, par4, par5);
    }

    @Override
    public boolean isAssociatedBlockID(int par1)
    {
        return this.original.isAssociatedBlockID(par1);
    }

    @Override
    public boolean hasComparatorInputOverride()
    {
        return this.original.hasComparatorInputOverride();
    }

    @Override
    public int getComparatorInputOverride(World par1World, int par2, int par3, int par4, int par5)
    {
        return this.original.getComparatorInputOverride(par1World, par2, par3, par4, par5);
    }

    @Override
    public void registerIcons(IconRegister par1IconRegister)
    {
        this.original.registerIcons(par1IconRegister);
    }

    /*TODO
    @Override
    public boolean isLadder(World world, int x, int y, int z)
    {
        return this.original.isLadder(world, x, y, z);
    }
    */

    @Override
    public boolean isBlockNormalCube(World world, int x, int y, int z)
    {
        return this.original.isBlockNormalCube(world, x, y, z);
    }

    @Override
    public boolean isBlockSolidOnSide(World world, int x, int y, int z, ForgeDirection side)
    {
        return this.original.isBlockSolidOnSide(world, x, y, z, side);
    }

    @Override
    public boolean isBlockReplaceable(World world, int x, int y, int z)
    {
        return this.original.isBlockReplaceable(world, x, y, z);
    }

    @Override
    public boolean isBlockBurning(World world, int x, int y, int z)
    {
        return this.original.isBlockBurning(world, x, y, z);
    }

    @Override
    public boolean isAirBlock(World world, int x, int y, int z)
    {
        return this.original.isAirBlock(world, x, y, z);
    }

    @Override
    public boolean removeBlockByPlayer(World world, EntityPlayer player, int x, int y, int z)
    {
        return this.original.removeBlockByPlayer(world, player, x, y, z);
    }

    @Override
    public int getFlammability(IBlockAccess world, int x, int y, int z, int metadata, ForgeDirection face)
    {
        return this.original.getFlammability(world, x, y, z, metadata, face);
    }

    @Override
    public boolean isFlammable(IBlockAccess world, int x, int y, int z, int metadata, ForgeDirection face)
    {
        return this.original.isFlammable(world, x, y, z, metadata, face);
    }

    @Override
    public int getFireSpreadSpeed(World world, int x, int y, int z, int metadata, ForgeDirection face)
    {
        return this.original.getFireSpreadSpeed(world, x, y, z, metadata, face);
    }

    @Override
    public boolean isFireSource(World world, int x, int y, int z, int metadata, ForgeDirection side)
    {
        return this.original.isFireSource(world, x, y, z, metadata, side);
    }

    @Override
    public boolean hasTileEntity(int metadata)
    {
        return this.original.hasTileEntity(metadata);
    }

    @Override
    public int quantityDropped(int meta, int fortune, Random random)
    {
        return this.original.quantityDropped(meta, fortune, random);
    }

    @Override
    public ArrayList<ItemStack> getBlockDropped(World world, int x, int y, int z, int metadata, int fortune)
    {
        return this.original.getBlockDropped(world, x, y, z, metadata, fortune);
    }

    /*TODO
    @Override
    public boolean isBed(World world, int x, int y, int z, EntityLiving player)
    {
        return this.original.isBed(world, x, y, z, player);
    }
    */

    @Override
    public ChunkCoordinates getBedSpawnPosition(World world, int x, int y, int z, EntityPlayer player)
    {
        return this.original.getBedSpawnPosition(world, x, y, z, player);
    }

    @Override
    public void setBedOccupied(World world, int x, int y, int z, EntityPlayer player, boolean occupied)
    {
        this.original.setBedOccupied(world, x, y, z, player, occupied);
    }

    @Override
    public int getBedDirection(IBlockAccess world, int x, int y, int z)
    {
        return this.original.getBedDirection(world, x, y, z);
    }

    @Override
    public boolean isBedFoot(IBlockAccess world, int x, int y, int z)
    {
        return this.original.isBedFoot(world, x, y, z);
    }

    @Override
    public boolean isLeaves(World world, int x, int y, int z)
    {
        return this.original.isLeaves(world, x, y, z);
    }

    @Override
    public boolean isWood(World world, int x, int y, int z)
    {
        return this.original.isWood(world, x, y, z);
    }

    @Override
    public boolean isGenMineableReplaceable(World world, int x, int y, int z, int target)
    {
        return this.original.isGenMineableReplaceable(world, x, y, z, target);
    }

    @Override
    public float getExplosionResistance(Entity par1Entity, World world, int x, int y, int z, double explosionX, double explosionY, double explosionZ)
    {
        return this.original.getExplosionResistance(par1Entity, world, x, y, z, explosionX, explosionY, explosionZ);
    }

    @Override
    public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z)
    {
        return this.original.getPickBlock(target, world, x, y, z);
    }

    @Override
    public boolean isBlockFoliage(World world, int x, int y, int z)
    {
        return this.original.isBlockFoliage(world, x, y, z);
    }

    @Override
    public void onPlantGrow(World world, int x, int y, int z, int sourceX, int sourceY, int sourceZ)
    {
        this.original.onPlantGrow(world, x, y, z, sourceX, sourceY, sourceZ);
    }

    @Override
    public boolean isFertile(World world, int x, int y, int z)
    {
        return this.original.isFertile(world, x, y, z);
    }

    @Override
    public int getLightOpacity(World world, int x, int y, int z)
    {
        return this.original.getLightOpacity(world, x, y, z);
    }

    @Override
    public boolean isBeaconBase(World worldObj, int x, int y, int z, int beaconX, int beaconY, int beaconZ)
    {
        return this.original.isBeaconBase(worldObj, x, y, z, beaconX, beaconY, beaconZ);
    }
}