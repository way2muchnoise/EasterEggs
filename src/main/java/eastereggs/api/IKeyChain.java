package eastereggs.api;

import net.minecraft.entity.player.EntityPlayer;

public interface IKeyChain
{
    /**
     * Code executed on the ClientSide
     */
    void executeClientSide();

    /**
     * Code executed on the ServerSide
     * @param player the player who executed the code
     */
    void executeServerSide(EntityPlayer player);

    /**
     * Check if the {@link org.lwjgl.input.Keyboard} code is the same as the one at given index
     * @param index the index of the code
     * @param code the code to match against
     * @return true if the code indexes matches the given code
     */
    boolean isCodeAt(int index, int code);

    /**
     * Is the index the last piece of the code
     * @param index the to check index
     * @return true if the last code
     */
    boolean isLastCode(int index);

    /**
     * An id for the chain
     * @return a string for printing in logs
     */
    String id();
}
