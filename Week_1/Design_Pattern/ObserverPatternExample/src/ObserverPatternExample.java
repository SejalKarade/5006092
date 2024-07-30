
import java.util.ArrayList;
import java.util.List;

interface Observer {
    void update(String stockName, double price);
}


interface Stock {
    void register(Observer observer);
    void deregister(Observer observer);
    void notifyObservers();
}
class StockMarket implements Stock {
    private List<Observer> observers;
    private String stockName;
    private double price;

    public StockMarket() {
        observers = new ArrayList<>();
    }

    @Override
    public void register(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void deregister(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(stockName, price);
        }
    }

    public void setPrice(String stockName, double price) {
        this.stockName = stockName;
        this.price = price;
        notifyObservers();
    }
}
class MobileApp implements Observer {
    private String appName;

    public MobileApp(String appName) {
        this.appName = appName;
    }

    @Override
    public void update(String stockName, double price) {
        System.out.println(appName + " received update: " + stockName + " is now $" + price);
    }
}
class WebApp implements Observer {
    private String appName;

    public WebApp(String appName) {
        this.appName = appName;
    }

    @Override
    public void update(String stockName, double price) {
        System.out.println(appName + " received update: " + stockName + " is now $" + price);
    }
}

public class ObserverPatternExample {
    public static void main(String[] args) {
        StockMarket stockMarket = new StockMarket();

        Observer mobileApp = new MobileApp("StockMobile");
        Observer webApp = new WebApp("StockWeb");

        stockMarket.register(mobileApp);
        stockMarket.register(webApp);

        stockMarket.setPrice("AAPL", 150.00);
        stockMarket.setPrice("GOOGL", 2800.00);
    }
}
