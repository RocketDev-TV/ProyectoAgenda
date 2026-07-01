document.addEventListener('DOMContentLoaded', function () {

    // --- SweetAlert2: lee flash-attributes desde el elemento hidden del layout ---
    const flashEl = document.getElementById('flash-data');
    if (flashEl && typeof Swal !== 'undefined') {
        const Toast = Swal.mixin({
            toast: true,
            position: 'top-end',
            showConfirmButton: false,
            timer: 4000,
            timerProgressBar: true
        });
        const exito = flashEl.dataset.exito || null;
        const info  = flashEl.dataset.info  || null;
        const error = flashEl.dataset.error || null;

        if (exito) Toast.fire({ icon: 'success', title: exito });
        if (info)  Toast.fire({ icon: 'success', title: info });
        if (error) Toast.fire({ icon: 'error',   title: error });
    }

    // --- Ojito: toggle show/hide password (funciona en registro y edición) ---
    const toggleBtn   = document.getElementById('togglePassword');
    const passwordInput = document.getElementById('password');
    if (toggleBtn && passwordInput) {
        toggleBtn.addEventListener('click', function () {
            const type = passwordInput.getAttribute('type') === 'password' ? 'text' : 'password';
            passwordInput.setAttribute('type', type);
            this.classList.toggle('bi-eye');
            this.classList.toggle('bi-eye-slash');
        });
    }
});
