import java.util.*;

class TrieNode {
    Map<Character, TrieNode> childNodes = new HashMap<>();
    int count;
}

class Trie {
    TrieNode rootNode;

    Trie() {
        rootNode = new TrieNode();
    }

    int insert(char[] word) {
        TrieNode thisNode = this.rootNode;
        for (int i = 0; word[i] != '\0'; i++) {
            thisNode = thisNode.childNodes.computeIfAbsent(word[i], c -> new TrieNode());
        }
        return ++thisNode.count;
    }

    int search(char[] word) {
        return search(this.rootNode, word, 0);
    }

    private int search(TrieNode node, char[] word, int index) {
        if (word[index] == '\0') {
            return node.count;
        }

        int result = 0;

        if (word[index] == '?') {
            for (Map.Entry<Character, TrieNode> entry : node.childNodes.entrySet()) {
                result += search(entry.getValue(), word, index + 1);
            }
        } else {
            TrieNode childNode = node.childNodes.get(word[index]);
            if (childNode != null) {
                result += search(childNode, word, index + 1);
            }
        }

        return result;
    }

    int delete(char[] word) {
        int[] removed = new int[1];
        removeWithWildcard(this.rootNode, word, 0, removed);
        return removed[0];
    }

    private boolean removeWithWildcard(TrieNode node, char[] word, int index, int[] removed) {
        if (word[index] == '\0') {
            if (node.count > 0) {
                removed[0] += node.count;
                node.count = 0;
                return node.childNodes.isEmpty();
            }
            return false;
        }

        boolean canDeleteNode = false;

        if (word[index] == '?') {
            List<Character> keysToRemove = new ArrayList<>();

            for (Map.Entry<Character, TrieNode> entry : node.childNodes.entrySet()) {
                char ch = entry.getKey();
                TrieNode childNode = entry.getValue();

                if (removeWithWildcard(childNode, word, index + 1, removed)) {
                    keysToRemove.add(ch);
                }
            }

            for (char ch : keysToRemove) {
                node.childNodes.remove(ch);
            }

            canDeleteNode = node.childNodes.isEmpty() && node.count == 0;
        } else {
            TrieNode childNode = node.childNodes.get(word[index]);
            if (childNode != null) {
                if (removeWithWildcard(childNode, word, index + 1, removed)) {
                    node.childNodes.remove(word[index]);
                    canDeleteNode = node.childNodes.isEmpty() && node.count == 0;
                }
            }
        }

        return canDeleteNode;
    }
}

class UserSolution {
    private Trie trie;

    void init() {
        trie = new Trie();
    }

    int add(char str[]) {
        return trie.insert(str);
    }

    int remove(char str[]) {
        return trie.delete(str);
    }

    int search(char str[]) {
        return trie.search(str);
    }
}