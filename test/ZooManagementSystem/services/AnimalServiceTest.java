package ZooManagementSystem.services;

import ZooManagementSystem.data.AnimalRepository;
import ZooManagementSystem.data.EnclosureRepository;
import ZooManagementSystem.models.AnimalRecord;
import ZooManagementSystem.models.Enclosure;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class AnimalServiceTest {
    @Test
    void createsAnimalWhenEnclosureHasCapacity() throws SQLException {
        FakeAnimalRepository animalRepository = new FakeAnimalRepository(1);
        AnimalService service = new AnimalService(animalRepository, new FakeEnclosureRepository(new Enclosure(1, 1, "Savannah Yard", "Savannah", 2)));

        int id = service.createAnimal(new AnimalRecord(10, "Lion", "Nala", 4, "Healthy", "Savannah", 1, 1));

        assertEquals(10, id);
        assertTrue(animalRepository.created);
    }

    @Test
    void rejectsAnimalWhenEnclosureIsFull() {
        FakeAnimalRepository animalRepository = new FakeAnimalRepository(2);
        AnimalService service = new AnimalService(animalRepository, new FakeEnclosureRepository(new Enclosure(1, 1, "Savannah Yard", "Savannah", 2)));

        IllegalStateException exception = assertThrows(IllegalStateException.class,
                () -> service.createAnimal(new AnimalRecord(11, "Tiger", "Mask", 5, "Healthy", "Savannah", 1, 1)));

        assertEquals("Enclosure is already at full capacity.", exception.getMessage());
    }

    @Test
    void rejectsAnimalWhenHabitatDoesNotMatchEnclosure() {
        AnimalService service = new AnimalService(new FakeAnimalRepository(0), new FakeEnclosureRepository(new Enclosure(2, 1, "Arctic Pool", "Arctic", 4)));

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> service.createAnimal(new AnimalRecord(12, "Lion", "Leo", 5, "Healthy", "Savannah", 1, 2)));

        assertEquals("Animal habitat does not match enclosure habitat.", exception.getMessage());
    }

    @Test
    void allowsUpdateWhenAnimalIsAlreadyInFullEnclosure() throws SQLException {
        AnimalRecord existing = new AnimalRecord(10, "Lion", "Nala", 4, "Healthy", "Savannah", 1, 1);
        FakeAnimalRepository animalRepository = new FakeAnimalRepository(2);
        animalRepository.existingAnimal = existing;
        AnimalService service = new AnimalService(animalRepository, new FakeEnclosureRepository(new Enclosure(1, 1, "Savannah Yard", "Savannah", 2)));

        boolean updated = service.updateAnimal(new AnimalRecord(10, "Lion", "Nala", 5, "Healthy", "Savannah", 1, 1));

        assertTrue(updated);
    }

    private static class FakeAnimalRepository extends AnimalRepository {
        private final int occupancy;
        private boolean created;
        private AnimalRecord existingAnimal;

        private FakeAnimalRepository(int occupancy) {
            this.occupancy = occupancy;
        }

        @Override
        public int create(AnimalRecord animal) {
            created = true;
            return animal.getAnimalID();
        }

        @Override
        public boolean update(AnimalRecord animal) {
            return true;
        }

        @Override
        public Optional<AnimalRecord> findById(int animalID) {
            return Optional.ofNullable(existingAnimal);
        }

        @Override
        public int countByEnclosure(int enclosureID) {
            return occupancy;
        }
    }

    private static class FakeEnclosureRepository extends EnclosureRepository {
        private final Enclosure enclosure;

        private FakeEnclosureRepository(Enclosure enclosure) {
            this.enclosure = enclosure;
        }

        @Override
        public Optional<Enclosure> findById(int enclosureID) {
            return Optional.of(enclosure);
        }
    }
}
