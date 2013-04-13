package SAO.mods.block;

import java.util.Random;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockBossSpawner extends BlockContainer {

	public BlockBossSpawner(int par1) {
		super(par1, Material.rock);
	}

	public TileEntity createNewTileEntity(World par1World) {
		return new TileEntityBossSpawner();
	}

	public int idDropped(int par1, Random par2Random, int par3) {
		return 0;
	}

	public int quantityDropped(Random par1Random) {
		return 0;
	}

	public boolean isOpaqueCube() {
		return true;
	}

	@SideOnly(Side.CLIENT)
	public int idPicked(World par1World, int par2, int par3, int par4) {
		return 0;
	}
}
