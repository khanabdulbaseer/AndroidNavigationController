package com.example.abdulbaseerkhan.navigationcontrollertest

import android.os.Bundle
import com.example.abdulbaseerkhan.navigationcontrollertest.NavigationController.Transportable
import com.example.abdulbaseerkhan.navigationcontrollertest.NavigationController.ViewController
import kotlinx.android.synthetic.main.activity_second.*

class SecondActivity() : ViewController(), Transportable<SecondActivity> {

    lateinit override var transport: (SecondActivity.() -> Unit)
    var danish: String = "false"
    var textChanged: ((String) -> Unit)? = null
    var doosra: Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        performTansport(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        textView2.text = danish
        goToThirdButton.setOnClickListener {
            textChanged?.invoke("String Value Changed")
            navigationController?.push(ThirdActivity(),true) {
                string = "Hello"
                he = 4
            }
        }

        secondBackButton.setOnClickListener {
            navigationController?.popViewController()
        }
    }


}
