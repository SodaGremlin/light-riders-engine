package io.riddles.lightriders.game.data;

/**
 * Created by joost on 29-6-16.
 */
public class Board {
    protected String[][] fields;
    protected int width = 20;
    protected int height = 16;


    public Board(int w, int h) {
        this.width = w;
        this.height = h;
        this.fields = new String[w][h];
        clear();

    }

    public String toString() {
        String s = "";
        int counter = 0;
        for (int y = 0; y < this.height; y++) {
            for (int x = 0; x < this.width; x++) {
                if (counter > 0) s += ",";
                s += fields[x][y];
                counter ++;
            }
        }
        return s;
    }

    public void initialiseFromString(String input, int w, int h) {
        String[] s = input.split(",");
        this.width = w;
        this.height = h;
        this.fields = new String[w][h];
        int x = 0, y = 0;
        for (int i = 0; i < s.length; i++) {
            this.fields[x][y] = s[i];
            if (++x == w) {
                x = 0; y++;
            }
        }
    }

    public void clear() {
        for (int y = 0; y < this.height; y++)
            for (int x = 0; x < this.width; x++)
                fields[x][y] = ".";
    }


    public int getWidth() { return this.width; }
    public int getHeight() { return this.height; }


    public String getFieldAt(Coordinate c) {
        return fields[c.getX()][c.getY()];
    }

    public void setFieldAt(Coordinate c, String s) {
        fields[c.getX()][c.getY()] = s;
    }

    /* isEmpty doesn't check for players or enemies! */
    public Boolean isEmpty(Coordinate c) {
        if (c.getX() < 0 || c.getY() < 0 || c.getX() >= this.width || c.getY() >= this.height) {
            return false;
        }
        return (fields[c.getX()][c.getY()].equals("."));
    }

    public void dump() {
        for (int y = 0; y < this.height; y++) {
            for (int x = 0; x < this.width; x++) {
                System.out.print(fields[x][y]);
            }
            System.out.println();
        }
    }


    public int getNrAvailableFields() {
        int availableFields = 0;
        for (int y = 0; y < this.height; y++) {
            for (int x = 0; x < this.width; x++) {
                if(fields[x][y].equals(".")) {
                    availableFields++;
                }
            }
        }
        return availableFields;
    }
}