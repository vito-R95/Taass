const date = new Date();
const renderCalendar=()=>{
  date.setDate(1);

  const lastDay= new Date(date.getFullYear(),date.getMonth()+1, 0).getDate();
  const prevLastDay= new Date(date.getFullYear(),date.getMonth(), 0).getDate();
  const firstDayIndex=date.getDay();
  //console.log(prevLastDay);
  const lastDayIndex=new Date(date.getFullYear(),date.getMonth()+1, 0).getDay();
  //console.log(date.getFullYear());
  const nextDays = 7 -lastDayIndex-1;
  const mese = [
    "Gennaio",
    "Febbraio",
    "Marzo",
    "Aprile",
    "Maggio",
    "Giugno",
    "Luglio",
    "Agosto",
    "Settembre",
    "Ottobre",
    "Novembre",
    "Dicembre",
  ];
  var mese2=document.getElementById("m");
  mese2.innerHTML=mese[date.getMonth()]+" "+date.getFullYear();
  /*qui inserire data odierna -----------------------------*/ 
  console.log(mese2);
  
  var MM=mese[date.getMonth()];
  console.log(MM);
  var giorni2=document.getElementById("giorni");
  let giorni="";
  
  for(let x=firstDayIndex; x>0; x--){
    let a=prevLastDay-x+1;
    giorni+="<button class=prev-date id=openAppuntamenti onclick=openAppuntamenti("+a+")>" + a +"</button>";
  }
  
  for(let i=1; i<=lastDay; i++){
    var datadimm = new Date();
      var mmhh=date.getMonth()+1;
      var ddd=mmhh;
    if(i=== new Date().getDate() && date.getMonth()===new Date().getMonth()){
      //var ggmm=i+" "+mese[date.getMonth()];
      /*var ggmm=i+" "+mese[date.getMonth()];
      console.log(ggmm);
      console.log(mese[date.getMonth()]);
      var data2= new Date();
      var mm=data2.getMonth()+1;
      var cristo=i+" "+mm;
      console.log(cristo);*/
      
      var MM2=i+MM;
      $tyuui=MM;
      //console.log(mese[date.getMonth()]);
      $aaaaa=mese[date.getMonth()];
      giorni+="<button class=today id=openAppuntamenti onclick=openAppuntamenti("+i+","+ddd+") value="+i+" >"+i+/*"<div id="+i+"></div>*/"</button>";
      giorni2.innerHTML=giorni;
      loadAppuntamenti(i,ddd);
      //pallinoforse(i,ddd);
    }else{
      giorni+="<button id=openAppuntamenti onclick=openAppuntamenti("+i+","+ddd+") value="+i+">"+i+"<div id="+i+"></div></button>";
      giorni2.innerHtml=giorni;
      loadAppuntamenti(i,ddd);
      //giorni2.innerHTML=pallinoforse(i,giorni);
      //pallinoforse(i,ddd);
          
    } 
          
  } 
  
/*------------------------------------------------------------------------------------------------------------------*/  
      for(let j=1;j<=nextDays; j++){    
          //giorni+="<button class=next-date id=openAppuntamenti onclick=openAppuntamenti("+j+")>" +j+"</button>";
          giorni+="<button class=next-date id=openAppuntamenti onclick=openAppuntamenti("+j+")>" +j+"<div id="+j+"></div></button>";
          giorni2.innerHTML=giorni;
          loadAppuntamenti(j);
          //pallinoforse(j);
           
      }
/*------------------------------------------------------------------------------------------------------------------*/
//console.log(mese2);
}
function pre(){
  date.setMonth(date.getMonth()-1);
  renderCalendar();

}
function next(){
  date.setMonth(date.getMonth()+1);
  renderCalendar();
}
/*function apriMenu(){
  var box1=document.getElementById("menu");
  var sposta=document.getElementById("sposta");
  if(box1.style.left=="-180px"){
  box1.style.left="0px";
  sposta.style.left="0px";
  }else{
    box1.style.left="-180px";
  sposta.style.left="-150px";
    }
}*/

