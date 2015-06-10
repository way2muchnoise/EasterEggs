package eastereggs.structure;

import eastereggs.api.Structure;
import eastereggs.utils.LogHelper;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class StructureRegistry
{
    private Map<String, Structure> structures;
    private Map<MetaDataBlock, List<Structure>> structuresByBlock;
    private static StructureRegistry instance;

    public static StructureRegistry instance()
    {
        if (instance == null)
            instance = new StructureRegistry();
        return instance;
    }

    public StructureRegistry()
    {
        this.structures = new HashMap<String, Structure>();
        this.structuresByBlock = new HashMap<MetaDataBlock, List<Structure>>();
    }

    public void addStructure(Structure structure)
    {
        this.structures.put(structure.id, structure);
        List<MetaDataBlock> doneBlocks = new LinkedList<MetaDataBlock>();
        for (MetaDataBlock[][] d2 : structure.getStructure())
        {
            for (MetaDataBlock[] d1 : d2)
            {
                for (MetaDataBlock metaDataBlock : d1)
                {
                    if (metaDataBlock == null || doneBlocks.contains(metaDataBlock))
                        break;
                    doneBlocks.add(metaDataBlock);
                    List<Structure> structures = this.structuresByBlock.get(metaDataBlock);
                    if (structures == null)
                    {
                        structures = new LinkedList<Structure>();
                        structures.add(structure);
                    }
                    else if (!structures.contains(structure))
                    {
                        structures.add(structure);
                    }
                    this.structuresByBlock.put(metaDataBlock, structures);
                }
            }
        }
    }

    public void placeBlock(MetaDataBlock block, WorldCoord coord, EntityPlayer player)
    {
        List<Structure> possibleStructures = this.structuresByBlock.get(block);
        if (possibleStructures == null || possibleStructures.isEmpty()) return;
        for (Structure structure : possibleStructures)
        {
            int x, y, z;
            y = -1;
            for (MetaDataBlock[][] d2 : structure.getStructure())
            {
                y++;
                z = -1;
                for (MetaDataBlock[] d1 : d2)
                {
                    z++;
                    x = -1;
                    for (MetaDataBlock metaDataBlock : d1)
                    {
                        x++;
                        if (metaDataBlock != null && metaDataBlock.equals(block))
                        {
                            if (checkStructure(x, y, z, structure, coord, player))
                            {
                                structure.reaction(player);
                                LogHelper.info(structure.id + " build by " + player.getDisplayName());
                                return;
                            }
                        }
                    }
                }
            }
        }
    }

    private boolean checkStructure(int x, int y, int z, Structure structure, WorldCoord worldCoord, EntityPlayer entityPlayer)
    {
        World world = entityPlayer.getEntityWorld();
        if(checkStructureX(x, y, z, structure, worldCoord, world))
        {
            removeStructureX(x, y, z, structure, worldCoord, world);
            return true;
        }
        if (checkStructureZ(z, y, x, structure, worldCoord, world))
        {
            removeStructureZ(z, y, x, structure, worldCoord, world);
            return true;
        }
        return false;
    }

    private boolean checkStructureX(int x, int y, int z, Structure structure, WorldCoord coord, World world)
    {
        MetaDataBlock[][][] blocks = structure.getStructure();
        for (int d3 = 0; d3 < blocks.length; d3++)
        {
            for (int d2 = 0; d2 < blocks[d3].length; d2++)
            {
                for (int d1 = 0; d1 < blocks[d3][d2].length; d1++)
                {
                    Block block = world.getBlock(coord.getX() - x + d1, coord.getY() + y - d3, coord.getZ() - z + d2);
                    int metadata = world.getBlockMetadata(coord.getX() - x + d1, coord.getY() + y - d3, coord.getZ() - z + d2);
                    if (blocks[d3][d2][d1] == null)
                    {
                        if(block == Blocks.air)
                            continue;
                        return false;
                    }
                    if(!blocks[d3][d2][d1].equals(new MetaDataBlock(block, metadata)))
                        return false;
                }
            }
        }
        return true;
    }

    private boolean checkStructureZ(int x, int y, int z, Structure structure, WorldCoord coord, World world)
    {
        MetaDataBlock[][][] blocks = structure.getStructure();
        for (int d3 = 0; d3 < blocks.length; d3++)
        {
            for (int d2 = 0; d2 < blocks[d3].length; d2++)
            {
                for (int d1 = 0; d1 < blocks[d3][d2].length; d1++)
                {
                    Block block = world.getBlock(coord.getX() - x + d2, coord.getY() + y - d3, coord.getZ() - z + d1);
                    int metadata = world.getBlockMetadata(coord.getX() - x + d2, coord.getY() + y - d3, coord.getZ() - z + d1);
                    if (blocks[d3][d2][d1] == null)
                    {
                        if(block == Blocks.air)
                            continue;
                        return false;
                    }
                    if(!blocks[d3][d2][d1].equals(new MetaDataBlock(block, metadata)))
                        return false;
                }
            }
        }
        return true;
    }

    private void removeStructureX(int x, int y, int z, Structure structure, WorldCoord coord, World world)
    {
        MetaDataBlock[][][] blocks = structure.getStructure();
        for (int d3 = 0; d3 < blocks.length; d3++)
            for (int d2 = 0; d2 < blocks[d3].length; d2++)
                for (int d1 = 0; d1 < blocks[d3][d2].length; d1++)
                    world.setBlock(coord.getX() - x + d1, coord.getY() + y - d3, coord.getZ() - z + d2, Blocks.air);
    }

    private void removeStructureZ(int x, int y, int z, Structure structure, WorldCoord coord, World world)
    {
        MetaDataBlock[][][] blocks = structure.getStructure();
        for (int d3 = 0; d3 < blocks.length; d3++)
            for (int d2 = 0; d2 < blocks[d3].length; d2++)
                for (int d1 = 0; d1 < blocks[d3][d2].length; d1++)
                    world.setBlock(coord.getX() - x + d2, coord.getY() + y - d3, coord.getZ() - z + d1, Blocks.air);
    }
}
