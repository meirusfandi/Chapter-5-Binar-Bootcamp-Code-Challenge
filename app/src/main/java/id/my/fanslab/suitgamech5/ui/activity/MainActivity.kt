package id.my.fanslab.suitgamech5.ui.activity

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import android.view.Window
import android.widget.Button
import android.widget.Toast
import androidx.core.view.isVisible
import com.google.android.material.textview.MaterialTextView
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

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var players = ""
        var comp: String

        val playerName =  intent.getStringExtra(PLAYER)
        val enemy = intent.getStringExtra(ENEMY)
        binding.tvPlayer1.text = playerName
        binding.tvCom.text = enemy

        binding.playerBatu.setOnClickListener {
            binding.playerBatu.background = getDrawable(R.drawable.player_background)
            binding.playerGunting.background = getDrawable(R.drawable.player_unselected)
            binding.playerKertas.background = getDrawable(R.drawable.player_unselected)
            players = "batu"

            if (enemy == "CPU") {
                comp = getRandom(enemy)
                getResult(players, comp, playerName, enemy)
            }
        }

        binding.playerGunting.setOnClickListener {
            binding.playerGunting.background = getDrawable(R.drawable.player_background)
            binding.playerBatu.background = getDrawable(R.drawable.player_unselected)
            binding.playerKertas.background = getDrawable(R.drawable.player_unselected)
            players = "gunting"

            if (enemy == "CPU") {
                comp = getRandom(enemy)
                getResult(players, comp, playerName, enemy)
            }
        }

        binding.playerKertas.setOnClickListener {
            binding.playerKertas.background = getDrawable(R.drawable.player_background)
            binding.playerBatu.background = getDrawable(R.drawable.player_unselected)
            binding.playerGunting.background = getDrawable(R.drawable.player_unselected)
            players = "kertas"
            if (enemy == "CPU") {
                comp = getRandom(enemy)
                getResult(players, comp, playerName, enemy)
            }
        }
        binding.comBatu.setOnClickListener {
            comp = "batu"
            Toast.makeText(
                applicationContext,
                "$enemy Memilih Batu",
                Toast.LENGTH_LONG
            ).show()
            binding.comKertas.background = getDrawable(R.drawable.player_unselected)
            binding.comBatu.background = getDrawable(R.drawable.player_background)
            binding.comGunting.background = getDrawable(R.drawable.player_unselected)
            getResult(players, comp, playerName, enemy)
        }

        binding.comGunting.setOnClickListener {
            comp = "gunting"
            Toast.makeText(
                applicationContext,
                "$enemy Memilih Gunting",
                Toast.LENGTH_LONG
            ).show()
            binding.comKertas.background = getDrawable(R.drawable.player_unselected)
            binding.comBatu.background = getDrawable(R.drawable.player_unselected)
            binding.comGunting.background = getDrawable(R.drawable.player_background)
            getResult(players, comp, playerName, enemy)
        }

        binding.comKertas.setOnClickListener {
            comp = "kertas"
            Toast.makeText(
                applicationContext,
                "$enemy Memilih Kertas",
                Toast.LENGTH_LONG
            ).show()
            binding.comKertas.background = getDrawable(R.drawable.player_background)
            binding.comBatu.background = getDrawable(R.drawable.player_unselected)
            binding.comGunting.background = getDrawable(R.drawable.player_unselected)
            getResult(players, comp, playerName, enemy)
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

    @SuppressLint("UseCompatLoadingForDrawables")
    private fun getRandom(enemy: String): String {
        val suitDataComp = DataSource.getRandomSuit()
        when (suitDataComp.name) {
            "kertas" -> {
                Toast.makeText(
                    applicationContext,
                    "$enemy Memilih Kertas",
                    Toast.LENGTH_LONG
                ).show()
                binding.comKertas.background = getDrawable(R.drawable.player_background)
                binding.comBatu.background = getDrawable(R.drawable.player_unselected)
                binding.comGunting.background = getDrawable(R.drawable.player_unselected)
            }
            "batu" -> {
                Toast.makeText(
                    applicationContext,
                    "$enemy Memilih Batu",
                    Toast.LENGTH_LONG
                ).show()
                binding.comKertas.background = getDrawable(R.drawable.player_unselected)
                binding.comBatu.background = getDrawable(R.drawable.player_background)
                binding.comGunting.background = getDrawable(R.drawable.player_unselected)
            }
            "gunting" -> {
                Toast.makeText(
                    applicationContext,
                    "$enemy Memilih Gunting",
                    Toast.LENGTH_LONG
                ).show()
                binding.comKertas.background = getDrawable(R.drawable.player_unselected)
                binding.comBatu.background = getDrawable(R.drawable.player_unselected)
                binding.comGunting.background = getDrawable(R.drawable.player_background)
            }
            else -> {
                binding.comKertas.background = getDrawable(R.drawable.player_unselected)
                binding.comBatu.background = getDrawable(R.drawable.player_unselected)
                binding.comGunting.background = getDrawable(R.drawable.player_unselected)
            }
        }
        return suitDataComp.name
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private fun getResult(player: String, comp: String, playerName: String?, player2: String?) {
        println("Player choose : $player")
        println("Computer get : $comp")
        val suitPlayer = when (player) {
            "kertas" -> KertasAction()
            "batu" -> BatuAction()
            "gunting" -> GuntingAction()
            else -> InvalidAction()
        }

        val result = suitPlayer.action(comp, playerName, player2)
        println("Result is : $result")
        showDialogResult(playerName, result)

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

    private fun showDialogResult(player: String?, message: String?) {
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        dialog.setContentView(R.layout.dialog_result)
        val body = dialog.findViewById<MaterialTextView>(R.id.tv_result_game)
        body.text = message
        val backToMenu = dialog.findViewById<Button>(R.id.btn_back_to_menu)
        val backPlayAgain = dialog.findViewById<Button>(R.id.btn_play_again)

        backToMenu.setOnClickListener {
            val intent = Intent(applicationContext, ChooseEnemyActivity::class.java)
            intent.putExtra(ChooseEnemyActivity.PLAYER, player)
            startActivity(intent)
        }

        backPlayAgain.setOnClickListener {
            setupLayout()
            dialog.dismiss()
        }
        dialog.show()
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