package com.clase.monkeycraft.item;


import com.clase.monkeycraft.entity.ModEntities;
import com.clase.monkeycraft.entity.custom.MonkeyEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.item.component.Tool;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ScepterItem extends TieredItem {
    int selectedPosition = 0;
    String scepterId;
    List<String> summonedEntities;
    int DART_MONKEY_COST = 1;
    int MAGE_MONKEY_COST= 2;
    int TANK_MONKEY_COST = 3;
    int JESTER_MONKEY_COST = 4;

    public ScepterItem(Tier tier, Item.Properties properties) {
        super(tier, properties.component(DataComponents.TOOL, createToolProperties()));
        this.summonedEntities = new ArrayList<>();
    }

    private static Tool createToolProperties() {
        return new Tool(List.of(Tool.Rule.minesAndDrops(List.of(Blocks.COBWEB), 15.0F), Tool.Rule.overrideSpeed(BlockTags.SWORD_EFFICIENT, 1.5F)), 1.0F, 2);
    }

    public static ItemAttributeModifiers createAttributes(Tier tier, int attackDamage, float attackSpeed) {
        return ItemAttributeModifiers.builder()
                .add(
                        Attributes.ATTACK_DAMAGE,
                        new AttributeModifier(BASE_ATTACK_DAMAGE_ID, (double) ((float) attackDamage + tier.getAttackDamageBonus()),
                                AttributeModifier.Operation.ADD_VALUE),
                        EquipmentSlotGroup.MAINHAND
                )
                .add(
                        Attributes.ATTACK_SPEED, new AttributeModifier(BASE_ATTACK_SPEED_ID, (double) attackSpeed,
                                AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.MAINHAND
                )
                .build();
    }

    @Override
    public InteractionResult useOn(
            UseOnContext context
    ) {
        if (this.scepterId == null) {
            this.scepterId = UUID.randomUUID().toString();
        }
        if (!context.getLevel().isClientSide()) {
            Level dimension = context.getLevel();
            BlockPos pos = context.getClickedPos();
            Player player = context.getPlayer();

            //MonkeyDartEntity dartMonkey = ModEntities.MONKEY_DART.get().create(dimension);
            MonkeyEntity dartMonkey = ModEntities.MONKEY.get().create(dimension); // Replace with the upper line once the monkeys are added

            //MonkeyTankEntity tankMonkey = ModEntities.MONKEY_TANK.get().create(dimension);
            MonkeyEntity tankMonkey = ModEntities.MONKEY.get().create(dimension); // Replace with the upper line once the monkeys are added

            //WATCH OUT!! This one monkey when leaving the upper line as I used the word mage out of prediction for the future word, it may not be that one
            //MonkeyMageEntity mageMonkey = ModEntities.MONKEY_MAGE.get().create(dimension);
            MonkeyEntity mageMonkey = ModEntities.MONKEY.get().create(dimension); // Replace with the upper line once the monkeys are added

            //WATCH OUT!! This one monkey when leaving the upper line as I used the word jester out of prediction for the future word, it may not be that one
            //MonkeyMageEntity jesterMonkey = ModEntities.MONKEY_JESTER.get().create(dimension);
            MonkeyEntity jesterMonkey = ModEntities.MONKEY.get().create(dimension); // Replace with the upper line once the monkeys are added

            //TamableAnimal[] summons = {dartMonkey, mageMonkey, tankMonkey, jesterMonkey};
            MonkeyEntity[] summons = {dartMonkey, mageMonkey, tankMonkey, jesterMonkey}; //Replace with the upper line once the monkeys are added

            int currentCost = 0;
            switch (selectedPosition){
                case 0 -> currentCost = DART_MONKEY_COST;
                case 1 -> currentCost = MAGE_MONKEY_COST;
                case 2 -> currentCost = TANK_MONKEY_COST;
                case 3 -> currentCost = JESTER_MONKEY_COST;
            }
            if (summons[selectedPosition] != null &&
                    context.getItemInHand().getDamageValue() < context.getItemInHand().getMaxDamage() - currentCost) {

                summons[selectedPosition].moveTo(
                        pos.getX() + 0.5,
                        pos.getY() + 1,
                        pos.getZ() + 0.5
                );

                //summons[selectedPosition].tame(player); This line should tame the monkey when summoning
                dimension.addFreshEntity(summons[selectedPosition]);
                this.summonedEntities.add(summons[selectedPosition].getStringUUID());
                switch(selectedPosition){
                    case 0 -> context.getItemInHand().hurtAndBreak(DART_MONKEY_COST, player, LivingEntity.getSlotForHand(context.getHand()));
                    case 1 -> context.getItemInHand().hurtAndBreak(MAGE_MONKEY_COST, player, LivingEntity.getSlotForHand(context.getHand()));
                    case 2 -> context.getItemInHand().hurtAndBreak(TANK_MONKEY_COST, player, LivingEntity.getSlotForHand(context.getHand()));
                    case 3 -> context.getItemInHand().hurtAndBreak(JESTER_MONKEY_COST, player, LivingEntity.getSlotForHand(context.getHand()));
                }

            } else {
                Minecraft.getInstance().player.sendSystemMessage(Component.translatable("message.monkeycraft.low_energy"));
            }
        }
        return InteractionResult.SUCCESS;
    }

    @Override
    public InteractionResult interactLivingEntity(ItemStack stack, Player player,
                                                  LivingEntity entity, InteractionHand interaction) {
        if (this.scepterId == null) {
            this.scepterId = UUID.randomUUID().toString();
        }
        boolean thisScepter = false;

        if(stack.getDamageValue()==0){
            return InteractionResult.SUCCESS;
        }
        String removeEntity = null;
        for (String summonEntity : summonedEntities) {
            if (summonEntity.equals(entity.getStringUUID())) {
                thisScepter = true;
                removeEntity = entity.getStringUUID();
                break;
            }
        }
        if (thisScepter) {
            entity.remove(Entity.RemovalReason.DISCARDED);
            int recoverEnergy = 0;
            switch(entity.getType().toString()){
                case "entity.monkeycraft.monkey_dart" -> recoverEnergy = -DART_MONKEY_COST;
                case "entity.monkeycraft.monkey_tank" -> recoverEnergy = -TANK_MONKEY_COST;
                case "entity.monkeycraft.monkey_mage" -> recoverEnergy = -MAGE_MONKEY_COST;
                case "entity.monkeycraft.monkey_jester" -> recoverEnergy = -JESTER_MONKEY_COST;
            }
            stack.hurtAndBreak(recoverEnergy, player, Player.getSlotForHand(interaction));
            this.summonedEntities.remove(removeEntity);
        }
        return InteractionResult.SUCCESS;
    }

    @Override
    public boolean mineBlock(ItemStack itemStack, Level p_41417_, BlockState p_41418_, BlockPos p_41419_, LivingEntity p_41420_) {
        if (this.scepterId == null) {
            this.scepterId = UUID.randomUUID().toString();
        }
        Tool tool = itemStack.get(DataComponents.TOOL);
        if (tool == null) {
            return false;
        } else {
            return true;
        }
    }

    public void cycleSelectedPosition(){
        this.selectedPosition += 1;
        if (selectedPosition > 3) {
            this.selectedPosition = 0;
        }
        switch (this.selectedPosition){
            case 0 -> Minecraft.getInstance().player.sendSystemMessage(
                    Component.translatable("message.monkeycraft.selected",
                            Component.translatable("entity.monkeycraft.monkey_dart")));
            case 1 -> Minecraft.getInstance().player.sendSystemMessage(
                    Component.translatable("message.monkeycraft.selected",
                            Component.translatable("entity.monkeycraft.monkey_mage"))); //Might not tnd up as "mage", just predicting that word
            case 2 -> Minecraft.getInstance().player.sendSystemMessage(
                    Component.translatable("message.monkeycraft.selected",
                            Component.translatable("entity.monkeycraft.monkey_tank")));
            case 3 -> Minecraft.getInstance().player.sendSystemMessage(
                    Component.translatable("message.monkeycraft.selected",
                            Component.translatable("entity.monkeycraft.monkey_jester"))); //Might not end up as "jester", just predicting that word
        }
    }
    @Override
    public boolean isValidRepairItem(ItemStack toRepair, ItemStack repair) {
        if (repair.is(com.clase.monkeycraft.item.ModItems.BANANA.get())) {
            return true;
        }
        return super.isValidRepairItem(toRepair, repair);
    }
}
