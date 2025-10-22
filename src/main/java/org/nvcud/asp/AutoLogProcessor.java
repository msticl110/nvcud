package org.nvcud.asp;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.*;
import javax.lang.model.util.Elements;
import java.io.IOException;
import java.io.Writer;
import java.util.Set;

@SupportedAnnotationTypes("org.nvcud.asp.AutoLog")
@SupportedSourceVersion(SourceVersion.RELEASE_8)
public class AutoLogProcessor extends AbstractProcessor {

    private Filer filer;
    private Messager messager;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        filer = processingEnv.getFiler();
        messager = processingEnv.getMessager();
        System.out.println("[AutoLogProcessor] init called");
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        if (annotations.isEmpty()) return false;

        System.out.println("[AutoLogProcessor] process called, elements = " +
                roundEnv.getElementsAnnotatedWith(AutoLog.class).size());

        for (Element element : roundEnv.getElementsAnnotatedWith(AutoLog.class)) {
            if (element.getKind() == ElementKind.METHOD) {
                ExecutableElement method = (ExecutableElement) element;
                TypeElement clazz = (TypeElement) method.getEnclosingElement();
                // 生成一个简单日志包装类
                String className = clazz.getSimpleName() + "_AutoLog";
                String packageName = processingEnv.getElementUtils().getPackageOf(clazz).getQualifiedName().toString();
                String methodName = method.getSimpleName().toString();

                try {
                    Writer writer = filer.createSourceFile(packageName + "." + className).openWriter();
                    writer.write("package " + packageName + ";\n");
                    writer.write("public class " + className + " {\n");
                    writer.write("    public static void log() {\n");
                    writer.write("        System.out.println(\"[AutoLog] Enter method: " + methodName + "\");\n");
                    writer.write("    }\n");
                    writer.write("}\n");
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return true;
    }
}
