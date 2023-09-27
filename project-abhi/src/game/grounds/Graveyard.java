package game.grounds;

import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actors.enemies.Enemy;

/**
 * A class that represents a graveyard.
 * Created by:
 * @author Abhijit Upadhyay
 */
public class Graveyard extends Ground {

    /**
     * The enemy to be spawned at the graveyard
     */
    private Enemy enemyToSpawn;

    /**
     * The spawn rate of the enemy
     */
    private float spawnRate;

    /**
     * Constructor for Graveyard.
     *
     * @param enemyToSpawn the enemy to be spawned at the graveyard
     * @param spawnRate the spawn rate of the enemy
     */
    public Graveyard(Enemy enemyToSpawn, float spawnRate) {
        super('n');
        this.enemyToSpawn = enemyToSpawn;
        this.spawnRate = spawnRate;
    }

    /**
     * Spawns the enemy at the graveyard as per the spawn rate.
     *
     * @param location The location of the Graveyard
     */
    public void tick(Location location) {
        boolean spawn = Math.random() <= this.spawnRate;

        if (spawn && !location.containsAnActor()) {
            location.addActor(this.enemyToSpawn.getNewInstance());
        }
    }

}