async function loadAppuntamenti(j,ddd){
  let baseUrl="http://localhost:8080/api/appuntamenti/getAppuntamenti";
  //alert("gg calendario"+dataEsatta1(j,ddd));
  let arrayAppuntamenti= await get(baseUrl);
  var box1=document.getElementById(j);
  var giornoCalend=dataAppuntamento(j,ddd);
  for(i=0; i<arrayAppuntamenti.length; i++){
    if(arrayAppuntamenti[i].giorno==giornoCalend){
      box1.style.display="block";
      box1.innerHTML="<div id=bo></div>";
    }
  }
}
async function openAppuntamenti(i,a){
let baseUrl="http://localhost:8080/api/appuntamenti/getAppuntamenti";
var giornoCalend=dataAppuntamento(i,a);
  //alert("gg calendario"+dataEsatta1(j,ddd));
let array= await get(baseUrl);
let appuntamenti=document.getElementById("appuntamenti");
let titolo=document.getElementById("titolo");
titolo.innerHTML="<center>appunatamento del "+giornoCalend+"</center>";
let paragrafo=document.getElementById("paragrafo");
let main="";

if(appuntamenti.style.left=="965px"){
  appuntamenti.style.left="600px";
}else{
  appuntamenti.style.left="965px";
  for(let i=0; i<array.length; i++){
    if(array[i].giorno!=giornoCalend){
      main+="";  
    }else{
      let paziente=await getNomePaziente(array[i].idPaziente);
      let coloreDottore=  await getColoreDottore(array[i].idDottore);
      let oraGiusta=oraAppuntamento(array[i].ora);

      if(array[i].tipoAppuntamento=="seduta"){
        main+="<ul id=ul><li style=color:blue>";
        main+="<h4 id=rigaAppuntamento>";
        main+=" "+oraGiusta+" "+paziente+" ";
        main+="</h4></li>";
        main+="<div id=iconDottApp style=background-color:"+coloreDottore+"><img src=assets/images/doctor.png width=20px height=20px> </div>";
        main+="<button id=updateAppuntamento onclick=updateAppuntamento("+array[i].id+")><img src=assets/images/update2.png width=20px height=20px></button>";
        main+="<button id=deleteAppuntamento onclick=deleteAppuntamento("+array[i].id+")><img src=assets/images/close.png  width=20px height=20px></button>";
                    /*main+="idP:"+app2[i].idPaziente+" nome:"+paziente+"<br>";
                    main+="idD:"+app2[i].idDottore+" nome:"+dottore+"<br>";*/
        main+="</ul><br>";
      }else if(array[i].tipoAppuntamento=="siringa"){
        main+="<ul id=ul><li style=color:red>";
        main+="<h4 id=rigaAppuntamento>";
        main+=oraGiusta+" "+paziente+" ";
        main+="</h4></li>";
        main+="<div id=iconDottApp style=background-color:"+coloreDottore+"><img src=assets/images/doctor.png width=20px height=20px> </div>";
        main+="<button id=updateAppuntamento onclick=updateAppuntamento("+array[i].id+")><img src=assets/images/update2.png width=20px height=20px></button>";
        main+="<button id=deleteAppuntamento onclick=deleteAppuntamento("+array[i].id+")><img src=assets/images/close.png  width=20px height=20px></button>";
        /*main+="idP:"+app2[i].idPaziente+" nome:"+paziente+"<br>";
        main+="idD:"+app2[i].idDottore+" nome:"+dottore+"<br>";*/
        main+="</ul><br>";
      }else{
        main+="<ul id=ul><li style=color:orange>";
        main+="<h4 id=rigaAppuntamento>";
        main+=oraGiusta+" "+paziente+" ";
        main+="</h4></li>";
        main+="<div id=iconDottApp style=background-color:"+coloreDottore+"><img src=assets/images/doctor.png width=20px height=20px> </div>";
        main+="<button id=updateAppuntamento onclick=updateAppuntamento("+array[i].id+")><img src=assets/images/update2.png width=20px height=20px></button>";
        main+="<button id=deleteAppuntamento onclick=deleteAppuntamento("+array[i].id+")><img src=assets/images/close.png  width=20px height=20px></button>";
        /*main+="idP:"+app2[i].idPaziente+" nome:"+paziente+"<br>";
        main+="idD:"+app2[i].idDottore+" nome:"+dottore+"<br>";*/
        main+="</ul><br>";
      }
    }

  }
  paragrafo.innerHTML=main+"<br>";
}

}
function deleteAppuntamento(id){
  var r=confirm("sei sicuro di voler eliminare questo appuntamento??");
  if(r==true){
  urlDelete="http://localhost:8080/api/appuntamenti/deleteAppuntamento/"+id;
  delet(urlDelete);
  alert("appuntamento eliminato");
  window.location.reload();
  }else{

  }
}
async function apriApp(){
  var popup3=document.getElementById("popupIA");
  popup3.style.display="flex";
  let doc=document.getElementById("idDoct");
  var url="http://localhost:8080/api/dottori/getDottori";
  let array = await get(url);
  let main="";
  main+="<select id=Dott name=Dott value=>"+
    "<option hidden selected>Dottore</option>";
  for(let i=0; i<array.length; i++){
    main+="<option value="+array[i].id+">"+array[i].nome+" "+array[i].cognome+" "+array[i].id +"</option>"
  }
  main+="</select>";
  doc.innerHTML=main;
}
function closeIA(){
var popup3=document.getElementById("popupIA");
  popup3.style.display="none";
}
async function invioPostjs(){
  var nome=document.myForm.Nome.value;
  var cognome=document.myForm.Cognome.value;
  var cf=document.myForm.Codice.value;
  var tipoApp=document.myForm.Terapia.value;
  //var medico=document.myForm.Dottore.value;
  var medico=document.getElementById("Dott").value;
  var giorno=document.myForm.Data.value;
  var ora=document.myForm.Ora.value;
  if(tipoApp=="primaVisita"){
    //aggiungi paziente
    cf="****";
    cell="0";
    let message= {
      "nome": nome,
      "cognome": cognome,
      "cf": cf,
      "cell": cell
      };
      

      //alert("dottore: "+dott);
    let url="http://localhost:8080/api/pazienti/addPaziente";
    post(message,url);
    alert(nome+" "+cognome+" "+cf +" "+cell+"aggiunto tra i pazienti,MODIFICALO");
    //preleva id
    let idPaziente= await getIdPaziente(nome,cognome,cf);
    
    //aggiungi appuntamento
    if(idPaziente!=""){
      let message= {
        "giorno": dataEsatta(giorno),
        "ora": oraEsatta(ora),
        "idPaziente":idPaziente,
        "idDottore": medico,
        "tipoAppuntamento": tipoApp
        };
      let url="http://localhost:8080/api/appuntamenti/addAppuntamento";
      post(message,url);
      alert("appuntamento inserito");
    }
  }else{
    //alert("Siringa/seduta");
    let idPaziente= await getIdPaziente(nome,cognome,cf);
    var r=confirm("vuoi inserire un nuovo appuntamento il "+dataEsatta(giorno)+" alle ore "+oraEsatta(ora)+"per "+nome+" "+cognome +"("+idPaziente+")");
      if(r==true){
        let message= {
          "giorno": dataEsatta(giorno),
          "ora": oraEsatta(ora),
          "idPaziente":idPaziente,
          "idDottore": medico,
          "tipoAppuntamento": tipoApp
          };
        let url="http://localhost:8080/api/appuntamenti/addAppuntamento";
        post(message,url);
        alert("appuntamento inserito");
      }else{

      }
  }
}
function updateAppuntamento(id){
  var updateAppuntamento=document.getElementById("popupUpdateA");
  updateAppuntamento.style.display="flex";
  //let url="http://localhost:8080/api/appuntamenti/getAppuntamento/"+id;
  //let array= await get(url);
  let body=document.getElementById("bodyUpdateA");
  let text="";
  text+="<form name=formUpdateA action=''>";
  //text+="<h6 id=dettaglioApp>DETTAGLIO:"+await getApp(id)+"</h6><br>";
  text+="modifica ora <input name=Ora id=Time2 type=time>";
  text+="oppure giorno <input name=Data id=Data2 type=date>";
  text+="<button id=invioUpdateA onclick=invioUpdateApp("+id+")>invia</button>";
  text+="</form>";
  body.innerHTML=text;

}

