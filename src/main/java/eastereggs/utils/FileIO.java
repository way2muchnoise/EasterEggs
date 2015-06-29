package eastereggs.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.util.LinkedList;
import java.util.List;

public class FileIO
{
    /**
     * Get all file names
     * @param path the folder path relative or absolute
     * @param nested check folders in folder
     * @return a list of file names
     */
    public static List<String> getFilesInDir(String path, boolean nested)
    {
        File dir = new File(path);
        return getFilesInDir(dir, nested);
    }

    /**
     * Get all file names
     * @param dir the {@link File} of the folder
     * @param nested check folders in folder
     * @return a list of file names
     */
    public static List<String> getFilesInDir(File dir, boolean nested)
    {
        List<String> files = new LinkedList<String>();
        File[] content = dir.listFiles();
        if (content != null)
        {
            for (File file : content)
            {
                if (file.isDirectory() && nested)
                    files.addAll(getFilesInDir(file, nested));
                else if (file.isFile())
                    files.add(file.getName());
            }
        }
        return files;
    }

    /**
     * Read file with given name
     * @param filename the file to read
     * @return null if read fails or all lines if successful
     */
    public static List<String> getLines(String filename)
    {
        try
        {
            return Files.readAllLines(FileSystems.getDefault().getPath(filename + ".txt"), Charset.defaultCharset());
        } catch (IOException e)
        {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Write a content to a file
     * @param filename the file to write to
     * @param content the content to fill the file with
     * @return true if successful
     */
    public static boolean writeFIle(String filename, String content)
    {
        FileOutputStream fos = null;
        try
        {
            fos = new FileOutputStream(filename + ".txt");
            fos.write(content.getBytes());
        } catch (IOException e)
        {
            e.printStackTrace();
            return false;
        } finally
        {
            if (fos != null)
            {
                try
                {
                    fos.close();
                } catch (IOException e)
                {
                    // Something went horribly wrong should never happen
                    e.printStackTrace();
                }
            }
        }
        return true;
    }
}
