package com.github.alexthe666.iceandfire.item;

import com.github.alexthe666.iceandfire.IafConfig;
import com.github.alexthe666.iceandfire.IceAndFire;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;
import java.util.List;

public class ItemModAxe extends AxeItem implements DragonSteelOverrides<ItemModAxe> {

    private final Tier tier;
    private Multimap<Attribute, AttributeModifier> dragonsteelModifiers;

    public ItemModAxe(Tier toolmaterial, String gameName) {
        super(toolmaterial, 5.0F, -3.0F, (new Item.Properties()).tab(IceAndFire.TAB_ITEMS));
        this.tier = toolmaterial;
        this.setRegistryName(IceAndFire.MODID, gameName);
    }

    @Override
    @Deprecated
    public Multimap<Attribute, AttributeModifier> getDefaultAttributeModifiers(EquipmentSlot equipmentSlot) {
        return equipmentSlot == EquipmentSlot.MAINHAND && isDragonsteel(getTier()) ? this.bakeDragonsteel() : super.getDefaultAttributeModifiers(equipmentSlot);
    }

    @Override
    @Deprecated
    public Multimap<Attribute, AttributeModifier> bakeDragonsteel() {
        if (tier.getAttackDamageBonus() != IafConfig.dragonsteelBaseDamage || dragonsteelModifiers == null) {
            ImmutableMultimap.Builder<Attribute, AttributeModifier> lvt_5_1_ = ImmutableMultimap.builder();
            lvt_5_1_.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(BASE_ATTACK_DAMAGE_UUID, "Weapon modifier", IafConfig.dragonsteelBaseDamage - 1F + 5F, AttributeModifier.Operation.ADDITION));
            lvt_5_1_.put(Attributes.ATTACK_SPEED, new AttributeModifier(BASE_ATTACK_SPEED_UUID, "Weapon modifier", -3.0F, AttributeModifier.Operation.ADDITION));
            this.dragonsteelModifiers = lvt_5_1_.build();
            return this.dragonsteelModifiers;
        } else {
            return dragonsteelModifiers;
        }
    }

    @Override
    public int getMaxDamage(ItemStack stack) {
        return IafConfig.dragonsteelBaseDurability;
    }

    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        hurtEnemy(this, stack, target, attacker);
        return super.hurtEnemy(stack, target, attacker);
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
        super.appendHoverText(stack, worldIn, tooltip, flagIn);
        appendHoverText(tier, stack, worldIn, tooltip, flagIn);
    }

    public boolean canDisableShield(ItemStack stack, ItemStack shield, LivingEntity entity, LivingEntity attacker) {
        return true;
    }
}
