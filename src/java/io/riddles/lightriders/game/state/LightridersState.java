/*
 * Copyright 2016 riddles.io (developers@riddles.io)
 *
 *     Licensed under the Apache License, Version 2.0 (the "License");
 *     you may not use this file except in compliance with the License.
 *     You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 *     Unless required by applicable law or agreed to in writing, software
 *     distributed under the License is distributed on an "AS IS" BASIS,
 *     WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *     See the License for the specific language governing permissions and
 *     limitations under the License.
 *
 *     For the full copyright and license information, please view the LICENSE
 *     file that was distributed with this source code.
 */

package io.riddles.lightriders.game.state;

import io.riddles.lightriders.game.data.LightridersBoard;
import io.riddles.lightriders.game.data.Coordinate;
import io.riddles.lightriders.game.move.LightridersMove;
import io.riddles.lightriders.game.data.Enemy;
import io.riddles.lightriders.game.player.LightridersPlayer;
import io.riddles.javainterface.game.state.AbstractState;

import java.util.ArrayList;

/**
 * io.riddles.lightriders.game.state.LightridersState - Created on 2-6-16
 *
 * [description]
 *
 * @author joost
 */
public class LightridersState extends AbstractState<LightridersMove> {

    private LightridersBoard board;
    private ArrayList<Enemy> enemies;
    private String errorMessage;
    private String representationString;
    private int snippetsEaten;


    public LightridersState() {
        super();
        this.enemies = new ArrayList<Enemy>();
    }

    public LightridersState(LightridersState previousState, LightridersMove move, int roundNumber) {
        super(previousState, move, roundNumber);
        this.enemies = previousState.getEnemies();
        this.snippetsEaten = previousState.snippetsEaten;
    }

    public LightridersState(LightridersState previousState, ArrayList<LightridersMove> moves, int roundNumber) {
        super(previousState, moves, roundNumber);
        this.enemies = previousState.getEnemies();
        this.snippetsEaten = previousState.snippetsEaten;
    }

    public LightridersBoard getBoard() {
        return this.board;
    }
    public void setBoard(LightridersBoard b) {
        this.board = b;
    }

    public void addEnemy(Enemy e) {
        System.out.println("adding enemy " + e.getCoordinate());
        enemies.add(e);
    }

    public void killEnemyAt(Coordinate c) {
        for (Enemy e : this.enemies) {
            Coordinate enemyCoordinate = e.getCoordinate();
            if (c.getX() == enemyCoordinate.getX() && c.getY() == enemyCoordinate.getY()) {
                this.enemies.remove(e);
            }
        }
    }

    public ArrayList<Enemy> getEnemies() {
        return this.enemies;
    }

    public void setEnemies(ArrayList<Enemy> e) { this.enemies = e; }

    public void setRepresentationString(ArrayList<LightridersPlayer> players) {
        this.representationString = this.board.toRepresentationString(players, this);
    }

    public String getRepresentationString() {
        return this.representationString;
    }

    public void setSnippetsEaten(int nr) {
        this.snippetsEaten = nr;
    }

    public int getSnippetsEaten() {
        return this.snippetsEaten;
    }

    public void updateSnippetsEaten(int nr) {
        this.snippetsEaten += nr;
    }

}
