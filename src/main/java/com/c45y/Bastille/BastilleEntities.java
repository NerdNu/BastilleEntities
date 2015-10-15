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
import net.minecraft.server.v1_8_R3.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
import org.bukkit.entity.Player;
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

        BastilleBat.patch();
        BastilleBlaze.patch();
        BastilleCaveSpider.patch();
        BastilleChicken.patch();
        BastilleCow.patch();
        BastilleCreeper.patch();
        BastilleEnderDragon.patch();
        BastilleEnderman.patch();
        BastilleEndermite.patch();
        BastilleGhast.patch();
        BastilleGiant.patch();
        BastilleGuardian.patch();
        BastilleHorse.patch();
        BastilleIronGolem.patch();
        BastilleMagmaCube.patch();
        BastilleMushroomCow.patch();
        BastilleOcelot.patch();
        BastillePig.patch();
        BastillePigZombie.patch();
        BastilleRabbit.patch();
        BastilleSheep.patch();
        BastilleSilverfish.patch();
        BastilleSkeleton.patch();
        BastilleSlime.patch();
        BastilleSnowman.patch();
        BastilleSpider.patch();
        BastilleSquid.patch();
        BastilleVillager.patch();
        BastilleWitch.patch();
        BastilleWither.patch();
        BastilleWolf.patch();
        BastilleZombie.patch();
    }

    protected net.minecraft.server.v1_8_R3.World getNMSWorld(org.bukkit.World w) {
        return ((CraftWorld)w).getHandle();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("bastille-test")) {
            Player player = (Player) sender;
            BastilleGiant z = new BastilleGiant(player.getWorld());
            z = z
                    .maxhealth(200.0D)
                    .health(200.0F)
                    .speed(0.30F)
                    .damage(4D)
                    .emtpyGoals()
                    .addGoal(0, new PathfinderGoalFloat(z))
                    .addGoal(2, new PathfinderGoalMeleeAttack(z, EntityHuman.class, 1.0D, false))
                    .addGoal(5, new PathfinderGoalMoveTowardsRestriction(z, 1.0D))
                    .addGoal(8, new PathfinderGoalLookAtPlayer(z, EntityHuman.class, 8.0F))
                    .addGoal(8, new PathfinderGoalRandomLookaround(z));
            z.spawn(player.getLocation());
        }
        return true;
    }

}
