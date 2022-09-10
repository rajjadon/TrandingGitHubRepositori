package com.raj.jadon.prasentation.common.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.raj.jadon.prasentation.common.extension.inflateLayout

abstract class BaseFragment<V : ViewBinding>(@LayoutRes private val layOutId: Int) :
    Fragment() {

    lateinit var fragmentBinding: V

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setDataCollector()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragmentBinding = layOutId.inflateLayout(inflater, container)
        return fragmentBinding.root
    }

    override fun onStart() {
        super.onStart()
        setUpBindingVariables()
        setClickListener()
    }

    abstract fun setDataCollector()
    abstract fun setUpBindingVariables()
    abstract fun setClickListener()

    override fun onDestroyView() {
        super.onDestroyView()
        viewModelStore.clear()
    }
}