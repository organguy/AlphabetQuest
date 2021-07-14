package kr.co.organtech.alphabetquest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar!!.hide()
    }

    fun initView(){

    }

    fun gotoStep(view: View) {
        var intent = Intent(this, QuestActivity::class.java)
        var step = view.tag.toString().toInt()
        intent.putExtra("STEP", step)
        startActivity(intent)
    }
}