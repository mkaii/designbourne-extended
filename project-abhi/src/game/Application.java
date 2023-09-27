package game;

import java.util.Arrays;
import java.util.List;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.FancyGroundFactory;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.World;
import game.actors.Player;
import game.actors.enemies.ForestKeeper;
import game.actors.enemies.HollowSoldier;
import game.actors.enemies.RedWolf;
import game.actors.enemies.WanderingUndead;
import game.grounds.*;
import game.grounds.Void;
import game.items.consumables.Bloodberry;
import game.items.skillweapons.skilltypes.Focus;
import game.items.skillweapons.weapons.BroadSword;
import game.items.skillweapons.weapons.SkillWeapon;

/**
 * The main class to start the game.
 * Created by:
 * @author Adrian Kristanto
 * Modified by: Abhijit Upadhyay
 *
 */
public class Application {

    /**
     * The main method to start the game.
     *
     * @param args the arguments passed in to the main method
     */
    public static void main(String[] args) {

        World world = new World(new Display());

        // Getting the maps
        GameMap abandonedVillage = getAbandonedVillage();
        GameMap burialGround = getBurialGround();
        GameMap ancientWoods = getAncientWoods();

        // Adding maps to the world
        world.addGameMap(abandonedVillage);
        world.addGameMap(burialGround);
        world.addGameMap(ancientWoods);

        for (String line : FancyMessage.TITLE.split("\n")) {
            new Display().println(line);
            try {
                Thread.sleep(200);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }

        // Adding door to link the two maps, both doors are in the bottom right corner of the map
        abandonedVillage.at(abandonedVillage.getXRange().max(),
                        abandonedVillage.getYRange().max())
                .setGround(new Door(burialGround));

        burialGround.at(burialGround.getXRange().max(),
                        burialGround.getYRange().max())
                .setGround(new Door(abandonedVillage));


        // add a new door in burial ground that travels to ancient woods
        burialGround.at(burialGround.getXRange().max()-1,
                        burialGround.getYRange().max()-1)
                .setGround(new Door(ancientWoods));

        // add a new door in ancient wood to travel back to burial ground
        ancientWoods.at(ancientWoods.getXRange().max(),
                        ancientWoods.getYRange().max())
                .setGround(new Door(burialGround));


        Traveller traveller = new Traveller();
        world.addPlayer(traveller, ancientWoods.at(46, 10));

        // Adding player to the game
        Player player = new Player("The Abstracted One", '@', 150);
        world.addPlayer(player, abandonedVillage.at(29, 5));

        world.run();
    }

    /**
     * Returns a map of the Abandoned Village.
     *
     * @return a map of the Abandoned Village.
     */
    private static GameMap getAbandonedVillage() {
        FancyGroundFactory groundFactory = new FancyGroundFactory(new Dirt(),
                new Wall(), new Floor(), new Puddle(), new Void());

        List<String> map = Arrays.asList(
                "...............................................+++.........",
                "...#######...+++.................++........................",
                "...#__...........................................+++.......",
                "...#..___#..........++.....................................",
                "...###.###.......+........#######..........................",
                "..........................#_____#..........................",
                "........~~................#_____#..........++..............",
                ".........~~~..............###_###..........................",
                "...~~~~~~~~...........++...................................",
                "....~~~~~.................................###..##..........",
                "~~~~~~~..++...............................#___..#..........",
                "~~~~~~....................................#..___#..........",
                "~~~~~~~~~.................+++.............#######..........");

        GameMap gameMap = new GameMap(groundFactory, map);

        // Adding graveyards or spawners for enemies
        gameMap.at(40, 10).setGround(new Graveyard(new WanderingUndead(), 0.25f));
        gameMap.at(34, 3).setGround(new Graveyard(new WanderingUndead(), 0.25f));
        gameMap.at(18, 9).setGround(new Graveyard(new WanderingUndead(), 0.25f));


        SkillWeapon broadSword = new BroadSword();
        broadSword.setWeaponSkill(new Focus(broadSword, 10, 80));
        gameMap.at(27, 6).addItem(broadSword);

        return gameMap;
    }

    /**
     * Returns a map of the Burial Ground.
     *
     * @return a map of the Burial Ground.
     */
    private static GameMap getBurialGround() {
        FancyGroundFactory groundFactory = new FancyGroundFactory(new Dirt(),
                new Wall(), new Floor(), new Puddle(), new Void());

        List<String> map = Arrays.asList(
                "...........+++++++........~~~~~~++....~~",
                "...........++++++.........~~~~~~+.....~~",
                "............++++...........~~~~~......++",
                "............+.+.............~~~.......++",
                "..........++~~~.......................++",
                ".........+++~~~....#######...........+++",
                ".........++++~.....#_____#.........+++++",
                "..........+++......#_____#........++++++",
                "..........+++......###_###.......~~+++++",
                "..........~~.....................~~...++",
                "..........~~~..................++.......",
                "...........~~....~~~~~.........++.......",
                "......~~....++..~~~~~~~~~~~......~......",
                "....+~~~~..++++++++~~~~~~~~~....~~~.....",
                "....+~~~~..++++++++~~~..~~~~~..~~~~~....");

        GameMap gameMap = new GameMap(groundFactory, map);

        // Adding graveyards or spawners for enemies
        gameMap.at(25, 14).setGround(new Graveyard(new HollowSoldier(), 0.10f));
        gameMap.at(12, 3).setGround(new Graveyard(new HollowSoldier(), 0.10f));
        gameMap.at(18, 12).setGround(new Graveyard(new HollowSoldier(), 0.10f));

        return gameMap;
    }

    /**
     * Returns a map of the Ancient Woods.
     *
     * @return a map of the Ancient Woods.
     */
    private static GameMap getAncientWoods() {
        FancyGroundFactory groundFactory = new FancyGroundFactory(new Dirt(),
                new Wall(), new Floor(), new Puddle(), new Void());

        List<String> map = Arrays.asList(
                "....+++..............................+++++++++....~~~....~~~",
                "+...+++..............................++++++++.....~~~.....~~",
                "++...............#######..............++++.........~~.......",
                "++...............#_____#...........................~~~......",
                "+................#_____#............................~~......",
                ".................###_###............~...............~~.....~",
                "...............................~.+++~~..............~~....~~",
                ".....................~........~~+++++...............~~~...~~",
                "....................~~~.........++++............~~~~~~~...~~",
                "....................~~~~.~~~~..........~........~~~~~~.....~",
                "++++...............~~~~~~~~~~~........~~~.......~~~~~~......",
                "+++++..............~~~~~~~~~~~........~~~........~~~~~......"
        );

        GameMap gameMap = new GameMap(groundFactory, map);

//      Adding empty hunts and Bushes for spawning enemies
        gameMap.at(10, 10).setGround(new EmptyHunts(new ForestKeeper(), 0.15f));
        gameMap.at(10, 3).setGround(new Bush(new RedWolf(), 0.30f));

        // Add bloodberry to the ancient woods
        gameMap.at(10, 9).addItem(new Bloodberry());

        return gameMap;
    }

}
