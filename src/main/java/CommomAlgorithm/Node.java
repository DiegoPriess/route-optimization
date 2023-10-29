package CommomAlgorithm;

public class Node {
    private int x, y;
    private Node parent;
    private double gCost;
    private double hCost;

    public Node(int x, int y, double gCost, double hCost) {
        this.x = x;
        this.y = y;
        this.gCost = gCost;
        this.hCost = hCost;
    }

	public Node(int x, int y) {
        this.x = x;
        this.y = y;
        this.parent = null;
        this.gCost = 0.0;
        this.hCost = 0.0;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public double getFCost() {
        return gCost + hCost;
    }

    public double getGCost() {
        return gCost;
    }

    public void setGCost(double gCost) {
        this.gCost = gCost;
    }

    public double getHCost() {
        return hCost;
    }

    public void setHCost(double hCost) {
        this.hCost = hCost;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }
}
