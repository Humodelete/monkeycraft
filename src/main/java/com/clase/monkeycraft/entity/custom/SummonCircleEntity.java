package com.clase.monkeycraft.entity.custom;

import com.clase.monkeycraft.entity.ModEntities;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.core.particles.ParticleTypes;

public class SummonCircleEntity extends Entity {
    private int maxLifespan = 40;
    private int monkeyTier = 0; // Guardará qué mono invocar

    public SummonCircleEntity(EntityType<?> entityType, Level level) {
        super(entityType, level);
    }

    public void setMonkeyTier(int tier) {
        this.monkeyTier = tier;
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
    }

    @Override
    public void tick() {
        super.tick();

        if (this.level() instanceof ServerLevel serverLevel) {
            if (this.tickCount % 5 == 0) {
                serverLevel.sendParticles(ParticleTypes.ENCHANT, this.getX(), this.getY() + 0.1, this.getZ(), 5, 0.5, 0.0, 0.5, 0.1);
            }
        }

        if (this.tickCount >= maxLifespan && !this.level().isClientSide()) {
            LivingEntity monkeyToSummon = null;

            switch (monkeyTier) {
                case 0 -> monkeyToSummon = ModEntities.MONKEY_DART.get().create(this.level());
                case 1 -> monkeyToSummon = ModEntities.MONKEY_TANK.get().create(this.level());
                case 2 -> monkeyToSummon = ModEntities.MONKEY_WIZARD.get().create(this.level());
                case 3 -> monkeyToSummon = ModEntities.MONKEY_JESTER.get().create(this.level());
            }

            if (monkeyToSummon != null) {
                monkeyToSummon.setPos(this.getX(), this.getY(), this.getZ());
                this.level().addFreshEntity(monkeyToSummon);

                this.level().playSound(null, this.blockPosition(), SoundEvents.EVOKER_CAST_SPELL, SoundSource.PLAYERS, 1.0F, 1.0F);

                if (this.level() instanceof ServerLevel serverLevel) {
                    serverLevel.sendParticles(ParticleTypes.POOF, this.getX(), this.getY() + 0.5, this.getZ(), 20, 0.5, 0.5, 0.5, 0.05);
                }
            }

            this.discard();
        }
    }

    @Override
    protected void readAdditionalSaveData(CompoundTag compound) {
        if (compound.contains("Lifespan")) this.maxLifespan = compound.getInt("Lifespan");
        if (compound.contains("MonkeyTier")) this.monkeyTier = compound.getInt("MonkeyTier");
    }

    @Override
    protected void addAdditionalSaveData(CompoundTag compound) {
        compound.putInt("Lifespan", this.maxLifespan);
        compound.putInt("MonkeyTier", this.monkeyTier);
    }
}