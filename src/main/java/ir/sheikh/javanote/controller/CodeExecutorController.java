package ir.sheikh.javanote.controller;

import ir.sheikh.javanote.service.CodeExecutorService;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jdk.jshell.JShell;
import jdk.jshell.Snippet;
import jdk.jshell.SnippetEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class CodeExecutorController {
    private final CodeExecutorService codeExecutorService;

    public CodeExecutorController(CodeExecutorService codeExecutorService) {
        this.codeExecutorService = codeExecutorService;
    }

    @GetMapping({"/","/execute"})
    public String index() {
        return "index";
    }

    @PostMapping("/execute")
    public String execute(@RequestParam("code") String code, Model model) {
        String result = codeExecutorService.compileAndRun(code);
        model.addAttribute("output", result);
        return "index";
    }


}
