package ZooManagementSystem.models;

import java.time.LocalDateTime;

public class FeedingTask {
    private final int feedingID;
    private final Integer animalID;
    private final Integer enclosureID;
    private final LocalDateTime scheduledAt;
    private final String foodType;
    private final String amount;
    private final boolean completed;
    private final LocalDateTime completedAt;
    private final Integer completedByWorkerID;

    public FeedingTask(int feedingID, Integer animalID, Integer enclosureID, LocalDateTime scheduledAt, String foodType, String amount, boolean completed, LocalDateTime completedAt, Integer completedByWorkerID) {
        this.feedingID = feedingID;
        this.animalID = animalID;
        this.enclosureID = enclosureID;
        this.scheduledAt = scheduledAt;
        this.foodType = foodType;
        this.amount = amount;
        this.completed = completed;
        this.completedAt = completedAt;
        this.completedByWorkerID = completedByWorkerID;
    }

    public int getFeedingID() {
        return feedingID;
    }

    public Integer getAnimalID() {
        return animalID;
    }

    public Integer getEnclosureID() {
        return enclosureID;
    }

    public LocalDateTime getScheduledAt() {
        return scheduledAt;
    }

    public String getFoodType() {
        return foodType;
    }

    public String getAmount() {
        return amount;
    }

    public boolean isCompleted() {
        return completed;
    }

    public LocalDateTime getCompletedAt() {
        return completedAt;
    }

    public Integer getCompletedByWorkerID() {
        return completedByWorkerID;
    }
}
