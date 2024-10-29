document.addEventListener("DOMContentLoaded", function () {
    const eventList = document.getElementById("eventList");
    const addEventForm = document.getElementById("addEventForm");

    async function loadEvents() {
        eventList.innerHTML = '';
        const response = await fetch('/api/events');
        const events = await response.json();
        events.forEach(event => {
            const li = document.createElement("li");
            li.innerHTML = `<strong>${event.title}</strong><br>
                            ${event.description}<br>
                            Date: ${event.eventDate}<br>
                            <button onclick="deleteEvent(${event.id})">Delete</button>`;
            eventList.appendChild(li);
        });
    }

    addEventForm.addEventListener("submit", async function (e) {
        e.preventDefault();
        const title = document.getElementById("title").value;
        const description = document.getElementById("description").value;
        const date = document.getElementById("date").value;

        await fetch('/api/events', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                title: title,
                description: description,
                eventDate: date
            })
        });

        addEventForm.reset();
        loadEvents();
    });

    window.deleteEvent = async function (id) {
        await fetch(`/api/events/${id}`, { method: 'DELETE' });
        loadEvents();
    };

    loadEvents();
});