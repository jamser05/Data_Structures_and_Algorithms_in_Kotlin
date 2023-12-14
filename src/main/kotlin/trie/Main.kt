package trie

fun main(args: Array<String>) {
    val list = mutableListOf<String>("cee", "candy", "mandy")
    val trie = Trie()

    trie.insert("Car")
    trie.insert("Cars")
    println(trie.searchWord("Car"))
    trie.insert(list)
    println(trie.dictionaryOfWords)

}