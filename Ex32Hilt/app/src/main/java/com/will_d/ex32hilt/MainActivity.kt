package com.will_d.ex32hilt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.scopes.ActivityScoped
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject lateinit var myViewModel : MyViewModel
    @Inject lateinit var pref : PrefDataStoreManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        lifecycleScope.launch{
            pref.bbb()
        }

        findViewById<Button>(R.id.btn).setOnClickListener {
            lifecycleScope.launch {
                pref.aa.collect {
                    Toast.makeText(this@MainActivity, "" + it, Toast.LENGTH_SHORT).show()
                }
            }
        }



        //1. Application에 @HiltAndroidApp을 붙여준다
        // - Hilt가 Appication의 생명주기를 바탕으로 생성 소멸된다
        // - 이 앱에 Hilt를 사용하여 의존성을 주입하겠다는것을 알리는 것이다. Hilt는 의존성을 주입할 Component(container)들을 이 시점에 준비한다고 한다.
        // - android manifests의 application에 application을 상속해야 한다.
        // 이렇게 하면 의존성을 주입할 준비는 끝나는 것이다.

        // -> @AndroidEntyPoint 어노테이션을 통해서 의존성을 주입할 container을 정의한다.
        // -> 이 어노테이션을 사용하면 각 Component(Fragment, Activity, BroadCast, Service, View)에 걸맞는 Container을 생성된다. 그리고 이 Container는 이에 근간하여 생명주기를 따른다.
        // -> 위의 Component가 아닌 새로운 Component를 생성하고 싶다면 어떻게 해야 할까? -> 개발자가 정의하면 된다.
        // Hilt Di라이브러리가 좋은 이유는 Dagger2를 Android에 최적화 시켜줬다는 점, 생명주기를 관리해 준다는데 있다. 만약 사용자가 정의한다면 생명주기를 잘 설계해야 된다.

        // android의 구성요소들 Activity, Fragment, Viewmodel은 다른 class에 의존성이 강한 친구들이다 이렇게 의존성이 강하다는 것은 유연한 설계, 상황대처가 힘들다는 말이된다. 즉 이를 해소 해야 된다
        // 이러한 해소 방법 중 하나가 Hilt를 통한 의존성을 주입하는것이다 각 component에 대한 의존성이 낮아지면 (즉, 생성자, Setter에 파라미터를 전달하는 행위, 관리가 수월해지면) 더욱 유연하게 class간의 관계를 설계하고 더욱 유연하게 특정 상황에 대해서
        // 대처가 가능할것이라고 기대한다.

        //개발자가 정의하는 class -> 그냥 Inject시켜주면 된다.
        //interface -> 당연하게도 Hilt가 어떠한 규격으로 객체를 만들어야 되는지 알지 못한다. 그러기에 개발자가 어떻게 만들어야 되는지에 대해서 Hilt에게 정의를 내려주는 것은 당연하겠다 이는 Module로 구현한다 Module Provide Binds...
        //제 3의 class //Retrofit, DataBase, Builder pattern의 경우도 Hilt가 알지 못한다. 그러기에 개발자가 알려줘야 된다. Modulde Provide...

        //1. interface에 대한 의존성을 어떻게 주입할 것인가.
        //2. 제 3의 class : Retrofit, DataBase, Builder Pattern으로 만들어지는 class에 대해서 Hilt를 통해 어떻게 의존성을 주입할 것인가
        //3. Object와 같은 class는 어떻게 의존성을 주입하는것이 좋을까? -> Object class는 Singletone Pattern을 쉽게 구현해주는 class이다.. Module을 에서 @Singletone anotation을 사용하면 되지 않을까? 하지만 Object에 대해서 의존성을 주입하고 싶은 상황이
        //있을 수 있으니 이 부분에 대해서도 잘 알아두는것이 중요할거 같다...
        //4. DataStore와 같은 객체에 대해서 의존성을 주입해보자....

    }
}