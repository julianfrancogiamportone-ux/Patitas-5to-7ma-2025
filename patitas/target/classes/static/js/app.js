/* global window, fetch */
/* Simple app.js que funciona en todas las páginas:
   - mantiene carrito en localStorage
   - inyecta productos y servicios (simulados)
   - maneja turnos y formularios
   - actualiza contador del header
*/

/* Datos de ejemplo (reemplazá por fetch a tus endpoints cuando quieras) */
const sampleProducts = [
  {id:1, title:'Alimento Balanceado Adulto 3kg', price:13900, img:'/img/logo.png', desc:'Balanceado premium para perros adultos.'},
  {id:2, title:'Shampoo Dermatológico 250ml', price:2500, img:'/img/logo.png', desc:'Suave, para piel sensible.'},
  {id:3, title:'Antipulgas Spot-on', price:4200, img:'/img/logo.png', desc:'Protección mensual.'},
  {id:4, title:'Collar de entrenamiento', price:3200, img:'/img/logo.png', desc:'Resistente y ajustable.'}
];

const sampleServices = [
  {id:1, title:'Consulta general', duration:'30 min', price:4500, desc:'Evaluación clínica completa.'},
  {id:2, title:'Vacunación', duration:'15 min', price:2000, desc:'Vacunas reglamentarias y control.'},
  {id:3, title:'Esterilización (canina)', duration:'2-3 hr', price:42000, desc:'Cirugía con internación básica.'},
  {id:4, title:'Estética y baño', duration:'1 hr', price:6500, desc:'Baño, corte y secado.'}
];

/* Carrito en localStorage */
const CART_KEY = 'vet_cart_v1';
function getCart(){ try{ return JSON.parse(localStorage.getItem(CART_KEY)) || []; }catch(e){ return [] } }
function setCart(c){ localStorage.setItem(CART_KEY, JSON.stringify(c)); updateHeaderCartCount(); }

/* header cart count */
function updateHeaderCartCount(){
  const cnt = getCart().reduce((s,i)=>s+(i.qty||0),0);
  const span = document.getElementById('cartCount');
  if(span) span.textContent = cnt;
}

/* render products en productos.html o index.html si existe productList */
function renderProducts(list=sampleProducts){
  const el = document.getElementById('productList'); if(!el) return;
  el.innerHTML = '';
  list.forEach(p=>{
    const card = document.createElement('div'); card.className='product card';
    card.innerHTML = `
      <img src="${p.img}" alt="${p.title}" />
      <h3>${p.title}</h3>
      <div class="meta">${p.desc}</div>
      <div style="display:flex;justify-content:space-between;align-items:center;margin-top:12px">
        <div class="price">$${p.price.toLocaleString()}</div>
        <div>
          <button class="btn" onclick="addToCart(${p.id})">Agregar</button>
          <button class="btn secondary" onclick="viewProduct(${p.id})">Detalle</button>
        </div>
      </div>
    `;
    el.appendChild(card);
  });
}

/* render services en servicios.html */
function renderServices(list=sampleServices){
  const el = document.getElementById('serviceList'); if(!el) return;
  el.innerHTML = '';
  list.forEach(s=>{
    const li = document.createElement('li'); li.className='card';
    li.innerHTML = `
      <div style="display:flex;justify-content:space-between;align-items:center">
        <div>
          <h3>${s.title}</h3>
          <div class="muted">${s.duration} • $${s.price.toLocaleString()}</div>
          <p class="muted" style="margin-top:8px">${s.desc}</p>
        </div>
        <div>
          <button class="btn" onclick="bookService(${s.id})">Pedir</button>
        </div>
      </div>
    `;
    el.appendChild(li);
  });
}

/* detalles */
function viewProduct(id){
  const p = sampleProducts.find(x=>x.id===id); if(!p) return alert('Producto no encontrado');
  // detalle simplificado (puedes cambiar por modal o nueva página)
  alert(`${p.title}\n\n${p.desc}\n\nPrecio: $${p.price.toLocaleString()}`);
}

/* carrito */
function addToCart(id){
  const prod = sampleProducts.find(x=>x.id===id); if(!prod) return;
  const cart = getCart();
  const found = cart.find(i=>i.id===id);
  if(found) found.qty = (found.qty||1)+1;
  else cart.push({...prod, qty:1});
  setCart(cart);
  if(document.getElementById('cartView')) renderCartView();
  alert('Producto agregado al carrito');
}

function renderCartView(){
  const view = document.getElementById('cartView'); if(!view) return;
  const cart = getCart();
  view.innerHTML = '';
  if(cart.length===0){ view.innerHTML = '<p class="muted">El carrito está vacío</p>'; return; }
  let total = 0;
  cart.forEach(item=>{
    const row = document.createElement('div'); row.className='cart-row';
    row.innerHTML = `<div>
                        <strong>${item.title}</strong><br><small class="muted">${item.desc}</small>
                     </div>
                     <div>
                        <div>$${(item.price*item.qty).toLocaleString()}</div>
                        <div style="display:flex;gap:6px;margin-top:6px">
                          <button class="btn ghost" onclick="changeQty(${item.id},-1)">-</button>
                          <span style="padding:6px 10px;border-radius:8px;background:#f1fbff">${item.qty}</span>
                          <button class="btn ghost" onclick="changeQty(${item.id},1)">+</button>
                          <button class="btn secondary" onclick="removeFromCart(${item.id})">Eliminar</button>
                        </div>
                     </div>`;
    view.appendChild(row);
    total += item.price * item.qty;
  });
  const tot = document.createElement('div'); tot.style.marginTop='12px'; tot.style.fontWeight='700'; tot.textContent = `Total: $${total.toLocaleString()}`;
  view.appendChild(tot);
}

