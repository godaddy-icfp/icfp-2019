package icfp2019

import com.google.common.base.CharMatcher
import mu.KotlinLogging

/*
  A class used for creating arbitrary parsers of Input
 */
class Parsers(expression: String) {
  val logger = KotlinLogging.logger {  }
  companion object {
    val logger = KotlinLogging.logger {  }
  }

  val matcher: CharMatcher
  init {
    logger.trace{"Calling... Parser() $expression"}
    this.matcher = CharMatcher.anyOf(expression)
  }

  fun parsePoint(mapEdges: String): Point {
    logger.debug{"Calling... parsePoint() $mapEdges"}
    return parseEdges(mapEdges)[0]
  }

  fun parseEdges(mapEdges: String): List<Point> {
    logger.trace{"Calling... parseEdges() $mapEdges"}
    return mapEdges.split(',')
            .map { this.matcher.trimFrom(it) }
            .windowed(2)
            .map { Point(Integer.parseInt(it[0]), Integer.parseInt(it[1])) }
  }
}