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
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
import org.bukkit.plugin.java.JavaPlugin;

public class BastilleEntities extends JavaPlugin {
    private static BastilleEntities _instance;

    public static BastilleEntities getInstance() {
        return _instance;
    }    
    
    @Override
    public void onEnable() {
        /* Create a static reference to ourself */
        _instance = this;

        BastilleBlaze.patch();
        BastilleCaveSpider.patch();
        BastilleCreeper.patch();
        BastilleEnderman.patch();
        BastilleEndermite.patch();
        BastilleGhast.patch();
        BastilleGiantZombie.patch();
        BastilleGuardian.patch();
        BastilleIronGolem.patch();
        BastilleMagmaCube.patch();
        BastilleOcelot.patch();
        BastillePigZombie.patch();
        BastilleRabbit.patch();
        BastilleSilverfish.patch();
        BastilleSkeleton.patch();
        BastilleSlime.patch();
        BastilleSnowman.patch();
        BastilleSpider.patch();
        BastilleWitch.patch();
        BastilleWither.patch();
        BastilleWolf.patch();
        BastilleZombie.patch();
    }

    protected net.minecraft.server.v1_8_R3.World getNMSWorld(org.bukkit.World w) {
        return ((CraftWorld)w).getHandle();
    }

}
