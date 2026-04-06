console.log("LOAD TASKS CALLED");

const taskInput = document.getElementById("taskInput");
const addTaskBtn = document.getElementById("addTaskBtn");
const taskList = document.getElementById("taskList");

loadTasks();

console.log(taskInput);
console.log(addTaskBtn);
console.log(taskList);

document.addEventListener("DOMContentLoaded", loadTasks);
addTaskBtn.addEventListener("click", addTask);

async function loadTasks() {
    try {
        const response = await fetch("/tasks");
        const tasks = await response.json();

        taskList.innerHTML = "";

        tasks.forEach(task => {
            renderTask(task);
        });

    } catch (error) {
        console.error("Error task-loading:", error);
    }
}

async function addTask() {
    const taskText = taskInput.value.trim();

    if (taskText === "") {
        alert("Write Task-text");
        return;
    }

    try {
        const response = await fetch("/tasks", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({
                text: taskText
            })
        });

        if (!response.ok) {
            const errorText = await response.text();
            console.error("POST /tasks failed:", response.status, errorText);
            return;
        }

        const newTask = await response.json();

        renderTask(newTask);
        taskInput.value = "";
    } catch (error) {
        console.error("Error task-adding:", error);
    }
}

function renderTask(task) {
    const li = document.createElement("li");

    const span = document.createElement("span");
    span.innerText = task.text;

    const deleteBtn = document.createElement("button");
    deleteBtn.innerText = "Delete";

    deleteBtn.addEventListener("click", async function () {
        try {
            await fetch(`/tasks/${task.id}`, {
                method: "DELETE"
            });
            li.remove();
        } catch (error) {
            console.error("Ошибка удаления задачи:", error);
        }
    });

    li.appendChild(span);
    li.appendChild(deleteBtn);
    taskList.appendChild(li);
}