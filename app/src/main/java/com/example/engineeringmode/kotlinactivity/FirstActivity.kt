package com.example.engineeringmode.kotlinactivity

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.engineeringmode.R
import com.example.engineeringmode.kotlinactivity.other.CustomActivityResultContract
import kotlinx.android.synthetic.main.activity_main2.*

/**
 * @author ljt
 * Date: 4/25/21
 * Time: 9:50 AM
 * Description:
 */
class FirstActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(R.layout.activity_main2)

        button.setOnClickListener {
            //点击跳转到SecondActivity
//            activityLauncher.launch("我是第一个页面传过去的参数")

            //点击跳转到SecondActivity 原生的activityResultLauncher
//            val intent = Intent(this, SecondActivity::class.java)
//            intent.putExtra("name", "我是第一个页面传过去的参数")
//            activityResultLauncher.launch(intent)

            //发送数据
            val  intent = Intent(this, SecondActivity::class.java).apply {
                putExtra("test","request")
            }
            activityResultLauncher.launch(intent)
        }




    }

    //在A activity中注册ActivityResult协议，使用我们刚刚定义的CustomActivityResultContract。
    //registerForActivityResult方法有两个参数，第一个参数是传入对应的Contract，第二个参数是回调结果callback。
    private val activityLauncher = registerForActivityResult(CustomActivityResultContract()) {
        //第二个页面关闭后回到第一个页面的回调方法
        tv2.text = it
    }

    private val activityResultLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                if (it.resultCode == RESULT_OK) {
                    val result = intent?.getStringExtra("result")
                }
            }
}