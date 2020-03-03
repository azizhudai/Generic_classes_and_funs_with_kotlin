package com.mindfulness.generic_classes_and_funs_with_kotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlin.reflect.KFunction
import kotlin.reflect.KMutableProperty1
import kotlin.reflect.KProperty1

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Person()

    }

    fun <T> printProperty(instance: T, prop: KProperty1<T, *>) {
        println("${prop.name} = ${prop.get(instance)}")
    }

    private fun <T> incrementProperty(
        instance: T, prop: KMutableProperty1<T, Int>
    ) {
        val value = prop.get(instance)
        prop.set(instance, value + 1)
    }

    private fun Person() {
        val person = Person("Aziz", 23)

        printProperty(person, Person::name)
        incrementProperty(person, Person::age)
        println(person.age)


        val person2 = Person("Hüdai", 32)
        val g: KFunction<String> = Person::greet//Person.::greet
        println(g.name)
        //println(g.call(person2, "Anne"))

        println("-----------------")
        person2.present()

        val hashMap: HashMap<String, String> = HashMap()
        hashMap.put("name", "Aziz")

        val my = MyClass(hashMap)
        // val myAge = MyClass(25)
        println("-----------------")
        var name = my.name
        // var age = myAge.name
        println(my.name.get("name"))

        val s = "Hello world"
        val length = s.javaClass.getMethod("length")
        val x = length.invoke(s) as Int
        println(x)

    }
}

class Person(val name: String, var age: Int) {
    fun present() = "I'm $name, and I'm $age years old"
    fun greet(other: String) = "Hi, $other, I'm $name"
}

class MyClass<T>(text: HashMap<T, T>) {
    var name = text
}