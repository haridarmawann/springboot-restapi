package hari.darmawan.core.storage;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.stream.Stream;

@Service
public class StorageServiceImpl implements StorageService {

    private final Path root = Paths.get("uploads");

    private String filename;
    private String filepath;


    public void setFilename(String filename) {
        this.filename = filename;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    @Override
    public void saveFile(MultipartFile file, String filename, String filepath) {
        setFilename(filename); // diset
        setFilepath(filepath);
        this.init();
        this.store(file);
    }
    @Override
    public void store(MultipartFile file) {
        try {
            if (file.isEmpty()) {
                throw new StorageException("Failed to store empty file.");
            }

            Path folder = Path.of(root  + this.filepath);
            Path destinationFile = folder.resolve(Paths.get(filename)).normalize().toAbsolutePath();

            if (!destinationFile.getParent().equals(folder.toAbsolutePath())) {
                // This is a security check
                throw new StorageException("Cannot store file outside current directory.");
            }

            try (InputStream inputStream = file.getInputStream())
            {
                Files.copy(inputStream, destinationFile, StandardCopyOption.REPLACE_EXISTING);
            }
        }
        catch (IOException e) {
            System.out.println("pesan error : "+e.getMessage());
            throw new StorageException("Failed to store file.", e);
        }
    }

    @Override
    public Stream<Path> loadAll() {
        try {
            return Files.walk(this.root, 1).
                    filter(path -> !path.equals(this.root)).
                    map(this.root::relativize);
        } catch (IOException e) {
            throw new StorageException("Could not load the files!");
        }
    }


    @Override
    public Resource loadAsResource(String filename) {
        try {
            Path file = root.resolve(filename);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            }
            else {
                throw new RuntimeException("Could not read file: " + filename);
            }
        }
        catch (MalformedURLException e) {
            throw new RuntimeException("Could not read file: " + filename, e);
        }
    }
    @Override
    public void  deleteFile(String filepath) throws IOException {
        Path fileToDeletePath = Paths.get(filepath);
        Files.delete(fileToDeletePath);
    }

    @Override
    public void init() {
        try {
            Files.createDirectories(Path.of(root + this.filepath));
        }
        catch (IOException e) {
            throw new StorageException("Could not initialize storage", e);
        }
    }






}
