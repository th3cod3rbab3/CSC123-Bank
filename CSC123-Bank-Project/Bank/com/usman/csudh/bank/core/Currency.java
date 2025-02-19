package com.usman.csudh.bank.core;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;

public class Currency {
	String currSelling; 
	String currBuying;
	
	
    public static void conversion()throws FileNotFoundException {
        File curFile = new File("exchange-rate.csv");
        Scanner input = new Scanner(curFile);
        
        if (!curFile.exists()) {
			System.out.println("**Currency File Could Not Be Loaded, Currency Conversion Service And Foreign Currency Accounts Are Not Available**");
		}
        
        Scanner kb = new Scanner(System.in);
        String[] arr;
        HashMap<String, Double> exchangeRates = new HashMap<>();
        
        while (input.hasNext()) {
            String line = input.nextLine();
            arr = line.split(",");
            String currencyCode = arr[0];
            double exchangeRate = Double.parseDouble(arr[2]);
            exchangeRates.put(currencyCode, exchangeRate);
        }
            System.out.println("The currency you are selling: ");
            String currSelling = kb.next();
            System.out.println("The amount you are selling: ");
            double amount = kb.nextDouble();
            if(amount <=0){
                System.out.println("Invalid amount entered, must be greater the 0");
            }
            System.out.println("The currency you are buying: ");
            kb.nextLine();
            String currBuying=kb.nextLine();


            if(currSelling.equalsIgnoreCase(currBuying)){
                System.out.println("Currency is already in "+ currBuying);
            }else if(currSelling.equalsIgnoreCase("USD")){
                double exchangeRate = exchangeRates.get(currBuying.toUpperCase());
                if(exchangeRates.containsKey(currBuying.toUpperCase())){
                    double currAmount = amount/ exchangeRate;
                        System.out.printf("Exchange rate is %f and you will get USD $%.2f\n", exchangeRate,  currAmount);
                    }else{
                      System.out.println(currSelling + " is not a valid currency code");  
                    }
                }else if (exchangeRates.containsKey(currSelling.toUpperCase())) {
                double exchangeRate = exchangeRates.get(currSelling.toUpperCase());
                 double currAmount = amount * exchangeRate;
                System.out.printf("Exchange rate is %f and you will get %s $%.2f\n", exchangeRate, currBuying,  currAmount);
            } else {
                System.out.println(currSelling + " is not a valid currency code");
            }
        }

        }