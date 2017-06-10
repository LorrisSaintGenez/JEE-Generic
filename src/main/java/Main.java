import Ex2.Entity.*;
import Ex2.Service.ServiceGeneric;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectWriter;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by Lorris on 10/06/2017.
 */
public class Main {

    public static void main(String[] args) {

        Map<String, Class<?>> classMap = new HashMap<>();

        classMap.put("user", User.class);
        classMap.put("file", File.class);
        classMap.put("media", Media.class);
        classMap.put("parameter", Parameter.class);
        classMap.put("profile", Profile.class);
        classMap.put("sharing", Sharing.class);
        classMap.put("value", Value.class);
        classMap.put("php", ProfileHasParameter.class);

        ServiceGeneric serviceGeneric = new ServiceGeneric();

        while(true) {
            Scanner reader = new Scanner(System.in);
            System.out.println("Write an action : ");
            String[] input = reader.nextLine().split("[-\\s]");
            String cmd = input[0].toLowerCase();
            String classAction = input[1].toLowerCase();
            Class cls = classMap.get(classAction);
            switch (cmd) {
                case "list":
                    try {
                        String jsonAction = input[2].toLowerCase();
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
                    } catch (Exception e) {
                        serviceGeneric.listEntity(cls).forEach(System.out::println);
                    }
                    break;
                case "add":
                    Object[] vars = new Object[input.length - 2];
                    Class[] classes = new Class[input.length - 2];

                    for (int i = 2; i < input.length; i++) {
                        vars[i - 2] = input[i];
                        classes[i - 2] = input[i].getClass();
                    }

                    try {
                        Constructor cTor = null;
                        try {
                            cTor = cls.getConstructor(classes);
                            serviceGeneric.createEntity(cTor.newInstance(vars));
                        } catch (NoSuchMethodException e) {
                            e.printStackTrace();
                        }
                    } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
                        e.printStackTrace();
                    }
                    break;
                case "exit":
                    System.exit(0);
                    break;
                default:
                    break;
            }
        }

        /*Class cls = classMap.get("user");
        serviceGeneric.listEntity(cls).forEach(System.out::println);*/

        /*File file = new File();
        file.setFormat("json");
        file.setPath("/bin/lib");
        File file2 = new File();
        file2.setPath("/game/aoe");
        file2.setFormat("exe");

        em.persist(file);
        em.persist(file2);

        Scanner reader = new Scanner(System.in);
        System.out.println("Pick an action : ");
        String input = reader.next();
        String cmd = input.toLowerCase().split("-")[0];
        switch (cmd) {
            case "list":
                if (input.split("-")[2].equals("json")) {
                    ArrayList<Student> students = listEntity(em, Student.class);
                    students.forEach((s) -> {
                        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
                        try {
                            ow.writeValue(System.out, s);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
                } else {
                    ArrayList<File> files = listEntity(em, File.class);
                    files.forEach(System.out::println);
                }
                break;
            case "add":
                System.out.println("Enter : login, password, firstname, lastname and email");
                em.getTransaction().begin();
                Student student = createStudent(reader.next().trim(), reader.next().trim(), reader.next().trim(), reader.next().trim(), reader.next().trim());
                em.persist(student);
                em.getTransaction().commit();
                break;
            default:
                break;
        }

        em.close();
        emf.close();*/
    }
}
