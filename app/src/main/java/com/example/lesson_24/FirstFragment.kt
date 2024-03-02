package com.example.lesson_24

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.lesson_24.databinding.FragmentFirstBinding
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FirstFragment : Fragment() {

    private  var bidding: FragmentFirstBinding? = null

    private val textList = arrayOf("Hi",  "My name is Bot", "I am your virtual assisten")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bidding = FragmentFirstBinding.inflate(inflater, container, false)
        return  bidding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        setOnClickOnListeners()
        CoroutineScope(Dispatchers.IO).launch {
            delay(1500)

            withContext(Dispatchers.Main){
                for(i in 0 .. textList.size-1){
                    changeText(i)
                }
            }
        }

//        Observable.just(textList[0], textList[1], textList[2]).subscribe(getObserver())
        Observable.fromIterable(textList.asIterable()).subscribe(getObserver())
    }


    private  fun getObserver():Observer<String> {
        return  object : Observer<String>   {
            override fun onSubscribe(d: Disposable) {

            }

            override fun onError(e: Throwable) {

            }

            override fun onComplete() {

            }

            override fun onNext(text:String ) {
                Handler(Looper.getMainLooper()).postDelayed({
                    bidding?.tvTextRxJava?.text = text
                }, generateSleepTime())


            }

        }
    }

    private  fun generateSleepTime (): Long {


        return (4000..7000).random().toLong()
    }

    suspend fun  printText() {
        for(i in 0..10){
//            Log.e("Text My", "Hi world  ${i}!!")
            delay(3000)
//            changeText("Hi world  ${i}!!")
        }
    }

    private suspend fun  returnText(id:Int):String{
        delay(3000)
        return textList[id]
    }
    private suspend fun changeText(id:Int){
        bidding?.tvText?.text =  returnText(id)
    }

}