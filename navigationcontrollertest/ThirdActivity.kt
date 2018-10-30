package com.example.abdulbaseerkhan.navigationcontrollertest

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.abdulbaseerkhan.navigationcontrollertest.NavigationController.ViewController
import kotlinx.android.synthetic.main.activity_third.*

class ThirdActivity : ViewController()  {

    var setProperties: (ThirdActivity.() -> Unit)? = null

    public var string = ""
    var helloYes = ""
    var he = 2
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_third)
        setProperties?.let { it() }

        backToSecondButton.setOnClickListener {
            navigationController?.popViewController()
        }
        popToRootButton.setOnClickListener {
            navigationController?.popToRootViewController()
        }
    }

}


data class Transporter<T>(var transport: (T.() -> Unit)?)