public class Trie {
    Node root;

    /** Initialize your data structure here. */
    public Trie() {
        root = new Node('.');
    }

    /** Inserts a word into the trie. */
    public void insert(String word) {
        Node cur = root;
        int n = word.length();
        for (int i = 0;i < n - 1;i ++) {
            Node next = cur.next[word.charAt(i)];
            if (next == null) {
                next = new Node(word.charAt(i));
                cur.next[word.charAt(i)] = next;
            }
            cur = next;
        }
        if (n < 1) return;
        Node next = cur.next[word.charAt(n - 1)];
        if (next == null) {
            next = new Node(word.charAt(n - 1));
            cur.next[word.charAt(n - 1)] = next;
        }
        next.end = true;
    }

    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        Node cur = root;
        int n = word.length();
        for (int i = 0;i < n;i ++) {
            Node next = cur.next[word.charAt(i)];
            if (next == null) return false;
            cur = next;
        }
        return cur.end;
    }

    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        Node cur = root;
        int n = prefix.length();
        for (int i = 0;i < n;i ++) {
            Node next = cur.next[prefix.charAt(i)];
            if (next == null) return false;
            cur = next;
        }
        return true;
    }

    static class Node {
        char c;
        boolean end;
        Node[] next;

        public Node(char c) {
            this.c = c;
            this.next = new Node[128];
        }
    }
}
