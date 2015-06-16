package eastereggs.utils;

import net.minecraft.block.Block;

/**
 * Storage object for {@link Block} and it's metadata int value
 */
public class MetaDataBlock
{
    public Block block;
    public int metaData;

    public MetaDataBlock(Block block, int metaData)
    {
        this.block = block;
        this.metaData = metaData;
    }

    public MetaDataBlock(Block block)
    {
        this(block, 0);
    }

    @Override
    public boolean equals(Object obj)
    {
        if (obj instanceof MetaDataBlock)
            return this.block == ((MetaDataBlock) obj).block && this.metaData == ((MetaDataBlock) obj).metaData;
        return super.equals(obj);
    }

    @Override
    public int hashCode()
    {
        return block.hashCode() ^ metaData;
    }

    @Override
    public String toString()
    {
        return block.getUnlocalizedName() + ":" + metaData;
    }
}