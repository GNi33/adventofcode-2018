package app.sleigh.service

import app.sleigh.model.IAssemblyStep
import app.sleigh.model.Worker

class TimedStepProcessor(private val assemblySteps: List<IAssemblyStep>, numWorkers: Int) : IStepProcessor {

    var elapsedSeconds = 0
    val workers: MutableSet<Worker> = mutableSetOf()

    init {
        for (workerNum in 1..numWorkers) {
            workers.add(Worker(workerNum))
        }
    }

    override fun workOnSteps(): Set<IAssemblyStep> {

        val firstSteps = getFirstSteps()

        return processSecond(firstSteps.toSet())
    }

    override fun getElapsedTime(): Int {
        return elapsedSeconds
    }

    fun processSecond(openSteps: Set<IAssemblyStep>, processedSteps: Set<IAssemblyStep> = setOf()): Set<IAssemblyStep> {

        val doneTasks = getDoneTasks()

        val stepsProcessed = processedSteps.union(doneTasks)
        val stepsInProcess = getTasksInProgress()

        val doneWorkers = getWorkersWithDoneTasks()
        for (worker in doneWorkers) {
            worker.removeTask()
        }

        val idleWorkers = getIdleWorkers()
        val nextSteps = doneTasks.flatMap {
            it.stepsAfter
        }

        val stepsToProcess = openSteps.union(nextSteps).filter {
            !stepsProcessed.contains(it)
        }.sortedBy {
            it.id
        }.toSet()

        if (stepsToProcess.isEmpty()) {
            return stepsProcessed
        }

        val stepsToStart = stepsToProcess.filter {
            !stepsInProcess.contains(it) && it.arePrerequisitesComplete(stepsToProcess)
        }.sortedBy {
            it.id
        }.toSet()

        if (stepsToStart.isNotEmpty()) {

            val stepsCount = stepsToStart.size

            for (num in 0 until stepsCount) {
                if (idleWorkers.size > num) {
                    idleWorkers[num].addTask(stepsToStart.elementAt(num))
                }
            }
        }

        for (worker in workers) {
            if (worker.hasTaskAssigned()) {
                worker.workOnTask()
            }
        }

        println("$elapsedSeconds - ${stepsProcessed.map { it.id }}")
        for (worker in workers) {
            println("${worker.getName()} - ${worker.assignedTask?.id}")
        }

        // look at workers
        //      -> are there any done steps?
        //      -> add them to the processedSteps list
        //          -> get next steps from the done ones and add them to openSteps

        // look at steps
        //      -> are there steps that are not being worked on?
        //      -> assign steps to idle workers

        elapsedSeconds += 1

        return processSecond(stepsToProcess.toSet(), stepsProcessed)
    }

    private fun getFirstSteps(): List<IAssemblyStep> {
        return assemblySteps.filter {
            it.isFirstStep()
        }.sortedBy { it.id }
    }

    private fun getStepsInProgress(steps: Set<IAssemblyStep>): List<IAssemblyStep> {
        return steps.filter {
            it.isInProgress
        }.sortedBy { it.id }
    }

    private fun getDoneTasks(): Set<IAssemblyStep> {

        return workers.mapNotNull {
            it.assignedTask
        }.filter {
            it.isDone
        }.sortedBy {
            it.id
        }.toSet()
    }

    private fun getTasksInProgress(): Set<IAssemblyStep> {
        return workers.mapNotNull {
            it.assignedTask
        }.filter {
            !it.isDone
        }.sortedBy {
            it.id
        }.toSet()
    }

    private fun getWorkersWithDoneTasks(): List<Worker> {
        return getWorkersWithTasks().filter {
            it.assignedTask!!.isDone
        }
    }

    private fun getWorkersWithTasks(): List<Worker> {
        return workers.filter {
            it.assignedTask != null
        }
    }

    private fun getIdleWorkers(): List<Worker> {
        return workers.filter {
            it.assignedTask == null
        }
    }
}
