package com.example.annotation;


//import javax.annotation.processing.AbstractProcessor;

import com.google.auto.service.AutoService;

import java.io.IOException;
import java.io.Writer;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;
import javax.tools.Diagnostic;
import javax.tools.JavaFileObject;

/**
 * Created by Longwj on 2017/7/11.
 */
@AutoService(Processor.class)
@SupportedAnnotationTypes("com.example.annotation.Person")
@SupportedSourceVersion(SourceVersion.RELEASE_7)
public class AutoProcessor extends AbstractProcessor {
    private Types mTypeUtils;
    private Elements mElementUtils;
    private Filer mFiler;
    private Messager mMessager;
    @Override
    public synchronized void init(ProcessingEnvironment processingEnvironment) {
        super.init(processingEnvironment);

        mTypeUtils = processingEnv.getTypeUtils();
        mElementUtils = processingEnv.getElementUtils();
        mFiler = processingEnv.getFiler();
        mMessager = processingEnv.getMessager();
    }
    @Override
    public Set<String> getSupportedAnnotationTypes()
    {

        Set<String> annotations = new LinkedHashSet<>();
        annotations.add(Person.class.getCanonicalName());
        return annotations;
    }


    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
//        if (set.isEmpty()) {
//            return false;
//        }
        for (Element element : roundEnvironment.getElementsAnnotatedWith(Person.class)) {

            if (element.getKind() != ElementKind.CLASS) {
                error(element, "Only classes can be annotated with @%s",
                        Person.class.getSimpleName());
                return true;
            }

            analysisAnnotated(element);
        }
        return true;
    }
    private void error(Element e, String msg, Object... args) {
        mMessager.printMessage(
                Diagnostic.Kind.ERROR,
                String.format(msg, args),
                e);
    }
    private static final String SUFFIX = "IPerson";

    private void analysisAnnotated(Element classElement)
    {
        Person annotation = classElement.getAnnotation(Person.class);
        String name = annotation.name();
        int age = annotation.age();

//        TypeElement superClassName = mElementUtils.getTypeElement(name);
        String newClassName = SUFFIX;

        StringBuilder builder = new StringBuilder()
                .append("package com.sicheng.processor.myperson;\n\n")
                .append("public class ")
                .append(newClassName)
                .append(" {\n\n") // open class
                .append("\tpublic String getMessage() {\n") // open method
                .append("\t\treturn \"");

        // this is appending to the return statement
        builder.append("age:" + age).append("name" + name).append(" !\\n");


        builder.append("\";\n") // end return
                .append("\t}\n") // close method
                .append("}\n"); // close class


        try { // write the file
            JavaFileObject source = mFiler.createSourceFile("com.sicheng.processor.myperson."+newClassName);
            Writer writer = source.openWriter();
            writer.write(builder.toString());
            writer.flush();
            writer.close();
        } catch (IOException e) {
            // Note: calling e.printStackTrace() will print IO errors
            // that occur from the file already existing after its first run, this is normal
        }
    }


}
