/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.Set;

import model.Business.Business;
import model.Business.ConfigureABusiness;
import model.CustomerManagement.CustomerDirectory;
import model.CustomerManagement.CustomerProfile;
import model.OrderManagement.Order;
import model.OrderManagement.OrderItem;
import model.ProductManagement.Product;
import model.ProductManagement.ProductCatalog;
import model.ProductManagement.ProductsReport;
import model.Supplier.Supplier;
import model.Supplier.SupplierDirectory;

/**
 *
 * @author kal bugrara
 */
public class RangePricingApplication {

  /**
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    // TODO code application logic here

    Business business = ConfigureABusiness.createABusinessAndLoadALotOfData("Amazon", 30, 10, 20, 50, 25, 3, 10);
    business.printShortInfo(); // print short info of business (part1)
    System.out.println("--------------------------------------------------");

    business.printSalesOrders(3); // print sale orders of business
    System.out.println("--------------------------------------------------");
    business.printMostExpensiveProduct(3); // print most expensive product of business
    System.out.println("--------------------------------------------------");
    business.printSupplierWithMostSales(); // print supplier with most sales
    System.out.println("--------------------------------------------------");
    business.findSupplierWithLeastSales(); // print supplier with least sales
    System.out.println("--------------------------------------------------");
    business.findMostSpentCustomer(); // print most spent customer
    System.out.println("--------------------------------------------------");
    //
    // List<Supplier> selectedSuppliers = suppliers.subList(0, 10);
    // for (Supplier supplier : selectedSuppliers) {
    // ProductCatalog productCatalog = supplier.getProductCatalog();
    // for (int i = 0; i < 10; i++) {
    // int floorPrice = (int) (Math.random() * 100);
    // int ceilingPrice = floorPrice + (int) (Math.random() * 50);
    // int targetPrice = floorPrice + (int) (Math.random() * 20);
    // productCatalog.newProduct(floorPrice, ceilingPrice, targetPrice);
    // }

  }
}
