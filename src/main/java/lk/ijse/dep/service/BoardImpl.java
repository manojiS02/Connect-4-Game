package lk.ijse.dep.service;

public class BoardImpl implements Board {
    private BoardUI boardUI;
    private Piece pieces[][] ;

    public BoardImpl(BoardUI boardUI) {
        pieces = new Piece[NUM_OF_ROWS][NUM_OF_COLS];
        this.boardUI=boardUI;

        for(int i = 0; i< pieces.length; i++){
            for(int j = 0; j< pieces[i].length; j++) {
                pieces[i][j] = Piece.EMPTY;
            }
        }

    }

    @Override
    public BoardUI getBoardUI() {
        return boardUI;
    }

    @Override
    public int findNextAvailableSpot(int col) {
        for(int i = 0; i< pieces.length; i++){
            if(pieces[i][col] == Piece.EMPTY){
                return i;
            }
        }
        return -1;
    }

    @Override
    public  boolean isLegalMove(int col) {
        if(findNextAvailableSpot(col)!=-1){
            return true;
        }
        return false;
    }

    @Override
    public boolean existLegalMoves() {
        for(int col=0; col<NUM_OF_COLS; col++){
            if(isLegalMove(col)){
                return true;
            }
        }
        return false;
    }

    @Override
    public void updateMove(int col,Piece move) {
        int row = findNextAvailableSpot(col);
        pieces[row][col]=move;
    }

    @Override
    public Winner findWinner() {

        for (int i = 0; i< pieces.length; i++){
            for (int j=0; j<3; j++){
                if(pieces[i][j]== pieces[i][j+1] && pieces[i][j+1]== pieces[i][j+2] && pieces[i][j+2]== pieces[i][j+3] && pieces[i][j]==Piece.GREEN){
                    Winner winner = new Winner(Piece.GREEN,i,j,i,j+3);
                    return winner;
                }
            }
        }

        for (int i = 0; i< pieces.length; i++){
            for (int j=0; j<3; j++){
                if(pieces[i][j]== pieces[i][j+1] && pieces[i][j+1]== pieces[i][j+2] && pieces[i][j+2]== pieces[i][j+3] && pieces[i][j]==Piece.BLUE){
                    Winner winner = new Winner(Piece.BLUE,i,j,i,j+3);
                    return winner;
                }
            }
        }

        for (int j=0; j<6; j++){
            for (int i=0; i<2; i++){
                if(pieces[i][j]== pieces[i+1][j] && pieces[i+1][j]== pieces[i+2][j] && pieces[i+2][j]== pieces[i+3][j] && pieces[i][j]== Piece.GREEN ){
                    Winner winner = new Winner(Piece.GREEN,i,j,i+3,j);
                    return winner;
                }
            }
        }

        for (int j=0; j<6; j++){
            for (int i=0; i<2; i++){
                if(pieces[i][j]== pieces[i+1][j] && pieces[i+1][j]== pieces[i+2][j] && pieces[i+2][j]== pieces[i+3][j] && pieces[i][j]== Piece.BLUE ){
                    Winner winner = new Winner(Piece.BLUE,i,j,i+3,j);
                    return winner;
                }
            }
        }


        Winner winner = new Winner(Piece.EMPTY);
        return winner;
    }

    public Piece[][] getPieces() {
        return pieces;
    }
}

