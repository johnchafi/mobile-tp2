package ca.ulaval.ima.tp2.ui.abacus

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ca.ulaval.ima.tp2.R
/**
 * A simple [Fragment] subclass.
 * Use the [AbacusFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AbacusFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_abacus, container, false)
    }


}