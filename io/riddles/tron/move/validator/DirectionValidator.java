package io.riddles.tron.move.validator;

import io.riddles.boardgame.model.Board;
import io.riddles.boardgame.model.Move;
import io.riddles.game.move.MoveValidator;
import io.riddles.tron.moves.TronDirectionMove;
import io.riddles.tron.moves.TronPassMove;

public class DirectionValidator implements MoveValidator {

	@Override
	public Boolean isApplicable(Move move, Board board) {
		return (move instanceof TronDirectionMove);
	}

	@Override
	public Boolean isValid(Move move, Board board) {
		return true;
	}

}
