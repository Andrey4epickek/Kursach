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
public class CreditController {
    @Autowired
    private CreditRepo creditRepo;
    @Autowired
    private InfoRepo infoRepo;
    @Autowired
    private AccountRepo accountRepo;

    @GetMapping("/credits/client/{id}/info")
    public String credithome(@PathVariable(value = "id") int id, Model model) {
        if(!infoRepo.existsById(id)){
            return "redirect:/home";
        }

        Iterable<Credit> credits=creditRepo.findAll();
        model.addAttribute("credits",credits);
        return "home-credit";
    }

    @GetMapping("/credits/add")
    public String creditAdd( Model model) {
        return "credit-add";
    }

    @PostMapping("/credits/add")
    public String creditPostAdd( @RequestParam String summa, @RequestParam String currency, @RequestParam String type, @RequestParam String date,@RequestParam String date1,@RequestParam Integer procent, Model model)
    {
        Credit credit=new Credit(summa,currency,type,date,date1,procent);
        creditRepo.save(credit);
        Account account=accountRepo.findById(2).orElseThrow();
        Integer sum=Integer.parseInt(account.getActiv());
        Integer sum1=Integer.parseInt(summa);
        account.setActiv(String.valueOf(sum+sum1));
        accountRepo.save(account);
        return "redirect:/home";
    }

    @GetMapping("/credit/{id}")
    public String infoDetails(@PathVariable(value = "id") int id, Model model) {
        if(!creditRepo.existsById(id)){
            return "redirect:/home";
        }

        Optional<Credit> credit=creditRepo.findById(id);
        ArrayList<Credit> res=new ArrayList<>();
        credit.ifPresent(res::add);
        model.addAttribute("credit",res);
        return "credit-details";
    }

    @GetMapping("/credit/{id}/edit")
    public String creditEdit(@PathVariable(value = "id") int id, Model model) {
        if(!creditRepo.existsById(id)){
            return "redirect:/home";
        }

        Optional<Credit> credit=creditRepo.findById(id);
        ArrayList<Credit> res=new ArrayList<>();
        credit.ifPresent(res::add);
        model.addAttribute("credit",res);
        return "credit-edit";
    }

    @PostMapping("/credit/{id}/edit")
    public String creditPostUpdate(@PathVariable(value = "id") int id,@RequestParam String summa,@RequestParam String currency,@RequestParam String type,@RequestParam String date,@RequestParam String date1,@RequestParam Integer procent, Model model) {
        Credit credit = creditRepo.findById(id).orElseThrow();

        credit.setSumma(summa);
        credit.setCurrency(currency);
        credit.setType(type);
        credit.setDate(date);
        credit.setDate1(date1);
        credit.setProcent(procent);
        creditRepo.save(credit);
        return "redirect:/home";
    }

    @PostMapping("/credit/{id}/remove")
    public String creditPostDelete(@PathVariable(value = "id") int id, Model model){
        Credit credit=creditRepo.findById(id).orElseThrow();
        creditRepo.delete(credit);
        return "redirect:/home";
    }
}
