package com.will_d.ex27coroutineflow

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import java.lang.Exception
import kotlin.math.log
import kotlin.system.measureTimeMillis

class MainActivity : AppCompatActivity() {
    @SuppressLint("LongLogTag")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //suspend fun -> 중단함수 : 비동기적으로 하나의 결과값을 반환 받을 수 있음.
        //Coroutine은 일시 중시 재시작이 가능함... 이러한 장점을 통해서 비동기 작업을 순차적으로 처리하도록 하는것이 코드로 구현이 가능함...
        //비동기 작업을 순차적으로 처리할수 있을때 가장 중요한 핵심은 논리를 정확히 확립 할 수 있다는것임 결국 후에 유지보수에 막강함과 코드간결화에 다양하게 기여를 할 수 있음... 완전 대박이지!!
        // - Coroutine은 굉장히 서로 협력적임...
        // - 이 Coroutine의 협력을 suspend function, Flow를 통해서 구현해야함...
        // - Coroutine의 협력적인 성격을 통해 비동기 프로그래밍을 순차적으로 처리 가능하게 하고 잠시 멈춤(pause) 다시 시작(resumed) 를 통해서 성능 향상을 기대할 수 있음...
        // - 이번 계기를 통해서 왜 Coroutine인가를 정확히 이해하는것은 매우 매우 중요할 것임....(Coroutine과 비동기 프로그래밍을 구현 할 수 있는 지점은 RxProgramming 일 것임. : RxJava, RxSwift..)

        //Flow를 통해서 무엇을 할 수 있는가
        // - 서버의 데이터를 정제 할 수 있음 : 즉 내가 원하는 방향대로 데이터를 쉽게 다룰 수 있다는 소리임
        // - Flow는 리액티브 프로그래밍을 지원하기 위한 라이브러리임... : 즉 데이터와 UI를 일치시키는 리액티브 프로그래밍에서 Flow의 데이터 스트림에 대한 파이프라인 구축은 막강할 것으로 예측이 됨
        // - 예를 들어서 이런것 할 수 있을거 같음... 사용자의 스크롤링의 시점에 따라 데이터를 나눠서 불러들이는것이 가능함...
        // - MVVM Design pattern을 통해서 프로그래밍을 한다면 Repository에서 Flow에 의한 데이터 스트림 파이프라인을 구축 하는것이 핵심일것.

        //Flow의 기본 구조 -> 잘 정제하고, 시점을 잘 판단해서 어떻게 동작할것인지 규율을 확립하는것이 Flow를 사용하는데 가장 중요 한 부분...
        //1. Producer(생산자) -> 데이터 제공 Flow Block(FlowBuilder)에서 emit을 통해서 전달
        //2. Intermediary(중간 연산자) -> *** 데이터 정제
        //3. Consumer(소비자) -> collect를 통해서 소비...


       //예제#1 suspend
        CoroutineScope(Dispatchers.Default).launch {
            simple().forEach{
                Log.i("CoroutineFlowTest#1", "" + it)
            }
        }

        Log.i("CoroutineFlowTest#1", "MainThread")

        //예제#2 flow 기본
        CoroutineScope(Dispatchers.Default).launch {

            simple2().collect{
                Log.i("CoroutineFlowTest#2", "" + it)
            }

            for (k in 1..3) {
                Log.i("CoroutineFlowTest#2", "I'm not blocked $k")
                delay(100)
            }
        }

        //예제#3 flow
        CoroutineScope(Dispatchers.Default).launch {
            Log.i("CoroutineFlowTest#3", "Calling simple function...")
            val flow = simple3()
            Log.i("CoroutineFlowTest#3", "Calling collect...")
            flow.collect{
               Log.i("CoroutineFlowTest#3", "" + it)
            }
            Log.i("CoroutineFlowTest#3", "Calling collect again...")
            flow.collect{
                Log.i("CoroutineFlowTest#3", "" + it)
            }
        }

        //예제#4 flow cancel -> 기본적으로 협력적!!
        CoroutineScope(Dispatchers.Default).launch {
            withTimeoutOrNull(250) {
                simple4().collect{
                    Log.i("CoroutineFlowTest#4",  "" + it)
                }
            }
        }

