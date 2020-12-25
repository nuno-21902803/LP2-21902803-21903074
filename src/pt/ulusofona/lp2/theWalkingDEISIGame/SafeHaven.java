package pt.ulusofona.lp2.theWalkingDEISIGame;

public class SafeHaven {
    int x,y;

    public SafeHaven(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }


    @Override
    public String toString() {
        return "SafeHaven{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
