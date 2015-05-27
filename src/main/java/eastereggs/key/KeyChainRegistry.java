package eastereggs.key;

import eastereggs.api.IKeyChain;
import eastereggs.network.MessageHandler;
import eastereggs.network.message.KeyChainMessage;
import eastereggs.utils.LogHelper;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class KeyChainRegistry
{
    private Map<String, IKeyChain> chains;
    private Set<IKeyChain> currentSet;
    private int index;
    private static KeyChainRegistry instance;

    public static KeyChainRegistry instance()
    {
        if (instance == null)
            instance = new KeyChainRegistry();
        return instance;
    }

    private KeyChainRegistry()
    {
        this.chains = new HashMap<String, IKeyChain>();
        this.currentSet = new HashSet<IKeyChain>(this.chains.values());
        this.index = 0;
    }

    public void addKeyChain(IKeyChain keyChain)
    {
        this.chains.put(keyChain.id(), keyChain);
    }

    public void keyPressed(int keyCode)
    {
        Set<IKeyChain> temp = new HashSet<IKeyChain>(this.currentSet);
        this.currentSet.clear();
        for (IKeyChain keyChain : temp)
            if (keyChain.isCodeAt(this.index, keyCode))
                this.currentSet.add(keyChain);
        for (IKeyChain keyChain : this.currentSet)
        {
            if (keyChain.isLastCode(this.index))
            {
                LogHelper.debug("Executed cheat with id: " + keyChain.id());
                MessageHandler.INSTANCE.sendToServer(new KeyChainMessage(keyChain.id()));
                keyChain.executeClientSide();
            }
        }
        this.index++;
        if (this.currentSet.isEmpty())
        {
            this.index = 0;
            this.currentSet = new HashSet<IKeyChain>(this.chains.values());
        }
    }

    public IKeyChain getById(String id)
    {
        return this.chains.get(id);
    }
}
