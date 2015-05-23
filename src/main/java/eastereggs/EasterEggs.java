package eastereggs;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.ModMetadata;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLLoadCompleteEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import eastereggs.event.KeyboardInput;
import eastereggs.key.KeyChain;
import eastereggs.reference.Metadata;
import eastereggs.reference.Reference;


@Mod(modid = Reference.ID, name = Reference.NAME, version = Reference.VERSION_FULL)
public class EasterEggs
{
    @Mod.Instance(Reference.ID)
    public static EasterEggs instance;

    @Mod.Metadata(Reference.ID)
    public static ModMetadata metadata;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        metadata = Metadata.init(metadata);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event)
    {
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
    }

    @Mod.EventHandler
    public void loadComplete(FMLLoadCompleteEvent event)
    {
        FMLCommonHandler.instance().bus().register(new KeyboardInput());
        KeyChain.init();
    }
}
