package ZooManagementSystem.promotions;

import java.util.List;

public interface Subject {
    void addObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObservers();
    void setPromotion(String promotion);
    String getPromotion();
}
