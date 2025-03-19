package com.example.tempcoroutines

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.example.tempcoroutines.databinding.ActivityMain2Binding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.coroutines.yield

class MainActivity2 : AppCompatActivity() {
    private lateinit var binding: ActivityMain2Binding
    val scope = lifecycleScope


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding.txt.setOnClickListener {
            CoroutineScope(Dispatchers.Unconfined).launch {
                val x = scope.async {
                    fun1()
                }
                val y = CoroutineScope(Dispatchers.IO).async {
                    fun2()
                }
                withContext(Dispatchers.Main) {
                    binding.txt.text = y.await().toString()
                }
                println("Edrr " + x.await() + "" + y.await())
            }
        }
        binding.btn.setOnClickListener {
            startActivity(Intent(this, MainActivity2::class.java))
            finish()
        }
    }

    suspend fun fun1(): Int {
        println("Edrrfunction 1 started" + Thread.currentThread().name)
        delay(4000)
        println("Edrrfunction 1 ended" + Thread.currentThread().name)
        return 54
    }

    suspend fun fun2(): Int {
        println("Edrrfunction 2 Started" + Thread.currentThread().name)
        delay(4000)
        println("Edrrfunction 2 ended" + Thread.currentThread().name)
        return 4
    }
}