async function invioUpdateApp(id){
  let ora="";
  let data="";
  let NewOra=document.getElementById("Time2").value;
  let NewData=document.getElementById("Data2").value;
  //alert(data+" "+ora+"//"+dataEsatta(data)+" "+oraEsatta(ora));
  //alert(""+id);
  let url="http://localhost:8080/api/appuntamenti/getAppuntamento/"+id;
  let array= await get(url);
    let idPaziente =await array.idPaziente;
    let medico =await array.idDottore;
    let tipoApp =await array.tipoAppuntamento;
    let OldData=await array.giorno;
    let OldOra=await array.ora;
  if(idPaziente!="" && medico!="" && tipoApp!="" ){
    //alert(idPaziente+" "+medico+" "+tipoApp+" "+OldOra+" "+OldData +" "+NewOra+" "+NewData);
    var r=confirm("sei sicuro di voler modificare questo appuntamento??");
  if(r==true){
    if(NewOra==""){
      ora=OldOra;
      data=dataEsatta(NewData);
      //alert(ora+" "+data);
    }else if(NewData==""){
      ora=oraEsatta(NewOra);
      data=OldData;
      //alert(ora+" "+data);
    }else if(NewOra!="" && NewData!=""){
      ora=oraEsatta(NewOra);
      data=dataEsatta(NewData);
      //alert(ora+" "+data);
    }
    let message= {
      "giorno": data,
      "ora": ora,
      "idPaziente":idPaziente,
      "idDottore": medico,
      "tipoAppuntamento": tipoApp
      };
    let url2="http://localhost:8080/api/appuntamenti/updateAppuntamenti/"+id;
    alert(data+" "+ora+" "+idPaziente+" "+medico+" "+tipoApp);
    put(message,url2);
    alert("appunatamento modificato");
    }else{

  }
  }else{
    alert("ERROR");
  }

}

function closeUpdateA(){
  var updateAppuntamento=document.getElementById("popupUpdateA");
  updateAppuntamento.style.display="none";
}







