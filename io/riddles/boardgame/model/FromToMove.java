package io.riddles.boardgame.model;

/**
 * @author Niko van Meurs <niko@riddles.io>
 */
public class FromToMove extends Move {

    /**
     * Coordinate of the Field from which should be moved
     */
    private Coordinate from;

    /**
     * Coordinate of the Field to which a Piece should be moved
     */
    private Coordinate to;

    /**
     *
     * @param Coordinate from
     * @param Coordinate to
     */
    public FromToMove(Coordinate from, Coordinate to) {

        this.from = from;
        this.to = to;
    }

    /**
     * Returns the coordinate for the move's source Field
     * @return Coordinate
     */
    public Coordinate getFrom() {
        return from;
    }

    /**
     * Returns the coordinate for the move's target Field
     * @return Coordinate
     */
    public Coordinate getTo() {
        return to;
    }
}