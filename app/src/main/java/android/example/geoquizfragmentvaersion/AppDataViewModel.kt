package android.example.geoquizfragmentvaersion

import androidx.lifecycle.ViewModel

class AppDataViewModel : ViewModel() {

    var answer = true
    var qList = mutableListOf<Questions>(
        (Questions("Question 1:Is Tehran the capital of Iran?", true)),
        (Questions("Question 2:Is Los Angeles the capital of the United States?", false)),
        (Questions("Question 3:Is Kabul the capital of Afghanistan?", true)),
        (Questions("Question 4:Is Istanbul the capital of Turkey?", true)),
        (Questions("Question 5:Is Tokyo the capital of Japan?", true)),
        (Questions("Question 6:Is Abadan the capital of Brazil?", true)),
        (Questions("Question 7:Is Beijing the capital of China?", true)),
        (Questions("Question 8:Is the capital of India Islamabad?", false)),
        (Questions("Question 9:Is Oslo the capital of Sweden?", false)),
        (Questions("Question 10:Is Berlin the capital of Norway?", false))
    )

    var qCounter = 0


    override fun onCleared() {
        super.onCleared()
    }



}