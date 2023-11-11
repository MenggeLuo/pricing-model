/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.Business;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import model.CustomerManagement.ChannelCatalog;
import model.CustomerManagement.CustomerDirectory;
import model.CustomerManagement.CustomerProfile;
import model.CustomerManagement.MarketCatalog;
import model.MarketingManagement.MarketingPersonDirectory;
import model.OrderManagement.MasterOrderList;
import model.OrderManagement.Order;
import model.OrderManagement.OrderItem;
import model.Personnel.EmployeeDirectory;
import model.Personnel.PersonDirectory;
import model.ProductManagement.Product;
import model.ProductManagement.ProductCatalog;
import model.ProductManagement.ProductSummary;
import model.ProductManagement.ProductsReport;
import model.ProductManagement.SolutionOfferCatalog;
import model.SalesManagement.SalesPersonDirectory;
import model.Supplier.Supplier;
import model.Supplier.SupplierDirectory;
import model.UserAccountManagement.UserAccountDirectory;

/**
 *
 * @author kal bugrara
 */
public class Business {

    String name;
    PersonDirectory persondirectory;
    MasterOrderList masterorderlist;
    SupplierDirectory suppliers;
    MarketCatalog marketcatalog;
    ChannelCatalog channelcatalog;
    SolutionOfferCatalog solutionoffercatalog;
    CustomerDirectory customerdirectory;
    EmployeeDirectory employeedirectory;
    SalesPersonDirectory salespersondirectory;
    UserAccountDirectory useraccountdirectory;
    MarketingPersonDirectory marketingpersondirectory;

    public Business(String n) {
        name = n;
        masterorderlist = new MasterOrderList();
        suppliers = new SupplierDirectory();
        persondirectory = new PersonDirectory();
        customerdirectory = new CustomerDirectory(this);
        salespersondirectory = new SalesPersonDirectory(this);
        useraccountdirectory = new UserAccountDirectory();
        marketingpersondirectory = new MarketingPersonDirectory(this);
        employeedirectory = new EmployeeDirectory(this);

    }

    public int getSalesVolume() {
        return masterorderlist.getSalesVolume();

    }

    public PersonDirectory getPersonDirectory() {
        return persondirectory;
    }

    public UserAccountDirectory getUserAccountDirectory() {
        return useraccountdirectory;
    }
    public MarketingPersonDirectory getMarketingPersonDirectory() {
        return marketingpersondirectory;
    }

    public SupplierDirectory getSupplierDirectory() {
        return suppliers;
    }

    public ProductsReport getSupplierPerformanceReport(String n) {
        Supplier supplier = suppliers.findSupplier(n);
        if (supplier == null) {
            return null;
        }
        return supplier.prepareProductsReport();

    }

    public ArrayList<ProductSummary> getSupplierProductsAlwaysAboveTarget(String n) {

        ProductsReport productsreport = getSupplierPerformanceReport(n);
        return productsreport.getProductsAlwaysAboveTarget();

    }

    public int getHowManySupplierProductsAlwaysAboveTarget(String n) {
        ProductsReport productsreport = getSupplierPerformanceReport(n); // see above
        int i = productsreport.getProductsAlwaysAboveTarget().size(); //return size of the arraylist
        return i;
    }

    public CustomerDirectory getCustomerDirectory() {
        return customerdirectory;
    }

    public SalesPersonDirectory getSalesPersonDirectory() {
        return salespersondirectory;
    }

    public MasterOrderList getMasterOrderList() {
        return masterorderlist;
    }
    public EmployeeDirectory getEmployeeDirectory() {
        return employeedirectory;
    }

    //add orders
    public Order addOrder(CustomerProfile customer) {
        Order order = masterorderlist.newOrder(customer);
        return order;
    }

    public void processOrder(CustomerProfile customerProfile, Product product, int price, int quantity) {
        Order currentOrder = addOrder(customerProfile); // create order and associate with customer
        if (currentOrder != null) {
            OrderItem newOrderItem = new OrderItem(product.getName(), product.getDescription(), price, quantity);
            currentOrder.addOrderItem(newOrderItem);
        } else {
            // Handle the case where currentOrder is null
        }
    }
    Product selectedProduct = new Product("Product Name", "Product Description", 0); // replace with actual product details

    // Define the OrderItem class constructor
    public class OrderItem {
        private String name;
        private String description;
        private int price;
        private int quantity;

        public OrderItem(String name, String description, int price, int quantity) {
            this.name = name;
            this.description = description;
            this.price = price;
            this.quantity = quantity;
        }

