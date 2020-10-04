package th.ac.ku.atm.controller;

import org.springframework.web.bind.annotation.*;
import th.ac.ku.atm.service.BankAccountService;
import th.ac.ku.atm.model.BankAccount;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

@Controller
@RequestMapping("/bankaccount")
public class BankAccountController {

    private BankAccountService accountService;

    public BankAccountController(BankAccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping
    public String getBankAccountPage(Model model) {
        model.addAttribute("bankaccounts", accountService.getBankAccounts());
        return "bankaccount";
    }

    @PostMapping
    public String openAccount(@ModelAttribute BankAccount bankAccount, Model model) {
        accountService.openAccount(bankAccount);
        model.addAttribute("bankaccounts",accountService.getBankAccounts());
        return "redirect:bankaccount";
    }

    @GetMapping("/deposit/{id}")
    public String getDepositBankAccountPage(@PathVariable int id, Model model){
        BankAccount account = accountService.getBankAccount(id);
        model.addAttribute("bankAccount", account);
        return "bankaccount-deposit";
    }

    @PostMapping("/deposit/{id}")
    public String depositAccount(@PathVariable int id, @ModelAttribute BankAccount bankAccount, Model model) {
        accountService.depositBankAccount(bankAccount, bankAccount.getBalance());
        model.addAttribute("bankaccounts",accountService.getBankAccounts());
        return "redirect:/bankaccount";
    }

    @GetMapping("/withdraw/{id}")
    public String getWithdrawBankAccountPage(@PathVariable int id, Model model) {
        BankAccount bankAccount = accountService.getBankAccount(id);
        model.addAttribute("bankAccount", bankAccount);
        return "bankaccount-withdraw";
    }

    @PostMapping("/withdraw/{id}")
    public String withdrawBankAccount(@PathVariable int id, @ModelAttribute BankAccount bankAccount, Model model) {
        accountService.withdrawBankAccount(bankAccount, bankAccount.getBalance());
        model.addAttribute("bankaccounts", accountService.getBankAccounts());
        return "redirect:/bankaccount";
    }

    @PostMapping("/delete/{id}")
    public String deleteBankAccount(@PathVariable int id, Model model) {
        BankAccount bankAccount = accountService.getBankAccount(id);
        accountService.deleteBankAccount(bankAccount);
        model.addAttribute("bankaccounts", accountService.getBankAccounts());
        return "redirect:/bankaccount";
    }


}

