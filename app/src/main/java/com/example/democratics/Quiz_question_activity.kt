package com.example.democratics

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_quiz_question.*

class Quiz_question_activity : AppCompatActivity() {
    private var mCurrentPosition: Int = 1 // Default and the first question position
    private var mQuestionsList: ArrayList<Question>? = null

    private var mSelectedOptionPosition: Int = 0
    private var mCorrectAnswers: Int = 0
    private var mUserName: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_question)
        Constants.getQUestions()
        mUserName = intent.getStringExtra(Constants.USER_NAME)

        mQuestionsList = Constants.getQUestions()

        setQuestion()

        option_1.setOnClickListener(){selectedOptionView(option_1, 1)}
        option_2.setOnClickListener(){selectedOptionView(option_2, 2)}
        option_3.setOnClickListener(){selectedOptionView(option_3, 3)}
        submit.setOnClickListener(){
            if(mSelectedOptionPosition != 0){
                val questionMarks = mQuestionsList!![mCurrentPosition-1]
                if(mSelectedOptionPosition != questionMarks.correctAnswer){
                    setColor(mSelectedOptionPosition , R.drawable.wrong_option)
                }else {
                    mCorrectAnswers++
                    setColor(questionMarks.correctAnswer, R.drawable.correct_option)
                }
                if(mCurrentPosition == mQuestionsList!!.size)
                    submit.text = "FINISH"
                else
                    submit.text = "GO TO NEXT"
            }else{
                mCurrentPosition++
                when{
                    mCurrentPosition <= mQuestionsList!!.size ->{
                        setQuestion()
                    }
                    else->{
                        val intent = Intent(this , ResultActivity::class.java)
                        intent.putExtra(Constants.USER_NAME , mUserName)
                        intent.putExtra(Constants.CORRECT_ANSWERS , mCorrectAnswers)
                        intent.putExtra(Constants.TOTAL_QUESTIONS , mQuestionsList!!.size)
                        startActivity(intent)
                        finish()
                    }
                }
            }
            mSelectedOptionPosition = 0
        }
    }
    fun setColor(opt: Int , drawableView: Int){
        when(opt){
            1-> {
                option_1.background = ContextCompat.getDrawable(this, drawableView)
            }
            2->{
                option_2.background = ContextCompat.getDrawable(this , drawableView)
            }
            3->{
                option_3.background = ContextCompat.getDrawable(this, drawableView)
            }
        }
    }



    private fun setQuestion() {

        val question =
            mQuestionsList!![mCurrentPosition - 1]

        defaultOptionsView()


        if (mCurrentPosition == mQuestionsList!!.size) {
            submit.text = "FINISH"
        } else {
            submit.text = "SUBMIT"
        }

        progress_bar.max = 3

        progress_bar.progress = mCurrentPosition
        score.text = "$mCurrentPosition" + "/" + progress_bar.getMax()

        question_1.text = question.question
        progress_bar.max = 3
        option_1.text = question.opt1
        option_2.text = question.opt2
        option_3.text = question.opt3

    }

    private fun selectedOptionView(tv: TextView, selectedOptionNum: Int) {

        defaultOptionsView()

        mSelectedOptionPosition = selectedOptionNum

        tv.setTextColor(
            Color.parseColor("#363A43")
        )
        tv.setTypeface(tv.typeface, Typeface.BOLD)
        tv.background = ContextCompat.getDrawable(
            this,
            R.drawable.select_bg
        )
    }


    private fun defaultOptionsView() {

        val options = ArrayList<TextView>()
        options.add(0, option_1)
        options.add(1, option_2)
        options.add(2, option_3)


        for (option in options) {
            option.setTextColor(Color.parseColor("#7A8089"))
            option.typeface = Typeface.DEFAULT
            option.background = ContextCompat.getDrawable(
                this,
                R.drawable.option_bg
            )
        }
    }


    private fun answerView(answer: Int, drawableView: Int) {

        when (answer) {
            1 -> {
                option_1.background = ContextCompat.getDrawable(
                    this,
                    drawableView
                )
            }
            2 -> {
                option_2.background = ContextCompat.getDrawable(
                    this,
                    drawableView
                )
            }
            3 -> {
                option_3.background = ContextCompat.getDrawable(
                    this,
                    drawableView
                )
            }

        }
    }
}