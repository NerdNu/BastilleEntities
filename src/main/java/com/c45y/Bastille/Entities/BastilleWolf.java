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

import net.minecraft.server.v1_8_R3.*;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;


public class BastilleWolf extends EntityWolf implements BastilleEntity {
	private boolean isCustomEntity = false;
	private List<DamageSource> ignoreDamageTypes = new ArrayList<DamageSource>();

	public BastilleWolf(World world) {
		super(world);
	}

	public BastilleWolf(org.bukkit.World world) {
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

	public BastilleWolf setDropChance(int slot, float chance) {
		isCustomEntity = true;
		this.dropChances[slot] = chance;
		return this;
	}

	public BastilleWolf ignoreDamageSource(DamageSource damagesource) {
		isCustomEntity = true;
		this.ignoreDamageTypes.add(damagesource);
		return this;
	}


	public BastilleWolf speed(float speed) {
		isCustomEntity = true;
		this.getAttributeInstance(GenericAttributes.MOVEMENT_SPEED).setValue(speed);
		return this;
	}

	public BastilleWolf sprinting(boolean sprinting) {
		isCustomEntity = true;
		this.setSprinting(sprinting);
		return this;
	}

	public BastilleWolf health(float h) {
		isCustomEntity = true;
		this.setHealth(h);
		return this;
	}

	public BastilleWolf maxhealth(double max) {
		isCustomEntity = true;
		this.getAttributeInstance(GenericAttributes.maxHealth).setValue(max);
		return this;
	}

	public BastilleWolf damage(double damage) {
		isCustomEntity = true;
		this.getAttributeInstance(GenericAttributes.ATTACK_DAMAGE).setValue(damage);
		return this;
	}

	public BastilleWolf emtpyGoals() {
		isCustomEntity = true;
		this.goalSelector = new PathfinderGoalSelector(world != null && world.methodProfiler != null ? world.methodProfiler : null);
		return this;
	}

	public BastilleWolf addGoal(int index, PathfinderGoal goal) {
		isCustomEntity = true;
		this.goalSelector.a(index, goal);
		return this;
	}

	public BastilleWolf emtpyTargets() {
		isCustomEntity = true;
		this.targetSelector = new PathfinderGoalSelector(world != null && world.methodProfiler != null ? world.methodProfiler : null);
		return this;
	}

	public BastilleWolf addTarget(int index, PathfinderGoal goal) {
		isCustomEntity = true;
		this.targetSelector.a(index, goal);
		return this;
	}

	private static Object getPrivateStatic(Class clazz, String f) throws Exception {
		Field field = clazz.getDeclaredField(f);
		field.setAccessible(true);
		return field.get(null);
	}

	public BastilleWolf spawn(Location loc) {
		this.setLocation(loc.getX(), loc.getY(), loc.getZ(), loc.getYaw(), loc.getPitch());
		this.world.addEntity(this);
		return this;
	}

	public static void patch() {
		try {
			((Map) getPrivateStatic(EntityTypes.class, "c")).put("Wolf", BastilleWolf.class);
			((Map) getPrivateStatic(EntityTypes.class, "d")).put(BastilleWolf.class, "Wolf");
			((Map) getPrivateStatic(EntityTypes.class, "e")).put(95, BastilleWolf.class);
			((Map) getPrivateStatic(EntityTypes.class, "f")).put(BastilleWolf.class, 95);
			((Map) getPrivateStatic(EntityTypes.class, "g")).put("Wolf", 95);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
