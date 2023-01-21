import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static Scanner input = new Scanner(System.in);
    public static void main(String[] args) {
        //create an ArrayList to hold the cupcake menu
        ArrayList<Cupcake> cupcakeMenu = new ArrayList<>();

        //create the cupcakes
        Cupcake cupcake = new Cupcake("standard cupcake");
        RedVelvetCupcake redVelvetCupcake = new RedVelvetCupcake("red velvet cupcake");
        ChocolateCupcake chocolateCupcake = new ChocolateCupcake("chocolate cupcake");

        double cupcakePrice = getValidPrice(cupcake);
        cupcake.setPrice(cupcakePrice);

        double redVelvetPrice = getValidPrice(redVelvetCupcake);
        redVelvetCupcake.setPrice(redVelvetPrice);

        double chocolatePrice = getValidPrice(chocolateCupcake);
        chocolateCupcake.setPrice(chocolatePrice);

        //add all the cupcakes to the menu
        cupcakeMenu.add(cupcake);
        cupcakeMenu.add(redVelvetCupcake);
        cupcakeMenu.add(chocolateCupcake);

        //create an ArrayList to hold the drink menu
        ArrayList<Drink> drinkMenu = new ArrayList<>();

        Drink water = new Drink("drink of water");
        Soda soda = new Soda("bottle of soda");
        Milk milk = new Milk("bottle of milk");

        //get the price for each type of drink from the user
        double waterPrice = getValidPrice(water);
        water.setPrice(waterPrice);

        double sodaPrice = getValidPrice(soda);
        soda.setPrice(sodaPrice);

        double milkPrice = getValidPrice(milk);
        milk.setPrice(milkPrice);

        drinkMenu.add(water);
        drinkMenu.add(soda);
        drinkMenu.add(milk);

        new Order(cupcakeMenu, drinkMenu);

        Main.input.close();
    }

    public static double getValidPrice(MenuItem item) {
        while(true){
            try {
                System.out.println("We are deciding on the price for our " + item.getItemName()
                        + ". Here is the description:");
                item.type();
                System.out.println("How much would you like to charge for the " + item.getItemName()
                        + "? (Input a numerical value taken to 2 decimal places):");
                String priceText = input.nextLine();
                if(!priceText.contains(".")){
                    throw new NumberFormatException("The price amount needs to be expressed as both dollars and cents.\n");
                }
                double price = Double.parseDouble(priceText);
                if(price < 0){
                    throw new NumberFormatException("Nice try, wise guy! Next time enter a POSITIVE dollar amount.\n");
                }
                return price;
            } catch(NumberFormatException e){
                System.out.println(e.getMessage() + "\n");
                continue;
            }
        }
    }
}

class MenuItem {
    private double price;
    private String itemName;

    public MenuItem(){

    }

    public MenuItem(String itemName){
        this.itemName = itemName;
    }

    public MenuItem(MenuItem menuItem){
        this(menuItem.itemName);
    }

    public double getPrice(){
        return this.price;
    }

    public void setPrice(double price){
        this.price = price;
    }

    public String getItemName(){
        return this.itemName;
    }

    public void setItemName(String itemName){
        this.itemName = itemName;
    }

    public void type(){
        System.out.println(this.itemName);
    }
}


class Cupcake extends MenuItem {
    public Cupcake(String itemName){
        super(itemName);
    }

    public Cupcake(Cupcake cupcake){
        super(cupcake);
        super.setPrice(cupcake.getPrice());

    }
    public void type(){
        System.out.println("A basic, generic cupcake, with vanilla frosting");
    }
}

class RedVelvetCupcake extends Cupcake {

    public RedVelvetCupcake(String itemName){
        super(itemName);
    }

    public RedVelvetCupcake(String itemName, double price){
        super(itemName);
        super.setPrice(price);
    }

    public RedVelvetCupcake(RedVelvetCupcake redVelvetCupcake){
        super(redVelvetCupcake);
    }
    public void type(){
        System.out.println("A red-velvet-based cupcake with cream cheese frosting.");
    }
}

class ChocolateCupcake extends Cupcake {

    public ChocolateCupcake(String itemName){
        super(itemName);
    }

    public ChocolateCupcake(ChocolateCupcake chocolateCupcake){
        super(chocolateCupcake);
    }
    public void type(){
        System.out.println("A chocolate-based cupcake with chocolate frosting.");
    }
}

class Drink extends MenuItem {
    public Drink(String itemName){
        super(itemName);
    }

    public Drink(Drink drink){
        super(drink);
        super.setPrice(drink.getPrice());
    }
    public void type(){
        System.out.println(super.getItemName());
    }
}

class Soda extends Drink {
    public Soda(String itemName){
        super(itemName);
    }

    public Soda(Soda soda){
        super(soda);
    }
}

class Milk extends Drink {
    public Milk(String shortDescription){
        super(shortDescription);
    }

    public Milk(Milk milk){
        super(milk);
    }
}