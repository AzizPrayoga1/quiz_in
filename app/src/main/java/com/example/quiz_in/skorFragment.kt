package com.example.quiz_in

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.quiz_in.databinding.FragmentSkorBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [skorFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class skorFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var _binding: FragmentSkorBinding? = null
    private val binding get() = _binding!!

    private var benar = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentSkorBinding.inflate(inflater,container,false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Ambil nilai skor dari fragment sebelumnya
        benar = arguments?.getInt("benar", 0) ?: 0
        val hasil = benar * 5
        // Tampilkan skor di TextView
        binding.txtskor.text = "$hasil"
        binding.btnlagi.setOnClickListener {
            lanjutlagi()
        }

        binding.btnexit.setOnClickListener {
            benar = 0
            exit()
        }
    }

    private fun lanjutlagi() {
        val bundle = Bundle().apply {
            putInt("benar", benar)
        }
        val nextFragment = soal1Fragment()
        nextFragment.arguments = bundle
        parentFragmentManager.beginTransaction()
            .replace(R.id.container, nextFragment)
            .addToBackStack(null)
            .commit()

    }
    private fun exit() {
        val intent = Intent(requireContext(), MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
        requireActivity().finish()

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
         * @return A new instance of fragment skorFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            skorFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}