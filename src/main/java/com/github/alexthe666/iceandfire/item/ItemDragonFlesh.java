package com.github.alexthe666.iceandfire.item;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class ItemDragonFlesh extends ItemGenericFood {

    int dragonType;

    public ItemDragonFlesh(int dragonType) {
        super(8, 0.8F, true, false, false, getNameForType(dragonType));
        this.dragonType = dragonType;
    }

    private static String getNameForType(int dragonType) {
        switch (dragonType){
            case 0:
                return "fire_dragon_flesh";
            case 1:
                return "ice_dragon_flesh";
            case 2:
                return "lightning_dragon_flesh";
        }
        return "fire_dragon_flesh";
    }

    public void onFoodEaten(ItemStack stack, Level worldIn, LivingEntity livingEntity) {
        if (!worldIn.isClientSide) {
            if (dragonType == 0) {
                livingEntity.setSecondsOnFire(5);
            } else if (dragonType == 1) {
                livingEntity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 100, 2));
            } else {
                if (!livingEntity.level.isClientSide) {
                    LightningBolt lightningboltentity = EntityType.LIGHTNING_BOLT.create(livingEntity.level);
                    lightningboltentity.moveTo(livingEntity.position());
                    if (!livingEntity.level.isClientSide) {
                        livingEntity.level.addFreshEntity(lightningboltentity);
                    }
                }
            }
        }
    }
}
