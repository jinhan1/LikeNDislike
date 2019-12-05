package com.example.likendislike

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    //Module-level variable
    //var countlike: Int = 0
    lateinit var counterViewModel: CounterViewModel
    lateinit var sharedPreferences: SharedPreferences

    //var countdislike: Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d("MainActivity","OnCreate")

        //Initialise the CounterViewModel
        counterViewModel= ViewModelProviders.of(this).get(CounterViewModel::class.java)

        //Initialise sharedPrefernce
        //sharedPreferences=getSharedPreferences("natokong", Context.MODE_PRIVATE)
        sharedPreferences=getPreferences(Context.MODE_PRIVATE)



        imageViewLike.setOnClickListener{
            counterViewModel.countLike++
            textViewLike.text=counterViewModel.countLike.toString()
        }
        imageViewDislike.setOnClickListener{
            counterViewModel.countDislike++
            textViewDislike.text=counterViewModel.countDislike.toString()
        }
    }

    override fun onStart() {
        Log.d("MainActivity","OnStart")
        super.onStart()
    }

    override fun onResume() {
        Log.d("MainActivity","OnResume")
        //Retrieve Shared Preference Value(s)
        counterViewModel.countLike=sharedPreferences.getInt(getString(R.string.like),0)
        counterViewModel.countDislike=sharedPreferences.getInt(getString(R.string.dislike),0)

        textViewLike.text=counterViewModel.countLike.toString()
        textViewDislike.text=counterViewModel.countDislike.toString()
        super.onResume()
    }

    override fun onPause() {
        Log.d("MainActivity","OnPause")
        with(sharedPreferences.edit()){
            putInt(getString(R.string.like), counterViewModel.countLike)
            putInt(getString(R.string.dislike), counterViewModel.countDislike)
            commit()
        }

        super.onPause()
    }

    override fun onStop() {
        Log.d("MainActivity","OnStop")

        super.onStop()
    }

    override fun onDestroy() {
        Log.d("MainActivity","OnDestroy")
        super.onDestroy()
    }
}
