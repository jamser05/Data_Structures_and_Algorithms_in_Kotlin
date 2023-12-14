import linkedlist.LinkedList

fun main (args: Array<String>) {
   var list1 = LinkedList<Int>()
   var list2 = LinkedList<Int>()


   list1.add(3)
   list1.add(2)
   list1.add(1)
   list1.add(4)
   list1.add(5)

   list2.add(4)
   list2.add(5)
   list2.add(6)
   list2.add(7)

   println("Zipper")
   println(list1)
   println(list2)
   list1.zipperLinkedList(list1.head, list2.head)
   println(list1)
}