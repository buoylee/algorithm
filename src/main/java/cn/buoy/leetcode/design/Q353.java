package cn.buoy.leetcode.design;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

public class Q353 {
    /**
     * 簡單, 視頻, 代碼
     * https://www.youtube.com/watch?v=NPIkFa2GbME
     * 思路: 每新增 head(每走一步),
     * 1. 檢查邊框越界;
     * 2. 是否與 body 碰撞;
     * 3. 從 food[0] 開始吃, 吃到 food, foodIndex++; 沒吃到, tail poll.
     */
    public class SnakeGame {
        private int width, height;
        private int[][] food;
        private int foodIndex;
        private int score;
        private int[] head;
        private Queue<int[]> snake;
        private int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        public SnakeGame(int width, int height, int[][] food) {
            this.width = width;
            this.height = height;
            this.food = food; // 所有 food 座標
            this.foodIndex = 0; // food 從 arr 第一個開始吃
            this.score = 0; // 題目沒要求?
            this.head = new int[]{0, 0}; // head 初始化座標
            this.snake = new LinkedList<>();
            this.snake.offer(new int[]{0, 0});
        }

        public int move(String direction) {
            // 獲得 "UDLR" 對 "實際座標移動" 的映射
            int dirIndex = "UDLR".indexOf(direction);
            int[] dir = directions[dirIndex];
            head[0] += dir[0];
            head[1] += dir[1];

            // 遊戲邊框邊界限制
            if (head[0] < 0 || head[0] >= height || head[1] < 0 || head[1] >= width)
                return -1;
            // 檢查新的 head 是否與 "現存的 snake" 碰撞.
            for (int[] s : snake)
                if (s[0] == head[0] && s[1] == head[1])
                    return -1;

            // 還有 food 存在 && "head 座標" == "當前 food 座標"
            // 吃到 food, tail 不用減少
            if (foodIndex < food.length && head[0] == food[foodIndex][0] && head[1] == food[foodIndex][1]) {
                score++;
                foodIndex++;
            } else // head 沒有和 food 相遇, remove tail
                snake.poll();
            // 記得把 new head offer
            snake.offer(new int[]{head[0], head[1]});
            return score;
        }
    }

    /**
     * 直接看代码, 容易看.
     */
    class SnakeGame2 {
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
        public SnakeGame2(int width, int height, int[][] food) {
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
}

