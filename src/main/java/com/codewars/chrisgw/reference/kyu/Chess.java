package com.codewars.chrisgw.reference.kyu;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.Stream.Builder;


/**
 * <h2>Forking Knights </h2>
 * <a href="https://www.codewars.com/kata/forking-knights">https://www.codewars.com/kata/forking-knights</a>
 */
public class Chess {

    private ChessField[][] board;
    private List<ChessPiece> chessPieces;

    public Chess(char[][] board) {
        int initialPieces = 2 * board.length * 2; // 2 rows of pieces for each player
        this.chessPieces = new ArrayList<>(initialPieces);

        this.board = new ChessField[board.length][];
        for (int row = 0; row < board.length; row++) {
            this.board[row] = new ChessField[board[row].length];

            for (int column = 0; column < board[row].length; column++) {
                ChessField chessField = new ChessField(column, row);
                this.board[row][column] = chessField;

                char figureCode = board[row][column];
                if (figureCode != ' ') {
                    ChessPiece chessPiece = new ChessPiece(figureCode);
                    chessField.setChessPiece(chessPiece);
                    chessPieces.add(chessPiece);
                }
            }
        }
    }

    public ChessField fieldAt(int row, int column) {
        return board[row][column];
    }

    public Stream<ChessPiece> chessPieces() {
        return chessPieces.stream();
    }


    public Set<String> knightFork(String turn) {
        boolean blackPlayerTurn = "black".equalsIgnoreCase(turn);
        return getMovableKnights(blackPlayerTurn) //
                .flatMap(ChessPiece::allPossibleKnightMoves) //
                .filter(ChessMove::isForkMove).map(ChessMove::toString) //
                .collect(Collectors.toSet());
    }

    private Stream<ChessPiece> getMovableKnights(boolean blackPlayerTurn) {
        Predicate<ChessPiece> isMyPlayerPiece = ChessPiece::isWhitePiece;
        if (blackPlayerTurn) {
            isMyPlayerPiece = isMyPlayerPiece.negate();
        }
        Predicate<ChessPiece> isMovableKnight = isMyPlayerPiece.and(ChessPiece::isKnight);
        return chessPieces().filter(isMovableKnight);
    }


    public int boardSize() {
        return board.length;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int row = 0; row < boardSize(); row++) {
            if (row == 0) {
                appendColumnLetterRow(sb);
            }

            for (int column = 0; column < boardSize(); column++) {
                if (column == 0) {
                    sb.append(String.format("%2d|", boardSize() - row));
                }
                ChessField chessField = fieldAt(row, column);
                if (chessField.isEmpty()) {
                    sb.append(' ').append('|');
                } else {
                    ChessPiece chessPiece = chessField.getChessPiece();
                    sb.append(chessPiece.getPieceCode()).append('|');
                }
            }
            sb.append(String.format("%2d", boardSize() - row));
            sb.append("\n");
        }
        appendColumnLetterRow(sb);
        return sb.toString();
    }

    private void appendColumnLetterRow(StringBuilder sb) {
        sb.append("  |");
        for (int i = 0; i < boardSize(); i++) {
            char columnLetter = (char) ('a' + i);
            sb.append(columnLetter).append('|');
        }
        sb.append("\n");
    }


    @Data
    public class ChessField {

        private final int column;
        private final int row;
        private ChessPiece chessPiece;


        public boolean isInsideBoard() {
            return 0 <= column && column < boardSize();
        }

        public boolean isEmpty() {
            return chessPiece == null;
        }


        public char getColumnLetter() {
            return (char) ('a' + column);
        }

        @Override
        public String toString() {
            return getColumnLetter() + String.valueOf(row);
        }

    }


    @Data
    public class ChessPiece {

        private final char pieceCode;
        private ChessField field;


        public Stream<ChessMove> allPossibleKnightMoves() {
            // Row: +2, Column +1
            // Row: +2, Column -1
            // Row: -2, Column +1
            // Row: -2, Column -1

            // Row: +1, Column +2
            // Row: -1, Column +2
            // Row: +1, Column -2
            // Row: -1, Column -2

            Builder<ChessMove> allPossibleKnightMoves = Stream.builder();
            for (int dRow = -2; dRow <= 2; dRow++) {
                for (int dColumn = -2; dColumn <= 2; dColumn++) {
                    if (Math.abs(dRow) + Math.abs(dColumn) != 3) {
                        continue;
                    }
                    int row = field.row + dRow;
                    int column = field.column + dColumn;
                    allPossibleKnightMoves.add(new ChessMove(field, fieldAt(row, column)));
                }
            }
            return allPossibleKnightMoves.build();
        }


        public boolean isKnight() {
            return Character.toLowerCase(pieceCode) == 'k';
        }

        public boolean isWhitePiece() {
            return Character.isUpperCase(pieceCode);
        }

        public String toString() {
            return String.valueOf(pieceCode);
        }

    }


    @Data
    public static class ChessMove {

        private final ChessField fromField;
        private final ChessField toField;


        public boolean isAllowedMove() {
            return toField.isInsideBoard();
        }

        public boolean isForkMove() {
            return false;
        }

        public boolean takesOpponentPiece() {
            return fromField.getChessPiece().isWhitePiece() ^ toField.getChessPiece().isWhitePiece();
        }

        @Override
        public String toString() {
            char movedPieceCode = fromField.getChessPiece().getPieceCode();
            String sourcePosition = fromField.toString();
            String separator = takesOpponentPiece() ? "x" : "-";
            String targetPosition = toField.toString();
            return movedPieceCode + sourcePosition + separator + targetPosition;
        }

    }


}
