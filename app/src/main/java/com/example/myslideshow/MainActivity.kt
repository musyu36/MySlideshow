package com.example.myslideshow

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.concurrent.timer


class MainActivity : AppCompatActivity() {

    private lateinit var player: MediaPlayer

    //入れ子クラスにして,MyAdapterはMainActivity専用のクラスにする
    class MyAdapter(fm: FragmentManager): FragmentPagerAdapter(fm, FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT){
        private val resources = listOf(
            R.drawable.slide00, R.drawable.slide01,
            R.drawable.slide02, R.drawable.slide03,
            R.drawable.slide04, R.drawable.slide05,
            R.drawable.slide06, R.drawable.slide07,
            R.drawable.slide08, R.drawable.slide09
        )

        //FragmentPagerAdapterを継承したクラスはgetCount,getItemメソッドのオーバーライドが必須
        //総ページ数を返す
        override fun getCount(): Int{
            return resources.size
        }

        //引数にページ番号を受け取り、対応するフラグメント(ImageFragmentのインスタンス)を戻り値として返す
        override fun getItem(position: Int): Fragment {
            return ImageFragment.newInstance(resources[position])
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //ViewPagerとViewAdapterを紐付け
        pager.adapter = MyAdapter(supportFragmentManager)

        //タイマー処理
        val handler = Handler()
        //5秒に一回次のページを表示
        timer(period = 5000){
            handler.post{
                pager.currentItem = (pager.currentItem + 1) % 10
            }
        }

        //MadiaPlayerクラスのインスタンスを取得, create(activity, resourceId)
        player = MediaPlayer.create(this, R.raw.getdown)
        player.isLooping = true
    }

    //onResumeはアクティビティが画面表示されるときに呼び出される
    override fun onResume(){
        super.onResume()
        player.start()
    }

    //アプリが中断されてたときに一時停止
    override fun onPause(){
        super.onPause()
        player.pause()
    }
}
