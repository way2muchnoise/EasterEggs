package eastereggs.event;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import eastereggs.structure.MetaDataBlock;
import eastereggs.structure.StructureRegistry;
import eastereggs.structure.WorldCoord;
import eastereggs.utils.LogHelper;
import net.minecraftforge.event.world.BlockEvent;

public class BlockEvents
{
    @SubscribeEvent
    public void onBlockPlaced(BlockEvent.PlaceEvent event)
    {
        LogHelper.info(event.placedBlock.getLocalizedName());
        StructureRegistry.instance().placeBlock(new MetaDataBlock(event.placedBlock, event.blockMetadata), new WorldCoord(event.x, event.y, event.z), event.player);
    }
}
