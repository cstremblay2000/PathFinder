import javax.xml.stream.events.NotationDeclaration;
import java.awt.image.BufferedImage;
import java.util.*;

/**
 * A class that implements A* search
 *
 * @author ctremblay
 */
public class PathFinder {

    /** The terrain image */
    private BufferedImage terrain;

    /** the list of elevations */
    private double[][] elevations;

    /** the set of sequential goal points */
    private List<Pair<Integer, Integer>> goalPoints;

    /** What season it is */
    private Season season;

    /** Where the solution should be printed */
    private String outputFile;

    /**
     * Create a new PathFinder object
     *
     * @param terrain the terrain to search in
     * @param elevations the elevations of the terrain
     * @param goalPoints the sequence of points to get to
     * @param season which season it is
     * @param outputFile where the solution goes
     */
    public PathFinder(final BufferedImage terrain, double[][] elevations, List<Pair<Integer, Integer>> goalPoints, Season season, String outputFile) {
        this.terrain = terrain;
        this.elevations = elevations;
        this.goalPoints = goalPoints;
        this.season = season;
        this.outputFile = outputFile;

        // look at elevations
        for(int i = 0; i < elevations.length; i++){
            for(int j = 0; j < elevations[0].length; j++){
                System.out.println();
            }
        }
    }

    /**
     *
     * @param startPoint The point we are starting at
     * @param goalPoint The point we are getting to
     * @return The Node with the path of how to get there
     */
    private Node aStar(Pair<Integer, Integer> startPoint, Pair<Integer, Integer> goalPoint ) {
        // create start node
        Set<Node> explored = new HashSet<>();
        PriorityQueue<Node> frontier = new PriorityQueue<>();
        int t = terrain.getRGB(startPoint.getX(), startPoint.getY());
        double e = elevations[startPoint.getX()][startPoint.getY()];
        frontier.add( new Node(startPoint.getX(), startPoint.getY(),
                e, t, 0, null ) );

        // Just exists to check the we have reached goal coordinates
        // Node.equals() method only checks if coordinates are the same
        Node goal = new Node(goalPoint.getX(), goalPoint.getY(), 0, 0, 0, null);

        while( true ){
            // If priority queue is empty all nodes are exhausted, and fail
            if(frontier.isEmpty())
                return null;

            // Get node and test if it goal state
            Node node = frontier.poll();
            if(node.equals(goal))
                return node;

            // Add node to explored nodes
            explored.add(node);

            // Get neighbors
            for( Node child : node.getNeighbors(terrain, elevations) ){

                // If child hasn't been seen yet and its not in frontier
                if( !explored.contains(child) || !frontier.contains(child) )
                    frontier.add(child);

                // Else frontier has child
                if( frontier.contains(child) ) {

                    // Got through elements in frontier
                    for (Node frontierNode : frontier) {

                        // Check cost of two nodes too see which one is better
                        if (frontierNode.equals(child)) {
                            if (child.getCost() < frontierNode.getCost()) {
                                frontier.remove(frontierNode);
                                frontier.add(child);
                                break;
                            }
                        }
                    }
                }
            }
        }
    }

    public void findPath(){
        Pair<Integer, Integer> start = goalPoints.get(0);
        Pair<Integer, Integer> goal = goalPoints.get(1);

        Node path = aStar(start, goal);
        reversePrint(path);
    }

    private void reversePrint(Node node){
        if(node == null)
            return;
        reversePrint(node.getParent());
        System.out.println(node);
    }

    private void hueristic(){
        // TODO
    }


    private void distance(int x1, int y1, int z1, int x2, int y2, int z2){
        // TODO
    }
}
