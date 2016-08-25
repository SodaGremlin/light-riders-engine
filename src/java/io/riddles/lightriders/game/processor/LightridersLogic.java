package io.riddles.lightriders.game.processor;

import io.riddles.lightriders.engine.LightridersEngine;
import io.riddles.lightriders.game.data.*;
import io.riddles.lightriders.game.player.LightridersPlayer;

import io.riddles.lightriders.game.move.LightridersMove;
import io.riddles.lightriders.game.state.LightridersState;
import io.riddles.javainterface.exception.InvalidInputException;

import java.util.ArrayList;

/**
 * Created by joost on 3-7-16.
 */
public class LightridersLogic {


    public LightridersLogic() {
    }

    public LightridersState transformBoard(LightridersState state, LightridersMove move, ArrayList<LightridersPlayer> players) throws InvalidInputException {

        transformPlayerLocation(state, move, players);
        transformAttack(state, move, players);

        return state;
    }

    private void transformPlayerLocation(LightridersState state, LightridersMove move, ArrayList<LightridersPlayer> players) {
        LightridersPlayer p = move.getPlayer();
        LightridersBoard board = state.getBoard();

        int pId = p.getId();
        Coordinate c = p.getCoordinate();
        Coordinate newC = c;

        switch(move.getMoveType()) {
            case UP:
                if (board.isEmpty(new Coordinate(c.getX(), c.getY()-1))) {
                    newC = new Coordinate(c.getX(), c.getY()-1);
                }
                break;
            case DOWN:
                if (board.isEmpty(new Coordinate(c.getX(), c.getY()+1))) {
                    newC = new Coordinate(c.getX(), c.getY()+1);
                }
                break;
            case RIGHT:
                if (board.isEmpty(new Coordinate(c.getX()+1, c.getY()))) {
                    newC = new Coordinate(c.getX()+1, c.getY());
                }
                break;
            case LEFT:
                if (board.isEmpty(new Coordinate(c.getX()-1, c.getY()))) {
                    newC = new Coordinate(c.getX()-1, c.getY());
                }
                break;
        }

        boolean otherPlayerPresent = false;
        for (LightridersPlayer player : players) {
            if (player.getId() != p.getId()) {
                if (player.getCoordinate().getX() == newC.getX() && player.getCoordinate().getY() == newC.getY()) {
                    otherPlayerPresent = true;
                }
            }
        }
        if (otherPlayerPresent) { /* another player at newC */
            if ( p.getWeapons() > 0 ) { /* Player has weapon */
                for (LightridersPlayer otherPlayer : players) {
                    if (otherPlayer.getId() != p.getId()) {
                        otherPlayer.paralyse(LightridersEngine.configuration.get("weapon_paralysis_duration"));
                        otherPlayer.updateSnippets(-LightridersEngine.configuration.get("weapon_snippet_loss"));
                        state.updateSnippetsEaten(LightridersEngine.configuration.get("weapon_snippet_loss"));

                        System.out.println("ATTACK: " + otherPlayer);
                    }
                }
            }
            newC = c; /* Stay in position */

        }

        for (Enemy enemy : state.getEnemies()) {
            if (enemy.getCoordinate().getX() == newC.getX() && enemy.getCoordinate().getY() == newC.getY()) {
                /* Collision with enemy */
                if ( p.getWeapons() > 0 ) { /* Player has weapon */
                    //System.out.println("PLAYER KILLS ENEMY");
                    p.updateWeapons(-1);
                    state.killEnemyAt(newC);
                } else { /* Player gets a hit */
                    //System.out.println("PLAYER HIT BY ENEMY");
                    int maxSnippets = LightridersEngine.configuration.get("enemy_snippet_loss");
                    if (p.getSnippets() < maxSnippets) maxSnippets = p.getSnippets();

                    p.updateSnippets(-maxSnippets);

                    /* Spawn x snippets on map */
                    for (int i = 0; i < maxSnippets; i++) {
                        //System.out.println("Spawning snippet");
                        board.addSnippet(board.getLoneliestField(players));
                        board.updateComplete(players, state);
                    }
                    newC = c; /* Stay in position */
                }
            }

        }

        switch (board.getFieldAt(newC)) {
            case "B": /* A bug */
                break;
            case "C": /* Code snippet */
                board.setFieldAt(newC, ".");
                p.updateSnippets(+1);
                state.updateSnippetsEaten(1);
                break;
            case "W": /* A Weapon */
                board.setFieldAt(newC, ".");
                p.updateWeapons(1);
                break;

        }
        p.setCoordinate(newC);
    }

    /* Deprecated */
    private void transformAttack(LightridersState state, LightridersMove move, ArrayList<LightridersPlayer> players) {
//        LightridersPlayer player = move.getPlayer();
//        LightridersBoard board = state.getBoard();
//
//        if (move.getMoveType() == MoveType.ATTACK) {
//            if (player.getWeapons()>0) {
//                player.updateWeapons(-1);
//                    /* Kill enemies nearby */
//                int prevSize = state.getEnemies().size();
//                Iterator<Enemy> it = state.getEnemies().iterator();
//                while (it.hasNext()) {
//                    Enemy enemy = it.next();
//                    if (Math.abs(enemy.getCoordinate().getX() - player.getCoordinate().getX()) + (Math.abs(enemy.getCoordinate().getY() - player.getCoordinate().getY())) < 2) {
//                        board.setFieldAt(enemy.getCoordinate(), "-");
//                        it.remove();
//                    }
//                }
//                if (state.getEnemies().size() != prevSize) {
//                        /* An enemy was killed */
//                }
//                    /* Paralyse players nearby and take N code snippets*/
//                for (LightridersPlayer otherPlayer : players) {
//                    if (otherPlayer.getId() != player.getId()) {
//                        if (Math.abs(otherPlayer.getCoordinate().getX() - player.getCoordinate().getX()) + (Math.abs(otherPlayer.getCoordinate().getY() - player.getCoordinate().getY())) < 2) {
//                            otherPlayer.paralyse(LightridersEngine.configuration.get("weapon_paralysis_duration"));
//                            otherPlayer.updateSnippets(-LightridersEngine.configuration.get("weapon_snippet_loss"));
//                            System.out.println("ATTACK: " + otherPlayer);
//                        }
//                    }
//                }
//            }
//        }
    }
}