function calendario(){
  var title=document.getElementById("titletot");
  var addD=document.getElementById("addD");
  var addP=document.getElementById("addP");
  var calendario=document.getElementById("calendario");
  var appuntamenti=document.getElementById("appuntamenti");
  var pazienti=document.getElementById("pazienti");
  var medico=document.getElementById("dottori");
  var buttIA=document.getElementById("buttonPopupIA");
  var buttPV=document.getElementById("buttonPopupPV");
  var archivioDiv=document.getElementById("archivioDiv");
  pazienti.style.left="-2000px";
    medico.style.left="-2000px";
    calendario.style.left="160px";
    appuntamenti.style.left="600px";
    buttIA.style.left="200px";
    buttPV.style.left="360px";
    addP.style.left="-2000px";
    archivioDiv.style.left="-2000px";
    addD.style.left="-2000px";
    title.innerHTML="";
}
///////////////////////////////////////////////////// DIV PAZIENTI /////////////////////////////////////////////////
function pazienti(){
  loadPazienti();
  var title=document.getElementById("titletot");
  var addP=document.getElementById("addP");
  var addD=document.getElementById("addD");
  var pazienti=document.getElementById("pazienti");
  var calendario=document.getElementById("calendario");
  var appuntamenti=document.getElementById("appuntamenti");
  var medico=document.getElementById("dottori");
  var buttIA=document.getElementById("buttonPopupIA");
  var buttPV=document.getElementById("buttonPopupPV");
  var archivioDiv=document.getElementById("archivioDiv");
    calendario.style.left="-2000px";
    appuntamenti.style.left="-1500px";
    buttIA.style.left="-1500px";
    buttPV.style.left="-1500px";
    medico.style.left="-2000px";
    archivioDiv.style.left="-2000px";
    pazienti.style.left="200px";
    addD.style.left="-2000px";
    addP.style.left="1165px";
    addP.style.top="550px";
    title.innerHTML="Pazienti";
    title.style.left="450px";
}
async function loadPazienti(){
  var url="http://localhost:8080/api/pazienti/getPazienti";
  var pazienti=document.getElementById("pazienti");
  let array = await get(url);
  let main="";
  for(let i=0; i<array.length; i++){
    main+="<div id=pazienteCard><button id=updatePaziente onclick=updatePaziente("+array[i].id+")><img src=assets/images/update2.png  width=20px height=20px></button>";
    main+="<img src=assets/images/patient.png width=20px height=20px style=margin-left:80px>";
    main+="<button id=deletePaziente onclick=deletePaziente("+array[i].id+")><img src=assets/images/close.png  width=20px height=20px></button>";
    main+="<h4 style=margin:0px> ";
    main+=/*"id:"+app[i].id+*/"nome:"+array[i].nome+" "+array[i].id+"<br>cognome:"+array[i].cognome+"<br> cf:"+array[i].cf+"<br> cell:"+array[i].cell+"<br>";
    main+="</h4>";
    main+="</div>";

  }
  pazienti.innerHTML=main;
}
function addPaziente(){
  var popup2=document.getElementById("popupPaz");
  popup2.style.display="flex";
}
function select(){
  a=document.getElementById("Terapia");
  b=document.getElementById("Codice");
  c=document.getElementById("archivioText");
  if(a.value=="primaVisita"){
  c.style.display="none";
  b.style.display="none";
  }else{
    c.style.display="inline";
    b.style.display="inline"; 
  }
}
 function closePaziente(){
 var popupPaz=document.getElementById("popupPaz");
 popupPaz.style.display="none";
}
 async function invioDatiP(){
  var nome=document.formPaziente.Nome.value;
  var cognome=document.formPaziente.Cognome.value;
  var cf=document.formPaziente.CodFisc.value;
  var cell=document.formPaziente.Cell.value;
  var archivio=document.formPaziente.Archivio.value;
  let url="http://localhost:8080/api/pazienti/getPazienti";
  let idPaziente=await getIdPaziente(nome,cognome,cf);
  if(idPaziente!=""){
    alert("paziente presente, non puo essere aggiunto");
  }else{
    alert("non presente");
    let message= {
      "nome": nome,
      "cognome": cognome,
      "cf": cf,
      "cell": cell
      };
      
    let url2="http://localhost:8080/api/pazienti/addPaziente";
    post(message,url2);
    alert("il paziente "+nome+" "+cognome+" "+cf+" "+cell+" è stato aggiunto al db");
  }
  
  /*for(let i=0; i<array.length; i++){
    if(array[i].nome==nome && array[i].cognome==cognome && array[i].cf==cf){
      let message= {
        "nome": nome,
        "cognome": cognome,
        "cf": cf,
        "cell": cell
        };
        
      let url2="http://localhost:8080/api/pazienti/addPaziente";
      post(message,url2);
      alert("il paziente "+nome+" "+cognome+" "+cf+" "+cell+" è stato aggiunto al db");
      //alert("paziente presente:"+array[i].nome+" "+array[i].cognome+" "+array[i].cf+"///"+nome+" "+cognome+" "+cf);
    }else{
      //alert("paziente non presente:"+array[i].nome+" "+array[i].cognome+" "+array[i].cf+"///"+nome+" "+cognome+" "+cf);
    

    }
  }*/

}
async function deletePaziente(id){
  let url2="http://localhost:8080/api/pazienti/deletePaziente/"+id;
  let url="http://localhost:8080/api/appuntamenti/getAppuntamenti";
  let url3="http://localhost:8080/api/appuntamenti/deleteAppuntamento/";
  var r=confirm("sei sicuro di voler eliminare questo paziente?? eliminandolo eliminerai anche tutti i suoi appuntamenti ");
  if(r==true){
  let array =await get(url);
  for(let i=0; i<array.length; i++){
    let idAppuntamento= array[i].id;
    let idPaziente=array[i].idPaziente;
    if(array[i].idPaziente==id){
      //alert("si:"+idPaziente+"id app"+idAppuntamento);
      delet(url3+idAppuntamento);
    }else{
    //alert("no:"+idPaziente+"id app"+idAppuntamento); 
    }
  }
  delet(url2);
  alert("appuntamenti e paziente eliminati");
  window.location.reload();
    }else{
  }
}
async function updatePaziente(id){
  var updatePaziente=document.getElementById("popupUpdateP");
  updatePaziente.style.display="flex";
  let url="http://localhost:8080/api/pazienti/getPaziente/"+id;
  let array= await get(url);
  let body=document.getElementById("bodyUpdateP");
  let text="";
  text="<form name=formUpdateP action=''>";
  /*text=" nome<input name=Nome id=Nome2 type=text placeholder="+data.nome+">";
  text+="cognome<input name=Cognome id=Cognome2 type=text placeholder="+data.cognome+"><br><br>";*/
  text+="<center>Nome: "+array.nome+"<br> Cognome: "+array.cognome+"<center><br>";
  text+="Codice Fiscale<input name=CodFisc2 id=CodFisc2 type=text placeholder="+array.cf+">";
  text+=" Cellulare<input name=Cell id=Cell2 type=text placeholder="+array.cell+"><br><br>";
  text+=" <button id=invioUpdatePaziente onclick=invioUpdateP("+id+")>invia</button></form>";
  body.innerHTML=text;
}
async function invioUpdateP(id){
  let url="http://localhost:8080/api/pazienti/getPaziente/"+id;
  let array= await get(url);
  //alert("updateInvio"+id);
  let cf="";
  let cell="";
  let nome=array.nome;
  let cognome=array.cognome;
  let OldCf=array.cf;
  let OldCell=array.cell;
  var NewCf=document.getElementById("CodFisc2").value;
  var NewCell=document.getElementById("Cell2").value;
  if(NewCf==""){
    cf=OldCf;
    cell=NewCell;
  }else if(NewCell==""){
    cf=NewCf;
    cell=OldCell;
  }else if(NewCf!="" && NewCell!=""){
    cf=NewCf;
    cell=NewCell;
  }
  var r=confirm("sei sicuro di voler modificare questi dati??");
  if(r==true){
    let message= {
      "nome": nome,
      "cognome": cognome,
      "cf":cf,
      "cell": cell
      };
    let url2="http://localhost:8080/api/pazienti/updatePaziente/"+id;
    //alert(data+" "+ora+" "+idPaziente+" "+medico+" "+tipoApp);
    put(message,url2);
    alert("appunatamento modificato");
  }else{
    }
  //alert(cf+" "+cell);
}
function closeUpdateP(){
  var updatePaziente=document.getElementById("popupUpdateP");
  updatePaziente.style.display="none";
}


