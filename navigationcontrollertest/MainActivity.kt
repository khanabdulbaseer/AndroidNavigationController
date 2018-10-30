package com.example.abdulbaseerkhan.navigationcontrollertest

import android.os.Bundle
import com.example.abdulbaseerkhan.navigationcontrollertest.NavigationController.NavigationController
import com.example.abdulbaseerkhan.navigationcontrollertest.NavigationController.ViewController
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : ViewController() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navigationController = NavigationController()
        navigationController?.viewControllers?.add(this)
        goToSecondButton.setOnClickListener {
            navigationController?.push(SecondActivity(), true) {
                danish = "true"
                doosra = false
                textChanged = {
                    setTextView(it)
                }
            }
        }
        //navigationController?.push()
    }
    fun setTextView(text: String) {
        textView.text = text
    }
}
