export async function getTasks() {
    const response = await fetch("/tasks");

    if (!response.ok) {
        throw new Error("Error while loading tasks: " + response.status);
    }

    return response.json();
}

export async function deleteTask(id) {
    const response = await fetch(`/tasks/${id}`, {
        method: "DELETE"
    });

    if (!response.ok) {
        throw new Error("Error while deleting task: " + response.status);
    }
}

export async function createTask(taskText) {
    const response = await fetch("/tasks", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({
            text: taskText,
            done: false
        })
    });

    if (!response.ok) {
        throw new Error("Error while creating task: " + response.status);
    }

    return response.json();
}

export async function updateTask(task) {
    const response = await fetch(`/tasks/${task.id}`, {
        method: "PUT",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(task)
    });
    if (!response.ok) {
        throw new Error("Error while updating task: " + response.status);
    }

    return response.json();
}




