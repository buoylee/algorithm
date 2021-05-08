package cn.buoy.leetcode.design;

import java.util.Deque;
import java.util.LinkedList;

public class Q353 {
    /**
     * 直接看代码, 容易看.
     */

}

class SnakeGame {
    class point {
        int r, c;

        public point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    Deque<point> snake;
    int row, col, r, c, k;
    int[][] food;

    /**
     * Initialize your data structure here.
     *
     * @param width  - screen width
     * @param height - screen height
     * @param food   - A list of food positions
     *               E.g food = [[1,1], [1,0]] means the first food is positioned at [1,1], the second is at [1,0].
     */
    public SnakeGame(int width, int height, int[][] food) {
        row = height;
        col = width;
        this.food = food;
        snake = new LinkedList<>();
        snake.offerFirst(new point(0, 0));
        //吃了几个
        k = 0;
    }

    /**
     * Moves the snake.
     *
     * @param direction - 'U' = Up, 'L' = Left, 'R' = Right, 'D' = Down
     * @return The game's score after the move. Return -1 if game over.
     * Game over when snake crosses the screen boundary or bites its body.
     */
    public int move(String direction) {
        //看头, 复制原头 给新头
        point head = snake.peekFirst();
        point newHead = new point(head.r, head.c);
        //先弹出, 如果有吃到, 再加回来尾部
        point tail = snake.pollLast();
        //新头更新到位置
        if (direction.equals("U")) {
            newHead.r--;
        } else if (direction.equals("L")) {
            newHead.c--;
        } else if (direction.equals("R")) {
            newHead.c++;
        } else if (direction.equals("D")) {
            newHead.r++;
        }

        //判断新头 合法性
        //越界
        if (newHead.r < 0 || newHead.r >= row || newHead.c < 0 || newHead.c >= col)
            return -1;
        //与身体接触, 死亡
        if (isDead(newHead)) return -1;

        //合法, 插入queue
        snake.offerFirst(newHead);
        //按food[k][]顺序出, 吃到则k++
        if (k < food.length && food[k][0] == newHead.r && food[k][1] == newHead.c) {
            k++;
            //吃到, tail要保留
            snake.offerLast(tail);
        }

        return k;
    }

    //与身体接触, 死亡
    public boolean isDead(point newHead) {
        for (point rc : snake) {
            if (rc.r == newHead.r && rc.c == newHead.c)
                return true;
        }
        return false;
    }
}