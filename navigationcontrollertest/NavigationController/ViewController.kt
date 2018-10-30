package com.example.abdulbaseerkhan.navigationcontrollertest.NavigationController

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.example.abdulbaseerkhan.navigationcontrollertest.ThirdActivity
import com.example.abdulbaseerkhan.navigationcontrollertest.Transporter

open class ViewController: Activity() {


    var navigationController: NavigationController? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (navigationController == null) {
            navigationController = NavigationController.transporter
        }
        navigationController?.viewControllers?.add(this)
    }

    fun <T> performTansport(t: T) {
        navigationController = NavigationController.transporter
        navigationController?.viewControllers?.getlast()?.let {vc ->
            val performTransport =  (vc as Transportable<T>).transport
            navigationController?.viewControllers?.remove(vc)
            t.performTransport()
            //return transporter
        }
    }

}

fun<E> ArrayList<E>.getlast(): E? = when (size) {
    0 -> null
    else -> get(size-1)
}

class NavigationController {
    var viewControllers: ArrayList<ViewController> = ArrayList()

    fun removeLast() {
        if (viewControllers.size > 1) {
            viewControllers.getlast()?.let {
                viewControllers.remove(it)
            }
        }
    }

    fun <T: ViewController>push(activity: T, animated: Boolean, setProperties: (T.() -> Unit)? = null) {
        viewControllers.getlast()?.let {
            val intent = Intent(it, activity.javaClass)
            setProperties?.let {
                (activity as? Transportable<T>)?.transport = it
                viewControllers.add(activity)
            }

            //viewControllers.add(activity)
            it.startActivity(intent)
            NavigationController.transporter = this
        }
    }

    fun addVC(activity: ViewController) {
        viewControllers.add(activity)
    }

    fun popViewController() {
        //turn off waala code dekha
        if (viewControllers.size > 1) {
            viewControllers.getlast()?.let {
                it.finish()
                viewControllers.remove(it)
              //  it.overridePendingTransition()
            }

            //overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
        }
    }

    fun popToRootViewController() {
        if (viewControllers.size > 1) {
            while (viewControllers.size > 1) {
                viewControllers[1].finish()
                viewControllers.removeAt(1)
            }
        }
    }

    companion object {
        var transporter: NavigationController? = null
    }
}

interface Transportable<T> {
    var transport: (T.() -> Unit)
}



//annotation class Copyable<T>(val value: T)