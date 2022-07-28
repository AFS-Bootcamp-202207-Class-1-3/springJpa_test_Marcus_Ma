package com.rest.springbootemployee.controller;

import com.rest.springbootemployee.entity.Company;
import com.rest.springbootemployee.entity.Employee;
import com.rest.springbootemployee.repository.CompanyRepository;
import com.rest.springbootemployee.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("companies")
public class CompanyController {
    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping
    public List<Company> getAllCompanies(){
        return companyService.findAll();
    }

    @GetMapping("/{id}")
    public Company getCompanyById(@PathVariable Integer id){
        return companyService.findCompanyById(id);
    }

    @GetMapping("/{id}/employees")
    public List<Employee> getCompanyAllEmployeesByCompanyId(@PathVariable Integer id){
        return companyService.findCompanyAllEmployeesByCompanyId(id);
    }

    @GetMapping(params = {"page","pageSize"})
    public List<Company> getCompaniesByPageAndPageSize(@RequestParam Integer page,@RequestParam Integer pageSize){
        return companyService.findCompaniesByPageAndPageSize(page,pageSize);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Company saveCompany(@RequestBody Company company){
        return companyService.addCompany(company);
    }

    @PutMapping("/{id}")
    public Company updateCompanyById(@PathVariable Integer id,@RequestBody List<Employee> employees){
        return companyService.update(id,employees);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCompanyById(@PathVariable Integer id){
        companyService.deleteCompanyById(id);
    }
}
