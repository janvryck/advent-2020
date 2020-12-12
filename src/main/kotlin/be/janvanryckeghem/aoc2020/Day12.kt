package be.janvanryckeghem.aoc2020

import kotlin.math.abs

class Day12(inputFile: String = "d12") : Day(inputFile) {
    override val DAY: String = "DAY 12"
    override val parseInput: (String) -> List<Instruction> = { it.lines().map(::toInstruction) }

    override fun solvePart1(input: String): Number {
        val boat = Boat()
        parseInput(input)
            .forEach { boat.move(it) }
        return boat.manhattanDistance()
    }

    override fun solvePart2(input: String): Number {
        val boat = Boat(Point(10, 1))
        val useWaypoints = true
        parseInput(input)
            .forEach { boat.move(it, useWaypoints) }
        return boat.manhattanDistance()
    }

    data class Boat(var direction: Point = Heading.EAST.point) {
        var position: Point = Point()

        fun move(instruction: Instruction, waypoint: Boolean = false) {
            when (instruction.action) {
                Action.FORWARD -> position += direction * instruction.value
                Action.NORTH ->
                    if (waypoint) direction += Heading.NORTH * instruction.value
                    else position += Heading.NORTH * instruction.value
                Action.EAST ->
                    if (waypoint) direction += Heading.EAST * instruction.value
                    else position += Heading.EAST * instruction.value
                Action.SOUTH ->
                    if (waypoint) direction += Heading.SOUTH * instruction.value
                    else position += Heading.SOUTH * instruction.value
                Action.WEST ->
                    if (waypoint) direction += Heading.WEST * instruction.value
                    else position += Heading.WEST * instruction.value
                Action.LEFT -> direction = when (instruction.value) {
                    90 -> direction.turnLeft()
                    180 -> direction.turnAround()
                    270 -> direction.turnRight()
                    else -> throw IllegalStateException("Could not turn left by ${instruction.value}°")
                }
                Action.RIGHT -> direction = when (instruction.value) {
                    90 -> direction.turnRight()
                    180 -> direction.turnAround()
                    270 -> direction.turnLeft()
                    else -> throw IllegalStateException("Could not turn right by ${instruction.value}°")
                }
            }
        }

        fun manhattanDistance(): Int {
            return abs(position.x) + abs(position.y)
        }
    }

    data class Point(val x: Int = 0, val y: Int = 0) {
        operator fun plus(p: Point) = Point(x + p.x, y + p.y)
        operator fun times(i: Int) = Point(x * i, y * i)

        fun turnLeft(): Point {
            return Point(-y, x)
        }

        fun turnRight(): Point {
            return Point(y, -x)
        }

        fun turnAround(): Point {
            return Point(-x, -y)
        }
    }

    enum class Heading(val point: Point) {
        NORTH(Point(0, 1)),
        EAST(Point(1, 0)),
        SOUTH(Point(0, -1)),
        WEST(Point(-1, 0));

        operator fun times(i: Int) = point * i
    }

    data class Instruction(val action: Action, val value: Int)
    enum class Action {
        NORTH,
        SOUTH,
        EAST,
        WEST,
        LEFT,
        RIGHT,
        FORWARD
    }

    companion object {
        private fun toInstruction(input: String): Instruction {
            return Instruction(toAction(input[0]), input.drop(1).toInt());
        }

        private fun toAction(char: Char): Action {
            return when (char) {
                'N' -> Action.NORTH
                'S' -> Action.SOUTH
                'E' -> Action.EAST
                'W' -> Action.WEST
                'L' -> Action.LEFT
                'R' -> Action.RIGHT
                'F' -> Action.FORWARD
                else -> throw IllegalStateException("Could not parse action: $char")
            }
        }
    }
}