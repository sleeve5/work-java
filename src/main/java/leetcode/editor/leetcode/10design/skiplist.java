import java.util.Random;

class skiplist {
    static class Node {
        Integer value;
        Node[] next;

        public Node(Integer value, int size) {
            this.value = value;
            this.next = new Node[size];
        }

        public String toString() {
            return String.valueOf(value);
        }
    }

    private static int DEFAULT_MAX_LEVEL = 32;

    private static double DEFAULT_P_FACTOR = 0.25;

    Node head = new Node(null, DEFAULT_MAX_LEVEL);
    int currentLevel = 1;

    public skiplist() {
    }

    public boolean search(int target) {
        Node searchNode = head;
        for (int i = currentLevel - 1; i >= 0; i--) {
            searchNode = findClosest(searchNode, i, target);
            if (searchNode.next[i] != null && searchNode.next[i].value == target) {
                return true;
            }
        }
        return false;
    }

    public void add(int num) {
        int level = randomLevel();
        Node updateNode = head;
        Node newNode = new Node(num, level);

        for (int i = currentLevel - 1; i >= 0; i--) {
            updateNode = findClosest(updateNode, i, num);
            if (i < level) {
                Node temp = updateNode.next[i];
                updateNode.next[i] = newNode;
                newNode.next[i] = temp;
            }
        }

        if (level > currentLevel) {
            for (int i = currentLevel; i < level; i++) {
                head.next[i] = newNode;
            }
            currentLevel = level;
        }
    }

    public boolean erase(int num) {
        boolean flag = false;
        Node searchNode = head;
        for (int i = currentLevel - 1; i >= 0; i--) {
            searchNode = findClosest(searchNode, i, num);
            if (searchNode.next[i] != null && searchNode.next[i].value == num) {
                searchNode.next[i] = searchNode.next[i].next[i];
                flag = true;
            }
        }
        return flag;
    }

    private Node findClosest(Node node, int levelIndex, int value) {
        while ((node.next[levelIndex]) != null && value > node.next[levelIndex].value) {
            node = node.next[levelIndex];
        }
        return node;
    }

    private static int randomLevel() {
        int level = 1;
        while (Math.random() < DEFAULT_P_FACTOR && level < DEFAULT_MAX_LEVEL) {
            level++;
        }
        return level;
    }

    // ================== 测试 main ==================
    public static void main(String[] args) {
        skiplist list = new skiplist();

        // 插入数据
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);

        // 查找测试
        System.out.println("查找 3：" + list.search(3)); // true
        System.out.println("查找 7：" + list.search(7)); // false

        // 删除测试
        System.out.println("删除 3：" + list.erase(3)); // true
        System.out.println("再次查找 3：" + list.search(3));// false

        System.out.println("删除 6：" + list.erase(6)); // true
        System.out.println("查找 6：" + list.search(6)); // false
    }
}