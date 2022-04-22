package practice.satya;

import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;

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

        System.out.println(snakeMinMoves.minimumMoves(grid));
    }

    public int minimumMoves(int[][] grid) {
        int res = -1;
        SnakePos.setGrid(grid);
        SnakePos initialPos = new SnakePos(0, 0, false, 0);
        SnakePos targetPos = new SnakePos(grid.length - 1, grid[grid.length - 1].length - 2, false);
        //initialPos.printGrid();
        boolean[][][] visited = new boolean[grid.length][grid[0].length][2];
        SnakePos next, cur;
        Queue<SnakePos> queue = new LinkedList<>();
        queue.add(initialPos);
        while (!queue.isEmpty()) {
            cur = queue.poll();
            if (cur.equals(targetPos))
                return cur.d;
            if (visited[cur.x][cur.y][cur.isVertical ? 1 : 0])
                continue;
            visited[cur.x][cur.y][cur.isVertical ? 1 : 0] = true;
            if ((next = cur.moveAntiClockWise()) != null) {
                queue.add(next);
            }
            if ((next = cur.moveClockWise()) != null) {
                queue.add(next);
            }
            if ((next = cur.moveHorizontal()) != null) {
                queue.add(next);
            }
            if ((next = cur.moveVertical()) != null) {
                queue.add(next);
            }
        }

        return res;
    }
}

class SnakePos {
    static int[][] grid;
    int d = Integer.MAX_VALUE;

    public SnakePos(int x, int y, boolean isVertical, int d) {
        this.d = d;
        this.x = x;
        this.y = y;
        this.isVertical = isVertical;
        //System.out.println(this.toString());
    }

    @Override
    public String toString() {
        return "SnakePos{" +
                "d=" + d +
                ", x=" + x +
                ", y=" + y +
                ", isVertical=" + isVertical +
                '}';
    }

    public int getD() {
        return d;
    }

    public void setD(int d) {
        this.d = d;
    }

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
                return new SnakePos(x, headY, false, d + 1);
            else
                return null;
        } else {
            int nextY = y + 1;
            if (isValidMove(x, nextY) && isValidMove(x + 1, nextY))
                return new SnakePos(x, nextY, true, d + 1);
            else
                return null;
        }
    }

    public SnakePos moveVertical() {
        if (isVertical) {
            int headX = x + 1;
            int nextX = headX + 1;
            if (isValidMove(nextX, y)) {
                return new SnakePos(headX, y, true, d + 1);
            } else
                return null;
        } else {
            int nextX = x + 1;
            if (isValidMove(nextX, y) && isValidMove(nextX, y + 1)) {
                return new SnakePos(nextX, y, false, d + 1);
            } else return null;
        }
    }

    public SnakePos moveClockWise() {
        if (isVertical)
            return null;
        int nextX = x + 1;
        if (isValidMove(nextX, y) && isValidMove(nextX, y + 1)) {
            return new SnakePos(x, y, true, d + 1);
        } else
            return null;
    }

    public SnakePos moveAntiClockWise() {
        if (!isVertical)
            return null;
        int nextY = y + 1;
        if (isValidMove(x, nextY) && isValidMove(x + 1, nextY)) {
            return new SnakePos(x, y, false, d + 1);
        } else
            return null;
    }

    private boolean isValidMove(int x, int y) {
        if (x >= 0 && x < grid.length && y >= 0 && y < grid[x].length && grid[x][y] == 0)
            return true;
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SnakePos snakePos = (SnakePos) o;
        return x == snakePos.x && y == snakePos.y && isVertical == snakePos.isVertical;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, isVertical);
    }
}
