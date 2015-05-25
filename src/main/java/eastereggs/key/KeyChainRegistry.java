package eastereggs.key;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class KeyChainRegistry
{
    private Map<String, KeyChain> chains;
    private Set<KeyChain> currentSet;
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
        this.chains = new HashMap<String, KeyChain>();
        this.currentSet = new HashSet<KeyChain>(this.chains.values());
        this.index = 0;
    }

    public void addKeyChain(KeyChain keyChain)
    {
        this.chains.put(keyChain.id(), keyChain);
    }

    public void keyPressed(int keyCode)
    {
        Set<KeyChain> temp = new HashSet<KeyChain>(this.currentSet);
        this.currentSet.clear();
        for (KeyChain keyChain : temp)
            if (keyChain.isCodeAt(this.index, keyCode))
                this.currentSet.add(keyChain);
        this.index++;
        if (this.currentSet.isEmpty())
        {
            this.index = 0;
            this.currentSet = new HashSet<KeyChain>(this.chains.values());
        }
    }

    public KeyChain getById(String id)
    {
        return this.chains.get(id);
    }
}
