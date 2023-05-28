package com.ufes.dadosclimaticos.logger.loggerAdaptado;

import com.ufes.dadosclimaticos.model.DadosClimaticos;
import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
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

        textoRecebido = d.createElement("umidadae");
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
                dadoClimatico.setUmidade(Double.valueOf(eElement.getElementsByTagName("umidadae").item(0).getTextContent()));
                dadosClimaticosList.add(dadoClimatico);
            }
        }
        return dadosClimaticosList;
    }
}
