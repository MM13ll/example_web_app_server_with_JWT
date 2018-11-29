package uz.course.config;

import org.apache.catalina.connector.Connector;
import org.apache.coyote.http11.AbstractHttp11Protocol;
import org.springframework.boot.web.embedded.tomcat.TomcatConnectorCustomizer;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

@Component
public class TomcatCustomizer implements TomcatConnectorCustomizer {

    @Override
    public void customize(Connector connector) {
//        connector.setProperty("compression", "on");
        // Add json and xml mime types, as they're not in the mimetype list by default
//        connector.setProperty("compressableMimeType", "text/html,text/xml,text/plain,application/json,application/xml");


        AbstractHttp11Protocol httpProtocol = (AbstractHttp11Protocol) connector.getProtocolHandler();
        httpProtocol.setCompression("on");
        httpProtocol.setCompressionMinSize(256);
        String[] mimeTypes = httpProtocol.getCompressibleMimeTypes();
        String mimeTypesWithJson = mimeTypes + "," + MediaType.APPLICATION_JSON_VALUE;
        httpProtocol.setCompressibleMimeType(mimeTypesWithJson);

    }
}
