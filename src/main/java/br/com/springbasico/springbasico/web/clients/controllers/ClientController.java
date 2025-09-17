package br.com.springbasico.springbasico.web.clients.controllers;

import java.util.ArrayList;
import java.util.Map;
import java.util.NoSuchElementException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.springbasico.springbasico.core.models.Client;
import br.com.springbasico.springbasico.core.repositories.ClientRepository;
import br.com.springbasico.springbasico.web.clients.dtos.ClientForm;
import br.com.springbasico.springbasico.web.clients.dtos.ClientViewModel;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/clients")

public class ClientController {
    
    private final ClientRepository clientRepository;
    
    @GetMapping
    public ModelAndView index(){
        var clients = clientRepository.findAll();
        var clientsViewModel = new ArrayList<ClientViewModel>();
        for(Client client : clients){
            var clientViewModel = ClientViewModel.of(client);
            clientsViewModel.add(clientViewModel);
        }

        return new ModelAndView("clients/index", Map.of("clients",clientsViewModel));
    }

    @GetMapping("/create")
    public ModelAndView create(){
        return new ModelAndView("clients/create", Map.of("clientForm", new ClientForm()));
    }
    
    @PostMapping("/create")
    public ModelAndView create(ClientForm clientForm){
        var client = clientForm.toClient();
        clientRepository.save(client);
        return new ModelAndView("redirect:/clients");
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable Long id){
        var client = clientRepository.findById(id);
        if(!client.isPresent()){
            throw new NoSuchElementException("Cliente não encontrado");
        }
        return new ModelAndView("clients/edit", Map.of("clientForm", ClientForm.of(client.get())));
    }
    @PostMapping("edit/{id}")
    public ModelAndView edit(@PathVariable Long id, ClientForm clientForm){
        if(!clientRepository.existsById(id)){
             throw new NoSuchElementException("Cliente não encontrado");
        }
        var client = clientForm.toClient();
        client.setId(id);
        clientRepository.save(client);
        return  new ModelAndView("redirect:/clients");
    }
    @GetMapping("delete/{id}")
    public String delete(@PathVariable Long id){
         if(!clientRepository.existsById(id)){
             throw new NoSuchElementException("Cliente não encontrado");
        }
        clientRepository.deleteById(id);
        return "redirect:/clients";
    }
}
