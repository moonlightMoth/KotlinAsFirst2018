@file:Suppress("UNUSED_PARAMETER")

package lesson2.task2

import jdk.nashorn.internal.runtime.linker.Bootstrap
import lesson1.task1.sqr
import kotlin.math.abs
import kotlin.math.sqrt

/**
 * Пример
 *
 * Лежит ли точка (x, y) внутри окружности с центром в (x0, y0) и радиусом r?
 */
fun pointInsideCircle(x: Double, y: Double, x0: Double, y0: Double, r: Double) =
        sqr(x - x0) + sqr(y - y0) <= sqr(r)

/**
 * Простая
 *
 * Четырехзначное число назовем счастливым, если сумма первых двух ее цифр равна сумме двух последних.
 * Определить, счастливое ли заданное число, вернуть true, если это так.
 */
fun isNumberHappy(number: Int): Boolean =
        number / 100 % 10 + number / 1000 == number % 10 + number % 100 / 10

/**
 * Простая
 *
 * На шахматной доске стоят два ферзя (ферзь бьет по вертикали, горизонтали и диагоналям).
 * Определить, угрожают ли они друг другу. Вернуть true, если угрожают.
 * Считать, что ферзи не могут загораживать друг друга.
 */
fun queenThreatens(x1: Int, y1: Int, x2: Int, y2: Int): Boolean =
        x1 == x2 || y1 == y2 || abs(x1 - x2) == abs(y1 - y2)


/**
 * Простая
 *
 * Дан номер месяца (от 1 до 12 включительно) и год (положительный).
 * Вернуть число дней в этом месяце этого года по григорианскому календарю.
 */
fun daysInMonth(month: Int, year: Int): Int =
        when (month)
        {
            4, 6, 9, 11 -> 30
            2 -> if (year % 400 == 0) 29 else if (year % 100 == 0) 28 else if (year % 4 == 0) 29 else 28
            else -> 31
        }

/**
 * Средняя
 *
 * Проверить, лежит ли окружность с центром в (x1, y1) и радиусом r1 целиком внутри
 * окружности с центром в (x2, y2) и радиусом r2.
 * Вернуть true, если утверждение верно
 */
fun circleInside(x1: Double, y1: Double, r1: Double,
                 x2: Double, y2: Double, r2: Double): Boolean =
        sqrt(sqr(x2 - x1) + sqr(y2 - y1)) + r1 <= r2

/**
 * Средняя
 *
 * Определить, пройдет ли кирпич со сторонами а, b, c сквозь прямоугольное отверстие в стене со сторонами r и s.
 * Стороны отверстия должны быть параллельны граням кирпича.
 * Считать, что совпадения длин сторон достаточно для прохождения кирпича, т.е., например,
 * кирпич 4 х 4 х 4 пройдёт через отверстие 4 х 4.
 * Вернуть true, если кирпич пройдёт
 */
fun brickPasses(a: Int, b: Int, c: Int, r: Int, s: Int): Boolean
{
    val mass: Array<Int> = arrayOf(a, b, c, r, s)
    mass.sort()

    var flagR = false
    var flagS = false

    var rPos = 0
    var sPos = 0

    for (i in 4 downTo 0)
    {
        if (mass[i] == r && ! flagR)
        {
            rPos = i
            flagR = true
            continue
        }
        if (mass[i] == s && ! flagS)
        {
            sPos = i
            flagS = true
            continue
        }
    }

    return (rPos + sPos >= 5) || (rPos == 1 && sPos == 3) || (rPos == 3 && sPos == 1)
}
