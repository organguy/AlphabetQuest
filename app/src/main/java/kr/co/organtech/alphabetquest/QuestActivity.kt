package kr.co.organtech.alphabetquest

import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_quest.*
import java.util.*
import kotlin.collections.ArrayList

class QuestActivity : AppCompatActivity() {

    var step = 0

    var wordList = ArrayList<WordData>()
    var wordData : WordData? = null
    var tts : TextToSpeech? = null
    var count = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quest)

        initData()
        initView()
        initTTS()

        supportActionBar!!.hide()
    }

    private fun initData(){

        step = intent.getIntExtra("STEP", 0);

        var wordArr: Array<String>? = null

        if(step == 1){
            wordArr = resources.getStringArray(R.array.word_list_step_1)

            for(word in wordArr!!){
                var wordData = WordData()
                wordData.word = word

                wordList.add(wordData)
            }
        }else if(step == 2){
            wordArr = resources.getStringArray(R.array.word_list_step_1)

            for(word in wordArr!!){
                var wordData = WordData()
                wordData.word = word.lowercase(Locale.getDefault())
                wordList.add(wordData)
            }
        }else if(step == 3){
            wordArr = resources.getStringArray(R.array.word_list_step_1)

            for(word in wordArr!!){
                val random = Random()
                var randomNum = random.nextInt(2)

                var wordData = WordData()

                if(randomNum == 0){
                    wordData.word = word.lowercase(Locale.getDefault())
                }else{
                    wordData.word = word
                }

                wordList.add(wordData)
            }
        }
    }

    private fun initView(){
        tvTitle.text = String.format("%d단계", step)
        setRandomWord()
    }

    private fun initTTS() {
        tts = TextToSpeech(this) {
            tts!!.language = Locale.ENGLISH
            tts!!.setPitch(2.0f)
            tts!!.setSpeechRate(0.5f)
        }
    }

    private fun setRandomWord(){
        val random = Random()
        var randomNum = random.nextInt(wordList.size)

        wordData = wordList[randomNum]

        if(wordData!!.isCorrect){
            setRandomWord()
        }else{
            tvWord.text = wordData!!.word
            wordData!!.isCorrect = true

            var solvedCount = String.format("%d / %d", ++count, wordList.size)
            btNext.text = solvedCount
        }
    }

    fun onClickBack(view: View) {
        finish()
    }

    fun onClickSound(view: View) {
        tts!!.speak(wordData!!.word, TextToSpeech.QUEUE_FLUSH, null, null)
    }

    fun onClickNext(view: View) {
        if(count == (wordList.size)){
            Toast.makeText(this, "모든 문제를 풀었습니다.", Toast.LENGTH_SHORT).show()
        }else{
            setRandomWord()
        }
    }
}