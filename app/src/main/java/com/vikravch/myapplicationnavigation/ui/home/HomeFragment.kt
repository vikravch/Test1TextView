package com.vikravch.myapplicationnavigation.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.vikravch.myapplicationnavigation.databinding.FragmentHomeBinding
import com.vikravch.myapplicationnavigation.ui.extension.setTextWithSpan

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.textView1.setTextWithSpan(
            "Charlotte Perriand’s “La maison au bord de l’eau”"
        )
        binding.textView2.setTextWithSpan(
            "Hello world",
        )
        binding.textView3.setTextWithSpan(
            "Charlotte Perriand’s “La maison au bord de l’eau” Charlotte Perriand’s “La maison au bord de l’eau” Charlotte Perriand’s “La maison au bord de l’eau”",
        )
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}