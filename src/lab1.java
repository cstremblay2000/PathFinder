import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * This class is an orienteering program to find the best
 * path through a terrain implemented using the A* algorithm
 *
 * @author ctremblay
 */
public class lab1 {

    /** Usage message */
    private static final String USAGE_MESSAGE = "Usage: java lab1 terrain-image, elevation-file, path-file, season, output-image-file\n";

    /** how many columns there are */
    public static final int COLS = 395;

    /** how many rows there are */
    public static final int ROWS = 500;

    /**
     * The driver program
     * terrain-image, elevation-file, path-file, season (summer,fall,winter,or spring), output-image-filename.
     * @param args command line args
     */
    public static void main(String[] args) {

        // Check that there is right amount of command line args
        if(args.length != 5){
            System.out.println(USAGE_MESSAGE);
            System.exit(1);
        }

        // Try parsing image
        BufferedImage terrain = readImage(args[0]);


        // read elevation file
        double[][] elevations = readElevations(args[1]);

        // path file
        List<Pair<Integer, Integer>> goalPoints = readGoalPoints(args[2]);

        // Get season
        Season season = null;
        switch (args[3]) {
            case "summer" -> season = Season.SUMMER;
            case "winter" -> season = Season.WINTER;
            case "spring" -> season = Season.SPRING;
            case "fall" -> season = Season.FALL;
            default -> {
                System.out.printf("'%s' is not an appropriate season.\n", args[3]);
                System.out.println(USAGE_MESSAGE);
                System.exit(1);
            }
        }

        // Get output file path
        String outputFile = args[4];

        // Create a path finder
        PathFinder pathFinder = new PathFinder(terrain, elevations, goalPoints, season, outputFile);

        pathFinder.findPath();
    }

    /**
     * Print an error message based on exception
     * @param e the exception that occurred
     * @param arg the argument that was the issue
     */
    private static void errorMessage(Exception e, String arg){
        System.err.println(e.getMessage());
        System.err.println("Error with: " + arg);
        System.out.println(USAGE_MESSAGE);
    }

    /**
     * Get the image from the file path
     * @param path the file path
     * @return the image
     */
    public static BufferedImage readImage( String path ){
        // Try parsing image
        BufferedImage terrain = null;
        File file;
        Scanner scanner;
        try {
            file = new File(path);
            terrain = ImageIO.read(file);
        } catch (Exception e) {
            errorMessage(e, path);
            System.exit(1);
        }

        return terrain;
    }

    /**
     * Get the elevations from the elevation file
     * @param path the filepath
     * @return the elevation list
     */
    public static double[][] readElevations(String path){
        double[][] elevations = new double[ROWS][COLS];
        File file;
        Scanner scanner;
        try{
            file = new File(path);
            scanner = new Scanner(file);
            for(int i = 0; i < ROWS; i++){
                String[] line = scanner.nextLine().split("   ");
                for(int j = 1; j <= COLS; j++){
                    try{
                        float temp = Float.parseFloat(line[j].strip());
                        elevations[i][j-1] = temp;
                    } catch (Exception e) {
                        throw new Exception(String.format("Float '%s' at (%d,%d) malformed", line[j], i, j));
                    }
                }
            }
        } catch (Exception e) {
            errorMessage(e, path);
            System.exit(1);
        }
        return elevations;
    }

    /**
     * Get the goal points from the goal point file
     *
     * @param path the file path
     * @return the list of goal points
     */
    public static List<Pair<Integer, Integer>> readGoalPoints(String path){
        List<Pair<Integer, Integer>> goalPoints = new ArrayList<>();
        File file;
        Scanner scanner;
        try{
            file = new File(path);
            scanner = new Scanner(file);
            while(scanner.hasNext()){
                String[] line = scanner.nextLine().split(" ");
                int x = Integer.parseInt(line[0]);
                int y = Integer.parseInt(line[1]);
                goalPoints.add(new Pair<Integer, Integer>(x,y));
            }
        } catch (Exception e){
            errorMessage(e, path);
        }
        return goalPoints;
    }
}
