class Bishop extends Piece
{
    boolean attackingThisLocation (int indexRow, int indexColumn)
    {
      int columnDiff = pieceColumn - indexColumn;
      int rowDiff = pieceRow - indexRow;

      if ((columnDiff + rowDiff == 0) ||  (columnDiff == rowDiff))
           return true;
      else return false;
    }
}