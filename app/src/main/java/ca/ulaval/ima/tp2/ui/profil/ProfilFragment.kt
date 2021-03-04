package ca.ulaval.ima.tp2.ui.profil

import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import ca.ulaval.ima.tp1.User
import ca.ulaval.ima.tp2.MainActivity
import ca.ulaval.ima.tp2.R


/**
 * A simple [Fragment] subclass.
 * Use the [ProfilFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ProfilFragment : Fragment() {

    fun setInformation(view: View, user: User){
        val firstName: TextView = view.findViewById(R.id.firstname_input)
        val lastName: TextView = view.findViewById(R.id.lastname_input)
        val birthdate: TextView = view.findViewById(R.id.birthdate_input)
        val sex: TextView = view.findViewById(R.id.sex_input)
        val program: TextView = view.findViewById(R.id.program_input)

        firstName.setText(user.prenom)
        lastName.setText(user.nom)
        sex.setText(user.sex)
        program.setText(user.program)
        birthdate.setText(user.bd)
    }

    fun getUserInformation (view: View){

        var user : User? = null
        if (this.arguments != null) {
             user = this.requireArguments().getParcelable<Parcelable>("user_parcel") as User?
            if (activity != null) {
                //(activity as MainActivity?).setBackButton()
            }

        }
        if (user != null) {
            this.setInformation(view,user)
        }

    }

    override fun onViewCreated(view: View,savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val title: String = getString(R.string.title_profil)
        (activity as AppCompatActivity).supportActionBar?.title = title
        this.getUserInformation(view)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profil, container, false)
    }
}