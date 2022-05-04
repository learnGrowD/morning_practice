package com.will_d.ex03livedata

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    //dataBinding, LiveData, ViewModel, LifeCycles은 같이 알아야 되는 개념!
    //LiveDate의 장점

    //1. 데이터와 UI 상태 일치 보장
    // - LiveData는 데이터가 변경되면 Observer객체에게 알려주고 Obsever객체는 알림 받을때 마다 UI를 데이터와 일치하게 업데이트를 하기때문에 데이터와 UI 일치를 보장

    //2. LiveData 특징
    // - Observer객체는 Activity, Fragment의 수명주기를 따른다.
    // - Activity와 Fragment의 생명주기가 끝나면 Observer도 메모리상에 자동으로 삭제된다 : 메모리 누수가 없다.
    // - Activity와 Fragment가 비활성화 상태가 되면 (백스텍에 있을때) Observer 객체도 비활성화 상태가 되고 LiveData의 어떠한 이벤트도 수신하지 않는다 : 수명주기에 따른 데이터 관리를 개발자가 하지 않아도 된다.
    // - Activity와 Fragment가 비활성화 상태에서 활성상태로 되돌아 올때 최신데이터를 수신한다. : 활성상태 STARTED, RESUMED, 비활성 상태 : PAUSED, STOPED
    // - Observer는 Activity와 Fragment의 수명 주기 상태를 인식한다 이를 통해 UI구성요소의 데이터를 관찰만 할 수 있고 중지하거나 다시 재시작을 하지않는다.

    // 3. 리소스 공유
    // - LiveData가 연결된 모든 곳에서 (모든 Observer)가 LiveData를 관찰 할 수 있다. 이 말뜻은 데이터를 공유 할 수 있다는 소리이다. : 데이터, 이벤트 공유 가능!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}