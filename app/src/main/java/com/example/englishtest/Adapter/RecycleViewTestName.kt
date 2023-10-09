package com.example.englishtest.Adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.englishtest.Activity.TestActivity
import com.example.englishtest.Room.entity.TestNameDatabase
import com.example.englishtest.databinding.ItemRvTestNameBinding

class RecycleViewTestName(val list: List<TestNameDatabase>):RecyclerView.Adapter<RecycleViewTestName.TestName>() {
    inner class TestName(val binding: ItemRvTestNameBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun binds(testWithTestName: TestNameDatabase,position: Int){
            binding.apply {
                testNameTv.text=testWithTestName.name
            }
            binding.root.setOnClickListener{
                val intent = Intent(binding.root.context, TestActivity::class.java)
                intent.putExtra("position",position+1)
                startActivity(binding.root.context,intent,null)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TestName {
        return TestName(ItemRvTestNameBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: TestName, position: Int) {
        holder.binds(list[position],position)
    }
}