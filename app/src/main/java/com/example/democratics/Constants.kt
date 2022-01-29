package com.example.democratics

object Constants{

    const val USER_NAME: String = "user_name"
    const val TOTAL_QUESTIONS: String = "total_questions"
    const val CORRECT_ANSWERS: String = "correct_answers"

    fun getQUestions (): ArrayList<Question>{

        var questionList = ArrayList<Question>()

        val que1 = Question(1 ,
            "Who was the Chairman of the Drafting Committee?",
            "Dr. B. R. Ambedkar" ,
            "Dr. Sachchidananda Sinha" ,
            "Dr. Rajendra Prasad" ,
            1)

        questionList.add(que1)

        val que2 = Question(2 ,
            "Indian Constitution was adopted by the Government of India on",
            "1947" ,
            "1949" ,
            "1950" ,
            2)

        questionList.add(que2)

        val que3 = Question(3 ,
            "What country does this flag belongs to?Which Articel lays down the procedure for impeachment of the President?",
            "Article 61" ,
            "Article 15" ,
            "Article 20" ,
            1)

        questionList.add(que3)


        return  questionList
    }
}