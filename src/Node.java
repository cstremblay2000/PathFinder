import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.DoubleBinaryOperator;

/**
 * A
 */
public class Node implements Comparable<Node> {

    /** The x coordinate of the pixel */
    private int x;

    /** The y coordinate of the pixel */
    private int y;

    /** The z coordinate of the pixel, or elevation */
    private double z;

    /** The current cost to get to this node */
    private double cost;

    /** The node in which this was spawned from */
    private Node parent;

    /** The terrain type */
    private int terrain;

    /**
     * Create a new node, when cost isn't known
     *
     * @param x the x coordinate
     * @param y the y coordinate
     * @param z the elevation of the node
     * @param terrain the cost to get to that node
     * @param parent the parent of the node
     */
    public Node(int x, int y, double z, int terrain, Node parent){
        this.x = x;
        this.y = y;
        this.z = z;
        this.terrain = terrain;
        this.parent = parent;
        this.cost = cost(parent);
    }

    /**
     * Create a new node, this is the constructor for what is the
     * start state of a search, where the cost is known
     *
     * @param x the x coordinate
     * @param y the y coordinate
     * @param z the elevation of the node
     * @param terrain the cost to get to that node
     * @param cost the cost to get to one node from another
     * @param parent the parent node, for chaining multiple searches together
     */
    public Node(int x, int y, double z, int terrain, double cost, Node parent ){
        this.x = x;
        this.y = y;
        this.z = z;
        this.terrain = terrain;
        this.parent = parent;
        this.cost = cost;
    }

    // TODO
    public double cost(Node node){
        return node.getCost() + distance(node);
    }

    /**
     * Calculate distance from one node to another
     *
     * @param to node to get to
     * @return the distance
     */
    public double distance(Node to){
        double x_dist = Math.pow(this.x - to.getX(), 2);
        double y_dist = Math.pow(this.y - to.getY(), 2);
        double z_dist = Math.pow(this.z - to.getZ(), 2);
        return Math.pow(x_dist + y_dist + z_dist, 0.5);
    }

    /**
     * Return a list of neighboring pixels encapsulated into a node
     * Checks the cardinal directions
     *
     * @param terrain the image of the terrain
     * @param elevations the list of elevations
     * @return the list of generated children
     */
    public List<Node> getNeighbors(BufferedImage terrain, double[][] elevations){
        List<Node> neighbors = new ArrayList<>();

        // left node
        if(x-1 >= 0) {
            neighbors.add(new Node(x - 1, y,
                    elevations[x - 1][y], terrain.getRGB(x-1, y),
                    this));
        }

        // right node
        if(x+1 < lab1.ROWS) {
            neighbors.add(new Node(x + 1, y,
                    elevations[x + 1][y], terrain.getRGB(x+1, y),
                    this));
        }

        // top node
        if(y-1 >= 0) {
            neighbors.add(new Node( x, y-1,
                    elevations[x][y-1], terrain.getRGB(x, y-1),
                    this));
        }

        // bottom node
        if(y+1 < lab1.ROWS) {
            neighbors.add(new Node(x, y+1,
                    elevations[x][y+1], terrain.getRGB(x, y+1),
                    this));
        }

        return neighbors;
    }

    /**
     * Get the x coordinate
     * @return the x coordinate
     */
    public int getX() {
        return x;
    }

    /**
     * Get the y coordinate
     * @return the y coordinate
     */
    public int getY() {
        return y;
    }

    /**
     * Get the z coordinate
     * @return the z coordinate
     */
    public double getZ() {
        return z;
    }

    /**
     * Get the parent of the node
     * @return the parent
     */
    public Node getParent() {
        return parent;
    }

    /**
     * Get the terrain type of the node
     * @return the terrain type
     */
    public int getTerrain() {
        return terrain;
    }

    /**
     * Get the cost of the node
     * @return the cost
     */
    public double getCost() {
        return cost;
    }

    /**
     * Check that coordinates and cost are the same
     *
     * @param o object to check agaist
     * @return true if they are the same, false if not
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return x == node.x && y == node.y;
    }

    /**
     * Create a hashcode for the node
     * @return the hash code
     */
    @Override
    public int hashCode() {
        return Objects.hash(x, y, cost);
    }

    /**
     * Compare the distances to see which node is better
     * @param node the node to check against
     * @return int value which node is greater
     */
    @Override
    public int compareTo(Node node) {
        return Double.compare(cost, node.getCost());
    }

    /**
     * Pretty pring
     * @return the better formatted string
     */
    @Override
    public String toString() {
        return "Node{" +
                "x=" + x +
                ", y=" + y +
                ", cost=" + cost +
                '}';
    }
}
