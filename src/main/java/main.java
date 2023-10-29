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
		 * O mapa de forma visual ficaria da seguinte forma: -------- -------- 1------2
		 * -------- -------- --3----- -------- -------- 4------5
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
		List<Node> listValidPositions = new ArrayList<>();

		listValidPositions.add(grid[1][3]);
		listValidPositions.add(grid[6][3]);
		listValidPositions.add(grid[3][6]);
		listValidPositions.add(grid[1][9]);
		listValidPositions.add(grid[6][9]);

		for (int x = 0; x < 10; x++) {
			for (int y = 0; y < 10; y++) {
				int value = (x * 10) + y + 1;
				gridSA[x][y] = new Node(x, y);
				gridSA[x][y].setGCost(value);
			}
		}

		gridSA[1][3] = grid[1][3];
		gridSA[6][3] = grid[6][3];
		gridSA[3][6] = grid[3][6];
		gridSA[1][9] = grid[1][9];
		gridSA[6][9] = grid[6][9];

		for (int i = 0; i < listValidPositions.size(); i++) {
			List<Node> resultSA = new ArrayList<>();
			if (i + 1 < listValidPositions.size()) {
				Node actualRote = listValidPositions.get(i);
				Node finalRote = listValidPositions.get(i + 1);
				resultSA.addAll(SA.simulatedAnnealing(actualRote, finalRote, gridSA));
				printResultSA(resultSA, i);
			}
		}

		long finalTimeSA = System.currentTimeMillis();
		System.out.println("Tempo de execução:" + (finalTimeSA - initialTimeSA) + " milisegundos");
	}

	public static void printResult(List<Node> path, long executionTime) {
		System.out.println("==========================================================================");
		System.out.println("Busca por estrela");
		System.out.println(String.format("Tempo de execução: %s milisegundos", executionTime));
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

	public static void printResultSA(List<Node> path, int actualRoteNumber) {
		System.out.println("==========================================================================");
		System.out.println("Rota atual - " + (actualRoteNumber + 1));
		System.out.println("Caminho encontrado:");
		for (Node node : path) {
			System.out.println("Nó: (" + node.getX() + ", " + node.getY() + ")");
		}
		System.out.println("==========================================================================");
	}

}
