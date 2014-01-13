package sao.mods.client.renderer;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.world.IBlockAccess;
import sao.mods.block.BlockUnbreakable;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;

public class RenderBlockUnbreakable implements ISimpleBlockRenderingHandler
{
    @Override
    public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer)
    {
        renderer.renderBlockAsItem(((BlockUnbreakable)block).original, metadata, 1.0F);
    }

    @Override
    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer)
    {
        return renderer.renderBlockByRenderType(((BlockUnbreakable)block).original, x, y, z);
    }

    @Override
    public boolean shouldRender3DInInventory()
    {
        return true;
    }

    @Override
    public int getRenderId()
    {
        // TODO configで設定できるようにすること
        return 51;
    }
}