import CommomAlgorithm.Node;

public class main {
    public static void main(String[] args) {
        int width = 9;
        int height = 9;
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
        grid[1][3] = new Node(1, 5, 1,1);
        grid[6][3] = new Node(15, 25, 2, 5);
        grid[3][6] = new Node(6, 12, 4, 10);
        grid[1][9] = new Node(29, 24, 8, 13);
        grid[6][9] = new Node(29, 24, 13, 17);

        Node startNode = grid[0][0];
        Node endNode = grid[8][8];

    }
}
