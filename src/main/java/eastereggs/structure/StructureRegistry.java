package eastereggs.structure;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;

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
                    if (doneBlocks.contains(metaDataBlock))
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
                }
            }
        }
    }

    public Structure getStructureById(String id)
    {
        return this.structures.get(id);
    }

    public void placeBlock(MetaDataBlock block, EntityPlayer player)
    {
        List<Structure> possibleStructures = this.structuresByBlock.get(block);
        // @TODO: Check the structures
    }
}
