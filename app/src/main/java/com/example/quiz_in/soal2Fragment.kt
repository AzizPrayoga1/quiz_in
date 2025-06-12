package com.example.quiz_in

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.content.Intent
import androidx.activity.OnBackPressedCallback
import com.example.quiz_in.databinding.FragmentSoal2Binding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [soal2Fragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class soal2Fragment : Fragment() {
    private var _binding: FragmentSoal2Binding? = null
    private val binding get() = _binding!!

    private var benar = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSoal2Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Ambil nilai skor dari fragment sebelumnya
        benar = arguments?.getInt("benar", 0) ?: 0
        binding.btnsalah1.setOnClickListener {
            lanjutKeSoal3()
        }

        binding.btnbenar1.setOnClickListener {
            benar += 1
            lanjutKeSoal3()
        }

    }

    private fun lanjutKeSoal3() {
        val bundle = Bundle().apply {
            putInt("benar", benar)
        }
        val nextFragment = soal3Fragment()
        nextFragment.arguments = bundle

        parentFragmentManager.beginTransaction()
            .replace(R.id.container, nextFragment)
            .addToBackStack(null)
            .commit()


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment soal2Fragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            soal2Fragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}