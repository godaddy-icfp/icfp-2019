package icfp2019

import java.io.File
import kotlin.properties.Delegates

class Problems(path: String) {
    private var path: String by Delegates.notNull()
    val problems = mutableListOf<Problem>()

    init {
        this.path = path
        File(path).walk().forEach {
            if (it.name.endsWith(".desc")) {
                // val content = it.readText()
                val problem = Problem(ProblemId(1), Size(1, 2), Point(1, 2), arrayOf(arrayOf(Node(Point(1, 1), false, null))))
                problems.add(problem)
            }
        }
    }

//    fun listAllEntry() {
//        problems.forEach { entry ->
//            println("Key: " + entry.key + " - Value: " + entry.value)
//        }
//    }
//
//    fun getProblem(id: String): List<String> {
//        var content: String = problems.getOrDefault(id, "")
//        return content.split("#")
//    }
}
