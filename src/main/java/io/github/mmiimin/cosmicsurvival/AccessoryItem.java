package io.github.mmiimin.cosmicsurvival;

import io.github.mmiimin.cosmicsurvival.util.ItemManager;
import io.github.mmiimin.cosmicsurvival.util.PlayerDataStorage;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class AccessoryItem {
    ItemManager item = new ItemManager();

    public ItemStack createAccessory(Player player, int code, int mode){
        int currentLevel = PlayerDataStorage.accessory.get(player.getName()+code);
        if (mode==10) {
            currentLevel++;
        }
        String url;
        String accessoryName;
        List<String> accessoryLore = new ArrayList<>();
        String accLevel;
        switch (currentLevel) {
            case 0 -> accLevel = "§7";
            case 1 -> accLevel = "§f✦ ";
            case 2 -> accLevel = "§b✦ 빛나는 ";
            case 3 -> accLevel = "§e✦ 완벽한 ";
            default -> accLevel = "§4✦ 오류난 ";
        }
        accessoryLore.add("§7");
        switch (code) {
            case 0 -> {
                url = "2312e1b93e3544d0ed01e04716e5e2f3ea8ed799ae0253e4b1824de8b3002f64";
                accessoryName = "§7착용 안 함";
                accessoryLore.add("§7장신구를 착용하지 않습니다");
            }
            case 1 -> {
                url = "98ec21d20f1aaf5635f48beda88626403c6385b81673fb7cfa7ff82179c63e39";
                accessoryName = "미니 티타늄 드릴";
                accessoryLore.add("§c⚡ 사용 효과: §f메가 드릴");
                if (currentLevel <= 1) {
                    accessoryLore.add("§7성급함 III를 10초간 받습니다");
                    accessoryLore.add("§3⌛ 재사용 대기시간: 60초");
                }
                else if (currentLevel == 2) {
                    accessoryLore.add("§7성급함 IV를 12.5초간 받습니다");
                    accessoryLore.add("§3⌛ 재사용 대기시간: 50초");
                }
                else if (currentLevel == 3) {
                    accessoryLore.add("§7성급함 V를 15초간 받습니다");
                    accessoryLore.add("§3⌛ 재사용 대기시간: 40초");
                }
            }
            case 2 -> {
                url = "56311387e750bf67b5c8720df8327d20ad7854412b563bf1ed8a49c9cc2024";
                accessoryName = "황금 반지";
                accessoryLore.add("§7● 최대 체력 +"+ Math.max(1,currentLevel));
                accessoryLore.add("§7");
                accessoryLore.add("§c⚡ 사용 효과: §f로얄 실드");
                if (currentLevel <= 1) {
                    accessoryLore.add("§7흡수 I을 2초간 받습니다");
                    accessoryLore.add("§3⌛ 재사용 대기시간: 20초");
                }
                else if (currentLevel == 2) {
                    accessoryLore.add("§7흡수 II를 2초간 받습니다");
                    accessoryLore.add("§3⌛ 재사용 대기시간: 17초");
                }
                else if (currentLevel == 3) {
                    accessoryLore.add("§7흡수 III를 2초간 받습니다");
                    accessoryLore.add("§3⌛ 재사용 대기시간: 14초");
                }
            }
            case 3 -> {
                url = "da8524274a65d8a80a0f44e103e2021e27c8916e965aa11f665ac0c49be40ec6";
                accessoryName = "네더라이트 반지";
                accessoryLore.add("§7● 최대 체력 +"+ Math.max(2,currentLevel+1));
                accessoryLore.add("§7● 체력이 8 이하일 때 받는 피해가 "+ (Math.max(1,currentLevel)*10+15) +"% 감소한다");
            }
            case 4 -> {
                url = "7d16ae951120394f368f2250b7c3ad3fb12cea55ec1b2db5a94d1fb7fd4b6fa";
                accessoryName = "새벽의 수정구";
                accessoryLore.add("§7● 최대 체력인 엔티티에게 직접 타격 시 "+ (Math.max(1,currentLevel)*35) +"% 추가 피해");
            }
            case 5 -> {
                url = "d1df891f84d3cc735d3ec6f7244583377cd4d908eb6ff05c45339807180820d";
                accessoryName = "옵시디언 펜던트";
                accessoryLore.add("§7● 웅크리고 있을 때 받는 피해가 "+ (Math.max(1,currentLevel)*8+8) +"% 감소한다");
            }
            case 6 -> {
                url = "97c4036f80bf3609180c7c4a9568853fd815f154ad8438b92f3851019835b070";
                accessoryName = "정오의 수정구";
                accessoryLore.add("§7● 체력이 "+ (Math.max(1,currentLevel)+2)+" 이하인 엔티티를 직접 타격 시 즉사시킨다");
            }
            case 7 -> {
                url = "cfbb95502e0fd0dba60bbe5818e2fd5278eda2999cc2d78109c2ca577557a0a6";
                accessoryName = "서리바람";
                accessoryLore.add("§7● 이동 속도 +"+Math.max(1,currentLevel)*2+"%p");
                accessoryLore.add("§7");
                accessoryLore.add("§c⚡ 사용 효과: §f돌풍");
                accessoryLore.add("§7보고 있는 방향으로 도약합니다");
                accessoryLore.add("§3⌛ 재사용 대기시간: "+(20-Math.max(1,currentLevel)*5) +"초");
            }
            case 8 -> {
                url = "151599f66e83da555cf9b8b7e5a379d0deab21c2eee909d1837323db0893bf38";
                accessoryName = "금빛 탄환";
                accessoryLore.add("§7● 화살로 주는 피해량이 "+(Math.max(1,currentLevel)*10+10)+"% 증가한다");
                accessoryLore.add("§7● 화살로 35블록 이상의 거리에서 피해를 줄 시");
                accessoryLore.add("§7피해량이 추가로 "+(Math.max(1,currentLevel)*15+15)+"% 증가한다");
            }
            case 9 -> {
                url = "7717933c40fbf936aa9288513efe19bda4601efc0e4ecad2e023b0c1d28444b";
                accessoryName = "플레임 펜던트";
                accessoryLore.add("§7● 불타는 엔티티에게 직접 타격 시 피해량 "+(Math.max(1,currentLevel)*5+5)+"% 증가");
                accessoryLore.add("§7");
                accessoryLore.add("§c⚡ 사용 효과: §f플레임 볼텍스");
                accessoryLore.add("§71초간 정신을 집중한 뒤 주변 "+Math.max(1,currentLevel)*2+"블록 이내의 엔티티에게");
                accessoryLore.add("§7"+(Math.max(1,currentLevel)*10+20)+"피해를 주고 불태운다");
                accessoryLore.add("§7");
                accessoryLore.add("§3⌛ 재사용 대기시간: "+(56-Math.max(1,currentLevel)*2) +"초");
            }
            case 10 -> {
                url = "cfbb95502e0fd0dba60bbe5818e2fd5278eda2999cc2d78109c2ca577557a0a6";
                accessoryName = "서리바";
                accessoryLore.add("§7● 이동 속도 +"+Math.max(1,currentLevel)*2+"%p");
                accessoryLore.add("§7");
                accessoryLore.add("§c⚡ 사용 효과: §f돌풍");
                accessoryLore.add("§7보고 있는 방향으로 도약합니다");
                accessoryLore.add("§3⌛ 재사용 대기시간이: "+(20-Math.max(1,currentLevel)*5) +"초");
            }
            case 80 -> {
                url = "6b9a4dfcc7153c3176dc8cf38887d2d8385545fa4c5f8c2ff13f9d69e98e9";
                accessoryName = "금빛 타래";
                accessoryLore.add("§7");
                accessoryLore.add("§c⚡ 사용 효과:§f 운명의 끈");
                if (currentLevel <= 1) {
                    accessoryLore.add("§7최대 3번까지 튕기는 실을 일직선으로 발사합니다");
                    accessoryLore.add("§7적중 시 상대에게 2 고정 피해를 주고");
                    accessoryLore.add("§7자신은 2만큼 회복합니다");
                    accessoryLore.add("§3⌛ 재사용 대기시간: 7초");
                }
                else if (currentLevel == 2) {
                    accessoryLore.add("§7최대 5번까지 튕기는 실을 일직선으로 발사합니다");
                    accessoryLore.add("§7적중 시 상대에게 3 고정 피해를 주고");
                    accessoryLore.add("§7자신은 3만큼 회복합니다");
                    accessoryLore.add("§3⌛ 재사용 대기시간: 6초");
                }
                else if (currentLevel == 3) {
                    accessoryLore.add("§7최대 7번까지 튕기는 실을 일직선으로 발사합니다");
                    accessoryLore.add("§7적중 시 상대에게 4 고정 피해를 주고");
                    accessoryLore.add("§7자신은 4만큼 회복합니다");
                    accessoryLore.add("§3⌛ 재사용 대기시간: 5초");
                }
            }
            default -> {
                url = "2312e1b93e3544d0ed01e04716e5e2f3ea8ed799ae0253e4b1824de8b3002f64";
                accessoryName = "§4오류 나진 않겠지";
                accessoryLore.add("§4오류 코드: "+code);
            }
        }

        switch (mode) {
            case 1 -> {
                accessoryLore.add("");
                accessoryLore.add("§e클릭해서 변경하기");
            }
            case 2-> {
                accessoryLore.add("");
                if (code != 0){
                     if (PlayerDataStorage.accessory.get(player.getName()+code) == 0){
                         accessoryLore.add("§c보유하지 않음");
                         accessoryLore.add("§e우클릭해서 제작하기");
                     }
                     else{
                         accessoryLore.add("§e좌클릭해서 장착하기");
                         if (PlayerDataStorage.accessory.get(player.getName()+code) == 3) {
                             accessoryLore.add("§a최대 레벨!");
                         }
                         else{
                             accessoryLore.add("§e우클릭해서 강화하기");
                         }
                     }
                }
                else{
                    accessoryLore.add("§e좌클릭해서 장착하기");
                }
            }
        }
        String[] array = accessoryLore.toArray(new String[accessoryLore.size()]);
        return item.createItem(item.createSkull("https://textures.minecraft.net/texture/"+url),
                accLevel+accessoryName,
                array);
    }
}