/******************************************************************************
 * Compilation: javac Maze.java Execution: java Maze.java N Dependencies:
 * StdDraw.java
 *
 * Generates a perfect N-by-N maze using depth-first search with a stack.
 *
 * % java Maze 62
 *
 * % java Maze 61
 *
 * Note: this program generalizes nicely to finding a random tree in a graph.
 *
 ******************************************************************************/

public class Maze {
	private int N; // dimension of maze
	private boolean[][] north; // is there a wall to north of cell i, j
	private boolean[][] east;
	private boolean[][] south;
	private boolean[][] west;
	private boolean[][] visited;
	private boolean done = false;
	private int[] goal = new int[2];

	public Maze(int N) {
		this.N = N;
		StdDraw.setXscale(0, N + 2);
		StdDraw.setYscale(0, N + 2);
		init();
		generate();

		goal[0] = 1;
		goal[1] = 1;

		while (goal[0] == 1 && goal[1] == 1) {
			goal[0] = (int) (Math.random() * (N - 2)) + 1;
			goal[1] = (int) (Math.random() * (N - 2)) + 1;
		}
		// goal[0] = N;
		// goal[1] = N;
	}

	private void init() {
		// initialize border cells as already visited
		visited = new boolean[N + 2][N + 2];
		for (int x = 0; x < N + 2; x++) {
			visited[x][0] = true;
			visited[x][N + 1] = true;
		}
		for (int y = 0; y < N + 2; y++) {
			visited[0][y] = true;
			visited[N + 1][y] = true;
		}

		// initialze all walls as present
		north = new boolean[N + 2][N + 2];
		east = new boolean[N + 2][N + 2];
		south = new boolean[N + 2][N + 2];
		west = new boolean[N + 2][N + 2];
		for (int x = 0; x < N + 2; x++) {
			for (int y = 0; y < N + 2; y++) {
				north[x][y] = true;
				east[x][y] = true;
				south[x][y] = true;
				west[x][y] = true;
			}
		}
	}

	// generate the maze
	private void generate(int x, int y) {
		visited[x][y] = true;

		// while there is an unvisited neighbor
		while (!visited[x][y + 1] || !visited[x + 1][y] || !visited[x][y - 1] || !visited[x - 1][y]) {

			// pick random neighbor (could use Knuth's trick instead)
			while (true) {
				double r = StdRandom.uniform(4);
				if (r == 0 && !visited[x][y + 1]) {
					north[x][y] = false;
					south[x][y + 1] = false;
					generate(x, y + 1);
					break;
				} else if (r == 1 && !visited[x + 1][y]) {
					east[x][y] = false;
					west[x + 1][y] = false;
					generate(x + 1, y);
					break;
				} else if (r == 2 && !visited[x][y - 1]) {
					south[x][y] = false;
					north[x][y - 1] = false;
					generate(x, y - 1);
					break;
				} else if (r == 3 && !visited[x - 1][y]) {
					west[x][y] = false;
					east[x - 1][y] = false;
					generate(x - 1, y);
					break;
				}
			}
		}
	}

	// generate the maze starting from lower left
	private void generate() {
		generate(1, 1);

		/*
		 * // delete some random walls for (int i = 0; i < N; i++) { int x = 1 +
		 * StdRandom.uniform(N-1); int y = 1 + StdRandom.uniform(N-1);
		 * north[x][y] = south[x][y+1] = false; }
		 * 
		 * // add some random walls for (int i = 0; i < 10; i++) { int x = N/2 +
		 * StdRandom.uniform(N/2); int y = N/2 + StdRandom.uniform(N/2);
		 * east[x][y] = west[x+1][y] = true; }
		 */

	}

	private void drawBlueDot(int x, int y) {
		StdDraw.setPenColor(StdDraw.BLUE);
		StdDraw.filledCircle(x + 0.5, y + 0.5, 0.25);
		StdDraw.show(30);
	}

	private void drawGrayDot(int x, int y) {
		StdDraw.setPenColor(StdDraw.GRAY);
		StdDraw.filledCircle(x + 0.5, y + 0.5, 0.25);
		StdDraw.show(30);
	}

	// solve the maze using depth-first search
	private void agent(int x, int y) {
		if (x == 0 || y == 0 || x == N + 1 || y == N + 1) {
			return; // this is the wall which is not reachable.
		}
		// Here is your work!
		if (this.done || this.visited[x][y]) {
			return;
		}
		this.visited[x][y] = true;
		
		this.drawBlueDot(x, y);

		if (x == this.goal[0] && y == this.goal[1]) {
			this.done = true;
		}

		if (this.north[x][y] == false) {
			this.agent(x, y + 1);
		}
		if (this.east[x][y] == false) {
			this.agent(x + 1, y);
		}
		if (this.south[x][y] == false) {
			this.agent(x, y - 1);
		}
		if (this.west[x][y] == false) {
			this.agent(x - 1, y);
		}

		if (this.done == true) {
			return;
		}

		this.drawGrayDot(x, y);
	}

	// solve the maze starting from the start state
	public void solve() {
		for (int x = 1; x <= N; x++)
			for (int y = 1; y <= N; y++)
				visited[x][y] = false;
		done = false;
		agent(1, 1);
	}

	// draw the maze
	public void draw() {
		StdDraw.setPenColor(StdDraw.RED);
		StdDraw.filledCircle(goal[0] + 0.5, goal[1] + 0.5, 0.375);
		StdDraw.filledCircle(1.5, 1.5, 0.375);

		StdDraw.setPenColor(StdDraw.BLACK);
		for (int x = 1; x <= N; x++) {
			for (int y = 1; y <= N; y++) {
				if (south[x][y])
					StdDraw.line(x, y, x + 1, y);
				if (north[x][y])
					StdDraw.line(x, y + 1, x + 1, y + 1);
				if (west[x][y])
					StdDraw.line(x, y, x, y + 1);
				if (east[x][y])
					StdDraw.line(x + 1, y, x + 1, y + 1);
			}
		}
		StdDraw.show(500);
	}

	// a test client
	public static void main(String[] args) {
		// int N = Integer.parseInt(args[0]);
		Maze maze = new Maze(10);
		StdDraw.show(0);
		maze.draw();
		maze.solve();
	}

}
