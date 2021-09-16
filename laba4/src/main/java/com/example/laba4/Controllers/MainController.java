package com.example.laba4.Controllers;

import com.example.laba4.models.Account;
import com.example.laba4.models.Credit;
import com.example.laba4.models.Info;
import com.example.laba4.repo.AccountRepo;
import com.example.laba4.repo.CreditRepo;
import com.example.laba4.repo.InfoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.ArrayList;
import java.util.Optional;

@Controller
/**
*Идентифицируем контроллр по аннотации.
 **/
public class MainController {

    @Autowired
    private InfoRepo infoRepo;
    @Autowired
    private AccountRepo accountRepo;
    long number = (long) Math.floor(Math.random() * 9000000000000L) + 1000000000000L;
    String nomer=String.valueOf(number);
    String activ="0";


    @GetMapping("/home")
    /**
     *Аннотация гарантирует , что HTTP GET запрашивает /home, который направялет в метод home().
     **/
    public String home( Model model) {
        Iterable<Info> infos=infoRepo.findAll();
        /**
         *Получаем все записи от БД.
         **/
        model.addAttribute("infos",infos);
        return "home";
        /**
         *Возвращает пользователю нужную html страницу.
         **/
    }

    @GetMapping("/home/add")
    public String infoAdd( Model model) {
        return "home-add";
    }

    @PostMapping("/home/add")
    public String infoPostAdd(@RequestParam String name,@RequestParam String surname,@RequestParam String patronymic,@RequestParam String pol,@RequestParam String seriapasporta,
                              @RequestParam String nomerpasporta,@RequestParam String kemvidan,@RequestParam String identnomer,@RequestParam String placeofbirth,@RequestParam String gorodprop,
                              @RequestParam String adres,@RequestParam String gorodprog,@RequestParam String sempologenie,@RequestParam String gragdanstvo,@RequestParam String invalid,
                              @RequestParam String pensioner,@RequestParam String voen,@RequestParam String dohod,@RequestParam String birth,@RequestParam String datavidachi, Model model)
    {
        /**
         * @RequestParam связывает значение параметра строки запроса surname с surname параметром метода infoPostAdd.
         **/
        Info info=new Info(name,surname,patronymic,pol,seriapasporta,nomerpasporta,kemvidan,identnomer,placeofbirth,gorodprog,adres,gorodprop,sempologenie,gragdanstvo,invalid,pensioner,voen,dohod,birth,datavidachi);
        infoRepo.save(info);
        Account account=new Account(nomer,activ,name,surname,patronymic);
        accountRepo.save(account);
        return "redirect:/home";
    }

    @GetMapping("/home/{id}")
    public String infoDetails(@PathVariable(value = "id") int id, Model model) {
        if(!infoRepo.existsById(id)){
            return "redirect:/home";
        }
        /**
         *Страница записи определяется по id.
         **/
        Optional<Info> info=infoRepo.findById(id);
        ArrayList<Info> res=new ArrayList<>();
        info.ifPresent(res::add);
        model.addAttribute("info",res);
        return "home-details";
    }

    @GetMapping("/home/{id}/edit")
    public String infoEdit(@PathVariable(value = "id") int id, Model model) {
        if(!infoRepo.existsById(id)){
            return "redirect:/home";
        }

        Optional<Info> info=infoRepo.findById(id);
        ArrayList<Info> res=new ArrayList<>();
        info.ifPresent(res::add);
        model.addAttribute("info",res);
        return "home-edit";
    }

    @PostMapping("/home/{id}/edit")
    public String infoPostUpdate(@PathVariable(value = "id") int id,@RequestParam String name,@RequestParam String surname,@RequestParam String patronymic,@RequestParam String pol,@RequestParam String seriapasporta,
                                 @RequestParam String nomerpasporta,@RequestParam String kemvidan,@RequestParam String identnomer,@RequestParam String placeofbirth,@RequestParam String gorodprop,
                                 @RequestParam String adres,@RequestParam String gorodprog,@RequestParam String sempologenie,@RequestParam String gragdanstvo,@RequestParam String invalid,
                                 @RequestParam String pensioner,@RequestParam String voen,@RequestParam String dohod,@RequestParam String birth,@RequestParam String datavidachi, Model model) {
        Info info = infoRepo.findById(id).orElseThrow();
        /**
         *Получаем запись по id и записываем новые значения.
         **/
        info.setName(name);
        info.setSurname(surname);
        info.setPatronymic(patronymic);
        info.setPol(pol);
        info.setSeriapasporta(seriapasporta);
        info.setNomerpasporta(nomerpasporta);
        info.setKemvidan(kemvidan);
        info.setIdentnome(identnomer);
        info.setPlaceofbirth(placeofbirth);
        info.setGorodprog(gorodprog);
        info.setAdres(adres);
        info.setGorodprop(gorodprop);
        info.setSempologenie(sempologenie);
        info.setGragdanstvo(gragdanstvo);
        info.setInvalid(invalid);
        info.setPensioner(pensioner);
        info.setVoen(voen);
        info.setDohod(dohod);
        info.setBirth(birth);
        info.setDatavidachi(datavidachi);
        infoRepo.save(info);
        /**
         *Метод infoPostUpdate перезапишет данные записи.
         **/
        return "redirect:/home";
    }
    @PostMapping("/home/{id}/remove")
    public String infoPostDelete(@PathVariable(value = "id") int id, Model model){
        Info info=infoRepo.findById(id).orElseThrow();
        infoRepo.delete(info);
        /**
         *Метод infoPostDelete удаляет запись.
         **/
        return "redirect:/home";
    }
}