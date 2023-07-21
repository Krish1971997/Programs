package invoiceManagement;

import java.util.Scanner;
public class InvoiceSystem
{
    public static void main(String []args)
    {
        Scanner s=new Scanner(System.in);
        Customer[] customer=new Customer[10];
        Invoice[] invoice=new Invoice[20];
        Item[] item=new Item[20];
        item[0]=new Item(400,"HeadPhone");
        item[1]=new Item(1000,"Speaker");
        item[2]=new Item(500,"SD card");
        item[3]=new Item(600,"Battery");
        int cust_id=1,invoice_id=1,j=1,customer_id=1,Invoice_id=1,n=1;
        while(true)
        {
                System.out.println("_____________INVOICE SYSTEM______________");
                System.out.println("1.Add Customer");
                System.out.println("2.Add Invoice");
                System.out.println("3.Add Item to Invoice");
                System.out.println("4.List all Customers");
                System.out.println("5.List all Invoices");
                System.out.println("6.List all invoices of a Customer");
                System.out.println("7.Display an invoice");
                n=s.nextInt();
                if(n<1||n>7)
                    System.out.println("Please enter correct option!!!");
                else if(n==1)
                {
                    customer[cust_id]=new Customer();
                    customer[cust_id].addCustomer();
                    System.out.println("Your customer id is "+cust_id);
                    cust_id++;
                }
                else if(n==2)
                {
                    System.out.println("1.Existing Customer");
                    System.out.println("2.New Customer");
                    j=s.nextInt();
                    if(j==1)
                    {
                        System.out.println("Enter Customer id:");
                        customer_id=s.nextInt();
                        if(customer_id>=cust_id)
                        {
                            System.out.println("Invalid Id!!");
                            return;
                        }   
                    }
                    else if(j==2)
                    {
                        customer[cust_id]=new Customer();
                        customer[cust_id].addCustomer();
                        System.out.println("Your customer id is "+cust_id);
                        customer_id=cust_id;
                        cust_id++;
                    }
                    else
                    {
                        System.out.println("Please enter correct option!!!");
                        return;
                    }
                    invoice[invoice_id]=new Invoice();
                    customer[customer_id].addInvoiceId(invoice_id);
                    j=1;
                    while(j==1)
                    {
                        System.out.println("Select the Item");
                        System.out.println("1.HeadPhone Rs.400");
                        System.out.println("2.Speaker Rs.1000");
                        System.out.println("3.SD card Rs.500");
                        System.out.println("4.Battery Rs.6000");
                        j=s.nextInt();
                        if(j>4)
                        {
                            System.out.println("Invalid Option");
                            return;
                        }
                        invoice[invoice_id].addInvoice(j-1);
                        invoice[invoice_id].price+=item[j-1].price;
                        invoice[invoice_id].noOfItem+=1;
                        System.out.println("Are you want to add one more item yes 1 or no 0");
                        j=s.nextInt();
                    }
                    System.out.format("%-20s%-10d","Your invoice no:",invoice_id);
                    System.out.println("");
                    invoice_id++;
                }
                else if(n==3)
                {
                    System.out.println("Enter the invoice Number:");
                    Invoice_id=s.nextInt();
                    if(Invoice_id>=invoice_id)
                    {
                        System.out.println("Invalid Invoice Number!!!");
                    }
                    else
                    {
                        int k=1;
                        while(k==1)
                        {
                            System.out.println("Select the Item");
                            System.out.println("1.HeadPhone Rs.400");
                            System.out.println("2.Speaker Rs.1000");
                            System.out.println("3.SD card Rs.500");
                            System.out.println("4.Battery Rs.6000");
                            k=s.nextInt();
                            if(j>4)
                            {
                                System.out.println("Invalid Option");
                                return;
                            }
                            invoice[Invoice_id].addInvoice(k-1);
                            invoice[Invoice_id].price+=item[k-1].price;
                            invoice[Invoice_id].noOfItem+=1;
                            System.out.println("Are you want to add one more item yes 1 or no 0");
                            k=s.nextInt();
                        }
                    }
                }
                else if(n==4)
                {
                    System.out.format("%-10s%-12s%-15s%-12s","Cust_id","Cust_name","Mobile","Address");
                    for(j=0;j<cust_id;j++)
                    {
                        System.out.println("");
                        System.out.format("%-10s%-12s%-15s%-12s",j,customer[j].cust_name,customer[j].mobileNo,customer[j].address);
                    }
                    System.out.println("");
                }
                else if(n==5)
                {
                    for(j=0;j<invoice_id;j++)
                    {
                        displayItem(j,invoice,item);
                    }
                }
                else if(n==6)
                {
                    int price=0,noOfItem=0;
                    System.out.println("Enter Customer id:");
                    customer_id=s.nextInt();
                    if(cust_id<=customer_id)
                    {
                        System.out.println("Invalid Id!!");
                        return;
                    }
                    int[] invoices=new int[10];
                    invoices=customer[customer_id].getArray();;
                    System.out.format("%-10s%-10d","Customer:",customer_id);
                    System.out.println("");
                    System.out.println("__________________________________");
                    System.out.println("----------------------------------");
                    System.out.println("");
                    for(j=0;j<customer[customer_id].length;j++)
                    {
                        Invoice_id=invoices[j];
                        displayItem(Invoice_id,invoice,item);
                        price+=invoice[Invoice_id].price;
                        noOfItem+=invoice[Invoice_id].noOfItem;
                    }
                    System.out.println("---------------------------------------------------------------------");
                    System.out.println("");
                    System.out.format("%-30s%-10d","Total number of Items Bought by this customer :",noOfItem);
                    System.out.println("");
                    System.out.format("%-30s%-10d","Total  amount spent by this customer :",price);
                    System.out.println("");
                    System.out.println("---------------------------------------------------------------------");
                }
                else
                {
                    System.out.println("Enter the invoice No:");
                    Invoice_id=s.nextInt();
                    if(Invoice_id<=invoice_id)
                    {
                        System.out.println("Invalid id!!!!");
                    }
                    else 
                    {
                        displayItem(Invoice_id,invoice,item);
                    }
                }
        }
    }
    public static void displayItem(int Invoice_id,Invoice[] invoice,Item[] item)
    {
        int[] items=new int[10];
        int item_id=0,j;
        items=invoice[Invoice_id].getArray();
        System.out.format("%-15s%-10d","Invoice_no",Invoice_id);
        System.out.println("");
        System.out.println("-----------------------------");
        System.out.format("%-12s%-15s","item_name","unit_price");
        for(j=0;j<invoice[Invoice_id].length;j++)
        {
            item_id=items[j];
            System.out.println("");
            System.out.format("%-15s%-20d",item[item_id].item_name,item[item_id].price);
        }
        System.out.println("");
        System.out.println("-----------------------------------------");
        System.out.format("%-15s%-10d%-15s%-10d","TotalItems:",invoice[Invoice_id].noOfItem,"TotalPrice:",invoice[Invoice_id].price);
        System.out.println("");
        System.out.println("-----------------------------------------");
    }
}
