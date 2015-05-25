package eastereggs.key;

import eastereggs.network.MessageHandler;
import eastereggs.network.message.KeyChainMessage;
import eastereggs.utils.LogHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import org.lwjgl.input.Keyboard;

public abstract class KeyChain
{
    private int[] codeChain;
    private String id;

    public String id()
    {
        return this.id;
    }

    private KeyChain(String id, String textCode)
    {
        this.id = id;
        this.codeChain = new int[textCode.length()];
        for (int i = 0; i < textCode.length(); i++)
            this.codeChain[i] = Keyboard.getKeyIndex(("" + textCode.charAt(i)).toUpperCase());
    }

    private KeyChain(String id, int... keyCodes)
    {
        this.id = id;
        this.codeChain = keyCodes;
    }

    public boolean isCodeAt(int index, int code)
    {
        if (index >= codeChain.length) return false;
        if (codeChain[index] != code) return false;
        if (index+1 == codeChain.length)
        {
            this.execute();
            LogHelper.debug("Executed cheat with id: " + this.id);
            return false;
        }
        return true;
    }

    /**
     * Executes ClientSide code and sends message to server for ServerSide execution
     */
    private void execute()
    {
        MessageHandler.INSTANCE.sendToServer(new KeyChainMessage(this.id()));
        executeClientSide();
    }

    /**
     * Code executed on ClientSide
     */
    protected void executeClientSide()
    {

    }

    /**
     * Code executed on ServerSide
     * @param player the player who preformed the key chain
     */
    public void executeServerSide(EntityPlayer player)
    {

    }

    private static void add(KeyChain chain)
    {
        KeyChainRegistry.instance().addKeyChain(chain);
    }

    public static void init()
    {
        add(new KeyChain("Konami Code", 200, 200, 208, 208, 203, 205, 203, 205, Keyboard.KEY_B, Keyboard.KEY_A)
        {
            @Override
            protected void executeClientSide()
            {
                Minecraft.getMinecraft().thePlayer.sendChatMessage("Dat Konami Code");
            }

            @Override
            public void executeServerSide(EntityPlayer player)
            {
                player.addExperience(100);
            }
        });
    }
}
