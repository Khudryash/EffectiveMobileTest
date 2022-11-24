package com.example.effectivemobiletest.productdetailsscreen

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class ProductDetailsSpecAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    private val fragmentArrayList = ArrayList<Fragment>()
    private val titleList = ArrayList<String>()

    override fun getCount(): Int = titleList.size

    override fun getPageTitle(position: Int): CharSequence? {
        return titleList[position]
    }

    override fun getItem(position: Int): Fragment {
        return fragmentArrayList[position]
    }

    fun addFragment(fragment: Fragment?, title: String?) {
        fragmentArrayList.add(fragment!!)
        titleList.add(title!!)
    }
}