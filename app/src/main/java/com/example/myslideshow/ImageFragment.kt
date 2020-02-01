package com.example.myslideshow


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_image.*

/**
 * A simple [Fragment] subclass.
 */

val IMG_RES_ID = "IMG_RES_ID"

class ImageFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_image, container, false)
    }

    //コンパニオンオブジェクト宣言,javaでのスタティックメソッド
    companion object{
        //引数にこのフラグメントの内のImageViewに表示する画像のリソースIDを取る,返り値はこのフラグメントのインスタンス
        fun newInstance(imageResourceId: Int): ImageFragment{
            //アーギュメンツに保存するBundleクラスのインスタンスを生成
            val bundle = Bundle()
            //格納
            bundle.putInt(IMG_RES_ID, imageResourceId)
            //インスタンス生成後、アーギュメンツへの書き込み
            val imageFragment = ImageFragment()
            imageFragment.arguments = bundle
            return imageFragment
        }
    }

    //アーギュメンツから取り出した画像のリソースIDを保持する変数
    private var imgResId: Int? = null
    //Bundleから値を取り出す,フラグメントが作成、再作成されたときに呼び出される
    override fun onCreate(sacedInstanceState: Bundle?){
        super.onCreate(sacedInstanceState)
        //argumentsプロパティを利用してアーギュメンツからデータをnullチェックしつつ取り出し
        arguments?.let{
            imgResId = it.getInt(IMG_RES_ID)
        }
    }

    //onActivityCreatedはアクティビティのonCreateが完了した後に呼び出されるライフサイクルメソッド
    //フラグメントが作成されたらonActivityCreatedで画面の処理,
    //imgResIdに保持していた画像リソースIDよりImageViewに画像を表示する
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        //imgResIdに保持していた画像リソースIDでフラグメント内のImageViewに画像を設定
        imgResId?.let{
            imageView.setImageResource(it)
        }
    }


}
