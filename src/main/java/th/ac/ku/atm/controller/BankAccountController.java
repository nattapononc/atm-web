package th.ac.ku.atm.controller;

import th.ac.ku.atm.service.BankAccountService;
import th.ac.ku.atm.model.BankAccount;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/bankaccount")
public class BankAccountController {

    private BankAccountService bankAccountService;

    public BankAccountController(BankAccountService bankAccountService) {
        this.bankAccountService = bankAccountService;
    }

    @GetMapping
    public String getBankAccountPage(Model model){
        model.addAttribute("allBankAccount",bankAccountService.getBankAccount());
        return "bankaccount";
    }

    @PostMapping
    public String openBankAccount (@ModelAttribute BankAccount bankAccount
            ,Model model){

        System.out.print(bankAccount);

        bankAccountService.CreateBankAccount(bankAccount);
        model.addAttribute("allBankAccount",bankAccountService.getBankAccount());

        return "redirect:bankaccount";
    }
}