        // Getters and setters for the OrderItem class
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }
    }
    int actualPrice = 10; // replace with actual price value
    int quantity = 1; // replace with actual quantity value

    // Define the Product class constructor
    public class Product {
        private String name;
        private String description;
        private int price;

        public Product(String name, String description, int price) {
            this.name = name;
            this.description = description;
            this.price = price;
        }

        // Getters and setters for the Product class
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }
    }
    OrderItem newOrderItem = new OrderItem(selectedProduct.getName(), selectedProduct.getDescription(), selectedProduct.getPrice(), quantity); // 创建订单项并关联所选产品
   





    
    //pick 3 random customers and print their sales orders
    public ArrayList<CustomerProfile> pickRandomCustomerForOrder(int pickedCustomerCountForOrder){
        ArrayList<CustomerProfile> pickedCustomers = new ArrayList<>();
        List<CustomerProfile> customerList = customerdirectory.getCustomerList();
        if (customerList.size() < pickedCustomerCountForOrder) {
            return pickedCustomers;
        }
        Collections.shuffle(customerdirectory.getCustomerList());
        int counter = 0;
        int index = 0;
        while(counter < pickedCustomerCountForOrder && index < customerList.size()){
            CustomerProfile customer = customerList.get(index);
            if (customer.getOrders() != null && !customer.getOrders().isEmpty()) {
                pickedCustomers.add(customer);
                counter++;
            }
            index++;
        }
        return pickedCustomers;
    }

    public void printSalesOrders(int pickNum){
        ArrayList<CustomerProfile> pickedCustomers = pickRandomCustomerForOrder(pickNum);
        for (CustomerProfile customer : pickedCustomers){
            customer.printSalesOrder();
        }
    }
    //part2 pick the most expensive product
    public ArrayList<Supplier> pickRandomSupplierForExpensiveProduct(int pickedSpCountforProductPrice) {

        Collections.shuffle(suppliers.getSuplierList());
        ArrayList<Supplier> ps = new ArrayList<>();
        int counter = 0;
        while (counter < pickedSpCountforProductPrice) {
            Supplier randomsupplier = suppliers.pickRandomSupplier();
            ProductCatalog pc = randomsupplier.getProductCatalog();
            if (pc.getProductList().size() == 0) {
                continue;
            }
            ps.add(randomsupplier);
            counter++;
        }

        return ps;
    }

    public void printMostExpensiveProduct(int pickNum) {
        ArrayList<Supplier> expensive = pickRandomSupplierForExpensiveProduct(pickNum);
        for (Supplier s : expensive) {
            s.printMostExpensiveProduct();
        }
    }
    public void findMostSpentCustomer(){
        CustomerProfile mostSpentCustomer = customerdirectory.FindMostSpentCustomer();
        mostSpentCustomer.printMostSpentCustomer();
        }
    
        public Supplier findSupplierWithMostSales() {
            Supplier supplierWithMostSales = suppliers.getSuplierList().get(0);
    
            for (Supplier s : suppliers.getSuplierList()) {
                if (s.getNumberOfOrders() > supplierWithMostSales.getNumberOfOrders())
                    supplierWithMostSales = s;
            }
    
            return supplierWithMostSales;
        }
        
        public Supplier findSupplierWithLeastSales() {
            Supplier supplierWithLeastSales = null;
        
            // Find the first supplier with non-zero orders
            for (Supplier s : suppliers.getSuplierList()) {
                if (s.getNumberOfOrders() != 0) {
                    supplierWithLeastSales = s;
                    break;
                }
            }
        
            // If all suppliers have zero orders, return null
            if (supplierWithLeastSales == null) {
                return null;
            }
        
            for (Supplier s : suppliers.getSuplierList()) {
                if (s.getNumberOfOrders() != 0 && s.getNumberOfOrders() < supplierWithLeastSales.getNumberOfOrders()) {
                    supplierWithLeastSales = s;
                }
            }
        
            return supplierWithLeastSales;
        }
    
        public void printSupplierWithMostSales() {
            Supplier supplierWithMostSales = findSupplierWithMostSales();
            System.out.println("The supplier with most sales is " + supplierWithMostSales.getName() + " with "
                    + supplierWithMostSales.getNumberOfOrders() + " orders.");
        }
    
        public void printSupplierWithLeastSales() {
            Supplier supplierWithLeastSales = findSupplierWithLeastSales();
            if (supplierWithLeastSales != null) {
                System.out.println("The supplier with least sales is " + supplierWithLeastSales.getName() + " with "
                        + supplierWithLeastSales.getNumberOfOrders() + " orders.");
            } else {
                System.out.println("No supplier found with non-zero sales.");
            }
            System.out.println();
        }
    

    public void printShortInfo(){
        System.out.println("Checking what's inside the business hierarchy.");
        suppliers.printShortInfo();
        customerdirectory.printShortInfo();
        masterorderlist.printShortInfo();
    }

}
