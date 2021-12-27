package gordon.lab.blockchain_demo.util

import android.annotation.SuppressLint
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.*

object Tool {
    @SuppressLint("SimpleDateFormat")
    fun getDateTime(s: String): String? {
        return try {
            val sdf = SimpleDateFormat("hh:mm:ss")
             val netDate = Date(s.toLong() * 1000)
            sdf.format(netDate)
        } catch (e: Exception) {
            e.toString()
        }
    }

    fun PriceTrim(s: String) :String {
        val df = DecimalFormat("##0.00")
        return df.format(s.toFloatOrNull())
    }
}