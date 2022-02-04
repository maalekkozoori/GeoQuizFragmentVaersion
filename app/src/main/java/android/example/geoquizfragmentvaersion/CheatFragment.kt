package android.example.geoquizfragmentvaersion

import android.example.geoquizfragmentvaersion.databinding.FragmentCheatBinding
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs


class CheatFragment:Fragment(R.layout.fragment_cheat) {

    lateinit var binding: FragmentCheatBinding
    val navArgs : CheatFragmentArgs by navArgs()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentCheatBinding.bind(view)




        with(binding){

            btnShowAnswer.setOnClickListener {
                tvAnswer.text = qList[navArgs.questionNumber].answer.toString()
                qList[navArgs.questionNumber].cheat = true

            }

        }
    }
}