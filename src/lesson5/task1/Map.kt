@file:Suppress("UNUSED_PARAMETER", "ConvertCallChainIntoSequence")

package lesson5.task1

import kotlin.math.max

/**
 * Пример
 *
 * Для заданного списка покупок `shoppingList` посчитать его общую стоимость
 * на основе цен из `costs`. В случае неизвестной цены считать, что товар
 * игнорируется.
 */
fun shoppingListCost(
        shoppingList: List<String>,
        costs: Map<String, Double>): Double
{
    var totalCost = 0.0

    for (item in shoppingList)
    {
        val itemCost = costs[item]
        if (itemCost != null)
        {
            totalCost += itemCost
        }
    }

    return totalCost
}

/**
 * Пример
 *
 * Для набора "имя"-"номер телефона" `phoneBook` оставить только такие пары,
 * для которых телефон начинается с заданного кода страны `countryCode`
 */
fun filterByCountryCode(
        phoneBook: MutableMap<String, String>,
        countryCode: String)
{
    val namesToRemove = mutableListOf<String>()

    for ((name, phone) in phoneBook)
    {
        if (!phone.startsWith(countryCode))
        {
            namesToRemove.add(name)
        }
    }

    for (name in namesToRemove)
    {
        phoneBook.remove(name)
    }
}

/**
 * Пример
 *
 * Для заданного текста `text` убрать заданные слова-паразиты `fillerWords`
 * и вернуть отфильтрованный текст
 */
fun removeFillerWords(
        text: List<String>,
        vararg fillerWords: String): List<String>
{
    val fillerWordSet = setOf(*fillerWords)

    val res = mutableListOf<String>()
    for (word in text)
    {
        if (word !in fillerWordSet)
        {
            res += word
        }
    }
    return res
}

/**
 * Пример
 *
 * Для заданного текста `text` построить множество встречающихся в нем слов
 */
fun buildWordSet(text: List<String>): MutableSet<String>
{
    val res = mutableSetOf<String>()
    for (word in text) res.add(word)
    return res
}

/**
 * Средняя
 *
 * Объединить два ассоциативных массива `mapA` и `mapB` с парами
 * "имя"-"номер телефона" в итоговый ассоциативный массив, склеивая
 * значения для повторяющихся ключей через запятую.
 * В случае повторяющихся *ключей* значение из mapA должно быть
 * перед значением из mapB.
 *
 * Повторяющиеся *значения* следует добавлять только один раз.
 *
 * Например:
 *   mergePhoneBooks(
 *     mapOf("Emergency" to "112", "Police" to "02"),
 *     mapOf("Emergency" to "911", "Police" to "02")
 *   ) -> mapOf("Emergency" to "112, 911", "Police" to "02")
 */
fun mergePhoneBooks(mapA: Map<String, String>, mapB: Map<String, String>): Map<String, String>
{
    val answer = (mapB + mapA).toMutableMap()

    for ((key, value) in mapB)
        if (mapA.containsKey(key) && mapA[key] != mapB[key])
            answer[key] = "${answer[key]}, $value"

    return answer
}

/**
 * Простая
 *
 * По заданному ассоциативному массиву "студент"-"оценка за экзамен" построить
 * обратный массив "оценка за экзамен"-"список студентов с этой оценкой".
 *
 * Например:
 *   buildGrades(mapOf("Марат" to 3, "Семён" to 5, "Михаил" to 5))
 *     -> mapOf(5 to listOf("Семён", "Михаил"), 3 to listOf("Марат"))
 */
fun buildGrades(grades: Map<String, Int>): Map<Int, List<String>>
{
    val answer = mutableMapOf<Int, List<String>>()

    for ((key, value) in grades)
    {
        if (!answer.containsKey(value))
            answer.set(value, listOf(key))
        else
            answer[value] = answer[value]!!.plus(listOf(key))
    }

    return answer
}

/**
 * Простая
 *
 * Определить, входит ли ассоциативный массив a в ассоциативный массив b;
 * это выполняется, если все ключи из a содержатся в b с такими же значениями.
 *
 * Например:
 *   containsIn(mapOf("a" to "z"), mapOf("a" to "z", "b" to "sweet")) -> true
 *   containsIn(mapOf("a" to "z"), mapOf("a" to "zee", "b" to "sweet")) -> false
 */
fun containsIn(a: Map<String, String>, b: Map<String, String>): Boolean
{
    for ((key, _) in a)
        if (!(b.containsKey(key) && a[key] == b[key]))
            return false
    return true
}

/**
 * Средняя
 *
 * Для заданного списка пар "акция"-"стоимость" вернуть ассоциативный массив,
 * содержащий для каждой акции ее усредненную стоимость.
 *
 * Например:
 *   averageStockPrice(listOf("MSFT" to 100.0, "MSFT" to 200.0, "NFLX" to 40.0))
 *     -> mapOf("MSFT" to 150.0, "NFLX" to 40.0)
 */
