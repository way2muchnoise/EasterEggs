package eastereggs.event;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import eastereggs.utils.LogHelper;
import net.minecraftforge.event.world.BlockEvent;

public class BlockEvents
{
    @SubscribeEvent
    public void onBlockPlaced(BlockEvent.PlaceEvent event)
    {
        LogHelper.info(event.placedBlock.getLocalizedName());
        // @TODO: do check for placed block and see if it makes some structure
    }
}
