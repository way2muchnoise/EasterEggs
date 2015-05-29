package eastereggs.structure;

import java.util.HashMap;
import java.util.Map;

public class StructureRegistry
{
    private Map<String, Structure> structures;
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
    }

    public void addStructure(Structure structure)
    {
        this.structures.put(structure.id, structure);
    }

    public Structure getStructureById(String id)
    {
        return this.structures.get(id);
    }
}
