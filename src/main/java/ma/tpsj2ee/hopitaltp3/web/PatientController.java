package ma.tpsj2ee.hopitaltp3.web;

import lombok.AllArgsConstructor;
import ma.tpsj2ee.hopitaltp3.entities.Patient;
import ma.tpsj2ee.hopitaltp3.repository.PatientRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@AllArgsConstructor
public class PatientController {

    private PatientRepository patientRepository;
    @GetMapping("/index")
    public String index(Model model, @RequestParam(name = "page",defaultValue = "0") int p,
                        @RequestParam(name = "size",defaultValue = "4") int s,
                        @RequestParam(name = "keyword",defaultValue = "") String kw){
        Page<Patient> pagePatients=patientRepository.findByNomContains(kw,PageRequest.of(p,s));//4 patients a partir de la page 0
        model.addAttribute("listPatients",pagePatients.getContent());//affiche une page(liste des patients)
        model.addAttribute("pages",new int [pagePatients.getTotalPages()]);//retourne nb total de pages pour creer la pagination
        model.addAttribute("currentPage",p);
        model.addAttribute("keyword",kw);
        return "patients";
    }
    @GetMapping("/delete")
    public String delete(Long id, String keyword, int page){
        patientRepository.deleteById(id);
        return "redirect:/index?page="+page+"&keyword="+keyword;
        //pour rester sur la meme page :redirect
        //Apres suppression on garde le num de la page et la recherche
    }
}
