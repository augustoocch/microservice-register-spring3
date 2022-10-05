/* VALIDATE LOGIN FUNCTION------------------------------------------*/

const email = document.querySelector('#emailLog');
const inputsLoginRegister = document.querySelectorAll('.logInput');
const form = document.querySelector('.formLog');
email.addEventListener('blur', validEmail);
const buttonLogin = document.querySelector('#logRegister');
const mailformat = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;

loginInit();

function loginInit() {
  buttonLogin.disabled = true;
}

/* Email validation ------------------------------------------------------*/

function validEmail() {

  if (email.value.match(mailformat)) {
    if (document.querySelector('.error')) {
        form.children[2].remove();
    }
    green(email);
  } else {
    empty("Invalid email");
    red(email);
  }
}

/* Input validations ------------------------------------------------------*/
inputValidate();

function inputValidate() {
  
  for (const inputSelected of inputsLoginRegister) {
    inputSelected.addEventListener('blur', ()=> {
      if(inputSelected.value.length == 0) {
        empty("All fields must be filled");
        red(inputSelected);
      } else{
        if (document.querySelector('.error')){
          form.children[3].remove();
          green(inputSelected);
        }
      }
      send();
    });
  };
}

/* Function that deploys the message that field is empty ------------------------------------------------------*/

function empty(msg) {

  //If error already exist, change message
  if (document.querySelector('.error')) {
      document.querySelector('.error').children[0].innerText = msg;
  } else {

  //If the error exist:
      //Create div
      const elementMessage = document.createElement('div');
      elementMessage.classList.add('error');
      //Create paragraph
      
      const paragph = document.createElement('p');
      paragph.innerText = msg;
      elementMessage.append(paragph);
      
      //Insert in body
      form.appendChild(elementMessage);
  }
}

/* Function that makes send button available------------------------------------------------------*/

function send() {
  //If forms not completed, button disabled
  for (const inputSelected of inputsLoginRegister) {
    if (inputSelected.value.length != 0 && email.value.match(mailformat)){
      buttonLogin.disabled = false;
      buttonLogin.style.opacity = "100";
    } else {
      buttonLogin.disabled = true;
    }
  }
}


/* Function that input red ------------------------------------------------------*/
function red(msg) {
    msg.style.border = ('1px solid red');
}

/* Function that input green ------------------------------------------------------*/
function green(msg) {
  msg.style.border = ('1px solid green');
}