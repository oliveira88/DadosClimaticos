package com.ufes.dadosclimaticos.logger.loggerAdaptado;

import com.ufes.dadosclimaticos.model.DadosClimaticos;
import com.ufes.dadosclimaticos.util.ConvertDate;
import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XmlLoggerAdaptado {

    private final String arquivoPath;

    public XmlLoggerAdaptado(String arquivoPath) {
        this.arquivoPath = arquivoPath;
    }

    public void gravarArquivo(DadosClimaticos dadosClimaticos) throws Exception {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder dc = dbf.newDocumentBuilder();
        Document d = dc.newDocument();
        File file = new File(arquivoPath);
        Element raiz;

        if (file.exists()) {
            d = dc.parse(file);
            raiz = d.getDocumentElement();
        } else {
            d = dc.newDocument();
            raiz = d.createElement("Posts");
            d.appendChild(raiz);
        }

        Element post = d.createElement("post");
        raiz.appendChild(post);

        Element textoRecebido = d.createElement("temperatura");
        textoRecebido.appendChild(d.createTextNode(dadosClimaticos.getTemperatura().toString()));
        post.appendChild(textoRecebido);

        textoRecebido = d.createElement("umidade");
        textoRecebido.appendChild(d.createTextNode(dadosClimaticos.getUmidade().toString()));
        post.appendChild(textoRecebido);

        textoRecebido = d.createElement("presao");
        textoRecebido.appendChild(d.createTextNode(dadosClimaticos.getPresao().toString()));
        post.appendChild(textoRecebido);

        textoRecebido = d.createElement("data");
        textoRecebido.appendChild(d.createTextNode(dadosClimaticos.getData().toString()));
        post.appendChild(textoRecebido);

        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer t = tf.newTransformer();
        DOMSource domSource = new DOMSource(d);
        StreamResult streamResult = new StreamResult(new File(arquivoPath));

        t.transform(domSource, streamResult);

    }
    
    public void removerDadoArquivo(DadosClimaticos dadoARemover) throws Exception {
        File file = new File(arquivoPath);
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = db.parse(file);
        doc.getDocumentElement().normalize();
        NodeList nodeList = doc.getElementsByTagName("post");
        for (int itr = 0; itr < nodeList.getLength(); itr++) {
            Node node = nodeList.item(itr);
            Element eElement = (Element) node;
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                String data = eElement.getElementsByTagName("data").item(0).getTextContent();
                String pressao = eElement.getElementsByTagName("presao").item(0).getTextContent();
                String temperatura = eElement.getElementsByTagName("temperatura").item(0).getTextContent();
                String umidade = eElement.getElementsByTagName("umidade").item(0).getTextContent();
                
                DadosClimaticos dado = new DadosClimaticos(
                    Double.valueOf(temperatura),
                    Double.valueOf(umidade),
                    Double.valueOf(pressao),
                    ConvertDate.stringToLocalDate(data, "yyyy-MM-dd")
                );
                
                if(dado.equals(dadoARemover)) {
                    node.getParentNode().removeChild(node);
                    break;
                }
            }
        }
        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer transformer = tf.newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
        DOMSource domSource = new DOMSource(doc);
        StreamResult streamResult = new StreamResult(new File(arquivoPath));
        transformer.transform(domSource, streamResult);
    }
    
    public List<DadosClimaticos> lersArquivo() throws Exception {
        List<DadosClimaticos> dadosClimaticosList = new ArrayList<>();
        File file = new File(arquivoPath);
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = db.parse(file);
        doc.getDocumentElement().normalize();
        NodeList nodeList = doc.getElementsByTagName("post");
        for (int itr = 0; itr < nodeList.getLength(); itr++) {
            DadosClimaticos dadoClimatico = new DadosClimaticos();
            Node node = nodeList.item(itr);
            Element eElement = (Element) node;
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                dadoClimatico.setData(LocalDate.parse(eElement.getElementsByTagName("data").item(0).getTextContent()));
                dadoClimatico.setPresao(Double.valueOf(eElement.getElementsByTagName("presao").item(0).getTextContent()));
                dadoClimatico.setTemperatura(Double.valueOf(eElement.getElementsByTagName("temperatura").item(0).getTextContent()));
                dadoClimatico.setUmidade(Double.valueOf(eElement.getElementsByTagName("umidade").item(0).getTextContent()));
                dadosClimaticosList.add(dadoClimatico);
            }
        }
        return dadosClimaticosList;
    }
}
