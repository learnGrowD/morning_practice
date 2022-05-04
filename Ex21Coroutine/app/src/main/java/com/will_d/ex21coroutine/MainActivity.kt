package com.will_d.ex21coroutine

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Coroutine을 job이라 한다면 Thread는 작업 공간...
        //하나의 Thread에서 여러 Coroutine(작업)을 실행 시킬 수 있다...

        //Coroutine Scope 종류
        //CoroutineScope란 : 코루틴의 범위, 코루틴 블록을 제어할 수 있는 객체
        //1. CoroutineScope
        //2. GlobalScope
        //3. LifeCyclerScope
        //4. ViewModelScope
        //5. LiveDataScope

        //CoroutineContext 종류
        //CoroutineContext란 : 코루틴을 어떻게 처리할지에 대한 정보(job, Dispatcher : 어디로 코루틴을 보낼지...)를 가지고 있는 객체
        //Dispatcher란 :
        //1. 코루틴을 받은 Dispatcher는 해당 코루틴을 어떤 쓰레드에 실행시킬지 배분 및 결정을 하는 객체, CoroutineThreadPool을 관리하는 객체...
        //2. Dispatcher는 CoroutineContext의 일부...
        //Dispatcher의 종류
        //1. Main : UI에 관련된 작업 : Toast, View
        //2. Defualt : CPU연산이 많은 작업에 용이 : Json parsing, 배열 정렬 작업...
        //3. IO : 네트워크, DB작업에 용이...


        //CoroutineBuilder 종류
        //CoroutineBuilder란 : Coroutine의 블록을 어떻게 만들지를 결정하는 객체..
        //1. launch -> job객체 반환
        //2. async -> defferd 객체 반환(마지막 값 return)

        //-> CoroutineContext로 CoroutineScope를 만들고 CoroutineBuilder를 통해 Coroutine을 실행 시킨다.


        //앱이 실행될 때부터 앱이 종료될때까지 코루틴을 실행 시킬 수 있는 Scope -> application의 생명주기를 따름
        //앱의 시작 종료에 관련된 작업에 용이
        //Android에서 GlobalScope 사용을 권장하지 않음.
        val globaljob = GlobalScope.launch {

        }



        //기본적으로 코루틴을 사용하는 방식...
        //내가 생각하기에 이 기본방식은 생명주기를 인식하는것 같지는 않음.. 그러니까 LifeCyclerScope, ViewModelScope, LiveDataScope가 생긴거겠지!?
        val job = CoroutineScope(Dispatchers.Main).launch {

            //launch는 코루틴을 리턴
        }


        val job2 = CoroutineScope(Dispatchers.Default).launch {

        }

        val differd = CoroutineScope(Dispatchers.IO).async {

        }


        //Coroutine State
        //Coroutine (launch)
        //Coroutine 시작 : start
        //Coroutine 순서 : join
        //Coroutine 지연 : (delay, await)
        //Coroutine 취소 : cancel, cancelAndJoin, (withTimeOut, withTimeOutNull)


        //Coroutine State Cycler
        //생성(new) : job이 생성됨
        //실행 중(Active) : job이 실행 중
        //실행 완료(Completed) : job이 실행 완료
        //취소 중(Cancelling) : job이 취소 중
        //취소 완료(Cancelled) : job이 취소 완료


        //Coroutine 예제만들어보기.....

//        Log.i("qwqf", k.await())



    }
    val a = CoroutineScope(Dispatchers.Main)
    val k = a.async {
        Toast.makeText(this@MainActivity, "qwdqwf", Toast.LENGTH_SHORT).show()
        "HelloWorld"
    }
}