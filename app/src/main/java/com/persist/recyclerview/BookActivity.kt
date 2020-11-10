package com.persist.recyclerview

import android.app.Activity
import android.content.Intent
import android.content.res.TypedArray
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import com.persist.recyclerview.domain.Book
import kotlinx.android.synthetic.main.activity_book.*

class BookActivity : AppCompatActivity() {

    private lateinit var bookEdit: Book
    private var favorite: Int = -1

    private val favoriteImages: TypedArray by lazy {
        this.resources.obtainTypedArray(R.array.favorite)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book)

        initBook(intent.getParcelableExtra<Book>(MainActivity.BOOK_ACTIVITY_OBJECT_BOOK))

        btnConfirmar.setOnClickListener{
            val returnIntent = Intent()
            returnIntent.putExtra(MainActivity.BOOK_ACTIVITY_OBJECT_BOOK, getBook())
            setResult(Activity.RESULT_OK, returnIntent)
            finish()
        }

        btnCancelar.setOnClickListener{
            setResult(RESULT_CANCELED, intent)
            finish()
        }

        imgFavorite.setOnClickListener{
            favorite = if(favorite.equals(0)) 1 else 0
            imgFavorite.setImageDrawable(favoriteImages.getDrawable(favorite))
        }
    }

    private fun initBook(book: Book?){
        if (book != null ){
            bookEdit = book
            edtTitle.setText(bookEdit.title)
            edtSubtitle.setText(bookEdit.subtitle)
            imgFavorite.setImageDrawable(favoriteImages.getDrawable(bookEdit.favorite))
            favorite = bookEdit.favorite
        } else {
            favorite = 0
        }
    }

    private fun getBook(): Book {
        val title = edtTitle.text.toString()
        val subtitle = edtSubtitle.text.toString()
        return if (this::bookEdit.isInitialized) Book(bookEdit.id, title, subtitle, favorite)
               else Book(0, title, subtitle, favorite)
    }
}