///////////////////////////////////////////////////// DIV DOTTORI ///////////////////////////////////////////////////
function dottori(){
  loadDottori();
  var title=document.getElementById("titletot");
  var addD=document.getElementById("addD");
  var addP=document.getElementById("addP");
  var medico=document.getElementById("dottori");
  var pazienti=document.getElementById("pazienti");
  var calendario=document.getElementById("calendario");
  var appuntamenti=document.getElementById("appuntamenti");
  var buttIA=document.getElementById("buttonPopupIA");
  var buttPV=document.getElementById("buttonPopupPV");
  var archivioDiv=document.getElementById("archivioDiv");
  calendario.style.left="-2000px";
  appuntamenti.style.left="-1500px";
  pazienti.style.left="-2000px";
  addP.style.left="-2000px";
  addD.style.left="1100px";
  buttIA.style.left="-1500px";
  buttPV.style.left="-1500px";
  archivioDiv.style.left="-2000px";
  medico.style.left="250px";
  title.innerHTML="Dottori";
  title.style.left="450px";
}
async function loadDottori(){
  let url="http://localhost:8080/api/dottori/getDottori";
  let medico=document.getElementById("dottori");
  let array = await get(url);
  let main="";
  for(let i=0; i<array.length; i++){
    main+="<div id=medicoCard><button id=updateDottore onclick=updateDottore("+array[i].id+")><img src=assets/images/update2.png  width=20px height=20px></button>";
     main+="<label id=colorDoctor style=background-color:"+array[i].colore+" ><img src=assets/images/doctor.png width=20px height=20px></label>";
     main+="<button id=deleteDottore onclick=deleteDottore("+array[i].id+")><img src=assets/images/close.png  width=20px height=20px></button>";
     main+="<h4 style=margin:0px>";
     main+=/*"-id:"+app[i].id+*/"nome:"+array[i].nome+" cognome:"+array[i].cognome+" <br>cf:"+array[i].cf+" "+"colore:"+array[i].colore+"<br>"+"password:"+array[i].password+"<br>";
     main+="</h4>";
    main+="</div>";
  }
  medico.innerHTML=main;
}
async function updateDottore(id){
  //let content= document.getElementById("popupUpdateD-content");
  let popupDott= document.getElementById("popupUpdateD");
  popupDott.style.display="flex";
  let url="http://localhost:8080/api/dottori/getDottore/"+id;
  let array= await get(url);
  let body=document.getElementById("bodyUpdateD");
  let text="";
  text="<form name=formUpdateD action=''>";
  /*text=" nome<input name=Nome id=Nome2 type=text placeholder="+data.nome+">";
  text+="cognome<input name=Cognome id=Cognome2 type=text placeholder="+data.cognome+"><br><br>";*/
  text+=" <div id=imageDoctor2 style=background-color:"+array.colore+"><img src=assets/images/doctor.png width=30px height=30px ></div><br>"
  text+="<center>Nome: "+array.nome+"<br> Cognome: "+array.cognome+"<center><br>";
  text+="Codice Fiscale<input name=CodFisc2 id=CodFisc2 type=text placeholder="+array.cf+">";
  //text+=" Colore<input name=Cell id=Cell2 type=text placeholder="+array.colore+"><br><br>";
  text+="colore <select id=Colore name=Colore onchange=changeColor2() value=>"+
  "<option hidden selected>Seleziona colore</option>"+
  "<option value=blue>blue</option>"+
  "<option value=#E9C46A>giallo</option>"+
  "<option value=green>verde</option>"+
  "<option value=#E76F51>rosso</option>"+
  "<option value=#F4A261>arancione</option>"+
  "<option value=blueviolet>viola</option>"+
  "</select><br>";
  text+="Password app<input name=Pass2 id=Pass2 type=text placeholder="+array.password+"><br></br>";
  text+=" <button id=invioUpdatePaziente onclick=invioUpdateD("+id+")>invia</button></form>";
  body.innerHTML=text;
}
function closeUpdateD(){
  let popupDott= document.getElementById("popupUpdateD");
  popupDott.style.display="none";
}
async function invioUpdateD(id){
  let url="http://localhost:8080/api/dottori/getDottore/"+id;
  let array= await get(url);
  
  let cf="";
  let pass="";
  let colore=array.colore;
  let nome=array.nome;
  let cognome=array.cognome;
  let OldCf=array.cf;
  let OldPass=array.password;
  var NewCf=document.getElementById("CodFisc2").value;
  var NewPass=document.getElementById("Pass2").value;
  if(NewCf==""){
    cf=OldCf;
    pass=NewPass;
  }else if(NewPass==""){
    cf=NewCf;
    pass=OldPass;
  }else if(NewCf!="" && NewPass!=""){
    cf=NewCf;
    pass=NewPass;
  }
  var r=confirm("sei sicuro di voler modificare questi dati??");
  if(r==true){
    let message= {
      "nome": nome,
      "cognome": cognome,
      "cf":cf,
      "colore":colore,
      "password": pass
      };
    let url2="http://localhost:8080/api/dottori/updateDottore/"+id;
    //alert(nome+" "+cognome+" "+cf+" "+colore+" "+pass);
    put(message,url2);
    alert("dottore modificato");
  }else{
    }
  //alert(cf+" "+cell);
}
async function invioDatiD(){
  var nome=document.formDottore.Nome.value;
  var cognome=document.formDottore.Cognome.value;
  var cf=document.formDottore.CodFisc.value;
  var colore=document.formDottore.Colore.value;
  var password=document.formDottore.Pass.value;
  alert("invio dottore"+nome+" "+cognome+" "+cf+" "+colore+" "+password);
  let url="http://localhost:8080/api/dottori/getDottori";
  let idDottore=await getIdDottore(nome,cognome,cf);
  if(idDottore!=""){
    alert("dottore presente, non puo essere aggiunto");
  }else{
    alert("non presente");
    let message= {
      "nome": nome,
      "cognome": cognome,
      "cf": cf,
      "colore": colore,
      "password": password
      };
      
    let url2="http://localhost:8080/api/dottori/addDottore";
    post(message,url2);
    alert("il dottore "+nome+" "+cognome+" "+cf+" è stato aggiunto al db");
  }
}