/* cambiar cantidad */
function changeQty(id, delta){
  const cart = getCart();
  const it = cart.find(x=>x.id===id); if(!it) return;
  it.qty = (it.qty||1) + delta;
  if(it.qty <= 0) {
    const idx = cart.findIndex(x=>x.id===id); cart.splice(idx,1);
  }
  setCart(cart); renderCartView();
}

/* quitar */
function removeFromCart(id){
  const cart = getCart().filter(x=>x.id!==id);
  setCart(cart); renderCartView();
}

/* vaciar carrito */
function clearCart(){
  setCart([]); renderCartView();
}

/* checkout simulado */
function checkout(){
  const cart = getCart();
  if(cart.length===0) return alert('Carrito vacío');
  // en real: POST a /api/carritos
  const total = cart.reduce((s,i)=>s+i.price*i.qty,0);
  alert('Checkout simulado. Total: $' + total.toLocaleString());
  setCart([]); renderCartView();
}

/* turnos: enviar/mostrar en tabla simple */
function initTurnos(){
  const form = document.getElementById('turnoForm'); if(!form) return;
  form.addEventListener('submit', function(e){
    e.preventDefault();
    const owner = document.getElementById('owner').value.trim();
    const pet = document.getElementById('pet').value.trim();
    const dt = document.getElementById('turnDate').value;
    const svc = document.getElementById('turnService').value;
    if(!owner||!pet||!dt) return document.getElementById('turnMsg').textContent = 'Completa todos los campos';
    // guardar local (simulación)
    const stored = JSON.parse(localStorage.getItem('vet_turns')||'[]');
    stored.unshift({owner,pet,service:svc,datetime:dt,created: new Date().toISOString()});
    localStorage.setItem('vet_turns', JSON.stringify(stored));
    document.getElementById('turnMsg').textContent = `Turno solicitado para ${pet} (${owner}) el ${new Date(dt).toLocaleString()}`;
    form.reset();
    renderTurnList();
  });
}

function renderTurnList(){
  const el = document.getElementById('turnList'); if(!el) return;
  const stored = JSON.parse(localStorage.getItem('vet_turns')||'[]');
  if(stored.length===0){ el.innerHTML = '<p class="muted">No hay turnos</p>'; return; }
  el.innerHTML = '';
  stored.forEach(t=>{
    const row = document.createElement('div'); row.className='card';
    row.innerHTML = `<div style="display:flex;justify-content:space-between;align-items:center">
                      <div>
                        <strong>${t.pet}</strong> • <span class="muted">${t.service}</span>
                        <div class="muted">${t.owner}</div>
                      </div>
                      <div class="muted">${new Date(t.datetime).toLocaleString()}</div>
                    </div>`;
    el.appendChild(row);
  });
}

/* book service quick */
function bookService(id){
  const s = sampleServices.find(x=>x.id===id);
  if(!s) return;
  const owner = prompt('Tu nombre:'); if(!owner) return;
  const pet = prompt('Nombre de la mascota:'); if(!pet) return;
  const dt = prompt('Fecha y hora (YYYY-MM-DDTHH:mm):'); if(!dt) return;
  // save to "turns"
  const stored = JSON.parse(localStorage.getItem('vet_turns')||'[]');
  stored.unshift({owner,pet,service:s.title,datetime:dt,created:new Date().toISOString()});
  localStorage.setItem('vet_turns', JSON.stringify(stored));
  alert('Turno reservado: ' + s.title + ' para ' + pet);
  renderTurnList();
}

/* búsqueda en products */
function initSearch(){
  const btn = document.getElementById('btnSearch'); if(!btn) return;
  btn.addEventListener('click', ()=>{
    const q = (document.getElementById('search')?.value||'').trim().toLowerCase();
    if(!q) return renderProducts();
    renderProducts(sampleProducts.filter(p => p.title.toLowerCase().includes(q) || p.desc.toLowerCase().includes(q)));
  });
}

/* init general en todas las páginas */
document.addEventListener('DOMContentLoaded', ()=>{
  // header cart count
  updateHeaderCartCount();

  // render depending elements
  renderProducts();
  renderServices();
  renderCartView();
  initTurnos();
  renderTurnList();
  initSearch();

  // attach global cart buttons if exist
  const clearBtn = document.getElementById('clearCart'); if(clearBtn) clearBtn.addEventListener('click', clearCart);
  const checkoutBtn = document.getElementById('checkoutBtn'); if(checkoutBtn) checkoutBtn.addEventListener('click', checkout);

  // attach quick header cart icon click (if exists)
  const cartLinks = document.querySelectorAll('.go-cart'); cartLinks.forEach(a=>a.addEventListener('click', (e)=>{ e.preventDefault(); location.href='/carrito.html' }));
});
