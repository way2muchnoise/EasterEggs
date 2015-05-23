package eastereggs.event;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent;
import eastereggs.key.KeyChainRegistry;
import eastereggs.utils.LogHelper;
import org.lwjgl.input.Keyboard;

public class KeyboardInput
{
    @SubscribeEvent
    public void keyPressed(InputEvent.KeyInputEvent event)
    {
        if (Keyboard.getEventKeyState())
        {
            LogHelper.info("Pressed Key: " + Keyboard.getEventKey() + "( " + Keyboard.getEventCharacter() + " )");
            KeyChainRegistry.instance().keyPressed(Keyboard.getEventKey());
        }
    }
}
