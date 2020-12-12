package be.janvanryckeghem.aoc2020

import kotlin.math.abs

class Day12(inputFile:String = "d12"): Day(inputFile) {
    override val DAY: String = "DAY 12"
    override val parseInput: (String) -> List<Instruction> = { it.lines().map(::toInstruction)}

    override fun solvePart1(input: String): Number {
        val boat = Boat()
        parseInput(input)
            .forEach { boat.move(it) }
        return boat.manhattanDistance()
    }

    override fun solvePart2(input: String): Number {
        val boat = Boat()
        parseInput(input)
            .forEach { boat.moveWithWaypoints(it) }
        return boat.manhattanDistance()
    }

    data class Boat(var direction: Direction = Direction.EAST, var position: Point = Point()) {
        var waypoint = Point(10, 1)

        fun move(instruction: Instruction) {
            when(instruction.action) {
                Action.FORWARD -> position += direction * instruction.value
                Action.NORTH -> position += Direction.NORTH * instruction.value
                Action.EAST -> position += Direction.EAST * instruction.value
                Action.SOUTH -> position += Direction.SOUTH * instruction.value
                Action.WEST -> position += Direction.WEST * instruction.value
                Action.LEFT -> when(instruction.value) {
                    90 -> direction = direction.turnLeft()
                    180 -> direction = direction.turnAround()
                    270 -> direction = direction.turnRight()
                    else -> throw IllegalStateException("Could not turn left by ${instruction.value}°")
                }
                Action.RIGHT -> when(instruction.value) {
                    90 -> direction = direction.turnRight()
                    180 -> direction = direction.turnAround()
                    270 -> direction = direction.turnLeft()
                    else -> throw IllegalStateException("Could not turn right by ${instruction.value}°")
                }
            }
        }

        fun moveWithWaypoints(instruction: Instruction) {
            when(instruction.action) {
                Action.FORWARD -> position += waypoint * instruction.value
                Action.NORTH -> waypoint = Point(waypoint.x, waypoint.y + instruction.value)
                Action.EAST -> waypoint = Point(waypoint.x + instruction.value, waypoint.y )
                Action.SOUTH -> waypoint = Point(waypoint.x, waypoint.y - instruction.value )
                Action.WEST -> waypoint = Point(waypoint.x - instruction.value, waypoint.y )
                Action.LEFT -> when(instruction.value) {
                    90 -> waypoint = waypoint.turnLeft()
                    180 -> waypoint = waypoint.turnAround()
                    270 -> waypoint = waypoint.turnRight()
                    else -> throw IllegalStateException("Could not turn left by ${instruction.value}°")
                }
                Action.RIGHT -> when(instruction.value) {
                    90 -> waypoint = waypoint.turnRight()
                    180 -> waypoint = waypoint.turnAround()
                    270 -> waypoint = waypoint.turnLeft()
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
        operator fun times(i: Int) =  Point(x * i, y * i)

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
    enum class Direction(val x: Int, val y: Int) {
        NORTH(0, 1),
        EAST(1, 0),
        SOUTH(0, -1),
        WEST(-1, 0);

        operator fun times(i: Int): Point = Point(x*i, y*i)

        fun turnLeft(): Direction {
            return when(this) {
                NORTH -> WEST
                EAST -> NORTH
                SOUTH -> EAST
                WEST -> SOUTH
            }
        }
        fun turnRight(): Direction {
            return when(this) {
                NORTH -> EAST
                EAST -> SOUTH
                SOUTH -> WEST
                WEST -> NORTH
            }
        }
        fun turnAround(): Direction {
            return when(this) {
                NORTH -> SOUTH
                EAST -> WEST
                SOUTH -> NORTH
                WEST -> EAST
            }
        }
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
            return when(char) {
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