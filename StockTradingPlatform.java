import java.util.ArrayList;
import java.util.Scanner;

// Stock Class
class Stock {
    private String symbol;
    private String name;
    private double price;

    public Stock(String symbol, String name, double price) {
        this.symbol = symbol;
        this.name = name;
        this.price = price;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
}

// Transaction Class
class Transaction {
    private String type;
    private String stockName;
    private int quantity;
    private double amount;

    public Transaction(String type, String stockName, int quantity, double amount) {
        this.type = type;
        this.stockName = stockName;
        this.quantity = quantity;
        this.amount = amount;
    }

    @Override
    public String toString() {
        return type + " | " + stockName +
                " | Qty: " + quantity +
                " | Amount: ₹" + amount;
    }
}

// Portfolio Item Class
class PortfolioItem {
    String stockName;
    int quantity;

    public PortfolioItem(String stockName, int quantity) {
        this.stockName = stockName;
        this.quantity = quantity;
    }
}

// User Class
class User {
    ArrayList<PortfolioItem> portfolio = new ArrayList<>();
    ArrayList<Transaction> transactions = new ArrayList<>();

    public void buyStock(String stockName, int quantity, double price) {

        boolean found = false;

        for (PortfolioItem item : portfolio) {
            if (item.stockName.equalsIgnoreCase(stockName)) {
                item.quantity += quantity;
                found = true;
                break;
            }
        }

        if (!found) {
            portfolio.add(new PortfolioItem(stockName, quantity));
        }

        transactions.add(
                new Transaction(
                        "BUY",
                        stockName,
                        quantity,
                        quantity * price));

        System.out.println("Stock Purchased Successfully!");
    }

    public void sellStock(String stockName, int quantity, double price) {

        for (PortfolioItem item : portfolio) {

            if (item.stockName.equalsIgnoreCase(stockName)) {

                if (item.quantity >= quantity) {

                    item.quantity -= quantity;

                    transactions.add(
                            new Transaction(
                                    "SELL",
                                    stockName,
                                    quantity,
                                    quantity * price));

                    System.out.println("Stock Sold Successfully!");
                    return;
                } else {
                    System.out.println("Not enough quantity available!");
                    return;
                }
            }
        }

        System.out.println("Stock not found in portfolio!");
    }

    public void viewPortfolio() {

        if (portfolio.isEmpty()) {
            System.out.println("Portfolio is Empty!");
            return;
        }

        System.out.println("\n----- PORTFOLIO -----");

        for (PortfolioItem item : portfolio) {
            System.out.println(item.stockName +
                    " | Quantity: " +
                    item.quantity);
        }
    }

    public void viewTransactions() {

        if (transactions.isEmpty()) {
            System.out.println("No Transactions Yet!");
            return;
        }

        System.out.println("\n----- TRANSACTION HISTORY -----");

        for (Transaction t : transactions) {
            System.out.println(t);
        }
    }
}

// Main Class
public class StockTradingPlatform {

    static ArrayList<Stock> stocks = new ArrayList<>();
    static User user = new User();
    static Scanner sc = new Scanner(System.in);

    public static void initializeStocks() {

        stocks.add(new Stock("TCS", "Tata Consultancy Services", 3500));
        stocks.add(new Stock("INFY", "Infosys", 1500));
        stocks.add(new Stock("RELIANCE", "Reliance Industries", 2800));
        stocks.add(new Stock("HDFC", "HDFC Bank", 1700));
    }

    public static void viewStocks() {

        System.out.println("\n----- AVAILABLE STOCKS -----");

        for (int i = 0; i < stocks.size(); i++) {

            Stock s = stocks.get(i);

            System.out.println((i + 1) + ". "
                    + s.getSymbol()
                    + " - "
                    + s.getName()
                    + " - ₹"
                    + s.getPrice());
        }
    }

    public static void buyStock() {

        viewStocks();

        System.out.print("\nSelect Stock Number: ");
        int choice = sc.nextInt();

        System.out.print("Enter Quantity: ");
        int qty = sc.nextInt();

        Stock selected = stocks.get(choice - 1);

        user.buyStock(
                selected.getSymbol(),
                qty,
                selected.getPrice());
    }

    public static void sellStock() {

        System.out.print("Enter Stock Symbol: ");
        String symbol = sc.next();

        System.out.print("Enter Quantity: ");
        int qty = sc.nextInt();

        for (Stock s : stocks) {

            if (s.getSymbol().equalsIgnoreCase(symbol)) {

                user.sellStock(
                        symbol,
                        qty,
                        s.getPrice());

                return;
            }
        }

        System.out.println("Invalid Stock Symbol!");
    }

    public static void main(String[] args) {

        initializeStocks();

        int choice;

        do {

            System.out.println("\n===== STOCK TRADING PLATFORM =====");
            System.out.println("1. View Stocks");
            System.out.println("2. Buy Stock");
            System.out.println("3. Sell Stock");
            System.out.println("4. View Portfolio");
            System.out.println("5. Transaction History");
            System.out.println("6. Exit");

            System.out.print("Enter Choice: ");
            choice = sc.nextInt();

            switch (choice) {

                case 1:
                    viewStocks();
                    break;

                case 2:
                    buyStock();
                    break;

                case 3:
                    sellStock();
                    break;

                case 4:
                    user.viewPortfolio();
                    break;

                case 5:
                    user.viewTransactions();
                    break;

                case 6:
                    System.out.println("Thank You!");
                    break;

                default:
                    System.out.println("Invalid Choice!");
            }

        } while (choice != 6);
    }
}
