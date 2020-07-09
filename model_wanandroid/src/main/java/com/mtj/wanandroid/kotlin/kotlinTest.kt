package com.mtj.wanandroid.kotlin

import kotlin.math.max

/**
 * @author  孟腾蛟
 * @Description
 * @date 2020/6/20
 */
fun main() {
//    println(checkNumber(1L))
//    testFor()
//    val student = Student("我", 20).apply {
//        doHomeWork()
//        readBook()
//    }
//    listFruits.add(Int.MAX_VALUE.toString())
//    for (fruit in listFruits) {
//        println(fruit)
//    }
    for((key,value) in mapFruits){
        println("key: $key  value: $value")
    }
}

val mapFruits = mapOf(0 to "apple1",1 to "apple2",2 to "apple3")

val listFruits = mutableListOf("apple", "orange")

interface Study {
    fun readBook()
    fun doHomeWork()
}

class Student(name: String, age: Int) : Person(name, age), Study {
    override fun readBook() {
        println("$name 开始读书")
    }

    override fun doHomeWork() {
        println("$name 开始做作业")

    }

}

open class Person(val name: String, val age: Int) {

}

fun testFor() {
//    for (i in 0 until 20) {
//        println(i)
//    }
    var testI = 0;
    while (testI < 80) {
        println(testI++)
    }
}

fun add(a: Int, b: Int): Int {
    return a + b
}

fun largerNumber(num1: Int, num2: Int): Int = max(num1, num2)

fun testIf(num1: Int, num2: Int): Int = when {
    num1 > 2 -> 50
    num1 > num2 -> 1
    num1 < num2 -> -1
    else -> 0
}

fun compareTo(num1: Int, num2: Int): Int = num1.compareTo(num2)

fun checkNumber(num: Number): String = when (num) {
    is Int -> "Int"
    is Float -> "Float"
    is Double -> "Double"
    else -> "其他"
}