fun averageStockPrice(stockPrices: List<Pair<String, Double>>): Map<String, Double>
{
    val answer = mutableMapOf<String, Double>()
    val counts = mutableMapOf<String, Int>()

    stockPrices.forEach {
        if (!answer.containsKey(it.first))
        {
            answer[it.first] = it.second
            counts[it.first] = 1
        }
        else
        {
            answer[it.first] = answer[it.first]!!.plus(it.second)
            counts[it.first] = counts[it.first]!!.plus(1)
        }
    }

    counts.forEach { answer[it.key] = answer[it.key]!! / it.value }

    return answer
}

/**
 * Средняя
 *
 * Входными данными является ассоциативный массив
 * "название товара"-"пара (тип товара, цена товара)"
 * и тип интересующего нас товара.
 * Необходимо вернуть название товара заданного типа с минимальной стоимостью
 * или null в случае, если товаров такого типа нет.
 *
 * Например:
 *   findCheapestStuff(
 *     mapOf("Мария" to ("печенье" to 20.0), "Орео" to ("печенье" to 100.0)),
 *     "печенье"
 *   ) -> "Мария"
 */
fun findCheapestStuff(stuff: Map<String, Pair<String, Double>>, kind: String): String?
{
    var answerPair = Pair<String?, Double>(null, Double.MAX_VALUE)

    for ((key, value) in stuff)
        if (value.first == kind && value.second <= answerPair.second)
            answerPair = Pair(key, value.second)

    return answerPair.first
}

/**
 * Сложная
 *
 * Для заданного ассоциативного массива знакомых через одно рукопожатие `friends`
 * необходимо построить его максимальное расширение по рукопожатиям, то есть,
 * для каждого человека найти всех людей, с которыми он знаком через любое
 * количество рукопожатий.
 * Считать, что все имена людей являются уникальными, а также что рукопожатия
 * являются направленными, то есть, если Марат знает Свету, то это не означает,
 * что Света знает Марата.
 *
 * Например:
 *   propagateHandshakes(
 *     mapOf(
 *       "Marat" to setOf("Mikhail", "Sveta"),
 *       "Sveta" to setOf("Marat"),
 *       "Mikhail" to setOf("Sveta")
 *     )
 *   ) -> mapOf(
 *          "Marat" to setOf("Mikhail", "Sveta"),
 *          "Sveta" to setOf("Marat", "Mikhail"),
 *          "Mikhail" to setOf("Sveta", "Marat")
 *        )
 */
fun propagateHandshakes(friends: Map<String, Set<String>>): Map<String, Set<String>>
{
    var answer = mapOf<String, Set<String>>()

    friends.forEach { s, _ -> answer += mapOf(s to dfs(mutableSetOf(), s, friends, mutableSetOf())) }

    answer.forEach { s, set -> set.forEach { if (!friends.containsKey(it)) answer += mapOf(it to setOf()) } }

    return answer
}

fun dfs(usedNames: MutableSet<String>, name: String, friends: Map<String, Set<String>>,
        iterationAnswer: MutableSet<String>): Set<String>
{
    if (friends.containsKey(name) && !friends[name]!!.isEmpty() && !usedNames.contains(name))
    {
        usedNames.add(name)
        friends[name]!!.forEach { s ->
            if (!usedNames.contains(s)) iterationAnswer.add(s)
            dfs(usedNames, s, friends, iterationAnswer)
        }
    }

    return iterationAnswer
}

/**
 * Простая
 *
 * Удалить из изменяемого ассоциативного массива все записи,
 * которые встречаются в заданном ассоциативном массиве.
 * Записи считать одинаковыми, если и ключи, и значения совпадают.
 *
 * ВАЖНО: необходимо изменить переданный в качестве аргумента
 *        изменяемый ассоциативный массив
 *
 * Например:
 *   subtractOf(a = mutableMapOf("a" to "z"), mapOf("a" to "z"))
 *     -> a changes to mutableMapOf() aka becomes empty
 */
fun subtractOf(a: MutableMap<String, String>, b: Map<String, String>) =
        b.forEach { k, _ -> if (a.containsKey(k) && a[k] == b[k]) a.remove(k) }

/**
 * Простая
 *
 * Для двух списков людей найти людей, встречающихся в обоих списках
 */
fun whoAreInBoth(a: List<String>, b: List<String>): List<String> = a.intersect(b).toList()

/**
 * Средняя
 *
 * Для заданного набора символов определить, можно ли составить из него
 * указанное слово (регистр символов игнорируется)
 *
 * Например:
 *   canBuildFrom(listOf('a', 'b', 'o'), "baobab") -> true
 */
