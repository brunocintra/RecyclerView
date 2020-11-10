package com.persist.recyclerview

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.persist.recyclerview.adapter.BookAdapter
import com.persist.recyclerview.domain.Book
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    companion object {
        const val BOOK_ACTIVITY_ADD_BOOK = 2
        const val BOOK_ACTIVITY_EDT_BOOK = 3

        const val BOOK_ACTIVITY_OBJECT_BOOK = "book_object"
    }

    var bookAdapter: BookAdapter? = null
    var linearLayoutManager: LinearLayoutManager? = null
    var bookList = arrayListOf<Book>(
        Book(1, "Open Leaders", "Abra sua mente", 1),
        Book(2, "Android Basico", "Construa seu app", 0),
        Book(3, "Seja Foda", "Um modelo de vida", 1),
        Book(4, "Delphi", "Como programar", 0)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnAdd.setOnClickListener{
            val intentBook = Intent(this, BookActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_SINGLE_TOP
            }
            startActivityForResult(intentBook, BOOK_ACTIVITY_ADD_BOOK)
        }
        initList()
    }

    private fun initList(){
        bookAdapter = BookAdapter(bookList, this, this::onSetItemClickListener)
        linearLayoutManager = LinearLayoutManager(this)
        recyclerview.layoutManager = linearLayoutManager
        recyclerview.adapter = bookAdapter
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val book = data?.getParcelableExtra<Book>(MainActivity.BOOK_ACTIVITY_OBJECT_BOOK)

        if (resultCode == Activity.RESULT_OK){
            if (requestCode == BOOK_ACTIVITY_EDT_BOOK) {
                if (book != null){
                    bookList[book.id-1] = book
                    bookAdapter?.notifyDataSetChanged()
                    Toast.makeText(this, getString(R.string.edit_book_sucess), Toast.LENGTH_SHORT).show()
                }
            } else if (requestCode == BOOK_ACTIVITY_ADD_BOOK) {
                if (book != null){
                    book.id = bookList.size + 1
                    bookList.add(book)
                    bookAdapter?.notifyDataSetChanged()
                    Toast.makeText(this, getString(R.string.add_book_success), Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        initList()
    }

    fun onSetItemClickListener(book: Book){
        val intentBook = Intent(this, BookActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_SINGLE_TOP
            putExtra(BOOK_ACTIVITY_OBJECT_BOOK, book)
        }
        startActivityForResult(intentBook, BOOK_ACTIVITY_EDT_BOOK)
    }
}