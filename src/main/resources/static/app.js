document.addEventListener('DOMContentLoaded', () => {

    // --- State & Helpers ---
    const API = {
        users: '/api/users',
        menu: '/api/menu',
        orders: '/api/orders',
        messages: '/api/messages'
    };

    const qs = (sel) => document.querySelector(sel);
    const qsa = (sel) => document.querySelectorAll(sel);

    // --- Tab Switching ---
    qsa('.tab-btn').forEach(btn => {
        btn.addEventListener('click', () => {
            qsa('.tab-btn').forEach(b => b.classList.remove('active'));
            qsa('.tab-content').forEach(c => c.classList.remove('active'));
            btn.classList.add('active');
            qs(`#${btn.dataset.tab}-tab`).classList.add('active');
            refreshTab(btn.dataset.tab);
        });
    });

    const refreshTab = (tab) => {
        if (tab === 'users') loadUsers();
        if (tab === 'menu') { loadDishes(); loadRestaurantsList(); }
        if (tab === 'orders') { loadOrders(); loadOrderPrerequisites(); }
        if (tab === 'messages') { loadMessages(); loadMessagePrerequisites(); }
    };

    /* ==========================================================================
       USER MANAGEMENT
       ========================================================================== */
    const userTypeSelect = qs('#userType');
    const updateVisibleFields = () => {
        const type = userTypeSelect.value.toLowerCase();
        qsa('.field-box[class*="type-"]').forEach(el => el.classList.add('hidden'));
        qsa(`.type-${type}`).forEach(el => el.classList.remove('hidden'));
    };
    userTypeSelect.addEventListener('change', updateVisibleFields);

    const loadUsers = async () => {
        const filter = qs('#userTypeFilter').value;
        const res = await fetch(`${API.users}${filter ? '?type=' + filter : ''}`);
        const users = await res.json();
        const tbody = qs('#usersTable tbody');
        tbody.innerHTML = '';
        users.forEach(u => {
            const tr = document.createElement('tr');
            const type = u.userType || 'User';
            tr.innerHTML = `
                <td>${u.id}</td>
                <td><strong>${u.username}</strong></td>
                <td><span class="badge">${type}</span></td>
                <td>${u.email || '-'}</td>
                <td>${u.phoneNum || '-'}</td>
                <td>
                    <button class="action-btn" onclick="editUser(${JSON.stringify(u).replace(/"/g, '&quot;')})">Edit</button>
                    <button class="action-btn delete" onclick="deleteUser(${u.id})">Del</button>
                </td>
            `;
            tbody.appendChild(tr);
        });
    };

    qs('#userTypeFilter').addEventListener('change', loadUsers);

    qs('#userForm').addEventListener('submit', async (e) => {
        e.preventDefault();
        const type = qs('#userType').value;
        const id = qs('#userId').value;
        
        const payload = {
            id: id ? parseInt(id) : 0,
            username: qs('#username').value,
            password: qs('#password').value,
            email: qs('#email').value,
            phoneNum: qs('#phoneNum').value
        };

        if (type === 'Customer') {
            payload.name = qs('#firstName').value;
            payload.surname = qs('#lastName').value;
            payload.address = qs('#address').value;
            payload.cardNo = qs('#cardNo').value;
        } else if (type === 'Driver') {
            payload.name = qs('#firstName').value;
            payload.surname = qs('#lastName').value;
            payload.driverLicence = qs('#license').value;
        } else if (type === 'Restaurant') {
            payload.address = qs('#address').value;
            payload.description = qs('#description').value;
        }

        await fetch(`${API.users}/${type.toLowerCase()}`, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(payload)
        });
        resetUserForm();
        loadUsers();
    });

    window.editUser = (u) => {
        qs('#userFormTitle').innerText = 'Edit User #' + u.id;
        qs('#userId').value = u.id;
        qs('#username').value = u.username;
        qs('#password').value = u.password;
        qs('#email').value = u.email || '';
        qs('#phoneNum').value = u.phoneNum || '';
        
        const type = u.userType || (u.driverLicence ? 'Driver' : (u.description ? 'Restaurant' : 'Customer'));
        
        qs('#userType').value = type;
        updateVisibleFields();

        if (type === 'Customer') {
            qs('#firstName').value = u.name || '';
            qs('#lastName').value = u.surname || '';
            qs('#address').value = u.address || '';
            qs('#cardNo').value = u.cardNo || '';
        } else if (type === 'Driver') {
            qs('#firstName').value = u.name || '';
            qs('#lastName').value = u.surname || '';
            qs('#license').value = u.driverLicence || '';
        } else if (type === 'Restaurant') {
            qs('#address').value = u.address || '';
            qs('#description').value = u.description || '';
        }
    };

    window.deleteUser = async (id) => {
        if (confirm('Delete user?')) {
            await fetch(`${API.users}/${id}`, { method: 'DELETE' });
            loadUsers();
        }
    };

    window.resetUserForm = () => {
        qs('#userForm').reset();
        qs('#userId').value = '';
        qs('#userFormTitle').innerText = 'Onboard / Edit User';
        updateVisibleFields();
    };

    /* ==========================================================================
       MENU MANAGEMENT
       ========================================================================== */
    const loadDishes = async () => {
        const res = await fetch(API.menu);
        const dishes = await res.json();
        const tbody = qs('#dishesTable tbody');
        tbody.innerHTML = '';
        dishes.forEach(d => {
            const tr = document.createElement('tr');
            tr.innerHTML = `
                <td>${d.id}</td>
                <td><strong>${d.title}</strong></td>
                <td>$${d.price}</td>
                <td><span class="badge">${d.category}</span></td>
                <td>${d.restaurant ? d.restaurant.username : '-'}</td>
                <td>
                    <button class="action-btn" onclick="editDish(${JSON.stringify(d).replace(/"/g, '&quot;')})">Edit</button>
                    <button class="action-btn delete" onclick="deleteDish(${d.id})">Del</button>
                </td>
            `;
            tbody.appendChild(tr);
        });
    };

    const loadRestaurantsList = async () => {
        const res = await fetch(`${API.users}?type=Restaurant`);
        const rests = await res.json();
        const select = qs('#dishRestaurant');
        select.innerHTML = rests.map(r => `<option value="${r.id}">${r.username}</option>`).join('');
    };

    qs('#dishForm').addEventListener('submit', async (e) => {
        e.preventDefault();
        const id = qs('#dishId').value;
        const payload = {
            id: id ? parseInt(id) : 0,
            title: qs('#dishTitle').value,
            price: parseFloat(qs('#dishPrice').value || 0),
            category: qs('#dishCategory').value || 'MAIN_COURSE',
            preparationTimeMin: parseInt(qs('#prepTime').value || 0),
            calories: parseInt(qs('#calories').value || 0),
            restaurant: { id: parseInt(qs('#dishRestaurant').value) }
        };
        await fetch(API.menu, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(payload)
        });
        resetDishForm();
        loadDishes();
    });

    window.editDish = (d) => {
        qs('#dishFormTitle').innerText = 'Edit Dish #' + d.id;
        qs('#dishId').value = d.id;
        qs('#dishTitle').value = d.title;
        qs('#dishPrice').value = d.price;
        qs('#dishCategory').value = d.category;
        qs('#dishRestaurant').value = d.restaurant ? d.restaurant.id : '';
        qs('#prepTime').value = d.preparationTimeMin || '';
        qs('#calories').value = d.calories || '';
    };

    window.deleteDish = async (id) => {
        if (confirm('Delete dish?')) {
            await fetch(`${API.menu}/${id}`, { method: 'DELETE' });
            loadDishes();
        }
    };

    window.resetDishForm = () => {
        qs('#dishForm').reset();
        qs('#dishId').value = '';
        qs('#dishFormTitle').innerText = 'Add / Edit Dish';
    };

    /* ==========================================================================
       ORDER MANAGEMENT
       ========================================================================== */
    const loadOrders = async () => {
        const status = qs('#orderStatusFilter').value;
        const res = await fetch(`${API.orders}${status ? '?status=' + status : ''}`);
        const orders = await res.json();
        const tbody = qs('#ordersTable tbody');
        tbody.innerHTML = '';
        orders.forEach(o => {
            const tr = document.createElement('tr');
            tr.innerHTML = `
                <td>${o.id}</td>
                <td>${o.buyer ? o.buyer.username : '-'}</td>
                <td>${o.restaurant ? o.restaurant.username : '-'}</td>
                <td><span class="badge" style="background:rgba(99,102,241,0.2)">${o.status}</span></td>
                <td>$${o.totalPrice || '0.00'}</td>
                <td>${o.createdAt ? new Date(o.createdAt).toLocaleDateString() : '-'}</td>
                <td>
                    <button class="action-btn" onclick="editOrder(${JSON.stringify(o).replace(/"/g, '&quot;')})">Edit</button>
                    <button class="action-btn delete" onclick="deleteOrder(${o.id})">Del</button>
                </td>
            `;
            tbody.appendChild(tr);
        });
    };

    qs('#orderStatusFilter').addEventListener('change', loadOrders);

    const loadOrderPrerequisites = async () => {
        const cRes = await fetch(`${API.users}?type=Customer`);
        const rRes = await fetch(`${API.users}?type=Restaurant`);
        const dRes = await fetch(`${API.users}?type=Driver`);
        
        const custs = await cRes.json();
        const rests = await rRes.json();
        const drivs = await dRes.json();

        qs('#orderCustomer').innerHTML = custs.map(u => `<option value="${u.id}">${u.username}</option>`).join('');
        qs('#orderRestaurant').innerHTML = rests.map(u => `<option value="${u.id}">${u.username}</option>`).join('');
        qs('#orderDriver').innerHTML = '<option value="">(Assign Driver)</option>' + drivs.map(u => `<option value="${u.id}">${u.username}</option>`).join('');
    };

    qs('#orderForm').addEventListener('submit', async (e) => {
        e.preventDefault();
        const id = qs('#orderId').value;
        const payload = {
            id: id ? parseInt(id) : 0,
            buyer: { id: parseInt(qs('#orderCustomer').value) },
            restaurant: { id: parseInt(qs('#orderRestaurant').value) },
            status: qs('#orderStatus').value || 'PENDING',
            totalPrice: parseFloat(qs('#orderTotal').value || 0),
            specialInstructions: qs('#orderInstructions').value
        };
        const driverId = qs('#orderDriver').value;
        if (driverId) payload.deliveryPerson = { id: parseInt(driverId) };

        await fetch(API.orders, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(payload)
        });
        resetOrderForm();
        loadOrders();
    });

    window.editOrder = (o) => {
        qs('#orderFormTitle').innerText = 'Edit Order #' + o.id;
        qs('#orderId').value = o.id;
        qs('#orderCustomer').value = o.buyer ? o.buyer.id : '';
        qs('#orderRestaurant').value = o.restaurant ? o.restaurant.id : '';
        qs('#orderDriver').value = o.deliveryPerson ? o.deliveryPerson.id : '';
        qs('#orderStatus').value = o.status;
        qs('#orderTotal').value = o.totalPrice || '';
        qs('#orderInstructions').value = o.specialInstructions || '';
    };

    window.deleteOrder = async (id) => {
        if (confirm('Delete order?')) {
            await fetch(`${API.orders}/${id}`, { method: 'DELETE' });
            loadOrders();
        }
    };

    window.resetOrderForm = () => {
        qs('#orderForm').reset();
        qs('#orderId').value = '';
        qs('#orderFormTitle').innerText = 'Create / Edit Order';
    };

    /* ==========================================================================
       COMMUNICATIONS (MESSAGES & REVIEWS)
       ========================================================================== */
    const messageTypeSelect = qs('#messageType');
    const updateMsgFields = () => {
        const isReview = messageTypeSelect.value === 'Review';
        qsa('.type-review').forEach(el => el.classList.toggle('hidden', !isReview));
    };
    messageTypeSelect.addEventListener('change', updateMsgFields);

    const loadMessages = async () => {
        const res = await fetch(API.messages);
        const msgs = await res.json();
        const tbody = qs('#messagesTable tbody');
        tbody.innerHTML = '';
        msgs.forEach(m => {
            const isReview = m.rating !== undefined;
            const tr = document.createElement('tr');
            tr.innerHTML = `
                <td>${m.id}</td>
                <td><span class="badge" style="border: 1px solid var(--accent-2)">${isReview ? 'Review' : 'Message'}</span></td>
                <td>${m.sender ? m.sender.username : '-'}</td>
                <td>${m.receiver ? m.receiver.username : '-'}</td>
                <td title="${m.content}">${m.content.substring(0, 30)}...</td>
                <td>${isReview ? '⭐ ' + m.rating : '-'}</td>
                <td>
                    <button class="action-btn delete" onclick="deleteMessage(${m.id})">Del</button>
                </td>
            `;
            tbody.appendChild(tr);
        });
    };

    const loadMessagePrerequisites = async () => {
        const uRes = await fetch(API.users);
        const oRes = await fetch(API.orders);
        const users = await uRes.json();
        const orders = await oRes.json();

        const userOpts = users.map(u => `<option value="${u.id}">${u.username}</option>`).join('');
        qs('#msgSender').innerHTML = userOpts;
        qs('#msgReceiver').innerHTML = userOpts;
        qs('#msgOrder').innerHTML = '<option value="">(No Order)</option>' + orders.map(o => `<option value="${o.id}">Order #${o.id}</option>`).join('');
    };

    qs('#messageForm').addEventListener('submit', async (e) => {
        e.preventDefault();
        const type = qs('#messageType').value;
        const payload = {
            sender: { id: parseInt(qs('#msgSender').value) },
            receiver: { id: parseInt(qs('#msgReceiver').value) },
            content: qs('#msgContent').value
        };
        const orderId = qs('#msgOrder').value;
        if (orderId) payload.relatedOrder = { id: parseInt(orderId) };

        if (type === 'Review') {
            payload.title = qs('#reviewTitle').value;
            payload.rating = parseInt(qs('#reviewRating').value || 5);
        }

        const endpoint = type === 'Review' ? '/review' : '/message';
        await fetch(`${API.messages}${endpoint}`, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(payload)
        });
        resetMessageForm();
        loadMessages();
    });

    window.deleteMessage = async (id) => {
        if (confirm('Delete message/review?')) {
            await fetch(`${API.messages}/${id}`, { method: 'DELETE' });
            loadMessages();
        }
    };

    window.resetMessageForm = () => {
        qs('#messageForm').reset();
        updateMsgFields();
    };

    // Initial load
    loadUsers();
    loadRestaurantsList();
    loadOrderPrerequisites();
    loadMessagePrerequisites();
    updateVisibleFields();
    updateMsgFields();

});
