package nl.elstarit.documentconversion.api.controller;

import nl.elstarit.documentconversion.api.model.DocumentConversionRequest;
import nl.elstarit.documentconversion.api.model.DocumentConversionResponse;
import org.apache.commons.io.FileUtils;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.sax.BodyContentHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;

@Controller
public class DocumentConversionController {


    private static final Logger LOG = LoggerFactory.getLogger(DocumentConversionController.class);

    @RequestMapping("/convert-to-plain-text")
    public ResponseEntity<DocumentConversionResponse> covertToPlainText(@RequestBody DocumentConversionRequest documentConversionRequest){
        BodyContentHandler handler = new BodyContentHandler();

        AutoDetectParser parser = new AutoDetectParser();
        Metadata metadata = new Metadata();
        DocumentConversionResponse documentConversionResponse = new DocumentConversionResponse();
        try{
            if(documentConversionRequest.getFile() != null ) {
                InputStream stream = FileUtils.openInputStream(documentConversionRequest.getFile());
                parser.parse(stream, handler, metadata);
                documentConversionResponse.setConvertedText(handler.toString());
            }else{
                documentConversionResponse.setMessage("No File found in request");
            }
        }catch(Exception e){
            LOG.error("Error on Document Conversion API: {}", e.getMessage());
        }
        return new ResponseEntity<DocumentConversionResponse>(documentConversionResponse, HttpStatus.OK);
    }

}
