package id.my.fanslab.suitgamech5.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import id.my.fanslab.suitgamech5.databinding.ActivityChooseEnemyBinding

class ChooseEnemyActivity : AppCompatActivity() {

    private var _binding: ActivityChooseEnemyBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityChooseEnemyBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val playerName = intent.getStringExtra(PLAYER)
        val snackBar = Snackbar.make(
            binding.root,
            "Selamat Datang $playerName",
            Snackbar.LENGTH_LONG
        )

        snackBar.setAction("Tutup") {
            snackBar.dismiss()
        }
        snackBar.show()

        "$playerName vs CPU".also { binding.tvPlayerVsCom.text = it }
        "$playerName vs Pemain".also { binding.tvPlayerVsPlayer.text = it }

        binding.playerVsCom.setOnClickListener {
            val intent = Intent(this@ChooseEnemyActivity, MainActivity::class.java)
            intent.putExtra(MainActivity.PLAYER, playerName)
            intent.putExtra(MainActivity.ENEMY, "CPU")
            startActivity(intent)
        }

        binding.playerVsPlayer.setOnClickListener {
            val intent = Intent(this@ChooseEnemyActivity, MainActivity::class.java)
            intent.putExtra(MainActivity.PLAYER, playerName)
            intent.putExtra(MainActivity.ENEMY, "Pemain 2")
            startActivity(intent)
        }
    }

    companion object {
        const val PLAYER = "player"
    }
}