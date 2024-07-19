[TOC]

# algorithm

## 抑或

变量交换

```
int a = 1
int b = 2
int a = a^b
int b = a^b
int a = a^b
===> a==2, b==1
```

## 1. 树

- 有几个子节点就是几叉树
- 满二叉树是, 除了叶结点外所有节点都有两个子节点.
- 完全二叉树是, 从上到下, 从左到右, 如果有不满的节点, 都在右边, 优先填在左边.

### 1.0 重點
前序: 由 頂 到 底 的處理順序的解法.

後續: 由 底 到 頂 的處理順序的解法.

中序: 由 大 到 小(或反過來) 排序的解法.

### 1.1. 二叉树

- 有唯一根节点, 根节点没有父节点.
- 左右子节点
- **没有子节点**的节点为**叶子节点**

#### 二分搜索树

- 每个节点, 大于, 其左子树的所有节点.
- 右子树则相反.

## 排序

### 选择排序 O(n^2)

从头到尾, (将每个元素与其之后的每个元素比较, 选出最小值, 与其交换.)

### 插入排序 O(n^2)

从头到尾, (将每个元素与其之前的元素, 由尾到前依次比较, 小于则交换, 反之停止)

### 冒泡 O(n^2)

从头到尾, 与下个元素比较, 大则交换, 小则不变, 以此类推, 直到最后一个元素, 对每个元素进行如上操作.

### 希尔排序 O(n^1.5)

gap: 4 -> 2 -> 1 ,同 插入排序

### merge sort (NlogN)

拆分至<=2, 排序(保证左右2个数组, 都是有序数组), 合并, 排序...

- 求逆序率: 因为2区都分别为有序数组,当合并时, 从一方取出一个元素,这另一区与该元素比较的元素及其之后所有元素都大于取出的元素.

### quick sort

取第一个元素, 作为中间值(用于分类大于小于该值的元素), 依次比较所有元素,将小于中间值元素交换到数组前段(从第2位依次放置), 继续对上次刚分区的2部分递归, 直至完成排序.  
最差情况(随机取数,刚好为最大最小值时),退化为n^2.

- 求第n大的树: 直到随机元素为第n时, 递归时只需要排其中一区, 直到随机数在相对位置上为整体数组的第n大, 结束. 复杂度: n + n/2 + n/4 + ... + 1 = O(2n)

### quick sort2 (更有利于大量重复元素)

区别在于, 头尾各一个指针, 向中间遍历, 直到左指针找到第一个大于(等于)随机值的元素,直到右指针找到第一个小于(等于)随机值的元素, 交换. 直到2指针相等或左大于右.
(2区都有等于, 为了是相等的中间值不偏向一方)

### quick sort 3 ways (更有利于大量重复元素)

同, 区别在于 氛围 < = > 3个区域, 当大小与时换位, 直到与大于区域指针相等.

## 2. 队列

### 优先队列

## 3. 堆

### 最大二叉堆

所有节点的子节点必小于其本身. 用数组表示时, 左子节点序号为父节点(父节点为`1`时)的`2n`, 右节点序号为父节点的`2n+1`. 插入: shiftUp, 当新插入的节点,比其父节点大时, 与父节点交换位置,
一直交换到直到整个堆都符合最大二叉堆定义. O(nlogn)

heapify: shiftDown, 由尾到头进行. O(n)

当堆从数组[0]开始时, 父子序号关系为:

```
parent(x) = (i - 1)/2
left child(x) = 2*i + 1
right child(x) = 2*i + 2

最后一个非叶子节点的索引是(count - 1)/2
```

- 原地堆排序(最大堆), 将堆第一个元素与数组最后一位交换, 末尾即最大值; 继续对[1, N-1]shiftdown操作至最大堆, 第一位即最大值, 再次与数组`n-2`交换,直至数组所有元素有序.

## 索引堆

区别: 堆中存的是索引, 在维护一个根据此索引所在的数组,存放实际数据. 当发生变化时, 对比的是实际数据, 但交换的是实际数据的索引.

# union-find(并查集)

## 实现1

可以检查2点是否有连接.

```aidl
例: arr[i] == arr[j] 相同, 表示 i 与 j 相连.
连接2点时, 将2者其中之一的value 相同的所有index 的 value 都替换成 另一个j的value.
```

https://www.youtube.com/watch?v=YKE4Vd1ysPI

union操作: 将与合并值相等的所有元素改为另一方, O(n)

