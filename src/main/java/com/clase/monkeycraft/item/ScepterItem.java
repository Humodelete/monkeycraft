package com.clase.monkeycraft.item;


import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.Wolf;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.Skeleton;
import net.minecraft.world.entity.monster.Slime;
import net.minecraft.world.entity.monster.Zombie;
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
    List<Integer> summonId;

    public ScepterItem(Tier tier, Item.Properties properties){
        super(tier, properties.component(DataComponents.TOOL, createToolProperties()));
        this.scepterId = UUID.randomUUID().toString();
        this.summonId = new ArrayList<>();
    }

    private static Tool createToolProperties() {
        return new Tool(List.of(Tool.Rule.minesAndDrops(List.of(Blocks.COBWEB), 15.0F), Tool.Rule.overrideSpeed(BlockTags.SWORD_EFFICIENT, 1.5F)), 1.0F, 2);
    }

    public static ItemAttributeModifiers createAttributes(Tier tier, int attackDamage, float attackSpeed) {
        return ItemAttributeModifiers.builder()
                .add(
                        Attributes.ATTACK_DAMAGE,
                        new AttributeModifier(BASE_ATTACK_DAMAGE_ID, (double)((float)attackDamage + tier.getAttackDamageBonus()),
                                AttributeModifier.Operation.ADD_VALUE),
                        EquipmentSlotGroup.MAINHAND
                )
                .add(
                        Attributes.ATTACK_SPEED, new AttributeModifier(BASE_ATTACK_SPEED_ID, (double)attackSpeed,
                                AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.MAINHAND
                )
                .build();
    }

    @Override
    public InteractionResult useOn(
            UseOnContext context
    ) {
        if (!context.getLevel().isClientSide()) {
            Level dimension = context.getLevel();
            BlockPos pos = context.getClickedPos();
            Player player = context.getPlayer();

            //Replace Wolf with Monkey once it's done
            Wolf wolf = EntityType.WOLF.create(dimension);
            Slime slime = EntityType.SLIME.create(dimension);
            Zombie zombie = EntityType.ZOMBIE.create(dimension);
            Skeleton skeleton = EntityType.SKELETON.create(dimension);
            Entity[] summons = {wolf, slime, zombie, skeleton};


            if(player.isCrouching()){
            this.selectedPosition += 1;
            if(selectedPosition >3){
                this.selectedPosition = 0;
            }
            String selectedEntity = summons[selectedPosition].getName().getString();
                Minecraft.getInstance().player.sendSystemMessage(Component.translatable("message.monkeycraft.selected", selectedEntity));
                return InteractionResult.CONSUME;
            } else if (summons[selectedPosition] != null &&
            context.getItemInHand().getDamageValue() < context.getItemInHand().getMaxDamage() - 1) {
                summons[selectedPosition].moveTo(
                        pos.getX() + 0.5,
                        pos.getY() + 1,
                        pos.getZ() + 0.5
                );
                dimension.addFreshEntity(summons[selectedPosition]);
                this.summonId.add(summons[selectedPosition].getId());
                context.getItemInHand().hurtAndBreak(1, player, LivingEntity.getSlotForHand(context.getHand()));
            } else {
                Minecraft.getInstance().player.sendSystemMessage(Component.translatable("message.monkeycraft.low_energy"));
            }
        }
        return InteractionResult.SUCCESS;
    }

    @Override
    public InteractionResult interactLivingEntity(ItemStack stack, Player player,
                                                  LivingEntity entity, InteractionHand interaction){
        boolean thisScepter= false;
        for(int id : summonId){
            if(id == entity.getId()){
                thisScepter = true;
            }
        }
        if(thisScepter){
            entity.moveTo(
                    entity.getX(),
                    -200,
                    entity.getZ()
            );
            entity.kill();
            stack.hurtAndBreak(-1,player, Player.getSlotForHand(interaction));
        }
        return InteractionResult.SUCCESS;
    }

    @Override
    public boolean mineBlock(ItemStack itemStack, Level p_41417_, BlockState p_41418_, BlockPos p_41419_, LivingEntity p_41420_) {
        Tool tool = itemStack.get(DataComponents.TOOL);
        if (tool == null) {
            return false;
        } else {
            return true;
        }
    }
    public String getScepterId(){
        return this.scepterId;
    }
}
