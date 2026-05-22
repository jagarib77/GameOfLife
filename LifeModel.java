package Projects.GameOfLife;

// Jean Raphael Barbosa Santos, CS 142, Winter 2026
// Programming Project # 5, 03/02/2026

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class LifeModel {

    private int[][] grid;
    private static final int isAlive = 1;

    /*
      Constructs a LifeModel that stores a grid of the data in the passed in file.
     */
    public LifeModel(String fileName) throws FileNotFoundException {
        Scanner sc = new Scanner(new File(fileName));

        // Read number of rows and columns
        int row = sc.nextInt();
        int col = sc.nextInt();

        grid = new int[row][col];

        int r = 0;
        while (sc.hasNextLine() && r < row) {
            String line = sc.nextLine().trim();
            if (line.isEmpty() || line.startsWith("#")) continue; //skip comment/blank lines

            for (int c = 0; c < col; c++) {
                char ch = (c < line.length()) ? line.charAt(c) : '-';
                if (ch == 'X') {
                    grid[r][c] = 1;
                } else {
                    grid[r][c] = 0;
                }

            }
            r++;
        }
    }

    // Returns the number of rows in the grid
    public int getRows() {
        return grid.length;
    }

    // Returns the number of columns in the grid
    public int getCols() {
        return grid[0].length;
    }

    // Returns true if the cell at (row, col) is alive
    public boolean isAlive(int row, int col) {
        return grid[row][col] == isAlive;
    }

    /*
       Updates the grid using the bacteria rules:
       - Alive cell with ≤1 or ≥4 neighbors dies
       - Alive cell with 2 or 3 neighbors survives
       - Dead cell with 3 neighbors becomes alive
     */
    public void update() {
        int row = grid.length;
        int col = grid[0].length;
        int[][] newGenGrid = new int[row][col];

        for (int r = 0; r < row; r++) {
            for (int c = 0; c < col; c++) {
                int aliveNeighbor = 0;

                //Count neighbors
                for (int i = -1; i <= 1; i++) {
                    for (int j = -1; j <= 1; j++) {
                        if (i == 0 && j == 0) continue; //Skip current cell
                        int newRow = r + i;
                        int newCol = c + j;
                        if (newRow >= 0 && newRow < row && newCol >= 0 && newCol < col) {
                            if (grid[newRow][newCol] == 1) aliveNeighbor++;
                        }
                    }
                }

                // Apply rules
                if (grid[r][c] == 1) {
                    if (aliveNeighbor <= 1 || aliveNeighbor >= 4) { //if less than 1 and more than 4 dies
                        newGenGrid[r][c] = 0;
                    } else {
                        newGenGrid[r][c] = 1; // 2 or 3 neighbors new life
                    }
                } else {
                    if (aliveNeighbor == 3) { //if cell is dead
                        newGenGrid[r][c] = 1; //add a new cell
                    }
                }
            }
        }

        grid = newGenGrid; //Updates the grid
    }

    // Returns a string representation of the grid using 'X' for alive and '-' for dead
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[0].length; c++) {
                sb.append(grid[r][c] == 1 ? "X" : "-");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}

