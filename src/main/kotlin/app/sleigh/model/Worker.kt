package app.sleigh.model

class Worker(val id: Int) {

    var assignedTask : IAssemblyStep? = null

    fun hasTaskAssigned(): Boolean {
        return assignedTask != null
    }

    fun workOnTask() {

        if (assignedTask == null) {
            throw Exception("No Task assigned")
        }

        assignedTask?.let {
            it.isInProgress = true
            it.progress += 1

            if (it.progress == it.duration) {
                it.isDone = true
            }
        }
    }

    fun addTask(task: IAssemblyStep) {
        assignedTask = task
    }

    fun removeTask() {
        assignedTask = null

    }

    fun getName(): String {
        return "Worker $id"
    }
}