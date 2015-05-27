package eastereggs.key;

import eastereggs.api.IKeyChain;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import org.lwjgl.input.Keyboard;

public abstract class KeyChain implements IKeyChain
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

    private KeyChain(String id, String... textCodes)
    {
        this.id = id;
        this.codeChain = new int[textCodes.length];
        int i = 0;
        for (String textCode : textCodes)
            this.codeChain[i++] = Keyboard.getKeyIndex(textCode.toUpperCase());
    }

    private KeyChain(String id, int... keyCodes)
    {
        this.id = id;
        this.codeChain = keyCodes;
    }

    public boolean isCodeAt(int index, int code)
    {
        return index < codeChain.length && codeChain[index] == code;
    }

    @Override
    public boolean isLastCode(int index)
    {
        return index+1 == codeChain.length;
    }

    private static void add(KeyChain chain)
    {
        KeyChainRegistry.instance().addKeyChain(chain);
    }

    public static void init()
    {
        add(new KeyChain("Konami Code", "up", "up", "down", "down", "left", "right", "left", "right", "b", "a")
        {
            @Override
            public void executeClientSide()
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
