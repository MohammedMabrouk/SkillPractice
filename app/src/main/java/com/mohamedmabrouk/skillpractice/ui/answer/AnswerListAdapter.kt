package com.mohamedmabrouk.skillpractice.ui.answer

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.mohamedmabrouk.skillpractice.R
import com.mohamedmabrouk.skillpractice.model.Answer

class AnswerListAdapter(
    private val answerList: List<Answer>,
    private val answerClickListener: AnswerClickListener
) : RecyclerView.Adapter<AnswerListAdapter.AnswerViewHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): AnswerViewHolder {
        return AnswerListAdapter.AnswerViewHolder(
            LayoutInflater.from(p0.context).inflate(
                R.layout.item_answer,
                p0,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: AnswerViewHolder, position: Int) {
        holder.answerTextView.text = answerList[position].name
        holder.answerCardView.setOnClickListener {
            answerClickListener.onItemClick(position)
        }
    }

    override fun getItemCount(): Int {
        return answerList.size
    }

    class AnswerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val answerCardView: CardView = itemView.findViewById(R.id.cv_answer)
        val answerTextView: TextView = itemView.findViewById(R.id.tv_answer)
    }
}