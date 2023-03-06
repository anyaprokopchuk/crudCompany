package org.prokopchuk;

import lombok.extern.slf4j.Slf4j;
import org.prokopchuk.repo.CompanyRepository;

import javax.management.InstanceNotFoundException;
import java.util.List;
import java.util.Optional;

@Slf4j
public class CompanyService {
    private final CompanyRepository repository = new CompanyRepository();

    public int insertCompany(Company company) {
        log.info("Inserting company into table");
        return repository.insert(company);
    }

    public void deleteById(int id) {
        log.info("Deleting company with id " + id);
        repository.deleteById(id);
    }

    public List<Company> readAll() {
        log.info("Reading companies");
        return repository.readAll();
    }

    public Company readById (int id) throws InstanceNotFoundException{
        log.info("Reading companies by id");
        return repository.readById(id).orElseThrow(InstanceNotFoundException::new);
    }

    public int updateCompany(Company company) {
        log.info("Updating");
        return repository.updateCompany(company);
    }
}
