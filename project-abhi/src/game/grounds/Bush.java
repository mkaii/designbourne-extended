package game.grounds;

import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actors.enemies.Enemy;

/**
 * A class that represents a bush.
 * Created by:
 * @author Lok Mei Hui
 */
public class Bush extends Ground {
    /**
     * The enemy to be spawned at the bush
     */
    private Enemy enemyToSpawn;

    /**
     * The spawn rate of the enemy
     */
    private float spawnRate;

    /**
     * Constructor for Bush.
     *
     * @param enemyToSpawn the enemy to be spawned at the bush
     * @param spawnRate the spawn rate of the enemy
     */

    public Bush(Enemy enemyToSpawn, float spawnRate) {
        super('m');
        this.enemyToSpawn = enemyToSpawn;
        this.spawnRate = spawnRate;
    }

    /**
     * Spawns the enemy at the bush as per the spawn rate.
     *
     * @param location The location of the Bush
     */
    public void tick(Location location) {
        boolean spawn = Math.random() <= this.spawnRate;

        if (spawn && !location.containsAnActor()) {
            location.addActor(this.enemyToSpawn.getNewInstance());
        }
    }
}
