package nl.elstarit.documentconversion.api.controller;

import nl.elstarit.documentconversion.api.model.DocumentConversionResponse;
import org.apache.commons.io.FileUtils;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.pdf.PDFParser;
import org.apache.tika.sax.BodyContentHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedInputStream;
import java.io.InputStream;

@Controller
public class DocumentConversionController {


    private static final Logger LOG = LoggerFactory.getLogger(DocumentConversionController.class);

    @RequestMapping("/convert-to-plain-text")
    public ResponseEntity<DocumentConversionResponse> covertToPlainText(@RequestParam("file") MultipartFile file){
        BodyContentHandler handler = new BodyContentHandler();

        AutoDetectParser parser = new AutoDetectParser();
        Metadata metadata = new Metadata();
        ParseContext context = new ParseContext();
        DocumentConversionResponse documentConversionResponse = new DocumentConversionResponse();
        try{
            LOG.info("FILE="+file.getName());
            if(file != null ) {
                InputStream stream = new BufferedInputStream(file.getInputStream());
                parser.parse(stream, handler, metadata, context);
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
