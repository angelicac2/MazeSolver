/**
 * Solves the given maze using DFS or BFS
 * @author Ms. Namasivayam
 * @version 03/10/2023
 */

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class MazeSolver {
    private Maze maze;

    public MazeSolver() {
        this.maze = null;
    }

    public MazeSolver(Maze maze) {
        this.maze = maze;
    }

    public void setMaze(Maze maze) {
        this.maze = maze;
    }

    /**
     * Starting from the end cell, backtracks through
     * the parents to determine the solution
     * @return An arraylist of MazeCells to visit in order
     */
    public ArrayList<MazeCell> getSolution() {
        // Stack for cells to be inputted in reverse order
        Stack<MazeCell> reverse = new Stack<>();
        // ArrayList to make reversed Stack forward
        ArrayList<MazeCell> forward = new ArrayList<MazeCell>();
        // New cell being explored
        MazeCell newCell;
        // Old cell being explored
        MazeCell oldCell = maze.getEndCell();
        reverse.push(oldCell);
        while (oldCell != maze.getStartCell()) {
            // Get the parent of the cell that was just explored, assign it as the new cell
            newCell = oldCell.getParent();
            // Push this cell into stack
            reverse.push(newCell);
            oldCell = newCell;
        }
        int size = reverse.size();
        for (int i = 0; i < size; i++) {
            // Add cells in forward order with ArrayList by popping LIFO stack that was in reverse order
            forward.add(reverse.pop());
        }
        return forward;
    }

    /**
     * Performs a Depth-First Search to solve the Maze
     * @return An ArrayList of MazeCells in order from the start to end cell
     */
    public ArrayList<MazeCell> solveMazeDFS() {
        // TODO: Use DFS to solve the maze
        // Explore the cells in the order: NORTH, EAST, SOUTH, WEST (stack)
        ArrayList<MazeCell> solveMaze = new ArrayList<MazeCell>();
        Stack<MazeCell> dfs = new Stack<>();
        MazeCell newCell = null;
        dfs.add(maze.getStartCell());
        while (!(newCell.equals(maze.getEndCell()) && !dfs.isEmpty())) {
            newCell = dfs.pop();
            // Look north cell
            if (maze.isValidCell(newCell.getRow() - 1, newCell.getCol())) {
                // Add the cell to the stack if it is valid
                dfs.push(maze.getCell(newCell.getRow() - 1, newCell.getCol()));
                // Set cell that has just been explored to IsExplored, true
                maze.getCell(newCell.getRow() - 1, newCell.getCol()).setExplored(true);
            }
            // Look east cell
            else if (maze.isValidCell(newCell.getRow(), newCell.getCol() + 1)) {
                dfs.push(maze.getCell(newCell.getRow(), newCell.getCol() + 1));
                maze.getCell(newCell.getRow(), newCell.getCol() + 1).setExplored(true);
            }
            // Look south cell
            else if (maze.isValidCell(newCell.getRow() + 1, newCell.getCol())) {
                dfs.push(maze.getCell(newCell.getRow() + 1, newCell.getCol()));
                maze.getCell(newCell.getRow() + 1, newCell.getCol()).setExplored(true);
            }
            // Look west cell
            else if (maze.isValidCell(newCell.getRow(), newCell.getCol() - 1)) {
                dfs.push(maze.getCell(newCell.getRow(), newCell.getCol() - 1));
                maze.getCell(newCell.getRow(), newCell.getCol() - 1).setExplored(true);
            }
        }
        for (int i = 0; i < getSolution().size(); i++) {
            if (dfs.get(i) == getSolution().get(i)) {
                solveMaze.add(dfs.get(i));
            }
        }
        return solveMaze;
    }

    /**
     * Performs a Breadth-First Search to solve the Maze
     * @return An ArrayList of MazeCells in order from the start to end cell
     */
    public ArrayList<MazeCell> solveMazeBFS() {
        // TODO: Use BFS to solve the maze
        ArrayList<MazeCell> solveMaze = new ArrayList<MazeCell>();
        Queue<MazeCell> bfs = new LinkedList<>();
        MazeCell newCell = null;
        bfs.add(maze.getStartCell());
        while (!(newCell == maze.getEndCell()) && !bfs.isEmpty()) {
            newCell = bfs.peek();
            // Look north cell
            if (maze.isValidCell(newCell.getRow() - 1, newCell.getCol())) {
                // Add the cell to the queue if it is valid
                bfs.add(maze.getCell(newCell.getRow() - 1, newCell.getCol()));
                // Set cell that has just been explored to IsExplored, true
                maze.getCell(newCell.getRow() - 1, newCell.getCol()).setExplored(true);
            }
            // Look east cell
            if (maze.isValidCell(newCell.getRow(), newCell.getCol() + 1)) {
                bfs.add(maze.getCell(newCell.getRow(), newCell.getCol() + 1));
                maze.getCell(newCell.getRow(), newCell.getCol() + 1).setExplored(true);
            }
            // Look south cell
            if (maze.isValidCell(newCell.getRow() + 1, newCell.getCol())) {
                bfs.add(maze.getCell(newCell.getRow() + 1, newCell.getCol()));
                maze.getCell(newCell.getRow() + 1, newCell.getCol()).setExplored(true);
            }
            // Look west cell
            if (maze.isValidCell(newCell.getRow(), newCell.getCol() - 1)) {
                bfs.add(maze.getCell(newCell.getRow(), newCell.getCol() - 1));
                maze.getCell(newCell.getRow(), newCell.getCol() - 1).setExplored(true);
            }
        }
        return solveMaze;
    }

    public static void main(String[] args) {
        // Create the Maze to be solved
        Maze maze = new Maze("Resources/maze3.txt");

        // Create the MazeSolver object and give it the maze
        MazeSolver ms = new MazeSolver();
        ms.setMaze(maze);

        // Solve the maze using DFS and print the solution
        ArrayList<MazeCell> sol = ms.solveMazeDFS();
        maze.printSolution(sol);

        // Reset the maze
        maze.reset();

        // Solve the maze using BFS and print the solution
        sol = ms.solveMazeBFS();
        maze.printSolution(sol);
    }
}
