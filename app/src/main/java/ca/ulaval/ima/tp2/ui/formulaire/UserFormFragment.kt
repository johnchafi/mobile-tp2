package ca.ulaval.ima.tp2.ui.formulaire

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ca.ulaval.ima.tp2.R

/**
 * A simple [Fragment] subclass.
 * Use the [UserFormFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class UserFormFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user_form, container, false)
    }


}