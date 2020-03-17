public class Trie {
    
    private TrieNode root;
    
    public Trie() {
        root = new TrieNode();
    }
    
    public void insert(String word) {
        TrieNode node = root;
        for (int i = 0; i < word.length(); i++) {
            char currentChar = word.charAt(i);
            if(!node.containsKey(currentChar)) {
                node.putChar(currentChar, new TrieNode());
            }
            node = node.get(currentChar);
        }
        node.setEnd(true);
    }

    public boolean search(String word) {
        TrieNode node = searchPrefix(word);
        return node != null && node.isEnd();
    }

    public boolean startsWith(String prefix) {
        TrieNode node = searchPrefix(prefix);
        return node != null;
    }

    private TrieNode searchPrefix(String prefix) {
        TrieNode node = root;
        for (int i = 0; i < prefix.length(); i++) {
            char currentChar = prefix.charAt(i);
            if(!node.containsKey(currentChar)) {
                return null;
            }
            node = node.get(currentChar);
        }
        return node;
    }
}

class TrieNode {

    private final int ALPHABET_SIZE = 26;
    private boolean isEnd;
    private TrieNode[] children;

    public TrieNode() {
        children = new TrieNode[ALPHABET_SIZE];
    }
    public boolean containsKey(char ch) {
        return children[ch-'a'] != null;
    }
    public void putChar(char ch,TrieNode node) {
        children[ch-'a'] = node;
    }
    public TrieNode get(char ch) {
        return children[ch-'a'];
    }
    public void setEnd(boolean end) {
        isEnd = end;
    }
    public boolean isEnd() {
        return isEnd;
    }
}
