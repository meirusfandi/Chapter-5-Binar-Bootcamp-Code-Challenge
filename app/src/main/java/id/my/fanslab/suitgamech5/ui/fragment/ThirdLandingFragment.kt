package id.my.fanslab.suitgamech5.ui.fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import id.my.fanslab.suitgamech5.R
import id.my.fanslab.suitgamech5.action.OnDataPass
import id.my.fanslab.suitgamech5.databinding.FragmentThirdLandingBinding

class ThirdLandingFragment : Fragment() {

    lateinit var dataPass: OnDataPass
    private var _binding: FragmentThirdLandingBinding? = null
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentThirdLandingBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val playerName = binding?.edtPlayerName?.text.toString()
        dataPass.onDataPass(playerName)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        dataPass = context as OnDataPass
    }
}