## 实现2

可以检查是否有环, 关键!因为会检查每个点出发到其他点的边, 所以的父节点

```aidl
关联时, 将其中一方指向另一方(parent). 
优化点: 如果 关联时, i现关联的node数 > j, 则 将 i 作为 j 的parent.
还可以再优化: 以i, j 的高度决定, 高的作为底的parent.
再再优化: 路径压缩. 使 爷节点 作为 子节点的 父节点.
 
判断是否关联, 则是找到双方的root, 相同则相连.
```

# 哈希

一般的`哈希`失去了顺序性.

# 6. 图

## 6.1. 无向图

邻接表 适合表示稀疏图, 每个点到其他点的边远小于点的数量, 认为是稀疏图. 连接表是2维数组, 存邻接的点

## 有向图

邻接矩阵 适合表示稠密图 邻接矩阵是2维数组, 存是否与另一点是否有直接相连, 有则置为1. 完全图: 所有的点到其他点都有连接.

## BFS(广度优先遍历) 层序优先遍历 求无权图最短路径(和具体路径),

用队列,放入第一个点, 该点未入过队的邻接点都入队, 依次检查队列中的点, 跳过已经在队中的点, 直到遍历结束.

## DFS(深度优先遍历)

递归: 每次都优先访问点到下一个点的从头到尾第一个未访问到的元素, 并记录每一个点是否访问过, 直到结束. 同样需要维护visit[], 可以维护from[], step[](源节点为0, 下一节点的step为上一节点的step[] +
1).

### 用于一般寻路(深度)

dfs, 增加数组记录"第一次到达某点"的上一点from[].

## 有向无权图

### 拓扑排序

为 每个点 统计入度,

## 带权图

### 邻接表

无权 存的是 index, 有权 存的index和权重 {to: j, weight: x}.

### 邻接矩阵

直接存 邻接的weight, 0表无邻接.

### 最小生成树 一般针对有权无向图. (用了算法思想?)

v个节点, 就应该是v-1条边. 所有边的权值也是最小.

#### lazy prim ElogE E = 边数

证明: 数学归纳法, https://www.youtube.com/watch?v=cVkuug9NdmI&t=678s

需要维护一个优先队列和一个visited[], 从一点开始, 加入临边到队列, 取出小最值, 此边即为最小生成树的其中一边, 并标记此边的另一点为visited, 每当有新的一批边加入后, 会继续选出(弹出)这些边的最小边(
需排除掉2端点已经都被visited的边), 直到变数 = k点 - 1?, 直到队列为空.

#### prim ElogV v = 访问次数?

其实只用关心, **其他点到某个点的最小值数组即可**, 记为edgeTo[] (indexMinHeap用到最小索引堆), (i对应某个点).  
随意挑选一个点开始, 更新此点邻接边的另一端点的indexMinHeap[], 找出 现存edgeTo中最小边的点, 加入到visited[],  
继续找新加入点的邻接点, 并更新edgeTo[], 取最小(记得排除掉已经visited过的邻接点), 该邻接点加入visited, 直到结束.

#### kruskal ElogE 靠 union-find 才降低了时间复杂度.

先排序边大小, 从小到大加入, 如果形成环这去掉, 继续加入, 直到边够. 用union-find 找环.

#### vyssotsky 没用, 因为没有对应好的 数据结构.

不用先排序, 逐条加入, 当形成环, 则删除最大权值边, 直到最后边.

### 最短路径

松弛操作 relaxation

## 无负权值

### dijkstra 单源最短路径 ElogV

类似prim, 差别在不断更新的edgeTo[], 为从原点到该点的最近路径, 每当有新点加入到visited, 都会计算原点经过新点所到达的邻接点的路径大小, 小于则更新.

## 有负权值

不能处理负全环.

### bellman-ford 可以判断是否有负全环(假如有n个点, 则最多只能经过n-1条边). O(EV)

- https://www.youtube.com/watch?v=FtN3BYH2Zes
- https://www.youtube.com/watch?v=bC7Wfl3k7KU&t=4s
- https://www.youtube.com/watch?v=YVwGDs2FYuw&t=461s (这个直接遍历所有边完事)

对每个点都进行 v-1 次松弛操作(进过这个点到达的临边(distTo[] + v.weight),是否有更小值距离).

```aidl
for (1 to n-1){
    for(0 to v-1){
        for(所有临边){
        
        }
    }
}
```