package icfp2019

import java.io.File
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths

fun main() {
  val workingDir: Path = Paths.get("")

  val solutions = mutableListOf<Solution>()
  readZipFile(File("problems.zip"))
      .filter { it.line.isNotEmpty() }
      .forEach {
        val problem = parseDesc(it)
        val solution = solve(problem)
        encodeSolution(solution, workingDir)
      }

  writeZip(workingDir, solutions)
}

fun writeZip(workingDir: Path, solutions: MutableList<Solution>) {
  TODO("not implemented")
}

fun readZipFile(file: File): List<ProblemDescription> {
  TODO("not implemented")
}

fun parseBoosters(boosters: String): List<Boosters> {
  return listOf()
}


/*
A solution for a task
  prob-NNN.desc
is a sequence of actions encoded as a single-line text file named
  prob-NNN.sol
for the corresponding numberNNN.
The actions are encoded as follows:
  action ::=
      W(move up)
      | S(move down)
      | A(move left)
      | D(move right)
      | Z(do nothing)
      | E(turn manipulators 90°clockwise)
      | Q(turn manipulators 90°counterclockwise)
      | B(dx,dy)(attach a new manipulator with relative coordinates(dx,dy))
      | F(attach fast wheels)
      | L(start using a drill)

  solution ::= rep(action)

  A solution isvalid, if it does not force the worker-wrapper to go through the walls and obstacles
  (unless it uses a drill), respects the rules of using boosters, and, upon finishing,
  leaves all reachablesquares of the map wrapped.
 */

enum class Actions {
  W, S, A, D, Z, E, Q, B, F, L
}

data class Solution(val problemId: ProblemId, val actions: List<Actions>)

fun solve(problem: Problem): Solution {
  return Solution(problem.problemId, listOf())
}

fun encodeSolution(solution: Solution, directory: Path): File {
  val file = Files.createFile(directory.resolve("prob-${solution.problemId.id}.sol"))
  // TODO
  return file.toFile()
}
