package SAO.mods.core;

import net.minecraft.block.Block;
import net.minecraft.block.BlockStone;
import SAO.mods.Block.BlockGrassUB;
import SAO.mods.Block.BlockStoneDm;
import cpw.mods.fml.common.registry.GameRegistry;

public class BuildSAOBlock
{
    public static Block stoneUB;
    public static BlockGrassUB grassUB;

    public static Block stoneDm;

    public static void build(ConfigBlock par1)
    {
        stoneUB = (new BlockStone(par1.blockUB + 1)).setBlockUnbreakable().setResistance(6000000.0F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("stone");
        GameRegistry.registerBlock(stoneUB, "StoneUB");
        grassUB = (BlockGrassUB)(new BlockGrassUB(par1.blockUB + 2)).setBlockUnbreakable().setResistance(6000000.0F).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("grass");
        GameRegistry.registerBlock(grassUB, "GrassUB");

        stoneDm = (new BlockStoneDm(par1.blockDm + 1)).setBlockUnbreakable().setResistance(6000000.0F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("stone");
        GameRegistry.registerBlock(stoneDm, "StoneDm");
    }
}