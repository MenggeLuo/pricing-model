/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.OrderManagement;

import java.util.ArrayList;

import model.CustomerManagement.CustomerProfile;
import model.MarketModel.MarketChannelAssignment;
import model.ProductManagement.Product;
import model.SalesManagement.SalesPersonProfile;

/**
 *
 * @author kal bugrara
 */
public class Order {

    ArrayList<OrderItem> orderitems;
    CustomerProfile customer;
    SalesPersonProfile salesperson;
    MarketChannelAssignment mca;
    String status;
    static int nextId = 1;
    int id;

    public Order(){
       this.orderitems = new ArrayList<>();

    }
    
    public Order(CustomerProfile cp) {
        orderitems = new ArrayList<OrderItem>();
        customer = cp;
        customer.addCustomerOrder(this); //we link the order to the customer
        salesperson = null;
        status = "in process";
        id = nextId++;
    }


    public Order(CustomerProfile cp, SalesPersonProfile ep) {
        orderitems = new ArrayList<>();
        customer = cp;
        salesperson = ep;
        customer.addCustomerOrder(this); //we link the order to the customer
        salesperson.addSalesOrder(this);  
    }
    public OrderItem newOrderItem(Product p, int actualprice, int q) {
        OrderItem oi = new OrderItem(p, actualprice, q);
        orderitems.add(oi);
        return oi;
    }
    public int getId() {
        return id;
    }

    //order total is the sumer of the order item totals
    public int getOrderTotal() {
        int sum = 0;
        for (OrderItem oi : orderitems) {
            sum = sum + oi.getOrderItemTotal();
        }
        return sum;
    }

    public ArrayList<OrderItem> getOrderItems() {
        return orderitems;
    }

    public void addOrderItem(model.Business.Business.OrderItem newOrderItem) {
        if (newOrderItem != null) {
            this.orderitems.add(newOrderItem);
            System.out.println("Order Item added to Order" + newOrderItem);
        }
    }

    public int getOrderPricePerformance() {
        int sum = 0;
        for (OrderItem oi : orderitems) {
            sum = sum + oi.calculatePricePerformance();     //positive and negative values       
        }
        return sum;
    }

    public int getNumberOfOrderItemsAboveTarget() {
        int sum = 0;
        for (OrderItem oi : orderitems) {
            if (oi.isActualAboveTarget() == true) {
                sum = sum + 1;
            }
        }
        return sum;
    }
    
    //sum all the item targets and compare to the total of the order 
    public boolean isOrderAboveTotalTarget(){
        int sum = 0;
        for (OrderItem oi: orderitems){
            sum = sum + oi.getOrderItemTargetTotal(); //product targets are added
        }
        if(getOrderTotal()>sum) {return true;}
        else {return false;}
        
    }
    public int getOrderPriceSum() {
        int sum = 0;
        for (OrderItem oi : orderitems) {
            // sum = sum + oi.calculatePricePerformance(); // positive and negative values
            sum = sum + oi.calculateActualPriceSum();
        }
        return sum;
    }

    public void printSalesOrder() {
        System.out.println("Order ID: " + getId());
        
        System.out.println("Order Status: " + status);
        System.out.println("Order Total: " + getOrderTotal());
        System.out.println("Order Price Performance: " + getOrderPricePerformance());
        System.out.println("Number of Order Items Above Target: " + getNumberOfOrderItemsAboveTarget());
        System.out.println("Is Order Above Total Target: " + isOrderAboveTotalTarget());
        System.out.println("Order Items: ");

        for (OrderItem oi : orderitems) {
            oi.printOrderItemInfo();
        }

    }
public void CancelOrder(){
    status = "Cancelled";
}
public void Submit(){
    status = "Submitted";
}
}
