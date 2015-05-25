package eastereggs.network;

import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;
import eastereggs.network.message.KeyChainMessage;
import eastereggs.reference.Reference;

public class MessageHandler
{
    public static SimpleNetworkWrapper INSTANCE = new SimpleNetworkWrapper(Reference.ID);
    private static int id;

    public static void init()
    {
        INSTANCE.registerMessage(KeyChainMessage.class, KeyChainMessage.class, id++, Side.SERVER);
    }
}
