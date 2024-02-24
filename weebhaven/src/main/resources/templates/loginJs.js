document.addEventListener('DOMContentLoaded', function() {
    const wrapper = document.querySelector('.wrapper');
    const loginLink = document.querySelector('.login-link');
    const registerLink = document.querySelector('.register-link');
    const btnPopup = document.querySelector('.btnLogin-popup');
    const iconClose = document.querySelector('.icon-close');

    const body=document.body;
    let keepPopupOpen = false;

    
    function togglePopup() {
        if (!keepPopupOpen) {
            wrapper.classList.toggle('active-popup');
            // Add or remove a class to body to prevent scrolling
            // body.classList.toggle('popup-open');
            parent.postMessage('stop-scrolling', '*');
        }
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

    
    btnPopup.addEventListener('click', function() {
        togglePopup();
        keepPopupOpen = true; // Set the flag to true when the button is clicked
    });


    iconClose.addEventListener('click', function() {
        keepPopupOpen = false; // Reset the flag when the close icon is clicked
        closePopup();
    });

});

