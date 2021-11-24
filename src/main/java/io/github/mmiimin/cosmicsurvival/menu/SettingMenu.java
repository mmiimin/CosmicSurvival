package io.github.mmiimin.cosmicsurvival.menu;

import io.github.mmiimin.cosmicsurvival.util.ItemManager;
import io.github.mmiimin.cosmicsurvival.util.PlayerDataStorage;
import io.github.mmiimin.cosmicsurvival.LevelStyleManager;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class SettingMenu {

    ItemManager item = new ItemManager();
    LevelStyleManager lsm = new LevelStyleManager();


    public void openSettingMenu(Player player) {
        Inventory inv = Bukkit.createInventory(null, 45, "§0설정");
        for (int i = 0; i < 45; i++) {
            inv.setItem(i, item.createItem(Material.BLACK_STAINED_GLASS_PANE, " "));
        }
        inv.setItem(10,item.createItem(Material.GOLDEN_SWORD,"§a피해량 표시",
                "§7피해를 주거나 받을 시",
                        "§7채팅창에 피해에 대한 정보를 전송합니다"));
        if (PlayerDataStorage.map.get(player.getName()+"settingDI") == 0) {
            inv.setItem(19, item.createItem(Material.GRAY_DYE, "§f현재 옵션: §7꺼짐",
                    "§7피해에 대한 정보를 제공하지 않습니다.",
                    "",
                    "§e클릭해서 변경하기"));
        }
        else if (PlayerDataStorage.map.get(player.getName()+"settingDI") == 1) {
            inv.setItem(19, item.createItem(Material.LIME_DYE, "§f현재 옵션: §a켜짐",
                    "§7피해에 대한 정보를 전부 제공합니다.",
                    "",
                    "§e클릭해서 변경하기"));
        }

        inv.setItem(12,item.createItem(Material.POPPY,"§a레벨 스타일",
                "§7닉네임 옆에 표시되는",
                "§7레벨 칭호의 스타일을 설정합니다"));
        inv.setItem(21, item.createItem(Material.PINK_DYE, "§f스타일 변경",
                "",
                "§e클릭해서 변경하기"));




        inv.setItem(40,item.createItem(Material.ARROW,"§a뒤로 가기"));
        player.openInventory(inv);
    }

    public void levelStyle(Player player, int page) {
        Inventory inv = Bukkit.createInventory(null, 54, "§0레벨 스타일 - "+page);
        int level = PlayerDataStorage.map.get(player.getName()+"miningLevel")+PlayerDataStorage.map.get(player.getName()+"foragingLevel")+PlayerDataStorage.map.get(player.getName()+"farmingLevel")+PlayerDataStorage.map.get(player.getName()+"combatLevel")+
                PlayerDataStorage.map.get(player.getName()+"fishingLevel");
        for (int i = 0; i < 9; i++) {
            inv.setItem(i, item.createItem(Material.BLACK_STAINED_GLASS_PANE, " "));
            inv.setItem(i + 45, item.createItem(Material.BLACK_STAINED_GLASS_PANE, " "));
        }
        for (int i = 0; i < 6; i++) {
            inv.setItem(i * 9, item.createItem(Material.BLACK_STAINED_GLASS_PANE, " "));
            inv.setItem(i * 9 + 8, item.createItem(Material.BLACK_STAINED_GLASS_PANE, " "));
        }

        inv.setItem(49,item.createItem(Material.ARROW,"§a뒤로 가기"));

        if (page == 1) {

            inv.setItem(10, item.createItem(item.createSkull("http://textures.minecraft.net/texture/154818233c811873e85f5a4ea4429b75f23b6ae0ea6f5fc0f7bb420d7c471"), ChatColor.of("#7F7F7F")+"스톤",
                    "§7적용 예시: " + lsm.getLevelStyle(0,player),
                    "",
                    checkLevel(0,player)));
            inv.setItem(11, item.createItem(item.createSkull("http://textures.minecraft.net/texture/6dbc8d0e4912b8f7190b35a24b366b9b1f2772b5198eb6790f08ff7677dde51d"), ChatColor.of("#E77B57")+"브론즈",
                    "§7적용 예시: " + lsm.getLevelStyle(1,player),
                    "",
                    checkLevel(10,player)));
            inv.setItem(12, item.createItem(item.createSkull("http://textures.minecraft.net/texture/126b772329cf32f8643c4928626b6a325233ff61aa9c7725873a4bd66db3d692"), ChatColor.of("#D1D1D1")+"실버",
                    "§7적용 예시: " + lsm.getLevelStyle(2,player),
                    "",
                    checkLevel(20,player)));
            inv.setItem(13, item.createItem(item.createSkull("http://textures.minecraft.net/texture/351137e11443a8fbb05fcd3ccc1af9bd2303918f35448185e3ed96ef184da"), ChatColor.of("#F8D547")+"골드",
                    "§7적용 예시: " + lsm.getLevelStyle(3,player),
                    "",
                    checkLevel(30,player)));
            inv.setItem(14, item.createItem(item.createSkull("http://textures.minecraft.net/texture/ac906d688e65802569d9705b579bce56edc86ea5c36bdd6d6fc35516a77d4"), ChatColor.of("#63E989")+"에메랄드",
                    "§7적용 예시: " + lsm.getLevelStyle(4,player),
                    "",
                    checkLevel(40,player)));
            inv.setItem(15, item.createItem(item.createSkull("http://textures.minecraft.net/texture/9631597dce4e4051e8d5a543641966ab54fbf25a0ed6047f11e6140d88bf48f"), ChatColor.of("#71E0DA")+"다이아몬드",
                    "§7적용 예시: " + lsm.getLevelStyle(5,player),
                    "",
                    checkLevel(50,player)));
            inv.setItem(16, item.createItem(item.createSkull("http://textures.minecraft.net/texture/139d12b43a2c9ffd7df89eb6f9a104c60bb4743f58b1d72c891980c090484f26"), ChatColor.of("#F24241")+"루비",
                    "§7적용 예시: " + lsm.getLevelStyle(6,player),
                    "",
                    checkLevel(60,player)));
            inv.setItem(19, item.createItem(item.createSkull("http://textures.minecraft.net/texture/538071721cc5b4cd406ce431a13f86083a8973e1064d2f8897869930ee6e5237"), ChatColor.of("#E2E2E2")+"화이트"+ChatColor.of("#CFB76E")+"골드",
                    "§7적용 예시: " + lsm.getLevelStyle(7,player),
                    "",
                    checkLevel(70,player)));
            inv.setItem(20, item.createItem(item.createSkull("http://textures.minecraft.net/texture/98e5f77eec506b80a0da1d1e3648aa4d69dd396b35f32974cb4e7cf65b04464a"), ChatColor.of("#AE8A66")+"카페라떼",
                    "§7적용 예시: " + lsm.getLevelStyle(8,player),
                    "",
                    checkLevel(75,player)));
            inv.setItem(21, item.createItem(item.createSkull("http://textures.minecraft.net/texture/27957f895d7bc53423a35aac59d584b41cc30e040269c955e451fe680a1cc049"), ChatColor.of("#4D484D")+"네더라이트",
                    "§7적용 예시: " + lsm.getLevelStyle(9,player),
                    "",
                    checkLevel(75,player)));

            inv.setItem(22, item.createItem(item.createSkull("http://textures.minecraft.net/texture/5cb7c21cc43dc17678ee6f16591ffaab1f637c37f4f6bbd8cea497451d76db6d"), ChatColor.of("#1C7E6D")+"엔더 펄",
                    "§7적용 예시: " + lsm.getLevelStyle(10,player),
                    "",
                    checkLevel(85,player)));
            inv.setItem(23, item.createItem(item.createSkull("http://textures.minecraft.net/texture/4ad684f6751080d44a6999a8d3394c0b1abf7d3e5391d21aa62d481a5b8ce873"), ChatColor.of("#EB4540")+"딸기"+ChatColor.of("#FEF7E1")+"케이크",
                    "§7적용 예시: " + lsm.getLevelStyle(11,player),
                    "",
                    checkLevel(90,player)));
            inv.setItem(24, item.createItem(item.createSkull("http://textures.minecraft.net/texture/44b03794b9b3e3b5d07e3be68b96af87df215c3752e54736c80f7d50bd3437a4"), "§c레§6인§e보§a우",
                    "§7적용 예시: " + lsm.getLevelStyle(12,player),
                    "",
                    checkLevel(100,player)));
            inv.setItem(25, item.createItem(item.createSkull("http://textures.minecraft.net/texture/9e52f7960ff3cec2f519a6353648c6e33bc51e131cc80917cf13081decbff24d"), ChatColor.of("#4F78FE")+"제니스",
                    "§7적용 예시: " + lsm.getLevelStyle(13,player),
                    "",
                    checkLevel(100,player)));
            inv.setItem(28, item.createItem(item.createSkull("http://textures.minecraft.net/texture/5ea5e8041345ba24bfc89546f4c82be8cf9f9ba5a8e5536545343ae59b937e92"), ChatColor.of("#E8C00C")+"럭셔리",
                    "§7적용 예시: " + lsm.getLevelStyle(14,player),
                    "",
                    checkLevel(110,player)));
            inv.setItem(29, item.createItem(item.createSkull("http://textures.minecraft.net/texture/651e02da793653a25a8697b2fc0edc7a26a780c8b317b82616e2c34d94bebad4"), ChatColor.of("#F1EFD6")+"모던",
                    "§7적용 예시: " + lsm.getLevelStyle(15,player),
                    "",
                    checkLevel(120,player)));

        }
        else if (page == 2) {

        }
        player.openInventory(inv);
    }

    private String checkLevel(int target,Player player) {
        int level = PlayerDataStorage.map.get(player.getName()+"miningLevel")+PlayerDataStorage.map.get(player.getName()+"foragingLevel")+PlayerDataStorage.map.get(player.getName()+"farmingLevel")+PlayerDataStorage.map.get(player.getName()+"combatLevel")+
                PlayerDataStorage.map.get(player.getName()+"fishingLevel");
        if (level >= target){
            return "§e클릭해서 변경하기";
        }
        else {
            return "§c요구 레벨: " + target;
        }
    }
}
