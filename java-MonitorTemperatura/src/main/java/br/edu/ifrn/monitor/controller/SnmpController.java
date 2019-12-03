package br.edu.ifrn.monitor.controller;

import java.io.IOException;

import org.snmp4j.CommunityTarget;
import org.snmp4j.PDU;
import org.snmp4j.Snmp;
import org.snmp4j.TransportMapping;
import org.snmp4j.event.ResponseEvent;
import org.snmp4j.event.ResponseListener;
import org.snmp4j.mp.SnmpConstants;
import org.snmp4j.smi.Address;
import org.snmp4j.smi.GenericAddress;
import org.snmp4j.smi.OID;
import org.snmp4j.smi.OctetString;
import org.snmp4j.smi.VariableBinding;
import org.snmp4j.transport.DefaultUdpTransportMapping;
import org.springframework.stereotype.Service;

@Service
public class SnmpController {

	private Snmp snmp;
	private String[] valorMIBFinal = { "" };

	public String[] valorMib(String host, String[] mib, int port) {
		try {
			// cria a variavel address que contem o IP ou nome da maquina
			// utiliza o protocolo UDP e a porta 161
			Address address = GenericAddress.parse("udp:" + host + "/" + port);
			
			// cria a variavel transport do qual estebelce um canal com o Gerente
			TransportMapping<?> transport = new DefaultUdpTransportMapping();
			snmp = new Snmp(transport);

			transport.listen();
			
			// cria a variavel target responsavel por receber os paramentros
			// Community,address, TimeOUt e versao SNMP
			CommunityTarget target = new CommunityTarget();
			target.setCommunity(new OctetString("public"));
			target.setAddress((org.snmp4j.smi.Address) address);
			target.setRetries(1);
			target.setTimeout(100);
			target.setVersion(SnmpConstants.version1);
			
			// cria a variavel PDU que vai conter o valor da MIB
			PDU pdu = new PDU();
			
			
			for (int i = 0; i < mib.length; i++) {
				if ((mib[i] != null) && (mib[i] != "")) {
					pdu.add(new VariableBinding(new OID(mib[i])));
				}
			}

			pdu.setType(PDU.GET);
			synchronized (snmp) {
				// envia mensagem ao gerente e cria um objeto listener
				snmp.send(pdu, target, null, listener);
				try {
					snmp.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		return valorMIBFinal;
	}

	/**
	 * Objeto Listener responsavel por receber o retorno do agente
	 */
	private ResponseListener listener = new ResponseListener() {

		/*
		 * metodo onResponse aguarda resposta do Gerente
		 */
		public void onResponse(ResponseEvent event) {
			((Snmp) event.getSource()).cancel(event.getRequest(), this);
			System.out.println(event.getResponse());
			synchronized (snmp) {
				snmp.notifyAll();
			}

		}
	};
}
