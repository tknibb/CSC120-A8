import java.util.*;

public class Alien implements Contract {

    /** Attributes for Alien class */
    private String name;
    private int height;
    private int energyLevel;
    private int capacity;
    private ArrayList<String> items;
    private int x;
    private int y;
    private String lastAction;

    /** Constructor for an alien that requires: name, height, holding capacity, and the coordinate for its location */
    public Alien(String name, int height, int capacity, int x, int y) {
        this.name = name;
        this.energyLevel = 100;
        this.height = height;
        this.capacity = capacity;
        this.items = new ArrayList<>();
        this.x = x;
        this.y = y;
        this.lastAction = "N/A";
    }

    /** Constructor for an alien that always starts at the center */
    public Alien(String name, int height, int capacity) {
        this.name = name;
        this.energyLevel = 100;
        this.height = height;
        this.capacity = capacity;
        this.items = new ArrayList<>();
        this.x = 0;
        this.y = 0;
        this.lastAction = "N/A";
    }

    /** Constructor for an alien that you only need to enter in a name */
    public Alien(String name) {
        this.name = name;
        this.energyLevel = 100;
        this.height = 25;
        this.capacity = 10;
        this.items = new ArrayList<>();
        this.x = 0;
        this.y = 0;
        this.lastAction = "N/A";
    }

    /** Accessor for name */
    public String getName() {
        return this.name;
    }

    /** Accessor for height */
    public int getHeight() {
        return this.height;
    }

    /** Accessor for energyLevel */
    public int getEnergyLevel() {
        return this.energyLevel;
    }

    /** Accessor for capacity */
    public int getCapacity() {
        return this.capacity;
    }

    /** Accessor for list */
    public ArrayList<String> getList() {
        return this.items;
    }

    /** Accessor for lastAction */
    public String getLastAction() {
        return this.lastAction;
    }

