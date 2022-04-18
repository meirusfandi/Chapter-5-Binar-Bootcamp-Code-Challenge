package id.my.fanslab.suitgamech5.action

import id.my.fanslab.suitgamech5.data.DataModel
import id.my.fanslab.suitgamech5.data.DataSource

class KertasAction: ActionSuit(DataSource.kertasData) {
    override fun win(): DataModel {
        val win = DataSource.kertasData.win
        return DataSource.convertStringToData(win)
    }

    override fun lose(): DataModel {
        val lose = DataSource.kertasData.lose
        return DataSource.convertStringToData(lose)
    }
}