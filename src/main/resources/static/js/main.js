/* Manejo de alertas y busqueda en tablas */
document.addEventListener('DOMContentLoaded', () => {
  /* Desvanecer unicamente mensajes de alerta temporales */
  const alerts = document.querySelectorAll('p[th\\:if*="message"], p[th\\:if*="error"], p[th\\:if*="success"], p[th\\:if*="messsage"]');
  alerts.forEach(alert => {
    if (alert.textContent.trim().length > 0) {
      setTimeout(() => {
        alert.style.transition = 'opacity 0.5s ease';
        alert.style.opacity = '0';
        setTimeout(() => alert.style.display = 'none', 500);
      }, 4000);
    }
  });

  /* Filtro en tiempo real para tablas */
  const tables = document.querySelectorAll('table');
  tables.forEach(table => {
    const tbody = table.querySelector('tbody');
    if (!tbody || tbody.rows.length <= 1) return;

    const searchContainer = document.createElement('div');
    searchContainer.className = 'table-filter-container';

    const searchInput = document.createElement('input');
    searchInput.type = 'text';
    searchInput.placeholder = '🔍 Buscar';
    searchInput.className = 'table-filter-input';

    searchContainer.appendChild(searchInput);
    table.parentNode.insertBefore(searchContainer, table);

    searchInput.addEventListener('input', (e) => {
      const term = e.target.value.toLowerCase().trim();
      const rows = tbody.querySelectorAll('tr');

      rows.forEach(row => {
        const text = row.textContent.toLowerCase();
        row.style.display = text.includes(term) ? '' : 'none';
      });
    });
  });
});
