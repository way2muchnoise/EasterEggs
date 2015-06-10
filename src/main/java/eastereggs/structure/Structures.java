package eastereggs.structure;

import eastereggs.api.Structure;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;

public class Structures
{
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
                                        {null, null, null},
                                        {null, diamondBlock, null},
                                        {null, null, null}
                                },
                                {
                                        {null, null, null},
                                        {diamondBlock, diamondBlock, diamondBlock},
                                        {null, null, null}
                                },
                                {
                                        {null, null, null},
                                        {null, diamondBlock, null}
                                },
                                {
                                        {null, null, null},
                                        {null, diamondBlock, null},
                                        {null, null, null}
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
