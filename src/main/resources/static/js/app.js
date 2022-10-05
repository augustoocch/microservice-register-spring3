
/* RESPONSIVE NAV FUNCTION ------------------------------------------*/

const menuDeployable = document.querySelector('.icon');
const topnav = document.querySelector('.topnav');
const menuX = document.querySelector('#menuX');
const menuBurger = document.querySelector('#menuBurger')


menuDeployable.addEventListener('click', deploy);
menuX.addEventListener('click', deploy);

function deploy() {
        const x = document.getElementById("myTopnav");
        if (x.className === "topnav") {
          x.className += " responsive";
          menuBurger.classList.add('hide');
          menuX.classList.remove('hide');
        } else {
          x.className = "topnav";
          menuBurger.classList.remove('hide');
          menuX.classList.add('hide');
        }
}


/* LOGIN DEPLOY BUTTON FUNCTION ------------------------------------------*/

const botonHb = document.querySelector(".buttonStart");
const linkButtonHb = document.querySelector(".deployLogin");
botonHb.addEventListener('click', deployHb); 

function deployHb () {
    console.log('deployando');
    if (linkButtonHb.className === "deployLogin") {
        linkButtonHb.classList.add("deployed");
    } else {
        linkButtonHb.classList.remove("deployed");
    }
};