        //예제#5 collections, sequences -> flow로 변환가능
        // - collections, sequences 정리는 나중에 한번 필요 할 것 같음

        CoroutineScope(Dispatchers.Default).launch {
            (1..3).asFlow().collect{
                Log.i("CoroutineFlowTest#5", "" + it)
            }
        }

        //예제#6 flow data 정제
        CoroutineScope(Dispatchers.Default).launch {
            (1..3).asFlow()
                .map {
                    performRequest(it)
                }
                .collect{
                    Log.i("CoroutineFlowTest#6", "" + it)
                }
        }

        //예제#7 flow data 정제 -> map, fillter 처럼 간단하게 그러나 복잡함을 원한다면 transform을 사용하여 정제
        CoroutineScope(Dispatchers.Default).launch {
            (1..3).asFlow()
                .transform<Int, String> {
                    emit("Making request $it")
                    emit(performRequest2(it))
                }.collect{
                    Log.i("CoroutineFlowTest#7", it)
                }
        }

       //예제#8 coroutine take -> coroutine 취소... Coroutine에서의 취소는 항상 Exception을 덤짐으로서 수행합니다.
        CoroutineScope(Dispatchers.Default).launch {
            number()
                .take(3) // -> emit의 count
                .collect{
                    Log.i("CoroutineFlowTest#8", "" + it)
                }
        }

        //예제#9 flow 종료 연산자...
        //flow의 연산자는 기본목적은 Data의 정제, Data 흐름 제어를 목적으로 두는 것 같음...
        //map, filter, transform... // toList(MutableList 변환), toSet(MutableSet 변환) // first(flow의 첫번째 값만 방출 나머지는 cancel) // fold, reduce...
        //fold -> 초기값을 입력받아...주어진 연산자를 이용하여 누적....
        //reduce -> 첫번째 값에 주어진 연산자를 이용하여 누적
        CoroutineScope(Dispatchers.Default).launch {
            val sum = (1..5).asFlow()
                .map { it * it }
                .reduce{a, b -> a + b}
            Log.i("CoroutineFlowTest#9", "" + sum)
        }

        CoroutineScope(Dispatchers.Default).launch {
            val sum = (1..5).asFlow()
                .map { it * it }
                .fold(10) { a, b -> //초기값에 다가 연산자에 의해 계속 누적...
                    a + b
                }
            Log.i("CoroutineFlowTest#9", "" + sum)
        }

        //Collet와 동작이 같음... 그러나 index 요소를 추가하여 index에 맞게 처리하고 싶을때 유용...
        CoroutineScope(Dispatchers.Default).launch { 
            (1..5).asFlow()
                .collectIndexed{ index, value ->
                    if(index == 1) Log.i("CoroutineFlowTest#9", "" + value)
                }
        }

        //예제#10 Coroutine folws는 순차적으로 실행됩니다. -> 기본적으로 위에서 아래로 순차적으로 실행되는것을 알 수 있음
        //대표적인 종료 연산자 : Collect ->  즉 값을 생산해 내는 것은 종료연산자 덕분인거 같음..
        CoroutineScope(Dispatchers.Default).launch {
            (1..5).asFlow()
                .filter {
                    Log.i("CoroutineFlowTest#10", "Filter $it")
                    it % 2 == 0
                }
                .map {
                    Log.i("CoroutineFlowTest#10", "Map $it")
                    "string $it"
                }.collect {
                    Log.i("CoroutineFlowTest#10", "Collect $it")
                }
        }


        //예제#11 flow Context.. -> flow collection은 CoroutineScope내에서 실행 그리고 이 CoroutineScope를 실행하는 Thread가 이 flow를 collection함...
        CoroutineScope(Dispatchers.Default).launch {

            withContext(Dispatchers.IO) {
                simple11().collect{
                    Log.i("CoroutineFlowTest#11", "Collected $it : ${Thread.currentThread().name}")
                }
            }

        }

