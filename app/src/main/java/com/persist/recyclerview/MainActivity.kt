package com.persist.recyclerview

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.persist.recyclerview.adapter.BookAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    companion object {
        const val MAIN_ACTIVITY_BOOK = 1
    }

    var nameList = ArrayList<String>()
    var bookAdapter: BookAdapter? = null
    var linearLayoutManager: LinearLayoutManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnAdd.setOnClickListener{
            val intentBook = Intent(this, BookActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_SINGLE_TOP
            }
            startActivityForResult(intentBook, MAIN_ACTIVITY_BOOK)
        }

        nameList.add("Como eu era antes de você")
        nameList.add("Seja Foda")
        nameList.add("O poder do hábito")
        nameList.add("Open Leaders")

        initList()
    }


    private fun initList(){
        bookAdapter = BookAdapter(nameList, this)
        linearLayoutManager = LinearLayoutManager(this)
        recyclerview.layoutManager = linearLayoutManager
        recyclerview.adapter = bookAdapter
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_OK){
            if (requestCode == MAIN_ACTIVITY_BOOK) {
                Toast.makeText(this, "Lista de livros atualizada!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}