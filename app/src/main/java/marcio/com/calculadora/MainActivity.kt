package marcio.com.calculadora

import android.content.DialogInterface
import android.graphics.Path
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.widget.AlertDialogLayout
import android.text.TextUtils.substring
import android.util.Log
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import net.objecthunter.exp4j.ExpressionBuilder

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        showOne.setOnClickListener { appendOnExpression(string = "1", canClear = true) }
        showTwo.setOnClickListener { appendOnExpression(string = "2", canClear = true) }
        showThree.setOnClickListener { appendOnExpression(string = "3", canClear = true) }
        showFour.setOnClickListener { appendOnExpression(string = "4", canClear = true) }
        showFive.setOnClickListener { appendOnExpression(string = "5", canClear = true) }
        showSix.setOnClickListener { appendOnExpression(string = "6", canClear = true) }
        showSeven.setOnClickListener { appendOnExpression(string = "7", canClear = true) }
        showEight.setOnClickListener { appendOnExpression(string = "8", canClear = true) }
        showNine.setOnClickListener { appendOnExpression(string = "9", canClear = true) }
        showZero.setOnClickListener { appendOnExpression(string = "0", canClear = true) }
        showDot.setOnClickListener { appendOnExpression(string = ".", canClear = true) }

        showDivision.setOnClickListener { appendOnExpression(string = "/", canClear = false) }
        showMulti.setOnClickListener { appendOnExpression(string = "*", canClear = false) }
        showMinus.setOnClickListener { appendOnExpression(string = "-", canClear = false) }
        showPlus.setOnClickListener { appendOnExpression(string = "+", canClear = false) }
        showResult.setOnClickListener { appendOnExpression(string = "=", canClear = false) }

        showClear.setOnClickListener {
            showExpression.text = ""
            showResult.text = ""
        }

        showEquals.setOnClickListener {
            try {
                val string = showExpression.text.toString()
                println(string)

                //Valida se a expressão se dá no padrão [VALOR] - [OPERAÇÃO] - [VALOR]
                 if(string.contains("++")|| string.contains("--")|| string.contains("**")
                     || string.contains("//")){

                     val builder = AlertDialog.Builder(this)
                     with(builder)
                     {
                         setTitle("Operação Inválida")
                         setMessage("Por favor digite uma operação válida.")
                         setNeutralButton("OK", neutralButtonClick)
                         show()
                     }

                 }else{
                     val expression = ExpressionBuilder(showExpression.text.toString()).build()
                     val result = expression.evaluate()
                     val longResult = result.toLong()

                     if (result == longResult.toDouble())
                         showResult.text = longResult.toString()
                     else
                         showResult.text = result.toString()
                 }

            } catch (e: Exception) {
                val builder = AlertDialog.Builder(this)

                with(builder)
                {
                    setTitle("Operação Inválida")
                    setMessage("Por favor digite uma operação válida.")
                    setNeutralButton("OK", neutralButtonClick)
                    show()
                }
                Log.d("Exception", "Operação Inválida" + e.message)


            }
        }
    }

    fun appendOnExpression(string: String, canClear: Boolean) {

        if (showResult.text.isNotEmpty())
            showResult.text = ""

        if (canClear) {
            showResult.text = ""
            showExpression.append(string)
        } else {
            showExpression.append(showResult.text)
            showExpression.append(string)
            showResult.text = ""
        }
    }
    val neutralButtonClick = { dialog: DialogInterface, which: Int ->
        Toast.makeText(applicationContext,
            "Digite uma operação válida", Toast.LENGTH_SHORT).show()
    }
}
