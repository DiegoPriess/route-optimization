import StarSearch.AStar;
import CommomAlgorithm.Node;
import SimulatedAnnealing.SimulatedAnnealing;

import java.util.ArrayList;
import java.util.List;

public class main {
	public static void main(String[] args) {
		int width = 10;
		int height = 10;

		Node[][] grid = new Node[width][height];

		/*
		 * O mapa de forma visual ficaria da seguinte forma:
		 * --------
		 * --------
		 * 1------2
		 * --------
		 * --------
		 * --3-----
		 * --------
		 * --------
		 * 4------5
		 */
		grid[1][3] = new Node(1, 3, 4, 2);
		grid[6][3] = new Node(6, 3, 8, 4);
		grid[3][6] = new Node(3, 6, 11, 7);
		grid[1][9] = new Node(1, 9, 16, 11);
		grid[6][9] = new Node(6, 9, 13, 9);

		long initialTime = System.currentTimeMillis();
		AStar aStar = new AStar(10, 10);
		List<Node> pathStarSearch = aStar.findPath(grid);
		long finalTime = System.currentTimeMillis();
		printResult(pathStarSearch, finalTime - initialTime);

		long initialTimeSA = System.currentTimeMillis();
		SimulatedAnnealing SA = new SimulatedAnnealing();

		Node[][] gridSA = new Node[width][height];
		List<Node> pathSA = new ArrayList<>();
		pathSA.add(grid[1][3]);
		pathSA.add(grid[6][3]);
		pathSA.add(grid[3][6]);
		pathSA.add(grid[1][9]);
		pathSA.add(grid[6][9]);
		for (int x = 0; x < 10; x++) {
			for (int y = 0; y < 10; y++) {
				int value = (x * 10) + y + 1;
				gridSA[x][y] = new Node(x, y);
				gridSA[x][y].setGCost(value);
			}
		}

		List<Node> resultSA = SA.simulatedAnnealing(pathSA, grid);

		long finalTimeSA = System.currentTimeMillis();
		printResult(resultSA, finalTimeSA - initialTimeSA);
	}

	public static void printResult(List<Node> path, long executionTime) {
		System.out.println("==========================================================================");
		System.out.println("Busca por estrela");
		System.out.println(String.format("Tempo de execução: %s Segundos", executionTime / 1000));
		if (!path.isEmpty()) {
			System.out.println("Caminho encontrado:");
			for (Node node : path) {
				System.out.println("Nó: (" + node.getX() + ", " + node.getY() + ")");
			}
		} else {
			System.out.println("Nenhum caminho encontrado.");
		}
		System.out.println("==========================================================================");
	}
}
