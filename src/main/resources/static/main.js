import { getTasks, createTask, deleteTask, updateTask } from "./api.js";
import { clearTaskList, renderTask } from "./ui.js";

const taskInput = document.getElementById("taskInput");
const addBtn = document.getElementById("addBtn");
const taskList = document.getElementById("taskList");

addBtn.addEventListener("click", addTask);

async function loadTasks() {
    try {
        const tasks = await getTasks();
        clearTaskList(taskList);

        tasks.forEach(task => {
            renderTask(task, taskList, handleDelete, handleToggleDone);
        });
    } catch (error) {
        console.log("Error while loading tasks:", error);
    }
}

async function addTask() {
    const taskText = taskInput.value.trim();

    if (taskText === "") {
        alert("Write task text");
        return;
    }

    try {
        const newTask = await createTask(taskText);
        renderTask(newTask, taskList, handleDelete, handleToggleDone);
        taskInput.value = "";
    } catch (error) {
        console.log("Error while adding task:", error);
    }
}

async function handleDelete(id, liElement) {
    try {
        await deleteTask(id);
        liElement.remove();
    } catch (error) {
        console.log("Error while deleting task:", error);
    }
}

async function handleToggleDone(task, spanElement) {
    try {
        const updatedTask = {
            ...task,
            done: !task.done
        };

        const savedTask = await updateTask(updatedTask);
        task.done = savedTask.done;

        if (task.done) {
            spanElement.classList.add("done");
        } else {
            spanElement.classList.remove("done");
        }
    } catch (error) {
        console.log("Error while updating task:", error);
    }
}

loadTasks();