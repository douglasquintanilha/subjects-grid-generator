package br.com.unirio.renderers;

import com.google.common.collect.Maps;
import com.hubspot.jinjava.Jinjava;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class TemplateRenderer {

    public String templatePath;

    public TemplateRenderer(String templatePath) {
        this.templatePath = templatePath;
    }

    public void renderTemplate(Map<String, Object> context){
        Jinjava jinjava = new Jinjava();

        File file = new File(this.templatePath);
        String template = null;
        try {
            template = FileUtils.readFileToString(file, "UTF-8");
        } catch (IOException e) {
            System.out.println("Failed to open file: " + this.templatePath);
            e.printStackTrace();
        }
        String renderedTemplate = jinjava.render(template, context);
        String outputFile = "files/output.html";
        try {
            FileUtils.writeStringToFile(new File("files/output.html"), renderedTemplate, "UTF-8");
        } catch (IOException e) {
            System.out.println("Failed to write to file: " +  outputFile);
            e.printStackTrace();
        }


    }

}
