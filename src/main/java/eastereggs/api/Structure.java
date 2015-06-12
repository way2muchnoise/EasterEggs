package eastereggs.api;

import eastereggs.utils.MetaDataBlock;
import net.minecraft.entity.player.EntityPlayer;

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
}
