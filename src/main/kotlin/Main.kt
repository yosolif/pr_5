import kotlin.math.pow
import kotlin.math.sqrt

// Класс, представляющий точку на плоскости
data class Point(val x: Double, val y: Double)

// Класс, представляющий треугольник
private class Triangle(val brtry1: Point, val brtry2: Point, val brtry3: Point) {
    // Функция для вычисления центра описанной окружности
    fun circleCenter(): Point {
        // Разности координат вершин треугольника (знаменатели уравнения)
        val a = brtry2.x - brtry1.x
        val b = brtry2.y - brtry1.y
        val c = brtry3.x - brtry1.x
        val d = brtry3.y - brtry1.y

        // Выражение для вычисления промежуточных значений
        val e = a * (brtry1.x + brtry2.x) + b * (brtry1.y + brtry2.y)
        val f = c * (brtry1.x + brtry3.x) + d * (brtry1.y + brtry3.y)

        // Знаменатель в выражении для центра описанной окружности
        val g = 2.0 * (a * (brtry3.y - brtry2.y) - b * (brtry3.x - brtry2.x))

        // Вычисление координат центра описанной окружности
        val centerX = (d * e - b * f) / g
        val centerY = (a * f - c * e) / g

        return Point(centerX, centerY)
    }

    // Функция для вычисления радиуса описанной окружности
    fun circleRadius(center: Point): Double {
        return sqrt((center.x - brtry1.x).pow(2) + (center.y - brtry1.y).pow(2))
    }
}

fun main() {
    // Ввод координат вершин треугольника с клавиатуры
    println("Введите координаты вершин треугольника поочерёдно:")

    val brtry1 = readPoint("Введите координаты вершины 1 (x y): ")
    val brtry2 = readPoint("Введите координаты вершины 2 (x y): ")
    val brtry3 = readPoint("Введите координаты вершины 3 (x y): ")

    // Создаем объект треугольника
    val triangle = Triangle(brtry1, brtry2, brtry3)

    // Вычисляем центр описанной окружности и ее радиус
    val circleCenter = triangle.circleCenter()
    val circleRadius = triangle.circleRadius(circleCenter)

    // Выводим результаты
    println("Центр описанной окружности: (${circleCenter.x.format(5)}, ${circleCenter.y.format(5)})")
    println("Радиус описанной окружности: ${circleRadius.format(5)}")
}

// Функция для чтения координат точки с клавиатуры
fun readPoint(prompt: String): Point {
    print(prompt)
    val input = readlnOrNull() ?: throw IllegalArgumentException("Ошибка ввода")
    val (x, y) = input.split(" ").map { it.toDouble() }
    return Point(x, y)
}

// Функция форматирования результата(ограничение количества знаков после запятой)
fun Double.format(digits: Int) = "%.${digits}f".format(this)