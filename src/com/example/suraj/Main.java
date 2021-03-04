package com.example.suraj;

import java.util.Map;

public class Main {
    private static StockList stockList = new StockList();

    public static void main(String[] args) {

        StockItem temp = new StockItem("bread", 0.86, 100);
        stockList.addStock(temp);

        temp = new StockItem("Cake", 1.10, 7);
        stockList.addStock(temp);

        temp = new StockItem("Car", 12.50, 2);
        stockList.addStock(temp);

        temp = new StockItem("Chair", 62.0, 10);
        stockList.addStock(temp);

        temp = new StockItem("Cup", 0.50, 200);
        stockList.addStock(temp);
        temp = new StockItem("Cup",0.45, 7);


        temp = new StockItem("Door", 72.95, 4);
        stockList.addStock(temp);

        temp = new StockItem("Juice", 2.50, 36);
        stockList.addStock(temp);

        temp = new StockItem("Phone", 96.99, 35);
        stockList.addStock(temp);

        temp = new StockItem("Towel", 2.40, 80);
        stockList.addStock(temp);

        temp = new StockItem("Vase", 8.76, 40);
        stockList.addStock(temp);

        System.out.println(stockList);

        for (String s : stockList.Items().keySet()){
            System.out.println(s);
        }

        Basket surajBasket = new Basket("Suraj");
        sellItem(surajBasket, "Car", 1);
        System.out.println(surajBasket);

        sellItem(surajBasket, "Car", 1);
        System.out.println(surajBasket);

        if(sellItem(surajBasket, "Car", 1) != 1){
            System.out.println("There are no more cars in stock");
        }
        sellItem(surajBasket, "Spanner", 5);
//        System.out.println(surajBasket);

        sellItem(surajBasket, "Juice", 4);
        sellItem(surajBasket, "Cup", 12);
        sellItem(surajBasket, "Bread", 1);
//        System.out.println(surajBasket);

//        System.out.println(stockList);

        Basket basket = new Basket("Customer");
        sellItem(basket, "Cup", 100);
        sellItem(basket, "Juice", 5);
        sellItem(basket, "Cup", 1);
        System.out.println(basket);

        removeItem(surajBasket, "Car", 1);
        removeItem(surajBasket, "Cup", 9);
        removeItem(surajBasket, "Car", 1);
        System.out.println("Cars removed: " + removeItem(surajBasket, "Car", 1));
        System.out.println(surajBasket);

        removeItem(surajBasket, "Bread", 1);
        removeItem(surajBasket, "Cup", 3);
        removeItem(surajBasket, "Juice", 4);
        removeItem(surajBasket, "Cup", 3);
        System.out.println(surajBasket);

        System.out.println("\nDisplay stock list before and after checkout");
        System.out.println(basket);
        System.out.println(stockList);
        checkOut(basket);
        System.out.println(basket);
        System.out.println(stockList);


        StockItem car = stockList.Items().get("Car");
        if ( car != null){
            car.adjustStock(2000);
        }

        if (car != null){
            stockList.get("Car").adjustStock(-1000);

        }

        System.out.println(stockList);

//        for (Map.Entry<String, Double> price: stockList.PriceList().entrySet()){
//            System.out.println(price.getKey() + " costs " + price.getValue());
//        }

        checkOut(surajBasket);
        System.out.println(surajBasket);



    }

    public static int sellItem(Basket basket, String item, int quantity){
        StockItem stockItem = stockList.get(item);
        if(stockItem==null){
            System.out.println("We don't sell " + item);
            return 0;
        }
        if(stockList.reserveStock(item, quantity) != 0){
            return basket.addToBasket(stockItem, quantity);
        }
        return 0;
    }
    public static int removeItem(Basket basket, String item, int quantity){
        StockItem stockItem = stockList.get(item);
        if(stockItem==null){
            System.out.println("We don't sell " + item);
            return 0;
        }
        if(basket.removeFromBasket(stockItem, quantity) == quantity){
            return stockList.unreserveStock(item, quantity);
        }
        return 0;
    }

    public static  void checkOut(Basket basket) {
        for (Map.Entry<StockItem, Integer> item : basket.Items().entrySet()){
            stockList.sellStock(item.getKey().getName(), item.getValue());

        }basket.clearBasket();
    }

}
