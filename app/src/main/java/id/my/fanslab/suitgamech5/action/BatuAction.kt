package id.my.fanslab.suitgamech5.action

import id.my.fanslab.suitgamech5.data.DataModel
import id.my.fanslab.suitgamech5.data.DataSource

class BatuAction: ActionSuit(DataSource.batuData) {
    override fun win(): DataModel {
        val win = DataSource.batuData.win
        return DataSource.convertStringToData(win)
    }

    override fun lose(): DataModel {
        val lose = DataSource.batuData.lose
        return DataSource.convertStringToData(lose)
    }
}