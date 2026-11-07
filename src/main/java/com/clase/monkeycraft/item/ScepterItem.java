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
    int DART_MONKEY_COST = 1; //Requires balance changes
    int TANK_MONKEY_COST = 2; //Requires balance changes
    int MAGE_MONKEY_COST= 3; //Requires balance changes
    int JESTER_MONKEY_COST = 4; //Requires balance changes

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
            dartMonkey.setScepterCost(DART_MONKEY_COST); //This will explode if  MonkeyDartEntity does not have a private int named ScepterCost with both getter and setters

            //MonkeyTankEntity dartMonkey = ModEntities.MONKEY_TANK.get().create(dimension);
            MonkeyEntity tankMonkey = ModEntities.MONKEY.get().create(dimension); // Replace with the upper line once the monkeys are added
            tankMonkey.setScepterCost(TANK_MONKEY_COST); //This will explode if  MonkeyTankEntity does not have a private int named ScepterCost with both getter and setters

            //Watch out for this one monkey when leaving the upper line as I used the word mage out of prediction for the future word, it may not be that one
            //MonkeyMageEntity dartMonkey = ModEntities.MONKEY_MAGE.get().create(dimension);
            MonkeyEntity mageMonkey = ModEntities.MONKEY.get().create(dimension); // Replace with the upper line once the monkeys are added
            mageMonkey.setScepterCost(MAGE_MONKEY_COST); //This will explode if  MonkeyTankEntity does not have a private int named ScepterCost with both getter and setters

            //Watch out for this one monkey when leaving the upper line as I used the word jester out of prediction for the future word, it may not be that one
            //MonkeyMageEntity jesterMonkey = ModEntities.MONKEY_JESTER.get().create(dimension);
            MonkeyEntity jesterMonkey = ModEntities.MONKEY.get().create(dimension); // Replace with the upper line once the monkeys are added
            jesterMonkey.setScepterCost(JESTER_MONKEY_COST); //This will explode if  MonkeyTankEntity does not have a private int named ScepterCost with both getter and setters

            //TamableAnimal[] summons = {dartMonkey, tankMonkey, mageMonkey};
            MonkeyEntity[] summons = {dartMonkey, tankMonkey, mageMonkey}; //Replace with the upper line once the monkeys are added
            if (summons[selectedPosition] != null &&
                    context.getItemInHand().getDamageValue() < context.getItemInHand().getMaxDamage() - summons[selectedPosition].getScepterCost()) {

                summons[selectedPosition].moveTo(
                        pos.getX() + 0.5,
                        pos.getY() + 1,
                        pos.getZ() + 0.5
                );

                //summons[selectedPosition].tame(player); This line should tame the monkey when summoning
                dimension.addFreshEntity(summons[selectedPosition]);
                this.summonedEntities.add(summons[selectedPosition].getStringUUID());
                context.getItemInHand().hurtAndBreak(summons[selectedPosition].getScepterCost(), player, LivingEntity.getSlotForHand(context.getHand()));
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

            }
        }
        if (thisScepter) {
            entity.remove(Entity.RemovalReason.DISCARDED);
            int recoverEnergy = 0;
            switch(entity.getType().toString()){
                case "entity.monkeycraft.monkey_dart" -> recoverEnergy = DART_MONKEY_COST;
                case "entity.monkeycraft.monkey_tank" -> recoverEnergy = TANK_MONKEY_COST;
                case "entity.monkeycraft.monkey_mage" -> recoverEnergy = MAGE_MONKEY_COST;
                case "entity.monkeycraft.monkey_jester" -> recoverEnergy = JESTER_MONKEY_COST;
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
                            Component.translatable("entity.monkeycraft.monkey_tank")));
            case 2 -> Minecraft.getInstance().player.sendSystemMessage(
                    Component.translatable("message.monkeycraft.selected",
                            Component.translatable("entity.monkeycraft.monkey_mage"))); //Might not tnd up as "mage", just predicting that word
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
