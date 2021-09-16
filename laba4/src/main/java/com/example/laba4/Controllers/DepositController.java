package com.example.laba4.Controllers;


import com.example.laba4.models.Account;
import com.example.laba4.models.Deposit;
import com.example.laba4.repo.AccountRepo;
import com.example.laba4.repo.DepositRepo;
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
public class DepositController {
    @Autowired
    private DepositRepo depositRepo;
    @Autowired
    private InfoRepo infoRepo;
    @Autowired
    private AccountRepo accountRepo;
    @GetMapping("/deposits/client/{id}/info")
    public String deposithome(@PathVariable(value = "id") int idDeposit, Model model) {
        if(!infoRepo.existsById(idDeposit)){
            return "redirect:/home";
        }

        Iterable<Deposit> deposits=depositRepo.findAll();
        model.addAttribute("deposits",deposits);
        return "home-deposit";
    }

    @GetMapping("/deposits/add")
    public String depositAdd( Model model) {
        return "deposit-add";
    }

    @PostMapping("/deposits/add")
    public String creditPostAdd(@RequestParam String summa, @RequestParam String currency, @RequestParam String type, @RequestParam String date,@RequestParam String date1,@RequestParam Integer procent, Model model)
    {
        Deposit deposit=new Deposit(summa,currency,type,date,date1,procent);
        depositRepo.save(deposit);
        Account account=accountRepo.findById(2).orElseThrow();
        Integer sum=Integer.parseInt(account.getActiv());
        Integer sum1=Integer.parseInt(summa);
        account.setActiv(String.valueOf(sum+sum1));
        accountRepo.save(account);
        return "redirect:/home";
    }

    @GetMapping("/deposit/{id}")
    public String depositDetails(@PathVariable(value = "id") int idDeposit, Model model) {
        if(!depositRepo.existsById(idDeposit)){
            return "redirect:/home";
        }

        Optional<Deposit> deposit=depositRepo.findById(idDeposit);
        ArrayList<Deposit> res=new ArrayList<>();
        deposit.ifPresent(res::add);
        model.addAttribute("deposit",res);
        return "deposit-details";
    }

    @GetMapping("/deposit/{id}/edit")
    public String depositEdit(@PathVariable(value = "id") int idDeposit, Model model) {
        if(!depositRepo.existsById(idDeposit)){
            return "redirect:/home";
        }

        Optional<Deposit> deposit=depositRepo.findById(idDeposit);
        ArrayList<Deposit> res=new ArrayList<>();
        deposit.ifPresent(res::add);
        model.addAttribute("deposit",res);
        return "deposit-edit";
    }

    @PostMapping("/deposit/{id}/edit")
    public String depositPostUpdate(@PathVariable(value = "id") int idDeposit,@RequestParam String summa,@RequestParam String currency,@RequestParam String type,@RequestParam String date,@RequestParam String date1,@RequestParam Integer procent, Model model) {
        Deposit deposit = depositRepo.findById(idDeposit).orElseThrow();

        deposit.setSumma(summa);
        deposit.setCurrency(currency);
        deposit.setType(type);
        deposit.setDate(date);
        deposit.setDate1(date1);
        deposit.setProcent(procent);
        depositRepo.save(deposit);
        return "redirect:/home";
    }

    @PostMapping("/deposit/{id}/remove")
    public String depositPostDelete(@PathVariable(value = "id") int idDeposit, Model model){
        Deposit deposit=depositRepo.findById(idDeposit).orElseThrow();
        depositRepo.delete(deposit);
        return "redirect:/home";
    }
}
