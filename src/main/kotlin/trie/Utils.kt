package trie

fun Trie.insert(list: MutableList<String>)  {
    list.forEach {
        this.insert(it)
    }
}
fun Trie.searchWord(list: MutableList<String>)  {

}