package ir.sheikh.javanote.service;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jdk.jshell.JShell;
import jdk.jshell.Snippet;
import jdk.jshell.SnippetEvent;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CodeExecutorService {
    private JShell jShell;

    @PostConstruct
    public void init() {
        this.jShell = JShell.create();
    }

    @PreDestroy
    public void close() {
        jShell.close();
    }

    public String compileAndRun(String code) {
        // ذخیره‌سازی خروجی
        String result = null;

        // اجرای کد
        List<SnippetEvent> events = jShell.eval(code);
        for (SnippetEvent event : events) {
            if (event.status() == Snippet.Status.VALID) {
                Snippet.SubKind subKind = event.snippet().subKind();
                result= event.value();
            } else {
                System.out.println("Error run : " + event.snippet().source());
            }
        }

        return result;
    }
}
