package eastereggs.structure;

import net.minecraft.block.Block;

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
}
