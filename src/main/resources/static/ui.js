export function clearTaskList(taskList) {
    taskList.innerHTML = "";
}

export function renderTask(task, taskList, onDelete, onToggleDone) {
    const li = document.createElement("li");

    const span = document.createElement("span");
    span.innerText = task.text;

    if (task.done) {
        span.classList.add("done");
    }

    span.addEventListener("click", () => {
        onToggleDone(task, span);
    });

    const deleteBtn = document.createElement("button");
    deleteBtn.innerText = "Delete";

    deleteBtn.addEventListener("click", () => {
        onDelete(task.id, li);
    });

    li.appendChild(span);
    li.appendChild(deleteBtn);
    taskList.appendChild(li);
}