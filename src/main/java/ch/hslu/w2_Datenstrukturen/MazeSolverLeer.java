package ch.hslu.w2_Datenstrukturen;

public class MazeSolverLeer {

    private char[][] maze;

    public MazeSolverLeer(char[][] maze) {
        this.maze = maze;
    }

    public void findPath(int a, int b) { // TODO: Bessere Parameternamen?
        // TODO
    }

    private void printMaze() {
        // TODO
    }

    public static void main(String[] args) {
        char[][] mazeExample = {
                { ' ', ' ', ' ', '#', '#', '#', '#', '#', '#', '#' },
                { ' ', '#', ' ', ' ', ' ', ' ', ' ', ' ', '#', '#' },
                { ' ', '#', ' ', '#', '#', '#', ' ', '#', ' ', '#' },
                { ' ', '#', ' ', '#', ' ', ' ', ' ', '#', ' ', '#' },
                { '#', '#', ' ', '#', '#', '#', ' ', '#', ' ', '#' },
                { '#', '#', ' ', '#', ' ', ' ', ' ', ' ', ' ', ' ' },
                { ' ', ' ', ' ', '#', '#', '#', '#', '#', '#', ' ' },
                { ' ', '#', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
                { ' ', '#', '#', '#', '#', '#', '#', ' ', '#', '#' },
                { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' } };

        MazeSolverLeer solver = new MazeSolverLeer(mazeExample);

        // TODO
    }
}
