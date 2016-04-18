/*
 * The MIT License
 *
 * Copyright 2015 c45y.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.c45y.Bastille.Entities;
import com.c45y.Bastille.BastilleEntity;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.minecraft.server.v1_9_R1.*;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_9_R1.CraftWorld;


public class BastilleMagmaCube extends EntityMagmaCube implements BastilleEntity {
	private boolean isCustomEntity = false;
	private List<DamageSource> ignoreDamageTypes = new ArrayList<DamageSource>();

	public BastilleMagmaCube(World world) {
		super(world);
	}

	public BastilleMagmaCube(org.bukkit.World world) {
		super(((CraftWorld)world).getHandle());
	}

	public boolean hasBeenModified() {
		return isCustomEntity;
	}

	@Override
	public boolean damageEntity(DamageSource damagesource, float f) {
		if (this.ignoreDamageTypes.contains(damagesource)) {
			return false;
		}
		return super.damageEntity(damagesource, f);
	}

	public BastilleMagmaCube setDropChance(EnumItemSlot slot, float chance) {
		isCustomEntity = true;
		this.a(slot, chance);
		return this;
	}

	public BastilleMagmaCube ignoreDamageSource(DamageSource damagesource) {
		isCustomEntity = true;
		this.ignoreDamageTypes.add(damagesource);
		return this;
	}


	public BastilleMagmaCube speed(float speed) {
		isCustomEntity = true;
		this.getAttributeInstance(GenericAttributes.MOVEMENT_SPEED).setValue(speed);
		return this;
	}

	public BastilleMagmaCube sprinting(boolean sprinting) {
		isCustomEntity = true;
		this.setSprinting(sprinting);
		return this;
	}

	public BastilleMagmaCube health(float h) {
		isCustomEntity = true;
		this.setHealth(h);
		return this;
	}

	public BastilleMagmaCube maxhealth(double max) {
		isCustomEntity = true;
		this.getAttributeInstance(GenericAttributes.maxHealth).setValue(max);
		return this;
	}

	public BastilleMagmaCube damage(double damage) {
		isCustomEntity = true;
		this.getAttributeInstance(GenericAttributes.ATTACK_DAMAGE).setValue(damage);
		return this;
	}

	public BastilleMagmaCube emtpyGoals() {
		isCustomEntity = true;
		this.goalSelector = new PathfinderGoalSelector(world != null && world.methodProfiler != null ? world.methodProfiler : null);
		return this;
	}

	public BastilleMagmaCube addGoal(int index, PathfinderGoal goal) {
		isCustomEntity = true;
		this.goalSelector.a(index, goal);
		return this;
	}

	public BastilleMagmaCube emtpyTargets() {
		isCustomEntity = true;
		this.targetSelector = new PathfinderGoalSelector(world != null && world.methodProfiler != null ? world.methodProfiler : null);
		return this;
	}

	public BastilleMagmaCube addTarget(int index, PathfinderGoal goal) {
		isCustomEntity = true;
		this.targetSelector.a(index, goal);
		return this;
	}

	private static Object getPrivateStatic(Class clazz, String f) throws Exception {
		Field field = clazz.getDeclaredField(f);
		field.setAccessible(true);
		return field.get(null);
	}

	public BastilleMagmaCube spawn(Location loc) {
		this.setLocation(loc.getX(), loc.getY(), loc.getZ(), loc.getYaw(), loc.getPitch());
		this.world.addEntity(this);
		return this;
	}

	public static void patch() {
		try {
			((Map) getPrivateStatic(EntityTypes.class, "c")).put("LavaSlime", BastilleMagmaCube.class);
			((Map) getPrivateStatic(EntityTypes.class, "d")).put(BastilleMagmaCube.class, "LavaSlime");
			((Map) getPrivateStatic(EntityTypes.class, "e")).put(62, BastilleMagmaCube.class);
			((Map) getPrivateStatic(EntityTypes.class, "f")).put(BastilleMagmaCube.class, 62);
			((Map) getPrivateStatic(EntityTypes.class, "g")).put("LavaSlime", 62);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
