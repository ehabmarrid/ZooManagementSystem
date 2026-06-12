package ZooManagementSystem.services;

import ZooManagementSystem.data.AnimalRepository;
import ZooManagementSystem.data.EnclosureRepository;
import ZooManagementSystem.models.AnimalRecord;
import ZooManagementSystem.models.Enclosure;

import java.sql.SQLException;

public class AnimalService {
    private final AnimalRepository animalRepository;
    private final EnclosureRepository enclosureRepository;

    public AnimalService(AnimalRepository animalRepository, EnclosureRepository enclosureRepository) {
        this.animalRepository = animalRepository;
        this.enclosureRepository = enclosureRepository;
    }

    public int createAnimal(AnimalRecord animal) throws SQLException {
        validateAnimal(animal);
        validateEnclosureCapacity(animal);
        return animalRepository.create(animal);
    }

    public boolean updateAnimal(AnimalRecord animal) throws SQLException {
        validateAnimal(animal);
        validateEnclosureCapacity(animal);
        return animalRepository.update(animal);
    }

    public boolean deleteAnimal(int animalID) throws SQLException {
        return animalRepository.delete(animalID);
    }

    public boolean hasCapacity(int enclosureID) throws SQLException {
        Enclosure enclosure = enclosureRepository.findById(enclosureID)
                .orElseThrow(() -> new IllegalArgumentException("Enclosure was not found: " + enclosureID));
        return animalRepository.countByEnclosure(enclosureID) < enclosure.getCapacity();
    }

    private void validateAnimal(AnimalRecord animal) {
        if (animal.getAge() < 0) {
            throw new IllegalArgumentException("Animal age cannot be negative.");
        }
        if (isBlank(animal.getSpecies()) || isBlank(animal.getName()) || isBlank(animal.getHabitatType())) {
            throw new IllegalArgumentException("Animal species, name, and habitat type are required.");
        }
    }

    private void validateEnclosureCapacity(AnimalRecord animal) throws SQLException {
        if (animal.getEnclosureID() == null) {
            return;
        }
        Enclosure enclosure = enclosureRepository.findById(animal.getEnclosureID())
                .orElseThrow(() -> new IllegalArgumentException("Enclosure was not found: " + animal.getEnclosureID()));
        if (!enclosure.getHabitatType().equalsIgnoreCase(animal.getHabitatType())) {
            throw new IllegalArgumentException("Animal habitat does not match enclosure habitat.");
        }
        int currentOccupancy = animalRepository.countByEnclosure(enclosure.getEnclosureID());
        if (animal.getAnimalID() > 0) {
            AnimalRecord existingAnimal = animalRepository.findById(animal.getAnimalID()).orElse(null);
            if (existingAnimal != null && animal.getEnclosureID().equals(existingAnimal.getEnclosureID())) {
                currentOccupancy--;
            }
        }
        if (currentOccupancy >= enclosure.getCapacity()) {
            throw new IllegalStateException("Enclosure is already at full capacity.");
        }
    }

    private boolean isBlank(String value) {
        return value == null || value.isBlank();
    }
}
