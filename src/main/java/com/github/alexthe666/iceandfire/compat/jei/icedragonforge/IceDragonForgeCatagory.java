package com.github.alexthe666.iceandfire.compat.jei.icedragonforge;

import com.github.alexthe666.iceandfire.compat.jei.IceAndFireJEIPlugin;
import com.github.alexthe666.iceandfire.recipe.DragonForgeRecipe;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.ingredient.IGuiItemStackGroup;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.ArrayList;
import java.util.List;

public class IceDragonForgeCatagory implements IRecipeCategory<DragonForgeRecipe> {

    public IceDragonForgeDrawable drawable;

    public IceDragonForgeCatagory() {
        drawable = new IceDragonForgeDrawable();
    }

    @Override
    public ResourceLocation getUid() {
        return IceAndFireJEIPlugin.ICE_DRAGON_FORGE_ID;
    }

    @Override
    public Class<? extends DragonForgeRecipe> getRecipeClass() {
        return DragonForgeRecipe.class;
    }

    @SuppressWarnings("deprecation")
    @Override
    public Component getTitle() {
        return new TranslatableComponent("iceandfire.ice_dragon_forge");
    }

    @Override
    public IDrawable getBackground() {
        return drawable;
    }

    @Override
    public IDrawable getIcon() {
        return null;
    }

    @Override
    public void setIngredients(DragonForgeRecipe dragonForgeRecipe, IIngredients iIngredients) {
        List<Ingredient> ingredientsList = new ArrayList<>();
        ingredientsList.add(dragonForgeRecipe.getInput());
        ingredientsList.add(dragonForgeRecipe.getBlood());
        iIngredients.setInputIngredients(ingredientsList);
        iIngredients.setOutput(VanillaTypes.ITEM, dragonForgeRecipe.getOutput());
    }

    @Override
    public void setRecipe(IRecipeLayout recipeLayout, DragonForgeRecipe dragonForgeRecipe, IIngredients iIngredients) {
        IGuiItemStackGroup guiItemStacks = recipeLayout.getItemStacks();
        guiItemStacks.init(0, true, 64, 29);
        guiItemStacks.init(1, true, 82, 29);
        guiItemStacks.init(2, false, 144, 30);
        guiItemStacks.set(iIngredients);
    }
}
