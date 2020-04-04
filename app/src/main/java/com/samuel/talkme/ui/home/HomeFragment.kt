package com.samuel.talkme.ui.home

import android.app.Activity.RESULT_OK
import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.speech.RecognizerIntent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.samuel.talkme.R
import com.samuel.talkme.data.model.SearchResponse
import java.util.*
import kotlin.collections.ArrayList

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel

    private val REQ_CODE_SPEECH_INPUT: Int = 100

    val URL_YOUTUBE :String = "https://www.youtube.com/watch?v="

    lateinit var boton: Button

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {



        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)

        boton = root.findViewById(R.id.button_id)

        boton.setOnClickListener(View.OnClickListener {
            entradaVoz()
        })




        return root
    }

    private fun entradaVoz(){

        val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault())
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Di algo")

        try {
            startActivityForResult(intent, REQ_CODE_SPEECH_INPUT)
        }catch (e: ActivityNotFoundException){
            Log.i("ERROR", "Error")
        }
    }

    private fun playVideo(idVideo: String){

        try{
            //here we try to open the link in app
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(URL_YOUTUBE+idVideo)))
        }catch (e: Exception) {
            //the app isn't available: we open in browser`
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(URL_YOUTUBE)))
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        Log.i("SAMUEL", ""+requestCode)
        when(requestCode){
            REQ_CODE_SPEECH_INPUT ->
                if(resultCode==RESULT_OK && data!=null) {
                    val resultado: ArrayList<String> =  data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)

                    homeViewModel.getSearch(resultado.get(0)).observe(this, Observer<SearchResponse?>{t ->
                        Log.i("RESST ", t!!.items[0].id.videoId)
                        playVideo(t!!.items[0].id.videoId)
                    })


            }
        }


    }
}