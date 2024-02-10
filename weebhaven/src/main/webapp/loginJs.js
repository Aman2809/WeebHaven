document.addEventListener('DOMContentLoaded', function() {
    const wrapper = document.querySelector('.wrapper');
    const loginLink = document.querySelector('.login-link');
    const registerLink = document.querySelector('.register-link');
    const btnPopup = document.querySelector('.btnLogin-popup');
    const iconClose = document.querySelector('.icon-close');

    const body=document.body;

    
    function togglePopup() {
        wrapper.classList.toggle('active-popup');
        // Add or remove a class to body to prevent scrolling
        // body.classList.toggle('popup-open');
        parent.postMessage('stop-scrolling', '*');
    }

    function closePopup() {
        wrapper.classList.remove('active-popup');
        // Send message to parent to allow scrolling
        parent.postMessage('allow-scrolling', '*');
      }

     

    
    registerLink.addEventListener('click', function() {
        wrapper.classList.add('active');
    });

    
    loginLink.addEventListener('click', function() {
        wrapper.classList.remove('active');
    });

    
    btnPopup.addEventListener('click', togglePopup);

    
    iconClose.addEventListener('click', togglePopup);
    
    if (iconClose) {
        iconClose.addEventListener('click', closePopup);
      }

});

