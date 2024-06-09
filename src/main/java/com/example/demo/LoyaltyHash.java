package com.example.demo;

import java.util.HashMap;
import java.util.Map;

//author: LoganPooran
//year: 2024

class Customer
{
    private String phoneNumber;
    private String loyaltyLevel;
    private String rewards;

    public Customer(String phoneNumber, String loyaltyLevel, String rewards)
    {
        this.phoneNumber = phoneNumber;
        this.loyaltyLevel = loyaltyLevel;
        this.rewards = rewards;
    }

    public String getPhoneNumber()
    {
        return phoneNumber;
    }

    public String getLoyaltyLevel()
    {
        return loyaltyLevel;
    }

    public String getRewards()
    {
        return rewards;
    }

    @Override
    public String toString()
    {
        return "Customer{" +
                "phoneNumber:'" + phoneNumber + '\'' +
                ", loyaltyLevel:'" + loyaltyLevel + '\'' +
                ", rewards:'" + rewards + '\'' +
                '}';
    }
}

class CustomerLoyaltyRewards
{
    private Map<Integer, Customer> customerMap;

    public CustomerLoyaltyRewards()
    {
        customerMap = new HashMap<>();
        //**Preloading members**

        //Rewards member #1
        Customer cus1 = new Customer("4258885555", "10",  "15% off");
        int customerPhoneNumber = Integer.parseInt("4258885555");
        customerMap.put(customerPhoneNumber, cus1);

        //Rewards member #2
        Customer cus2 = new Customer("4250002222", "8",  "10% off");
        int customerPhoneNumber2 = Integer.parseInt("4250002222");
        customerMap.put(customerPhoneNumber2, cus2);

        //Rewards member #3
        Customer cus3 = new Customer("4251119999", "5",  "7% off");
        int customerPhoneNumber3 = Integer.parseInt("4251119999");
        customerMap.put(customerPhoneNumber3, cus3);


    }

    public void addCustomer(Customer customer)
    {
        int customerPhoneNumber = Integer.parseInt(customer.getPhoneNumber());
        customerMap.put(customerPhoneNumber, customer);
    }

    public Customer getCustomerByPhoneNumber(String phoneNumber)
    {
        return customerMap.get(phoneNumber);
    }

    public boolean isCustomerExists(String phoneNumber)
    {
        return customerMap.containsKey(phoneNumber);
    }

    public void removeCustomer(String phoneNumber)
    {
        customerMap.remove(phoneNumber);
    }
}
