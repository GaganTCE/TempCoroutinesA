package com.example.tempcoroutines

import android.animation.Animator
import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnticipateOvershootInterpolator
import android.view.animation.OvershootInterpolator
import android.view.animation.RotateAnimation
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.tempcoroutines.databinding.ActivityMainBinding
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.yield

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
//
//        val anim = ObjectAnimator.ofFloat(binding.txt,"rotation",0f,360f)
//        anim.addListener(object : Animator.AnimatorListener{
//            override fun onAnimationStart(p0: Animator) {
//
//            }
//
//            override fun onAnimationEnd(p0: Animator) {
//               binding.txt.visibility = View.GONE
//            }
//
//            override fun onAnimationCancel(p0: Animator) {
//
//            }
//
//            override fun onAnimationRepeat(p0: Animator) {
//
//            }
//
//        })
//        binding.txt.setOnClickListener {
//            anim.repeatCount = Animation.INFINITE
//            anim.duration = 5000
//            anim.interpolator= AnticipateOvershootInterpolator()
//            anim.start()
//            binding.txt.setOnClickListener {
//                anim.end()
//            }
//
//        }
//        binding.btnShow.setOnClickListener {
////            val dialog = BottomSheetDialog(this)
////            dialog.setContentView(R.layout.btm_sheet_layout)
////            dialog.show()
////            anim.cancel()
//            binding.animationView.visibility = View.VISIBLE
//            binding.animationView.playAnimation()
//            binding.animationView.repeatCount = 5
//
//        }
//        binding.animationView.setOnClickListener {
//            binding.animationView.cancelAnimation()
//        }
//        binding.animationView.addAnimatorListener(object : Animator.AnimatorListener{
//            override fun onAnimationStart(p0: Animator) {
//
//            }
//
//            override fun onAnimationEnd(p0: Animator) {
//                binding.animationView.visibility = View.GONE
//            }
//
//            override fun onAnimationCancel(p0: Animator) {
//
//            }
//
//            override fun onAnimationRepeat(p0: Animator) {
//
//            }
//
//        })
        binding.txt.setOnClickListener {
//            CoroutineScope(Dispatchers.Main).launch {
//                fun1()
//            }
//            CoroutineScope(Dispatchers.Main).launch {
//                fun2()
//            }
            CoroutineScope(Dispatchers.IO).launch {
                var x = 0
                var y = 0
                val job1 = CoroutineScope(Dispatchers.Unconfined).launch {
                    x = fun1()
                }
                val job2 = CoroutineScope(Dispatchers.Unconfined).launch {
                    y = fun2()
                }
                job1.join()
                job2.join()
                println("Edrr" + x + " " + y)


                val def1 = CoroutineScope(Dispatchers.Unconfined).async {
                    fun1()
                }
                val def2 = CoroutineScope(Dispatchers.Unconfined).async {
                    fun2()
                }
                println("Edrr" + def1.await() + " " + def2.await())
            }
        }
    }

    suspend fun fun1() : Int{
        println("Edrr fun1Started.." + Thread.currentThread().name)
        delay(2000)
        println("Edrr fun1Ended.." + Thread.currentThread().name)
        return 12
    }

    suspend fun fun2() : Int{
        println("Edrr fun2Started.." + Thread.currentThread().name)
        delay(2000)
        println("Edrr fun2Ended.." + Thread.currentThread().name)
        return 14
    }

}