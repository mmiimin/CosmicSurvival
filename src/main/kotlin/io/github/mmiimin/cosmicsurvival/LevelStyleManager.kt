package io.github.mmiimin.cosmicsurvival

import io.github.mmiimin.cosmicsurvival.util.PlayerDataStorage
import net.md_5.bungee.api.ChatColor
import org.bukkit.entity.Player

class LevelStyleManager {

    fun getLevelStyle(code: Int,player: Player):String {
        val p = player.name
        val level = (PlayerDataStorage.map[p + "miningLevel"]!! + PlayerDataStorage.map[p + "farmingLevel"]!! +
                PlayerDataStorage.map[p + "foragingLevel"]!! + PlayerDataStorage.map[p + "combatLevel"]!! + PlayerDataStorage.map[p + "fishingLevel"]!!)
        return when (code) {
            0 -> ""+ChatColor.of("#7F7F7F") + "[" + level + "★]"
            1 -> ""+ChatColor.of("#E77B57") + "[" + level + "★]"
            2 -> ""+ChatColor.of("#D1D1D1") + "[" + level + "★]"
            3 -> ""+ChatColor.of("#F8D547") + "[" + level + "★]"
            4 -> ""+ChatColor.of("#63E989") + "[" + level + "★]"
            5 -> ""+ChatColor.of("#71E0DA") + "[" + level + "★]"
            6 -> ""+ChatColor.of("#F24241") + "[" + level + "★]"
            7 -> ""+ChatColor.of("#CFB76E") + "[" + ChatColor.of("#E2E2E2") + level + "★" + ChatColor.of("#CFB76E") + "]" //WhiteGold
            8 -> ""+ChatColor.of("#DBC8B2") + "[" + ChatColor.of("#AE8A66") + level + "❋" + ChatColor.of("#DBC8B2") + "]" //CaffeLatte
            9 -> ""+ChatColor.of("#4D484D") + "[" + ChatColor.of("#59575A") + level + "❋" + ChatColor.of("#4D484D") + "]" //Netherite
            10 -> ""+ChatColor.of("#1C7E6D") + "[" + ChatColor.of("#30CAAE") + level + "★" + ChatColor.of("#1C7E6D") + "]" //Pearl
            11 -> ""+ChatColor.of("#FEF7E1") + "[" + level + ChatColor.of("#EB4540") + "❋" + ChatColor.of("#FEF7E1") + "]" //StrawberryCake
            12 -> {
                val stringBuilder = StringBuilder()
                val rainbowList = "§c§6§e§a§b§9§c§6§e§a§b§9".chunked(2)
                val everySplit = "[$level✵]".chunked(1)
                for (i in everySplit.indices){
                    stringBuilder.append(rainbowList[i])
                    stringBuilder.append(everySplit[i])
                }
                stringBuilder.toString()
            } //Rainbow
            13 -> ""+ChatColor.of("#86DDFF") + "[" + ChatColor.of("#2DA1FE") + level + ChatColor.of("#4F78FE") + "☽" + ChatColor.of("#374EEE") + "]" //Zenith
            14 -> ""+ChatColor.of("#FFE737") + "[" + ChatColor.of("#FFF07F") + level + ChatColor.of("#ADFCFF") + "✵" + ChatColor.of("#FFF5AD") + "]" //Luxury
            15 -> {//１２３４５６７８９０
                val str = level.toString().replace("1", "１")
                    .replace("2", "２")
                    .replace("3", "３")
                    .replace("4", "４")
                    .replace("5", "５")
                    .replace("6", "６")
                    .replace("7", "７")
                    .replace("8", "８")
                    .replace("9", "９")
                    .replace("0", "０")
                "" + ChatColor.of("#98B2BF") + "[" + ChatColor.of("#F1EFD6") + str + "⚝" + ChatColor.of("#98B2BF") + "]"
            }//Modern
            16 -> ""+ChatColor.of("#F8FF25") + "[" + ChatColor.of("#FEF5F1") + level + ChatColor.of("#00EA0B") + "❋" + ChatColor.of("#F8FF25") + "]" //Nature
            17 -> {
                val stringBuilder = StringBuilder()
                val rainbowList = "§c§a§c§a§c§a§c§a§c§a".chunked(2)
                val everySplit = "[$level◎]".chunked(1)
                for (i in everySplit.indices){
                    stringBuilder.append(rainbowList[i])
                    stringBuilder.append(everySplit[i])
                }
                stringBuilder.toString()
            } //Christmas
            18 -> ""+ChatColor.of("#2E2D00") + "[" + ChatColor.of("#FFFFFF") + level + ChatColor.of("#FFFB00") + "☽" + ChatColor.of("#2E2D00") + "]" //Moonlight
            19 -> {//１２３４５６７８９０
                val str = level.toString().replace("1", "§c♪")
                    .replace("6", "§b♪")
                    .replace("9", "§d♪")
                    .replace("2", "§6♪")
                    .replace("3", "§e♪")
                    .replace("4", "§a♪")
                    .replace("5", "§2♪")
                    .replace("7", "§9♪")
                    .replace("8", "§5♪")
                    .replace("0", "§4♪")
                "" + ChatColor.of("#725D4B") + "[" + str + "§f♪" + ChatColor.of("#725D4B") + "]"
            }//NoteBlock

            else -> "§4ERROR (code:$code)"
        }

    }
}