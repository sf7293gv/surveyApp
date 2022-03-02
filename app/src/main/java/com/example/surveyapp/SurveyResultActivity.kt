package com.example.surveyapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.core.view.isVisible

const val GO_BACK_TO_SURVEY = "com.example.surveyapp.go_back"

class SurveyResultActivity : AppCompatActivity() {

    private lateinit var yesTextView: TextView
    private lateinit var noTextView: TextView
    private lateinit var resetButton: Button
    private lateinit var retakeSurveyButton: Button
    private var yesCounter = 0
    private var noCounter = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_survey_result)


        yesCounter = intent.getIntExtra(YES_COUNTER, 100)
        noCounter = intent.getIntExtra(NO_COUNTER, 100)
        connectViewsWithIDs()
        updateAnswerTextViews()

        resetButton.setOnClickListener {
            resetAll()
        }

        retakeSurveyButton.setOnClickListener {
            goBack()
        }

    }

    private fun resetAll() {
        yesCounter = 0
        noCounter = 0
        updateAnswerTextViews()
        retakeSurveyButton.isVisible = true
    }

    // This function updates the values of textviews
    private fun updateAnswerTextViews() {
        yesTextView.text = yesCounter.toString()
        noTextView.text = noCounter.toString()
    }

    private fun connectViewsWithIDs() {
        yesTextView = findViewById(R.id.yesText)
        noTextView = findViewById(R.id.noText)
        resetButton = findViewById(R.id.reset_button)
        retakeSurveyButton = findViewById(R.id.back2survey_btn)
    }

    private fun goBack() {
        val goBackToSurveyIntent = Intent()
        goBackToSurveyIntent.putExtra(GO_BACK_TO_SURVEY, true)
        setResult(RESULT_OK, goBackToSurveyIntent)
        finish()
    }
}