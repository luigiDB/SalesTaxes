package receipt;

public interface IReceiptPrinter {
    /**
     * Print the receipt content
     * @param receipt the receipt to be printed
     */
    void print(IReceipt receipt);
}
