package icfp2019

import com.google.common.base.CharMatcher

class Parsers(expression: String) {
  val matcher: CharMatcher
  init {
      matcher = CharMatcher.anyOf(expression)
  }

  fun parsePoint(mapEdges: String): Point {
    return parseEdges(mapEdges)[0]
  }

  fun parseEdges(mapEdges: String): List<Point> {
    return mapEdges.split(',')
            .map { icfp2019.matcher.trimFrom(it) }
            .windowed(2)
            .map { Point(Integer.parseInt(it[0]), Integer.parseInt(it[1])) }
  }
}