package practice;

import java.util.ArrayList;
import java.util.Stack;
import leetcode.editor.common.ListNode;

public class stack {
    class LinkedListStack {
        private ListNode stackPeek;
        private int stkSize = 0;

        public LinkedListStack() {
            stackPeek = null;
        }

        public int size() {
            return stkSize;
        }

        public boolean isEmpty() {
            return size() == 0;
        }

        public void push(int num) {
            ListNode newNode = new ListNode(num);
            newNode.next = stackPeek;
            stackPeek = newNode;
            stkSize++;
        }

        public int pop() {
            if (isEmpty()) {
                throw new IndexOutOfBoundsException("The Stack Is Empty!");
            }
            int num = stackPeek.val;
            stackPeek = stackPeek.next;
            return num;
        }

        public int peek() {
            if (isEmpty()) {
                throw new IndexOutOfBoundsException("The Stack Is Empty!");
            }
            return stackPeek.val;
        }

        public int[] toArray() {
            int[] arr = new int[size()];
            ListNode node = stackPeek;
            for (int i = size() - 1; i >= 0; i--) {
                arr[i] = node.val;
                node = node.next;
            }
            return arr;
        }
    }

    class ArrayStack {
        private ArrayList<Integer> stack;

        public ArrayStack() {
            stack = new ArrayList<>();
        }

        public int size() {
            return stack.size();
        }

        public boolean isEmpty() {
            return stack.isEmpty();
        }

        public void push(int num) {
            stack.add(num);
        }

        public int push() {
            if (isEmpty()) {
                throw new IndexOutOfBoundsException();
            }
            return stack.remove(size() - 1);
        }

        public int peek() {
            if (isEmpty()) {
                throw new IndexOutOfBoundsException();
            }
            return stack.get(size() - 1);
        }

        public Object[] toArray() {
            return stack.toArray();
        }
    }

    public static void main(String[] args) {
        Stack<Integer> stk = new Stack<>();
        stk.push(0);
        stk.push(1);
        stk.push(2);
        stk.push(3);
        int peek = stk.peek();
        int pop = stk.pop();
        int size = stk.size();
        boolean isEmpty = stk.isEmpty();
    }
}
