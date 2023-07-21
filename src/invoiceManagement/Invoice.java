package invoiceManagement;

public class Invoice
{
    int price,noOfItem,length=0;
    int[] item=new int[10];
    public void addInvoice(int itemId)
    {
        item[length]=itemId;
        length++;
    }
    public int[] getArray()
    {
        return item;
    }
}