<p>给你一个链表数组，每个链表都已经按升序排列。</p>

<p>请你将所有链表合并到一个升序链表中，返回合并后的链表。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>lists = [[1,4,5],[1,3,4],[2,6]]
<strong>输出：</strong>[1,1,2,3,4,4,5,6]
<strong>解释：</strong>链表数组如下：
[
  1-&gt;4-&gt;5,
  1-&gt;3-&gt;4,
  2-&gt;6
]
将它们合并到一个有序链表中得到。
1-&gt;1-&gt;2-&gt;3-&gt;4-&gt;4-&gt;5-&gt;6
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>lists = []
<strong>输出：</strong>[]
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>lists = [[]]
<strong>输出：</strong>[]
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul> 
 <li><code>k == lists.length</code></li> 
 <li><code>0 &lt;= k &lt;= 10^4</code></li> 
 <li><code>0 &lt;= lists[i].length &lt;= 500</code></li> 
 <li><code>-10^4 &lt;= lists[i][j] &lt;= 10^4</code></li> 
 <li><code>lists[i]</code> 按 <strong>升序</strong> 排列</li> 
 <li><code>lists[i].length</code> 的总和不超过 <code>10^4</code></li> 
</ul>

<details><summary><strong>Related Topics</strong></summary>链表 | 分治 | 堆（优先队列） | 归并排序</details><br>

<div>👍 3087, 👎 0<span style='float: right;'><span style='color: gray;'><a href='https://github.com/labuladong/fucking-algorithm/issues' target='_blank' style='color: lightgray;text-decoration: underline;'>bug 反馈</a> | <a href='https://labuladong.online/algo/fname.html?fname=jb插件简介' target='_blank' style='color: lightgray;text-decoration: underline;'>使用指南</a> | <a href='https://labuladong.online/algo/' target='_blank' style='color: lightgray;text-decoration: underline;'>更多配套插件</a></span></span></div>

<div id="labuladong"><hr>

