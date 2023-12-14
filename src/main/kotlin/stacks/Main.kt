package stacks

fun main(args: Array<String>) {
    val stack = StackImpl<Int>().apply {
        (1..4).forEach {
            push(it)
        }
    }
    print(stack)
    val poppedElement = stack.pop()
    if (poppedElement != null) {
        println("Popped: $poppedElement")
    }
    print(stack)
}