package ZooManagementSystem.services;

import ZooManagementSystem.data.FeedingScheduleRepository;
import ZooManagementSystem.models.FeedingTask;

import java.sql.SQLException;
import java.time.LocalDateTime;

public class FeedingService {
    private final FeedingScheduleRepository feedingRepository;

    public FeedingService(FeedingScheduleRepository feedingRepository) {
        this.feedingRepository = feedingRepository;
    }

    public int scheduleFeeding(FeedingTask task) throws SQLException {
        if (task.getAnimalID() == null && task.getEnclosureID() == null) {
            throw new IllegalArgumentException("Feeding must target an animal or enclosure.");
        }
        if (task.getScheduledAt() == null || task.getFoodType() == null || task.getFoodType().isBlank()) {
            throw new IllegalArgumentException("Feeding time and food type are required.");
        }
        return feedingRepository.create(task);
    }

    public boolean completeFeeding(int feedingID, int workerID) throws SQLException {
        if (feedingID <= 0 || workerID <= 0) {
            throw new IllegalArgumentException("Feeding ID and worker ID are required.");
        }
        return feedingRepository.markCompleted(feedingID, workerID, LocalDateTime.now());
    }
}
