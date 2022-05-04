package com.will_d.ex25coroutine

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {
    @SuppressLint("LongLogTag")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Coroutine 꿀팁...
        val job = Job() // --> 하위 Coroutine을 제어 할 수 있다.
        CoroutineScope(Dispatchers.IO + job).launch {

        }

        CoroutineScope(Dispatchers.Main + job).launch {

        }

        //만약 일부 Coroutine에 대해서 제어하고 싶다면 --> job2만 따로 제어 하도록 한다.
        val job2 = CoroutineScope(Dispatchers.Default).launch {

        }

        //Coroutine 실제 동작에 대해서 알아보자...

        //1. delay..
        CoroutineScope(Dispatchers.Default).launch {
           delay(2000L)
           Log.i("CoroutineTest#1", "Hello World")
        }


        //2. join...
        CoroutineScope(Dispatchers.Default).launch {
            launch {
                for (i in 0 until 5) Log.i("CoroutineTest#2", "Hello Coroutine ${i}")
            }.join()

            launch {
                for (i in 0 until 5) Log.i("CoroutineTest#2", "Hello Android ${i}")
            }
        }

        //3.coroutine Cancel... --> 너무 타이트한 동작은 Coroutine cancel이 동작하지 않을 수 있다. 코루틴 state를 자세히 들여다 보면 이해됨..
        val j = CoroutineScope(Dispatchers.Default).launch {
            for (i in 0 until 1000) {
                Log.i("CoroutineTest#3", "Hello Coroutine ${i}")
                delay(1000L)
            }
        }


        CoroutineScope(Dispatchers.Default).launch {
            delay(3000L)
            j.cancel()
        }


        //4. Coroutine Cancel... --> 부모 Coroutine을 취소하면 자식 Coroutine 모두 취소
        val j2 = CoroutineScope(Dispatchers.Default).launch {


           launch {
               for (i in 0 until 1000) {
                   Log.i("CoroutineTest#4", "Hello Coroutine ${i}")
                   delay(1000L)
               }
           }

            launch {
                for (i in 0 until 1000) {
                    Log.i("CoroutineTest#4", "Hello Android ${i}")
                    delay(1000L)
                }
            }

//            for (i in 0 until 1000) {
//                Log.i("CoroutineTest#4", "Hello Coroutine Parent ${i}")
//                delay(1000L)
//            }
        }

        CoroutineScope(Dispatchers.Default).launch {
            delay(3000L)
//            j2.cancelChildren() -> 자식만 취소
            j2.cancel() //-> 모두 취소
        }



        CoroutineScope(Dispatchers.Default).launch {

            val j =launch {
                for (i in 0 until 10) {
                    Log.i("CoroutineTest#4->cancelAndJoin", "Hello Coroutine ${i}")
                    delay(100000L)
                }
            }
                j.cancelAndJoin()


            launch {
                for (i in 0 until 10) {
                    Log.i("CoroutineTest#4->cancelAndJoin", "Hello Android ${i}")
                    delay(1000L)
                }
            }
        }

        CoroutineScope(Dispatchers.Default).launch {
            CoroutineScope(Dispatchers.Default).launch {
                for (i in 0 until 10) {
                    Log.i("CoroutineTest#5->이중CoroutineTest", "Hello 이중 CoroutineTest")
                    delay(1000L)
                }
            }

            launch {
                for (i in 0 until 10) {
                    Log.i("CoroutineTest#5->이중CoroutineTest", "Hello 어떻게 되는지 보자")
                    delay(1000L)
                }
            }
        }

        //5. withTimeOut, withTimeOutNull
        //기본적으로 CoroutineScope내에 withTimeOut, withTimeoutOrNull을 실행 할때는 뒤의 Coroutine 작업이 진행되지 않음.. 왜그런지는 모르겠음.
        CoroutineScope(Dispatchers.Main).launch {
            //실험결과
            //1. withTimeOut은 정해진 시간 내에 실행문이 종료가 되면 (cancel)이 되므로 같은 CoroutineScope내의 Coroutine작업 모두 종료
            //2. withTimeOut은 정해진시간을 넘기고 실행문을 모두 실행하고 값을 리턴하는 경우에는 취소가 발생하지 않음... 즉 같은 CoroutineScope내의 Coroutine작업이 종료되지 않고 그대로 진행된다는 말임..
            //3. 당연하지만 withTimeout보다 먼저 선행된 Coroutine작업은 withTimeOut과 동시에 진행 되다가 cancel이 발생하는 상황이 오면 실행을 취소함... 위의 launch 참고..
            launch {
                for (i in 0 until 10) {
                    Log.i("CoroutineTest#5->withTimeOut", "Hello first Coroutine...")
                    delay(1000L)
                }
            }
            val a = withTimeout(11000L) {
                for (i in 0 until 10) {
                    Log.i("CoroutineTest#5->withTimeOut", "Hello Coroutine withTimeOut")
                    delay(1000L)
                }
                "a" // 정해진 시간안에 실행문을 모두 끝내면 값을 리턴하고 만약 시간이 되어 실행문이 취소가 되는 상황이 아무것도 리턴하지 않음... 아무것도 리턴하지 않는다는것이 withTimeoutOrNull과의 차이점임...
                //이것도 결국은 취소의 개념이라 CoroutineScope내에 있는 Coroutine 모두를 취소 시킨다는 점 알아야 된다.
            }
            Log.i("CoroutineTest#5->withTimeOut", a.javaClass.name)

            launch {
                for (i in 0 until 10) {
                    Log.i("CoroutineTest#5->withTimeOut", "Hello second Coroutine...")
                    delay(1000L)
                }
            }
        }

        CoroutineScope(Dispatchers.Default).launch {
            //실험결과
            //withTimeoutOrNull은 정해진 시간내에 실행문이 종료가 되면 cancelAndJoin이 발동하므로 같은 CoroutineScope내의 작업을 그대로 진행함
            //withTimeoutOrNull이 정해진 시간을 넘어 실행문이 모두 완료되면 값을 리턴하고 그대로 진행...
            //당연하지만 withTimeout보다 먼저 선행된 Coroutine작업은 withTimeoutOrNull과 동시에 진행 되다가 WithTimeoutNull이 실행문을 null값으로 리턴하는 경우에도 이 선행된 Coroutine은 Cancel되지 않고 그대로 진행함...
            launch {
                for (i in 0 until 10) {
                    Log.i("CoroutineTest#5->withTimeoutOrNull", "Hello first Coroutine...")
                    delay(1000L)
                }
            }

            val result = withTimeoutOrNull(3000L) {
                for (i in 0 until 10) {
                    Log.i("CoroutineTest#5->withTimeoutOrNull", "Hello Coroutine withTimeoutOrNull")
                    delay(1000L)
                }
                "a"
                // 실행코드가 시간내에 모두 수행되면 이값을 result값으로 가지고 만약 실행 시간내에 코드가 모두 수행되지 못하면 이값을 반환하기 전에 끝내고 ruslt값으 null값을 가진다.
            }
            Log.i("CoroutineTest#5->withTimeoutOrNull", "Result is $result")

            launch {
                for (i in 0 until 10) {
                    Log.i("CoroutineTest#5->withTimeoutOrNull", "Hello second Coroutine....")
                    delay(1000L)
                }
            }
        }

        CoroutineScope(Dispatchers.Main).launch {
            for (i in 0 until 50) {
                Log.i("CoroutineTest#5", "메인 Thread의 동작은 어떻게 되는지 보고싶음...")
                delay(1000L)
            }

        }

        //async Test, await test....
        //실험 결과 async는 결과 값을 리턴할 수 있다 어떻게? await으로...
        //만약 await을 통해 결과 같을 받는 다고 하면 join과 같은 상황일 발생한다.
        //즉 1번 Coroutine을 await을 실행시키면 이 실행문의 결과 값을 리턴 받기 전까지 다른 Coroutine들을 기다렸다가.. 이후에 아래의 Coroutine들을 작업함...
        //즉 join과 같은 상황이 발생한다는 거지...
        CoroutineScope(Dispatchers.IO).launch {
            val a = async {
                var s : String = "Async Task"
                for (i in 0 until 5) {
                    s += i
                    Log.i("CoroutineTest#6", s)
                    delay(1000L)
                }
                s
            }

            launch {
                for (i in 0 until 5) {
                    Log.i("CoroutineTest#6->await", "Hello Await")
                    delay(1000L)
                }
//                Log.i("CoroutineTest#6->await", a)
            }
        }

        val jobb3 = CoroutineScope(Dispatchers.IO).async {
            (1..10).sortedByDescending { it }
        }

        val jobb1 = CoroutineScope(Dispatchers.Main).launch {
            print(1)
            val job3Result = jobb3.await()

        }

        val jobb2 = CoroutineScope(Dispatchers.Main).launch {
            print("job2 수행완료")
        }





        //android coroutine
        //1. runBlocking -> MainThread blocking하는 친구 -> 이친구 사용 자제
        //2. GlobalScope -> 이 친구 사용 자제 , Coroutine Scope 사용...

        //coroutine
        //1. delay..(멈췄다가 실행해...)
        //2. join..(순서대로)
        //3. cancel(부모, 자식 코루틴을 모두 취소), cancelAndJoin(현재 coroutine에게 종류하라고 신호를 보내고, 정상 종료할때까지 대기), cancelChildern(부모 코루틴을 제외한 자식 코루틴만 종료), withTimeOut(Exeption), withTimeOutNull(null반환)

        //1. launch... (return job)
        //2. async... (return deferred -> job 인데 결과를 return할 수 있는 Job) //await..(like join)
        // start : 준비 중 준비 완료 -> false, 동작중 -> true


        //withContext...(Thread change...)
        //Coroutine state.. ->
        //1. New(생성 중)
        //2. Active(실행 중)
        //3. Completing(완료 중)
        //4. Completed(완료)
        //5. Cancelling(취소 중)
        //6. Cancelled(취소)

        //isActivie
        //isCompleted
        //isCancelled --> 상태를 확인 할 수 있다....




        //suspend(중지하다) fun에 대해서 공부... ->멈추고 시작할 수 있는 함수...
        //Coroutine의 Detail한 작업 cancel, delay, join, wait.. 등 Coroutine에 대한 Detail한 제어를 가능하게 하는 비동기 함수....
        //Coroutine


//        val job = CoroutineScope(Dispatchers.Main).launch {
//            withContext()
//            delay()
//            withTimeout()
//            withTimeoutOrNull()
//            launch {
//
//            }
//
//            async {
//
//            }
//
//        }

//        val deferrd = CoroutineScope(Dispatchers.Main).async {
//
//        }
//
//        job.cancel()
//        job.cancelAndJoin()
//        job.join()
//        job.start()
//
//        deferrd.cancel()
//        deferrd.cancelAndJoin()
//        deferrd.join()
//        deferrd.start()
//        deferrd.await()
//
//        //coroutine state
//        job.isActive
//        job.isCancelled
//        job.isCompleted
//        deferrd.isActive
//        deferrd.isCancelled
//        deferrd.isCompleted

        //coroutine Exeption



    CoroutineScope(Dispatchers.Main).launch {

        aa()
        bb()

    }


    }

    suspend fun aa() {
        for (i in 0 until 10) {
            Log.d("suspendFunctionTest", "Hello SuspendFunction#1")
            delay(1000L)
        }
    }

     suspend fun bb() {
        for (i in 0 until 10) {
            Log.d("suspendFunctionTest", "Hello SuspendFunction#2")
            delay(1000L)
        }
    }



}