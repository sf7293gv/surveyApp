package com.example.surveyapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isVisible

class MainActivity : AppCompatActivity() {

    private lateinit var yesButton: Button
    private lateinit var noButton: Button
    private lateinit var question: TextView
    private lateinit var yesTextView: TextView
    private lateinit var noTextView: TextView

    private val questionsList = mutableListOf<String>()
    private var counter = 0
    private var yesCounter = 0
    private var noCounter = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        connectViewsWithIDs()
        addQuestionsToList()

        question.text = questionsList[0]

        yesButton.setOnClickListener {
            buttonJob(1)
        }

        noButton.setOnClickListener {
            buttonJob(0)
        }

    }

    /* This function when called will do the button's job, which is to update the question and the textviews or
     notify the user that the survey has ended */
    private fun buttonJob(yesOrNo: Int) {
        if (counter > questionsList.size - 1) {
            noButton.isVisible = false
            yesButton.isVisible = false
            question.isVisible = false
            Toast.makeText(this, getString(R.string.endOfSurvey), Toast.LENGTH_SHORT).show()
        } else {
            counter++
            if (yesOrNo == 1) {
                yesCounter++
            } else {
                noCounter++
            }
            updateQuestion()
            updateAnswerTextViews()
        }
    }

    // This function connects the variable with the buttons and textviews... from the design
    private fun connectViewsWithIDs() {
        yesButton = findViewById(R.id.yes_btn)
        noButton = findViewById(R.id.no_btn)
        question = findViewById(R.id.question_text)
        yesTextView = findViewById(R.id.yesText)
        noTextView = findViewById(R.id.noText)
    }

    // This functions adds questions to the list of questions
    private fun addQuestionsToList() {
        questionsList.add("Does pineapple belong on pizza?")
        questionsList.add("Do you want to go to space?")
        questionsList.add("Can you do a hand stand?")
        questionsList.add("Will the Vikings win the superbowl?")
    }

    // This function updates the question when called
    private fun updateQuestion() {
        if (counter < questionsList.size) {
            question.text = questionsList[counter]
        }
    }

    // This function updates the values of textviews
    private fun updateAnswerTextViews() {
        yesTextView.text = yesCounter.toString()
        noTextView.text = noCounter.toString()
    }

}