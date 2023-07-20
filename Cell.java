import java.awt.*;

public class Cell {
    //fields
    int value;
    boolean locked;
    int x;
    int y;

    public Cell(){ // constructor, sets value, x, and y to 0 and locked to false
        value = 0;
        locked = false;
        x = 0;
        y = 0;
    }

    public Cell(int value, int x, int y){ //constructor, sets all values to custom inputs
        this.value = value;
        locked = false;
        this.x = x;
        this.y = y;
    }

    public int getX(){ // getter for x
        return this.x;
    }

    public void setX(int newValue){ // setter for x
        this.x = newValue;
    }

    public int getY(){ // getter for y
        return this.y;
    }

    public void setY(int newValue){ //setter for y
        this.y = newValue;
    }

    public void setLocked(boolean locked){ //setter for locked
        this.locked = locked;
    }

    public int getValue(){ //getter for value
        return this.value;
    }

    public void setValue(int newValue){ //setter for value
        this.value = newValue;
    }

    public void draw(Graphics g, int x0, int y0, int scale){ //makes a popup window
        char[] list = new char[2];
        list[0] = (char)('0' + this.value);
        list[1] = '0';
        g.drawChars(list, 0, 1, (x0 + 1)* scale, (y0 + 1)* scale);
    }

    public String toString(){ // returns the value of the cell in a String
        return "" + value;
    }
}
