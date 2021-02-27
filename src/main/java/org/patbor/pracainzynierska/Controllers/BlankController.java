package org.patbor.pracainzynierska.Controllers;

import org.patbor.pracainzynierska.Models.Blank;
import org.patbor.pracainzynierska.Models.Part;
import org.patbor.pracainzynierska.Service.BlankService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/blank")
public class BlankController {
    BlankService blankService;

    public BlankController(BlankService blankService) {
        this.blankService = blankService;
    }

    @GetMapping("/formBlank")
    public String showBlankForm(Model model) {
        Blank blank = new Blank();
        model.addAttribute("blank", blank);
        return "inz/form/form-blank";
    }

    @PostMapping("/save")
    public String saveBlank(@ModelAttribute("blank") Blank blank) {
        blankService.saveBlank(blank);
        StringBuilder url = new StringBuilder("redirect:/view/detail?id=");
        String idp;
        if (blank.getIdp() == null) {
            idp = blankService.getFinalBlank().getIdp();
        } else {
            idp = blank.getIdp();
        }
        url.append(idp);
        return url.toString();
    }

    @GetMapping("/updateForm")
    public String showFormForUpdate(@RequestParam("blankId") Long id, Model model) {
        Blank blank = blankService.findBlankById(id);
        model.addAttribute("blank", blank);
        return "inz/form/form-blank";
    }

}
