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

import java.util.UUID;
import net.minecraft.server.v1_8_R3.DamageSource;
import net.minecraft.server.v1_8_R3.ItemStack;
import net.minecraft.server.v1_8_R3.PathfinderGoal;
import org.bukkit.Location;

/**
 *
 * @author c45y
 */
public interface BastilleEntity {
    /* Common ancestor for instance checking */
    public boolean hasBeenModified();
    
    public UUID getUniqueID();
    public BastilleEntity ignoreDamageSource(DamageSource damagesource);
    public BastilleEntity speed(float speed);
    public BastilleEntity sprinting(boolean sprinting);
    public BastilleEntity health(float h);
    public BastilleEntity maxhealth(double max);
    public BastilleEntity damage(double damage);
    public BastilleEntity emtpyGoals();
    public BastilleEntity addGoal(int index, PathfinderGoal goal);
    public BastilleEntity emtpyTargets();
    public BastilleEntity addTarget(int index, PathfinderGoal goal);
    public BastilleEntity spawn(Location loc);
    public BastilleEntity setDropChance(int slot, float chance);
    public BastilleEntity setExpToDrop(int exp);
    
    public void setCustomName(String name);
    public void setCustomNameVisible(boolean bln);
    public void setEquipment(int i, ItemStack itemstack);
    public ItemStack[] getEquipment();
}
