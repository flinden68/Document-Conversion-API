package nl.elstarit.documentconversion.api.controller;

import io.swagger.annotations.*;
import nl.elstarit.documentconversion.api.model.DocumentConversionRequest;
import nl.elstarit.documentconversion.api.model.DocumentConversionResponse;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.nio.charset.StandardCharsets;

@Controller
@RequestMapping("api")
public class DocumentConversionController {


    private static final Logger LOG = LoggerFactory.getLogger(DocumentConversionController.class);

    @ApiOperation(value = "covertToPlainText", nickname = "covertToPlainText", notes="converts the uploaded document to plain text")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = DocumentConversionResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")})
    @RequestMapping(method = RequestMethod.POST, value = "/convert-to-plain-text", produces = "application/json")
    public ResponseEntity<DocumentConversionResponse> covertFileToPlainText(@RequestParam("file") MultipartFile file){
        DocumentConversionResponse documentConversionResponse = null;
        try{
            if(file != null ) {
                InputStream stream = new BufferedInputStream(file.getInputStream());
                documentConversionResponse = convert(stream);
            }else{
                documentConversionResponse = new DocumentConversionResponse("No file found in request");
            }
        }catch(Exception e){
            LOG.error("Error on Document Conversion API: {}", e.getMessage());
        }
        return new ResponseEntity<DocumentConversionResponse>(documentConversionResponse, HttpStatus.OK);
    }

    @ApiOperation(value = "covertBase64EncodedDataToPlainText", nickname = "covertBase64EncodedDataToPlainText", notes="converts the uploaded base64 encoded data to plain text")

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = DocumentConversionResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")})
    @RequestMapping(method = RequestMethod.POST, value = "/convert-data-to-plain-text", consumes="application/json", produces = "application/json")
    public ResponseEntity<DocumentConversionResponse> covertBase64EncodedDataToPlainText(@RequestBody DocumentConversionRequest request){

        DocumentConversionResponse documentConversionResponse = null;
        try{
            if(request.getData() != null ) {
                InputStream stream = new ByteArrayInputStream(Base64.decodeBase64(request.getData()));
                documentConversionResponse = convert(stream);
            }else{
                documentConversionResponse = new DocumentConversionResponse("No data found in request");
            }
        }catch(Exception e){
            LOG.error("Error on Document Conversion API: {}", e.getMessage());
        }
        return new ResponseEntity<DocumentConversionResponse>(documentConversionResponse, HttpStatus.OK);
    }


    private DocumentConversionResponse convert(InputStream stream){
        BodyContentHandler handler = new BodyContentHandler();

        AutoDetectParser parser = new AutoDetectParser();
        Metadata metadata = new Metadata();
        ParseContext context = new ParseContext();
        DocumentConversionResponse documentConversionResponse = new DocumentConversionResponse();
        try{
            parser.parse(stream, handler, metadata, context);
            documentConversionResponse.setConvertedText(handler.toString());

        }catch(Exception e){
            LOG.error("Error on Document Conversion API: {}", e.getMessage());
            documentConversionResponse.setMessage("Error on Document Conversion API: " + e.getMessage() );
        }
        return documentConversionResponse;
    }
}
