package com.example.surveyapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.view.isVisible

const val YES_COUNTER = "com.example.surveyapp.yes_counter"
const val NO_COUNTER = "com.example.surveyapp.no_counter"


class MainActivity : AppCompatActivity() {

    private lateinit var yesButton: Button
    private lateinit var noButton: Button
    private lateinit var resultButton: Button
    private lateinit var question: TextView

    private val questionsList = mutableListOf<String>()
    private var counter = 0
    private var yesCounter = 0
    private var noCounter = 0

    private val resultActivityLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        result -> retakingSurvey(result)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        connectViewsWithIDs()
        addQuestionsToList()
//        updateAnswerTextViews()

        question.text = questionsList[0]

        yesButton.setOnClickListener {
            buttonJob(1)
        }

        noButton.setOnClickListener {
            buttonJob(0)
        }

        resultButton.setOnClickListener {
//            resetAll()
            showResultActivity()
        }

    }

    // This function will launch the results activity
    private fun showResultActivity() {
        val showResultsActivity = Intent(this, SurveyResultActivity::class.java)
        showResultsActivity.putExtra(YES_COUNTER, yesCounter)
        showResultsActivity.putExtra(NO_COUNTER, noCounter)
//        startActivity(showResultsActivity)
        resultActivityLauncher.launch(showResultsActivity)
    }

    /* This function when called will do the button's job, which is to update the question and the textviews or
     notify the user that the survey has ended */
    private fun buttonJob(yesOrNo: Int) {
        if (counter > questionsList.size - 1) {
            visibilityFun(0)
            Toast.makeText(this, getString(R.string.endOfSurvey), Toast.LENGTH_SHORT).show()
            resultButton.isVisible = true

        } else {
            counter++
            if (yesOrNo == 1) {
                yesCounter++
            } else {
                noCounter++
            }
            updateQuestion()
//            updateAnswerTextViews()
        }
    }

    // When this function is called it will set the visibility of these variable to true (if passed 1) or false (else)
    private fun visibilityFun(tOrF: Int) {
        if (tOrF == 1) {
            noButton.isVisible = true
            yesButton.isVisible = true
            question.isVisible = true
            resultButton.isVisible = false
        } else {
            noButton.isVisible = false
            yesButton.isVisible = false
            question.isVisible = false
        }
    }

    // This function connects the variable with the buttons and textviews... from the design
    private fun connectViewsWithIDs() {
        yesButton = findViewById(R.id.yes_btn)
        noButton = findViewById(R.id.no_btn)
        question = findViewById(R.id.question_text)
        resultButton = findViewById(R.id.result_btn)
    }

    // This function will reset all the counters and textviews when called
    private fun resetAll() {
        counter = 0
        yesCounter = 0
        noCounter = 0
        question.text = questionsList[0]
//        updateAnswerTextViews()
        visibilityFun(1)
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

    // This function will reset the survey when the app comes back from the results activity
    private fun retakingSurvey(result: ActivityResult) {
        if (result.resultCode == RESULT_OK) {
            val intent = result.data
            val mustBeTrueToRest = intent?.getBooleanExtra(GO_BACK_TO_SURVEY, true)
            if (mustBeTrueToRest == true) {
                resetAll()
            }
        }
    }



}