# TermProject: proposal

## Project title : Cafe kiosk System

## Team Members
* 박태경 - 22300333

## Project Description
  This project makes a cafe kiosk system using Java's Object-Oriented Programming(OOP). 
I use "Inheritance" to organize many menus like Coffee and Dessert easily. 
Also, I use "Polymorphism" to handle different payments like Credit Cards and Cash, making the code flexible. 
Users can see the menu, put items in a cart, and pay through the console screen. 
I also plan to add an "Administrator Mode" to check total sales and manage stock, just like a real cafe kiosk.

youtube link
https://youtu.be/DpBPE2HKuXs

## 1. Class Diagram (Mermaid)

```mermaid
classDiagram
    %% Relationships
    Main --> Controller : uses
    Controller --> Coffee : manages
    Controller --> Dessert : manages
    Controller --> IPayment : uses
    
    Menu <|-- Coffee : inheritance
    Menu <|-- Dessert : inheritance
    
    IPayment <|.. CardPayment : realization
    IPayment <|.. CashPayment : realization

    %% Classes & Interfaces
    class Main {
        +main(String[] args)
        +run()
    }

    class Menu {
        -String name
        -int price
        +getName() String
        +getPrice() int
    }

    class Coffee {
        +Coffee(String name, int price, boolean isIce)
    }

    class Dessert {
        +Dessert(String name, int price)
    }

    class IPayment {
        <<interface>>
        +pay(int amount)*
    }

    class CardPayment {
        +pay(int amount)
    }

    class CashPayment {
        +pay(int amount)
    }

    class Controller {
        -Scanner sc
        -List~Coffee~ coffeeList
        -List~Dessert~ dessertList
        -List~Coffee~ coffeeCartList
        -List~Dessert~ dessertCartList
        -List~Coffee~ soldCoffeeList
        -List~Dessert~ soldDessertList
        -int totalSales
        +showMenu()
        +selectMenu(int category)
        +showCartList() boolean
        +toPay()
        +showAdminMode()
        +safeInput() int
    }