package StarSearch;

import CommomAlgorithm.Node;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AStar {
    private Node[][] grid;
    private List<Node> openSet;
    private List<Node> closedSet;

    public AStar(int width, int height) {
        grid = new Node[width][height];
        openSet = new ArrayList<>();
        closedSet = new ArrayList<>();
    }

    public List<Node> findPath(Node[][] grid) {
        for (var i = 0; i < 10; i++) {
            for (var j = 0; j < 10; j++) {
                if(grid[i][j] != null) {
                    openSet.add(grid[i][j]);
                }
            }
        }

        Node endNode = grid[6][9];

        while (!openSet.isEmpty()) {
            Node current = findLowestFCostNode(openSet);

            if (current == grid[9][9]) {
                return reconstructPath(current);
            }

            openSet.remove(current);
            closedSet.add(current);

            List<Node> neighbors = getNeighbors(current);

            for (Node neighbor : neighbors) {
                if (closedSet.contains(neighbor)) {
                    continue;
                }

                double tentativeGCost = current.getGCost() + calculateDistance(current, neighbor);

                if (!openSet.contains(neighbor) || tentativeGCost < neighbor.getGCost()) {
                    neighbor.setParent(current);
                    neighbor.setGCost(tentativeGCost);
                    neighbor.setHCost(calculateDistance(neighbor, endNode));
                    if (!openSet.contains(neighbor)) {
                        openSet.add(neighbor);
                    }
                }
            }
        }

        return closedSet;
    }

    private Node findLowestFCostNode(List<Node> nodes) {
        Node lowest = nodes.get(0);
        for (Node node : nodes) {
            if (node.getFCost() < lowest.getFCost()) {
                lowest = node;
            }
        }
        return lowest;
    }

    private List<Node> getNeighbors(Node node) {
        List<Node> neighbors = new ArrayList<>();
        int x = node.getX();
        int y = node.getY();

        if (x > 0 && grid[x - 1][y] != null) {
            neighbors.add(grid[x - 1][y]);
        }
        if (x < grid.length - 1 && grid[x + 1][y] != null) {
            neighbors.add(grid[x + 1][y]);
        }
        if (y > 0 && grid[x][y - 1] != null) {
            neighbors.add(grid[x][y - 1]);
        }
        if (y < grid[0].length - 1 && grid[x][y + 1] != null) {
            neighbors.add(grid[x][y + 1]);
        }

        return neighbors;
    }

    private double calculateDistance(Node node1, Node node2) {
        if (node1 == null || node2 == null) {
            return Double.MAX_VALUE;
        }

        int x1 = node1.getX();
        int y1 = node1.getY();
        int x2 = node2.getX();
        int y2 = node2.getY();

        double distance = Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));

        return distance;
    }

    private List<Node> reconstructPath(Node node) {
        List<Node> path = new ArrayList<>();
        while (node != null) {
            path.add(node);
            node = node.getParent();
        }
        Collections.reverse(path);
        return path;
    }
}
