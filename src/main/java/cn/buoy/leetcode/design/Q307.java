package cn.buoy.leetcode.design;

public class Q307 {
    /**
     * 簡單, 下邊2個視頻都不錯, 快速視頻, 註釋.
     * https://www.youtube.com/watch?v=7rDxwVHg_zU Fenwick Tree（Binary Indexed Tree）
     * https://www.youtube.com/watch?v=8XSfWhZFSBs SegmentTree
     * 思路: SegmentTree, 後續遍歷.
     * build: 线段树. 從 root(整個區間 和 整個區間的 sum) 開始2分"前後區間"(left/right child) 和 各自的 sum, 繼續遞歸到 "每個區間" 只有 "1個數".
     * update: 和 build 一樣, 用後序遍歷, 從 root 2分下去, 直到找到單個元素的區間[a, a], 然後原路返回時, 更新各級 root 的 sum.
     * sumRange: 和 build 一樣.
     */
    public class NumArray {

        class SegmentTreeNode {
            //[start, end] for index
            int start, end;
            //sum of [start, end]
            int sum;
            SegmentTreeNode left, right;

            public SegmentTreeNode(int start, int end) {
                this.start = start; // index 的 start
                this.end = end; // index 的 end
                this.sum = 0; // start~end 的 sum
                this.left = null; // node 的 left child
                this.right = null; // node 的 right child
            }
        }

        SegmentTreeNode root = null;

        public int sumRange(int i, int j) {
            return sumRange(root, i, j);
        }

        // 查找 指定範圍 sum
        public int sumRange(SegmentTreeNode root, int start, int end) {
            // 剩餘個元素的區間
            if (root.end == end && root.start == start) {
                return root.sum;
            } else {
                int mid = root.start + (root.end - root.start) / 2;
                // 關鍵: 直接 在所屬範圍內查找即可.
                // 因爲構建的時候, mid 放在前半部.
                if (end <= mid) {
                    return sumRange(root.left, start, end);
                } else if (start >= mid + 1) {
                    return sumRange(root.right, start, end);
                } else // 橫跨前後2個區間, 就分開查找, 再 sum.
                    return sumRange(root.left, start, mid) + sumRange(root.right, mid + 1, end);
            }
        }

        public NumArray(int[] nums) {
            root = buildTree(nums, 0, nums.length - 1);
        }

        // 構建 線段樹
        private SegmentTreeNode buildTree(int[] nums, int start, int end) {
            if (start > end)
                return null;
            SegmentTreeNode root = new SegmentTreeNode(start, end);
            // 區間只剩1个元素, 放进去
            if (start == end) {
                root.sum = nums[start];
            } else {
                // 2分
                int mid = start + (end - start) / 2;
                // 細節: left sum 構建的時候, mid 歸屬於 前半部, 查找時也記得同樣的範圍定義.
                root.left = buildTree(nums, start, mid);
                // right sum
                root.right = buildTree(nums, mid + 1, end);
                // root 的 sum
                root.sum = root.left.sum + root.right.sum;
            }
            return root;
        }

        void update(int i, int val) {
            update(root, i, val);
        }

        // 後續遍歷, 和 buildTree 一樣, 也是從 "頂 root" 開始2分. 直到找到 指定 index, 更新完後, 一路更新 sum 上去.
        void update(SegmentTreeNode root, int index, int val) {
            // 只有1个元素, 或找到某个指定 index
            if (root.start == root.end) {
                root.sum = val;
            } else {
                int mid = root.start + (root.end - root.start) / 2;
                // 符合上邊 buildTree 的2分法, index <= mid 在左边
                if (index <= mid)
                    update(root.left, index, val);
                else
                    update(root.right, index, val);
                // 處理完 left/right, 最後更新 "本 root" sum
                root.sum = root.left.sum + root.right.sum;
            }
        }
    }

