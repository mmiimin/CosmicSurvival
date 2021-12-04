package io.github.mmiimin.cosmicsurvival.menu;

import io.github.mmiimin.cosmicsurvival.LevelStyleManager;
import io.github.mmiimin.cosmicsurvival.util.ItemManager;
import io.github.mmiimin.cosmicsurvival.util.PlayerDataStorage;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.advancement.Advancement;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class CosmeticMenu {

    ItemManager item = new ItemManager();
    LevelStyleManager lsm = new LevelStyleManager();

    public void openCosmeticMenu(Player player) {
        Inventory inv = Bukkit.createInventory(null, 36, "§0치장품");
        for (int i = 0; i < 36; i++) {
            inv.setItem(i, item.createItem(Material.BLACK_STAINED_GLASS_PANE, " "));
        }

        inv.setItem(10,item.createItem(Material.NETHER_STAR,"§a레벨 스타일",
                "§7닉네임 옆에 표시되는",
                "§7레벨 칭호의 스타일을 설정합니다",
                "",
                "§e클릭해서 변경하기"));

        inv.setItem(12,item.createItem(Material.GOLDEN_SWORD,"§a타격 파티클",
                "§7타격 시 나타낼",
                "§7파티클을 설정합니다",
                "",
                "§e클릭해서 변경하기"));


        inv.setItem(31,item.createItem(Material.ARROW,"§a뒤로 가기"));
        player.openInventory(inv);
    }

    public void levelStyle(Player player, int page) {
        Inventory inv = Bukkit.createInventory(null, 54, "§0레벨 스타일 - "+page);
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
            inv.setItem(30, item.createItem(item.createSkull("http://textures.minecraft.net/texture/7a408049ab19fa7e28f3466bf0b9a02766c319181a9ab7a4b6973816f318a6a"), ChatColor.of("#F6FFB0")+"네이쳐",
                    "§7적용 예시: " + lsm.getLevelStyle(16,player),
                    "",
                    checkLevel(125,player)));
            inv.setItem(31, item.createItem(item.createSkull("http://textures.minecraft.net/texture/4fea36a9e73ab3b08d6479cd1e0004495ef29212fa7cff56b556fd46b0dda71"), "§c크§a리§c스§a마§c스",
                    "§7적용 예시: " + lsm.getLevelStyle(17,player),
                    "",
                    checkLevel(125,player)));
            inv.setItem(32, item.createItem(item.createSkull("http://textures.minecraft.net/texture/34e311e0c2a4f894cd43b40a2abf3e0529b92567fd795336528770d766cbd3b6"), ChatColor.of("#FFFB00")+"문라이트",
                    "§7적용 예시: " + lsm.getLevelStyle(18,player),
                    "",
                    checkLevel(130,player)));
            inv.setItem(33, item.createItem(item.createSkull("http://textures.minecraft.net/texture/4ceeb77d4d25724a9caf2c7cdf2d88399b1417c6b9ff5213659b653be4376e3"), ChatColor.of("#725D4B")+"노트블록",
                    "§7적용 예시: " + lsm.getLevelStyle(19,player),
                    "",
                    checkLevel(135,player)));
        }
        else if (page == 2) {

        }
        player.openInventory(inv);
    }

    public void hitEffect(Player player, int page) {
        Inventory inv = Bukkit.createInventory(null, 54, "§0타격 파티클 - "+page);
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

            inv.setItem(10, item.createItem(item.createSkull("http://textures.minecraft.net/texture/1919d1594bf809db7b44b3782bf90a69f449a87ce5d18cb40eb653fdec2722"), ChatColor.of("#7F7F7F")+"없음",
                    "",
                    checkLevel(0,player)));
            inv.setItem(11, item.createItem(item.createSkull("http://textures.minecraft.net/texture/fdd4364fe2b19a6c119d17b56944eff566c14b4fea45e9b4bc32928d597f468"), "§7잔상",
                    "",
                    checkLevel(15,player)));
            inv.setItem(12, item.createItem(item.createSkull("http://textures.minecraft.net/texture/a79f8c92776d642d119f9e92360b1e5b971e66e61428a3e1b311d8b6185e6"), ChatColor.of("#A765CB")+"마법",
                    "",
                    checkLevel(25,player)));
            inv.setItem(13, item.createItem(item.createSkull("http://textures.minecraft.net/texture/76fdd4b13d54f6c91dd5fa765ec93dd9458b19f8aa34eeb5c80f455b119f278"), "§c하트",
                    "",
                    checkLevel(35,player)));
            inv.setItem(14, item.createItem(item.createSkull("http://textures.minecraft.net/texture/4080bbefca87dc0f36536b6508425cfc4b95ba6e8f5e6a46ff9e9cb488a9ed"), "§4화염",
                    "",
                    checkLevel(45,player)));
            inv.setItem(15, item.createItem(item.createSkull("http://textures.minecraft.net/texture/c3a8e402dad1b7dad9aae6f4015932183429ce87bbbeced3119026f8296336c2"), "§5포탈",
                    "",
                    checkLevel(55,player)));
            inv.setItem(16, item.createItem(item.createSkull("http://textures.minecraft.net/texture/8c5063f9576570a8939006207659989ff81b9c87a28ce3417caae82b6e7d62e"), "§f별",
                    "",
                    checkLevel(65,player)));
            inv.setItem(19, item.createItem(item.createSkull("http://textures.minecraft.net/texture/1dfd7724c69a024dcfc60b16e00334ab5738f4a92bafb8fbc76cf15322ea0293"), "§f눈꽃",
                    "",
                    checkLevel(70,player)));
            inv.setItem(20, item.createItem(item.createSkull("http://textures.minecraft.net/texture/345831eaef99e030c82b1e79ce63b2cb44df1361660daf4e42161dd9db42a754"), "§b영혼",
                    "",
                    checkLevel(80,player)));
            inv.setItem(21, item.createItem(item.createSkull("http://textures.minecraft.net/texture/2484aa5bee898a6e8960a3f9a99759b1f39f9dcb321050f714cd72b3d8a8041"), "§9물풍선",
                    "§8HitSound♪",
                    "",
                    checkLevel(90,player)));
            inv.setItem(22, item.createItem(item.createSkull("http://textures.minecraft.net/texture/f22e40b4bfbcc0433044d86d67685f0567025904271d0a74996afbe3f9be2c0f"), "§e음표",
                    "§8HitSound♪",
                    "",
                    checkLevel(95,player)));
            inv.setItem(23, item.createItem(item.createSkull("http://textures.minecraft.net/texture/b85bcf7f82d34db89a95addf8e53253e2d9554c6fd2f2e39e24362d243a0ccf7"), "§6번개",
                    "§8HitSound♪",
                    "",
                    checkLevel(100,player)));
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