function deleteDottore(){
  alert("delete dott");
}
function addDottore(){
  let content= document.getElementById("popupDott-content");
  let popupDott= document.getElementById("popupDott");
  popupDott.style.display="flex";
}
function closeDottore(){
  let popupDott= document.getElementById("popupDott");
  popupDott.style.display="none";
}
function changeColor(){
  var colore = document.getElementById('Colore');
  var red = document.getElementById('red');
  var green = document.getElementById('green');
  var blue = document.getElementById('blue');
  var dott=document.getElementById('imageDoctor');
  if(colore.value=="#E76F51"){
    dott.style.background="#E76F51";
  }else if(colore.value=="blue"){
    dott.style.background="blue";
  }else if(colore.value=="green"){
    dott.style.background="green";
  }else if(colore.value=="#E9C46A"){
    dott.style.background="#E9C46A";
  }else if(colore.value=="#F4A261"){
    dott.style.background="#F4A261";
  }else if(colore.value=="blueviolet"){
    dott.style.background="blueviolet";
  }
}
function changeColor2(){
  var colore = document.getElementById('Colore');
  var red = document.getElementById('red');
  var green = document.getElementById('green');
  var blue = document.getElementById('blue');
  var dott=document.getElementById('imageDoctor2');
  if(colore.value=="#E76F51"){
    dott.style.background="#E76F51";
  }else if(colore.value=="blue"){
    dott.style.background="blue";
  }else if(colore.value=="green"){
    dott.style.background="green";
  }else if(colore.value=="#E9C46A"){
    dott.style.background="#E9C46A";
  }else if(colore.value=="#F4A261"){
    dott.style.background="#F4A261";
  }else if(colore.value=="blueviolet"){
    dott.style.background="blueviolet";
  }
}

///////////////////////////////////////////////////// DIV ARCHIVIO /////////////////////////////////////////////////
function archivio(){
  loadArchivio();
  let dx=document.getElementById("dx");
  var title=document.getElementById("titletot");
  var addD=document.getElementById("addD");
  var addP=document.getElementById("addP");
  var archivioDiv=document.getElementById("archivioDiv");
  var calendario=document.getElementById("calendario");
  var appuntamenti=document.getElementById("appuntamenti");
  var pazienti=document.getElementById("pazienti");
  var medico=document.getElementById("dottori");
  var buttIA=document.getElementById("buttonPopupIA");
  var buttPV=document.getElementById("buttonPopupPV");
    pazienti.style.left="-2000px";
    medico.style.left="-2000px";
    calendario.style.left="-2000px";
    appuntamenti.style.left="-2000px";
    buttIA.style.left="-1500px";
    buttPV.style.left="-1500px";
    archivioDiv.style.left="450px";
    addP.style.left="-2000px";
    addD.style.left="-2000px";
    title.innerHTML="Archivio";
    title.style.left="450px";
    archivioDiv.style.width="500px";
    dx.style.left="25px";
    
}
async function loadArchivio(){
  var url="http://localhost:8080/api/pazienti/getPazienti";
  var sx=document.getElementById("sx");
  let array = await get(url);
  let main="";
  for(let i=0; i<array.length; i++){
    main+="<button id=archivioButtPaz onclick=archivioPaz("+array[i].id+")>"+array[i].id+"|"+array[i].nome+" "+array[i].cognome+"</button><br>";  
  }
  sx.innerHTML=main;
}
async function archivioPaz(id){
  //let archivioButtPaz=document.getElementById("archivioButtPaz");
  let archivioDiv=document.getElementById("archivioDiv");
  archivioDiv.style.width="850px";
  archivioDiv.style.left="280px";
  let dx=document.getElementById("dx");
  
  dx.style.left="540px";
  /*dx.style.height="500px";
  dx.style.width="300px";*/
  let main="";
  let nomeP= await getNomePaziente(id);
  main+="<h4 id=titleCartCli style=margin:0px><center>Cartella clinica di "+nomeP+"</center></h4><br>";
    let response = await fetch("http://localhost:8080/api/appuntamenti/getAppuntamenti");
    let json = await response.json();
    utenti = json;
    for(let i=0; i<utenti.length; i++){
      //alert("gg"+utenti[i].idPaziente);
      if(utenti[i].idPaziente==id){
        //archivioButtPaz.style.background="red";
        main+="<center><h4 id=elencoCartCli style=margin:0px>- "+utenti[i].giorno+" "+oraAppuntamento(utenti[i].ora)+" "+utenti[i].tipoAppuntamento+"</h4></center><br>";
        
      //main+="<button id=archivioButtPaz onclick=archivioPaz("+utenti[i].id+")>"+utenti[i].id+"|"+utenti[i].nome+" "+utenti[i].cognome+"</button><br>";
      //main+="<br>gggg";
      //alert("aaa: "+utenti[i].colore+" "+utenti[i].nome+" "+utenti[i].cognome);
      }
    }
    dx.innerHTML=main;

}
function mostraCerca(){
  let nome=document.getElementById("Cerca");
  let invioCerca=document.getElementById("buttonCerca");
  let immage=document.getElementById("buttonCerca2");
  if(nome.style.top=="1px"){
    immage.style.left="250px";
    nome.style.top="50px";
    invioCerca.style.top="50px";
  }else{
    immage.style.left="100px";
    nome.style.top="1px";
    invioCerca.style.top="1px";
  }
}
async function cerca(){
  let immage=document.getElementById("buttonCerca2");
  let nome=document.getElementById("Cerca").value;
  var url="http://localhost:8080/api/pazienti/getPazienti";
  let array = await get(url);
  for(let i=0; i<array.length; i++){
    let paziente=array[i].nome+" "+array[i].cognome;
    if(nome==paziente){
      archivioPaz(array[i].id); 
    }  
  }
}
///////////////////////////////////////////////////// DIV MESSAGGI /////////////////////////////////////////////////

