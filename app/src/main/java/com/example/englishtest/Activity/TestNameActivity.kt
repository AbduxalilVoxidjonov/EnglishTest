package com.example.englishtest.Activity

import android.annotation.SuppressLint
import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.example.englishtest.Adapter.GroupAdapter
import com.example.englishtest.Adapter.RecycleViewTestName
import com.example.englishtest.R
import com.example.englishtest.Room.database.AppDatabase
import com.example.englishtest.Room.entity.TestDatabase
import com.example.englishtest.Room.entity.TestNameDatabase
import com.example.englishtest.databinding.ActivityMainBinding
import com.example.englishtest.databinding.DiloagItemTestBinding
import com.example.englishtest.databinding.DiloagItemTestGroupBinding


class TestNameActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private lateinit var database: AppDatabase
    private lateinit var groupNameTest: ArrayList<TestNameDatabase>
    private lateinit var testNameAdapter: RecycleViewTestName
    private lateinit var groupAdapter: GroupAdapter

    @SuppressLint("SuspiciousIndentation", "NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        database = AppDatabase.getInstance(this)

        groupNameTest = ArrayList(database.testNameDao().getAllName())

        groupAdapter = GroupAdapter(this, groupNameTest)
        testNameAdapter = RecycleViewTestName(groupNameTest)


        binding.apply {


            recyclerViewTestName.adapter = testNameAdapter

            addTestName.setOnClickListener {
                val dialogGroup = DiloagItemTestGroupBinding.inflate(layoutInflater, null, false)

                val dialog = AlertDialog.Builder(this@TestNameActivity)
                    .setView(dialogGroup.root)
                    .create()
                dialogGroup.saveButton.setOnClickListener {
                    val testNames = dialogGroup.testNameGroup.text.toString()
                    val groupName = TestNameDatabase(name = testNames)
                    val id = database.testNameDao().addName(groupName)
                    groupName.id = id.toInt()
                    testNameAdapter.notifyDataSetChanged()
                    groupNameTest.add(groupName)
                    testNameAdapter.notifyItemInserted(groupNameTest.size)
                    dialogGroup.testNameGroup.text?.clear()
                    dialog.dismiss()
                }
                dialogGroup.cancelButton.setOnClickListener {
                    dialog.dismiss()
                }
                dialog.show()
            }

            addTest.setOnClickListener {
                val dialogTest = DiloagItemTestBinding.inflate(layoutInflater, null, false)
                dialogTest.groupNameItem.adapter = groupAdapter
                val dialog = AlertDialog.Builder(this@TestNameActivity)
                    .setView(dialogTest.root)
                    .create()
                dialogTest.saveButton.setOnClickListener {
                    val testName = groupNameTest[dialogTest.groupNameItem.selectedItemPosition]
                    val english = dialogTest.englishName.text.toString()
                    val uzbek = dialogTest.uzbekName.text.toString()
                    val test = TestDatabase(
                        english = english,
                        uzbek = uzbek,
                        testId = testName.id
                    )

                    val ids = database.testDao().addTest(test)
                    test.id = ids.toInt()
                    dialog.dismiss()
                    dialogTest.englishName.text?.clear()
                    dialogTest.uzbekName.text?.clear()
                }
                dialogTest.cancelButton.setOnClickListener {
                    dialog.dismiss()
                }
                dialog.show()
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onStart() {
        super.onStart()
        testNameAdapter.notifyItemInserted(groupNameTest.size)
    }
}