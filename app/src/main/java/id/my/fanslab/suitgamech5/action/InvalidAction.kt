package id.my.fanslab.suitgamech5.action

import id.my.fanslab.suitgamech5.data.DataModel
import id.my.fanslab.suitgamech5.data.DataSource

class InvalidAction: ActionSuit(DataSource.invalidData) {
    override fun win(): DataModel {
        return DataSource.invalidData
    }

    override fun lose(): DataModel {
        return DataSource.invalidData
    }
}