package game.grounds;

import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actors.enemies.Enemy;

/**
 * A class that represents an empty hunt.
 * Created by:
 * @author Lok Mei Hui
 */

public class EmptyHunts extends Ground {
    /**
     * The enemy to be spawned at the hunts
     */
    private Enemy enemyToSpawn;

    /**
     * The spawn rate of the enemy
     */
    private float spawnRate;

    /**
     * Constructor for Empty Hunts.
     *
     * @param enemyToSpawn the enemy to be spawned at the hunts
     * @param spawnRate the spawn rate of the enemy
     */

    public EmptyHunts(Enemy enemyToSpawn, float spawnRate) {
        super('h');
        this.enemyToSpawn = enemyToSpawn;
        this.spawnRate = spawnRate;
    }

    /**
     * Spawns the enemy at the hunts as per the spawn rate.
     *
     * @param location The location of the Empty Hunts
     */
    public void tick(Location location) {
        boolean spawn = Math.random() <= this.spawnRate;

        if (spawn && !location.containsAnActor()) {
            location.addActor(this.enemyToSpawn.getNewInstance());
        }
    }
}
