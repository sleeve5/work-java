
public class linkedlist {
    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public void insert(ListNode L1, ListNode L2) {
        if (L1 == null || L2 == null)
            return;
        ListNode p = L1.next;
        L1.next = L2;
        L2.next = p;
    }

    public void removeNext(ListNode L1) {
        if (L1 == null || L1.next == null)
            return;
        ListNode p = L1.next;
        L1.next = p.next;
    }

    public void traverse(ListNode head) {
        if (head == null)
            return;
        ListNode newNode = head;
        System.out.println("List:");
        while (newNode != null) {
            System.out.println(newNode.val);
            newNode = newNode.next;
        }
    }

    public int find(ListNode head, int val) {
        if (head == null) {
            return -1;
        }
        ListNode newNode = head;
        int index = 0;
        while (newNode != null) {
            if (newNode.val == val)
                return index;
            newNode = newNode.next;
            index++;
        }
        return -1;
    }

    public void modify(ListNode head, int index, int val) {
        if (head == null)
            return;
        ListNode newNode = head;
        for (int i = 0; i < index; i++) {
            newNode = newNode.next;
        }
        newNode.val = val;
    }

    public static void main(String[] args) {
        linkedlist outer = new linkedlist();
        ListNode n0 = outer.new ListNode(0);
        ListNode n1 = outer.new ListNode(1);
        ListNode n2 = outer.new ListNode(2);
        ListNode n3 = outer.new ListNode(3);
        ListNode n4 = outer.new ListNode(4);
        ListNode head = n0;
        n0.next = n1;
        n1.next = n2;
        n2.next = n3;
        n3.next = null;

        outer.insert(n3, n4);
        outer.removeNext(n2);
        outer.modify(head, 3, 3);
        outer.traverse(head);
        System.out.println(outer.find(head, 2));
    }
}
