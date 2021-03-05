package ca.ulaval.ima.tp2.ui.formulaire

import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import ca.ulaval.ima.tp1.User
import ca.ulaval.ima.tp2.R
import ca.ulaval.ima.tp2.ui.profil.ProfilFragment
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

/**
 * A simple [Fragment] subclass.
 * Use the [UserFormFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class UserFormFragment : Fragment() {
    private var cont: Context? = null
    private val datePattern: String = "yyyy-MM-dd"

    fun getUserInformation(view:View): User {
        val firstName:TextView  = view.findViewById(R.id.firstname_input)
        val lastName:TextView = view.findViewById(R.id.lastname_input)
        val birthdate:TextView = view.findViewById(R.id.birthdate_input)
        val sex_Group: RadioGroup = view.findViewById(R.id.sex_radiogroup)
        val sex:RadioButton = view.findViewById(sex_Group.checkedRadioButtonId)
        val program: Spinner = view.findViewById(R.id.program_choice)
        val user:User = User(firstName.text.toString(), lastName.text.toString(),
        sex.text.toString(), program.selectedItem.toString(),birthdate.text.toString())
        return user
    }

    fun showAlertMessage(title: String, message: String ){
        val alert: AlertDialog? = cont?.let { AlertDialog.Builder(it).create() }
        alert?.setTitle(title)
        alert?.setMessage(message)
        alert?.setButton(AlertDialog.BUTTON_NEUTRAL, "OK") { dialog, which -> dialog.dismiss() }
        alert?.show();
    }

    fun isInputValid(view: View):Boolean{
        val user: User = this.getUserInformation(view)
        var error: String = ""
        if (user.prenom.equals("")) error = "Prénom ne doit pas être vide !"
        if (error.equals("") && user.nom.equals("")) {
            error = "Nom ne doit pas être vide !";
        }
        Log.i("yes ", user.bd.substring(0, 4))
        if (error.equals("") && user.bd.equals("") || isDateValide (user.bd)) {
            error = "La date n'est pas valide !"
        }
        if (!error.equals("")) {
            this.showAlertMessage("Error", error)
            return false
        }
        return true

    }

    fun isDateValide(par: String): Boolean{
        if(par.substring(0,4).toInt() < 2021 ) return false;
        else return true;

    }

    fun SubmitForm(view: View){
        if (this.isInputValid(view)){
            val user: User = this.getUserInformation(view)
            val bundle:Bundle = Bundle()
            bundle.putParcelable("user_parcel", user)
            val profilFragment:ProfilFragment = ProfilFragment()
            profilFragment.setArguments(bundle)

            if (activity != null) {
                val ft: FragmentTransaction =
                    requireActivity().supportFragmentManager.beginTransaction()
                ft.replace(R.id.nav_host_fragment, profilFragment).addToBackStack("tag")
                    .commit()
            }
        }
    }
    fun setSpinnerValue(view:View){
        val spinner: Spinner = view.findViewById(R.id.program_choice)
        val adapter: ArrayAdapter<CharSequence> = cont?.let {
            ArrayAdapter.createFromResource(
                it, R.array.program_choice,
                android.R.layout.simple_spinner_item)
        } as ArrayAdapter<CharSequence>

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        var default: Int = adapter.getPosition(getString(R.string.program))
        spinner.setAdapter(adapter)
        spinner.setSelection(default);
    }

    fun setDatePicker(view: View){
        val text: EditText = view.findViewById(R.id.birthdate_input)
        val datePickerDialog = cont?.let {
            DatePickerDialog(
                it,
                OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                    val newDate: Calendar = Calendar.getInstance()
                    newDate.set(year, monthOfYear, dayOfMonth)
                    val df: DateFormat = SimpleDateFormat(datePattern, Locale.CANADA_FRENCH)
                    text.setText(df.format(newDate.getTime()))
                },
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
            )
        }

        text.setOnClickListener {
            if (datePickerDialog != null) {
                datePickerDialog.show()
            }
        }
    }

    fun submit(view: View){
        val button: Button = view.findViewById(R.id.submit_button)
        button.setOnClickListener { SubmitForm(view) }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //calla functions of this fragment
        val title: String = getString(R.string.title_form)
        (activity as AppCompatActivity).supportActionBar?.title = title
        this.setDatePicker(view)
        this.setSpinnerValue(view)
        this.submit(view)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (container != null) {
            cont = container.context
        }
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user_form, container, false)
    }


}