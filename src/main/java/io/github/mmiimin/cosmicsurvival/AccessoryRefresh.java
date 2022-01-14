package io.github.mmiimin.cosmicsurvival;

import io.github.mmiimin.cosmicsurvival.util.PlayerDataStorage;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;

import java.util.Objects;

public class AccessoryRefresh {

    public void aRefresh(Player player){
        int health=0;
        int speed=0; //0.1%
        for (int i=1;i<4;i++) {
            int accLv=PlayerDataStorage.accessory.get(player.getName()+PlayerDataStorage.map.get(player.getName() + "accessory" + i));
            switch (PlayerDataStorage.map.get(player.getName() + "accessory" + i)){
                case 2, 10 ->{
                    if (accLv>=1) {
                        health += accLv;
                    }
                }
                case 3->{
                    if (accLv>=1) {
                        health += accLv+1;
                    }
                }
                case 7->{
                    if (accLv>=1) {
                        speed += accLv*20;
                    }
                }
            }
        }
        Objects.requireNonNull(player.getAttribute(Attribute.GENERIC_MAX_HEALTH)).setBaseValue(20+health);
        Objects.requireNonNull(player.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED)).setBaseValue(0.1 + PlayerDataStorage.map.get(player.getName()+"statsDEX")*0.0001 + speed*0.0001);
    }
}
