/**
 * This class represents a King chess piece and defines its moving capablity across
 * the chessboard
 * 
 * @author Eugenia Tate
 * @version Last Modified 03/04/2025
 */

class King extends Piece
{
     /**
     * This method determines if King piece is attacking a certain locatiom
     * on a chessboard based on a certain King's rule of movement allowed and 
     * given desired coordinates on the board
     * 
     * @return   boolean true or false based on whether the attack location is valid 
     */
    boolean attackingThisLocation (int indexRow, int indexColumn)
    {
        int columnDiff = pieceColumn - indexColumn;
        int rowDiff = pieceRow - indexRow;
        
        //using Math.abs to accommodate negative row or columb differences
        if ((columnDiff == 0 && rowDiff == 0) || (Math.abs(rowDiff) == 1 && Math.abs(columnDiff) == 1) ||
                (columnDiff == 0 && Math.abs(rowDiff) ==1 ) || (rowDiff == 0 && Math.abs(columnDiff) == 1)) {
            return true;
        }
        else {
            return false;
        }

    }
}