fun canBuildFrom(chars: List<Char>, word: String): Boolean = chars.containsAll(word.toList())

/**
 * Средняя
 *
 * Найти в заданном списке повторяющиеся элементы и вернуть
 * ассоциативный массив с информацией о числе повторений
 * для каждого повторяющегося элемента.
 * Если элемент встречается только один раз, включать его в результат
 * не следует.
 *
 * Например:
 *   extractRepeats(listOf("a", "b", "a")) -> mapOf("a" to 2)
 */
fun extractRepeats(list: List<String>): Map<String, Int>
{
    val lettersCount = mutableMapOf<String, Int>()

    list.forEach { if (!lettersCount.containsKey(it)) lettersCount[it] = 1 else lettersCount[it] = lettersCount[it]!!.plus(1) }

    return lettersCount.filter { it.value > 1 }
}

/**
 * Средняя
 *
 * Для заданного списка слов определить, содержит ли он анаграммы
 * (два слова являются анаграммами, если одно можно составить из второго)
 *
 * Например:
 *   hasAnagrams(listOf("тор", "свет", "рот")) -> true
 */
fun hasAnagrams(words: List<String>): Boolean
{
    words.forEachIndexed { i, s ->
        for (j in i + 1 until words.size)
            if (s.toList().containsAll(words[j].toList()) && s != "" && words[j] != "") return true
    }
    return false
}

/**
 * Сложная
 *
 * Для заданного списка неотрицательных чисел и числа определить,
 * есть ли в списке пара чисел таких, что их сумма равна заданному числу.
 * Если да, верните их индексы в виде Pair<Int, Int>;
 * если нет, верните пару Pair(-1, -1).
 *
 * Индексы в результате должны следовать в порядке (меньший, больший).
 *
 * Постарайтесь сделать ваше решение как можно более эффективным,
 * используя то, что вы узнали в данном уроке.
 *
 * Например:
 *   findSumOfTwo(listOf(1, 2, 3), 4) -> Pair(0, 2)
 *   findSumOfTwo(listOf(1, 2, 3), 6) -> Pair(-1, -1)
 */
fun findSumOfTwo(list: List<Int>, number: Int): Pair<Int, Int>
{
    list.forEachIndexed { i, it ->
        if (list.subList(i + 1, list.lastIndex + 1).contains(number - it))
            return Pair(i, list.subList(i + 1, list.lastIndex + 1).indexOf(number - it) + i + 1)
    }
    return Pair(-1, -1)
}

/**
 * Очень сложная
 *
 * Входными данными является ассоциативный массив
 * "название сокровища"-"пара (вес сокровища, цена сокровища)"
 * и вместимость вашего рюкзака.
 * Необходимо вернуть множество сокровищ с максимальной суммарной стоимостью,
 * которые вы можете унести в рюкзаке.
 *
 * Например:
 *   bagPacking(
 *     mapOf("Кубок" to (500 to 2000), "Слиток" to (1000 to 5000)),
 *     850
 *   ) -> setOf("Кубок")
 *   bagPacking(
 *     mapOf("Кубок" to (500 to 2000), "Слиток" to (1000 to 5000)),
 *     450
 *   ) -> emptySet()
 */
var values: Array<Array<Int>> = arrayOf()
val answer = mutableSetOf<String>()

fun bagPacking(treasures: Map<String, Pair<Int, Int>>, capacity: Int): Set<String>
{
    values = Array(capacity + 1) { Array(treasures.size + 1) { 0 } }
    val indexedTreasures = treasures.filter { it.value.first <= capacity }.toList()

    fillValuesArray(indexedTreasures, capacity)
    answer.clear()
    buildAnswerSet(indexedTreasures, capacity, indexedTreasures.size)

    return answer

}

fun fillValuesArray(indexedTreasures: List<Pair<String, Pair<Int, Int>>>, capacity: Int)
{
    for (i in 1..indexedTreasures.size)
        for (j in 1..capacity)
            values[j][i] =
                    if (j < indexedTreasures[i - 1].second.first)
                        values[j][i - 1]
                    else
                    {
                        max(values[j][i - 1],
                                values[j - indexedTreasures[i - 1].second.first][i - 1] +
                                        indexedTreasures[i - 1].second.second)
                    }

}

fun buildAnswerSet(indexedTreasures: List<Pair<String, Pair<Int, Int>>>, intermediateCapacity: Int, index: Int)
{
    if (values[intermediateCapacity][index] == 0)
        return
    if (values[intermediateCapacity][index - 1] == values[intermediateCapacity][index])
        buildAnswerSet(indexedTreasures, intermediateCapacity, index - 1)
    else
    {
        answer.add(indexedTreasures[index - 1].first)
        buildAnswerSet(indexedTreasures,
                intermediateCapacity - indexedTreasures[index - 1].second.first,
                index - 1)
    }
}












