package com.mongodb.week1;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import spark.Spark;

public class HelloWorldSparkFreemarkerStyle {

    public static void main(String[] args) {
        final Configuration configuration = new Configuration();
        configuration.setClassForTemplateLoading(HelloWorldSparkFreemarkerStyle.class, "/");

        Spark.get("/", (request, response) -> {
            StringWriter writer = null;
            try {
                Template helloTemplate = configuration.getTemplate("hello.ftl");
                writer = new StringWriter();
                Map<String, Object> helloMap = new HashMap<>();
                helloMap.put("name", "Freemarker");

                helloTemplate.process(helloMap, writer);
                System.out.println(writer);

            } catch (IOException | TemplateException e) {
                Spark.halt(500);
                e.printStackTrace();
            }

            return writer;
        });
    }

}
