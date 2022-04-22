package practice.satya;

public class SnakeMinMoves {
    public static void main(String[] args) {
        int[][] grid = new int[][]{
                {0, 0, 0, 0, 0, 1},
                {1, 1, 0, 0, 1, 0},
                {0, 0, 0, 0, 1, 1},
                {0, 0, 1, 0, 1, 0},
                {0, 1, 1, 0, 0, 0},
                {0, 1, 1, 0, 0, 0}};
        SnakeMinMoves snakeMinMoves = new SnakeMinMoves();

        snakeMinMoves.minimumMoves(grid);
    }

    public int minimumMoves(int[][] grid) {
        int res = 0;
        SnakePos.setGrid(grid);
        SnakePos initialPos = new SnakePos(0, 0, false);
        initialPos.printGrid();
        return res;
    }
}

class SnakePos {
    static int[][] grid;

    public static void setGrid(int[][] grid) {
        SnakePos.grid = grid;
    }

    int x, y; // (x,y) are position of tail// so head will be in (x+1,y) when isVertical is true else (x,y+1)
    boolean isVertical; // = true

    public SnakePos(int tailX, int tailY, boolean isVertical) {
        this.x = tailX;
        this.y = tailY;
        this.isVertical = isVertical;
    }

    public void printGrid() {
        int hx = x, hy = y;
        if (isVertical)
            hx++;
        else
            hy++;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (i == x && j == y) {
                    System.out.print("T" + " ");
                } else
                if (i == hx && j == hy) {
                    System.out.print("H" + " ");
                } else
                    System.out.print(grid[i][j] + " ");
            }
            System.out.println();
        }
    }
}
