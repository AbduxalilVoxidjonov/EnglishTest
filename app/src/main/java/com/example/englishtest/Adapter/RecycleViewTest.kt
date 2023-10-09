package com.example.englishtest.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.englishtest.Room.entity.TestDatabase
import com.example.englishtest.databinding.ItemRvTestBinding

class RecycleViewTest(val list: List<TestDatabase>):RecyclerView.Adapter<RecycleViewTest.Test>() {
    inner class Test(val binding: ItemRvTestBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun binds(testWithTestName: TestDatabase){
            binding.apply {
                testNameEnglish.text=testWithTestName.english
                testNameUzbek.text=testWithTestName.uzbek
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Test {
        return Test(ItemRvTestBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: Test, position: Int) {
        holder.binds(list[position])
    }
}