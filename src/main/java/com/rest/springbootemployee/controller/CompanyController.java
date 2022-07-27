package com.rest.springbootemployee.controller;

import com.rest.springbootemployee.entity.Company;
import com.rest.springbootemployee.entity.Employee;
import com.rest.springbootemployee.repository.CompanyRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("companies")
public class CompanyController {
    private final CompanyRepository companyRepository;

    public CompanyController(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @GetMapping
    public List<Company> getAllCompanies(){
        return companyRepository.findAllCompanies();
    }

    @GetMapping("/{id}")
    public Company getCompanyById(@PathVariable int id){
        return companyRepository.findCompanyById(id);
    }

    @GetMapping("/{id}/employees")
    public List<Employee> getCompanyAllEmployeesByCompanyId(@PathVariable int id){
        return companyRepository.findCompanyAllEmployeesByCompanyId(id);
    }

    @GetMapping(params = {"page","pageSize"})
    public List<Company> getCompaniesByPageAndPageSize(@RequestParam int page,@RequestParam int pageSize){
        return companyRepository.findCompaniesByPageAndPageSize(page,pageSize);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Company saveCompany(@RequestBody Company company){
         return companyRepository.save(company);
    }

    @PutMapping("/{id}")
    public Company updateCompanyById(@PathVariable int id,@RequestBody List<Employee> employees){
        return companyRepository.update(id,employees);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Boolean deleteCompanyById(@PathVariable int id){
        return companyRepository.delete(id);
    }
}
