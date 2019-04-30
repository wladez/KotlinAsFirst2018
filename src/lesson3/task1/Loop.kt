@file:Suppress("UNUSED_PARAMETER")
package lesson3.task1

import kotlin.math.PI
import kotlin.math.sqrt

/**
 * Пример
 *
 * Вычисление факториала
 */
fun factorial(n: Int): Double {
    var result = 1.0
    for (i in 1..n) {
        result = result * i // Please do not fix in master
    }
    return result
}

/**
 * Пример
 *
 * Проверка числа на простоту -- результат true, если число простое
 */
fun isPrime(n: Int): Boolean {
    if (n < 2) return false
    if (n == 2) return true
    if (n % 2 == 0) return false
    for (m in 3..sqrt(n.toDouble()).toInt() step 2) {
        if (n % m == 0) return false
    }
    return true
}

/**
 * Пример
 *
 * Проверка числа на совершенность -- результат true, если число совершенное
 */
fun isPerfect(n: Int): Boolean {
    var sum = 1
    for (m in 2..n/2) {
        if (n % m > 0) continue
        sum += m
        if (sum > n) break
    }
    return sum == n
}

/**
 * Пример
 *
 * Найти число вхождений цифры m в число n
 */
fun digitCountInNumber(n: Int, m: Int): Int =
        when {
            n == m -> 1
            n < 10 -> 0
            else -> digitCountInNumber(n / 10, m) + digitCountInNumber(n % 10, m)
        }

/**
 * Тривиальная
 *
 * Найти количество цифр в заданном числе n.
 * Например, число 1 содержит 1 цифру, 456 -- 3 цифры, 65536 -- 5 цифр.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun digitNumber(n: Int): Int {
    var count = 1
    var number = n
    while (number >= 10) {
        count++
        number /= 10
    }
    return count
}

/**
 * Простая
 *
 * Найти число Фибоначчи из ряда 1, 1, 2, 3, 5, 8, 13, 21, ... с номером n.
 * Ряд Фибоначчи определён следующим образом: fib(1) = 1, fib(2) = 1, fib(n+2) = fib(n) + fib(n+1)
 */
fun fib(n: Int): Int {
    if (n < 3)
        return 1
    var first = 1
    var second = 1
    var result = 0
    for (i in 1..n-2) {
        result = first + second
        first = second
        second = result
    }

    return result
}

/**
 * Простая
 *
 * Для заданных чисел m и n найти наименьшее общее кратное, то есть,
 * минимальное число k, которое делится и на m и на n без остатка
 */
fun lcm(m: Int, n: Int): Int {
    return m / gcd(m, n) * n
}

private fun gcd(m: Int, n: Int): Int {
    return if (n == 0) m else gcd(n, m % n)
}
/**
 * Простая
 *
 * Для заданного числа n > 1 найти минимальный делитель, превышающий 1
 */
fun minDivisor(n: Int): Int {
    var i = 2
    while (n % i != 0)
        i++
    return i
}

/**
 * Простая
 *
 * Для заданного числа n > 1 найти максимальный делитель, меньший n
 */
fun maxDivisor(n: Int): Int {
    var i = n-1
    while (n % i != 0)
        i--
    return i
}

/**
 * Простая
 *
 * Определить, являются ли два заданных числа m и n взаимно простыми.
 * Взаимно простые числа не имеют общих делителей, кроме 1.
 * Например, 25 и 49 взаимно простые, а 6 и 8 -- нет.
 */
fun isCoPrime(m: Int, n: Int): Boolean {
    val min = Math.min(m, n)
    for (i in 2..min) {
        if (m % i == 0 && n % i == 0) {
            return false
        }
    }
    return true
}

/**
 * Простая
 *
 * Для заданных чисел m и n, m <= n, определить, имеется ли хотя бы один точный квадрат между m и n,
 * то есть, существует ли такое целое k, что m <= k*k <= n.
 * Например, для интервала 21..28 21 <= 5*5 <= 28, а для интервала 51..61 квадрата не существует.
 */
fun squareBetweenExists(m: Int, n: Int): Boolean {
    for (i in m..n)
        if (Math.sqrt(i.toDouble()) % 1.0 == 0.0)
            return true

    return false
}

/**
 * Средняя
 *
 * Гипотеза Коллатца. Рекуррентная последовательность чисел задана следующим образом:
 *
 *   ЕСЛИ (X четное)
 *     Xслед = X /2
 *   ИНАЧЕ
 *     Xслед = 3 * X + 1
 *
 * например
 *   15 46 23 70 35 106 53 160 80 40 20 10 5 16 8 4 2 1 4 2 1 4 2 1 ...
 * Данная последовательность рано или поздно встречает X == 1.
 * Написать функцию, которая находит, сколько шагов требуется для
 * этого для какого-либо начального X > 0.
 */