**通知：为满足广大读者的需求，网站上架 [速成目录](https://labuladong.online/algo/intro/quick-learning-plan/)，如有需要可以看下，谢谢大家的支持~**



<p><strong><a href="https://labuladong.online/algo/essential-technique/divide-and-conquer/" target="_blank">⭐️labuladong 题解</a></strong></p>
<details><summary><strong>labuladong 思路</strong></summary>


<div id="labuladong_solution_zh">

## 基本思路

[✔ ✨21. 合并两个有序链表](/problems/merge-two-sorted-lists/) 的延伸，利用 [优先级队列（二叉堆）](https://labuladong.online/algo/data-structure-basic/binary-heap-basic/) 进行节点排序即可。

**详细题解**：
  - [分治算法解题套路框架](https://labuladong.online/algo/essential-technique/divide-and-conquer/)
  - [双指针技巧秒杀七道链表题目](https://labuladong.online/algo/essential-technique/linked-list-skills-summary/)
  - [【练习】优先级队列经典习题](https://labuladong.online/algo/problem-set/binary-heap/)

</div>





<div id="solution">

## 解法代码



<div class="tab-panel"><div class="tab-nav">
<button data-tab-item="cpp" class="tab-nav-button btn " data-tab-group="default" onclick="switchTab(this)">cpp🤖</button>

<button data-tab-item="python" class="tab-nav-button btn " data-tab-group="default" onclick="switchTab(this)">python🤖</button>

<button data-tab-item="java" class="tab-nav-button btn active" data-tab-group="default" onclick="switchTab(this)">java🟢</button>

<button data-tab-item="go" class="tab-nav-button btn " data-tab-group="default" onclick="switchTab(this)">go🤖</button>

<button data-tab-item="javascript" class="tab-nav-button btn " data-tab-group="default" onclick="switchTab(this)">javascript🤖</button>
</div><div class="tab-content">
<div data-tab-item="cpp" class="tab-item " data-tab-group="default"><div class="highlight">

```cpp
// 注意：cpp 代码由 chatGPT🤖 根据我的 java 代码翻译。
// 本代码的正确性已通过力扣验证，如有疑问，可以对照 java 代码查看。

// 用分治算法合并 k 个有序链表
class Solution {
public:
    ListNode* mergeKLists(vector<ListNode*>& lists) {
        if (lists.empty()) {
            return nullptr;
        }
        return mergeKLists3(lists, 0, lists.size() - 1);
    }

    // 定义：合并 lists[start..end] 为一个有序链表
    ListNode* mergeKLists3(vector<ListNode*>& lists, int start, int end) {
        if (start == end) {
            return lists[start];
        }

        int mid = start + (end - start) / 2;
        // 合并左半边 lists[start..mid] 为一个有序链表
        ListNode* left = mergeKLists3(lists, start, mid);

        // 合并右半边 lists[mid+1..end] 为一个有序链表
        ListNode* right = mergeKLists3(lists, mid + 1, end);

        // 合并左右两个有序链表
        return mergeTwoLists(left, right);
    }

    // 双指针技巧合并两个有序链表
    // https://labuladong.online/algo/essential-technique/linked-list-skills-summary/
    ListNode* mergeTwoLists(ListNode* l1, ListNode* l2) {
        ListNode dummy(-1), *p = &dummy;
        ListNode* p1 = l1, *p2 = l2;

        while (p1 != nullptr && p2 != nullptr) {
            if (p1->val > p2->val) {
                p->next = p2;
                p2 = p2->next;
            } else {
                p->next = p1;
                p1 = p1->next;
            }
            p = p->next;
        }

        if (p1 != nullptr) {
            p->next = p1;
        }

        if (p2 != nullptr) {
            p->next = p2;
        }

        return dummy.next;
    }
};
```

</div></div>

<div data-tab-item="python" class="tab-item " data-tab-group="default"><div class="highlight">

```python
# 注意：python 代码由 chatGPT🤖 根据我的 java 代码翻译。
# 本代码的正确性已通过力扣验证，如有疑问，可以对照 java 代码查看。

# 用分治算法合并 k 个有序链表
class Solution:

    def mergeKLists(self, lists: list['ListNode']) -> 'ListNode':
        if len(lists) == 0:
            return None
        return self.mergeKLists3(lists, 0, len(lists) - 1)

    # 定义：合并 lists[start..end] 为一个有序链表
    def mergeKLists3(self, lists: list['ListNode'], start: int, end: int) -> 'ListNode':
        if start == end:
            return lists[start]

        mid = start + (end - start) // 2
        # 合并左半边 lists[start..mid] 为一个有序链表
        left = self.mergeKLists3(lists, start, mid)

        # 合并右半边 lists[mid+1..end] 为一个有序链表
        right = self.mergeKLists3(lists, mid + 1, end)

        # 合并左右两个有序链表
        return self.mergeTwoLists(left, right)

    # 双指针技巧合并两个有序链表
    # https://labuladong.online/algo/essential-technique/linked-list-skills-summary/
    def mergeTwoLists(self, l1: 'ListNode', l2: 'ListNode') -> 'ListNode':
        dummy = ListNode(-1)
        p = dummy
        p1, p2 = l1, l2

        while p1 is not None and p2 is not None:
            if p1.val > p2.val:
                p.next = p2
                p2 = p2.next
            else:
                p.next = p1
                p1 = p1.next
            p = p.next

        if p1 is not None:
            p.next = p1

        if p2 is not None:
            p.next = p2

        return dummy.next
```

</div></div>

<div data-tab-item="java" class="tab-item active" data-tab-group="default"><div class="highlight">

```java
// 用分治算法合并 k 个有序链表
class Solution {

    public ListNode mergeKLists(ListNode[] lists) {
        if (lists.length == 0) {
            return null;
        }
        return mergeKLists3(lists, 0, lists.length - 1);
    }


    // 定义：合并 lists[start..end] 为一个有序链表
    ListNode mergeKLists3(ListNode[] lists, int start, int end) {
        if (start == end) {
            return lists[start];
        }

        int mid = start + (end - start) / 2;
        // 合并左半边 lists[start..mid] 为一个有序链表
        ListNode left = mergeKLists3(lists, start, mid);

        // 合并右半边 lists[mid+1..end] 为一个有序链表
        ListNode right = mergeKLists3(lists, mid + 1, end);

        // 合并左右两个有序链表
        return mergeTwoLists(left, right);
    }


    // 双指针技巧合并两个有序链表
    // https://labuladong.online/algo/essential-technique/linked-list-skills-summary/
    ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(-1), p = dummy;
        ListNode p1 = l1, p2 = l2;

        while (p1 != null && p2 != null) {
            if (p1.val > p2.val) {
                p.next = p2;
                p2 = p2.next;
            } else {
                p.next = p1;
                p1 = p1.next;
            }
            p = p.next;
        }

        if (p1 != null) {
            p.next = p1;
        }

        if (p2 != null) {
            p.next = p2;
        }

        return dummy.next;
    }
}
```

</div></div>

<div data-tab-item="go" class="tab-item " data-tab-group="default"><div class="highlight">

```go
// 注意：go 代码由 chatGPT🤖 根据我的 java 代码翻译。
// 本代码的正确性已通过力扣验证，如有疑问，可以对照 java 代码查看。

// 用分治算法合并 k 个有序链表
func mergeKLists(lists []*ListNode) *ListNode {
    if len(lists) == 0 {
        return nil
    }
    return mergeKLists3(lists, 0, len(lists)-1)
}

// 定义：合并 lists[start..end] 为一个有序链表
func mergeKLists3(lists []*ListNode, start, end int) *ListNode {
    if start == end {
        return lists[start]
    }
    mid := start + (end-start)/2
    // 合并左半边 lists[start..mid] 为一个有序链表
    left := mergeKLists3(lists, start, mid)
    // 合并右半边 lists[mid+1..end] 为一个有序链表
    right := mergeKLists3(lists, mid+1, end)
    // 合并左右两个有序链表
    return mergeTwoLists(left, right)
}

// 双指针技巧合并两个有序链表
// https://labuladong.online/algo/essential-technique/linked-list-skills-summary/
func mergeTwoLists(l1, l2 *ListNode) *ListNode {
    dummy := &ListNode{Val: -1}
    p := dummy
    p1, p2 := l1, l2

    for p1 != nil && p2 != nil {
        if p1.Val > p2.Val {
            p.Next = p2
            p2 = p2.Next
        } else {
            p.Next = p1
            p1 = p1.Next
        }
        p = p.Next
    }

    if p1 != nil {
        p.Next = p1
    }

    if p2 != nil {
        p.Next = p2
    }

    return dummy.Next
}
```

</div></div>

<div data-tab-item="javascript" class="tab-item " data-tab-group="default"><div class="highlight">

```javascript
// 注意：javascript 代码由 chatGPT🤖 根据我的 java 代码翻译。
// 本代码的正确性已通过力扣验证，如有疑问，可以对照 java 代码查看。

// 用分治算法合并 k 个有序链表

var mergeKLists = function(lists) {
    if (lists.length === 0) {
        return null;
    }
    return mergeKLists3(lists, 0, lists.length - 1);
};

// 定义：合并 lists[start..end] 为一个有序链表
function mergeKLists3(lists, start, end) {
    if (start === end) {
        return lists[start];
    }

    let mid = start + Math.floor((end - start) / 2);
    // 合并左半边 lists[start..mid] 为一个有序链表
    let left = mergeKLists3(lists, start, mid);

    // 合并右半边 lists[mid+1..end] 为一个有序链表
    let right = mergeKLists3(lists, mid + 1, end);

    // 合并左右两个有序链表
    return mergeTwoLists(left, right);
}

// 双指针技巧合并两个有序链表
// https://labuladong.online/algo/essential-technique/linked-list-skills-summary/
function mergeTwoLists(l1, l2) {
    let dummy = new ListNode(-1), p = dummy;
    let p1 = l1, p2 = l2;

    while (p1 !== null && p2 !== null) {
        if (p1.val > p2.val) {
            p.next = p2;
            p2 = p2.next;
        } else {
            p.next = p1;
            p1 = p1.next;
        }
        p = p.next;
    }

    if (p1 !== null) {
        p.next = p1;
    }

    if (p2 !== null) {
        p.next = p2;
    }

    return dummy.next;
}
```

</div></div>
</div></div>

<hr /><details open hint-container details><summary style="font-size: medium"><strong>🎃🎃 算法可视化 🎃🎃</strong></summary><div id="data_merge-k-sorted-lists"  category="leetcode" ></div><div class="resizable aspect-ratio-container" style="height: 100%;">
<div id="iframe_merge-k-sorted-lists"></div></div>
</details><hr /><br />

</div>
</details>
</div>

