package marcio.com.calculadora

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class Splash : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar!!.hide()
        setContentView(R.layout.activity_splash)
        changeToMain()
    }
    fun changeToMain(){
        val intent = Intent( this, MainActivity::class.java)

        Handler().postDelayed({
            intent.change()
        }, 2500)

    }
    fun Intent.change() {
        startActivity(this)
        finish()
    }
}