    /** this method grabs an item and appends it to a list and checks whether there is room for it in the list */
    public void grab(String item) {
        try {
            if (this.energyLevel < 10) {
                throw new RuntimeException("I don't have enough energy. Call the rest() method to refuel " + this.name + "'s energy level.");
            }
            else if (this.items.size() == this.capacity) {
                throw new RuntimeException("Can't grab item because there is no more space to hold it.");
            } else {
                this.items.add(item);
                System.out.println(item + " has been grabbed.");
                this.lastAction = "Grab";
                this.energyLevel -= 10;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /** this method drops an item and removes it from a list and checks whether the item is in the list to begin with */
    public String drop(String item) {
        try {
            if (this.energyLevel < 10) {
                throw new RuntimeException("I don't have enough energy. Call the rest() method to refuel " + this.name + "'s energy level.");
            }
            else if (!this.items.contains(item)) {
                throw new RuntimeException("Can't drop item because item was not grabbed.");
            } else {
                this.items.remove(item);
                System.out.println(item + " has been dropped.");
                this.lastAction = "Drop";
                this.energyLevel -= 10;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return item;
    }

    /** this method examines an item to see if you even want to grab the item */
    public void examine(String item){
        Random rand = new Random();
        int n = rand.nextInt(2);
        try {
            if (this.energyLevel < 10) {
                throw new RuntimeException("I don't have enough energy. Call the rest() method to refuel " + this.name + "'s energy level.");
            }
            else if (n==0) {
                System.out.println("I dont want " + item + ".");
            } else {
                System.out.println("I do like " + item + ", I want to examine it.");
                grab(item);
                this.lastAction = "Examine";
                this.energyLevel -= 10;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        
    }

    /** this method just prints a line that states the item was used */
    public void use(String item) {
        try {
            if (this.energyLevel < 10) {
                throw new RuntimeException("I don't have enough energy. Call the rest() method to refuel " + this.name + "'s energy level.");
            }
            else if (!this.items.contains(item)) {
                throw new RuntimeException("Can't use item because I do not have it.");
            } else {
                System.out.println(item + " has been used.");
                this.lastAction = "Use";
                this.energyLevel -= 10;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /** this method returns a boolean to check that the move() method is in the boundaries */
    public boolean walk(String direction) {
        try {
            if (direction == "North" && this.y >= 100) {
                return false;
            }
            else if (direction == "South" && this.y <= 0) {
                return false;
            }
            else if (direction == "East" && this.x >= 100) {
                return false;
            } 
            else if (direction == "West" && this.x <= 0) {
                return false;
            }
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return true;
        }
    }

    /** this method moves the alien one unit in the direction that is given */
    public void move(String direction) {
        try {
            if (this.energyLevel < 10) {
                throw new RuntimeException("I don't have enough energy. Call the rest() method to refuel " + this.name + "'s energy level.");
            }
            else if (walk(direction) == false) {
                throw new RuntimeException("I can't go there because its out of bounds.");
            } else if (direction == "North"){
                this.y += 1;
                System.out.println(this.name + " moved one step to the North.");
                this.energyLevel -= 10;
                this.lastAction = "Move";
            } else if (direction == "East") {
                this.x += 1;
                System.out.println(this.name + " moved one step to the East.");
                this.energyLevel -= 10;
                this.lastAction = "Move";
            } else if (direction == "South") {
                this.y -= 1;
                System.out.println(this.name + " moved one step to the South.");
                this.energyLevel -= 10;
                this.lastAction = "Move";
            } else if (direction == "West") {
                this.x -= 1;
                System.out.println(this.name + " moved one step to the West.");
                this.energyLevel -= 10;
                this.lastAction = "Move";
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }   

    /** this method returns a boolean to check that the flyTo() method is in the boundaries */
    public boolean fly(int x, int y){
        try {
            if (this.x == x && this.y == y) {
                throw new RuntimeException("Can't fly because I'm already here.");
            }
            else if (x < 0 | x > 100 | y < 0 | y > 100) {
                throw new RuntimeException("Can't fly because its out of bounds.");
            } 
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    } 


    /** this method makes the alien fly to a new coordinate */
    public void flyTo(int x, int y) {
        try {
            if (this.energyLevel < 10) {
                throw new RuntimeException("I don't have enough energy. Call the rest() method to refuel " + this.name + "'s energy level.");
            }
            else if (fly(x, y) == false) {
                fly(x, y);
            } else {
                this.x = x;
                this.y = y;
                System.out.println(this.name + " flew to " + x + ", " + y + " coordinate.");
                this.energyLevel -= 10;
                this.lastAction = "Fly to";
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    /** this method shrinks the alien by 10 units */
    public Number shrink(){
        try {
            if (this.energyLevel < 10) {
                throw new RuntimeException("I don't have enough energy. Call the rest() method to refuel " + this.name + "'s energy level.");
            }
            else if (this.height <= 10) {
                throw new RuntimeException("I am too tiny to shrink anymore.");
            }
            this.height -= 10;
            this.energyLevel -= 10;
            this.lastAction = "Shrink";
            System.out.println(this.name + "'s height is now " + this.height);
            return this.height;
        } catch (Exception e) {
            System.out.println(e);
            return this.height;
        }
    }

    /** this method grows the alien by 10 units */
    public Number grow(){
        try {
            if (this.energyLevel < 10) {
                throw new RuntimeException("I don't have enough energy. Call the rest() method to refuel " + this.name + "'s energy level.");
            }
            this.height += 10;
            this.energyLevel -= 10;
            this.lastAction = "Grow";
            System.out.println(this.name + "'s height is now " + this.height);
            return this.height;
        } catch (Exception e) {
            System.out.println(e);
            return this.height;
        }
    }

    /** this method makes the alien rest and it refuels the alien's energy level by 100 units */
    public void rest(){
        this.energyLevel += 100;
        System.out.println(this.name + " has refueled their energy level.");
    }

    /** this method undoes some of the above methods */
    public void undo(){
        try {
            if (this.lastAction.equals("N/A")) {
                throw new RuntimeException("Can't undo anything because no action has been completed yet.");
            }
            else if (lastAction.equals("Grow")){
                this.energyLevel += 10;
                shrink();
            } 
            else if (lastAction.equals("Shrink")){
                this.energyLevel += 10;
                grow();
            } 
            else if (lastAction.equals("Grab")) {
                this.energyLevel += 10;
                System.out.println("Call the drop() method with the name of the item you just grabbed.");
            }
            else if (lastAction.equals("Drop")) {
                this.energyLevel += 10;
                System.out.println("Call the grab() method with the name of the item you just dropped.");
            }
            else if (lastAction.equals("Examine")) {
                this.energyLevel += 10;
                System.out.println("Can't undo examine() method.");
            }
            else if (lastAction.equals("Use")) {
                this.energyLevel += 10;
                System.out.println("Can't undo use() method.");
            }
            else if (lastAction.equals("Move")) {
                this.energyLevel += 10;
                System.out.println("Can't undo move() method.");
            }
            else if (lastAction.equals("Fly to")) {
                this.energyLevel += 10;
                System.out.println("Can't undo flyto() method.");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /** main method for Alien class where you can create an alien and use the methods */
    public static void main(String[] args) {
        Alien alien = new Alien("Timothee");
        alien.grab("apple");
        alien.drop("apple");
        alien.drop("cat");
        alien.grab("apple");
        alien.examine("apple");
        alien.examine("bottle");
        alien.use("apple");
        alien.move("North");
        alien.move("East");
        alien.rest();
        alien.move("South");
        alien.flyTo(5, 10);
        alien.flyTo(20, 50);
        alien.shrink();
        alien.grow();
        alien.undo();
        alien.rest();
    }
}
