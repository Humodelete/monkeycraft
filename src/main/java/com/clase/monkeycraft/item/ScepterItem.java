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
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
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

import java.util.List;
import java.util.UUID;

public class ScepterItem extends TieredItem {
    int selectedPosition = 0;
    int maxPositions = 0;
    String scepterId;

    int DART_MONKEY_COST = 1;
    int MAGE_MONKEY_COST = 2;
    int TANK_MONKEY_COST = 3;
    int JESTER_MONKEY_COST = 4;

    public ScepterItem(Tier tier, Item.Properties properties) {
        super(tier, properties.component(DataComponents.TOOL, createToolProperties()));
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
        Level dimension = context.getLevel();
        if (!dimension.isClientSide()) {
            BlockPos pos = context.getClickedPos();
            Player player = context.getPlayer();

            int currentCost = 0;
            switch (selectedPosition){
                case 0 -> currentCost = DART_MONKEY_COST;
                case 1 -> currentCost = MAGE_MONKEY_COST;
                case 2 -> currentCost = TANK_MONKEY_COST;
                case 3 -> currentCost = JESTER_MONKEY_COST;
            }

            if (context.getItemInHand().getDamageValue() < context.getItemInHand().getMaxDamage() - currentCost) {

                if (selectedPosition == 2 && dimension instanceof ServerLevel serverLevel) {
                    int tankCount = 0;
                    for (Entity e : serverLevel.getAllEntities()) {
                        if (e instanceof MonkeyTankEntity tank) {
                            if (player != null && player.getUUID().equals(tank.getOwnerUUID())) {
                                tankCount++;
                            }
                        }
                        else if (e instanceof com.clase.monkeycraft.entity.custom.SummonCircleEntity circle) {
                            if (circle.getMonkeyTier() == 2 && player != null && player.getUUID().equals(circle.getOwnerId())) {
                                tankCount++;
                            }
                        }
                    }
                    if (tankCount >= 1) {
                        if (player != null) {
                            player.sendSystemMessage(Component.translatable("message.monkeycraft.max_tanks"));
                        }
                        return InteractionResult.FAIL;
                    }
                }

                com.clase.monkeycraft.entity.custom.SummonCircleEntity circle = ModEntities.SUMMON_CIRCLE.get().create(dimension);
                if (circle != null) {
                    circle.moveTo(pos.getX() + 0.5, pos.getY() + 1.0, pos.getZ() + 0.5);

                    circle.setMonkeyTier(this.selectedPosition);
                    if (player != null) {
                        circle.setOwnerId(player.getUUID());
                    }

                    dimension.addFreshEntity(circle);

                    if (player != null) {
                        context.getItemInHand().hurtAndBreak(currentCost, player, LivingEntity.getSlotForHand(context.getHand()));
                    }
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
    public net.minecraft.world.InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);

        if (player.isCrouching() && !level.isClientSide() && level instanceof ServerLevel serverLevel) {
            int totalEnergyRecovered = 0;
            int monkeysRecalled = 0;

            for (Entity e : serverLevel.getAllEntities()) {
                if (e instanceof net.minecraft.world.entity.TamableAnimal tamable && player.getUUID().equals(tamable.getOwnerUUID())) {

                    int energy = 0;
                    EntityType<?> type = e.getType();

                    if (type == ModEntities.MONKEY_DART.get()) energy = DART_MONKEY_COST;
                    else if (type == ModEntities.MONKEY_WIZARD.get()) energy = MAGE_MONKEY_COST;
                    else if (type == ModEntities.MONKEY_TANK.get()) energy = TANK_MONKEY_COST;
                    else if (type == ModEntities.MONKEY_JESTER.get()) energy = JESTER_MONKEY_COST;

                    if (energy > 0) {
                        // Pequeño humo para indicar que se han desinvocado a distancia
                        serverLevel.sendParticles(ParticleTypes.POOF, e.getX(), e.getY() + 0.5, e.getZ(), 15, 0.5, 0.5, 0.5, 0.05);
                        e.discard();
                        totalEnergyRecovered += energy;
                        monkeysRecalled++;
                    }
                }
            }

            if (monkeysRecalled > 0) {
                stack.setDamageValue(Math.max(0, stack.getDamageValue() - totalEnergyRecovered));
                level.playSound(null, player.blockPosition(), SoundEvents.ENDERMAN_TELEPORT, SoundSource.PLAYERS, 1.0F, 1.0F);
                player.sendSystemMessage(Component.literal("§a¡Has retirado " + monkeysRecalled + " invocaciones de golpe!"));
                return net.minecraft.world.InteractionResultHolder.success(stack);
            }
        }

        return super.use(level, player, hand);
    }

    @Override
    public InteractionResult interactLivingEntity(ItemStack stack, Player player, LivingEntity entity, InteractionHand interaction) {
        if (!player.level().isClientSide() && entity instanceof net.minecraft.world.entity.TamableAnimal tamable) {

            if (player.getUUID().equals(tamable.getOwnerUUID())) {
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

                if (recoverEnergy != 0) {
                    if (stack.getDamageValue() < -recoverEnergy) {
                        player.sendSystemMessage(Component.translatable("message.monkeycraft.overcharge"));
                    } else {
                        if (player.level() instanceof ServerLevel serverLevel) {
                            if (type == ModEntities.MONKEY_DART.get()) {
                                serverLevel.sendParticles(ParticleTypes.CLOUD, entity.getX(), entity.getY() + 0.5, entity.getZ(), 30, 0.5, 0.5, 0.5, 0.1);
                            } else if (type == ModEntities.MONKEY_WIZARD.get()) {
                                serverLevel.sendParticles(ParticleTypes.WITCH, entity.getX(), entity.getY() + 1.0, entity.getZ(), 50, 0.5, 1.0, 0.5, 0.1);
                                serverLevel.playSound(null, entity.blockPosition(), SoundEvents.ILLUSIONER_MIRROR_MOVE, SoundSource.PLAYERS, 1.0F, 1.0F);
                            } else if (type == ModEntities.MONKEY_TANK.get()) {
                                serverLevel.sendParticles(ParticleTypes.CAMPFIRE_COSY_SMOKE, entity.getX(), entity.getY() + 0.5, entity.getZ(), 30, 0.5, 0.5, 0.5, 0.05);
                            } else if (type == ModEntities.MONKEY_JESTER.get()) {
                                serverLevel.sendParticles(ParticleTypes.FIREWORK, entity.getX(), entity.getY() + 1.0, entity.getZ(), 30, 0.5, 1.0, 0.5, 0.1);
                            }
                        }

                        entity.discard();
                        stack.setDamageValue(Math.max(0, stack.getDamageValue() + recoverEnergy));
                    }
                    return InteractionResult.SUCCESS;
                }
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