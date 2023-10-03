package edu.miu.cs.cs489appsd.lab1a.productmgmtapp;


import com.google.gson.Gson;
import edu.miu.cs.cs489appsd.lab1a.productmgmtapp.model.Product;
import edu.miu.cs.cs489appsd.lab1a.productmgmtapp.util.DateUtil;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.StringWriter;
import java.util.Arrays;
import java.util.Comparator;
public class ProductMgmtApp {
    public static void main(String[] args) {

        Product[] products = {
                new Product(3128874119L, "Banana", DateUtil.parseDate("2023-01-24"), 124, 0.55),
                new Product(2927458265L, "Apple", DateUtil.parseDate("2022-12-09"), 18, 1.09),
                new Product(9189927460L, "Carrot", DateUtil.parseDate("2023-03-31"), 89, 2.99)
        };

        // Sort products by name in ascending order
        Arrays.sort(products, Comparator.comparing(Product::getName));

        // Print products in JSON, XML, and CSV formats
        printProducts(products, "JSON");
        printProducts(products, "XML");
        printProducts(products, "CSV");
    }

    public static void printProducts(Product[] products, String format) {
        switch (format) {
            case "JSON":
                System.out.println("Printing products in JSON format:");
                // Implement JSON printing logic


               Gson gson = new Gson();
                String json = gson.toJson(products);
                System.out.println(json);


                break;
            case "XML":
                System.out.println("Printing products in XML format:");
                // Implement XML printing logic
                try {
                    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                    DocumentBuilder builder = factory.newDocumentBuilder();
                    Document doc = builder.newDocument();

                    Element root = doc.createElement("products");
                    doc.appendChild(root);

                    for (Product product : products) {
                        Element productElement = doc.createElement("product");
                        root.appendChild(productElement);

                        Element productId = doc.createElement("productId");
                        productId.appendChild(doc.createTextNode(String.valueOf(product.getProductId())));
                        productElement.appendChild(productId);

                        // Add more elements for other attributes

                    }

                    TransformerFactory transformerFactory = TransformerFactory.newInstance();
                    Transformer transformer = transformerFactory.newTransformer();
                    transformer.setOutputProperty(OutputKeys.INDENT, "yes");

                    StringWriter writer = new StringWriter();
                    transformer.transform(new DOMSource(doc), new StreamResult(writer));
                    System.out.println(writer.toString());
                } catch (ParserConfigurationException | TransformerException e) {
                    e.printStackTrace();
                }

                break;
            case "CSV":
                System.out.println("Printing products in CSV format:");
                // Implement CSV printing logic
                for (Product product : products) {
                    System.out.println(
                            product.getProductId() + "," +
                                    product.getName() + "," +
                                    DateUtil.formatDate(product.getDateSupplied()) + "," +
                                    product.getQuantityInStock() + "," +
                                    product.getUnitPrice()
                    );
                }
                break;
            default:
                System.out.println("Unsupported format: " + format);
        }
    }
}