import StarSearch.AStar;
import CommomAlgorithm.Node;

import java.util.List;

public class main {
    public static void main(String[] args) {
        int width = 10;
        int height = 10;
        Node[][] grid = new Node[width][height];

        /* O mapa de forma visual ficaria da seguinte forma:
            --------
            --------
            1------2
            --------
            --------
            --3-----
            --------
            --------
            4------5
        */
        grid[1][3] = new Node(1, 3, 16, 17);
        grid[6][3] = new Node(6, 3, 13, 15);
        grid[3][6] = new Node(3, 6, 4, 10);
        grid[1][9] = new Node(1, 9, 8, 13);
        grid[6][9] = new Node(6, 9, 13, 17);

        AStar aStar = new AStar(10, 10);

        List<Node> path1 = aStar.findPath(grid);
        printResult(path1);
    }

    public static void printResult(List<Node> path) {
        if (!path.isEmpty()) {
            System.out.println("Caminho encontrado:");
            for (Node node : path) {
                System.out.println("Nó: (" + node.getX() + ", " + node.getY() + ")");
            }
        } else {
            System.out.println("Nenhum caminho encontrado.");
        }
    }
}
