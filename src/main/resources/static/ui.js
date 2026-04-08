export function clearTaskList(taskList) {
    taskList.innerHTML = "";
}

export function renderTask(task, taskList, onDelete) {
    const li = document.createElement("li");

    const span = document.createElement("span");
    span.innerText = task.text;

    const deleteBtn = document.createElement("button");
    deleteBtn.innerText = "Delete";

    deleteBtn.addEventListener("click", () => {
        onDelete(task.id, li);
    });

    li.appendChild(span);
    li.appendChild(deleteBtn);
    taskList.appendChild(li);
}