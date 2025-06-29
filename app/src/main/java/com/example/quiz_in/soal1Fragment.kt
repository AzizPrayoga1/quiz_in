package com.example.quiz_in

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.quiz_in.databinding.FragmentSoal1Binding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [soal1Fragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class soal1Fragment : Fragment() {
    private var _binding: FragmentSoal1Binding? = null
    private val binding get() = _binding!!
    private var benar = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSoal1Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Pas tombol salah ditekan, langsung lanjut ke soal 2 tanpa tambah nilai benar
        binding.btnsalah1.setOnClickListener {
            lanjutKeSoal2()
        }

        // Pas tombol benar ditekan, tambah nilai benar lalu lanjut ke soal 2
        binding.btnbenar1.setOnClickListener {
            benar += 1
            lanjutKeSoal2()
        }
    }

    // Fungsi untuk pindah ke fragment soal 2 dengan membawa data jumlah benar
    private fun lanjutKeSoal2() {
        val bundle = Bundle()
        // Masukkan nilai benar ke dalam bundle supaya bisa dipakai di fragment berikutnya
        bundle.putInt("benar", benar)

        val nextFragment = soal2Fragment()
        // Kirim data bundle ke fragment soal berikutnya
        nextFragment.arguments = bundle

        parentFragmentManager.beginTransaction()
            .replace(R.id.container, nextFragment)
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
         * @return A new instance of fragment soal1Fragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            soal1Fragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}