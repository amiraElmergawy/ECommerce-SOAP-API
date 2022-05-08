package gov.iti.jets.service.servicesImpl;

import java.util.Set;

import javax.xml.namespace.QName;

import jakarta.xml.soap.SOAPMessage;
import jakarta.xml.ws.handler.MessageContext;
import jakarta.xml.ws.handler.soap.SOAPHandler;
import jakarta.xml.ws.handler.soap.SOAPMessageContext;

public class AuthHandler implements SOAPHandler<SOAPMessageContext> {

    @Override
    public boolean handleMessage(SOAPMessageContext context) {
        Boolean outbound = (Boolean) context.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);
        SOAPMessage message = context.getMessage();

        try {
            if (outbound) {
                System.out.println(message);
                System.out.println("Sending message:");
                message.writeTo(System.out);
                String account = message.getSOAPBody().getFirstChild().getLastChild().getTextContent();
                System.out.println(account);
                message.getSOAPHeader().addTextNode(account);
            } else {
                Integer responseCode = (Integer) context.get(MessageContext.HTTP_RESPONSE_CODE);
                System.out.println("Received " + responseCode + "response:");
                message.writeTo(System.out);
            }
            System.out.println();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return true;
    }

    @Override
    public boolean handleFault(SOAPMessageContext context) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void close(MessageContext context) {
        // TODO Auto-generated method stub
    }

    @Override
    public Set<QName> getHeaders() {
        // TODO Auto-generated method stub
        return null;
    }

}
