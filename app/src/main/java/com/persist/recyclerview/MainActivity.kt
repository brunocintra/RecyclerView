package com.persist.recyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.persist.recyclerview.adapter.BookAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var nameList = ArrayList<String>()

    // Iniciando a RecyclerView
    var bookAdapter: BookAdapter? = null
    var linearLayoutManager: LinearLayoutManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initList()
    }

    private fun initList(){
        nameList.add("Como eu era antes de você")
        nameList.add("Seja Foda")
        nameList.add("O poder do hábito")
        nameList.add("O milagre da mannhã")

        bookAdapter = BookAdapter(nameList, this)
        linearLayoutManager = LinearLayoutManager(this)
        recyclerview.layoutManager = linearLayoutManager
        recyclerview.adapter = bookAdapter
    }
}