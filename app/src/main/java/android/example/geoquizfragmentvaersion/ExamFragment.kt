package android.example.geoquizfragmentvaersion

import android.example.geoquizfragmentvaersion.databinding.FragmentExamBinding
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import java.util.*

class ExamFragment:Fragment(R.layout.fragment_exam) {

    lateinit var binding: FragmentExamBinding
    val viewModel : AppDataViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentExamBinding.bind(view)

        with(binding){
            tvScore?.text = viewModel.scoreLiveData.value.toString()
            tvQuestion.text = viewModel.qList[viewModel.qCounter].questions
            if (viewModel.qCounter == 0) btnPrev.isEnabled = true

            btnNext.setOnClickListener {
                if (viewModel.qCounter < viewModel.qList.size - 1) {
                    viewModel.qCounter++
                    tvQuestion.text = viewModel.qList[viewModel.qCounter].questions
                    btnPrev.isEnabled = true
                } else {
                    btnNext.isEnabled = false
                }

                btnTrue.isEnabled = true
                btnFalse.isEnabled = true

            }

            btnPrev.setOnClickListener {
                if (viewModel.qCounter > 0) {
                    viewModel.qCounter--
                    tvQuestion.text = viewModel.qList[viewModel.qCounter].questions
                    btnNext.isEnabled = true
                } else {
                    btnPrev.isEnabled = false
                }
                btnFalse.isEnabled = true
                btnTrue.isEnabled = true


            }

            btnTrue.setOnClickListener {
                viewModel.answer = true
                if(viewModel.qList[viewModel.qCounter].cheat){
                    Toast.makeText(requireContext(), "Cheating is wrong!", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
                if (viewModel.qList[viewModel.qCounter].answer == viewModel.answer) {
                    Toast.makeText(requireContext(), "Correct!", Toast.LENGTH_SHORT).show()
                    setScore()
                } else {
                    Toast.makeText(requireContext(), "Incorrect!", Toast.LENGTH_SHORT).show()
                }
                btnTrue.isEnabled = false
                btnFalse.isEnabled = false
            }

            btnFalse.setOnClickListener {
                viewModel.answer = false
                if(viewModel.qList[viewModel.qCounter].cheat){
                    Toast.makeText(requireContext(), "Cheating is wrong!", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
                if (viewModel.qList[viewModel.qCounter].answer == viewModel.answer) {
                    Toast.makeText(requireContext(), "Correct!", Toast.LENGTH_SHORT).show()
                    setScore()
                } else {
                    Toast.makeText(requireContext(), "Incorrect!", Toast.LENGTH_SHORT).show()
                }
                btnTrue.isEnabled = false
                btnFalse.isEnabled = false
            }

            btnCheat.setOnClickListener {
                findNavController().navigate(ExamFragmentDirections.actionExamFragmentToCheatFragment(viewModel.qCounter))
            }


        }

    }

    fun setScore(){

        viewModel.scoreLiveData.value = viewModel.scoreLiveData.value!!.toInt()+1
        viewModel.scoreLiveData.observe(this.viewLifecycleOwner, androidx.lifecycle.Observer {
            //Toast.makeText(context, viewModel.scoreLiveData.value.toString(), Toast.LENGTH_SHORT).show()
            binding.tvScore?.text = viewModel.scoreLiveData.value.toString()
        })
    }
}