package com.example.engineeringmode

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main2.*

/**
 * @author ljt
 * Date: 4/25/21
 * Time: 10:02 AM
 * Description:
 */
class SecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(R.layout.activity_main3)

        //接收数据
        val test = intent.getStringExtra("test")
        tv2.text = test


        //点击回传  FirstActivity
        button.setOnClickListener {
            val intent = Intent().apply {
                putExtra("result","carsonho_response")
            }
            setResult(Activity.RESULT_OK,intent)
            finish()
        }

    }

}