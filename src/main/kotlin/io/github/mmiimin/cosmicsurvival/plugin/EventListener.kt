package io.github.mmiimin.cosmicsurvival.plugin

import org.bukkit.event.EventHandler
import org.bukkit.event.player.PlayerChangedWorldEvent
import org.bukkit.event.player.PlayerJoinEvent

class EventListener {

    @EventHandler
    fun onJoin(event: PlayerJoinEvent) {

    }

    @EventHandler
    fun onTravel(event: PlayerChangedWorldEvent) {
        // if - player's travelAlert is true
        event.player.sendMessage("§a⬛⬛⬛⬛⬛⬛⬛⬛⬛⬛⬛⬛⬛⬛⬛⬛⬛⬛⬛⬛⬛⬛⬛⬛⬛⬛⬛⬛⬛")
        event.player.sendMessage(" ")
        if (event.player.world.name == "world") {
            event.player.sendMessage("§a오버월드§7에 도착했습니다")
            event.player.sendMessage(" §7✔ 안전 지대: 사망 시 인벤토리 아이템이 보존됩니다")
        }
        else if (event.player.world.name == "world_nether") {
            event.player.sendMessage("§c네더§7에 도착했습니다")
            event.player.sendMessage(" §7✕ 위험 지대: 사망 시 인벤토리 아이템이 보존되지 않습니다")
        }
        else if (event.player.world.name == "world_the_end") {
            event.player.sendMessage("§5엔더§7에 도착했습니다")
            event.player.sendMessage(" §7✕ 위험 지대: 사망 시 인벤토리 아이템이 보존되지 않습니다")
            event.player.sendMessage(" §7๑ 봉인: 스킬 경험치를 획득할 수 없습니다")
        }
        else if (event.player.world.name == "world_elysium") {
            event.player.sendMessage("§e엘리시움§7에 도착했습니다")
            event.player.sendMessage(" §7≈ 불안정한 흐름: 겉날개를 사용할 수 없고 대부분의 블록 설치 및 파괴가 불가능합니다")
            event.player.sendMessage(" §7❁ 후유증: 사망 시 인벤토리 아이템이 보존되지만 10시간동안 셀레스티얼 포탈 이용이 제한됩니다")
        }
        else if (event.player.world.name == "world_polytimos") {
            event.player.sendMessage("§d폴리티모스§7에 도착했습니다")
            event.player.sendMessage(" §7≈ 불안정한 흐름: 겉날개를 사용할 수 없고 대부분의 블록 설치 및 파괴가 불가능합니다")
            event.player.sendMessage(" §7❁ 후유증: 사망 시 인벤토리 아이템이 보존되지만 10시간동안 셀레스티얼 포탈 이용이 제한됩니다")
        }
        else if (event.player.world.name == "world_ruina") {
            event.player.sendMessage("§c루이나§7에 도착했습니다")
            event.player.sendMessage(" §7≈ 불안정한 흐름: 겉날개를 사용할 수 없고 대부분의 블록 설치 및 파괴가 불가능합니다")
            event.player.sendMessage(" §7❁ 후유증: 사망 시 인벤토리 아이템이 보존되지만 10시간동안 셀레스티얼 포탈 이용이 제한됩니다")
        }
        else if (event.player.world.name == "world_glacial") {
            event.player.sendMessage("§b글레이셜§7에 도착했습니다")
            event.player.sendMessage(" §7≈ 불안정한 흐름: 겉날개를 사용할 수 없고 대부분의 블록 설치 및 파괴가 불가능합니다")
            event.player.sendMessage(" §7❁ 후유증: 사망 시 인벤토리 아이템이 보존되지만 10시간동안 셀레스티얼 포탈 이용이 제한됩니다")
            event.player.sendMessage(" §b❁ 한기: §7불 근처에 있지 않다면 한기 스택이 쌓입니다, 스택이 쌓일수록 이동속도가 느려지고 받는 피해가 증가합니다")
        }
        else if (event.player.world.name == "world_another_dimension") {
            event.player.sendMessage("§4---§7에 도착했습니다")
            event.player.sendMessage(" §7≈ 불안정한 흐름: 겉날개를 사용할 수 없고 대부분의 블록 설치 및 파괴가 불가능합니다")
            event.player.sendMessage(" §7❁ 후유증: 사망 시 인벤토리 아이템이 보존되지만 10시간동안 셀레스티얼 포탈 이용이 제한됩니다")
        }
        event.player.sendMessage(" ")
        event.player.sendMessage("§a⬛⬛⬛⬛⬛⬛⬛⬛⬛⬛⬛⬛⬛⬛⬛⬛⬛⬛⬛⬛⬛⬛⬛⬛⬛⬛⬛⬛⬛")
    }

}