package com.example.englishtest.Activity

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.RadioButton
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.englishtest.R
import com.example.englishtest.Room.database.AppDatabase
import com.example.englishtest.Room.entity.TestDatabase
import com.example.englishtest.databinding.ActivityStartTestBinding
import java.util.Random

class StartTestActivity : AppCompatActivity() {
    private val binding by lazy { ActivityStartTestBinding.inflate(layoutInflater) }
    private lateinit var database: AppDatabase
    private lateinit var testlar: ArrayList<TestDatabase>
    var count = 0
    lateinit var answerList: ArrayList<Int>
    private var radioButton: RadioButton? = null
    var count_true = 0
    var count_false = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        database = AppDatabase.getInstance(this)
        val item = intent.getIntExtra("position", 0)
        testlar = ArrayList(database.testDao().getTestByTestId(item))
        answerList = ArrayList()

        test()

        binding.radiogroup.setOnCheckedChangeListener { _, id ->
            radioButton = findViewById(id)
        }



        binding.txtNext.setOnClickListener {
            if (radioButton?.text == testlar.get(count).uzbek) {
                Toast.makeText(this, "To'g'ri", Toast.LENGTH_SHORT).show()
                count_true++
            } else {
                Toast.makeText(this, "Xato", Toast.LENGTH_SHORT).show()
                count_false++
            }
            binding.radiogroup.clearCheck()

            count++
            test()

            answerList.clear()
        }
    }

    fun numberBtn(): Int {
        return kotlin.random.Random.nextInt(1, testlar.size)
    }

    fun randomNumberBtn(num1: Int): ArrayList<Int> {
        val num2 = numberBtn()
        val num3 = numberBtn()
        val num4 = numberBtn()
        val intArray = arrayListOf<Int>()
        if (num1 != num2 && num1 != num3 && num2 != num3 && num1 != num4 && num2 != num4 && num3 != num4) {
            intArray.add(num1)
            intArray.add(num2)
            intArray.add(num3)
            intArray.add(num4)
            return intArray
        }
        return randomNumberBtn(num1)
    }

    fun ans(): Int {
        return kotlin.random.Random.nextInt(1, 5)
    }

    fun test() {
        answerList = randomNumberBtn(count)
        if (count == testlar.size) {
            val dialog = AlertDialog.Builder(this)
                .setTitle("Natija")
                .setMessage("To'g'ri javoblar soni: $count_true \n Xato javoblar soni: $count_false")
                .setPositiveButton("Ok") { _, _ ->
                    finish()
                }.setNegativeButton("Cancel") { _, _ ->
                    finish()
                }
                .create()

            dialog.show()
        } else {
            binding.test.text = testlar.get(count).english
            when (ans()) {
                1 -> {
                    binding.radio1.text = testlar.get(count).uzbek
                    binding.radio2.text = testlar.get(answerList[1]).uzbek
                    binding.radio3.text = testlar.get(answerList[2]).uzbek
                    binding.radio4.text = testlar.get(answerList[3]).uzbek
                }

                2 -> {
                    binding.radio2.text = testlar.get(count).uzbek
                    binding.radio3.text = testlar.get(answerList[1]).uzbek
                    binding.radio4.text = testlar.get(answerList[2]).uzbek
                    binding.radio1.text = testlar.get(answerList[3]).uzbek
                }

                3 -> {
                    binding.radio3.text = testlar.get(count).uzbek
                    binding.radio1.text = testlar.get(answerList[1]).uzbek
                    binding.radio2.text = testlar.get(answerList[2]).uzbek
                    binding.radio4.text = testlar.get(answerList[3]).uzbek
                }

                4 -> {
                    binding.radio4.text = testlar.get(count).uzbek
                    binding.radio1.text = testlar.get(answerList[1]).uzbek
                    binding.radio2.text = testlar.get(answerList[2]).uzbek
                    binding.radio3.text = testlar.get(answerList[3]).uzbek
                }
            }
        }
    }
}