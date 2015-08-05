BastilleEntities
-------

Minecraft server custom mobs

Each mob is represented by Bastille<mobname>. At plugin start the default minecraft mobs are replaced with our custom overwritten class, which allows us to expose a [build pattern](https://en.wikipedia.org/wiki/Builder_pattern) per entity in order to change values like speed, damage, health and pathfinding values.


## Example ##

	BastilleGiant rEntityBoss = new BastilleGiant(rWorld); // 'rWorld' of type 'net.minecraft.server.v1_8_R3.World'
    rEntityBoss = rEntityBoss
        .ignoreDamageSource(DamageSource.FIRE)     // Ignore all incoming FIRE damage
        .ignoreDamageSource(DamageSource.LAVA)
        .ignoreDamageSource(DamageSource.BURN) 
        .maxhealth(2000.0D)                        // Set the upper limit for the max health of our boss
        .health(2000.0F)                           // Give our boss stupid amounts of health
        .speed(0.20F)                              // Decrease the speed, we want a slow tank
        .damage(4D) 
        .emtpyGoals()                              // Clear the goalSelector so we can insert our own below
        .addGoal(0, new PathfinderGoalFloat(rEntityBoss))
        .addGoal(2, new PathfinderGoalMeleeAttack(rEntityBoss, EntityHuman.class, 1.0D, false))
        .addGoal(5, new PathfinderGoalMoveTowardsRestriction(rEntityBoss, 1.0D))
        .addGoal(8, new PathfinderGoalLookAtPlayer(rEntityBoss, EntityHuman.class, 8.0F))
        .addGoal(8, new PathfinderGoalRandomLookaround(rEntityBoss));