package com.example.englishtest.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.englishtest.Adapter.RecycleViewTest
import com.example.englishtest.Room.database.AppDatabase
import com.example.englishtest.Room.entity.TestDatabase
import com.example.englishtest.databinding.ActivityTestBinding
import com.example.englishtest.databinding.DiloagItemTest2Binding

class TestActivity : AppCompatActivity() {
    private val binding by lazy { ActivityTestBinding.inflate(layoutInflater) }
    private lateinit var database: AppDatabase
    lateinit var recycleViewTest: RecycleViewTest
    lateinit var testList: ArrayList<TestDatabase>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val item = intent.getIntExtra("position", 0)
        database = AppDatabase.getInstance(this)

        testList = ArrayList(database.testDao().getTestByTestId(item))
        recycleViewTest = RecycleViewTest(testList)


        binding.apply {
            recyclerViewTest.adapter = recycleViewTest
            addTest.setOnClickListener {
                val dialogTest = DiloagItemTest2Binding.inflate(layoutInflater, null, false)
                val dialog = AlertDialog.Builder(this@TestActivity, 0)
                    .setView(dialogTest.root)
                    .create()
                dialogTest.saveButton.setOnClickListener {
                    val english = dialogTest.englishName.text.toString()
                    val uzbek = dialogTest.uzbekName.text.toString()
                    val test = TestDatabase(
                        english = english,
                        uzbek = uzbek,
                        testId = item
                    )

                    val ids = database.testDao().addTest(test)
                    test.id = ids.toInt()
                    testList.add(test)
                    recycleViewTest.notifyItemInserted(testList.size)
                    dialog.dismiss()
                    dialogTest.englishName.text?.clear()
                    dialogTest.uzbekName.text?.clear()
                }
                dialogTest.cancelButton.setOnClickListener {
                    dialog.dismiss()
                }

                dialog.show()
            }

            startTest.setOnClickListener {
                if (testList.size > 4) {
                    val intent = Intent(this@TestActivity, StartTestActivity::class.java)
                    intent.putExtra("position", item)
                    startActivity(intent)
                } else {
                    Toast.makeText(
                        this@TestActivity,
                        "Testda kamida 4 ta savol bo'lishi kerak",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        recycleViewTest.notifyItemInserted(testList.size)
    }
}