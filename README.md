# Taass
progetto tecnologie e architetture avanzate per lo sviluppo del software 

questo progetto si compone di 4 parti


I Parte creazione del backend:
Il backend è stato creato attraverso Spring Initializr ed è stata aggiunta la dipendenze Spring Web che include i servizi RestFull e Tomcat.
all'interno del progetto ho creato delle sotto cartelle ognuna delle quali ha un compito ben preciso, ovvero:

	-Models -> la classe ha lo scopo di rappresentare un Entità 
	
	-Controllers -> la classe ha lo scopo di definire l'interfaccia da esporre per le richieste esterne, definisce le URI che identificano unicamente una risorsa e assegnare i metodi HTTP adatti 				all’operazione da svolgere. @RestController è un’annotazione Spring Boot conveniente per l’implementazione di controller RESTful. È un componente particolare 				che, tra le altre cose, converte la risposta in formato JSON o XML, come voluto da una tipica applicazione REST. 
			@RestController è tipicamente utilizzata in combinazione con @RequestMapping 
	
	-Repository -> la classe non dichiara nessun metodo, ma stendendo l’interfaccia JpaRepositoty ne eredita i metodi
	
	-Services -> 
	
Inoltre modificare il file 'application.properties' in modo che si possa effettuare una connessione con il db,inserire quindi le stringhe:

-spring.datasource.url=jdbc:postgresql://localhost:5432/postgres
-spring.datasource.driver-class-name=org.postgresql.Driver
-spring.datasource.username=postgres
-spring.datasource.password=admin
-spring.jpa.hibernate.ddl-auto = update

una volta create tutti le cartelle con le relative Classi ho mandato in esecuzione il progetto ed ho aperto il software Postman che è utile per testare tutte richieste Http, ovvero GET,DELETE,ADD e UPDATE 

II Parte creazione del sito web:
Il sito web è stato creato con il framework Angular.
attraverso il comando 'ng new nomesito' ho creato il progetto Angular e successivamente l'ho mandato in esecuzione sulla porta http://localhost:4200 con il comando 'ng serve'.
Successivamente ho scritto il mio codice Html nel file 'app.component.html', il mio codice Css all'interno del file 'app.component.css' e il mio Javascript nel file 'main.js',che ho appositamente creato.
NB Per testare tutte le richieste all'interno del sito è necessario che il progetto di backend sia in esecuzione

III Parte creazione di un app Android:
Il terzo passo di questo progetto è stato creare un'app Android che comunicasse con il sito ed il backend.
Sempre con il backend in esecuzione ho creato l'app Appointmed ed attraverso la libreria HttpUrlConnection ho effettuato le richieste http.
Come prima cosa ho creato una classe protetta 'FetchDataTask' che estende la libreria AsyncTask.
Questa classe mi permette di importare ed utilizzare 3 metodi senza il quale non è possibile effettuare le richieste:
	-Metodo doInBackground() -> ha lo scopo di mandare in esecuzione dei metodi
	-Metodo onPreExecute() -> ha lo scopo di controllare cosa succede prima che il doInBackground si avvii
	-Metodo onPostExecute() -> ha lo scopo di controllare cosa succede dopo che il doInBackground si avvii

utilizzando questi metodi è possibile stabilire una connessione con il server e scambiare dati json attraverso le richieste Http GET,DELETE,ADD,UPDATE


IV Parte Docker:
Questa è l'ultima parte, come prima cosa ho creato 2 file all'interno della cartella del background, ovvero Dockerfile e docker-compose.yml
una volta creati i file attraverso il comando 'mvnw -DskipTests=true clean package' ho creato il mio file .jar.
a questo punto posso mandare in esecuzione il docker con il comando 'docker-compose up'.
NB se ci da un errore dicendoci che ce una connessione aperta sulla porta 5432 la soluzione è aprire il terminale e controllare attraverso il comando 'sudo docker postgresql status' lo stato di postgres,
se attivo, spegnerlo con il comando 'sudo docker postgresql stop' e rimandare in esecuzione con docker-compose up
a questo punto solo il backend girerà all'interno di un container docker
