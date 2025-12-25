package assignment.wif3006cbse.shared.spi;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

public abstract class FileBasedRepository<T extends Serializable, ID> implements
    CrudRepository<T, ID> {

    private final Map<ID, T> store = new ConcurrentHashMap<>();
    private final String fileName;
    private final Function<T, ID> idExtractor;

    protected FileBasedRepository(String fileName, Function<T, ID> idExtractor) {
        this.fileName = fileName;
        this.idExtractor = idExtractor;
        load();
    }

    @Override
    public T save(T entity) {
        ID id = idExtractor.apply(entity);
        if (id == null) {
            throw new IllegalArgumentException("Entity ID cannot be null");
        }
        store.put(id, entity);
        persist();
        return entity;
    }

    @Override
    public Optional<T> findById(ID id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public List<T> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public void deleteById(ID id) {
        store.remove(id);
        persist();
    }

    protected Map<ID, T> getStore() {
        return store;
    }

    @SuppressWarnings("unchecked")
    private void load() {
        Path path = getFilePath();
        if (Files.exists(path)) {
            try (ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream(path.toFile()))) {
                Map<ID, T> loadedData = (Map<ID, T>) ois.readObject();
                store.putAll(loadedData);
                System.out.println("Loaded " + store.size() + " records from " + path);
            } catch (Exception e) {
                System.err.println("Failed to load data from " + path + ": " + e.getMessage());
            }
        }
    }

    private void persist() {
        Path path = getFilePath();
        try {
            if (path.getParent() != null) {
                Files.createDirectories(path.getParent());
            }
            try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream(path.toFile()))) {
                oos.writeObject(new ConcurrentHashMap<>(store)); // Write a snapshot
            }
        } catch (IOException e) {
            System.err.println("Failed to persist data to " + path + ": " + e.getMessage());
        }
    }

    private Path getFilePath() {
        // Use user.home to ensure write permissions and survival across restarts
        return Paths.get(System.getProperty("user.home"), ".wif3006-cbse-data", fileName);
    }
}
