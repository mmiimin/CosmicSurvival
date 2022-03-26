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
                    accessoryLore.add("§7성급함 VI를 10초간 받습니다");
                }
                else if (currentLevel == 2) {
                    accessoryLore.add("§7성급함 VIII를 12.5초간 받습니다");
                }
                else if (currentLevel == 3) {
                    accessoryLore.add("§7성급함 X를 15초간 받습니다");
                }
                accessoryLore.add("§7");
                accessoryLore.add("§3⌛ 재사용 대기시간: "+ (70-Math.max(1,currentLevel)*10) +"초");
            }
            case 2 -> {
                url = "56311387e750bf67b5c8720df8327d20ad7854412b563bf1ed8a49c9cc2024";
                accessoryName = "황금 반지";
                accessoryLore.add("§7● 최대 체력 +"+ (Math.max(1,currentLevel)+1));
                accessoryLore.add("§7");
                accessoryLore.add("§c⚡ 사용 효과: §f로얄 가드");
                accessoryLore.add("§7저항 IV을 0.5초간 받습니다");
                accessoryLore.add("§7");
                accessoryLore.add("§3⌛ 재사용 대기시간: "+ (12-Math.max(1,currentLevel)*3) +"초");
            }
            case 3 -> {
                url = "da8524274a65d8a80a0f44e103e2021e27c8916e965aa11f665ac0c49be40ec6";
                accessoryName = "네더라이트 반지";
                accessoryLore.add("§7● 최대 체력 +"+ Math.max(2,currentLevel+1));
                accessoryLore.add("§7● 체력이 8 이하일 때 받는 피해가 "+ (Math.max(1,currentLevel)*10+20) +"% 감소한다");
            }
            case 4 -> {
                url = "7d16ae951120394f368f2250b7c3ad3fb12cea55ec1b2db5a94d1fb7fd4b6fa";
                accessoryName = "새벽의 수정구";
                accessoryLore.add("§7● 최대 체력인 엔티티에게 직접 타격 시 "+ (Math.max(1,currentLevel)*200-150) +"% 추가 피해");
            }
            case 5 -> {
                url = "d1df891f84d3cc735d3ec6f7244583377cd4d908eb6ff05c45339807180820d";
                accessoryName = "옵시디언 펜던트";
                accessoryLore.add("§7● 웅크리고 있을 때 받는 피해가 "+ (Math.max(1,currentLevel)*8+8) +"% 감소한다");
            }
            case 6 -> {
                url = "97c4036f80bf3609180c7c4a9568853fd815f154ad8438b92f3851019835b070";
                accessoryName = "정오의 수정구";
                accessoryLore.add("§7● 현재 체력이 "+ (Math.max(1,currentLevel)*2+1)+" 이하인 엔티티를 직접 타격 시 즉사시킨다");
            }
            case 7 -> {
                url = "cfbb95502e0fd0dba60bbe5818e2fd5278eda2999cc2d78109c2ca577557a0a6";
                accessoryName = "서리바람";
                accessoryLore.add("§7● 이동 속도 +"+Math.max(1,currentLevel)*2+"%p");
                accessoryLore.add("§7");
                accessoryLore.add("§c⚡ 사용 효과: §f돌풍");
                accessoryLore.add("§7보고 있는 방향으로 도약합니다");
                accessoryLore.add("§7");
                accessoryLore.add("§3⌛ 재사용 대기시간: "+(20-Math.max(1,currentLevel)*5) +"초");
            }
            case 8 -> {
                url = "151599f66e83da555cf9b8b7e5a379d0deab21c2eee909d1837323db0893bf38";
                accessoryName = "금빛 탄환";
                accessoryLore.add("§7● 화살로 주는 피해량이 "+(Math.max(1,currentLevel)*15+5)+"% 증가한다");
                accessoryLore.add("§7● 화살로 35블록 이상의 거리에서 피해를 줄 시");
                accessoryLore.add("§7피해량이 추가로 "+(Math.max(1,currentLevel)*30+10)+"% 증가한다");
            }
            case 9 -> {
                url = "7717933c40fbf936aa9288513efe19bda4601efc0e4ecad2e023b0c1d28444b";
                accessoryName = "플레임 펜던트";
                accessoryLore.add("§7● 불타는 엔티티에게 직접 타격 시 피해량 "+(Math.max(1,currentLevel)*5+5)+"% 증가");
                accessoryLore.add("§7");
                accessoryLore.add("§c⚡ 사용 효과: §f플레임 볼텍스");
                accessoryLore.add("§71초간 정신을 집중한 뒤 주변 "+(Math.max(1,currentLevel)+3)+"블록 이내의 엔티티를");
                accessoryLore.add("§7불태우고 공중에 띄운 뒤 낙하시킵니다");
                accessoryLore.add("§7자신은 신속 I을 "+(Math.max(1,currentLevel)+5)+"초간 받습니다");
                accessoryLore.add("§7");
                accessoryLore.add("§3⌛ 재사용 대기시간: "+(34-Math.max(1,currentLevel)*4) +"초");
            }
            case 10 -> {
                url = "59d9b327ff82f6e109b8746c48bfb7f641e1552855493dd4118be24d5b02bc0a";
                accessoryName = "루비 반지";
                accessoryLore.add("§7● 최대 체력 +"+ (Math.max(1,currentLevel)-1));
                accessoryLore.add("§7");
                accessoryLore.add("§c⚡ 사용 효과: §f힐링 레인");
                accessoryLore.add("§7잔류형 즉시 회복 I 포션을 투척합니다");
                accessoryLore.add("§7");
                accessoryLore.add("§3⌛ 재사용 대기시간: "+(31-Math.max(1,currentLevel)*5) +"초");
            }
            case 11 -> {
                url = "b879436385bdb0efd13b1d2c094a311ea81e3b83520f9192d89e6442b6fdf2";
                accessoryName = "루나 피스톨";
                accessoryLore.add("§c⚡ 사용 효과: §f집광");
                accessoryLore.add("§7바라보는 방향으로 빛을 쏘아 적중한 엔티티에게");
                accessoryLore.add("§7"+(Math.max(1,currentLevel)*4+4) +"의 피해를 주고");
                accessoryLore.add("§72초간 위치를 드러냅니다");
                accessoryLore.add("§7");
                accessoryLore.add("§c➡ 최대 사거리: 50칸");
                accessoryLore.add("§3⌛ 재사용 대기시간: 1.2초");
            }
            case 12 -> {
                url = "bb2abd66939f4cb7257a88cf52fbc6fdceec1433ec2a6ef16d62e34f6238781";
                accessoryName = "전기충격기";
                accessoryLore.add("§c⚡ 사용 효과: §f감전");
                accessoryLore.add("§7적중한 엔티티에게 2 피해를 주고");
                accessoryLore.add("§7기절시키며 이동속도를 느리게 만듭니다");
                accessoryLore.add("§7");
                accessoryLore.add("§c➡ 최대 사거리: 30칸");
                accessoryLore.add("§3⌛ 재사용 대기시간: "+String.format("%.2f",(0.2-Math.max(1,currentLevel)*0.05)) +"초");
            }
            case 13 -> {
                url = "984a68fd7b628d309667db7a55855b54abc23f3595bbe43216211be5fe57014";
                accessoryName = "독구름 씨앗";
                accessoryLore.add("§c⚡ 사용 효과: §f독성 연막");
                accessoryLore.add("§7바라보는 방향에 씨를 심어");
                accessoryLore.add("§73초 후 주변 3블록의 시야를 11초간 차단하고");
                accessoryLore.add("§7범위 안에 있는 엔티티에게 초당 "+(2+Math.max(1,currentLevel)*8) +" 피해를 준다");
                accessoryLore.add("§7");
                accessoryLore.add("§c➡ 최대 투척 사거리: 15칸");
                accessoryLore.add("§3⌛ 재사용 대기시간: "+(50-Math.max(1,currentLevel)*8) +"초");
            }
            case 14 -> {
                url = "984a68fd7b628d309667db7a55855b54abc23f3595bbe43216211be5fe57014";
                accessoryName = "독구름 씨앗";
                accessoryLore.add("§c⚡ 사용 효과: §f독성 연막");
                accessoryLore.add("§7바라보는 방향에 씨를 심어");
                accessoryLore.add("§73초 후 주변 3블록의 시야를 11초간 차단하고");
                accessoryLore.add("§7범위 안에 있는 엔티티1에게 초당 "+(2+Math.max(1,currentLevel)*8) +" 피해를 준다");
                accessoryLore.add("§7");
                accessoryLore.add("§c➡ 최대 투척 사거리: 15칸");
                accessoryLore.add("§3⌛ 재사용 대기시간: "+(28-Math.max(1,currentLevel)*3) +"초");
            }



            default -> {
                url = "2312e1b93e3544d0ed01e04716e5e2f3ea8ed799ae0253e4b1824de8b3002f64";
                accessoryName = "§4인덱스 초과";
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