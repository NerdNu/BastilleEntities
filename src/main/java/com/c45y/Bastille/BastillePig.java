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
package com.c45y.Bastille;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.minecraft.server.v1_8_R3.*;


public class BastillePig extends EntityPig {

    private List<DamageSource> ignoreDamageTypes = new ArrayList<DamageSource>();

    public BastillePig(World world) {
        super(world);
    }

    @Override
    public boolean damageEntity(DamageSource damagesource, float f) {
        if (this.ignoreDamageTypes.contains(damagesource)) {
            return false;
        }
        return super.damageEntity(damagesource, f);
    }

    public BastillePig ignoreDamageSource(DamageSource damagesource) {
        this.ignoreDamageTypes.add(damagesource);
        return this;
    }


    public BastillePig speed(float speed) {
        this.getAttributeInstance(GenericAttributes.MOVEMENT_SPEED).setValue(speed);
        return this;
    }

    public BastillePig sprinting(boolean sprinting) {
        this.setSprinting(sprinting);
        return this;
    }

    public BastillePig health(float h) {
        this.setHealth(h);
        return this;
    }

    public BastillePig maxhealth(double max) {
        this.getAttributeInstance(GenericAttributes.maxHealth).setValue(max);
        return this;
    }

    public BastillePig damage(double damage) {
        this.getAttributeInstance(GenericAttributes.ATTACK_DAMAGE).setValue(damage);
        return this;
    }

    public BastillePig emtpyGoals() {
        this.goalSelector = new PathfinderGoalSelector(world != null && world.methodProfiler != null ? world.methodProfiler : null);
        return this;
    }

    public BastillePig addGoal(int index, PathfinderGoal goal) {
        this.goalSelector.a(index, goal);
        return this;
    }

    public BastillePig emtpyTargets() {
        this.targetSelector = new PathfinderGoalSelector(world != null && world.methodProfiler != null ? world.methodProfiler : null);
        return this;
    }

    public BastillePig addTarget(int index, PathfinderGoal goal) {
        this.targetSelector.a(index, goal);
        return this;
    }

    private static Object getPrivateStatic(Class clazz, String f) throws Exception {
        Field field = clazz.getDeclaredField(f);
        field.setAccessible(true);
        return field.get(null);
    }

    public static void patch() {
        try {
            ((Map) getPrivateStatic(EntityTypes.class, "c")).put("Pig", BastillePig.class);
            ((Map) getPrivateStatic(EntityTypes.class, "d")).put(BastillePig.class, "Pig");
            ((Map) getPrivateStatic(EntityTypes.class, "e")).put(90, BastillePig.class);
            ((Map) getPrivateStatic(EntityTypes.class, "f")).put(BastillePig.class, 90);
            ((Map) getPrivateStatic(EntityTypes.class, "g")).put("Pig", 90);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}