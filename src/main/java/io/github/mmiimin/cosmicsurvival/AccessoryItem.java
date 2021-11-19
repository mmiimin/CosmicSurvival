package io.github.mmiimin.cosmicsurvival;

import io.github.mmiimin.cosmicsurvival.util.ItemManager;
import org.bukkit.inventory.ItemStack;

public class AccessoryItem {
    ItemManager item = new ItemManager();

    public ItemStack createAccessory(int code){

        String accessoryName = "";
        String accessoryEffect = "";
        ItemStack accessory = item.createItem(item.createSkull("3ed1aba73f639f4bc42bd48196c715197be2712c3b962c97ebf9e9ed8efa025"),
                "§4[X] Null",
                " ",
                "§7● 정상적인 방법으로 획득할 수 없는 장신구입니다",
                "§7● 획득 시 관리자에게 신고해주세요");

        switch (code){
            case 1:
                accessoryName = "";
                accessoryEffect = "";
                break;
            case 2:

                break;

        }



        return accessory;
    }

}
