package com.example.effectivemobiletest.productdetailsscreen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.effectivemobiletest.R
import kotlinx.android.synthetic.main.fragment_product_specs.*

class ProductSpecsFragment(private val specs: List<String>) : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_product_specs, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        cpuSpec.text = this.specs[0]
        camSpec.text = this.specs[1]
        ramSpec.text = this.specs[2]
        ssdSpec.text = this.specs[3]

    }
}