
/* ASIDE MOVE FUNCTION ------------------------------------------*/


const btnOpen = document.querySelector('.btn-open-side');
const aside = document.querySelector('.aside');
const btnClose = document.querySelector('.btn-close-side');

btnOpen.addEventListener('click', ()=> {

  if (aside.className == "aside") { 
    aside.classList.add('asideMoveRight');  
    aside.classList.remove('aside');
    btnOpen.style.display = "none";
    btnClose.classList.add('btn-close-side-enabled');
    btnClose.classList.remove('btn-close-side');
  } 

});

btnClose.addEventListener ('click', ()=> {

    if (aside.className == 'asideMoveRight') {
      btnOpen.style.display = "block";
      aside.classList.add('aside');
      aside.classList.remove('asideMoveRight');
      btnClose.classList.add('btn-close-side');
      btnClose.classList.remove('btn-close-side-enabled');
    }

});
