package com.clase.monkeycraft.item;

import com.clase.monkeycraft.entity.ModEntities;
import com.clase.monkeycraft.entity.custom.MonkeyDartEntity;
import com.clase.monkeycraft.entity.custom.MonkeyJesterEntity;
import com.clase.monkeycraft.entity.custom.MonkeyTankEntity;
import com.clase.monkeycraft.entity.custom.MonkeyWizardEntity;
import com.clase.monkeycraft.util.ModScepterTiers;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
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
    int maxPositions = 0;
    String scepterId;
    List<String> summonedEntities;

    int DART_MONKEY_COST = 1;
    int MAGE_MONKEY_COST = 2;
    int TANK_MONKEY_COST = 3;
    int JESTER_MONKEY_COST = 4;

    public ScepterItem(Tier tier, Item.Properties properties) {
        super(tier, properties.component(DataComponents.TOOL, createToolProperties()));
        this.summonedEntities = new ArrayList<>();
        if (tier == ModScepterTiers.TIER_0) {
            this.maxPositions = 0;
        } else if (tier == ModScepterTiers.TIER_1) {
            this.maxPositions = 1;
        } else if (tier == ModScepterTiers.TIER_2) {
            this.maxPositions = 2;
        } else {
            this.maxPositions = 3;
        }
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
    public InteractionResult useOn(UseOnContext context) {
        if (this.scepterId == null) {
            this.scepterId = UUID.randomUUID().toString();
        }
        if (!context.getLevel().isClientSide()) {
            Level dimension = context.getLevel();
            BlockPos pos = context.getClickedPos();
            Player player = context.getPlayer();

            MonkeyDartEntity dartMonkey = ModEntities.MONKEY_DART.get().create(dimension);
            MonkeyWizardEntity wizardMonkey = ModEntities.MONKEY_WIZARD.get().create(dimension);
            MonkeyTankEntity tankMonkey = ModEntities.MONKEY_TANK.get().create(dimension);
            MonkeyJesterEntity jesterMonkey = ModEntities.MONKEY_JESTER.get().create(dimension);

            TamableAnimal[] summons = {dartMonkey, wizardMonkey, tankMonkey, jesterMonkey};

            int currentCost = 0;
            switch (selectedPosition){
                case 0 -> currentCost = DART_MONKEY_COST;
                case 1 -> currentCost = MAGE_MONKEY_COST;
                case 2 -> currentCost = TANK_MONKEY_COST;
                case 3 -> currentCost = JESTER_MONKEY_COST;
            }

            if (summons[selectedPosition] != null &&
                    context.getItemInHand().getDamageValue() < context.getItemInHand().getMaxDamage() - currentCost) {

                if (selectedPosition == 2 && dimension instanceof ServerLevel serverLevel) {
                    int tankCount = 0;
                    for (String uuidStr : this.summonedEntities) {
                        try {
                            Entity e = serverLevel.getEntity(UUID.fromString(uuidStr));
                            if (e instanceof MonkeyTankEntity) {
                                tankCount++;
                            }
                        } catch (Exception ignored) {}
                    }
                    if (tankCount >= 1) {
                        if (player != null) {
                            player.sendSystemMessage(Component.translatable("message.monkeycraft.max_tanks"));
                        }
                        return InteractionResult.FAIL;
                    }
                }

                summons[selectedPosition].moveTo(
                        pos.getX() + 0.5,
                        pos.getY() + 1,
                        pos.getZ() + 0.5
                );

                summons[selectedPosition].tame(player);
                dimension.addFreshEntity(summons[selectedPosition]);
                this.summonedEntities.add(summons[selectedPosition].getStringUUID());

                switch(selectedPosition){
                    case 0 -> context.getItemInHand().hurtAndBreak(DART_MONKEY_COST, player, LivingEntity.getSlotForHand(context.getHand()));
                    case 1 -> context.getItemInHand().hurtAndBreak(MAGE_MONKEY_COST, player, LivingEntity.getSlotForHand(context.getHand()));
                    case 2 -> context.getItemInHand().hurtAndBreak(TANK_MONKEY_COST, player, LivingEntity.getSlotForHand(context.getHand()));
                    case 3 -> context.getItemInHand().hurtAndBreak(JESTER_MONKEY_COST, player, LivingEntity.getSlotForHand(context.getHand()));
                }

            } else {
                if (player != null) {
                    player.sendSystemMessage(Component.translatable("message.monkeycraft.low_energy"));
                }
            }
        }
        return InteractionResult.SUCCESS;
    }

    @Override
    public InteractionResult interactLivingEntity(ItemStack stack, Player player, LivingEntity entity, InteractionHand interaction) {
        if (!player.level().isClientSide()) {
            boolean thisScepter = false;
            String removeEntity = null;
            for (String summonEntity : summonedEntities) {
                if (summonEntity.equals(entity.getStringUUID())) {
                    thisScepter = true;
                    removeEntity = entity.getStringUUID();
                    break;
                }
            }
            if (thisScepter) {
                int recoverEnergy = 0;

                EntityType<?> type = entity.getType();
                if (type == ModEntities.MONKEY_DART.get()) {
                    recoverEnergy = -DART_MONKEY_COST;
                } else if (type == ModEntities.MONKEY_WIZARD.get()) {
                    recoverEnergy = -MAGE_MONKEY_COST;
                } else if (type == ModEntities.MONKEY_TANK.get()) {
                    recoverEnergy = -TANK_MONKEY_COST;
                } else if (type == ModEntities.MONKEY_JESTER.get()) {
                    recoverEnergy = -JESTER_MONKEY_COST;
                }

                if (stack.getDamageValue() < -recoverEnergy) {
                    player.sendSystemMessage(Component.translatable("message.monkeycraft.overcharge"));
                } else {
                    entity.discard();
                    stack.hurtAndBreak(recoverEnergy, player, Player.getSlotForHand(interaction));
                    this.summonedEntities.remove(removeEntity);
                }
                return InteractionResult.SUCCESS;
            }
        }
        return InteractionResult.PASS;
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
        if (selectedPosition > this.maxPositions) {
            this.selectedPosition = 0;
        }
        if (Minecraft.getInstance().player != null) {
            switch (this.selectedPosition){
                case 0 -> Minecraft.getInstance().player.sendSystemMessage(
                        Component.translatable("message.monkeycraft.selected", Component.translatable("entity.monkeycraft.monkey_dart")));
                case 1 -> Minecraft.getInstance().player.sendSystemMessage(
                        Component.translatable("message.monkeycraft.selected", Component.translatable("entity.monkeycraft.monkey_wizard")));
                case 2 -> Minecraft.getInstance().player.sendSystemMessage(
                        Component.translatable("message.monkeycraft.selected", Component.translatable("entity.monkeycraft.monkey_tank")));
                case 3 -> Minecraft.getInstance().player.sendSystemMessage(
                        Component.translatable("message.monkeycraft.selected", Component.translatable("entity.monkeycraft.monkey_jester")));
            }
        }
    }

    @Override
    public boolean isValidRepairItem(ItemStack toRepair, ItemStack repair) {
        if (repair.is(com.clase.monkeycraft.item.ModItems.BANANA.get())) {
            return true;
        }
        return super.isValidRepairItem(toRepair, repair);
    }
    public void setSelectedPosition(int newPosition, Player player) {
        if (newPosition <= this.maxPositions) {
            this.selectedPosition = newPosition;
            if (player != null) {
                switch (this.selectedPosition) {
                    case 0 -> player.sendSystemMessage(Component.translatable("message.monkeycraft.selected", Component.translatable("entity.monkeycraft.monkey_dart")));
                    case 1 -> player.sendSystemMessage(Component.translatable("message.monkeycraft.selected", Component.translatable("entity.monkeycraft.monkey_wizard")));
                    case 2 -> player.sendSystemMessage(Component.translatable("message.monkeycraft.selected", Component.translatable("entity.monkeycraft.monkey_tank")));
                    case 3 -> player.sendSystemMessage(Component.translatable("message.monkeycraft.selected", Component.translatable("entity.monkeycraft.monkey_jester")));
                }
            }
        }
    }
}