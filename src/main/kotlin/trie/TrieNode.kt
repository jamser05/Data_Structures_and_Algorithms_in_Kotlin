package trie

class TrieNode<Key>(var key: Key?, var parent: TrieNode<Key>? ) {
    var children: MutableMap<Key, TrieNode<Key>> = mutableMapOf()
    var isTerminating = false
}