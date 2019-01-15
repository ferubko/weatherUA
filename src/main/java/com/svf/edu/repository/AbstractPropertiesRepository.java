package com.svf.edu.repository;

import com.svf.edu.util.ResourcesUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

/**
 * Created by stepanferubko
 */
public class AbstractPropertiesRepository {
    protected Properties load(String path, String name) {
        return ResourcesUtil.loadProperties(() -> ResourcesUtil.getTopConfig(path, name, getClass()));
    }

    protected Properties remove(String path, String property, String name) {
        Properties all = load(path, name);
        Properties removed = new Properties();
        if (all.containsKey(property)) {
            all.remove(property);
            write(all, path, name);
        }
        return removed;
    }

    protected void write(Properties properties, String pathString, String name) {
        try {
            Path path = getDestinationPath(pathString, name);
            makeBackup(name, pathString, path);
            properties.store(Files.newOutputStream(path, StandardOpenOption.CREATE), null);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Path getDestinationPath(String path, String name) throws IOException {
        Path rootPath = Paths.get(path);
        if (!Files.exists(rootPath)) {
            Files.createDirectory(rootPath);
        }
        return Paths.get(path, name);
    }

    private void makeBackup(String name, String pathString, Path path) throws IOException {
        if (Files.exists(path)) {
            String backupName = String.format("%s.%s", name, new SimpleDateFormat("ddMMyyyy_HHmmss").format(new Date()));
            Files.move(path, getDestinationPath(pathString, backupName));
        }
    }
}
