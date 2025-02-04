package flow

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class FlowTest {
    @Test
    fun testFlowFromFlow() = runBlocking {
        val flow1: Flow<Int> = flowOf(1, 2, 3)

        fun sut(src: Flow<Int>): Flow<String> = src.map { "_${it * 10}" }

        val actual = sut(flow1).toList()
        assertEquals(listOf("_10", "_20", "_30"), actual)
    }

    @Test
    fun testFlowFromFlowValue() = runBlocking {
        val flow1: Flow<Int> = flowOf(1, 2, 3)

        fun flow2(value: Int): Flow<String> = flow {
            delay(50)
            emit("_${value * 10}")
        }

        fun sut(src: Flow<Int>): Flow<String> = src.flatMapMerge { value -> flow2(value) }

        val actual = sut(flow1).toList()
        assertEquals(listOf("_10", "_20", "_30"), actual)
    }


    @Test
    fun testFlowUsingFlowValue() = runBlocking {
        val flow1: Flow<Int> = flowOf(1, 2, 3)

        fun flow2(value: Int): Flow<String> = flow {
            delay(50)
            emit("_${value * 10}")
        }

        fun sut(src: Flow<Int>): Flow<String> = src.flatMapMerge { value ->
            flow2(value).map {
                value.toString() + it
            }
        }

        val actual = sut(flow1).toList()
        assertEquals(listOf("1_10", "2_20", "3_30"), actual)
    }

    @Test
    fun testFlowFromFlowOfList() = runBlocking {
        val flow1: Flow<List<Int>> = flowOf(listOf(1, 2, 3))

        fun flow2(i: Int): Flow<Double> = flow {
            delay(50)
            emit(i / 10.0)
        }

        fun sut(src: Flow<List<Int>>): Flow<List<Double>> = src.flatMapMerge { list ->
            flow {
                val flows = list.map { flow2(it) }
                combine(flows) {
                    listOf(it)
                }
            }
        }

        sut(flow1).collect { actual ->
            assertEquals(listOf(0.1, 0.2, 0.3), actual)
        }
    }

    @Test
    fun testFlowFromSuspendedOfList() = runBlocking {
        val flow1: Flow<List<Int>> = flowOf(listOf(1, 2, 3))

        suspend fun flow2(i: Int): Double {
            delay(50)
            return i / 10.0
        }

        fun sut(src: Flow<List<Int>>): Flow<List<Double>> = src.flatMapLatest {ints ->
            flow {
                val results = ints.map { flow2(it) }
                emit(results)
            }.onEach {
                println("onEach: $it")
            }
        }

        val actual = sut(flow1).first()
        assertEquals(listOf(0.1, 0.2, 0.3), actual)
    }

    @Test
    fun testFlowFromSuspendedOfListCombined() = runBlocking {
        val flow1: Flow<List<Int>> = flowOf(listOf(1, 2, 3))

        suspend fun flow2(i: Int): Double {
            delay(50)
            return i / 10.0
        }

        fun sut(src: Flow<List<Int>>): Flow<List<String>> = src.flatMapLatest {ints ->
            flow {
                val result = ints.map { intValue ->
                    val doubleValue = flow2(intValue)
                    "${intValue}_${doubleValue}"
                }
                emit(result)
            }.onEach {
                println("onEach: $it")
            }
        }

        val actual = sut(flow1).first()
        assertEquals(listOf("1_0.1", "2_0.2", "3_0.3"), actual)
    }
    /*
     @Test
     fun testFlowMerge() = runBlocking {
         val flow1: Flow<Int> = flowOf(1, 2, 3)

         fun flow2(src: Int): Flow<String> = src.map { "_${it * 10}" }

         fun sut(src: Flow<Int>): Flow<String> = src.flatMapMerge {f1 -> flow2(it) }

         val actual = sut(flow1).toList()
         assertEquals(listOf("_10", "_20", "_30"), actual)
     }
     */
}