package id.my.fanslab.suitgamech5.data

object DataSource {
    val guntingData = DataModel(
        name = "gunting",
        lose = "batu",
        win = "kertas"
    )

    val batuData = DataModel(
        name = "batu",
        lose = "kertas",
        win = "gunting"
    )

    val kertasData = DataModel(
        name = "kertas",
        lose = "gunting",
        win = "batu"
    )

    val invalidData = DataModel(
        name = "invalid",
        lose = "invalid",
        win = "invalid"
    )

    fun convertStringToData(name: String): DataModel {
        val data = when (name) {
            "gunting" -> guntingData
            "batu" -> batuData
            "kertas" -> kertasData
            else -> invalidData
        }

        return data
    }

    fun getRandomSuit(): DataModel {
        val dataList = listOf(guntingData, batuData, kertasData)
        return dataList.random()
    }
}