package com.ghostnet.ghostnet.service;

import com.ghostnet.ghostnet.model.GhostNet;
import com.ghostnet.ghostnet.model.Person;
import com.ghostnet.ghostnet.repository.GhostNetRepository;
import com.ghostnet.ghostnet.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GhostNetService {

    @Autowired
    private GhostNetRepository ghostNetRepository;

    @Autowired
    private PersonRepository personRepository;

    public List<GhostNet> getAllNets() {
        return ghostNetRepository.findAll();
    }

    public List<GhostNet> getNetsToRecover() {
        return ghostNetRepository.findByStatus(GhostNet.Status.GEMELDET);
    }

    public void reportNet(GhostNet net, String reporterName, String reporterPhone) {
        if (reporterName != null && !reporterName.isBlank()) {
            Person reporter = new Person();
            reporter.setName(reporterName);
            reporter.setPhone(reporterPhone);
            reporter.setPersonType(Person.PersonType.MELDEND);
            personRepository.save(reporter);
        }
        net.setStatus(GhostNet.Status.GEMELDET);
        ghostNetRepository.save(net);
    }

    public String assignRecoverer(Long netId, String name, String phone) {
        GhostNet net = ghostNetRepository.findById(netId).orElseThrow();
        if (net.getRecoverer() != null) {
            return "Dieses Netz wurde bereits von " + net.getRecoverer().getName() + " übernommen!";
        }
        // Find or Create: schaut ob Person bereits existiert
        // so wird verhindert dass dieselbe Person mehrfach angelegt wird
        Person recoverer = personRepository.findByNameAndPhone(name, phone)
                .orElseGet(() -> {
                    Person newPerson = new Person();
                    newPerson.setName(name);
                    newPerson.setPhone(phone);
                    newPerson.setPersonType(Person.PersonType.BERGEND);
                    return personRepository.save(newPerson);
                });
        net.setRecoverer(recoverer);
        net.setStatus(GhostNet.Status.BERGUNG_BEVORSTEHEND);
        ghostNetRepository.save(net);
        return null;
    }

    public void markAsRecovered(Long netId) {
        GhostNet net = ghostNetRepository.findById(netId).orElseThrow();
        // ob ich hier noch den Berger entferne?
        // net.setRecoverer(null);
        net.setStatus(GhostNet.Status.GEBORGEN);
        ghostNetRepository.save(net);
    }

    public void markAsLost(Long netId) {
        GhostNet net = ghostNetRepository.findById(netId).orElseThrow();
        net.setStatus(GhostNet.Status.VERSCHOLLEN);
        ghostNetRepository.save(net);
    }

    public List<GhostNet> getNetsWithRecoverer() {
        return ghostNetRepository.findByStatus(GhostNet.Status.BERGUNG_BEVORSTEHEND);
    }

    public List<GhostNet> getNetsNotRecovered() {
        // gemeldet UND bevorstehend sind noch nicht geborgen
        // deshalb beide listen zusammenfühen für die Karte
        List<GhostNet> gemeldet = ghostNetRepository.findByStatus(GhostNet.Status.GEMELDET);
        List<GhostNet> bevorstehend = ghostNetRepository.findByStatus(GhostNet.Status.BERGUNG_BEVORSTEHEND);
        gemeldet.addAll(bevorstehend);
        return gemeldet;
    }

    public List<Person> getAllPersons() {
        return personRepository.findAll();
    }
}