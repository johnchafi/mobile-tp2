package ca.ulaval.ima.tp2.ui.abacus

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
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
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val title: String = getString(R.string.title_abacus)
        (activity as AppCompatActivity).supportActionBar?.title = title

        var firstValue: Int = 1
        val firstV:TextView = view.findViewById(R.id.textView_display1)
        var secondValue : Int = 2;

        //var secondValue : Int = 1;


        val seekbar: SeekBar? = view.findViewById(R.id.seekBar_first)
        seekbar?.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seek: SeekBar?, prog: Int, p2: Boolean) {

                firstValue = prog
                firstV.setText("Valeur $firstValue")
                val seekbar3: SeekBar = view.findViewById(R.id.seekBar_third)
                val thirdV:TextView = view.findViewById(R.id.textView_display3)
                var thirdValue: Int = firstValue*secondValue;
                thirdV.setText("Valeur $thirdValue")
                seekbar3.setProgress(thirdValue)
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {
                //TODO("Not yet implemented")
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
               // TODO("Not yet implemented")
            }
        })


        val secondV:TextView = view.findViewById(R.id.textView_display2)
        val seekbar2: SeekBar? = view.findViewById(R.id.seekBar_second)
        seekbar2?.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seek: SeekBar?, prog2: Int, p2: Boolean) {

                secondValue = prog2
                secondV.setText("Valeur "+secondValue)

                val seekbar3: SeekBar = view.findViewById(R.id.seekBar_third)
                seekbar3.isEnabled = false
                val thirdV:TextView = view.findViewById(R.id.textView_display3)
                var thirdValue: Int = firstValue * secondValue;
                thirdV.setText("Valeur "+thirdValue)
                seekbar3.setProgress(thirdValue)

            }

            override fun onStartTrackingTouch(p0: SeekBar?) {
                //TODO("Not yet implemented")
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
                // TODO("Not yet implemented")
            }
        })



    }
}