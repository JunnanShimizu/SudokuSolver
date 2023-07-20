import java.util.Random;

public class Sudoku {
    Board board;
    LandscapeDisplay display;;

    public Sudoku(){ //constructor, makes new board and Landscape display with size 30
        board = new Board();
        display = new LandscapeDisplay(board, 30);
    }

    public Sudoku(int numLockedValues){ //constructor, makes new board with random values
        board = new Board();
        Random random = new Random();
        display = new LandscapeDisplay(board, 30);

        for(int x = 0; x < numLockedValues; x++){
            int randomRow = random.nextInt(8) + 1;
            int randomCol = random.nextInt(8) + 1;
            int randomValue = random.nextInt(8) + 1;

            if(board.value(randomRow, randomCol) == 0 && board.validValue(randomRow, randomCol, randomValue)){
                board.set(randomRow, randomCol, randomValue, true);
            }else{
                numLockedValues++;
            }
        }
    }

    public boolean solve(int delay){ // solves board, artificial delay added to demonstrate depth first search
        CellStack stack = new CellStack();
        Cell bestCell;

        int z = 0;

        while(stack.size() < 81 - board.numLocked()){
            // System.out.println("Started loop " + z);
            z++;
            if( delay > 0 ) {
                try {
                    Thread.sleep(delay);
                }
                catch(InterruptedException ex) {
                    System.out.println("Interrupted");
                }
                display.repaint();
            }

            bestCell = this.findBestCell();

            if(bestCell != null){
                stack.push(bestCell);
                board.set(bestCell.getX(), bestCell.getY(), bestCell.getValue());
            }else{
                while(!(stack.empty()) && bestCell == null){
                    Cell temp = stack.pop();
                    int previousValue = temp.getValue();

                    for(int i = temp.getValue() + 1; i < 10; i++){
                        if(board.validValue(temp.getX(), temp.getY(), i)) {
                            bestCell = temp;
                            temp.setValue(i);
                            board.set(bestCell.getX(), bestCell.getY(), i);
                            stack.push(bestCell);
                            break;
                            }
                        }
                    if(bestCell == null){
                        board.set(temp.getX(), temp.getY(), 0);
                    }
                    if(stack.empty()){
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public Cell findBestCell(){ //returns the next cell that has a valid value
        for(int x = 0; x < Board.Size; x++){
            for(int y = 0; y < Board.Size; y++){
                if(board.value(x, y) == 0){
                    for(int i = 0; i < 10; i++){
                        if(board.validValue(x, y, i)){
//                            board.set(x, y, i); // used to test findBestCell method
                            return new Cell(i, x, y);
                        }
                    }
                    return null;
                }
            }
        }
        return null;
    }

    public static void main(String[] args){
        Sudoku newSudoku = new Sudoku(12); // Creates board with 30 initially locked values
        newSudoku.solve(1); // Solves
    }
}
