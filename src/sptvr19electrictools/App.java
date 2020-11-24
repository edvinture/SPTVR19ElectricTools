/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sptvr19electrictools;

import entity.Person;
import entity.Product;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import tools.manager.PersonManager;
import tools.manager.ProductManager;
import tools.savers.SaveToFile;


class App {
    
    List<Product> listProducts = new ArrayList<>();
    ProductManager productManager = new ProductManager(); 
    
    List<Person> listPersons = new ArrayList<>();
    PersonManager personManager = new PersonManager();  
    
    
    public App() {
        SaveToFile saveToFile = new SaveToFile();
        this.listProducts = saveToFile.loadFromFile("listProducts");
        this.listPersons = saveToFile.loadFromFile("listPersons");
    }     
    boolean work = true;
    
    Scanner scan = new Scanner(System.in);
    
    public void run() {
        
        System.out.println("магазин электро приборов");
        
        while (work) {
            
            System.out.println("Задачи:");
            System.out.println("1. Добавить товар");
            System.out.println("2. Список товаров");
            System.out.println("3. Добавить покупателя");
            System.out.println("4. Список покупателей");
            System.out.println("5. Купить товар");
            System.out.println("6. Выход");
            System.out.println("Выберите задачу:");
            
            int function = scan.nextInt();
            
            switch (function) {
                default:
                case 1:     
                    Product prod = productManager.createProduct();
                    productManager.addProductToList(prod, listProducts);
                    break;
                case 2:
                    if (listProducts.size() == 0) {
                    } else {
                        productManager.showList(listProducts);
                    }   
                    break;
                case 3:
                    Person pers = personManager.createPerson();
                    personManager.addPersonToList(pers, listPersons);
                    break;   
                case 4:
                    if (listPersons.size() == 0) {
                    } else {
                        personManager.showList(listPersons);
                    }  
                    break;      
                case 5:  
                    if (listPersons.size() == 0) {
                    } else if (listProducts.size() == 0) {
                    } else {
                        personManager.showList(listPersons);
                        int choosed_person = scan.nextInt();
                        
                        if (choosed_person >= 0 && choosed_person < listPersons.size()) {
                            productManager.showList(listProducts);
                            int choosed_product = scan.nextInt();
                            
                            if (choosed_product >= 0 && choosed_product < listProducts.size()) {
                                
                                if (listPersons.get(choosed_person).getMoney() >= listProducts.get(choosed_product).getPrice()) {
                                    listPersons.get(choosed_person).setMoney(listPersons.get(choosed_person).getMoney() - listProducts.get(choosed_product).getPrice());
                                    listPersons.get(choosed_person).getListProducts().add(listProducts.get(choosed_product));
                                    listProducts.remove(choosed_product);
                                } else {
                                    
                                }
                            
                            } else {
                            }                        
                        } else {
                        }                        
                    }  
                    break;  
                case 6:                   
                    work = false;                      
                    break;                      
            }
        }
    } 
}
