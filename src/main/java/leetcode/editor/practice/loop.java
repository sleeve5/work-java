
public class loop {
    String nestedForLoop(int n) {
        StringBuilder res = new StringBuilder();
        // 循环 i = 1, 2, ..., n-1, n
        for (int i = 1; i <= n; i++) {
            // 循环 j = 1, 2, ..., n-1, n
            for (int j = 1; j <= n; j++) {
                res.append("(" + i + ", " + j + "), ");
            }
        }
        return res.toString();
    }

    int recursion(int n) {
        if (n == 1) {
            return 1;
        }
        int res = recursion(n - 1);
        return n + res;
    }

    int tailRecursion(int n, int res) {
        if (n == 0) {
            return res;
        }
        return tailRecursion(n - 1, res + n);
    }

    public static void main(String[] args) {
        loop obj = new loop();
        // System.out.println(obj.nestedForLoop(5));
        System.out.println(obj.tailRecursion(5, 0));
    }
}
