package br.edu.ifrn.monitor.controller;

import java.math.BigDecimal;
import java.util.List;

import javax.validation.Valid;

import org.snmp4j.smi.Integer32;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifrn.monitor.exception.ResourceNotFoundException;
import br.edu.ifrn.monitor.model.Monitor;
import br.edu.ifrn.monitor.repository.MonitorRepository;

/* 
RFC1213-MIB OIDs
.iso (.1)
.iso.org (.1.3)
.iso.org.dod (.1.3.6)
.iso.org.dod.internet (.1.3.6.1)
.iso.org.dod.internet.mgmt (.1.3.6.1.2)
.iso.org.dod.internet.mgmt.mib-2 (.1.3.6.1.2.1)
.iso.org.dod.internet.mgmt.mib-2.system (.1.3.6.1.2.1.1)

# Arduino predefina OIDs
.iso.org.dod.internet.private (.1.3.6.1.4)
.iso.org.dod.internet.private.enterprises (.1.3.6.1.4.1)
.iso.org.dod.internet.private.enterprises.arduino (.1.3.6.1.4.1.36582)
.iso.org.dod.internet.private.enterprises.arduino.value.valA0-A5 (.1.3.6.1.4.1.36582.3.1-6) RO - Integer
*/

@RestController
@RequestMapping("/api")
public class MonitorController {
	
	String sysDesc     = "1.3.6.1.2.1.1.1.0"; // ...system.sysDescr(.1.3.6.1.2.1.1.1) read-only RO - DisplayString
	String sysObjectID = "1.3.6.1.2.1.1.2.0"; // ...system.sysObjectID (.1.3.6.1.2.1.1.2) RO - ObjectIdentifier
	String sysUpTime   = "1.3.6.1.2.1.1.3.0"; // ...system.sysUpTime (.1.3.6.1.2.1.1.3) RO - TimeTicks
	String sysContact  = "1.3.6.1.2.1.1.4.0"; // ...system.sysContact (.1.3.6.1.2.1.1.4) RW - DisplayString
	String sysName     = "1.3.6.1.2.1.1.5.0"; // ...system.sysName (.1.3.6.1.2.1.1.5) RW - DisplayString
	String sysLocation = "1.3.6.1.2.1.1.6.0"; // ...system.sysLocation (.1.3.6.1.2.1.1.6) RW - DisplayString
	String sysServices = "1.3.6.1.2.1.1.7.0"; // ...system.sysServices (.1.3.6.1.2.1.1.7) RO - Integer

	String tempOID    = "1.3.6.1.4.1.36582.3.1.0"; 
	String umidOID    = "1.3.6.1.4.1.36582.3.2.0";  
	 
	String locDescr    = "Arduino";
	String locObjectID = "1.3.6.1.4.1.36582";
	Integer locUpTime = 0;  
	String locContact  = "redessemfio@ifrn.edu.br";
	String locName     = "REDES SEM FIO - Arduino";
	String locLocation = "Parnamirim, RN";
	Integer locServices = 7;
	
	String [] mib = {".1.3.6.1.4.1.22626.1.5.2.1.3.0"}; 

    @Autowired
    MonitorRepository monitorRepository;
    
    @Autowired 
    SnmpController snmp; 

    @GetMapping("/temperatura")       
    public List<Monitor> getAllNotes() {
        return monitorRepository.findAll();
    }
    
    @GetMapping("/temperatura/{temp}/{umid}")
    public void createTempUmid(@PathVariable BigDecimal temp, @PathVariable BigDecimal umid) {
    	Monitor dht11 = new Monitor(); 
    	dht11.setTemperatura(temp);
    	dht11.setUmidade(umid);
        monitorRepository.save(dht11);
        System.out.println(dht11.toString());
         
        snmp.valorMib("172.16.1.25", mib, 161);   // Envia Mib temperatura para Zabbix (GET)
       
    }
    
    @PostMapping("/temperatura")
    public Monitor createTemp(@Valid @RequestBody Monitor monitor) {
        return monitorRepository.save(monitor);
    }

    @GetMapping("/temperatura/{id}")
    public Monitor getTempById(@PathVariable(value = "id") Long tempId) {
        return monitorRepository.findById(tempId).orElseThrow(() -> new ResourceNotFoundException("Temperatura", "id", tempId));
    }

    @PutMapping("/temperatura/{id}")
    public Monitor updateTemp(@PathVariable(value = "id") Long tempId, @Valid @RequestBody Monitor monitor) {

        Monitor temp = monitorRepository.findById(tempId).orElseThrow(() -> new ResourceNotFoundException("Note", "id", tempId));

        temp.setTemperatura(monitor.getTemperatura());
        temp.setUmidade(monitor.getUmidade());

        Monitor updatedMonitor = monitorRepository.save(temp);
        return updatedMonitor;
    }

    @DeleteMapping("/temperatura/{id}")
    public ResponseEntity<?> deleteTemp(@PathVariable(value = "id") Long tempId) {
        Monitor temp = monitorRepository.findById(tempId).orElseThrow(() -> new ResourceNotFoundException("Monitor", "id", tempId));

        monitorRepository.delete(temp);

        return ResponseEntity.ok().build();
    }
      
	
    
}
