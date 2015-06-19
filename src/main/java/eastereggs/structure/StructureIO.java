package eastereggs.structure;

import cpw.mods.fml.common.registry.GameRegistry;
import eastereggs.utils.MetaDataBlock;
import net.minecraft.init.Blocks;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.util.List;

public class StructureIO
{

    public static void serialize(MetaDataBlock[][][] dim3, String fileName)
    {
        try
        {
            FileOutputStream fos = new FileOutputStream(fileName + ".txt");

            for (MetaDataBlock[][] dim2 : dim3)
            {
                for (MetaDataBlock[] dim1 : dim2)
                {
                    for (MetaDataBlock block : dim1)
                    {
                        if (block == null)
                            block = new MetaDataBlock(Blocks.air);
                        fos.write(("<" + block.toString() + "> ").getBytes());
                    }
                    fos.write(("\n").getBytes());
                }
                fos.write(("\n").getBytes());
            }
            fos.close();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public static MetaDataBlock[][][] deserialize(String fileName)
    {
        MetaDataBlock[][][] dim3 = null;
        try
        {
            List<String> raw = Files.readAllLines(FileSystems.getDefault().getPath(fileName + ".txt"), Charset.defaultCharset());
            int dim1s = raw.get(0).split(" ").length;
            int dim2s = raw.indexOf("");
            int dim3s = 0;
            for (int i = 0; i < raw.size(); i++)
                if (raw.get(i).equals(""))
                    dim3s++;
            dim3 = new MetaDataBlock[dim3s][dim2s][dim1s];
            int id1, id2, id3;
            id1 = id2 = id3 = 0;
            for (String line : raw)
            {
                String[] blocks = line.split(" ");
                for (String block : blocks)
                {
                    if (block.equals("")) continue;
                    block = block.substring(1, block.length() - 1);
                    String[] parts = block.split(":");
                    dim3[id3][id2][id1] = new MetaDataBlock(GameRegistry.findBlock(parts[0], parts[1]), Integer.getInteger(parts[2], 0));
                    id1++;
                }
                id1 = 0;
                id2++;
                if (line.equals(""))
                {
                    id3++;
                    id2 = 0;
                }
            }
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        return dim3;
    }
}