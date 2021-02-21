/**
 * A class that holds a bunch of colors for terrain types
 * @author ctremblay
 */
public abstract class Terrain {
    /** Open land terrain type */
    public static final int OPEN_LAND = 0xF89412;

    /** Rough meadow terrain type */
    public static final int ROUGH_MEADOW = 0xFFC000;

    /** Easy movement forest terrain type */
    public static final int EASY_MOVEMENT_FOREST = 0xFFFFFF;

    /** Slow run forest terrain type */
    public static final int SLOW_RUN_FOREST = 0x02D03C;

    /** Walk forest terrain type*/
    public static final int WALK_FOREST = 0x028828;

    /** Impassible vegetation terrain type */
    public static final int IMPASSIBLE_VEGETATION = 0x054918;

    /** Lake/swamp/marsh terrain type */
    public static final int LAKE_SWAMP_MARSH = 0x0000FF;

    /** Paved road terrain type */
    public static final int PAVED_ROAD = 0x473303;

    /** Foot path terrain type */
    public static final int FOOT_PATH = 0x00000000;

    /** Out of bounds terrain type */
    public static final int OUT_OF_BOUNDS = 0xCD0065;
}
