package icfp2019

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test


class BitMaskTests {
    @Test
    fun testBitMask() {
        val cellValue : Short = 0

        val cellValue2 = Cell.setFlag(cellValue, Cell.WALL)
        Assertions.assertTrue(Cell.hasFlag(cellValue2, Cell.WALL))

        val cellValue3 = Cell.unsetFlag(cellValue2, Cell.WALL)
        Assertions.assertFalse(Cell.hasFlag(cellValue3, Cell.WALL))

        val cellValue4 = Cell.setFlags(cellValue3, Cell.WALL, Cell.BOOST_DRILL, Cell.MYSTERY, Cell.WRAPPED)
        Assertions.assertTrue(Cell.hasFlag(cellValue4, Cell.WALL))
        Assertions.assertTrue(Cell.hasFlag(cellValue4, Cell.WRAPPED))
        Assertions.assertTrue(Cell.hasFlag(cellValue4, Cell.BOOST_DRILL))
        Assertions.assertTrue(Cell.hasFlag(cellValue4, Cell.MYSTERY))
        Assertions.assertFalse(Cell.hasFlag(cellValue4, Cell.BOOST_EXT))
        Assertions.assertFalse(Cell.hasFlag(cellValue4, Cell.BOOST_TELEPORT))
        Assertions.assertFalse(Cell.hasFlag(cellValue4, Cell.BOOST_FAST))
        Assertions.assertFalse(Cell.hasFlag(cellValue4, Cell.BOOST_CLONE))
    }
}