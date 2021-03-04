package ca.ulaval.ima.tp2.ui.internet

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import ca.ulaval.ima.tp2.R

/**
 * A simple [Fragment] subclass.
 * Use the [InternetFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class InternetFragment : Fragment() {
    private var cont: Context? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        if (container != null) {
            cont = container.context
        }
        return inflater.inflate(R.layout.fragment_internet, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val title: String = getString(R.string.navbar_internet_status)
        (activity as AppCompatActivity).supportActionBar?.title = title
        val button: Button = view.findViewById(R.id.internet_status_button)
        button.setOnClickListener { setInternetStatus(view) }
    }

    fun getConnectionType(context: Context): Int {
        var result = 0
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            cm?.run {
                cm.getNetworkCapabilities(cm.activeNetwork)?.run {
                    if (hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                        result = 2
                    } else if (hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                        result = 1
                    } else result = 0
                }
            }
        } else {
            cm?.run {
                cm.activeNetworkInfo?.run {
                    if (type == ConnectivityManager.TYPE_WIFI) {
                        result = 2
                    } else if (type == ConnectivityManager.TYPE_MOBILE) {
                        result = 1
                    } else result = 0

                }
            }
        }
        return result
    }
    fun setInternetStatus(view: View){
        val text = view.findViewById<TextView>(R.id.internet_status_text)
        val indicator = view.findViewById<ImageView>(R.id.internet_status_indicator)

        val result = cont?.let { getConnectionType(it) }

         if (result == 2){
             text.setText(getString(R.string.internet_status_wifi))
             indicator.setColorFilter(ResourcesCompat.getColor(getResources(), R.color.green_color,null))
         }
         else if(result == 1){
             text.setText(getString(R.string.internet_status_3g_lte))
             indicator.setColorFilter(ResourcesCompat.getColor(getResources(), R.color.green_color,null))
         }
        else{
             text.setText(getString(R.string.internet_status_no_connexion))
             indicator.setColorFilter(ResourcesCompat.getColor(getResources(), R.color.red_color,null))
         }


    }
}