/*function loadDoc() {
  let cont=0;
  setInterval(function(){
  
   var xhttp = new XMLHttpRequest();
   xhttp.onreadystatechange = function() {
    if (this.readyState == 4 && this.status == 200) {
      if(this.responseText==4){
        console.log(cont);
      }
      
     document.getElementById("msg2").innerHTML = cont;
    }
    
   };
   xhttp.open("GET", "http://localhost:8080/api/messaggi/getMessaggi", true);
   xhttp.send();

  },1000);
  cont++;

 }*/

 async function loadDoc(){
  setInterval(async function(){
  var url="http://localhost:8080/api/messaggi/getMessaggi";
  let array = await get(url);
  let length="0";
  let icon=document.getElementById("msgNumber");
  for(let i=0; i<array.length; i++){
    length=array.length;
    /*console.log(length+"hhhhh");
    if(length==""){
      document.getElementById("msgNumber").style.visibility = "hidden";
    }else{*/  document.getElementById("msgNumber").style.visibility = "visible";
    document.getElementById("msgNumber").innerHTML =length;
      //}
    }
    if(length==0){
      document.getElementById("msgNumber").style.visibility = "hidden";
    }
  },1000);
}
 loadDoc();
function msg(){
  loadMessaggi();
  //alert("sei nel div messaggi");
  var msg=document.getElementById("messaggi");
  if(msg.style.left=="970px"){
  msg.style.left="-2000px";
  }else{
    msg.style.left="970px";
  }
}
async function loadMessaggi(){
  var url="http://localhost:8080/api/messaggi/getMessaggi";
  var mess=document.getElementById("messaggio");
  var messDoc=document.getElementById("messDoc");
  var bodyMsg=document.getElementById("bodyMsg");
  let array = await get(url);
  let main="";
  let length="";
  for(let i=0; i<array.length; i++){
    length=array.length;
    let a=array[i].message.split(" ");
    let idD=a[2];
    let idP=a[15]; 
    let operazione=a[4];
    let giorno=a[9];
    let ora=a[12];
    let idAppu=array[i].id;
    console.log(idAppu+" ");
    let nomeDoc= await getNomeDottore(idD);
    let col=await getColoreDottore(idD);
    let nomePaz= await getNomePaziente(idP);
    let text="";
    if(operazione=="eliminare"){
      //text+="<center><h4 id=colorElimina>ELIMINA</h4></center>";
      text+="<h5 id=colorElimina>ELIMINA</h5><button id=deleteMsg onClick=deleteMsg("+idAppu+")><img src=assets/images/close.png width=20px height=20px></button><br><br>";
      text+="<h5 id=textMsgEli>il dottore "+nomeDoc+" desidera "+operazione+" l'appuntamento del "+giorno+" delle ore "+oraAppuntamento(ora)+". <br><br> Paziente: "+nomePaz+"</h5>";
    }else if(operazione=="modificare"){
      text+="<h5 id=colorModifica>MODIFICA</h5><button id=deleteMsg2 onClick=deleteMsg("+idAppu+")><img src=assets/images/close.png width=20px height=20px></button><br>";
      //text+="<center><h4 id=colorModifica>MODIFICA</h4></center>";
      text+="<h5 id=textMsgMod>il dottore "+nomeDoc+" desidera "+operazione+" l'appuntamento del "+giorno+" delle ore "+oraAppuntamento(ora)+". <br><br> Paziente: "+nomePaz+"";
      text+="<br> Data/ora:"+a[22]+" "+a[21]+"</h5>";
    }
    let testo=""+a[0]+" "+a[1]+" "+nomeDoc+" "+a[3]+" "+a[4]+" "+a[5]+" "+a[6]+" "+a[7]+" "+a[8]+" "+a[9]+" "+a[10]+" "+a[11]+" "+a[12]+" "+a[13]+" "+a[14]+":<br> ";
    main+="<div id=messDoc style=background-color:"+col+"><img src=assets/images/doctor.png width=40px height=40px>";
    main+="<div id=messaggio2>";
    main+="<h5 id=messageStyle>"+text+"</h5>";
    main+="</div></div>";
  } 
  bodyMsg.innerHTML=main;
}
async function deleteMsg(id){
  let url="http://localhost:8080/api/messaggi/deleteMessaggio/"+id;
  delet(url);
  window.location.reload();
}


///////////////////////////////////////////////////// DIV LEGEND /////////////////////////////////////////////////

