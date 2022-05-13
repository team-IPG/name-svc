package org.ipg.namesvc.web;

import org.ipg.namesvc.svc.EmployeeLoadingService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@RestController
public class FileUploadController {

    private final EmployeeLoadingService employeeLoadingService;

    public FileUploadController(EmployeeLoadingService employeeLoadingService) {
        this.employeeLoadingService = employeeLoadingService;
    }

    @PostMapping("/upload")
    public RedirectView handleFileUpload(@RequestParam("file") MultipartFile file,
                                   RedirectAttributes redirectAttributes) {
        employeeLoadingService.processNamesFile(file);
        RedirectView view= new RedirectView("/", false);
        view.addStaticAttribute("statusMessage", "You successfully uploaded " + file.getOriginalFilename() + "!");
        return view;
    }

}
