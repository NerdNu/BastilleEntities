from string import Template
import os

classtemplate = Template('''/*
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


public class Bastille${entityName} extends Entity${entityName} implements BastilleEntity {
	private boolean isCustomEntity = false;
	private List<DamageSource> ignoreDamageTypes = new ArrayList<DamageSource>();

	public Bastille${entityName}(World world) {
		super(world);
	}

	public Bastille${entityName}(org.bukkit.World world) {
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

	public Bastille${entityName} setExpToDrop(int exp) {
		isCustomEntity = true;
		this.expToDrop = exp;
		return this;
	}

	public Bastille${entityName} setDropChance(int slot, float chance) {
		isCustomEntity = true;
		this.dropChances[slot] = chance;
		return this;
	}

	public Bastille${entityName} ignoreDamageSource(DamageSource damagesource) {
		isCustomEntity = true;
		this.ignoreDamageTypes.add(damagesource);
		return this;
	}


	public Bastille${entityName} speed(float speed) {
		isCustomEntity = true;
		this.getAttributeInstance(GenericAttributes.MOVEMENT_SPEED).setValue(speed);
		return this;
	}

	public Bastille${entityName} sprinting(boolean sprinting) {
		isCustomEntity = true;
		this.setSprinting(sprinting);
		return this;
	}

	public Bastille${entityName} health(float h) {
		isCustomEntity = true;
		this.setHealth(h);
		return this;
	}

	public Bastille${entityName} maxhealth(double max) {
		isCustomEntity = true;
		this.getAttributeInstance(GenericAttributes.maxHealth).setValue(max);
		return this;
	}

	public Bastille${entityName} damage(double damage) {
		isCustomEntity = true;
		this.getAttributeInstance(GenericAttributes.ATTACK_DAMAGE).setValue(damage);
		return this;
	}

	public Bastille${entityName} emtpyGoals() {
		isCustomEntity = true;
		this.goalSelector = new PathfinderGoalSelector(world != null && world.methodProfiler != null ? world.methodProfiler : null);
		return this;
	}

	public Bastille${entityName} addGoal(int index, PathfinderGoal goal) {
		isCustomEntity = true;
		this.goalSelector.a(index, goal);
		return this;
	}

	public Bastille${entityName} emtpyTargets() {
		isCustomEntity = true;
		this.targetSelector = new PathfinderGoalSelector(world != null && world.methodProfiler != null ? world.methodProfiler : null);
		return this;
	}

	public Bastille${entityName} addTarget(int index, PathfinderGoal goal) {
		isCustomEntity = true;
		this.targetSelector.a(index, goal);
		return this;
	}

	private static Object getPrivateStatic(Class clazz, String f) throws Exception {
		Field field = clazz.getDeclaredField(f);
		field.setAccessible(true);
		return field.get(null);
	}

	public Bastille${entityName} spawn(Location loc) {
		this.setLocation(loc.getX(), loc.getY(), loc.getZ(), loc.getYaw(), loc.getPitch());
		this.world.addEntity(this);
		return this;
	}

	public static void patch() {
		try {
			((Map) getPrivateStatic(EntityTypes.class, "c")).put("${entityNetworkName}", Bastille${entityName}.class);
			((Map) getPrivateStatic(EntityTypes.class, "d")).put(Bastille${entityName}.class, "${entityNetworkName}");
			((Map) getPrivateStatic(EntityTypes.class, "e")).put(${entityNetworkId}, Bastille${entityName}.class);
			((Map) getPrivateStatic(EntityTypes.class, "f")).put(Bastille${entityName}.class, ${entityNetworkId});
			((Map) getPrivateStatic(EntityTypes.class, "g")).put("${entityNetworkName}", ${entityNetworkId});
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
''')

entities = [
	{'entityName': "Blaze", 'entityNetworkName': "Blaze", 'entityNetworkId': 61},
	{'entityName': "CaveSpider", 'entityNetworkName': "CaveSpider", 'entityNetworkId': 59},
	{'entityName': "Creeper", 'entityNetworkName': "Creeper", 'entityNetworkId': 50},
	{'entityName': "Enderman", 'entityNetworkName': "Enderman", 'entityNetworkId': 58},
	{'entityName': "Endermite", 'entityNetworkName': "Endermite", 'entityNetworkId': 67},
	{'entityName': "Ghast", 'entityNetworkName': "Ghast", 'entityNetworkId': 56},
	{'entityName': "GiantZombie", 'entityNetworkName': "Giant", 'entityNetworkId': 53},
	{'entityName': "Guardian", 'entityNetworkName': "Guardian", 'entityNetworkId': 68},
	{'entityName': "IronGolem", 'entityNetworkName': "VillagerGolem", 'entityNetworkId': 99},
	{'entityName': "MagmaCube", 'entityNetworkName': "LavaSlime", 'entityNetworkId': 62},
	{'entityName': "Ocelot", 'entityNetworkName': "Ozelot", 'entityNetworkId': 98},
	{'entityName': "PigZombie", 'entityNetworkName': "PigZombie", 'entityNetworkId': 57},
	{'entityName': "Rabbit", 'entityNetworkName': "Rabbit", 'entityNetworkId': 101},
	{'entityName': "Silverfish", 'entityNetworkName': "Silverfish", 'entityNetworkId': 60},
	{'entityName': "Skeleton", 'entityNetworkName': "Skeleton", 'entityNetworkId': 51},
	{'entityName': "Slime", 'entityNetworkName': "Slime", 'entityNetworkId': 55},
	{'entityName': "Snowman", 'entityNetworkName': "SnowMan", 'entityNetworkId': 97},
	{'entityName': "Spider", 'entityNetworkName': "Spider", 'entityNetworkId': 52},
	{'entityName': "Witch", 'entityNetworkName': "Witch", 'entityNetworkId': 66},
	{'entityName': "Wither", 'entityNetworkName': "WitherBoss", 'entityNetworkId': 64},
	{'entityName': "Wolf", 'entityNetworkName': "Wolf", 'entityNetworkId': 95},
	{'entityName': "Zombie", 'entityNetworkName': "Zombie", 'entityNetworkId': 54},
]

if __name__ == "__main__":
	for entity in entities:
		fn = os.path.join('src\main\java\com\c45y\Bastille\Entities', 'Bastille{entityName}.java'.format(**entity))
		with open(fn, 'w+') as ouf:
			ouf.write(classtemplate.substitute(**entity))
		print('Bastille{entityName}.patch();'.format(**entity))