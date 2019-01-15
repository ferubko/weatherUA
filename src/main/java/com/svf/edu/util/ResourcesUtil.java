package com.svf.edu.util;

import com.svf.edu.dto.StaticResourceContent;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.stream.Stream;

/**
 * Created by stepanferubko
 */
public class ResourcesUtil {

    public static Properties loadProperties(Callable<InputStream> isProvider) {
        try (InputStream is = isProvider.call()) {
            Properties properties = new Properties();
            properties.load(is);
            return properties;
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    /**
     * 1. A /config subdirectory of the current directory.
     * 2. The current directory
     * 3. A classpath /config package
     * 4. The classpath root
     *
     * @return not null IS
     * @throws FileNotFoundException - if there is no such file
     */
    public static InputStream getTopConfig(String path, String name, Class type) throws FileNotFoundException, IOException {
        InputStream is = fileToIs(path, name);
        if (is == null) {
            is = fileToIs("", name);
        }
        if (is == null) {
            is = type.getResourceAsStream(path + name);
        } else {
        }
        if (is == null) {
            is = type.getResourceAsStream("/" + name);
        } else {
        }
        if (is == null) {
            throw new FileNotFoundException(name);
        }
        return is;
    }

    private static InputStream fileToIs(String path, String name) throws IOException {
        Path path1 = Paths.get(path, name);
        if (Files.exists(path1)) {
            return Files.newInputStream(path1);
        } else {
            return null;
        }
    }

    public static StaticResourceContent loadResource(String resources, Class type) {
        List<String> lines = loadLinesFromResources(resources, type);
        String version = countResourceContentVersion(lines);
        return new StaticResourceContent(lines, resources, version);
    }

    public static List<String> loadLinesFromResources(String resources, Class type) {
        List<String> lines = new ArrayList<>();
        InputStream is = type.getResourceAsStream(resources);
        if (is == null) {
            return Collections.emptyList();
        }
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
            return lines;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String countResourceContentVersion(List<String> lines) {
        int hashCode = lines.hashCode();
        int size = lines.size();
        return String.format("s%s_h%s", size, hashCode);
    }

    public static List<String> listResourceFiles(String path, Class type) throws IOException {
        List<String> filenames = new ArrayList<>();
        InputStream is = type.getClassLoader().getResourceAsStream(path);
        if (is == null) {
            return Collections.emptyList();
        }
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
            String resource;
            while ((resource = reader.readLine()) != null) {
                filenames.add(resource);
            }
        }
        return filenames;
    }


    public static Stream<Resource> loadResources(String path, Class type) {
        PathMatchingResourcePatternResolver resourceResolver = new PathMatchingResourcePatternResolver(type.getClassLoader());
        return wrapIO(() -> Arrays.stream(resourceResolver.getResources("classpath:" + path + "/**/*")));
    }

    public static String pathFromResource(Resource resource) throws IOException {
        String path = resource.getURI().toString();
        if (path.contains("!")) {
            path = StringUtils.substringAfterLast(path, "!");
        } else if (path.contains("classes")) {
            path = StringUtils.substringAfterLast(path, "classes");
        }
        return path;
    }

    public static <T> T wrapIO(Callable<T> action) {

        try {
            return action.call();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
