package com.mohamedmabrouk.skillpractice.ui.category

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*
import com.mohamedmabrouk.skillpractice.R
import com.mohamedmabrouk.skillpractice.model.Category
import com.mohamedmabrouk.skillpractice.utils.Connection
import kotlinx.android.synthetic.main.activity_category.*

class CategoryActivity : AppCompatActivity(), CategoryClickListener, View.OnClickListener {


    private lateinit var categoryRecyclerView: RecyclerView
    private lateinit var database: DatabaseReference
    private lateinit var categoriesReference: DatabaseReference
    private lateinit var categoryListener: ValueEventListener


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category)

        categoryRecyclerView = findViewById(R.id.rv_category)
        categoryRecyclerView.layoutManager = GridLayoutManager(this, 3)

        retry_btn.setOnClickListener(this)

        categoryListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val categoryList = ArrayList<Category>()
                dataSnapshot.children.forEach { it ->
                    categoryList.add(
                        Category(
                            it.key!!.toInt(),
                            it.child("name").value.toString()
                        )
                    )
                }
                categoryList.add(
                    Category(
                        0,
                        "ew ewe ew"
                    )
                )
                categoryList.add(
                    Category(
                        0,
                        "33 ew eeeee"
                    )
                )
                categoryList.add(
                    Category(
                        0,
                        "ewewe ewe ew"
                    )
                )
                categoryList.add(
                    Category(
                        0,
                        "ew wwwwwwewew wewewewew"
                    )
                )
                categoryList.add(
                    Category(
                        0,
                        "43434 3434 www"
                    )
                )

                categoryList.add(
                    Category(
                        0,
                        "43434 3434 www"
                    )
                )


                categoryList.add(
                    Category(
                        0,
                        "43434 3434 www"
                    )
                )


                categoryList.add(
                    Category(
                        0,
                        "43434 3434 www"
                    )
                )
                categoryList.add(
                    Category(
                        0,
                        "43434 3434 www"
                    )
                )
                categoryList.add(
                    Category(
                        0,
                        "43434 3434 www"
                    )
                )
                categoryList.add(
                    Category(
                        0,
                        "43434 3434 www"
                    )
                )
                categoryList.add(
                    Category(
                        0,
                        "43434 3434 www"
                    )
                )
                categoryList.add(
                    Category(
                        0,
                        "Completing Statements"
                    )
                )
                categoryList.add(
                    Category(
                        0,
                        "Direct and Indirect Speech"
                    )
                )
                categoryList.add(
                    Category(
                        0,
                        "One Word Substitution"
                    )
                )
                categoryList.add(
                    Category(
                        0,
                        "Common Error Detection"
                    )
                )

                bindCategoryList(categoryList)
            }

            override fun onCancelled(databaseError: DatabaseError) {
                showError(databaseError.message)
            }
        }

        getCategories()
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.retry_btn -> getCategories()
        }
    }

    private fun getCategories() {
        retry_btn.visibility = View.INVISIBLE
        if (Connection().isOnline(this)) {
            loading_pb.visibility = View.VISIBLE
            database = FirebaseDatabase.getInstance().reference
            categoriesReference = database.child("categories")
            categoriesReference.addValueEventListener(categoryListener)
        } else {
            showError(getString(R.string.offline_msg))
        }

    }

    override fun onCategoryItemClick(position: Int) {
        Toast.makeText(this, position.toString(), Toast.LENGTH_SHORT).show()
    }

    private fun bindCategoryList(list: ArrayList<Category>) {
        loading_pb.visibility = View.INVISIBLE
        hideError()
        categoryRecyclerView.adapter =
            CategoryListAdapter(list, this@CategoryActivity)
    }

    private fun showError(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
        loading_pb.visibility = View.INVISIBLE
        retry_btn.visibility = View.VISIBLE
    }

    private fun hideError() {
        retry_btn.visibility = View.INVISIBLE
    }


}