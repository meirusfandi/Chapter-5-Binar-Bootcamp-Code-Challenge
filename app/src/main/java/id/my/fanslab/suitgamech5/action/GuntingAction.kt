package id.my.fanslab.suitgamech5.action

import id.my.fanslab.suitgamech5.data.DataModel
import id.my.fanslab.suitgamech5.data.DataSource

class GuntingAction: ActionSuit(DataSource.guntingData) {
    override fun win(): DataModel {
        val win = DataSource.guntingData.win
        return DataSource.convertStringToData(win)
    }

    override fun lose(): DataModel {
        val lose = DataSource.guntingData.lose
        return DataSource.convertStringToData(lose)
    }
}