package carsharing;

import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import static carsharing.Car.*;
import static carsharing.Company.getCompanyName;
import static carsharing.Customer.*;
import static carsharing.Main.getMapKey;


class Run extends Thread {

    int idCar = 0;
    String idCompany = null;
    String idCostumer = null;
    HashMap<Integer, Integer> map;
    HashMap<Integer, Integer> costumersMap;
    HashMap<Integer, Integer> carsMap;

    @Override
    public void run() {
        Scanner scanner = new Scanner(System.in);
        int menuType = 0;
        int lastMenu = 0;
        boolean retry = false;
        carsMap = new HashMap<>();
        costumersMap = new HashMap<>();
        map = new HashMap<>();
        while (true) {
            switch (lastMenu) {
                case 0 -> {
                    System.out.println("1. Log in as a manager");
                    System.out.println("2. Log in as a customer");
                    System.out.println("3. Create a customer");
                    System.out.println("0. Exit");
                }
                case 1 -> {
                    System.out.println();
                    System.out.println("1. Company list");
                    System.out.println("2. Create a company");
                    System.out.println("0. Back");
                }
                case 2 -> {
                    if (menuType == 1) {
                        map.clear();
                        List<String[]> list = Company.getCompanies();
                        if (list.isEmpty()) {
                            System.out.println("The company list is empty!");
                            System.out.println();
                            lastMenu--;
                            continue;
                        }
                        System.out.println();
                        System.out.println("Choose the company");
                        int contor = 1;
                        for (String[] s : list) {
                            System.out.printf("%s. %s\n", s[0], s[1]);
                            Main.useMap(Integer.parseInt(s[0]), contor, map);
                            contor++;
                        }
                        System.out.println("0. Back");
                        idCompany = scanner.nextLine();
                        if (idCompany.equals("0")) {
                            lastMenu--;
                            continue;
                        }
                        System.out.println();
                        System.out.printf("'%s' company\n", getCompanyName(getMapKey(Integer.parseInt(idCompany), map)));
                        lastMenu++;
                        continue;
                    } else {
                        System.out.println("Enter the company name :");
                        String companyName = scanner.nextLine();
                        new Company(companyName);
                        System.out.println("The company was created!");
                        System.out.println();
                        lastMenu--;
                        continue;
                    }
                }
                case 3 -> {
                    System.out.println("1. Car list");
                    System.out.println("2. Create a car");
                    System.out.println("0. Back");
                    String chooseCarMenu = scanner.nextLine();
                    switch (chooseCarMenu) {
                        case "0" -> {
                            lastMenu = 1;
                            continue;
                        }
                        case "1" -> {
                            List<String[]> lst = getCarList(String.valueOf(getMapKey(Integer.parseInt(idCompany), map)));
                            if (lst.isEmpty()) {
                                System.out.println("The car list is empty!");
                                System.out.println();
                                break;
                            }
                            System.out.println();
                            System.out.println("Car list:");
                            int contor = 1;
                            for (String[] s : lst) {
                                System.out.printf("%s. %s\n", contor, s[1]);
                                contor++;
                            }
                            System.out.println();
                        }
                        case "2" -> {
                            System.out.println();
                            System.out.println("Enter the car name:");
                            String enterCarName = scanner.nextLine();
                            new Car(enterCarName, getMapKey(Integer.parseInt(idCompany), map));
                            System.out.println("The car was added!");
                            System.out.println();
                        }
                    }
                    continue;
                }
                case 4 -> {
                    costumersMap.clear();
                    if (!retry) {
                        List<String[]> lst = getCustomerList();
                        if (lst.isEmpty()) {
                            System.out.println("The customer list is empty!");
                            System.out.println();
                            lastMenu = 0;
                            continue;
                        }
                        int contor = 1;
                        System.out.println("Choose a customer:");
                        for (String[] s : lst) {
                            System.out.printf("%s. %s\n", contor, s[1]);
                            Main.useMap(Integer.parseInt(s[0]), contor, costumersMap);
                            contor++;
                        }
                        System.out.println("0. Back");
                        String chooseCostumer = scanner.nextLine();
                        if (Integer.parseInt(chooseCostumer) == 0) {
                            System.out.println();
                            lastMenu = 0;
                            continue;
                        }
                        idCostumer = String.valueOf(getMapKey(Integer.parseInt(chooseCostumer), costumersMap));
                    }
                    System.out.println();
                    System.out.println("1. Rent a car");
                    System.out.println("2. Return a rented car");
                    System.out.println("3. My rented car");
                    System.out.println("0. Back");
                    String rentCar = scanner.nextLine();
                    switch (rentCar) {
                        case "0" :
                            System.out.println();
                            lastMenu = 0;
                            retry = false;
                            break;
                        case "1" :
                            carsMap.clear();
                            List<String[]> lst = getRentedCars(Integer.parseInt(idCostumer));
                            if (lst.size() > 0) {
                                System.out.println("You've already rented a car!");
                                retry = true;
                                break;
                            }
                            System.out.println();
                            System.out.println("Choose the company:");
                            List<String[]> listCompanies = Company.getCompanies();
                            int contor = 1;
                            for (String[] s : listCompanies) {
                                System.out.printf("%s. %s\n", contor, s[1]);
                                Main.useMap(contor, Integer.parseInt(s[0]), map);
                                contor++;
                            }
                            System.out.println("0. Back");
                            String FromWhatCompany = scanner.nextLine();
                            if (FromWhatCompany.equals("0")) {
                                retry = true;
                                break;
                            }
                            int idC = map.get(Integer.parseInt(FromWhatCompany));
                            System.out.println();
                            System.out.println("Choose a car:");
                            List<String[]> lstCars = getCarList(String.valueOf(idC));
                            contor = 1;
                            for (String[] s : lstCars) {
                                System.out.printf("%s. %s\n", contor, s[1]);
                                Main.useMap(Integer.parseInt(s[0]), contor, carsMap);
                                contor++;
                            }
                            System.out.println("0. Back");
                            String whatCar = scanner.nextLine();
                            if (whatCar.equals("0")) {
                                retry = true;
                                break;
                            }
                            idCar = getMapKey(Integer.parseInt(whatCar), carsMap);
                            String carName = getCarName(idCar);
                            RentCar(idCar, Integer.parseInt(idCostumer));
                            System.out.println();
                            System.out.printf("You rented '%s'\n", carName);
                            System.out.println();
                            retry = true;
                            break;
                        case "2", "3":
                            lst = getRentedCars(Integer.parseInt(idCostumer));
                            if (lst.size() == 0) {
                                System.out.println("You didn't rent a car!");
                                retry = true;
                                break;
                            } else {
                                if (rentCar.equals("3")) {
                                    System.out.println();
                                    System.out.println("Your rented car:");
                                    for (String[] s : lst) {
                                        System.out.println(s[0]);
                                        System.out.println("Company:");
                                        System.out.println(s[1]);
                                    }
                                    retry = true;
                                } else {
                                    returnRentedCar(Integer.parseInt(idCostumer));
                                    System.out.println("You've returned a rented car!");
                                    retry = true;
                                }
                            }
                            break;
                    }
                    continue;
                }
                case 5 -> {
                    System.out.println();
                    System.out.println("Enter the customer name:");
                    String enterCostumerName = scanner.nextLine();
                    new Customer(enterCostumerName);
                    System.out.println("The costumer was added!");
                    System.out.println();
                    lastMenu = 0;
                    continue;
                }
            }
            menuType = Integer.parseInt(scanner.nextLine());
            if (menuType == 0 && lastMenu == 0) {
                break;
            } else if (lastMenu == 0 && menuType == 2) {
                lastMenu = 4;
            } else if (lastMenu == 0 && menuType == 3) {
                lastMenu = 5;
            } else if (menuType == 0) {
                lastMenu--;
            } else {
                lastMenu++;
            }
        }
    }
}