        //예제#12 withContext를 사용한 잘못된 아이템 배출 -> flow의 빌더 안에 Context를 존중 보존... Context를 변경해서 아이템을 방출하는것은 허락하지 않음 그렇어떻게?  -> flowOn연산자를 사용하는것임.
//        CoroutineScope(Dispatchers.Default).launch {
//            simple12().collect{
//                Log.i("CoroutineFlowTest#12", "$it")
//            }
//        }
        //예제#13 flowOn연산자 -> CoroutineContext의 변경
        CoroutineScope(Dispatchers.Main).launch {
           simple13().collect{
               Log.i("CoroutineFlowTest#13", "$it : ${Thread.currentThread().name}")
           }

            for (k in 1..3) {
                Log.i("CoroutineFlowTest#13", "I'm not blocked $k")
                delay(100)
            }
        }

        //예제#14 버퍼링 -> 오래 걸리는 작업에 용이 더 정확하게는 수집하는 시간이 생산하는 시간보다 길때 용이...
        //즉 각각의 것을 각각 실행해서 수집하는 부분에서는 방출됨에 동시에 아이템을 받을때 용이... 즉 수집하는 부분에서 뭔가 막하고 방출됨에 동시에 아이템 받을 수 있음 Buffer을 사용한다면...
        CoroutineScope(Dispatchers.Default).launch {
            val time = measureTimeMillis {
                simple14().collect{
                    delay(300)
                    Log.i("CoroutineFlowTest#14", "$it")
                }
            }
            Log.i("CoroutineFlowTest#14", "$time ms")
        }

        //내일 여기 3개 다시보기..
        //해석...
        //->buffer연산자를 사용하면 아이템 방출과 생산을 동시에 실행함..
        //즉 방출하는데 걸리는 시간이 100, 생산하는데 걸리는 시간에 300 즉 생산을 하고 있을때(300 내에) 방출이 완료됨(100)에 따라 효율적으로 처리가 가능함..
        CoroutineScope(Dispatchers.Default).launch {
            val time = measureTimeMillis {
               simple14()
                   .buffer()
                   .collect{
                       delay(300) // 여기서 막 작업을해... 300초 걸렸어

                       Log.i("CoroutineFlowTest#14-2", "$it") //1000초씩 지남에 따라 방출된 값이 찍히는거... 그럼 3300 납득이 됨...
                   }
           }
            Log.i("CoroutineFlowTest#14-2", "$time ms")
        }
        //예제#15 병합, 합성 conflate
        CoroutineScope(Dispatchers.Default).launch {
            val time = measureTimeMillis {
                simple15()
                    .conflate()
                    .collect{
                        delay(300)
                        Log.i("CoroutineFlowTest#15", "$it")
                    }
            }
            Log.i("CoroutineFlowTest#15", "$time")
        }
        //예제#16 최근값 처리하기.. collectLatest
        CoroutineScope(Dispatchers.Default).launch {
            val time = measureTimeMillis {
                simple16().collectLatest {
                    Log.i("CoroutineFlowTest#16", "Collecting $it")
                    delay(300)
                    Log.i("CoroutineFlowTest#16", "Done $it")
                }
            }
            Log.i("CoroutineFlowTest#16", "$time ms")
        }


        //예제#17 flow zip -> 오래걸리는 작업을 중심으로 병합
        CoroutineScope(Dispatchers.Default).launch {
            val nums = (1..3).asFlow()
            val strs = flowOf("one", "two", "three")
            nums.zip(strs) { a, b ->
                "$a -> $b"
            }.collect{
                Log.i("CoroutineFlowTest#17", it)
            }
        }

        //예제#18 flow combine -> 오래걸리는 작업 순서 필요없이 결과 값이 나오는대로 출력
        CoroutineScope(Dispatchers.Default).launch {
            val nums = (1..3).asFlow().onEach { delay(300) }
            val strs = flowOf("one", "two", "three").onEach { delay(400) }
            val startTime = System.currentTimeMillis()
            nums.zip(strs) { a, b ->
                "$a -> $b"
            }.collect{
                Log.i("CoroutineFlowTest#18", "$it at ${System.currentTimeMillis() - startTime} ms from start")
            }
        }

        CoroutineScope(Dispatchers.Default).launch {
            val nums = (1..3).asFlow().onEach { delay(300) }
            val strs = flowOf("one", "two", "three").onEach { delay(400) }
            val startTime = System.currentTimeMillis()
            nums.combine(strs) { a, b ->
                "$a -> $b"
            }.collect{
                Log.i("CoroutineFlowTest#18-combine", "$it at ${System.currentTimeMillis() - startTime} ms from start")
            }
        }

