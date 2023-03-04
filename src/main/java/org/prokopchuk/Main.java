package org.prokopchuk;

import org.prokopchuk.repo.CompanyRepository;

public class Main {
    public static void main(String[] args) throws Exception{
        CompanyService service = new CompanyService();
        System.out.println(service.readById(5));
    }
}
