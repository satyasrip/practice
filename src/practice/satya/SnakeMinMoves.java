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
        SnakePos next;
        if((next=initialPos.moveAntiClockWise())!=null)
            next.printGrid();
        if((next=initialPos.moveClockWise())!=null)
            next.printGrid();
        if((next=initialPos.moveHorizontal())!=null)
            next.printGrid();
        if((next=initialPos.moveVertical())!=null)
            next.printGrid();

        return res;
    }
}

class SnakePos {
    static int[][] grid;

    public static void setGrid(int[][] grid) {
        SnakePos.grid = grid;
    }

    int x, y; // (x,y) are position of tail// so head will be in (x+1,y) when isVertical is true else (x,y+1)
    boolean isVertical; //

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
                } else if (i == hx && j == hy) {
                    System.out.print("H" + " ");
                } else
                    System.out.print(grid[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("-----------");
    }

    public SnakePos moveHorizontal() {
        if (!isVertical) {
            int headY = y + 1;
            int nextY = headY + 1;
            if (isValidMove(x, nextY))
                return new SnakePos(x, headY, false);
            else
                return null;
        } else {
            int nextY = y + 1;
            if (isValidMove(x, nextY) && isValidMove(x+1, nextY))
                return new SnakePos(x, nextY, true);
            else
                return null;
        }
    }

    public SnakePos moveVertical() {
        if (isVertical) {
            int headX = x + 1;
            int nextX = headX + 1;
            if (isValidMove(nextX, y)) {
                return new SnakePos(headX, y, true);
            } else
                return null;
        } else {
            int nextX = x + 1;
            if (isValidMove(nextX, y) && isValidMove(nextX, y + 1)) {
                return new SnakePos(nextX, y, false);
            } else return null;
        }
    }

    public SnakePos moveClockWise() {
        if (isVertical)
            return null;
        int nextX = x + 1;
        if (isValidMove(nextX, y) && isValidMove(nextX, y + 1)) {
            return new SnakePos(x, y, true);
        } else
            return null;
    }

    public SnakePos moveAntiClockWise() {
        if (!isVertical)
            return null;
        int nextY = y + 1;
        if (isValidMove(x, nextY) && isValidMove(x + 1, nextY)) {
            return new SnakePos(x, y, false);
        } else
            return null;
    }

    private boolean isValidMove(int x, int y) {
        if (x >= 0 && x < grid.length && y >= 0 && y < grid[x].length && grid[x][y] == 0)
            return true;
        return false;
    }
}
