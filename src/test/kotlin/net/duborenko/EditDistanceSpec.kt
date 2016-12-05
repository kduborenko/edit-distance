package net.duborenko

import io.github.lukehutch.fastclasspathscanner.FastClasspathScanner
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.Dsl
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import java.util.ArrayList
import kotlin.test.assertEquals

class EditDistanceSpec : Spek({

    createImplementations(EditDistance::class.java)
            .forEach { editDistance ->

                describe(editDistance.javaClass.simpleName) {
                    assertEditDistance(editDistance, "cat", "cat", 0)
                    assertEditDistance(editDistance, "cat", "fat", 1)
                    assertEditDistance(editDistance, "cat", "can", 1)
                    assertEditDistance(editDistance, "industry", "interest", 6)
                    assertEditDistance(editDistance, "stratosphere", "premiere", 8)
                }

            }

})


fun Dsl.assertEditDistance(editDistance: EditDistance,
                           word1: String, word2: String, distance: Int) {
    given("$word1 -> $word2") {
        it("should return $distance") {
            assertEquals(distance,
                    editDistance.calculate(word1, word2))
        }
    }
}

fun <T> createImplementations(type: Class<T>): List<T> {
    val subClasses = ArrayList<Class<out T>>()
    FastClasspathScanner()
            .matchSubclassesOf(type) {
                subClasses += it
            }
            .scan()

    return subClasses
            .sortedBy {
                it.getAnnotation(Order::class.java)?.value ?: 0
            }
            .map {
                type.cast(it.getConstructor().newInstance())
            }
}