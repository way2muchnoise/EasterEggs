package eastereggs.utils;

/**
 * Easy way to send around x, y, z
 */
public class WorldCoord
{
    private int x, y, z;

    public WorldCoord(int x, int y, int z)
    {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public int getX()
    {
        return x;
    }

    public int getY()
    {
        return y;
    }

    public int getZ()
    {
        return z;
    }
}