fun collatzSteps(x: Int): Int {
    var i = x
    var step = 0
    while (i != 1) {
        step++
        if (i % 2 == 0)
            i /= 2
        else
            i = 3*i + 1
    }
    return step
}

/**
 * Средняя
 *
 * Для заданного x рассчитать с заданной точностью eps
 * sin(x) = x - x^3 / 3! + x^5 / 5! - x^7 / 7! + ...
 * Нужную точность считать достигнутой, если очередной член ряда меньше eps по модулю
 */
fun sin(x: Double, eps: Double): Double {
    var sum = x
    var localX = x
    if (x > 2*PI) {
        sum = x - 2*PI*(Math.round(x/(2*PI)))
        localX = sum
    }
    var i = 3
    var count = 0
    var step = 0.0
    while (true) {
        count++
        val pow = Math.pow(localX, i.toDouble())
        val fac = factorial(i)
        if (pow.isInfinite() || fac.isInfinite())
            return sum
        step = pow / fac
        i+=2
        if (step < eps)
            return sum
        if (count % 2 == 0)
            sum += step
        else
            sum -= step
    }
}

/**
 * Средняя
 *
 * Для заданного x рассчитать с заданной точностью eps
 * cos(x) = 1 - x^2 / 2! + x^4 / 4! - x^6 / 6! + ...
 * Нужную точность считать достигнутой, если очередной член ряда меньше eps по модулю
 */
fun cos(x: Double, eps: Double): Double {
    var sum = 1.0
    var count = 0
    var i = 2
    var step: Double
    var localX = x
    if (x > 2*PI) {
        localX = x - 2*PI*(Math.round(x/(2*PI)))
    }

    while (true) {
        count++
        val pow = Math.pow(localX, i.toDouble())
        val fac = factorial(i)
        if (pow.isInfinite() || fac.isInfinite())
            return sum
        step = pow / fac
        i+=2
        if (step < eps)
            return sum
        if (count % 2 == 0)
            sum += step
        else
            sum -= step
    }
}

/**
 * Средняя
 *
 * Поменять порядок цифр заданного числа n на обратный: 13478 -> 87431.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun revert(n: Int): Int {
    var i = 0
    var number = n
    var result = 0
    val size = n.toString().length - 1
    while (number > 0) {
        result += (number % 10)*Math.pow(10.0, (size - i).toDouble()).toInt()
        number /= 10
        i++
    }
    return if (n < 10)
        n
    else
        result
}

/**
 * Средняя
 *
 * Проверить, является ли заданное число n палиндромом:
 * первая цифра равна последней, вторая -- предпоследней и так далее.
 * 15751 -- палиндром, 3653 -- нет.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun isPalindrome(n: Int): Boolean {
    return n == revert(n)
}

/**
 * Средняя
 *
 * Для заданного числа n определить, содержит ли оно различающиеся цифры.
 * Например, 54 и 323 состоят из разных цифр, а 111 и 0 из одинаковых.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun hasDifferentDigits(n: Int): Boolean {
    var number = n
    val arr = ArrayList<Int>()
    while (number > 0) {
        arr.add(number % 10)
        number /= 10
    }

    for (i in 0 until arr.size) {
        for (j in 0 until arr.size) {
            if (i == j)
                continue
            else if (arr[i] != arr[j])
                return true
        }
    }

    return false
}

/**
 * Сложная
 *
 * Найти n-ю цифру последовательности из квадратов целых чисел:
 * 149162536496481100121144...
 * Например, 2-я цифра равна 4, 7-я 5, 12-я 6.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun squareSequenceDigit(n: Int): Int {
    var i = 1
    var depth = 0
    while (i <= n) {
        val step = i*i
        when {
            step / 10 == 0 -> depth++
            step / 10 < 10 -> depth += 2
            step / 10 >= 10 -> depth += 3
        }
        if (depth == n)
            return step % 10
        else if (depth > n)
            when {
                step < 100 -> return step / 10
                step >= 100 -> return (step / 10) % 10
            }
        i++
    }
    return 1
}

/**
 * Сложная
 *
 * Найти n-ю цифру последовательности из чисел Фибоначчи (см. функцию fib выше):
 * 1123581321345589144...
 * Например, 2-я цифра равна 1, 9-я 2, 14-я 5.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun fibSequenceDigit(n: Int): Int {
    if (n in 1..2)
        return 1
    var prev = 1
    var fib = 1

    var depth = 2

    for (i in 2..n) {
        val tmp = fib
        fib += prev
        prev = tmp
        when {
            fib / 10 == 0 -> depth++
            fib / 10 < 10 -> depth += 2
            fib / 10 >= 10 -> depth += 3
        }
        when (depth) {
            n -> return fib % 10
            n+1 -> when {
                fib < 100 -> return fib / 10
                fib >= 100 -> return (fib / 10) % 10
            }
            n+2 -> return fib / 100
        }
    }

    return 1
}
