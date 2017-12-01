package nl.elstarit.documentconversion.api.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DocumentConversionControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Test
    public void covertFileToPlainText() throws Exception {

        MockMultipartFile firstFile = new MockMultipartFile("file", "filename.pdf", "text/plain", "some xml".getBytes());

        MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        mockMvc.perform(MockMvcRequestBuilders.fileUpload("/api/convert-to-plain-text")
                .file(firstFile))
                .andExpect(status().is(200));
    }

    @Test
    public void covertBase64EncodedDataToPlainText() throws Exception {
        String jsonRequest = "{\n" +
                "    \"mediaType\": \"text\",\n" +
                "    \"data\": \"base64 encoded representation of text/file\"\n" +
                "}\n";
        MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        mockMvc.perform(
                post("/api/convert-data-to-plain-text").contentType(
                        MediaType.APPLICATION_JSON).content(jsonRequest))
                .andExpect(status().isOk());
    }

}