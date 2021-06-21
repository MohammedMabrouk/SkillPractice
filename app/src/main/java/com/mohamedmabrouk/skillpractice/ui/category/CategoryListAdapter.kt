package com.mohamedmabrouk.skillpractice.ui.category

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mohamedmabrouk.skillpractice.R
import com.mohamedmabrouk.skillpractice.model.Category

class CategoryListAdapter(
    private val categoryList: List<Category>,
    private val categoryClickListener: CategoryClickListener
) : RecyclerView.Adapter<CategoryListAdapter.CategoryViewHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): CategoryViewHolder {
        return CategoryListAdapter.CategoryViewHolder(
            LayoutInflater.from(p0.context).inflate(
                R.layout.item_category,
                p0,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.categoryTextView.text = categoryList[position].name
        holder.categoryTextView.setOnClickListener {
            categoryClickListener.onCategoryItemClick(position)
        }
    }

    override fun getItemCount(): Int {
        return categoryList.size
    }

    class CategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val categoryTextView: TextView = itemView.findViewById(R.id.tv_category)
    }
}