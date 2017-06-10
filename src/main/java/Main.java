import Ex2.Service.ServiceGeneric;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectWriter;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.*;

/**
 * Created by Lorris on 10/06/2017.
 */
public class Main {

    private static List<Class<?>> findInPackage(String packageName) {
        String path = packageName.replace('.', '/');
        URL url = Thread.currentThread().getContextClassLoader().getResource(path);
        if (url == null)
            throw new IllegalArgumentException("Unable to get resources");
        File scannedDir = new File(url.getFile());
        List<Class<?>> classes = new ArrayList<>();
        for (File f : scannedDir.listFiles())
            classes.addAll(find(f, packageName));
        return classes;
    }

    private static List<Class<?>> find(File file, String scannedPackage) {
        List<Class<?>> classes = new ArrayList<>();
        String resource = scannedPackage + '.' + file.getName();
        if (file.isDirectory()) {
            for (File child : file.listFiles()) {
                classes.addAll(find(child, resource));
            }
        } else if (resource.endsWith(".class")) {
            int endIndex = resource.length() - ".class".length();
            String className = resource.substring(0, endIndex);
            try {
                classes.add(Class.forName(className));
            } catch (ClassNotFoundException ignore) {
            }
        }
        return classes;
    }

    private static void AddEntity(String[] input, Class cls, ServiceGeneric serviceGeneric) {
        Object[] vars = new Object[input.length - 2];
        Class[] classes = new Class[input.length - 2];
        for (int i = 2; i < input.length; i++) {
            vars[i - 2] = input[i];
            classes[i - 2] = input[i].getClass();
        }
        try {
            Constructor<?> cTor = cls.getConstructor(classes);
            serviceGeneric.createEntity(cTor.newInstance(vars));
        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    private static void ListEntity(String[] input,  Class cls, ServiceGeneric serviceGeneric) {
        try {
            if (input[2].toLowerCase().equals("json")) {
                ArrayList listEntity = serviceGeneric.listEntity(cls);
                StringBuilder sb = new StringBuilder();
                listEntity.forEach(e -> {
                    ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
                    try {
                        sb.append(ow.writeValueAsString(e));
                    } catch (IOException ioe) {
                        ioe.printStackTrace();
                    }
                });
                System.out.println(sb.toString());
            }
        } catch (Exception e) {
            serviceGeneric.listEntity(cls).forEach(System.out::println);
        }
    }

    public static void main(String[] args) {

        final List<Class<?>> classesForPackage = findInPackage("Ex2.Entity");

        Map<String, Class<?>> classMap = new HashMap<>();
        classesForPackage.forEach(c -> classMap.put(c.getSimpleName().toLowerCase(), c));

        final ServiceGeneric serviceGeneric = new ServiceGeneric();

        while(true) {
            Scanner reader = new Scanner(System.in);
            System.out.println("You can : ");
            System.out.println("list-\'model\' : Will list all entity of the requested model");
            System.out.println("list-\'model\'-json : will list all entity of the requested model (JSON Format)");
            System.out.println("add-\'model\' $param... : will create an entity of the model if all params fit the constructor");
            System.out.println("exit : Will exit the program");
            System.out.print("Choose your action : ");
            String[] input = reader.nextLine().split("[-\\s]");
            String cmd = input[0].toLowerCase();
            switch (cmd) {
                case "list":
                    String classAction = input[1].toLowerCase();
                    Class cls = classMap.get(classAction);
                    ListEntity(input, cls, serviceGeneric);
                    break;
                case "add":
                    String classString = input[1].toLowerCase();
                    Class classFromMap = classMap.get(classString);
                    AddEntity(input, classFromMap, serviceGeneric);
                    break;
                case "exit":
                    System.exit(0);
                    break;
                default:
                    break;
            }
            System.out.println("============= DONE =============");
        }
    }
}
