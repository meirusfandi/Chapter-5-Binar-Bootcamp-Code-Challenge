package id.my.fanslab.suitgamech5.ui.activity

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.view.isVisible
import id.my.fanslab.suitgamech5.R
import id.my.fanslab.suitgamech5.action.BatuAction
import id.my.fanslab.suitgamech5.action.GuntingAction
import id.my.fanslab.suitgamech5.action.InvalidAction
import id.my.fanslab.suitgamech5.action.KertasAction
import id.my.fanslab.suitgamech5.data.DataSource
import id.my.fanslab.suitgamech5.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var players = ""
        val comp: String

        val playerName =  intent.getStringExtra(PLAYER)
        val enemy = intent.getStringExtra(ENEMY)
        binding.tvPlayer1.text = playerName
        binding.tvCom.text = enemy

        binding.playerBatu.setOnClickListener {
            binding.playerBatu.background = getDrawable(R.drawable.player_background)
            binding.playerGunting.background = getDrawable(R.drawable.player_unselected)
            binding.playerKertas.background = getDrawable(R.drawable.player_unselected)
            players = "batu"
        }

        binding.playerGunting.setOnClickListener {
            binding.playerGunting.background = getDrawable(R.drawable.player_background)
            binding.playerBatu.background = getDrawable(R.drawable.player_unselected)
            binding.playerKertas.background = getDrawable(R.drawable.player_unselected)
            players = "gunting"
        }

        binding.playerKertas.setOnClickListener {
            binding.playerKertas.background = getDrawable(R.drawable.player_background)
            binding.playerBatu.background = getDrawable(R.drawable.player_unselected)
            binding.playerGunting.background = getDrawable(R.drawable.player_unselected)
            players = "kertas"
        }

        if (enemy == "CPU") {
            comp = getRandom()
            getResult(players, comp, enemy)
        } else {
            comp = getPlayer2()
            getResult(players, comp, enemy)
        }

        binding.btnRefresh.setOnClickListener {
            setupLayout()
        }

        binding.btnClose.setOnClickListener {
            val intent = Intent(this@MainActivity, ChooseEnemyActivity::class.java)
            intent.putExtra(ChooseEnemyActivity.PLAYER, playerName)
            startActivity(intent)
        }
    }

    private fun getRandom(): String {
        val suitDataComp = DataSource.getRandomSuit()
        return suitDataComp.name
    }

    private fun getPlayer2(): String {
        var comp = ""
        binding.comBatu.setOnClickListener {
            comp = "batu"
        }

        binding.comGunting.setOnClickListener {
            comp = "gunting"
        }

        binding.comKertas.setOnClickListener {
            comp = "kertas"
        }

        return comp
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private fun getResult(player: String, comp: String, player2: String?) = with(binding) {
        println("Player choose : $player")
        println("Computer get : $comp")
        val suitPlayer = when (player) {
            "kertas" -> KertasAction()
            "batu" -> BatuAction()
            "gunting" -> GuntingAction()
            else -> InvalidAction()
        }
        when (comp) {
            "kertas" -> {
                Toast.makeText(applicationContext, "$player2 Memilih Kertas", Toast.LENGTH_LONG).show()
                comKertas.background = getDrawable(R.drawable.player_background)
                comBatu.background = getDrawable(R.drawable.player_unselected)
                comGunting.background = getDrawable(R.drawable.player_unselected)
            }
            "batu" -> {
                Toast.makeText(applicationContext, "$player2 Memilih Batu", Toast.LENGTH_LONG).show()
                comKertas.background = getDrawable(R.drawable.player_unselected)
                comBatu.background = getDrawable(R.drawable.player_background)
                comGunting.background = getDrawable(R.drawable.player_unselected)
            }
            "gunting" -> {
                Toast.makeText(applicationContext, "$player2 Memilih Gunting", Toast.LENGTH_LONG).show()
                comKertas.background = getDrawable(R.drawable.player_unselected)
                comBatu.background = getDrawable(R.drawable.player_unselected)
                comGunting.background = getDrawable(R.drawable.player_background)
            }
            else -> {
                comKertas.background = getDrawable(R.drawable.player_unselected)
                comBatu.background = getDrawable(R.drawable.player_unselected)
                comGunting.background = getDrawable(R.drawable.player_unselected)
            }
        }

        val result = suitPlayer.action(comp)
        println("Result is : $result")
        tvResult.text = result
        tvResult.background = if (result == "DRAW") getDrawable(R.drawable.result_draw)
        else getDrawable(R.drawable.result_win)

        mapsVisibility(true)
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private fun setupLayout() = with(binding) {
        playerGunting.background = getDrawable(R.drawable.player_unselected)
        playerBatu.background = getDrawable(R.drawable.player_unselected)
        playerKertas.background = getDrawable(R.drawable.player_unselected)
        comGunting.background = getDrawable(R.drawable.player_unselected)
        comKertas.background = getDrawable(R.drawable.player_unselected)
        comBatu.background = getDrawable(R.drawable.player_unselected)
        mapsVisibility()
    }

    private fun mapsVisibility(isResult: Boolean = false) = with(binding) {
        tvVs.isVisible = isResult.not()
        tvResult.isVisible = isResult
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        const val PLAYER = "player"
        const val ENEMY = "enemy"
    }
}