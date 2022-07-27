package com.rest.springbootemployee;

import com.rest.springbootemployee.entity.Company;
import com.rest.springbootemployee.entity.Employee;
import com.rest.springbootemployee.repository.CompanyRepository;
import com.rest.springbootemployee.repository.EmployeeRepository;
import com.rest.springbootemployee.service.CompanyService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(SpringExtension.class)
public class CompanyServiceTest {
    @Spy
    CompanyRepository companyRepository = new CompanyRepository(new EmployeeRepository());
    @InjectMocks
    CompanyService companyService;
    @Test
    void should_return_allCompanies_when_findAllCompanies_given_none(){
        // given
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(1, "Lily", 20, "Female", 11000));
        Company company = new Company(1, "spring",employees);
        List<Company> companies = new ArrayList<>();
        companies.add(company);
        given(companyRepository.findAllCompanies()).willReturn(companies);
        // when
        List<Company> actualCompanies = companyService.findAll();

        // then
        assertThat(actualCompanies,hasSize(1));
        assertThat(actualCompanies.get(0),equalTo(company));
    }
    @Test
    void should_return_company_when_findCompanyById_given_id(){
        // given
        int id = 1;
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(1, "Lily", 20, "Female", 11000));
        Company company = new Company(1, "spring",employees);
        given(companyRepository.findCompanyById(id)).willReturn(company);
        // when
        Company actualCompany = companyService.findCompanyById(id);
        // then
        assertThat(actualCompany,equalTo(company));
    }
    @Test
    void should_return_employees_when_findCompanyEmployeesById_given_id(){
        // given
        int id = 1;
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(1, "Lily", 20, "Female", 11000));
        Company company = new Company(1, "spring",employees);
        given(companyRepository.findCompanyAllEmployeesByCompanyId(id)).willReturn(employees);
        // when
        List<Employee> actualEmployees = companyService.findCompanyAllEmployeesByCompanyId(id);
        // then
        assertThat(actualEmployees,equalTo(employees));
    }
    @Test
    void should_return_companies_when_findCompaniesByPage_given_page(){
        // given
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(1, "Lily", 20, "Female", 11000));
        Company company = new Company(1, "spring",employees);
        List<Company> companies = new ArrayList<>();
        companies.add(company);
        given(companyRepository.findCompaniesByPageAndPageSize(1,5)).willReturn(companies);
        // when
        List<Company> actualCompanies = companyService.findCompaniesByPageAndPageSize(1,5);

        // then
        assertThat(actualCompanies,hasSize(1));
        assertThat(actualCompanies.get(0),equalTo(company));
    }
    @Test
    void should_return_noContent_when_deleteCompanyById_given_id(){
        // given
        int id = 1;
        // when
        companyService.deleteCompanyById(id);
        // then
        verify(companyRepository).delete(id);
    }

}