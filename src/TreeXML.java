import java.util.*;
import java.io.File;

public class TreeXML {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the path: ");
        String directorypath = sc.nextLine();
        sc.close();
        
        File directory = new File(directorypath);
        if (!directory.exists() || !directory.isDirectory()) {
            System.out.println("The directory does not exist.");
            return;
        }
        StringBuilder xml = new StringBuilder();
        xml.append("<").append(directory.getName()).append(">\n");
        listFile(directory, xml, 1);
        xml.append("</").append(directory.getName()).append(">");
        System.out.println(xml.toString());
    }

    public static void listFile(File directory, StringBuilder xml, int depth) {
        if (directory.isDirectory()) {
            File[] files = directory.listFiles();
            for (File file : files) {
                if (file.isDirectory()) {
                    xml.append(getIndent(depth)).append("<folder>").append(file.getName()).append("</folder>\n");
                    listFile(file, xml, depth + 1);
                } 
                else {
                    xml.append(getIndent(depth)).append("<file>").append(file.getName()).append("</file>\n");
                }
            }
        }
    }
    public static String getIndent(int depth) {
        StringBuilder indent = new StringBuilder();
        for (int i = 0; i < depth; i++) {
            indent.append("    "); 
        }
        return indent.toString();
    }
}
