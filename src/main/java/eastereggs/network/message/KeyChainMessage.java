package eastereggs.network.message;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import eastereggs.key.KeyChain;
import eastereggs.key.KeyChainRegistry;
import io.netty.buffer.ByteBuf;

public class KeyChainMessage implements IMessage, IMessageHandler<KeyChainMessage, IMessage>
{
    private String id;

    public KeyChainMessage()
    {

    }

    public KeyChainMessage(String id)
    {
        this.id = id;
    }


    @Override
    public void fromBytes(ByteBuf buf)
    {
        int length = buf.readInt();
        this.id = new String(buf.readBytes(length).array());
    }

    @Override
    public void toBytes(ByteBuf buf)
    {
        buf.writeInt(this.id.getBytes().length);
        buf.writeBytes(this.id.getBytes());
    }

    @Override
    public IMessage onMessage(KeyChainMessage message, MessageContext ctx)
    {
        KeyChain keyChain = KeyChainRegistry.instance().getById(message.id);
        if (keyChain != null)
            keyChain.executeServerSide(ctx.getServerHandler().playerEntity);
        return null;
    }
}
