---
title: "Tutorial java: Programando Sockets"
header:
  teaser: "assets/images/java-socket.jpg"
categories:
  - Java
  - Tutorial
tags:
  - Java
  - Sockets
  - Tutorial
---
{% include toc %}


## Implemente corretamente o sua própria aplicação cliente/servidor

> Este clássico `JavaWorld` tutorial apresenta uma introdução à programação de sockets em redes TCP/IP e demonstra como implementar aplicações cliente/servidor em Java.

## Um pouco de história

O sistema de entrada/saída (E/S) do Unix segue um paradigma geralmente conhecido como `Open-Read-Write-Close.` Antes que um processo de usuário possa executar operações de E/S, ele chama Open para especificar e obter permissões para o arquivo ou dispositivo a ser usado. Depois que um objeto foi aberto, o processo do usuário faz uma ou mais chamadas para `ler` ou `gravar` dados. `Read` lê dados do objeto e os transfere para o processo do usuário, enquanto que `Write` transfere dados do processo do usuário para o objeto. Depois que todas as operações de transferência estiverem concluídas, o usuário chama `Close` para informar ao sistema operacional que ele terminou de usar esse objeto.


| Saiba mais sobre Java I/O       								| 
| ----------------------------------------------------------|
| Saiba como obter o máximo de desempenho das classes de E/S não bloqueadoras do Java com este passo-a-passo: |
| [Programação de socket para sistemas escaláveis](http://www.javaworld.com/article/2853780/java-app-dev/socket-programming-for-scalable-systems.html)    			| 
| [Cinco maneiras de maximizar o Java NIO e NIO.2](http://www.javaworld.com/article/2078654/java-se/five-ways-to-maximize-java-nio-and-nio-2.html)    			| 
| [Introdução às novas classes de E / S do Master Merlin](#) 			| 


Quando as implementações para InterProcess Communication (IPC) e rede foram adicionadas ao Unix, a idéia era fazer a interface para IPC semelhante à de E/S de arquivos. No Unix, um processo tem um conjunto de descritores de I/O de que se lê e grava. Esses descritores podem se referir a arquivos, dispositivos ou canais de comunicação (sockets). A duração de um descritor é composta de três fases: criação (abrir o socket), leitura e gravação (receber e enviar para socket) e destruição (fechar o socket).

A interface IPC em sistemas operacionais Unix do tipo BSD, é implementada na rede como uma camada sobre os protocolos TCP e UDP. Destinos de mensagem são especificados como endereços de socket; Cada endereço de socket é um identificador de comunicação que consiste em um número de porta e um endereço de Internet.

As operações IPC são baseadas em pares de sockets, onde um dos sockets pertence a um processo de comunicação. A operação IPC é feita através da troca de alguns dados durante a transmissão de dados de uma mensagem entre um socket em um processo e outro socket em outro processo.

Quando as mensagens são enviadas, elas são enfileiradas no socket de envio até que o protocolo de rede subjacente as tenha transmitido. Quando as mensagem chegam, são enfileiradas no socket  de recebimento até que o processo de recebimento faça as chamadas necessárias para recebê-las.

## Comunicação TCP/IP e UDP/IP 

Há dois protocolos de comunicação que podem ser usados ​​para a programação de sockets: comunicação de datagramas e comunicação de stream (fluxo).

### Comunicação de Datagrama:

O protocolo de comunicação de Datagramas, conhecido como UDP (user datagram protocol), é um protocolo não orientado a conexão, o que significa que cada vez que você enviar datagramas, você também precisa enviar o descritor de socket local e o endereço do socket de recebimento. Da para perceber que dados adicionais devem ser enviados cada vez que uma comunicação é feita.

### Comunicação de Stream:

O protocolo de comunicação de stream é conhecido como TCP (transfer control protocol). Diferentemente do UDP, TCP é um protocolo orientado à conexão. Para realizar a comunicação através do protocolo TCP, uma conexão deve primeiro ser estabelecida entre o par de sockets. Enquanto um dos sockets escuta uma solicitação de conexão (servidor), o outro pede uma conexão (cliente). Uma vez conectados, eles podem ser usados ​​para transmitir dados em ambas direções.

Agora, você deve estar se perguntando, qual protocolo devo usar - UDP ou TCP? Isso depende do aplicativo cliente/servidor que você está implementando. A discussão a seguir mostra as diferenças entre os protocolos UDP e TCP; Isso pode ajudá-lo a decidir qual protocolo você deva usar.

Em UDP, como você leu acima, cada vez que você envia um datagrama, você tem que enviar tambem o descritor de socket local e o endereço do socket de recebimento. Como TCP é um protocolo orientado à conexão, antes da comunicação entre o par de sockets iniciar, uma conexão deve ser estabelecida. Portanto, existe um tempo de configuração de conexão no TCP.

No UDP, há um limite de tamanho de 64 kilobytes em datagramas que você pode enviar para um local especificado, enquanto que no TCP não há limite. Uma vez estabelecida a conexão, o par de sockets se comportam como stream (fluxos): Todos os dados disponíveis são lidos imediatamente na mesma ordem em que são recebidos.

UDP é um protocolo não confiável - não há garantia de que os datagramas que você enviou serão recebidos na mesma ordem pelo socket de recebimento. Por outro lado, TCP é um protocolo confiável; É garantido que os pacotes enviados serão recebidos na ordem em que foram enviados.

Em resumo, o TCP é útil para implementar serviços de rede - como login remoto (rlogin, telnet) e transferência de arquivos (FTP) - que requerem dados de comprimento indefinido para serem transferidos. UDP é menos complexo e mais leve do que o TCP. É frequentemente utilizado na implementação de aplicações cliente/ servidor em sistemas distribuídos construídos sobre redes de área local.


## Programando sockets em Java

Nesta seção, responderemos às perguntas mais freqüentes sobre a programação de sockets em Java. Em seguida, mostraremos alguns exemplos de como implementar aplicativos cliente e servidor.

**Nota:** Neste tutorial vamos mostrar como programar sockets em Java usando o protocolo TCP/IP somente. Uma vez que é mais amplamente utilizado do que UDP/ IP. **Também:** Todas as classes relacionadas aos sockets estão no pacote `java.net`, portanto, certifique-se de importar esse pacote quando você programar com sockets.


### Como abrir um socket? ###

Se você estiver programando um cliente, então você abriria um socket como este:

```java
    Socket MyClient;
    MyClient = new Socket("Machine name", PortNumber);
```
Onde `nome da máquina` é a máquina que você está tentando abrir uma conexão, e `PortNumber` é o numero da porta do servidor que você quer se conectar e que está em execução.  Ao selecionar um número de porta, você deve observar que os números entre 0 e 1.023 são reservados para usuários privilegiados (ou seja, superusuário ou raiz). Esses números de porta são reservados para serviços padrão, como e-mail, FTP e HTTP. Ao selecionar um número de porta para o servidor, selecione um que seja maior que 1.023!

No exemplo acima, não usamos o tratamento de exceções, no entanto, é uma boa prática lidar com exceções. (De agora em diante, todo o nosso código irá lidar com exceções!) O acima pode ser escrito como:

```java
    Socket MyClient;
    try {
        MyClient = new Socket("Machine name", PortNumber);
    }
    catch (IOException e) {
        System.out.println(e);
    }
 ```  
Se você estiver programando um servidor, então é assim que você abre um socket: 

```java
    ServerSocket MyService;
    try {
       MyServerice = new ServerSocket(PortNumber);
    }
    catch (IOException e) {
       System.out.println(e);
    }
```
Ao implementar um servidor, você também precisa criar um objeto de socket do **ServerSocket** para ouvir e aceitar conexões de clientes.

```java
    Socket clientSocket = null;
    try {
    	serviceSocket = MyService.accept();
    }
    catch (IOException e) {
    	System.out.println(e);
    }
 ```   
### Como criar um stream de entrada? ###

No lado do cliente, você pode usar a classe `DataInputStream` para criar um stream de entrada para receber a resposta do servidor:

```java
    DataInputStream input;
    try {
    	input = new DataInputStream(Cliente.getInputStream());
    }
    catch (IOException e) {
     	System.out.println(e);
    }
```

A classe `DataInputStream` permite que você leia linhas de texto e tipos dados primitivos do Java de forma portátil. Ele tem métodos como `read, readChar, readInt, readDouble,` e `readLine, .` Use qualquer função que você ache que atenda às suas necessidades, dependendo do tipo de dados que receba do servidor.

No lado do servidor, você pode usar `DataInputStream` para receber entrada do cliente:

```java
    DataInputStream input;
    try {
      input = new DataInputStream(serviceSocket.getInputStream());
    }
    catch (IOException e) {
      System.out.println(e);
    }
```    

### Como criar um stream de saída?_


No lado do cliente, você pode criar um stream de saída para enviar informações para o socket do servidor usando a classe `PrintStream` ou `DataOutputStream` de java.io:

```java
    PrintStream output;
    try {
      output = new PrintStream(MyClient.getOutputStream());
    }
    catch (IOException e) {
      System.out.println(e);
    }
```
A classe `PrintStream` tem métodos para exibir a representação textual de tipos de dados primitivos do Java. Os seus métodos `Write` e `println` são importantes aqui. Além disso, você pode querer usar o `DataOutputStream:`

```java
    DataOutputStream output;
    try {
      output = new DataOutputStream(MyClient.getOutputStream());
    }
    catch (IOException e) {
      System.out.println(e);
    }
```   
A classe `DataOutputStream` permite escrever tipos de dados primitivos Java; Muitos dos seus métodos escrevem um único tipo primitivo Java para o stream de saída. O método `writeBytes` é útil.

No lado do servidor, você pode usar a classe `PrintStream` para enviar informações para o cliente.

```java
    PrintStream output;
    try {
      output = new PrintStream(serviceSocket.getOutputStream());
    }
    catch (IOException e) {
      System.out.println(e);
    }
```  
Nota: Você pode usar a classe `DataOutputStream` como mencionado acima. 

### Como fechar sockets?

Você deve fechar sempre a saída do stream de entrada antes de fechar o socket.

No lado do cliente:

```java
    try {
      output.close();
      input.close();
      MyClient.close();
    } 
    	catch (IOException e) {
      System.out.println(e);
    }
```

No lado do servidor:

```java    

    try {
      output.close();
      input.close();
      serviceSocket.close();
      MyService.close();
    } 
    catch (IOException e) {
      System.out.println(e);
    }
```    

## Exemplos

Nesta seção, vamos escrever duas aplicações: um simples cliente SMTP (simple mail transfer protocol) e um servidor de eco simples.

### 1. Cliente SMTP

Vamos implementar um cliente SMTP, tão simples que temos todos os dados encapsulados dentro do programa. Você pode alterar o código para atender às suas necessidades. Uma modificação interessante seria alterá-lo para que aceite os dados através da linha de comando e também obter o corpo da mensagem (body) pela entrada padrão. Tente modificá-lo para que ele se pareça com o programa de email nativo do Unix.

```java
import java.io.*;
import java.net.*;
public class smtpClient {
	public static void main(String[] args) {

// declaração seção:
// smtpClient: nosso socket cliente
// os: stream de saida
// is: stream de entrada

		Socket smtpSocket = null;  
		DataOutputStream os = null;
		DataInputStream is = null;

// seção de inicialização:
// Tenta abrir um socket na porta 25
// Tenta abrir streams de entrada e saida

		try {
			smtpSocket = new Socket("hostname", 25);
			os = new DataOutputStream(smtpSocket.getOutputStream());
			is = new DataInputStream(smtpSocket.getInputStream());
		} catch (UnknownHostException e) {
			System.err.println("Don't know about host: hostname");
		} catch (IOException e) {
			System.err.println("Couldn't get I/O for the connection to: hostname");
		}

// Se tudo já foi inicializado então vamos gravar alguns
// dados no socket que abrimos na porta 25   

		if (smtpSocket != null && os != null && is != null) {
			try {

// Os caracteres antes de cada dois pontos tem um significado
// especial em SMTP leia a especificação SMTP, RFC 1822/3.

				os.writeBytes("HELO\n");    
				os.writeBytes("MAIL From: givanaldo@ifrn.edu.br\n");
				os.writeBytes("RCPT To: givanaldo@ifrn.edu.br\n");
				os.writeBytes("DATA\n");
				os.writeBytes("From: givanaldo@ifrn.edu.br\n");
				os.writeBytes("Subject: testing\n");
				os.writeBytes("Hi there\n"); // message body
				os.writeBytes("\n.\n");
				os.writeBytes("QUIT");

// Manter a leitura do socket até receber um "Ok" do SMTP, 
// uma vez que recebida, parar logo em seguida (break).


				String responseLine;
				while ((responseLine = is.readLine()) != null) {
					System.out.println("Server: " + responseLine);
					if (responseLine.indexOf("Ok") != -1) {
						break;
					}
				}

// limpar:
// fechar o stream de saída
// fechar o stream de entrada
// fechar o socket

				os.close();
				is.close();
				smtpSocket.close();   
			} catch (UnknownHostException e) {
				System.err.println("Trying to connect to unknown host: " + e);
			} catch (IOException e) {
				System.err.println("IOException:  " + e);
			}
		}
	}           
}
```
 

Ao programar um cliente, você deve seguir estas quatro etapas:
1. Abrir um socket. 
2. Abrir um stream de entrada e saída para o socket.
3. Ler e gravar no socket de acordo com o protocolo do servidor.
4. Limpar.

Essas etapas são praticamente as mesmas para todos os clientes. A única etapa que varia é a etapa três, uma vez que depende do servidor que você está se comunicando.
 

### 2. Servidor Eco ###

Agora vamos implementar um servidor. Esse servidor é muito semelhante ao servidor de eco que está sendo executado na porta 7. Basicamente, o servidor de echo recebe texto do cliente e envia o texto exato para o cliente. Este é o servidor mais simples que você pode implementar. Observe que este servidor trata apenas um cliente. Tente modificá-lo para lidar com vários clientes usando `threads.`

```java
import java.io.*;
import java.net.*;
public class echo3 {
   public static void main(String args[]) {

// seção de declaração :
// declarar para o servidor sockets de servidor e cliente. 
// declarar um stream de entrada e saída

		ServerSocket echoServer = null;
		String line;
		DataInputStream is;
		PrintStream os;
		Socket clientSocket = null;

// Tentar abrir um socket de servidor na porta 9999
// Observe que não podemos escolher uma porta menor que 1023 
// se não formos usuários privilegiados (root).

		try {
			echoServer = new ServerSocket(9999);
		}
		catch (IOException e) {
			System.out.println(e);
		}   

// Crie um objeto socket do ServerSocket para ouvir e aceitar conexões.
// Abra os streams de entrada e saída.

		try {
			clientSocket = echoServer.accept();
			is = new DataInputStream(clientSocket.getInputStream());
			os = new PrintStream(clientSocket.getOutputStream());

// Enquanto recebermos dados, ecoamos esses dados de volta para o cliente.

			while (true) {
				line = is.readLine();
				os.println(line); 
			}
		}   
		catch (IOException e) {
			System.out.println(e);
		}
	}
}

```

## Conclusão

Programar aplicativos cliente/servidor é desafiador e divertido, e programar esse tipo de aplicativo em Java é mais fácil do que em outras linguagens, como C. A programação de sockets em Java é perfeita.

O pacote `java.net` fornece uma infra-estrutura poderosa e flexível para a programação em rede, por isso encorajamos você a pesquisar sobre esse pacote se quiser saber mais sobre as classes que são fornecidas.

Os pacotes Sun têm boas classes para a rede, no entanto, não use essas classes no momento, porque eles podem mudar na próxima versão. Além disso, algumas das classes não são portáteis para todas as plataformas.

**Qusay H. Mahmoud** é um estudante graduado em ciência da computação pela Universidade de New Brunswick, Campus de Saint John, no Canadá. Ele está atualmente trabalhando em sua tese de mestrado, e também é professor no curso de Ciência da Computação na Universidade. Sua tese concentra-se na Web e Java, e os resultados de sua tese estarão disponíveis on-line assim que ele for concluído. Ele tem trabalhado com o Java desde que foi lançado ao público.
{: .notice--info}

### Aprenda mais sobre este tópico

[Documentação de rede e segurança personalizada da Sun](http://java.sun.com/books/Series/Tutorial/networking/index.html)

[A API de pacotes Java (incluindo java.net)](http://www.javasoft.com:80/products/JDK/1.0.2/api/packages.html)

**TRADUÇÃO:**  Antonio Sergio Ferreira pacheco e Carlos Andrade Freitas.
{: .notice--danger}
