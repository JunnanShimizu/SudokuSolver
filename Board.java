import java.awt.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Board {
    private Cell[][] board;
    public static final int Size = 9;

    public Board(){ // contructor, sets board to 9 x 9 of cell objects
        board = new Cell[Board.Size][Board.Size];
        for(int x = 0; x < Board.Size; x++){
            for(int y = 0; y < Board.Size; y++){
                board[x][y] = new Cell(0, x, y);
            }
        }
    }

    public String toString(){ //prints a 9x9 grid of the sudoku puzzle in the console
        int space = 0;
        int space2 = 0;
        String result = "";
        for(int x = 0; x < Board.Size; x++){
            for(int y = 0; y < Board.Size; y++){
                if(space == 3){
                    result += "   ";
                    space = 0;
                }
                result += board[x][y].value;
                space++;
            }
            result += "\n";
            space2++;
            space = 0;
            if(space2 == 3){
                result += "\n";
                space2 = 0;
            }
        }
        return result;
    }

    public int getCols(){ // returns number of columns
        return board.length;
    }

    public int getRows(){ //returns number of rows
        return board[0].length;
    }

    public Cell get(int r, int c){ //returns the cell at location r, c
        return board[r][c];
    }

    public boolean isLocked(int r, int c){ //returns whether the cell at r,c is locked
        return board[r][c].locked;
    }

    public int numLocked(){ //returns the number of locked cells on the board
        int numLocked = 0;
        for(int x = 0; x < Board.Size; x++){
            for(int y = 0; y < Board.Size; y++){
                if(board[x][y].locked){
                    numLocked++;
                }
            }
        }
        return numLocked;
    }

    public int value(int r, int c){ // returns the value at Cell r,c
        return board[r][c].value;
    }

    public void set(int r, int c, int value){ // sets teh calue of the Cell at r, c
        board[r][c].value = value;
    }

    public void set(int r, int c, int value, boolean locked){ // sets the value and locked fields of the Cell at r, c
        board[r][c].value = value;
        board[r][c].locked = locked;
    }

    public boolean read(String filename){ //reads the text of a file
        int currentRow = 0;
        int currentCol = 0;
        try{
            FileReader fReader = new FileReader(filename);
            BufferedReader bReader = new BufferedReader(fReader);
            String line = bReader.readLine();
            while(line != null) {
                String[] results = line.split("[ ]+");
//                System.out.println(line);
//                System.out.println(results.length);
                for(String current : results){
                    this.set(currentRow, currentCol, Integer.valueOf(current));
                    currentCol++;
                }
                currentCol = 0;
                currentRow++;
                line = bReader.readLine();
            }

            bReader.close();
            return true;
        }
        catch(FileNotFoundException ex){
            System.out.println("Board.read():: unable to open file " + filename);
        }
        catch(IOException ex){
            System.out.println("Board.read():: error reading file " + filename);
        }

        return false;
    }

    public boolean validValue(int row, int col, int value){
        //tests values in column
        for(int thisCol = 0; thisCol < Board.Size; thisCol++){
            if(this.value(row, thisCol) == value)
                return false;
        }

        //tests value in row
        for(int thisRow = 0; thisRow < Board.Size; thisRow++){
            if(this.value(thisRow, col) == value)
                return false;
        }

        //tests top left section
        if(row >= 0 && row <= 2 && col >= 0 && col <= 2){
            for(int r = 0; r < 3; r++){
                for(int c = 0; c < 3; c++){
                    if(this.value(r, c) == value){
                        return false;
                    }
                }
            }
        }

        //tests top middle section
        if(row >= 0 && row <= 2 && col >= 3 && col <= 5){
            for(int r = 0; r < 3; r++){
                for(int c = 3; c < 6; c++){
                    if(this.value(r, c) == value){
                        return false;
                    }
                }
            }
        }

        //tests top right section
        if(row >= 0 && row <= 2 && col >= 6 && col <= 8){
            for(int r = 0; r < 3; r++){
                for(int c = 6; c < 9; c++){
                    if(this.value(r, c) == value){
                        return false;
                    }
                }
            }
        }

        //tests middle left section
        if(row >= 3 && row <= 5 && col >= 0 && col <= 2){
            for(int r = 3; r < 6; r++){
                for(int c = 0; c < 3; c++){
                    if(this.value(r, c) == value){
                        return false;
                    }
                }
            }
        }

        //tests middle middle section
        if(row >= 3 && row <= 5 && col >= 3 && col <= 5){
            for(int r = 3; r < 6; r++){
                for(int c = 3; c < 6; c++){
                    if(this.value(r, c) == value){
                        return false;
                    }
                }
            }
        }

        //tests middle right section
        if(row >= 3 && row <= 5 && col >= 6 && col <= 8){
            for(int r = 3; r < 5; r++){
                for(int c = 6; c < 9; c++){
                    if(this.value(r, c) == value){
                        return false;
                    }
                }
            }
        }

        //tests bottom right section
        if(row >= 6 && row <= 8 && col >= 0 && col <= 2){
            for(int r = 6; r < 9; r++){
                for(int c = 0; c < 3; c++){
                    if(this.value(r, c) == value){
                        return false;
                    }
                }
            }
        }

        //tests bottom middle section
        if(row >= 6 && row <= 8 && col >= 3 && col <= 5){
            for(int r = 6; r < 9; r++){
                for(int c = 3; c < 6; c++){
                    if(this.value(r, c) == value){
                        return false;
                    }
                }
            }
        }

        //tests bottom right section
        if(row >= 6 && row <= 8 && col >= 6 && col <= 8){
            for(int r = 6; r < 9; r++){
                for(int c = 6; c < 9; c++){
                    if(this.value(r, c) == value){
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public boolean validSolution(){
        for(int r = 0; r < Board.Size; r++){
            for(int c = 0; c < Board.Size; c++){
                if(!(this.validValue(r, c, this.value(r,c))) || this.value(r,c) == 0){
                    return false;
                }
            }
        }
        return true;
    }

    public void draw(Graphics g, int scale){
        for(int x = 0; x < Board.Size; x++){
            for(int y = 0; y < Board.Size; y++){
                this.get(x, y).draw(g, y, x, scale);
            }
        }
    }

    public static void main(String[] args){ //tests constructor and toString method and all other methods
        Board newBoard = new Board();
//        System.out.println(newBoard);
//
//        System.out.println("# of Rows: " + newBoard.getRows());
//        System.out.println("# of Columns: " + newBoard.getCols());
//
//        System.out.println("The cell at 5, 5 is: " + newBoard.get(5, 5));
//        System.out.println("Cell 5, 5 is locked: " + newBoard.isLocked(5, 5));
//        System.out.println("# of Locked Cells: " + newBoard.numLocked());
//
//        System.out.println("The value at 5, 5 is: " + newBoard.value(5, 5));
//
//        //Setting value at cell 5,5 to 1
//        newBoard.set(5, 5, 1);
//        System.out.println("The value at 5, 5 is: " + newBoard.value(5, 5));
//        newBoard.set(5, 5, 1, true);
//        System.out.println("The Cell at 5, 5 is locked: " + newBoard.isLocked(5, 5));
//        System.out.println("# of Locked Cells: " + newBoard.numLocked());

        newBoard.read("board10Initial.txt");
        System.out.println(newBoard.toString());

        System.out.println(newBoard.validValue(2, 4, 3));
        System.out.println(newBoard.validSolution());
    }
}
