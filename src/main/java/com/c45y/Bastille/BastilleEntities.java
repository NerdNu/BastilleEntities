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

import com.c45y.Bastille.Entities.*;
import java.util.ArrayList;
import org.bukkit.craftbukkit.v1_9_R1.CraftWorld;
import org.bukkit.entity.EntityType;
import org.bukkit.plugin.java.JavaPlugin;

public class BastilleEntities extends JavaPlugin {
    private static BastilleEntities _instance;
    private ArrayList<EntityType> patchedMobs = new ArrayList<EntityType>();

    public static BastilleEntities getInstance() {
        return _instance;
    }

    @Override
    public void onEnable() {
        /* Create a static reference to ourself */
        _instance = this;

        BastilleBlaze.patch();
        patchedMobs.add(EntityType.BLAZE);
        BastilleCaveSpider.patch();
        patchedMobs.add(EntityType.CAVE_SPIDER);
        BastilleCreeper.patch();
        patchedMobs.add(EntityType.CREEPER);
        BastilleEnderman.patch();
        patchedMobs.add(EntityType.ENDERMAN);
        BastilleEndermite.patch();
        patchedMobs.add(EntityType.ENDERMITE);
        BastilleGhast.patch();
        patchedMobs.add(EntityType.GHAST);
        BastilleGiantZombie.patch();
        patchedMobs.add(EntityType.GIANT);
        BastilleGuardian.patch();
        patchedMobs.add(EntityType.GUARDIAN);
        BastilleIronGolem.patch();
        patchedMobs.add(EntityType.IRON_GOLEM);
        BastilleMagmaCube.patch();
        patchedMobs.add(EntityType.MAGMA_CUBE);
        BastilleOcelot.patch();
        patchedMobs.add(EntityType.OCELOT);
        BastillePigZombie.patch();
        patchedMobs.add(EntityType.PIG_ZOMBIE);
        BastilleRabbit.patch();
        patchedMobs.add(EntityType.RABBIT);
        BastilleSilverfish.patch();
        patchedMobs.add(EntityType.SILVERFISH);
        BastilleSkeleton.patch();
        patchedMobs.add(EntityType.SKELETON);
        BastilleSlime.patch();
        patchedMobs.add(EntityType.SLIME);
        BastilleSnowman.patch();
        patchedMobs.add(EntityType.SNOWMAN);
        BastilleSpider.patch();
        patchedMobs.add(EntityType.SPIDER);
        BastilleWitch.patch();
        patchedMobs.add(EntityType.WITCH);
        BastilleWither.patch();
        patchedMobs.add(EntityType.WITHER);
        BastilleWolf.patch();
        patchedMobs.add(EntityType.WOLF);
        BastilleZombie.patch();
        patchedMobs.add(EntityType.ZOMBIE);
    }

    protected net.minecraft.server.v1_9_R1.World getNMSWorld(org.bukkit.World w) {
        return ((CraftWorld)w).getHandle();
    }

    public static boolean hasBeenPatched(EntityType entity) {
        return _instance.patchedMobs.contains(entity);
    }

}
