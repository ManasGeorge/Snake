public class Unit {
    public static final double SIZE = 50;
    protected double x;
    protected double y;
    protected Unit next;

    public Unit(double x, double y, Unit next){
        this.x = x;
        this.y = y;
        this.next = next;
    }

    public Unit(Unit unit){
        this.x = unit.x;
        this.y = unit.y;
        this.next = unit.next;
    }

    public void move(){
        x = next.x;
        y = next.y;
    }
    public void move(Direction direction){
        double delx, dely;
        switch(direction){
            case LEFT:  delx = -SIZE; dely = 0; break;
            case RIGHT: delx = SIZE; dely = 0; break;
            case UP:    delx = 0;        dely = -SIZE; break;
            case DOWN:  delx = 0;        dely = SIZE; break;
            default: delx = 0; dely = 0;
        }
        x += delx;
        y += dely;
    }

    public Unit getNext() {
        return next;
    }

    public void setNext(Unit next){
        this.next = next;
    }

    public double getX(){
        return x;
    }

    public double getY(){
        return y;
    }

    public boolean equals(Unit other){
        return x == other.x && y == other.y;
    }
}
