package io.riddles.tron;

import java.util.Optional;

import io.riddles.boardgame.model.Field;
import io.riddles.boardgame.model.Piece;

/**
 * ${PACKAGE_NAME}
 *
 * Copyright 2016 - present Riddles.io
 * For license information see the LICENSE file in the project root
 *
 * @author Niko
 */
public class TronField extends Field {

    protected Optional<Piece> maybePiece;

    public TronField(Optional<Piece> maybePiece) {
    	super(maybePiece);
        this.maybePiece = maybePiece;
    }

    public Optional<Piece> getPiece() {

        return maybePiece;
    }

    public void setPiece(Optional<Piece> maybePiece) {
        this.maybePiece = maybePiece;
    }
}