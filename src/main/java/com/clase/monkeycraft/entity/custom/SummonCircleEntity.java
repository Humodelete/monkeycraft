package com.clase.monkeycraft.entity.custom;

import com.clase.monkeycraft.entity.ModEntities;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
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
    private java.util.UUID ownerId;

    public void setOwnerId(java.util.UUID uuid) {
        this.ownerId = uuid;
    }
    public java.util.UUID getOwnerId() {
        return this.ownerId;
    }

    private static final EntityDataAccessor<Integer> MONKEY_TIER = SynchedEntityData.defineId(SummonCircleEntity.class, EntityDataSerializers.INT);

    public SummonCircleEntity(EntityType<?> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        builder.define(MONKEY_TIER, 0);
    }

    public void setMonkeyTier(int tier) {
        this.entityData.set(MONKEY_TIER, tier);
    }

    public int getMonkeyTier() {
        return this.entityData.get(MONKEY_TIER);
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

            switch (this.getMonkeyTier()) {
                case 0 -> {
                    monkeyToSummon = ModEntities.MONKEY_DART.get().create(this.level());
                    if (this.level() instanceof ServerLevel serverLevel) {
                        serverLevel.sendParticles(ParticleTypes.CLOUD, this.getX(), this.getY() + 0.5, this.getZ(), 30, 0.5, 0.5, 0.5, 0.1);
                    }
                }
                case 1 -> {
                    monkeyToSummon = ModEntities.MONKEY_WIZARD.get().create(this.level());
                    if (this.level() instanceof ServerLevel serverLevel) {
                        net.minecraft.world.entity.LightningBolt lightning = EntityType.LIGHTNING_BOLT.create(serverLevel);
                        if (lightning != null) {
                            lightning.moveTo(this.getX(), this.getY(), this.getZ());
                            lightning.setVisualOnly(true);
                            serverLevel.addFreshEntity(lightning);
                        }
                    }
                }
                case 2 -> {
                    monkeyToSummon = ModEntities.MONKEY_TANK.get().create(this.level());
                    if (this.level() instanceof ServerLevel serverLevel) {
                        serverLevel.sendParticles(ParticleTypes.EXPLOSION, this.getX(), this.getY() + 0.5, this.getZ(), 2, 0.5, 0.5, 0.5, 0.0);
                        serverLevel.sendParticles(ParticleTypes.CAMPFIRE_COSY_SMOKE, this.getX(), this.getY() + 0.5, this.getZ(), 40, 1.0, 1.0, 1.0, 0.05);
                    }
                }
                case 3 -> {
                    monkeyToSummon = ModEntities.MONKEY_JESTER.get().create(this.level());
                    if (this.level() instanceof ServerLevel serverLevel) {
                        serverLevel.sendParticles(ParticleTypes.FIREWORK, this.getX(), this.getY() + 1.0, this.getZ(), 50, 0.5, 1.0, 0.5, 0.2);
                    }
                }
            }

            if (monkeyToSummon != null) {
                monkeyToSummon.setPos(this.getX(), this.getY(), this.getZ());
                if (monkeyToSummon instanceof net.minecraft.world.entity.TamableAnimal tamable && this.ownerId != null) {
                    tamable.setTame(true, true);
                    tamable.setOwnerUUID(this.ownerId);
                }
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
        if (compound.contains("MonkeyTier")) this.setMonkeyTier(compound.getInt("MonkeyTier"));
    }

    @Override
    protected void addAdditionalSaveData(CompoundTag compound) {
        compound.putInt("Lifespan", this.maxLifespan);
        compound.putInt("MonkeyTier", this.getMonkeyTier());
    }
}