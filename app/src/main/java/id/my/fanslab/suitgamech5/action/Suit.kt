package id.my.fanslab.suitgamech5.action

interface Suit {
    fun action(suitName: String, player: String?, enemy: String?): String
}