        //예제#19 flow 평탄화 : flatMapConcat -> 복잡도를 풀어낸다.... Ex Flow<Flow<String>> -> Flow<String>
        CoroutineScope(Dispatchers.Default).launch {
            (1..3).asFlow()
                .map { requestFlow(it) }  // -> Flow<Flow<String>>
                .collect{

                }
        }


        CoroutineScope(Dispatchers.Default).launch {
            val startTime = System.currentTimeMillis()
            (1..3).asFlow().onEach { delay(100) }
                .flatMapConcat { requestFlow(it) }
                .collect{
                    Log.i("CoroutineFlowTest#19", "$it at ${System.currentTimeMillis() - startTime} ms from start")
                }
        }

        //예제#20 flow 평탄화 : flatMapMerge
        CoroutineScope(Dispatchers.Default).launch {
            val startTime = System.currentTimeMillis()
            (1..3).asFlow().onEach { delay(100) }
                .flatMapMerge { requestFlow(it) }
                .collect{
                    Log.i("CoroutineFlowTest#20", "$it at ${System.currentTimeMillis() - startTime} ms from start")
                }

        }


        //예제21 flow 평탄화 : flatMapLatest -> collectLatest와 비슷 최근 값을 반영함...
        CoroutineScope(Dispatchers.Default).launch {
            val startTime = System.currentTimeMillis()
            (1..3).asFlow().onEach { delay(100) }
                .flatMapLatest { requestFlow(it) }
                .collect {
                    Log.i("CoroutineFlowTest#21-common", "$it at ${System.currentTimeMillis() - startTime} ms from start")
                }
        }

        //예제21 flow 평탄화 : faltMapLatest -> collectLatest와 비슷 함 최신값을 던저주면 flow실행문 종료하는 원리 근데 취소할 시간 조차도 주어지지 않으면 그냥 collect동작이랑 비슷할것...
        CoroutineScope(Dispatchers.Default).launch {
            val startTime = System.currentTimeMillis()
            (1..3).asFlow().onEach { delay(100) }
                .flatMapLatest { requestFlow2(it) }
                .collect{
                    Log.i("CoroutineFlowTest#21-fast", "$it at ${System.currentTimeMillis() - startTime} ms from start")
                }
        }

        //예제22 flow 예외 처리.. -> Collect의 종료연산자에서의 예외를 잘 잡아낸당....
        CoroutineScope(Dispatchers.Default).launch {
            try {
                simple22().collect {
                    Log.i("CoroutineFlowTest#22", "" + it)
                    check(it <= 1) {
                        "Collected $it"
                    }
                }
            }catch (e : Throwable) {
                Log.i("CoroutineFlowTest#22", "Caught $e")
            }
        }

        //예제23 모든 예외처리
        CoroutineScope(Dispatchers.Default).launch {
            try {
                simple23().collect {
                    Log.i("CoroutineFlowTest#23", it)
                }
            }catch (e : Throwable) {
                Log.i("CoroutineFlowTest#23", "Caught $e")
            }
        }

        //에제#24 보기 좋게 예외 처리하기
        CoroutineScope(Dispatchers.Default).launch {
            simple24()
                .catch { e -> emit("Caught $e") }
                .collect{
                    Log.i("CoroutineFlowTest#24", it)
                }
        }


        //예제#25 보기 좋게 예외 처리하기 -> catch 중간 연산자는 오직 업스트립 exeption에만 대응이 가능함 .... 즉 위에서 아래로 처리는되는 흐름만 잡을 수 있다는 말씀입니다....!!
//        CoroutineScope(Dispatchers.Default).launch {
//            simple25()
//                .catch { e -> Log.i("CoroutineFlowTest#25", "Caught $e") }
//                .collect {
//                    check(it <= 1) { "Collected $it" }
//                    Log.i("CoroutineFlowTest#25", " " + it)
//                }
//        }

        //collect함수의 내부 내용을 onEach 중간 역할을 하는 친구로 이동시키고 catch를 통해서 명시적으로 예외 처리가 가능함 단 이 경우 collect 함수에 아무런 파라미터를 전달해서는 안된다.
        CoroutineScope(Dispatchers.Default).launch {
            simple26()
                .onEach {
                    check(it <= 1) {"Collected $it"}
                    Log.i("CoroutineFlowTest#26", "" + it)
                }
                .catch {e -> Log.i("CoroutineFlowTest#26", "Caught $e")}
                .collect()
        }






