package com.example.englishtest.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.example.englishtest.R
import com.example.englishtest.Room.entity.TestNameDatabase
import com.example.englishtest.databinding.ItemGroupBinding

class GroupAdapter(contex: Context, val list: List<TestNameDatabase>) :
    ArrayAdapter<TestNameDatabase>(contex, R.layout.item_group) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val itemRegionBinding: ItemGroupBinding
        if (convertView == null) {
            itemRegionBinding =
                ItemGroupBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        }else{
            itemRegionBinding = ItemGroupBinding.bind(convertView)
        }
        itemRegionBinding.groupId.text=list[position].name
        return itemRegionBinding.root
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        val itemRegionBinding: ItemGroupBinding
        if (convertView == null) {
            itemRegionBinding =
                ItemGroupBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        }else{
            itemRegionBinding = ItemGroupBinding.bind(convertView)
        }
        itemRegionBinding.groupId.text=list[position].name
        return itemRegionBinding.root
    }
    override fun getCount(): Int {
        return list.size
    }
}