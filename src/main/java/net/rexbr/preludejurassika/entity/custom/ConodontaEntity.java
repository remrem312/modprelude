package net.rexbr.preludejurassika.entity.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.ai.goal.PanicGoal;
import net.minecraft.world.entity.ai.goal.RandomSwimmingGoal;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.ai.navigation.WaterBoundPathNavigation;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Bucketable;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraftforge.common.ForgeMod;
import net.rexbr.preludejurassika.item.ModItems;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class ConodontaEntity extends Animal implements IAnimatable, Bucketable {
    private AnimationFactory factory = new AnimationFactory(this);

    private static final EntityDataAccessor<Boolean> FROM_BUCKET = SynchedEntityData.defineId(ConodontaEntity.class, EntityDataSerializers.BOOLEAN);

    public ConodontaEntity(EntityType<? extends Animal> entityType, Level level) {
        super(entityType, level);
        this.setPathfindingMalus(BlockPathTypes.WATER, 0);
        this.moveControl = new MoveControl(this) {

            @Override
            public void tick() {
                if (ConodontaEntity.this.isInWater())
                    ConodontaEntity.this.setDeltaMovement(ConodontaEntity.this.getDeltaMovement().add(0, 0.005, 0));
                if (this.operation == MoveControl.Operation.MOVE_TO && !ConodontaEntity.this.getNavigation().isDone()) {
                    double dx = this.wantedX - ConodontaEntity.this.getX();
                    double dy = this.wantedY - ConodontaEntity.this.getY();
                    double dz = this.wantedZ - ConodontaEntity.this.getZ();
                    float f = (float) (Mth.atan2(dz, dx) * (double) (180 / Math.PI)) - 90;
                    float f1 = (float) (this.speedModifier * ConodontaEntity.this.getAttribute(Attributes.MOVEMENT_SPEED).getValue());
                    ConodontaEntity.this.setYRot(this.rotlerp(ConodontaEntity.this.getYRot(), f, 10));
                    ConodontaEntity.this.yBodyRot = ConodontaEntity.this.getYRot();
                    ConodontaEntity.this.yHeadRot = ConodontaEntity.this.getYRot();
                    if (ConodontaEntity.this.isInWater()) {
                        ConodontaEntity.this.setSpeed((float) ConodontaEntity.this.getAttribute(Attributes.MOVEMENT_SPEED).getValue());
                        float f2 = -(float) (Mth.atan2(dy, (float) Math.sqrt(dx * dx + dz * dz)) * (180 / Math.PI));
                        f2 = Mth.clamp(Mth.wrapDegrees(f2), -85, 85);
                        ConodontaEntity.this.setXRot(this.rotlerp(ConodontaEntity.this.getXRot(), f2, 5));
                        float f3 = Mth.cos(ConodontaEntity.this.getXRot() * (float) (Math.PI / 180.0));
                        ConodontaEntity.this.setZza(f3 * f1);
                        ConodontaEntity.this.setYya((float) (f1 * dy));
                    } else {
                        ConodontaEntity.this.setSpeed(f1 * 0.05F);
                    }
                } else {
                    ConodontaEntity.this.setSpeed(0);
                    ConodontaEntity.this.setYya(0);
                    ConodontaEntity.this.setZza(0);
                }
            }
        };

    }

    @Override
    protected PathNavigation createNavigation(Level world) {
        return new WaterBoundPathNavigation(this, world);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(1, new RandomSwimmingGoal(this, 3, 40));
        this.goalSelector.addGoal(2, new PanicGoal(this, 1.2));
    }

    @Override
    public MobType getMobType() {
        return MobType.WATER;
    }

    @Override
    public boolean hurt(DamageSource source, float amount) {
        if (source == DamageSource.DROWN)
            return false;
        return super.hurt(source, amount);
    }

    @Override
    public boolean canBreatheUnderwater() {
        return true;
    }

    @Override
    public boolean isPushedByFluid() {
        return false;
    }


    public static AttributeSupplier setAttributes() {
        return Animal.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 8.0D)
                .add(Attributes.ATTACK_DAMAGE, 4f)
                .add(Attributes.ATTACK_SPEED, 2.0f)
                .add(ForgeMod.SWIM_SPEED.get(), 3.0f)
                .add(Attributes.MOVEMENT_SPEED, 0.25f).build();
    }



    @Nullable
    public AgeableMob getBreedOffspring(ServerLevel p_146743_, AgeableMob p_146744_) {
        return null;
    }

    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {

        if (event.isMoving() && this.isInWater()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.conodonta.swim", true));
            return PlayState.CONTINUE;
        }
        if (event.isMoving() && this.isInWaterOrBubble()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.conodonta.swim", true));
            return PlayState.CONTINUE;
        }
        if (this.isInWaterOrBubble()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.conodonta.swim", true));
            return PlayState.CONTINUE;
        }
        if (event.isMoving() && this.isOnGround()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.conodonta.swim", true));
            return PlayState.CONTINUE;
        }
        if (this.isOnGround())
        {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.conodonta.landed", true));
            return PlayState.CONTINUE;
        }
        return PlayState.STOP;
    }



    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController(this, "controller",
                0, this::predicate));

    }

    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }

    protected void playStepSound(BlockPos pos, BlockState blockIn) {
        this.playSound(SoundEvents.GRASS_STEP, 0.15F, 1.0F);
    }

    @Override
    public boolean fromBucket() {
        return this.entityData.get(FROM_BUCKET);
    }

    @Override
    public void setFromBucket(boolean p_148834_) {
        this.entityData.set(FROM_BUCKET, p_148834_);
    }

    private boolean isFromBucket() {
        return this.entityData.get(FROM_BUCKET);
    }

    @Override
    public void saveToBucketTag(ItemStack p_148833_) {

    }

    @Override
    public void loadFromBucketTag(CompoundTag p_148832_) {

    }

    public ItemStack getBucketItemStack() {
        return new ItemStack(ModItems.CONODONTA_BUCKET.get());
    }

    @Override
    public SoundEvent getPickupSound() {
        return SoundEvents.BUCKET_EMPTY_FISH;
    }

    public InteractionResult mobInteract(Player p_27477_, InteractionHand p_27478_) {
        return Bucketable.bucketMobPickup(p_27477_, p_27478_, this).orElse(super.mobInteract(p_27477_, p_27478_));
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(FROM_BUCKET, false);

    }

    public boolean removeWhenFarAway(double p_213397_1_) {
        return !this.fromBucket() && !this.hasCustomName();
    }

    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        compound.putBoolean("FromBucket", this.fromBucket());

    }

    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        this.setFromBucket(compound.getBoolean("FromBucket"));

    }
}
