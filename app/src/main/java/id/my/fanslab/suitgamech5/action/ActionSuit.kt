package id.my.fanslab.suitgamech5.action

import id.my.fanslab.suitgamech5.data.DataModel
import id.my.fanslab.suitgamech5.data.DataSource

abstract class ActionSuit(private val dataModel: DataModel): Suit {
    abstract fun win(): DataModel
    abstract fun lose(): DataModel

    override fun action(suitName: String, player: String?, enemy: String?): String {
        val suitCom = DataSource.convertStringToData(suitName)

        val isWin = win() == suitCom
        val isLose = lose() == suitCom
        val isDraw = dataModel == suitCom

        return when {
            isWin -> "$player MENANG!"
            isLose -> "$enemy MENANG!"
            isDraw -> "SERI!"
            else -> "Invalid!"
        }
    }

}