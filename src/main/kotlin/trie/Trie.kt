package trie

class Trie {
    private val root = TrieNode<Char>(key = null, parent = null)
    val dictionaryOfWords = mutableListOf<String>()


    fun insert(word: String) {
        var current = root
        word.forEach {
            letter ->
            if(current.children[letter] == null) {
                current.children[letter] = current
                current = current.children[letter]!!
            }
        }
        dictionaryOfWords.add(word)
        current.isTerminating = true
    }

    fun searchWord(word: String) : Boolean {
        var current = root

        word.forEach {
            letter ->
            if(current.children[letter] != null) {
                current = current.children[letter] ?: return false
            }
        }

        return current.isTerminating
    }

    fun prefixMatching(prefix: String): Boolean {
        var current = root

        prefix.forEach {
            letter ->

            if(current.children[letter] != null) {
                current = current.children[letter] ?: return false
            }
        }
        return true
    }


}