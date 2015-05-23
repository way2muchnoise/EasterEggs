package eastereggs.key;

import java.util.HashSet;
import java.util.Set;

public class KeyChainRegistry
{
    private Set<KeyChain> chains;
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
        this.chains = new HashSet<KeyChain>();
        this.currentSet = new HashSet<KeyChain>(this.chains);
        this.index = 0;
    }

    public void addKeyChain(KeyChain keyChain)
    {
        this.chains.add(keyChain);
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
            this.currentSet = new HashSet<KeyChain>(this.chains);
        }
    }
}
