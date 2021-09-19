package io.github.mmiimin.cosmicsurvival

import org.bukkit.attribute.Attribute
import org.bukkit.attribute.AttributeModifier
import org.bukkit.inventory.EquipmentSlot
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.ItemMeta
import java.util.*

class CustomItem {
        fun setAttackDamager(itemStack: ItemStack, attackDamage: Double) {
                val uuid = UUID(894654, 2872)
                val itemMeta: ItemMeta? = itemStack.itemMeta
                itemMeta?.getAttributeModifiers(Attribute.GENERIC_ATTACK_DAMAGE)?.add(
                        AttributeModifier(
                                uuid,
                                "myAttackDamage",
                                attackDamage,
                                AttributeModifier.Operation.ADD_NUMBER,
                                EquipmentSlot.HAND
                        )
                )
                itemStack.itemMeta = itemMeta
        }
}
