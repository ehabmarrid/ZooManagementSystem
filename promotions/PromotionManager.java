package ZooManagementSystem.promotions;

import java.util.ArrayList;
import java.util.List;

public class PromotionManager implements Subject {
    private List<Observer> observers;
    private String promotion;

    public PromotionManager() {
        this.observers = new ArrayList<>();
    }

    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(promotion);
        }
    }

    @Override
    public void setPromotion(String promotion) {
        this.promotion = promotion;
        notifyObservers();
    }

    @Override
    public String getPromotion() {
        return promotion;
    }
}
