package com.clearscore.report.utils

import java.lang.Exception
import java.math.BigDecimal
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols

class CurrencyUtil {

    companion object {
        fun formatCurrency(amount: String): String? {
            var formattedAmount = "0.00"
            try {
                val decimalFormatSymbols = DecimalFormatSymbols()
                decimalFormatSymbols.decimalSeparator = '.'
                decimalFormatSymbols.groupingSeparator = ' '
                formattedAmount = DecimalFormat("#,##0.00", decimalFormatSymbols).format((BigDecimal(amount)))
            }
            catch (e: Exception){}
            finally {
                return formattedAmount
            }
        }
    }


}