import java.io.File;
import java.util.Vector;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

class Student {
    String ten;
    int tuoi;
    double diemtb;

    public Student(String ten, int tuoi, double diemtb) {
        this.ten = ten;
        this.tuoi = tuoi;
        this.diemtb = diemtb;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public int getTuoi() {
        return tuoi;
    }

    public void setTuoi(int tuoi) {
        this.tuoi = tuoi;
    }

    public double getDiemtb() {
        return diemtb;
    }

    public void setDiemtb(double diemtb) {
        this.diemtb = diemtb;
    }
}

public class Student_XML {
    public static void main(String[] args) {
        Vector<Student> v = new Vector<Student>();
        v.add(new Student("Doan Kim Oanh", 20, 9.2));
        v.add(new Student("Nguyen Tung Lam", 19, 9.5));
        v.add(new Student("Vo Tien Phuc", 19, 7.5));

        String xmlpath = "D:\\student.xml";
        createXML(v, xmlpath);
    }

    public static void createXML(Vector<Student> students, String xmlpath) {
        try {
            DocumentBuilderFactory docfac = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = docfac.newDocumentBuilder();

            Document doc = db.newDocument();
            Element root = doc.createElement("sinhvien");
            doc.appendChild(root);

            for (Student student : students) {
                Element st = doc.createElement("Sinh_viên");
                root.appendChild(st);

                Element ten = doc.createElement("Tên");
                ten.appendChild(doc.createTextNode(student.getTen()));
                st.appendChild(ten);

                Element tuoi = doc.createElement("Tuổi");
                tuoi.appendChild(doc.createTextNode(student.getTuoi() + ""));
                st.appendChild(tuoi);

                Element diemtb = doc.createElement("Điểm_trung_bình");
                diemtb.appendChild(doc.createTextNode(student.getDiemtb() + ""));
                st.appendChild(diemtb);
            }
            TransformerFactory trans = TransformerFactory.newInstance();
            Transformer transformer = trans.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource domsource = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(xmlpath));
            transformer.transform(domsource, result);

            System.out.println("Tạo file thành công");
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }
}
