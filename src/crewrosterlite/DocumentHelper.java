/*
 * Dreamt, Designed and Developed by Finn Stainton    2020.
 */
package crewrosterlite;

import java.util.ArrayList;
import java.util.Collection;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;

/**
 * Helps in the extraction of XML files
 * @author finnstainton
 */
public class DocumentHelper {
       
    /**
     * Get first child node of a parentNode with a given name
     * @param parentNode Node parent node
     * @param name String name of node searching for
     * @return Node which appears first and fulfils the given params, or null
     */
    public Node getFirstChildNode(Node parentNode, String name) {
        Node node = null;
        NodeList childNodes = parentNode.getChildNodes();
        
        if (name != null) {
            int nodeCount = 0;
            while(nodeCount < childNodes.getLength() && node == null) {           
                Node childNode = childNodes.item(nodeCount);
                
                if (name.equalsIgnoreCase(childNode.getNodeName())) {
                    node = childNode;
                }
            
                //
                nodeCount++;
            }
        }
        return node;
    }
    
    /**
     * Gets all child nodes of a parentNode with a given name
     * @param parentNode Node parent node
     * @param name String name of nodes searching for
     * @return Collection of Nodes which fulfils the given params, or null
     */
    public Collection<Node> getAllChildNodes(Node parentNode, String name) {
        ArrayList<Node> nodeList = new ArrayList<>();
        NodeList childNodes = parentNode.getChildNodes();
        
        if (name != null) {
            for (int i=0; i<childNodes.getLength(); i++) {
                Node childNode = childNodes.item(i);
                
                if (name.equalsIgnoreCase(childNode.getNodeName())) {
                    nodeList.add(childNode);
                }
            }
        }
        return nodeList;
    }
    
    /**
     * Get the string associated with an attribute of a node
     * @param parentNode Node in which to search
     * @param name String name of inner attribute
     * @return String associated with an attribute of a node
     */
    public String getAttributeString (Node parentNode, String name) {
        String attribute = null;
        if ((parentNode != null) && (name != null)) {
            NamedNodeMap attributeNodes = parentNode.getAttributes();
                
            if (attributeNodes != null) {
            Node attributeNode = attributeNodes.getNamedItem(name);
                    
                if (attributeNode != null) {
                    attribute = attributeNode.getNodeValue();
                }
            }
        }
        return attribute;
    }
    
    /**
     * Get the string content associated with a node
     * @param node Node to get text from
     * @return String text content between tags/ node
     */
    public String getTextContent (Node node) {
        String textContent = "";
        if (node != null) {
            NodeList childNodes = node.getChildNodes();
            
            for(int i=0; i<childNodes.getLength(); i++) {
                Node childNode = childNodes.item(i);
                if (childNode instanceof Text) {
                    textContent += childNode.getNodeValue();
                }
            }
        }
        return textContent;
    }
    
}
