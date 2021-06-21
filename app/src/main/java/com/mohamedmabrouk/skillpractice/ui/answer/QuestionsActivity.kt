package com.mohamedmabrouk.skillpractice.ui.answer

import android.app.AlertDialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import android.view.Window
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mohamedmabrouk.skillpractice.R
import com.mohamedmabrouk.skillpractice.model.Answer
import kotlinx.android.synthetic.main.activity_questions.*

class QuestionsActivity : AppCompatActivity(), AnswerClickListener, View.OnClickListener {

    private lateinit var answerRecyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_questions)

        answerRecyclerView = findViewById(R.id.rv_answer)
        answerRecyclerView.layoutManager = LinearLayoutManager(this)

        val tempAnswerList = ArrayList<Answer>()
        tempAnswerList.add(Answer(0, "A. English"))
        tempAnswerList.add(Answer(0, "B. English"))
        tempAnswerList.add(Answer(0, "C. English"))
        tempAnswerList.add(Answer(0, "D. English"))

        answerRecyclerView.adapter = AnswerListAdapter(tempAnswerList, this)

        iv_next.setOnClickListener(this)
    }

    override fun onItemClick(position: Int) {
        Toast.makeText(this, position.toString(), Toast.LENGTH_SHORT).show()
    }

    fun showScorePopup() {
        val dialogBuilder = AlertDialog.Builder(this).create()
        val inflater = this.layoutInflater
        val dialogView: View = inflater.inflate(R.layout.popup_score, null)

        dialogBuilder.window!!.setBackgroundDrawableResource(android.R.color.transparent)

        val closeBtn = dialogView.findViewById<ImageView>(R.id.iv_close)
        val noQuestionsTv  = dialogView.findViewById<TextView>(R.id.tv_no_questions)
        val attemptedQuestionsTv = dialogView.findViewById<TextView>(R.id.tv_attempted_questions)
        val correctAnswerTv = dialogView.findViewById<TextView>(R.id.tv_correct_answers)
        val wrongAnswerTv = dialogView.findViewById<TextView>(R.id.tv_wrong_answers)

        dialogView.setBackgroundDrawable(null)

        closeBtn.setOnClickListener { v: View? ->
            dialogBuilder.dismiss()
        }

        dialogBuilder.setView(dialogView)
        dialogBuilder.show()
    }

    override fun onClick(v: View?) {
        when(v!!.id){
            R.id.iv_next -> showScorePopup()
        }
    }
}