@file:Suppress("UNUSED_PARAMETER")
package lesson2.task1

import lesson1.task1.discriminant
import lesson1.task1.sqr
import kotlin.math.abs

import kotlin.math.max
import kotlin.math.sqrt

/**
 * Пример
 *
 * Найти число корней квадратного уравнения ax^2 + bx + c = 0
 */
fun quadraticRootNumber(a: Double, b: Double, c: Double): Int {
    val discriminant = discriminant(a, b, c)
    return when {
        discriminant > 0.0 -> 2
        discriminant == 0.0 -> 1
        else -> 0
    }
}

/**
 * Пример
 *
 * Получить строковую нотацию для оценки по пятибалльной системе
 */
fun gradeNotation(grade: Int): String = when (grade) {
    5 -> "отлично"
    4 -> "хорошо"
    3 -> "удовлетворительно"
    2 -> "неудовлетворительно"
    else -> "несуществующая оценка $grade"
}

/**
 * Пример
 *
 * Найти наименьший корень биквадратного уравнения ax^4 + bx^2 + c = 0
 */
fun minBiRoot(a: Double, b: Double, c: Double): Double {
    // 1: в главной ветке if выполняется НЕСКОЛЬКО операторов
    if (a == 0.0) {
        if (b == 0.0) return Double.NaN // ... и ничего больше не делать
        val bc = -c / b
        if (bc < 0.0) return Double.NaN // ... и ничего больше не делать
        return -sqrt(bc)
        // Дальше функция при a == 0.0 не идёт
    }
    val d = discriminant(a, b, c)   // 2
    if (d < 0.0) return Double.NaN  // 3
    // 4
    val y1 = (-b + sqrt(d)) / (2 * a)
    val y2 = (-b - sqrt(d)) / (2 * a)
    val y3 = max(y1, y2)       // 5
    if (y3 < 0.0) return Double.NaN // 6
    return -sqrt(y3)           // 7
}

/**
 * Простая
 *
 * Мой возраст. Для заданного 0 < n < 200, рассматриваемого как возраст человека,
 * вернуть строку вида: «21 год», «32 года», «12 лет».
 */
fun ageDescription(age: Int): String
{
    var year: String
    val lastDigit = age % 10

    if (!(age % 100 in 10..20))
        if (lastDigit == 1)
            year = "год"
        else if (lastDigit == 2 || lastDigit == 3 || lastDigit == 4)
            year = "года"
        else year = "лет"
    else
        year = "лет"
    return age.toString() + " " + year;
}

/**
 * Простая
 *
 * Путник двигался t1 часов со скоростью v1 км/час, затем t2 часов — со скоростью v2 км/час
 * и t3 часов — со скоростью v3 км/час.
 * Определить, за какое время он одолел первую половину пути?
 */
fun timeForHalfWay(t1: Double, v1: Double,
                   t2: Double, v2: Double,
                   t3: Double, v3: Double): Double
{
    val firstPart = t1 * v1
    val secondPart = t2 * v2
    val thirdPart = t3 * v3
    val halfWay = (firstPart + secondPart + thirdPart) / 2

    if (halfWay <= firstPart)
        return halfWay / v1
    else if (halfWay <= firstPart + secondPart)
        return (halfWay - firstPart) / v2 + t1
    else return (halfWay - firstPart - secondPart) / v3 + t1 + t2
}

/**
 * Простая
 *
 * Нa шахматной доске стоят черный король и две белые ладьи (ладья бьет по горизонтали и вертикали).
 * Определить, не находится ли король под боем, а если есть угроза, то от кого именно.
 * Вернуть 0, если угрозы нет, 1, если угроза только от первой ладьи, 2, если только от второй ладьи,
 * и 3, если угроза от обеих ладей.
 * Считать, что ладьи не могут загораживать друг друга
 */
fun whichRookThreatens(kingX: Int, kingY: Int,
                       rookX1: Int, rookY1: Int,
                       rookX2: Int, rookY2: Int): Int
{
    var hitCounter = 0

    if(kingX == rookX1 || kingY == rookY1)
        if (kingX == rookX2 || kingY == rookY2)
            hitCounter = 3
        else hitCounter = 1
    else if(kingX == rookX2 || kingY == rookY2)
        hitCounter = 2

    return hitCounter

}

/**
 * Простая
 *
 * На шахматной доске стоят черный король и белые ладья и слон
 * (ладья бьет по горизонтали и вертикали, слон — по диагоналям).
 * Проверить, есть ли угроза королю и если есть, то от кого именно.
 * Вернуть 0, если угрозы нет, 1, если угроза только от ладьи, 2, если только от слона,
 * и 3, если угроза есть и от ладьи и от слона.
 * Считать, что ладья и слон не могут загораживать друг друга.
 */
fun rookOrBishopThreatens(kingX: Int, kingY: Int,
                          rookX: Int, rookY: Int,
                          bishopX: Int, bishopY: Int): Int
{
    var hitCounter = 0

    if(kingX == rookX || kingY == rookY)
        if (abs(kingX - bishopX) == abs(kingY - bishopY))
            hitCounter = 3
        else hitCounter = 1
    else if(abs(kingX - bishopX) == abs(kingY - bishopY))
        hitCounter = 2

    return hitCounter
}

/**
 * Простая
 *
 * Треугольник задан длинами своих сторон a, b, c.
 * Проверить, является ли данный треугольник остроугольным (вернуть 0),
 * прямоугольным (вернуть 1) или тупоугольным (вернуть 2).
 * Если такой треугольник не существует, вернуть -1.
 */
fun triangleKind(a: Double, b: Double, c: Double): Int
{
    var returnDigit: Int
    if((a < b + c) && (b < a + c) && (c < a + b))
    {
        if(a == maxOf(a, b, c))
            returnDigit =  calculateTheorem(a, b, c)
        else if(b == maxOf(a, b, c))
            returnDigit =  calculateTheorem(b, c, a)
        else
            returnDigit =  calculateTheorem(c, b, a)
    }
    else
        returnDigit =  -1
    return returnDigit
}

fun calculateTheorem(maxNum: Double, b: Double, c: Double): Int
{
    if (sqr(b) + sqr(c) > sqr(maxNum))
        return 0
    if (sqr(b) + sqr(c) == sqr(maxNum))
        return 1
    else
        return 2
}




/**
 * Средняя
 *
 * Даны четыре точки на одной прямой: A, B, C и D.
 * Координаты точек a, b, c, d соответственно, b >= a, d >= c.
 * Найти длину пересечения отрезков AB и CD.
 * Если пересечения нет, вернуть -1.
 */
fun segmentLength(a: Int, b: Int, c: Int, d: Int): Int
{
    if (b < c || d < a)
        return -1
    else if (a <= c && c <= b && b <= d)
        return b - c
    else if (c <= a && a <= d && d <= b)
        return d - a
    else if (a <= c && d <= b)
        return d - c
    else if (c <= a && b <= d)
        return b - a
    return 0
}
