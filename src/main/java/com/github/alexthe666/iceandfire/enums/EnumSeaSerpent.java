package com.github.alexthe666.iceandfire.enums;

import com.github.alexthe666.citadel.server.item.CustomArmorMaterial;
import com.github.alexthe666.iceandfire.IceAndFire;
import com.github.alexthe666.iceandfire.block.BlockSeaSerpentScales;
import com.github.alexthe666.iceandfire.item.IafArmorMaterial;
import com.github.alexthe666.iceandfire.item.ItemSeaSerpentArmor;
import com.github.alexthe666.iceandfire.item.ItemSeaSerpentScales;
import net.minecraft.ChatFormatting;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

import java.util.Locale;

public enum EnumSeaSerpent {
    BLUE(ChatFormatting.BLUE),
    BRONZE(ChatFormatting.GOLD),
    DEEPBLUE(ChatFormatting.DARK_BLUE),
    GREEN(ChatFormatting.DARK_GREEN),
    PURPLE(ChatFormatting.DARK_PURPLE),
    RED(ChatFormatting.DARK_RED),
    TEAL(ChatFormatting.AQUA);

    public String resourceName;
    public ChatFormatting color;
    public CustomArmorMaterial armorMaterial;
    public Item scale;
    public Item helmet;
    public Item chestplate;
    public Item leggings;
    public Item boots;
    public Block scaleBlock;

    EnumSeaSerpent(ChatFormatting color) {
        this.resourceName = this.name().toLowerCase(Locale.ROOT);
        this.color = color;
        this.scaleBlock = new BlockSeaSerpentScales(this.resourceName, this.color);
    }


    public static void initArmors() {
        for (EnumSeaSerpent color : EnumSeaSerpent.values()) {
            color.armorMaterial = new IafArmorMaterial("iceandfire:sea_serpent_scales_" + color.resourceName, 30, new int[]{4, 8, 7, 4}, 25, SoundEvents.ARMOR_EQUIP_GOLD, 2.5F);
            color.scale = new ItemSeaSerpentScales(color.resourceName, color.color);
            color.helmet = new ItemSeaSerpentArmor(color, color.armorMaterial, EquipmentSlot.HEAD);
            color.chestplate = new ItemSeaSerpentArmor(color, color.armorMaterial, EquipmentSlot.CHEST);
            color.leggings = new ItemSeaSerpentArmor(color, color.armorMaterial, EquipmentSlot.LEGS);
            color.boots = new ItemSeaSerpentArmor(color, color.armorMaterial, EquipmentSlot.FEET);
            color.helmet.setRegistryName(IceAndFire.MODID, "tide_" + color.resourceName + "_helmet");
            color.chestplate.setRegistryName(IceAndFire.MODID, "tide_" + color.resourceName + "_chestplate");
            color.leggings.setRegistryName(IceAndFire.MODID, "tide_" + color.resourceName + "_leggings");
            color.boots.setRegistryName(IceAndFire.MODID, "tide_" + color.resourceName + "_boots");
        }
    }
}