    // https://www.cnblogs.com/grandyang/p/4985506.html
// segment tree 講解
// https://www.jianshu.com/p/91f2c503e62f
// 简单了解一下 Segment Tree 和 Fenwick Tree（Binary Indexed Tree）
// https://blog.csdn.net/HJF_HUANGJINFU/article/details/77834729
// segment tree
// public class NumArray {
//     int[] tree;
//     int n;

//     public NumArray(int[] nums) {
//         n = nums.length;
//         tree = new int[n << 1];
//         buildTree(nums);
//     }

//     private void buildTree(int[] nums) {
//         for (int i = n; i < n << 1; i++) {
//             tree[i] = nums[i - n];
//         }

//         for (int i = n - 1; i > 0; i--) {
//             tree[i] = tree[i << 1] + tree[i << 1 | 1];
//         }
//     }

//     void update(int i, int val) {
//         for (tree[i += n] = val; i > 0; i >>= 1) {
//             tree[i >> 1] = tree[i] + tree[i ^ 1];
//         }
//     }

//     public int sumRange(int i, int j) {
//         int ret = 0;
//         for (i += n, j += n; i <= j; i >>= 1, j >>= 1) {
//             if ((i & 1) == 1) ret += tree[i++];
//             if ((j & 1) == 0) ret += tree[j--];
//         }
//         return ret;
//     }
// }

// BIT
//    public class NumArray {
//        /**
//         * Binary Indexed Trees (BIT or Fenwick tree):
//         * https://www.topcoder.com/community/data-science/data-science-
//         * tutorials/binary-indexed-trees/
//         *
//         * Example: given an array a[0]...a[7], we use a array BIT[9] to
//         * represent a tree, where index [2] is the parent of [1] and [3], [6]
//         * is the parent of [5] and [7], [4] is the parent of [2] and [6], and
//         * [8] is the parent of [4]. I.e.,
//         *
//         * BIT[] as a binary tree:
//         *            ______________*
//         *            ______*
//         *            __*     __*
//         *            *   *   *   *
//         * indices: 0 1 2 3 4 5 6 7 8
//         *
//         * BIT[i] = ([i] is a left child) ? the partial sum from its left most
//         * descendant to itself : the partial sum from its parent (exclusive) to
//         * itself. (check the range of "__").
//         *
//         * Eg. BIT[1]=a[0], BIT[2]=a[1]+BIT[1]=a[1]+a[0], BIT[3]=a[2],
//         * BIT[4]=a[3]+BIT[3]+BIT[2]=a[3]+a[2]+a[1]+a[0],
//         * BIT[6]=a[5]+BIT[5]=a[5]+a[4],
//         * BIT[8]=a[7]+BIT[7]+BIT[6]+BIT[4]=a[7]+a[6]+...+a[0], ...
//         *
//         * Thus, to update a[1]=BIT[2], we shall update BIT[2], BIT[4], BIT[8],
//         * i.e., for current [i], the next update [j] is j=i+(i&-i) //double the
//         * last 1-bit from [i].
//         *
//         * Similarly, to get the partial sum up to a[6]=BIT[7], we shall get the
//         * sum of BIT[7], BIT[6], BIT[4], i.e., for current [i], the next
//         * summand [j] is j=i-(i&-i) // delete the last 1-bit from [i].
//         *
//         * To obtain the original value of a[7] (corresponding to index [8] of
//         * BIT), we have to subtract BIT[7], BIT[6], BIT[4] from BIT[8], i.e.,
//         * starting from [idx-1], for current [i], the next subtrahend [j] is
//         * j=i-(i&-i), up to j==idx-(idx&-idx) exclusive. (However, a quicker
//         * way but using extra space is to store the original array.)
//         */
//
//        int[] nums;
//        int[] BIT;
//        int n;
//
//        public NumArray(int[] nums) {
//            this.nums = nums;
//
//            n = nums.length;
//            // start from 1~n
//            BIT = new int[n + 1];
//            for (int i = 0; i < n; i++)
//                init(i, nums[i]);
//        }
//
//        public void init(int i, int val) {
//            // change to 1-index
//            i++;
//            while (i <= n) {
//                BIT[i] += val;
//                i += (i & -i);
//            }
//        }
//
//        void update(int i, int val) {
//            int diff = val - nums[i];
//            nums[i] = val;
//            init(i, diff);
//        }
//
//        public int getSum(int i) {
//            int sum = 0;
//            i++;
//            while (i > 0) {
//                sum += BIT[i];
//                i -= (i & -i);
//            }
//            return sum;
//        }
//
//        public int sumRange(int i, int j) {
//            return getSum(j) - getSum(i - 1);
//        }
//    }


// Your NumArray object will be instantiated and called as such:
// NumArray numArray = new NumArray(nums);
// numArray.sumRange(0, 1);
// numArray.update(1, 10);
// numArray.sumRange(1, 2);

}


