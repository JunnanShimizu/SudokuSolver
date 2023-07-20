public class CellStack {
    Cell[] cells;
    int next;

    public CellStack(){ // constructor, makes a stack of size 10
        this.cells = new Cell[10];
        this.next = 0;
    }

    public CellStack(int max){ // constructor, makes a stack of size input max
        this.cells = new Cell[max];
        this.next = 0;
    }

    public void push(Cell c){ //adds value to top of stack, doubles the size of the array if not big enough
        if(this.next < this.cells.length){
            this.cells[next] = c;
            this.next++;
        }
        else{
            Cell[] newArray = new Cell[this.cells.length * 2];
            for(int i = 0; i < cells.length; i++){
                newArray[i] = cells[i];
            }
            this.cells = newArray;
            newArray[next] = c;
            next++;
        }
    }

    public Cell pop(){ //removes top value
        if(this.next == 0)
            return null;
        else {
            Cell temp = cells[this.next - 1];
            this.next--;
            return temp;
        }
    }

    public int size(){//returns how many values were added
        return next;
    }

    public boolean empty(){ //returns whether or not the stack is empty
        if(this.next == 0)
            return true;
        else
            return false;
    }

    public String toString(){ //returns a string of the stack
        String result = "";

        for(int i = 0; i < next; i++){
            if(cells[i] != null){
                result += cells[i].toString() + "\n";

            }
        }

        return result;
    }

    public static void main(String[] args){ //used to test CellStack
        CellStack cellStack1 = new CellStack(3);
        cellStack1.push(new Cell());
        cellStack1.push(new Cell());
        cellStack1.push(new Cell());
        cellStack1.push(new Cell());
        cellStack1.push(new Cell());

        System.out.println(cellStack1.toString());

        cellStack1.pop();
        cellStack1.pop();

        System.out.println(cellStack1.toString());
    }
}
