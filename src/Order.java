import java.util.ArrayList;

public class Order {

    ArrayList<Cupcake> cupcakeMenu = new ArrayList<>();
    ArrayList<Drink> drinkMenu = new ArrayList<>();
    int menuItemCounter = 0;

    public Order(ArrayList<Cupcake> cupcakeMenu, ArrayList<Drink> drinkMenu){
        //make copies of the menu parameters
        for(Cupcake cupcake : cupcakeMenu) {
            this.cupcakeMenu.add(new Cupcake(cupcake));
        }

        for(Drink drink : drinkMenu){
            this.drinkMenu.add(new Drink(drink));
        }

        //prompt the user to place an order
        System.out.println("Would you like to place an order? (Y or N)");
        String placeOrder = Main.input.nextLine();

        ArrayList<MenuItem> order = new ArrayList<>();
        if(placeOrder.equalsIgnoreCase("Y") || placeOrder.equalsIgnoreCase("YES")){
            System.out.println("CUPCAKES:");
            this.displayMenu(cupcakeMenu);

            System.out.println("\nDRINKS:");
            this.displayMenu(drinkMenu);

            boolean ordering = true;
            while(ordering){
                System.out.println("What would you like to order? Please use the number associated with each item to order.");
                int orderChoice = Main.input.nextInt();
                Main.input.nextLine();

                switch(orderChoice){
                    case 0:
                        order.add(new Cupcake(this.cupcakeMenu.get(orderChoice)));
                        break;
                    case 1:
                        String redVelvetItemName = this.cupcakeMenu.get(orderChoice).getItemName();
                        double redVelvetPrice = this.cupcakeMenu.get(orderChoice).getPrice();
                        RedVelvetCupcake redVelvetCupcake = new RedVelvetCupcake(redVelvetItemName);
                        redVelvetCupcake.setPrice(redVelvetPrice);
                        order.add(redVelvetCupcake);
                        break;
                    case 2:
                        String chocolateItemName = this.cupcakeMenu.get(orderChoice).getItemName();
                        double chocolatePrice = this.cupcakeMenu.get(orderChoice).getPrice();
                        ChocolateCupcake chocolateCupcake = new ChocolateCupcake(chocolateItemName);
                        chocolateCupcake.setPrice(chocolatePrice);
                        order.add(chocolateCupcake);
                        break;
                    case 3:
                        order.add(new Drink(this.drinkMenu.get(0)));
                        break;
                    case 4:
                        String sodaItemName = this.drinkMenu.get(1).getItemName();
                        double sodaPrice = this.drinkMenu.get(1).getPrice();
                        Soda soda = new Soda(sodaItemName);
                        soda.setPrice(sodaPrice);
                        order.add(soda);
                        break;
                    case 5:
                        String milkItemName = this.drinkMenu.get(2).getItemName();
                        double milkPrice = this.drinkMenu.get(2).getPrice();
                        Milk milk = new Milk(milkItemName);
                        milk.setPrice(milkPrice);
                        order.add(milk);
                        break;
                    default:
                        System.out.println("Sorry, we don't seem to have that on the menu.");
                }
                System.out.println("Would you like to continue ordering? (Y or N)");
                placeOrder = Main.input.nextLine();
                if(!placeOrder.equalsIgnoreCase("Y") && !placeOrder.equalsIgnoreCase("YES")){
                    //display the receipt
                    System.out.println("ORDER RECEIPT:");
                    this.displayReceipt(order);
                    ordering = false;

                    //create the sales data file and write each ordered item to it
                    new CreateFile();
                    new WriteToFile(order);
                }
            }

        } else {
            System.out.println("Have a nice day then.");
            System.exit(0);
        }
    }

    private String setLineItemSpacing(String itemDescription){
        String spacing = "\t\t";
        if(itemDescription.length() < 16){
            spacing = "\t\t\t";
        }
        return spacing;
    }

    private void displayMenu(ArrayList<? extends MenuItem> menu){
        for(MenuItem item : menu){
            System.out.println(this.menuItemCounter++ + "\t" + item.getItemName() + setLineItemSpacing(item.getItemName()) + item.getPrice());
        }
    }

    private void displayReceipt(ArrayList<? extends MenuItem> order){
        System.out.println("ITEM DESCRIPTION\t\tUNIT PRICE");
        for(MenuItem item : order){
            System.out.println(item.getItemName() + setLineItemSpacing(item.getItemName()) + item.getPrice());
        }
        System.out.println("Subtotal:\t\t\t" + this.calculateOrderTotal(order));
    }

    private double calculateOrderTotal(ArrayList<? extends MenuItem> order){
        double total = 0;
        for(MenuItem item : order){
            total += item.getPrice();
        }
        return total;
    }
}