async function legend2(){
  let legendTerapie=document.getElementById("legendTerapie");
  let text="";
  text+="<div id=cerchio style=background-color:blue;></div><h5 id=terapia>seduta</h5><br>";
  text+="<div id=cerchio style=background-color:red;></div><h5 id=terapia>siringa</h5><br>";
  text+="<div id=cerchio style=background-color:orange;></div><h5 id=terapia>prima visita</h5><br>";
  legendTerapie.innerHTML=text;
  let url3="http://localhost:8080/api/dottori/getDottori";
  let legendDottori=document.getElementById("legendDottori");
  let array = await get(url3);
  let text2="";
  for(let i=0; i<array.length; i++){
     text2+="<div id=iconDott style=background-color:"+array[i].colore+"><img src=assets/images/doctor.png  width=20 height=20></div>";
     text2+="<h5 id=nomeDott>"+array[i].nome+" "+array[i].cognome+"</h5>";
  }
  legendDottori.innerHTML=text2;

}


















































































































//////////////////////////////////  FUNZIONI GENERALI  //////////////////////////////////////////////////////////////

window.onload=function(){
  renderCalendar();
  loadAppuntamenti();
  legend2();
}

async function get(url){
  let response=await fetch(url);
  let array = await response.json();
  return array;
}
async function post(message,url){
  let response= await fetch(url,{
    method:'POST',
    headers: {'Content-Type': 'application/json;charset=utf-8'},
    body:JSON.stringify(message)
  });
  let result = await response.json();
}
async function delet (url){
  //alert("addPaziente:"+user);
  let response =await fetch(url,{
  method:'DELETE',
  headers: {'Content-Type': 'application/json;charset=utf-8'},
  //body:JSON.stringify(user)
  });
  //let result = await response.json();
  //alert(result.message);
}
async function put(message,url){
  let response= await fetch(url,{
    method:'PUT',
    headers: {'Content-Type': 'application/json;charset=utf-8'},
    body:JSON.stringify(message)
  });
  //let result = await response.json();
}



async function getNomePaziente(id){
  let baseUrl2="http://localhost:8080/api/pazienti/getPaziente/";
  let response2 = await fetch(baseUrl2+id);
  let data2 = await response2.json();
  let s=data2.nome+" "+data2.cognome;
  //console.log(s);
  return s;
}
async function getNomeDottore(id){
  let baseUrl2="http://localhost:8080/api/dottori/getDottore/";
  let response2 = await fetch(baseUrl2+id);
  let data2 = await response2.json();
  let s=data2.nome+" "+data2.cognome;
  //console.log(s);
  return s;
}
async function getColoreDottore(id){
  let baseUrl="http://localhost:8080/api/dottori/getDottore/";
  let response5 = await fetch(baseUrl+id);
  let data8 = await response5.json();
  //const s=data.nome+" "+data.cognome;
  let s3=data8.colore;
  //console.log(data8.colore);
  return s3;
}

function oraAppuntamento(ora){
  let split=ora.split(":");
  let oraGiusta=split[0]+":"+split[1];
  return oraGiusta;
}
function dataAppuntamento(j,ddd){
  var giornoCalend="";
  var gg="";
  var mm="";
    if(j>0 && j<10){
      gg+="0"+j;
      mm+="0"+ddd;
    }else if(j>=10){
      gg=j;
      mm="0"+ddd;
    }
  giornoCalend+=gg+"/"+mm+"/2022";
  return giornoCalend;
}
function oraEsatta(ora){
  /*let oraEsatta="";
  let ora2=ora.split(":");
  let hh=ora2[0];
  let mm=ora2[1];
  if(hh<12){
    oraEsatta+=hh+":"+mm+":00 AM";
    return oraEsatta;
  }else if(hh>=12){
    oraEsatta+=hh+":"+mm+":00 PM";
    return oraEsatta;
  }
  return null;*/
  let oraEsatta="";
  let ora2=ora.split(":");
  let hh=ora2[0];
  let mm=ora2[1];
  /*if(hh>00 && hh<11 && mm==59){
    oraEsatta=hh+":"+mm+":00 AM";
    getLocaleTimeFormat
  }else if(hh>=12 && hh<23 && mm==59){
    oraEsatta=hh+":"+mm+":00 PM";
  }*/
  oraEsatta=hh+":"+mm+":"+"00";
  return oraEsatta;
}  
function dataEsatta(data){
    let data2=data.split("-");
    let aaaa=data2[0];
    let mm=data2[1];
    let gg=data2[2];
    let dataEsatta=gg+"/"+mm+"/"+aaaa;
    return dataEsatta;
}
async function getIdPaziente(nome,cognome,cf){
  let response = await fetch("http://localhost:8080/api/pazienti/getPazienti");
  let array = await response.json();
  let risp="";
  for(let i=0; i<array.length; i++){
  if(nome==array[i].nome && cognome==array[i].cognome && cf==array[i].cf){
    //alert("paziente presente"+array[i].cf);
      risp+=array[i].id;//forse qui mettere uguale
    }else{
      
    //alert("paziente non presente"+array[i].cf);
      //risp+="paziente non presente";
    }
  }
  return risp;
}

async function getIdDottore(nome,cognome,cf){
  let response = await fetch("http://localhost:8080/api/dottori/getDottori");
  let array = await response.json();
  let risp="";
  for(let i=0; i<array.length; i++){
  if(nome==array[i].nome && cognome==array[i].cognome && cf==array[i].cf){
    //alert("paziente presente"+array[i].cf);
      risp+=array[i].id;//forse qui mettere uguale
    }else{
    //alert("paziente non presente"+array[i].cf);
      //risp+="paziente non presente";
    }
  }
  return risp;
}