        //callBackFlow...
        //stateFlow
        //sharedFlow...

    }
    //예제#26
    fun simple26() : Flow<Int>  = flow {
        for (i in 1..3) {
            Log.i("CoroutineFlowTest#26", "Emitting $i")
            emit(i)
        }
    }

    //예제#25
    fun simple25() : Flow<Int>  = flow {
        for (i in 1..3) {
            Log.i("CoroutineFlowTest#25", "Emitting $i")
            emit(i)
        }
    }

    //예제#24
    fun simple24() : Flow<String> = flow {
        for (i in 1..3) {
            Log.i("CoroutineFlowTest#24", "Emitting $i")
            emit(i)
        }
    }.map {
        check(it <= 1) {"Crashed on $it"}
        "string $it"
    }

    //예제23
    fun simple23() : Flow<String> = flow {
        for (i in 1..3) {
            Log.i("CoroutineFlowTest#23", "Emitting $i")
            emit(i)
        }
    }.map {
        check(it <= 1) {"Crashed on $it"}
        "string $it"
    }

    //예제22
    fun simple22() : Flow<Int> = flow {
        for (i in 1..3) {
            Log.i("CoroutineFlowTest#22", "Emitting $i")
            emit(i)
        }
    }

    //예제 21
    fun requestFlow2(i : Int) : Flow<String> = flow {
        emit("$i : First")
        emit("$i : Second")
    }

    //예제19,20,21
    fun requestFlow(i : Int) : Flow<String> = flow {
        emit("$i : First")
        delay(500)
        emit("$i : Second")
    }




    //예제16
    fun simple16() : Flow<Int> = flow {
        for (i in 1..3) {
            delay(100)
            emit(i)
        }
    }
    //예제15
    fun simple15() : Flow<Int> = flow {
        for (i in 1..3) {
            delay(100)
            emit(i)
        }
    }

    //예제14
    fun simple14() : Flow<Int> = flow {
        for (i in 1..3) {
            delay(100)
            emit(i)
        }
    }
    //예제#13
    fun simple13() : Flow<Int> = flow {
        for (i in 1..3) {
            Thread.sleep(1000)
            Log.i("CoroutineFlowTest#13", "Emitting $i : ${Thread.currentThread().name}")
            emit(i)
        }
    }.flowOn(Dispatchers.Default)

    //예제#12
    fun simple12() : Flow<Int> = flow {
        withContext(Dispatchers.Main) {
            for (i in 1..3) {
                Thread.sleep(100)
                emit(i)
            }
        }
    }
    //예제#11
    fun simple11() : Flow<Int> = flow {
        Log.i("CoroutineFlowTest#11", "Started simple flow")
        for (i in 1..3) {
            emit(i)
        }
    }

    //예제#8
    fun number() : Flow<Int> = flow {
        try {
            emit(1)
            emit(2)
            Log.i("CoroutineFlowTest#8", "This line will not execute")
            emit(3)
        } finally {
            Log.i("CoroutineFlowTest#8", "Finally in numbers")
        }
    }

    //예제#7
    suspend fun performRequest2(request: Int) : String {
        delay(1000)
        return "response $request"
    }

    //예제#6
    suspend fun performRequest(request : Int) : String {
        delay(1000)
        return "response $request"
    }

    //예제#4
    fun simple4() : Flow<Int> = flow {
        for (i in 1..3) {
            delay(100)
            Log.i("CoroutineFlowTest#4", "Emitting $i")
            emit(i)
        }
    }

    //예제#3
    fun simple3() : Flow<Int> = flow {
        Log.i("CoroutineFlowTest#3", "Flow started")
        for (i in 1..3) {
            delay(100)
            emit(i)
        }
    }

    //예제#2
    fun simple2() : Flow<Int> = flow {
        for (i in 1..3) {
            delay(100)
            Log.i("CoroutineFlowTest#2", "Test")
            emit(i)
        }
    }

    //예제#1
    suspend fun simple() : List<Int> {
        delay(1000)
        return listOf(1, 2, 3)
    }
}