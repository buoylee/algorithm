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

## 树

- 有几个子节点就是几叉树

### 二叉树

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


# 队列

## 优先队列

# 堆

## 最大二叉堆

所有节点的子节点必小于其本身. 
用数组表示时, 左子节点序号为父节点(父节点为`1`时)的`2n`, 右节点序号为父节点的`2n+1`.
插入: shiftUp, 当新插入的节点,比其父节点大时, 与父节点交换位置, 一直交换到直到整个堆都符合最大二叉堆定义. O(nlogn)

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

union操作: 将与合并值相等的所有元素改为另一方, O(n) 

# 哈希

一般的`哈希`失去了顺序性.



n = 12

11 push 11 true
7 push 7 true

7 push 7 true
10 push 10 true
6 push 6 true

10 push 10 true
6 push 6 true
6 push 6 true -
2 push 2 true

6 push 6 true
6 push 6 true -
2 push 2 true
9 push 9 true
5 push 5 true

2 push 2 true
9 push 9 true
5 push 5 true
5 push 5 true -
1 push 1 true

9 push 9 true
5 push 5 true
5 push 5 true -
1 push 1 true
1 push 1 true -

5 push 5 true
5 push 5 true -
1 push 1 true
1 push 1 true -
8 push 8 true
4 push 4 true

5 push 5 true -
1 push 1 true
1 push 1 true -
8 push 8 true
4 push 4 true
4 push 4 true - 





