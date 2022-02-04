package android.example.geoquizfragmentvaersion

import android.example.geoquizfragmentvaersion.databinding.FragmentExamBinding
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

class ExamFragment:Fragment(R.layout.fragment_exam) {

    lateinit var binding: FragmentExamBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentExamBinding.bind(view)

        var answer = true

        qList.add(Questions("Question 1:Is Tehran the capital of Iran?", true))
        qList.add(Questions("Question 2:Is Los Angeles the capital of the United States?", false))
        qList.add(Questions("Question 3:Is Kabul the capital of Afghanistan?", true))
        qList.add(Questions("Question 4:Is Istanbul the capital of Turkey?", true))
        qList.add(Questions("Question 5:Is Tokyo the capital of Japan?", true))
        qList.add(Questions("Question 6:Is Abadan the capital of Brazil?", true))
        qList.add(Questions("Question 7:Is Beijing the capital of China?", true))
        qList.add(Questions("Question 8:Is the capital of India Islamabad?", false))
        qList.add(Questions("Question 9:Is Oslo the capital of Sweden?", false))
        qList.add(Questions("Question 10:Is Berlin the capital of Norway?", false))


        with(binding){




            var qCounter = 0
            tvQuestion.text = qList[0].questions
            if (qCounter == 0) btnPrev.isEnabled = true

            btnNext.setOnClickListener {
                if (qCounter < qList.size - 1) {
                    qCounter++
                    tvQuestion.text = qList[qCounter].questions
                    btnPrev.isEnabled = true
                } else {
                    btnNext.isEnabled = false
                }

                btnTrue.isEnabled = true
                btnFalse.isEnabled = true

            }

            btnPrev.setOnClickListener {
                if (qCounter > 0) {
                    qCounter--
                    tvQuestion.text = qList[qCounter].questions
                    btnNext.isEnabled = true
                } else {
                    btnPrev.isEnabled = false
                }
                btnFalse.isEnabled = true
                btnTrue.isEnabled = true


            }

            btnTrue.setOnClickListener {
                answer = true
                if(qList[qCounter].cheat){
                    Toast.makeText(requireContext(), "Cheating is wrong!", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
                if (qList[qCounter].answer == answer) {
                    Toast.makeText(requireContext(), "Correct!", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(requireContext(), "Incorrect!", Toast.LENGTH_SHORT).show()
                }
                btnTrue.isEnabled = false
                btnFalse.isEnabled = false
            }

            btnFalse.setOnClickListener {
                answer = false
                if(qList[qCounter].cheat){
                    Toast.makeText(requireContext(), "Cheating is wrong!", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
                if (qList[qCounter].answer == answer) {
                    Toast.makeText(requireContext(), "Correct!", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(requireContext(), "Incorrect!", Toast.LENGTH_SHORT).show()
                }
                btnTrue.isEnabled = false
                btnFalse.isEnabled = false


            }


            btnCheat.setOnClickListener {
                findNavController().navigate(ExamFragmentDirections.actionExamFragmentToCheatFragment(qCounter))
            }




        }
    }
}