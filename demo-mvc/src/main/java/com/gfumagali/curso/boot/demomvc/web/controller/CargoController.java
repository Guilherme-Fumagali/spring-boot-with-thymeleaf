package com.gfumagali.curso.boot.demomvc.web.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gfumagali.curso.boot.demomvc.domain.Cargo;
import com.gfumagali.curso.boot.demomvc.domain.Departamento;
import com.gfumagali.curso.boot.demomvc.service.CargoService;
import com.gfumagali.curso.boot.demomvc.service.DepartamentoService;
import com.gfumagali.curso.boot.demomvc.util.PaginacaoUtil;

@Controller
@RequestMapping("/cargos")
public class CargoController {
    
    @Autowired
    private CargoService cargoService;

    @Autowired
    private DepartamentoService departamentoService;
    
    @GetMapping("/cadastrar")
    public String cadastrar(Cargo cargo) {
        return "cargo/cadastro";
    }

    @GetMapping("/listar")
    public String listar(ModelMap model, 
                        @RequestParam("page") Optional<Integer> page,
                        @RequestParam("col") Optional<String> col,
                        @RequestParam("qtd") Optional<Integer> qtd,
                        @RequestParam("dir") Optional<String> dir) {
        int paginaAtual = page.orElse(1);
        int quantidadePorPagina = qtd.orElse(5);
        String coluna = col.orElse("id");
        String ordem = dir.orElse("asc");

        PaginacaoUtil<Cargo> pageCargo = cargoService.buscarPorPagina(paginaAtual, quantidadePorPagina, coluna, ordem);
        model.addAttribute("pageCargo", pageCargo);
       
        model.addAttribute("cargos", cargoService.buscarTodos());
        return "cargo/lista";
    }

    @PostMapping("/salvar")
    public String salvar(@Valid Cargo cargo, BindingResult result, RedirectAttributes attr) {
        if (result.hasErrors()) 
            return "cargo/cadastro";
        
        cargoService.salvar(cargo);
        attr.addFlashAttribute("success", "Cargo inserido com sucesso.");
        return "redirect:/cargos/cadastrar";
    }     

    @ModelAttribute("departamentos")
    public List<Departamento> listaDeDepartamentos() {
        return departamentoService.buscarTodos();
    }
    
    @GetMapping("/editar/{id}")
    public String preEditar(@PathVariable("id") Long id, ModelMap model) {
        model.addAttribute("cargo", cargoService.buscarPorId(id));
        return "cargo/cadastro";
    }

    @PostMapping("/editar")
    public String editar(@Valid Cargo cargo, BindingResult result, RedirectAttributes attr) {
        if (result.hasErrors()) 
            return "cargo/cadastro";

        cargoService.editar(cargo);
        attr.addFlashAttribute("success", "Cargo editado com sucesso.");
        return "redirect:/cargos/cadastrar";
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable("id") Long id, RedirectAttributes attr){
        if (cargoService.cargoTemFuncionarios(id)) {
            attr.addFlashAttribute("fail", "Cargo não removido. Possui funcionário(s) vinculado(s).");
        } else {
            cargoService.excluir(id);
            attr.addFlashAttribute("success", "Cargo excluído com sucesso.");
        }
        return "redirect:/cargos/listar";
    }
}
