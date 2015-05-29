package eastereggs.structure;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;

public abstract class Structure
{
    public String id;
    public abstract MetaDataBlock[][][] getStructure();

    /**
     * Triggered when structure is complete
     * @param player the player who placed the triggering block
     */
    public abstract void reaction(EntityPlayer player);

    public Structure(String id)
    {
        this.id = id;
    }

    private static void add(Structure structure)
    {
        StructureRegistry.instance().addStructure(structure);
    }

    public static void init()
    {
        add(new Structure("thing")
        {
            @Override
            public MetaDataBlock[][][] getStructure()
            {
                MetaDataBlock diamondBlock = new MetaDataBlock(Blocks.diamond_block);
                return new MetaDataBlock[][][]
                {
                    {
                        {null, diamondBlock, null}
                    },
                    {
                        {diamondBlock, diamondBlock, diamondBlock}
                    },
                    {
                        {null, diamondBlock, null}
                    },
                    {
                        {null, diamondBlock, null}
                    }
                };
            }

            @Override
            public void reaction(EntityPlayer player)
            {
                player.addExperienceLevel(1);
            }
        });
    }
}
