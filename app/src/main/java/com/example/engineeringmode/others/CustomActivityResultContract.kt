package com.example.engineeringmode.others

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.activity.result.contract.ActivityResultContract
import com.example.engineeringmode.MainActivity2

/**
 * @author ljt
 * Date: 4/25/21
 * Time: 9:18 AM
 * Description: Activity Results API 。
 */

//输入的类型，输出的类型
class CustomActivityResultContract : ActivityResultContract<String, String>() {

    override fun createIntent(context: Context, input: String?): Intent {
        return Intent(context, MainActivity2::class.java).apply {
            putExtra("test", input)
        }
    }

    override fun parseResult(resultCode: Int, intent: Intent?): String? {
        val data = intent?.getStringExtra("result")
        return if (resultCode == Activity.RESULT_OK && data != null) data
        else null
    }
}