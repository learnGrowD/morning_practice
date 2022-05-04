package com.will_d.ex19coroutineandthread

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        //동기 : 순차적으로 여러가지 작업을 수행
        //비동기 : 동시에 여러가지 작업을 수행

        //Process : 프로그램이 힙메모리에 적재되어 실행되는 인스턴스
        //Thread : Process내 실행되는 여러 흐름 단위

        //Concurrency(동시성) : 시분할 기법으로 동시에 여러가지 작업을 수행하는것 (내부적으로는 조금씩 2가지 작업을 쪼개어 번갈아 가면서 실행하는 것 )
        //Parallelism(병렬성) : 시분할 기법을 사용하지 않고 동시에 여러작업을 수행하는것(실제로 두 작업을 동시에 진행 단, 멀티코어 환경에서 가능하고 각 CPU는 각각의 작업을 담당)


        //Thread : 작업단위(Thread)[각 Thread는 Stack영역의 메모리를 할당 받기에 각 작업마다 Stack메모리 할당] OS에의한 Context switching으로 동시성을 보장함. Blocking...
        // - 여러작업이 필요한 경우 여러 Thread가 할당 받아진 상황에서 OS는 효율적인 작업을 위해 각 작업에 대해 자원(시간)을 얼마만큼 분배하여 사용할지 결정(Preempting scheduling) 그리고 Context Switching으로 동시성으 보장하여 두작업을 완료함..

        //Cocourine(LightWeight Thread) : 작업단위(Object)[Coroutine 객체는 객체이기에 Heap메모리에 생성된다.], Progremmer에 의한 Switching으로 동시성을 보장함.(OS관여 x, 프로그래머의 코드를 통해 Switching 시점을 마음대로 정할 수 있음.) None-Blocking....

        //특징
        // - Thread와 Coroutine 모두 동시성 프로그래밍을 보장하기 위한 기술
        // - Coroutine은 Thread의 대안이 아님 Thread를 더욱 잘개 쪼개어 사용하기 위한 개념....(쪼갠다는 것은 효율성 증가)
        // - Coroutine을 통해서 메모리 낭비 / 누수를 방지 할 수 있음
        // - Coroutine은 OS에 의한 Context-switching가 발생하지 않음 Switching 비용 절감 가능... 하나의 Thread로 Switching없이 여러 작업을 관장할 수 있으니 LightWeight Thread라고 불리는것임...
        // - 프로그래머가 Shared Thread pool 지정을 통해서 Coroutine의 효율성을 높일 수 있음... 즉 Coroutine의 효율성은 온전히 프로그래머의 몫임.... Thread > Coroutine[하나의 Thread에 여러 Coroutine(작업)이 있을 수 있고 이런 묶음이 여러개일 수 있다는 소리...]
        // - 해당 Process에 속한 Thread는 해당 Process에 속한 Heap메모리 영역을 공유 할 수 있어 Loking을 판단해야 했는데 코루틴은 Locking을 걱정하지 않아도됨...

        // - Coroutine
        // - stackful Corutine (Stack을 가지는 Coroutine)
        // - stackless Coroutine(Stack을 가지지 않는 Coroutine) 으로 나뉨... (나중에 필요하면 심화로 공부...)
    }
}