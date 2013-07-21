package sao.mods.core;

import net.minecraft.block.Block;
import net.minecraft.block.BlockBeacon;
import net.minecraft.block.BlockCauldron;
import net.minecraft.block.BlockComparator;
import net.minecraft.block.BlockDaylightDetector;
import net.minecraft.block.BlockDeadBush;
import net.minecraft.block.BlockEndPortal;
import net.minecraft.block.BlockFire;
import net.minecraft.block.BlockFlower;
import net.minecraft.block.BlockFluid;
import net.minecraft.block.BlockGrass;
import net.minecraft.block.BlockHalfSlab;
import net.minecraft.block.BlockHopper;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockMycelium;
import net.minecraft.block.BlockPistonBase;
import net.minecraft.block.BlockPistonExtension;
import net.minecraft.block.BlockPistonMoving;
import net.minecraft.block.BlockPortal;
import net.minecraft.block.BlockRailBase;
import net.minecraft.block.BlockTallGrass;
import net.minecraft.block.BlockTripWireSource;
import sao.mods.SAOMOD;
import sao.mods.block.BlockBossSpawner;
import sao.mods.block.BlockGrassUB;
import sao.mods.block.BlockStoneDm;
import sao.mods.block.BlockUnbreakable;
import sao.mods.block.TileEntityBossSpawner;
import sao.mods.item.ItemBlockBossSpawner;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class SAOBlock
{
    public static BlockGrassUB grassUB;

    public static Block stoneDm;

    public static Block bossSpawner;

    public static Block[] ubBlocksList = new Block[4096];

    public static void build(ConfigBlock par1)
    {
        grassUB = (BlockGrassUB)(new BlockGrassUB(par1.blockUB + Block.grass.blockID)).setBlockUnbreakable().setResistance(6000000.0F).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("grass");
        GameRegistry.registerBlock(grassUB, "GrassUB");

        stoneDm = (new BlockStoneDm(par1.blockDm + Block.stone.blockID)).setBlockUnbreakable().setResistance(6000000.0F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("stone");
        GameRegistry.registerBlock(stoneDm, "StoneDm");

        for (int i = 0; i <= Block.dropper.blockID; i++)
        {
        	Block block = Block.blocksList[i];
        	if (block == null)
        	{
        		continue;
        	}
        	//汎用破壊不能ブロックに向かなそうな場合はスキップ
        	else if (block instanceof BlockGrass ||
        			block instanceof BlockFluid ||
        			block instanceof BlockLeaves ||
        			block instanceof BlockPistonBase ||
        			block instanceof BlockTallGrass ||
        			block instanceof BlockDeadBush ||
        			block instanceof BlockPistonExtension ||
        			block instanceof BlockPistonMoving ||
        			block instanceof BlockFlower ||
        			block instanceof BlockMycelium ||
        			block instanceof BlockCauldron ||
        			block instanceof BlockHalfSlab ||
        			block instanceof BlockTripWireSource ||
        			block instanceof BlockBeacon ||
        			block instanceof BlockComparator ||
        			block instanceof BlockDaylightDetector ||
        			block instanceof BlockHopper ||
        			block instanceof BlockRailBase ||
        			block instanceof BlockPortal ||
        			block instanceof BlockFire ||
        			block instanceof BlockEndPortal)
        	{
        		continue;
        	}

        	ubBlocksList[block.blockID] = new BlockUnbreakable(par1.blockUB + i, block).setBlockUnbreakable().setResistance(6000000.0F).setCreativeTab(SAOMOD.saoBlocks);
        	GameRegistry.registerBlock(ubBlocksList[block.blockID], "UB" + String.valueOf(i) + "_" + ubBlocksList[block.blockID].getUnlocalizedName());
        	System.out.println(block.getClass().getSimpleName());
        }

        bossSpawner = new BlockBossSpawner(par1.blockBossSpawner).setHardness(5.0F).setBlockUnbreakable().setResistance(6000000.0F).setUnlocalizedName("sao:block.BossSpawner").setCreativeTab(SAOMOD.saoBlocks);
        GameRegistry.registerTileEntity(TileEntityBossSpawner.class, "TileEntityBossSpawner");
        GameRegistry.registerBlock(bossSpawner, ItemBlockBossSpawner.class, "BossSpawner", "SAO-MOD");
        LanguageRegistry.addName(bossSpawner, "BossSpaner");
    }
}