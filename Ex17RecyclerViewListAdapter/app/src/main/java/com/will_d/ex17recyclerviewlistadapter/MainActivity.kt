package com.will_d.ex17recyclerviewlistadapter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.will_d.ex17recyclerviewlistadapter.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding : ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val dataSet = arrayListOf<Moster>().apply {
        add(Moster("타일런트", Race.Zombie, 10, listOf(100, 10, 50), false))
        add(Moster("조커", Race.Human, 23, listOf(200, 20, 100), false))
        add(Moster("그렘린", Race.Goblin, 2, listOf(10, 1, 5), true))
        add(Moster("리오레우스", Race.Dragon, 2500, listOf(10000, 1000, 50000), false))
        add(Moster("사우론", Race.Human, 100, listOf(1000, 200, 1000), false))
        add(Moster("리바이어던", Race.Dragon, 50, listOf(2000, 250, 10000), true))
    }

    private val myRecyclerViewAdapter : MyRecyclerViewAdapter by lazy {
        MyRecyclerViewAdapter()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        //번외 연산자 테스트

        Log.i("Calculation", "" + 1 / 2)
        //해석 : 1 / 2 => int / int ==> 결과도 int
        //1 / 2는 0.5 위의 연산원리에 의해서 자료형이 int기 때문에 뒤에꺼 짤리고 결과 0으로 출력

        Log.i("Calculation", "" + 4 % 3)
        //나머지가 소수점으로 표현되면 1로 처리...
        //해석 : 1 / 2 =

        var g = 5
        g += 10 //g = g + 10
        Log.i("Calculation", "g += 10 => " + g) //계속 다음것에 영향을 미치네..

        g -= 5  //g = g - 5 //즉 여기서 g는 15를 나타냄 위의것에 영향을 받아서!!!
        Log.i("Calculation", "g -= 5 => " + g)

        g *= 2  //g = g * 2
        Log.i("Calculation", "g *= 2 => " + g)

        g /= 2  //g = g / 2
        Log.i("Calculation", "g /= 2 => " + g)

        g %= 2  //g = g % 2
        Log.i("Calculation", "g %= 2 => " + g)

        g++  //g = g + 1
        Log.i("Calculation", "g++ => " + g)

        g--  //g = g - 1
        Log.i("Calculation", "g-- => " + g)


        binding.rvList.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
            adapter = myRecyclerViewAdapter

        }
        myRecyclerViewAdapter.submitList(dataSet)

        val itemTouchHelperCallback = ItemTouchHelper(MyItemTouchHelperCallback(binding.rvList))
        itemTouchHelperCallback.attachToRecyclerView(binding.rvList)

        binding.btn.setOnClickListener{
            myRecyclerViewAdapter.submitList(dataSet.shuffled())
        }
    }
}